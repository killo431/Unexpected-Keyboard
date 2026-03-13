package juloo.keyboard2;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import juloo.keyboard2.DirectBootAwarePreferences;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Dialog activity for AI-powered text and code generation via the Gemini API.
 *
 * Users add the key value "gemini" to a layout to launch this activity.
 * An API key obtained from https://aistudio.google.com/app/apikey is required
 * and is stored in the keyboard's own SharedPreferences so it persists across
 * sessions.  The activity follows the same dialog/theme pattern as the
 * existing Base64Activity and CalculatorActivity helpers.
 */
public class GeminiActivity extends Activity
{
  /** SharedPreferences key for the Gemini API key (shared with the Settings screen). */
  private static final String KEY_API_KEY = "gemini_api_key";

  /**
   * Connection timeout: 30 s (time to establish the TCP connection).
   * Read timeout: 60 s (time to wait for the full AI response body).
   * These values give the model adequate time to generate longer outputs
   * while still providing a reasonable failure signal to the user.
   */
  private static final int CONNECT_TIMEOUT_MS = 30_000;
  private static final int READ_TIMEOUT_MS    = 60_000;

  /** Base REST endpoint for Gemini API. */
  private static final String GEMINI_API_BASE_URL =
    "https://generativelanguage.googleapis.com/v1beta/models/";


  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    getWindow().setSoftInputMode(
        android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

    // ── Theme colors ──────────────────────────────────────────────────────────
    int labelColor = 0xFF000000;
    int bgColor    = 0xFFFFFFFF;
    int keyColor   = 0xFFCCCCCC;
    Config config  = Config.globalConfig();
    if (config != null)
    {
      android.view.ContextThemeWrapper wrap =
          new android.view.ContextThemeWrapper(this, config.theme);
      android.content.res.TypedArray ta = wrap.obtainStyledAttributes(
          new int[]{ R.attr.colorLabel, R.attr.colorKeyboard, R.attr.colorKey });
      labelColor = ta.getColor(0, labelColor);
      bgColor    = ta.getColor(1, bgColor);
      keyColor   = ta.getColor(2, keyColor);
      ta.recycle();
    }
    final int finalLabelColor = labelColor;
    final int finalKeyColor   = keyColor;

    int dp  = (int) getResources().getDisplayMetrics().density;
    int pad = 16 * dp;

    // ── Root layout ───────────────────────────────────────────────────────────
    ScrollView scrollView = new ScrollView(this);
    LinearLayout layout = new LinearLayout(this);
    layout.setOrientation(LinearLayout.VERTICAL);
    layout.setPadding(pad, pad / 2, pad, pad / 2);
    layout.setBackgroundColor(bgColor);
    scrollView.addView(layout);

    // ── Title ─────────────────────────────────────────────────────────────────
    TextView title = new TextView(this);
    title.setText("Gemini AI");
    title.setTextSize(18);
    title.setTextColor(labelColor);
    title.setGravity(Gravity.CENTER);
    layout.addView(title);

    // ── Mode selector (Text / Code) ───────────────────────────────────────────
    RadioGroup modeGroup = new RadioGroup(this);
    modeGroup.setOrientation(RadioGroup.HORIZONTAL);

    RadioButton textBtn = new RadioButton(this);
    textBtn.setText("Generate Text");
    textBtn.setTextColor(labelColor);
    textBtn.setId(View.generateViewId());

    RadioButton codeBtn = new RadioButton(this);
    codeBtn.setText("Generate Code");
    codeBtn.setTextColor(labelColor);
    codeBtn.setId(View.generateViewId());

    modeGroup.addView(textBtn);
    modeGroup.addView(codeBtn);
    textBtn.setChecked(true);
    layout.addView(modeGroup);

    // ── Prompt ────────────────────────────────────────────────────────────────
    TextView promptLabel = new TextView(this);
    promptLabel.setText("Prompt:");
    promptLabel.setTextColor(labelColor);
    layout.addView(promptLabel);

    final EditText promptField = new EditText(this);
    promptField.setHint("Enter your prompt…");
    promptField.setInputType(
        InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
    promptField.setMinLines(3);
    promptField.setMaxLines(6);
    promptField.setGravity(Gravity.TOP | Gravity.START);
    promptField.setTextColor(labelColor);
    promptField.setHintTextColor((labelColor & 0x00FFFFFF) | 0x80000000);
    promptField.setBackgroundResource(R.drawable.rect_rounded);
    promptField.setBackgroundTintList(
        android.content.res.ColorStateList.valueOf(keyColor));
    layout.addView(promptField);

    // ── API Key ───────────────────────────────────────────────────────────────
    TextView apiKeyLabel = new TextView(this);
    apiKeyLabel.setText("Gemini API Key:");
    apiKeyLabel.setTextColor(labelColor);
    apiKeyLabel.setPadding(0, pad / 2, 0, 0);
    layout.addView(apiKeyLabel);

    SharedPreferences prefs = DirectBootAwarePreferences.get_shared_preferences(this);
    final EditText apiKeyField = new EditText(this);
    apiKeyField.setText(prefs.getString(KEY_API_KEY, ""));
    apiKeyField.setHint("Paste your API key here…");
    apiKeyField.setInputType(
        InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    apiKeyField.setTextColor(labelColor);
    apiKeyField.setHintTextColor((labelColor & 0x00FFFFFF) | 0x80000000);
    apiKeyField.setBackgroundResource(R.drawable.rect_rounded);
    apiKeyField.setBackgroundTintList(
        android.content.res.ColorStateList.valueOf(keyColor));
    layout.addView(apiKeyField);

    // ── Generate button ───────────────────────────────────────────────────────
    final Button generateBtn = new Button(this);
    generateBtn.setText("Generate");
    generateBtn.setTextColor(labelColor);
    generateBtn.setBackgroundTintList(
        android.content.res.ColorStateList.valueOf(keyColor));
    layout.addView(generateBtn);

    // ── Output ────────────────────────────────────────────────────────────────
    TextView outputLabel = new TextView(this);
    outputLabel.setText("Result:");
    outputLabel.setTextColor(labelColor);
    outputLabel.setPadding(0, pad / 2, 0, 0);
    layout.addView(outputLabel);

    final EditText outputField = new EditText(this);
    outputField.setHint("Generated text will appear here…");
    outputField.setInputType(
        InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
    outputField.setMinLines(4);
    outputField.setMaxLines(12);
    outputField.setGravity(Gravity.TOP | Gravity.START);
    outputField.setKeyListener(null); // read-only
    outputField.setTextColor(labelColor);
    outputField.setHintTextColor((labelColor & 0x00FFFFFF) | 0x80000000);
    outputField.setBackgroundResource(R.drawable.rect_rounded);
    outputField.setBackgroundTintList(
        android.content.res.ColorStateList.valueOf(keyColor));
    layout.addView(outputField);

    // ── Action buttons ────────────────────────────────────────────────────────
    LinearLayout actionRow = new LinearLayout(this);
    actionRow.setOrientation(LinearLayout.HORIZONTAL);
    actionRow.setWeightSum(3);

    Button closeBtn = makeButton("Close",  labelColor, keyColor, 1);
    Button copyBtn  = makeButton("Copy",   labelColor, keyColor, 1);
    Button insertBtn = makeButton("Insert", labelColor, keyColor, 1);
    actionRow.addView(closeBtn);
    actionRow.addView(copyBtn);
    actionRow.addView(insertBtn);
    layout.addView(actionRow);

    // ── Note ──────────────────────────────────────────────────────────────────
    TextView note = new TextView(this);
    note.setText("Get a free API key at aistudio.google.com/app/apikey");
    note.setTextSize(11);
    note.setTextColor((labelColor & 0x00FFFFFF) | 0xA0000000);
    note.setPadding(0, pad / 2, 0, 0);
    layout.addView(note);

    setContentView(scrollView);
    promptField.requestFocus();

    // ── Listeners ─────────────────────────────────────────────────────────────
    Handler mainHandler = new Handler(Looper.getMainLooper());

    generateBtn.setOnClickListener(v ->
    {
      String prompt = promptField.getText().toString().trim();
      String apiKey = apiKeyField.getText().toString().trim();
      if (prompt.isEmpty())
      {
        Toast.makeText(this, "Please enter a prompt.", Toast.LENGTH_SHORT).show();
        return;
      }
      if (apiKey.isEmpty())
      {
        Toast.makeText(this, "Please enter your Gemini API key.", Toast.LENGTH_SHORT).show();
        return;
      }
      // Persist the API key for convenience
      prefs.edit().putString(KEY_API_KEY, apiKey).apply();

      // Get the selected model from config
      Config config = Config.globalConfig();
      String model = (config != null) ? config.gemini_model : "gemini-3.5-flash";

      boolean isCode = codeBtn.isChecked();
      String fullPrompt = isCode
          ? "Generate code for the following. Return only the code without any explanation:\n\n" + prompt
          : prompt;

      generateBtn.setEnabled(false);
      outputField.setText("Generating…");

      new Thread(() ->
      {
        String result = callGeminiApi(apiKey, fullPrompt, model);
        mainHandler.post(() ->
        {
          generateBtn.setEnabled(true);
          outputField.setText(result);
        });
      }).start();
    });

    closeBtn.setOnClickListener(v -> finish());

    copyBtn.setOnClickListener(v ->
    {
      String text = outputField.getText().toString();
      if (text.isEmpty() || text.equals("Generating…")) return;
      ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
      cm.setPrimaryClip(ClipData.newPlainText("Gemini Result", text));
      Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
    });

    insertBtn.setOnClickListener(v ->
    {
      String text = outputField.getText().toString();
      if (text.isEmpty() || text.equals("Generating…") || text.startsWith("Error:")) return;
      finish();
      new Handler().postDelayed(() -> ClipboardHistoryService.paste(text), 200);
    });
  }

  // ── Helpers ─────────────────────────────────────────────────────────────────

  private Button makeButton(String label, int textColor, int bgColor, float weight)
  {
    Button btn = new Button(this);
    btn.setText(label);
    btn.setLayoutParams(new LinearLayout.LayoutParams(
        0, LinearLayout.LayoutParams.WRAP_CONTENT, weight));
    btn.setTextColor(textColor);
    btn.setBackgroundTintList(android.content.res.ColorStateList.valueOf(bgColor));
    return btn;
  }

  /**
   * Performs a synchronous POST to the Gemini REST API.
   * Must be called from a background thread.
   *
   * @param apiKey  The user's Gemini API key.
   * @param prompt  The full prompt text to send.
   * @param model   The Gemini model to use (e.g., "gemini-3.5-flash").
   * @return The generated text, or an "Error: …" message on failure.
   */
  private String callGeminiApi(String apiKey, String prompt, String model)
  {
    try
    {
      String apiUrl = GEMINI_API_BASE_URL + model + ":generateContent?key=" + apiKey;
      URL url = new URL(apiUrl);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
      conn.setDoOutput(true);
      conn.setConnectTimeout(CONNECT_TIMEOUT_MS);
      conn.setReadTimeout(READ_TIMEOUT_MS);

      // Build request body
      JSONObject part = new JSONObject();
      part.put("text", prompt);
      JSONArray parts = new JSONArray();
      parts.put(part);
      JSONObject content = new JSONObject();
      content.put("parts", parts);
      JSONArray contents = new JSONArray();
      contents.put(content);
      JSONObject body = new JSONObject();
      body.put("contents", contents);

      byte[] bodyBytes = body.toString().getBytes(StandardCharsets.UTF_8);
      try (OutputStream os = conn.getOutputStream())
      {
        os.write(bodyBytes);
      }

      int status = conn.getResponseCode();
      StringBuilder sb = new StringBuilder();
      try (BufferedReader br = new BufferedReader(new InputStreamReader(
          status >= 400 ? conn.getErrorStream() : conn.getInputStream(),
          StandardCharsets.UTF_8)))
      {
        String line;
        while ((line = br.readLine()) != null)
          sb.append(line);
      }

      if (status != 200)
        return "Error " + status + ": " + sb.toString();

      // Parse: candidates[0].content.parts[0].text
      JSONObject response = new JSONObject(sb.toString());
      return response.getJSONArray("candidates")
                     .getJSONObject(0)
                     .getJSONObject("content")
                     .getJSONArray("parts")
                     .getJSONObject(0)
                     .getString("text");
    }
    catch (Exception e)
    {
      return "Error: " + e.getMessage();
    }
  }
}

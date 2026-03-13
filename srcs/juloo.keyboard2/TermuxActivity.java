package juloo.keyboard2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Dialog activity that sends a shell command or multi-line script to Termux
 * for execution via the {@code com.termux.RUN_COMMAND} service intent.
 *
 * Users add the key value "termux_run" to a layout to launch this activity.
 *
 * Prerequisites (user must perform these once in Termux):
 *   1. Install Termux from F-Droid or the Play Store.
 *   2. In Termux, run: {@code termux-setup-storage}
 *   3. In Termux ≥ 0.109, enable "Allow External Apps" under
 *      Termux → Settings → Allow External Apps (or via
 *      {@code touch ~/.termux/allow-external-apps}).
 *
 * The activity follows the same dialog/theme pattern as Base64Activity.
 */
public class TermuxActivity extends Activity
{
  /** Termux service class that handles the RUN_COMMAND intent. */
  private static final String TERMUX_SERVICE =
    "com.termux.app.RunCommandService";

  /** Intent action recognised by Termux's RunCommandService. */
  private static final String ACTION_RUN_COMMAND =
    "com.termux.RUN_COMMAND";

  /** Absolute path to the bash binary inside Termux's usr tree. */
  private static final String BASH_PATH =
    "/data/data/com.termux/files/usr/bin/bash";

  /** Default working directory (Termux home). */
  private static final String TERMUX_HOME =
    "/data/data/com.termux/files/home";

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
    title.setText("Run in Termux");
    title.setTextSize(18);
    title.setTextColor(labelColor);
    title.setGravity(Gravity.CENTER);
    layout.addView(title);

    // ── Command / script input ────────────────────────────────────────────────
    TextView cmdLabel = new TextView(this);
    cmdLabel.setText("Command / Script:");
    cmdLabel.setTextColor(labelColor);
    layout.addView(cmdLabel);

    final EditText cmdField = new EditText(this);
    cmdField.setHint("e.g.  echo Hello from Termux!\npwd\nls -la");
    cmdField.setInputType(
        InputType.TYPE_CLASS_TEXT
        | InputType.TYPE_TEXT_FLAG_MULTI_LINE
        | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
    cmdField.setMinLines(5);
    cmdField.setMaxLines(12);
    cmdField.setGravity(Gravity.TOP | Gravity.START);
    cmdField.setTextColor(labelColor);
    cmdField.setHintTextColor((labelColor & 0x00FFFFFF) | 0x80000000);
    cmdField.setBackgroundResource(R.drawable.rect_rounded);
    cmdField.setBackgroundTintList(
        android.content.res.ColorStateList.valueOf(keyColor));
    layout.addView(cmdField);

    // ── Options ───────────────────────────────────────────────────────────────
    final CheckBox showTerminal = new CheckBox(this);
    showTerminal.setText("Show in Termux terminal window");
    showTerminal.setTextColor(labelColor);
    showTerminal.setChecked(true);
    layout.addView(showTerminal);

    // ── Status line ───────────────────────────────────────────────────────────
    final TextView statusView = new TextView(this);
    statusView.setText("Ready.");
    statusView.setTextColor(labelColor);
    statusView.setPadding(0, pad / 4, 0, 0);
    layout.addView(statusView);

    // ── Buttons ───────────────────────────────────────────────────────────────
    LinearLayout btnRow = new LinearLayout(this);
    btnRow.setOrientation(LinearLayout.HORIZONTAL);
    btnRow.setWeightSum(2);

    Button runBtn   = makeButton("Run in Termux", labelColor, keyColor, 1);
    Button closeBtn = makeButton("Close",         labelColor, keyColor, 1);
    btnRow.addView(runBtn);
    btnRow.addView(closeBtn);
    layout.addView(btnRow);

    // ── Usage note ────────────────────────────────────────────────────────────
    TextView note = new TextView(this);
    note.setText(
        "Requires Termux (F-Droid) with \"Allow External Apps\" enabled.\n"
        + "In Termux, run:  touch ~/.termux/allow-external-apps");
    note.setTextSize(11);
    note.setTextColor((labelColor & 0x00FFFFFF) | 0xA0000000);
    note.setPadding(0, pad / 2, 0, 0);
    layout.addView(note);

    setContentView(scrollView);
    cmdField.requestFocus();

    // ── Listeners ─────────────────────────────────────────────────────────────
    runBtn.setOnClickListener(v ->
    {
      String command = cmdField.getText().toString().trim();
      if (command.isEmpty())
      {
        Toast.makeText(this, "Please enter a command.", Toast.LENGTH_SHORT).show();
        return;
      }
      runInTermux(command, showTerminal.isChecked(), statusView);
    });

    closeBtn.setOnClickListener(v -> finish());
  }

  // ── Helpers ─────────────────────────────────────────────────────────────────

  /**
   * Sends a shell command string to Termux via the {@code com.termux.RUN_COMMAND}
   * service intent.  The command is executed by {@code bash -c <command>}.
   *
   * @param command       The shell command or script text.
   * @param showTerminal  When {@code true} the Termux terminal window is shown.
   * @param statusView    TextView updated with the dispatch result.
   */
  private void runInTermux(String command, boolean showTerminal, TextView statusView)
  {
    try
    {
      Intent intent = new Intent();
      intent.setClassName("com.termux", TERMUX_SERVICE);
      intent.setAction(ACTION_RUN_COMMAND);
      intent.putExtra("com.termux.RUN_COMMAND_PATH", BASH_PATH);
      intent.putExtra("com.termux.RUN_COMMAND_ARGUMENTS", new String[]{ "-c", command });
      intent.putExtra("com.termux.RUN_COMMAND_WORKDIR", TERMUX_HOME);
      intent.putExtra("com.termux.RUN_COMMAND_TERMINAL", showTerminal);
      // FLAG_ACTIVITY_NEW_TASK is required when starting components from a
      // non-Activity context, but here we call startService from an Activity.
      startService(intent);
      statusView.setText("Sent to Termux ✓");
      Toast.makeText(this, "Command sent to Termux", Toast.LENGTH_SHORT).show();
    }
    catch (Exception e)
    {
      statusView.setText("Error: " + e.getMessage());
      Toast.makeText(this,
          "Could not reach Termux. Is it installed and \"Allow External Apps\" enabled?",
          Toast.LENGTH_LONG).show();
    }
  }

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
}

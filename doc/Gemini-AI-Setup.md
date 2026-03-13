# Gemini AI — Setup Guide

Unexpected Keyboard includes a built-in dialog for **AI-powered text and code generation** using Google's Gemini API. Once set up, you can tap a single key on your keyboard, type a prompt, and have the result inserted directly into any app.

---

## What it does

Pressing the `gemini` key opens a dialog where you can:

- **Generate Text** — Write a draft, rephrase a sentence, translate a phrase, summarise content, or ask any open-ended question.
- **Generate Code** — Describe what code you need and receive only the code (no explanation), ready to paste into an editor or terminal.

Results can be:
- **Inserted** directly into the app where your cursor is.
- **Copied** to the Android clipboard.
- **Dismissed** with Close.

The model used is **Gemini 2.0 Flash**, Google's fast, low-latency model that is available for free under the Gemini API free tier.

---

## Prerequisites

- An Android device running Android 7 (API 24) or later.
- An internet connection (Wi-Fi or mobile data) when generating text.
- A Google account (required to obtain a free API key).

No payment method is required for the free tier.

---

## Step 1 — Get a free Gemini API key

1. Open a browser and go to **https://aistudio.google.com/app/apikey**
2. Sign in with your Google account if prompted.
3. Click **Create API key**.
4. Select an existing Google Cloud project, or click **Create API key in new project** — either works.
5. The key is displayed as a long string beginning with `AIza`. Copy it to your clipboard.

> **Keep your key private.** Anyone with your API key can make requests on your behalf. Do not share it or post it publicly.

---

## Step 2 — Add the `gemini` key to a layout

The `gemini` key value is what connects a key press to the Gemini dialog. You need to add it to whichever layout you use.

### Option A — Add it as an extra key (easiest)

1. Open Unexpected Keyboard settings (swipe the gear icon, or open the app).
2. Tap **Layout**, then tap **Extra keys**.
3. Scroll to find a free slot, or tap **Add** to add a new one.
4. Type `gemini` as the key value and save.

The key will appear in the bottom row of your keyboard.

### Option B — Add it to a custom layout

If you already use a custom layout file, add a key with `c="gemini"` where you want it:

```xml
<key c="gemini" />
```

For example, to put it in the bottom row near the spacebar:

```xml
<row>
    <key c="gemini" />
    <key c="space" w="5" />
    <key c="action" />
</row>
```

See [Custom-layouts.md](Custom-layouts.md) for a full guide to editing layouts.

---

## Step 3 — Enter your API key

The first time you open the Gemini dialog (by pressing the `gemini` key), you will see a **Gemini API Key** field. Paste your key there.

The key is saved automatically when you tap **Generate**, so you will not need to enter it again.

### Saving the key in advance via Settings

You can also save the key before using the dialog:

1. Open Unexpected Keyboard settings.
2. Scroll to the **AI & Scripting** category near the bottom.
3. Tap **Gemini API Key** and paste your key.
4. Tap **OK**.

The same key is used by both the settings screen and the dialog.

---

## Using the Gemini dialog

### Generating text

1. Tap the `gemini` key on your keyboard.
2. Select **Generate Text** (selected by default).
3. Type your prompt in the **Prompt** field. Examples:
   - `Write a polite follow-up email for a job interview`
   - `Translate "good morning" into French, Spanish, and Japanese`
   - `Summarise the key points of the GNU GPL licence in two sentences`
4. Tap **Generate**.
5. The result appears in the **Result** field within a few seconds.
6. Tap **Insert** to paste it at your cursor, or **Copy** to copy it to the clipboard.

### Generating code

1. Tap the `gemini` key on your keyboard.
2. Select **Generate Code**.
3. Describe the code you need. Examples:
   - `Python function to read a JSON file and print each key-value pair`
   - `Bash one-liner to find all .log files modified in the last 24 hours and delete them`
   - `JavaScript fetch request to POST JSON data to an API endpoint`
4. Tap **Generate**.
5. The result contains only the code — no prose explanation.
6. Tap **Insert** to paste it directly into an editor or the Termux terminal, or **Copy** to copy it.

---

## Troubleshooting

| Symptom | Cause | Fix |
|---------|-------|-----|
| `Error 400: API key not valid` | The API key was entered incorrectly or has been deleted. | Return to Google AI Studio, copy the key again, and re-enter it in the dialog or Settings. |
| `Error 429: Resource has been exhausted` | You have exceeded the free-tier rate limit. | Wait a minute and try again. The free tier allows 15 requests per minute and 1 500 requests per day. |
| `Error 403: …` | The API is not enabled for your project, or the key is restricted. | In Google Cloud Console, ensure the "Generative Language API" is enabled for your project. |
| Result shows `Error: …` with a network message | The device has no internet access, or the connection timed out. | Check Wi-Fi or mobile data and try again. |
| The `gemini` key does not appear on the keyboard | The key has not been added to the active layout. | Follow Step 2 above. |
| API key field is empty after restarting | The key is stored in shared preferences. This should not happen normally. | Re-enter the key via Settings → AI & Scripting → Gemini API Key. |

---

## Privacy notes

- Your **prompt text** and **API key** are sent to Google's servers (`generativelanguage.googleapis.com`) over HTTPS for each request.
- The API key is stored in the keyboard's private app storage on your device; it is not synced or transmitted elsewhere.
- Review [Google's Gemini API privacy policy](https://ai.google.dev/gemini-api/terms) for details on how prompt data is handled.
- To stop making requests entirely, remove the `gemini` key from your layout or clear the API key in Settings.

---

## See also

- [Possible-key-values.md](Possible-key-values.md) — full reference for key values, including `gemini` and `termux_run`
- [Custom-layouts.md](Custom-layouts.md) — how to build or modify a keyboard layout
- [Termux-Run-Setup.md](Termux-Run-Setup.md) — run shell scripts from your keyboard using Termux

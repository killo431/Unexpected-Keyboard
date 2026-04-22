# 1Password Integration

Unexpected Keyboard supports seamless integration with 1Password through Android's Autofill Framework, as well as a dedicated `1password` key that launches 1Password directly.

## Features

### 1. Inline Autofill Suggestions (Android 11+)

When you tap on a password or username field, 1Password suggestions appear directly in the keyboard UI, letting you fill credentials without leaving the current app.

### 2. Automatic Autofill Key

The keyboard automatically detects password, username, and email fields and displays a convenient autofill button (`🔑`) in a prominent position.

### 3. Dedicated 1Password Key (`1P`)

A dedicated `1password` key is available. Pressing it triggers the Android Autofill Framework if 1Password is set as the autofill provider; otherwise it launches 1Password directly.

### 4. Smart Field Detection

The keyboard recognizes autofill fields by analyzing:
- Input types (password, email, visible password)
- Autofill hints provided by apps
- Field names and metadata

## Setup Instructions

### Step 1: Install 1Password

Install **1Password** from [Google Play](https://play.google.com/store/apps/details?id=com.onepassword.android) and sign in to your account.

### Step 2: Enable 1Password as Autofill Service

1. Open **Android Settings** → **Passwords & accounts** (or **System** → **Languages & input** → **Autofill service** — exact path varies by Android version and device)
2. Select **Autofill service**
3. Choose **1Password**
4. Follow the on-screen prompts to grant the required permissions

### Step 3: Enable Inline Autofill in 1Password (Android 11+)

1. Open the **1Password app**
2. Go to **Settings** → **Autofill**
3. Make sure **Autofill** is enabled
4. Enable **Inline autofill** so suggestions appear directly in the keyboard strip

### Step 4: Add the 1Password Key to the Keyboard (Optional)

The keyboard ships with a `1password` key that can be placed on any layout:

1. Open **Unexpected Keyboard Settings** → **Add keys to keyboard** → **Select keys**
2. Enable `1password` (shown as `1P`)
3. The key now appears on your keyboard; tapping it triggers 1Password autofill

### Step 5: Test the Integration

1. Open any app with a login form (e.g., a browser or banking app)
2. Tap the username or password field
3. You should see:
   - An autofill key (`🔑` or "auto") added automatically to the keyboard
   - 1Password suggestions appearing inline in the keyboard strip (Android 11+)
4. Tap a suggestion or the autofill key to fill your credentials

## Usage

### Using the Automatic Autofill Key

When you focus a password or username field:

1. The autofill key (`🔑`) appears automatically on the keyboard
2. Tap it to open the 1Password autofill picker
3. Select the item you want — the fields are filled automatically

### Using Inline Suggestions (Android 11+)

On Android 11 and later:

1. 1Password suggestions appear as chips/buttons in the keyboard suggestion strip
2. Tap any suggestion to fill the credential immediately — no separate dialog needed

### Using the Dedicated `1P` Key

The `1password` key (label `1P`) works as follows:

- **If 1Password is set as the autofill service:** triggers the system autofill picker directly from the keyboard
- **If 1Password is not the autofill service:** launches the 1Password app so you can copy credentials manually

### Manual Access

You can always trigger autofill manually:

1. Add the `autofill` key to your keyboard via **Settings** → **Add keys to keyboard** → **Select keys** → Enable `autofill`
2. Long-press a text field → Select **Autofill** from the context menu

## Compatibility

### Android Version Requirements

- **Android 8.0 (API 26)** and above: Basic autofill support (autofill service + `1P` key fallback)
- **Android 11 (API 30)** and above: Inline autofill suggestions in keyboard strip

### Other Supported Password Managers

The autofill integration works with any password manager that supports Android's Autofill Framework:

- 1Password
- Bitwarden (see [Bitwarden-Autofill-Setup.md](Bitwarden-Autofill-Setup.md))
- LastPass
- Dashlane
- Google Password Manager
- KeePassDX
- And others

## Troubleshooting

### Autofill Key Not Appearing

If the autofill key doesn't appear automatically:

1. Verify that the app you are logging into properly declares autofill hints in its input fields
2. Manually add the autofill key: **Settings** → **Add keys to keyboard** → **Select keys** → Enable `autofill`
3. Long-press the field and select **Autofill** from the context menu

### No Inline Suggestions Showing

If inline suggestions don't appear (Android 11+):

1. Confirm your Android version is 11 or higher
2. Check that **Inline autofill** is enabled in 1Password → **Settings** → **Autofill**
3. Verify that 1Password is selected as the autofill service in Android Settings
4. Restart the keyboard service or the app and try again

### The `1P` Key Opens the App Instead of Filling

This means 1Password is not currently set as the system autofill service. Either:

1. Set 1Password as the autofill service (Step 2 above), **or**
2. Use the app that opens to copy the password manually

### Autofill Not Working in Some Apps

Some apps opt out of the autofill framework:

1. Enable **Accessibility-based fill** in 1Password → **Settings** → **Autofill** for broader compatibility
2. Use the manual autofill key or long-press menu as a fallback
3. Contact the app developer to request proper autofill support

## Technical Details

### How the `1password` Key Works

The `1password` key (event `LAUNCH_1PASSWORD`) follows this logic in `Keyboard2.java`:

1. On **API 26+**, it first requests the system autofill picker via `performContextMenuAction(android.R.id.autofill)` — this works with any configured autofill provider, not just 1Password.
2. If that call returns `false` (no autofill service is active), it falls back to launching the 1Password package (`com.onepassword.android`) with an explicit Intent.
3. If 1Password is not installed, the fallback is a no-op and a debug message is logged.

### Field Detection

The keyboard detects autofill fields by checking:

1. **Input Type Variations:**
   - `TYPE_TEXT_VARIATION_PASSWORD`
   - `TYPE_TEXT_VARIATION_WEB_PASSWORD`
   - `TYPE_TEXT_VARIATION_VISIBLE_PASSWORD`
   - `TYPE_TEXT_VARIATION_EMAIL_ADDRESS`
   - `TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS`

2. **Autofill Hints (API 26+):**
   - Fields containing "password" in hints
   - Fields containing "username", "email", "userid", or "login" in hints

3. **EditorInfo Metadata:**
   - Presence of an `autofillId`
   - Hint text analysis

### Privacy

The keyboard integration:
- Does **not** store or access your passwords
- Only communicates with the system autofill service
- Works entirely through Android's secure autofill framework
- Maintains the same privacy guarantees as Unexpected Keyboard

## See Also

- [Possible Key Values](Possible-key-values.md) — Full list of available keys including `1password` and `autofill`
- [Features](Features.md) — Overview of all keyboard features
- [Bitwarden Autofill Setup](Bitwarden-Autofill-Setup.md) — Guide for Bitwarden users
- [1Password Help Center](https://support.1password.com/android/) — Official 1Password for Android documentation

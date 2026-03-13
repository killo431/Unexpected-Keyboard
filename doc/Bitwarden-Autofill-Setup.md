# Bitwarden Autofill Integration

Unexpected Keyboard now supports seamless integration with Bitwarden and other password managers through Android's Autofill Framework.

## Features

### 1. Inline Autofill Suggestions (Android 11+)

When you tap on a password or username field, Bitwarden suggestions will appear directly in the keyboard UI (API 30+), allowing quick access to your credentials without leaving the app.

### 2. Automatic Autofill Key

The keyboard automatically detects password, username, and email fields and displays a convenient autofill button (`🔑`) in a prominent position.

### 3. Smart Field Detection

The keyboard intelligently recognizes autofill fields by analyzing:
- Input types (password, email, visible password)
- Autofill hints provided by apps
- Field names and metadata

## Setup Instructions

### Step 1: Enable Bitwarden Autofill Service

1. Open **Android Settings** → **Passwords & accounts** (or **System** → **Languages & input** → **Autofill service**)
2. Select **Autofill service**
3. Choose **Bitwarden**

### Step 2: Configure Bitwarden

1. Open the **Bitwarden app**
2. Go to **Settings** → **Auto-fill Services**
3. Enable **Auto-fill Services** (Android 8+)
4. Enable **Inline Autofill** (Android 11+) for keyboard integration
5. Optionally enable **Use Accessibility** for broader app compatibility

### Step 3: Test the Integration

1. Open any app with a login form (e.g., browser, banking app)
2. Tap on the username or password field
3. You should see:
   - An autofill key (`🔑` or "auto") automatically added to your keyboard
   - Bitwarden suggestions appearing inline in the keyboard (Android 11+)
4. Tap the autofill key or a suggestion to fill in your credentials

## Usage

### Using the Autofill Key

When typing in a password or username field:

1. The autofill key appears automatically on your keyboard
2. Tap the autofill key to open the Bitwarden autofill picker
3. Select the credentials you want to use
4. The fields are filled automatically

### Using Inline Suggestions (Android 11+)

On Android 11 and later:

1. Bitwarden suggestions appear as chips/buttons in the keyboard
2. Tap any suggestion to fill the credential immediately
3. No need to open a separate picker dialog

### Manual Access

You can always trigger autofill manually:

1. Add the autofill key to your keyboard via **Settings** → **Add keys to keyboard** → **Select keys** → Enable `autofill`
2. Long-press a text field → Select **Autofill** from the context menu

## Compatibility

### Android Version Requirements

- **Android 8.0 (API 26)** and above: Basic autofill support
- **Android 11 (API 30)** and above: Inline autofill suggestions in keyboard

### Supported Password Managers

While this guide focuses on Bitwarden, the integration works with any password manager that supports Android's Autofill Framework, including:

- Bitwarden
- 1Password
- LastPass
- Dashlane
- Google Password Manager
- KeePassDX
- And others

## Troubleshooting

### Autofill Key Not Appearing

If the autofill key doesn't appear automatically:

1. Verify that the app properly declares autofill hints in its input fields
2. Manually add the autofill key via keyboard settings
3. Try long-pressing the field and selecting "Autofill" from the context menu

### No Inline Suggestions Showing

If inline suggestions don't appear (Android 11+):

1. Ensure your Android version is 11 or higher
2. Check that inline autofill is enabled in your password manager settings
3. Verify the password manager's autofill service is active in Android settings
4. Try restarting the keyboard or the app

### Autofill Not Working in Some Apps

Some apps may not fully support the autofill framework:

1. Enable accessibility-based autofill in your password manager settings
2. Use the manual autofill key or long-press menu
3. Consider reaching out to the app developer to add proper autofill support

## Technical Details

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
   - `autofillId` presence
   - Hint text analysis
   - Package name and context

### Privacy

The keyboard integration:
- Does **not** store or access your passwords
- Only communicates with the system autofill service
- Works entirely through Android's secure autofill framework
- Maintains the same privacy guarantees as Unexpected Keyboard

## See Also

- [Possible Key Values](Possible-key-values.md) - Full list of available keys including `autofill`
- [Features](Features.md) - Overview of all keyboard features
- [Bitwarden Help Center](https://bitwarden.com/help/article/auto-fill-android/) - Official Bitwarden autofill documentation

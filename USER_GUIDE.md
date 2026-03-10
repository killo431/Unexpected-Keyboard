# Unexpected Keyboard - Comprehensive User Guide

## Table of Contents
1. [Introduction](#introduction)
2. [Installation and Setup](#installation-and-setup)
3. [Basic Usage](#basic-usage)
4. [Advanced Features](#advanced-features)
5. [Customization](#customization)
6. [Tips and Tricks](#tips-and-tricks)
7. [Troubleshooting](#troubleshooting)
8. [FAQ](#faq)

---

## Introduction

### What is Unexpected Keyboard?

Unexpected Keyboard is a lightweight, privacy-conscious virtual keyboard for Android that revolutionizes mobile typing by allowing you to access multiple characters from a single key through intuitive swipe gestures.

**Key Features:**
- **No ads, no tracking, no network requests** - Complete privacy
- **9 characters per key** - Tap the center + swipe in 8 directions
- **89+ built-in layouts** - Support for 12 different writing systems
- **Custom layouts** - Create your own keyboard layout
- **Programmer-friendly** - Originally designed for Termux users
- **Open source** - GPL licensed, community-driven

### Why Choose Unexpected Keyboard?

- **Privacy First**: No permissions required, no data collection
- **Efficient Typing**: Access symbols without switching keyboard modes
- **Highly Customizable**: Adjust size, colors, spacing, and layouts
- **Multilingual**: Support for 50+ languages across multiple scripts
- **Lightweight**: Small app size, minimal resource usage
- **Active Development**: Regular updates and community contributions

---

## Installation and Setup

### Installation

**Option 1: F-Droid (Recommended)**
1. Install F-Droid from https://f-droid.org if you haven't already
2. Open F-Droid and search for "Unexpected Keyboard"
3. Tap Install

**Option 2: Google Play Store**
1. Open Google Play Store
2. Search for "Unexpected Keyboard"
3. Tap Install

**Option 3: Build from Source**
See [CONTRIBUTING.md](CONTRIBUTING.md) for build instructions

### Initial Setup

1. **Enable the Keyboard**
   - Open your device Settings
   - Navigate to System → Languages & input → Virtual keyboard
   - Select "Manage keyboards"
   - Enable "Unexpected Keyboard"

2. **Set as Default Keyboard**
   - Long-press on any text field
   - Tap "Input method" or the keyboard icon
   - Select "Unexpected Keyboard"

   Alternatively:
   - Go to Settings → System → Languages & input
   - Select "Virtual keyboard" → "Manage keyboards"
   - Set Unexpected Keyboard as default

3. **Access Settings**
   - Open any text field to display the keyboard
   - Swipe the **gear icon (⚙)** in the **bottom-left corner** towards the **southwest (↙)** direction
   - This opens the Unexpected Keyboard settings

---

## Basic Usage

### Understanding the Key Layout

Each key on Unexpected Keyboard can output up to 9 different characters:

```
   NW    N    NE
    ↖    ↑    ↗
W   ←    C    →   E
    ↙    ↓    ↘
   SW    S    SE
```

- **C (Center)**: Tap the key normally
- **N (North)**: Swipe upward
- **NE (Northeast)**: Swipe diagonally up-right
- **E (East)**: Swipe right
- **SE (Southeast)**: Swipe diagonally down-right
- **S (South)**: Swipe downward
- **SW (Southwest)**: Swipe diagonally down-left
- **W (West)**: Swipe left
- **NW (Northwest)**: Swipe diagonally up-left

### Basic Typing

1. **Letters**: Tap the center of keys
2. **Numbers**: Swipe upward-right (NE) on the top row
   - Q → 1, W → 2, E → 3, etc.
3. **Common Symbols**: Swipe in various directions
   - Example on 'E' key: ! (NW), 3 (NE), # (SW)
4. **Shift**: Tap the Shift key (left side, 3rd row) for uppercase
5. **Backspace**: Bottom-right corner key

### Special Keys

**Bottom Row (Left to Right):**
- **Ctrl**: Control key for keyboard shortcuts
- **Fn**: Function key for alternative symbols
- **Space**: Spacebar (can also swipe for special spaces)
- **Compose**: Compose key for creating accented characters
- **Enter**: Return/Enter key

**Modifier Keys:**
- **Shift**: Capitalize letters, access shifted symbols
- **Ctrl**: Send Ctrl+ combinations (useful in terminal apps)
- **Alt**: Send Alt+ combinations
- **Fn**: Access alternative symbol layer
- **Meta**: Send Meta/Win key combinations

### Text Editing

**Quick Actions:**
- **Copy**: Swipe Ctrl key towards a specific direction (if configured)
- **Paste**: Swipe Ctrl key towards another direction
- **Cut**: Swipe Ctrl key
- **Undo**: Swipe Fn key
- **Redo**: Swipe Fn key in opposite direction
- **Select All**: Usually Ctrl+A (tap Ctrl, then tap 'A')

**Cursor Movement:**
Configure arrow keys or use:
- **Arrow Keys**: If enabled in settings
- **Ctrl+Arrow**: Move by word
- **Home/End**: Jump to line start/end
- **Page Up/Down**: Scroll content

---

## Advanced Features

### Using Compose Key

The Compose key allows you to create accented and special characters through intuitive key combinations:

**How to Use:**
1. Tap the **Compose** key (bottom row)
2. Type a sequence of keys
3. The characters combine into a special character

**Common Examples:**
- `Compose` + `'` + `e` = é (e with acute accent)
- `Compose` + `` ` `` + `a` = à (a with grave accent)
- `Compose` + `~` + `n` = ñ (n with tilde)
- `Compose` + `"` + `u` = ü (u with umlaut)
- `Compose` + `c` + `c` = © (copyright symbol)
- `Compose` + `<` + `<` = « (left guillemet)
- `Compose` + `1` + `2` = ½ (one half)

**More Sequences:**
- Currency: `Compose` + `e` + `=` = €
- Superscripts: Use Shift + number combinations
- Math symbols: Various compose sequences available

### Using Function (Fn) Key

The Fn key transforms keys into alternative symbols:

**Common Fn Combinations:**
- `Fn` + `<` = « (left guillemet)
- `Fn` + `>` = » (right guillemet)
- `Fn` + `e` = € (euro sign)
- `Fn` + `Space` = Non-breaking space
- `Fn` + various letters = special symbols

### Accent Keys

For languages with diacritical marks:

1. Enable accent keys in Settings → "Add keys to keyboard"
2. Tap an accent key (e.g., acute accent)
3. Tap the base letter
4. Result: accented character (e.g., é)

**Available Accents:**
- Acute (´): á, é, í, ó, ú
- Grave (`): à, è, ì, ò, ù
- Circumflex (^): â, ê, î, ô, û
- Tilde (~): ã, ñ, õ
- Umlaut/Trema (¨): ä, ë, ï, ö, ü
- Cedilla (¸): ç
- Ring (°): å
- Stroke (ø): ø
- Caron (ˇ): č, š, ž

### Gestures

**Special Gestures:**
- **Circular Swipe (Clockwise)**: Activates Fn layer
- **Circular Swipe (Anti-clockwise)**: Special function per key
- **Round-trip Gesture**: Swipe and return activates Shift layer

### Text Suggestions and Autocorrect

**Enabling Suggestions:**
1. Open Settings (swipe gear icon ↙)
2. Enable "Suggestions" or "Autocorrect"
3. Download dictionary if needed

**Note**: Unexpected Keyboard's autocorrect is minimal by design to respect user input.

---

## Customization

### Accessing Settings

Swipe the gear icon (⚙) in the bottom-left corner towards southwest (↙).

### Essential Settings

#### Appearance

**Keyboard Layout:**
- Tap the layout name at the top
- Choose from 89+ built-in layouts
- Or select "Custom layout" to create your own

**Theme:**
- Light, Dark, System, or custom themes
- Configure colors for keys, labels, and backgrounds
- Adjust transparency

**Keyboard Height:**
- Drag slider to adjust vertical size
- Useful for larger phones or tablets

**Horizontal Margin:**
- Add space on left/right edges
- Useful for edge-to-edge screens

**Character Size:**
- Adjust label text size
- Makes legends more readable

**Keyboard Opacity:**
- Make keyboard transparent to see content below

#### Behavior

**Vibration:**
- Enable/disable haptic feedback
- Adjust vibration duration and intensity

**Sound:**
- Enable/disable key press sounds
- Adjust volume

**Swipe Sensitivity:**
- Adjust how far you need to swipe
- Higher = requires longer swipe
- Lower = more sensitive, easier accidental swipes

**Long-press Delay:**
- Adjust how long to hold for long-press actions
- Affects keys with alternate functions

**Repeat Delay:**
- Adjust key repeat behavior
- Useful for backspace, arrow keys

#### Advanced

**Show Number Row:**
- Add dedicated number row above keyboard
- Trade-off: less vertical space for keys

**Precision Mode:**
- Shrink keyboard for more accurate typing
- Toggle with pinch gesture

**Swipe Distance Multiplier:**
- Fine-tune swipe detection
- Default works for most users

**Lock keyboard:**
- Prevent accidental layout changes
- Unlock in settings

### Selecting a Layout

1. Open Settings (swipe gear icon ↙)
2. Tap the current layout name at the top
3. Browse available layouts:
   - **Latin Scripts**: QWERTY (US), AZERTY (French), QWERTZ (German), Colemak, Dvorak, etc.
   - **Cyrillic**: Russian, Ukrainian, Serbian, Bulgarian, etc.
   - **Arabic**: Arabic (various regions), Persian, Urdu
   - **Asian Scripts**: Devanagari, Bengali, Tamil, Hangul, etc.
   - **Other Scripts**: Hebrew, Armenian, Georgian, etc.
4. Tap to select
5. Tap back to apply

**Multiple Layouts:**
- Select "Add an alternate layout" to enable multiple layouts
- Switch between layouts using the globe icon (if configured)

### Creating a Custom Layout

See [doc/Custom-layouts.md](doc/Custom-layouts.md) for comprehensive instructions.

**Quick Start:**
1. Open Settings → Layout selection
2. Scroll to bottom and tap "Custom layout"
3. Edit the XML code (see custom layout guide)
4. Tap OK to apply

**Recommendations:**
- Use the [web-based editor](https://domportera.github.io/app-unexpected-keyboard-layout-editor/)
- Start from an existing layout (copy from `/srcs/layouts/`)
- Keep a backup of your XML in a text file
- Test thoroughly before relying on it

**Web-Based Editors:**
- https://domportera.github.io/app-unexpected-keyboard-layout-editor/
- https://unexpected-keyboard-layout-editor.lixquid.com/

Both provide visual interfaces for creating layouts without manual XML editing.

### Adding Keys to Keyboard

Some keys are placeholders and only appear when enabled:

1. Open Settings
2. Tap "Add keys to keyboard"
3. Enable desired keys:
   - Accent marks (acute, grave, circumflex, etc.)
   - Special letters (ß, æ, ø, etc.)
   - Currency symbols (€, £, ¥, etc.)
   - Mathematical symbols
   - Arrows (←, →, ↑, ↓)

These keys will appear on your layout if the layout has designated spots for them (marked as `loc` in layout XML).

### Portrait vs. Landscape Layouts

Unexpected Keyboard remembers different layouts for portrait and landscape orientations:

1. Rotate device to landscape
2. Open Settings
3. Select a different layout
4. Rotate back to portrait
5. Select another layout

Now the keyboard automatically switches between these layouts when you rotate.

---

## Tips and Tricks

### Efficiency Tips

1. **Learn Common Swipes**: Memorize frequently-used symbols
   - @ (usually SW on W)
   - # (usually SW on E)
   - $ (usually SW on R)
   - Parentheses (usually on O key)

2. **Use the Fn Key**: Quick access to alternative symbols without swiping

3. **Master Compose**: Faster than searching for special characters
   - `Compose` + `'` + letter = acute accent
   - `Compose` + `` ` `` + letter = grave accent

4. **Enable Number Row**: If you type numbers frequently

5. **Custom Layout**: Create a layout optimized for your most-used characters

### Programming Tips

Unexpected Keyboard was designed for programmers:

1. **All ASCII Characters**: Available without switching modes
2. **Ctrl Combinations**: Full support for terminal shortcuts
   - `Ctrl+C`: Interrupt
   - `Ctrl+Z`: Suspend
   - `Ctrl+D`: EOF
   - `Ctrl+L`: Clear screen

3. **Brackets and Braces**: Easily accessible via swipes
   - {} usually on J key (SE and SW)
   - [] usually on K key (SW and SE)
   - () usually on O key (SW and SE)

4. **Recommended Layouts**:
   - QWERTY (US): Most symbols easily accessible
   - Colemak: Ergonomic with good symbol placement
   - Dvorak: Alternative ergonomic layout

### Terminal Usage (Termux)

Perfect keyboard for Termux and other terminal apps:

1. **All Modifiers Available**: Ctrl, Alt, Meta
2. **Escape Key**: Usually at Q NE or Q SE
3. **Tab Key**: Usually at A NW
4. **Arrow Keys**: Enable in "Add keys to keyboard"
5. **Function Keys**: Can be mapped in custom layouts

**Pro Tip**: Create a custom layout specifically for terminal use with frequently-used shell commands mapped to swipes.

### Writing in Multiple Languages

1. **Enable Multiple Layouts**:
   - Settings → Add an alternate layout
   - Select layouts for each language

2. **Quick Switching**:
   - Configure layout switching gesture
   - Or access via settings

3. **Language-Specific Keys**:
   - Enable in "Add keys to keyboard"
   - Characters like ñ, ç, ß, æ, etc.

### Accessibility

1. **Adjust Keyboard Size**: Make keys larger for easier tapping
2. **Increase Label Size**: Improve readability
3. **Enable Vibration**: Tactile feedback for key presses
4. **High Contrast Themes**: Create custom themes with better contrast
5. **Reduce Swipe Sensitivity**: Prevent accidental swipes

---

## Troubleshooting

### Keyboard Not Appearing

1. Check if Unexpected Keyboard is enabled:
   - Settings → System → Languages & input → Virtual keyboard
   - Manage keyboards → Enable Unexpected Keyboard

2. Set as default input method:
   - Long-press text field → Select input method
   - Choose Unexpected Keyboard

3. Restart app or device if needed

### Swipes Not Working

1. **Check Swipe Sensitivity**: Settings → Adjust swipe distance multiplier
2. **Swipe Direction**: Ensure you're swiping in the correct direction
3. **Swipe Length**: Swipe far enough to register
4. **Key Has Swipe**: Not all keys have all 8 swipe directions configured

### Custom Layout Not Loading

1. **Check XML Syntax**: Ensure XML is valid
2. **Required Attributes**: Layout must have `name` attribute
3. **Test with Simple Layout**: Start with minimal example and add complexity
4. **Check Error Messages**: App may display parsing errors
5. **Backup and Restore**: Keep XML in external file

### Missing Characters

1. **Check Layout**: Not all layouts have all characters
2. **Enable Locale Keys**: Settings → Add keys to keyboard
3. **Use Compose**: Many characters available via compose sequences
4. **Use Fn Layer**: Alternative symbols via Fn key
5. **Custom Layout**: Add missing characters yourself

### Performance Issues

1. **Reduce Keyboard Size**: Smaller keyboard uses less resources
2. **Disable Animations**: If available in settings
3. **Clear App Data**: Settings → Apps → Unexpected Keyboard → Clear data (will reset settings)
4. **Update App**: Check for latest version

### Autocorrect Issues

Unexpected Keyboard has minimal autocorrect by design. If you need stronger autocorrect:
1. Use a different keyboard for casual typing
2. Unexpected Keyboard is optimized for precise input (programming, terminal)
3. Enable suggestions in settings for basic word suggestions

---

## FAQ

### Is Unexpected Keyboard completely free?

Yes, it's 100% free and open source under GPL license. No ads, no in-app purchases, no tracking.

### Does it collect any data?

No. Unexpected Keyboard makes zero network requests and collects zero data. It doesn't even request internet permission.

### Can I use it offline?

Yes, completely offline. No internet connection required.

### Which Android versions are supported?

Minimum: Android 5.0 (API 21)
Target: Android 14 (API 35)
Works on most modern Android devices.

### How many layouts are available?

89+ built-in layouts covering:
- 12 different writing systems
- 50+ languages
- Multiple layout styles (QWERTY, AZERTY, QWERTZ, Dvorak, Colemak, etc.)

Plus unlimited custom layouts you can create.

### Can I contribute my custom layout?

Yes! See [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines. You can:
1. Submit to [Unexpected-Keyboard-layouts](https://github.com/Julow/Unexpected-Keyboard-layouts) repository
2. Create a Pull Request to add it to built-in layouts

### Does it support emoji?

Yes, emoji are accessible through:
- Fn key combinations
- Custom layouts
- Compose sequences
- System emoji picker (if enabled)

### How do I report bugs?

Open an issue on GitHub: https://github.com/Julow/Unexpected-Keyboard/issues

### How can I help translate?

Visit the Weblate project: https://hosted.weblate.org/engage/unexpected-keyboard/

Currently available in 20+ languages.

### Can I use it with password managers?

Yes, Unexpected Keyboard works with autofill and password managers like:
- Bitwarden
- KeePassDX
- 1Password
- Android Autofill framework

### Does it support physical keyboards?

Yes, you can use a physical keyboard alongside Unexpected Keyboard. The virtual keyboard complements physical keyboard usage.

### Can I export/import settings?

Currently, settings are stored in app preferences. To backup:
1. Use Android backup feature
2. Keep custom layouts in external files
3. Screenshot your settings for reference

### Why is it called "Unexpected" Keyboard?

The name refers to the unexpected locations of symbols (in the corners) and the unexpected efficiency of accessing multiple characters per key.

### How is this different from other keyboards?

**Unique Features:**
- 9 characters per key via swipes
- Zero tracking/ads/permissions
- Designed for programmers
- Minimal, efficient design
- Complete offline functionality
- Highly customizable layouts

**Trade-offs:**
- Learning curve for swipe gestures
- Minimal autocorrect (by design)
- No GIF keyboard or stickers
- Focused on text input efficiency

---

## Additional Resources

### Documentation

- [Custom Layouts Guide](doc/Custom-layouts.md) - Complete XML layout reference
- [Possible Key Values](doc/Possible-key-values.md) - All available key functions
- [Contributing Guide](CONTRIBUTING.md) - How to contribute to the project
- [GitHub Repository](https://github.com/Julow/Unexpected-Keyboard) - Source code and issues

### External Tools

- [Web Layout Editor 1](https://domportera.github.io/app-unexpected-keyboard-layout-editor/) - Visual layout editor
- [Web Layout Editor 2](https://unexpected-keyboard-layout-editor.lixquid.com/) - Alternative visual editor
- [Community Layouts](https://github.com/Julow/Unexpected-Keyboard-layouts) - User-contributed layouts

### Community

- GitHub Issues: Bug reports and feature requests
- GitHub Discussions: Community discussions
- Weblate: Translation contributions

---

## Support the Project

Unexpected Keyboard is maintained by volunteers. You can help by:

1. **Contributing Code**: Fix bugs, add features
2. **Translating**: Help translate to your language via Weblate
3. **Creating Layouts**: Share your custom layouts
4. **Reporting Bugs**: Open detailed issue reports
5. **Spreading the Word**: Tell others about the app
6. **Documentation**: Improve or translate documentation

Visit the [GitHub repository](https://github.com/Julow/Unexpected-Keyboard) to get involved.

---

## License

- **Application Code**: GPL-3.0
- **Layout Definitions**: CC0 (Public Domain)

---

**Version**: 1.32.1
**Last Updated**: 2026-03-10
**Maintained by**: Community contributors

For the most up-to-date information, visit: https://github.com/Julow/Unexpected-Keyboard

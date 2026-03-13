# Termux Keyboard Layouts

This document describes the 4 custom keyboard layouts optimized specifically for Termux usage. Each layout is designed with a different focus to suit various terminal workflows and usage patterns.

---

## Overview

All Termux layouts share these common features:
- **3-row compact design** - Maximizes screen space for terminal output
- **Built-in bottom row** - Standard navigation and control keys (space, enter, modifier keys)
- **Termux Run key** - Quick access to the Termux command execution dialog
- **Navigation keys** - Cursor movement, home/end, page up/down
- **Essential terminal symbols** - All common shell operators and programming characters
- **Numbers on northeast swipes** - Traditional number placement (1-0 across top row)

---

## 1. Termux Compact

**Best for:** Users who want maximum efficiency with minimal screen space usage

### Features
- Minimal, efficient layout with all terminal essentials
- Essential terminal symbols on primary swipes
- Quick access to common shell characters: `-`, `_`, `|`, `/`, `\`, `~`
- All brackets and quotes easily accessible
- Compact design leaves maximum room for terminal output

### Key Highlights
- **Row 1:** Letters q-p with numbers 1-0 on NE swipes, special chars on other swipes
- **Row 2:** Letters a-l with tab, backtick, operators, and Termux run key
- **Row 3:** Letters z-m with copy/paste, cursor navigation, and function keys

### Symbol Access
| Symbol | Location | Swipe Direction |
|--------|----------|-----------------|
| `-` (dash) | Y key | NW |
| `_` (underscore) | Y key | SW |
| `\|` (pipe) | I key | SW |
| `/` (slash) | F key | NE |
| `\` (backslash) | F key | SW |
| `~` (tilde) | W key | NW |
| `@` (at) | W key | SW |
| `#` (hash) | E key | NW |
| `$` (dollar) | E key | SW |

---

## 2. Termux Developer

**Best for:** Programming and software development in Termux

### Features
- Programming-focused with easy access to all bracket types
- Common operators readily available: `+`, `-`, `*`, `/`, `=`, `!=`
- Programming symbols: `&`, `|`, `^`, `~`, `$`, `@`
- Git and version control symbols
- Quick access to quotes, backticks, and string delimiters
- Developer mode key alongside Termux run key

### Key Highlights
- **All bracket types** easily accessible: `()`, `[]`, `{}`, `<>`
- **Comparison operators:** `=`, `!=`, `<`, `>`
- **Bitwise operators:** `&`, `|`, `^`, `~`
- **String delimiters:** `'`, `"`, `` ` ``

### Symbol Access
| Symbol | Location | Swipe Direction |
|--------|----------|-----------------|
| `(` `)` | U key | NW, SW |
| `[` `]` | I key | NW, SW |
| `{` `}` | O key | NW, SW |
| `<` `>` | P key | NW, SW |
| `/` `\` | S key | NE, SW |
| `-` `_` | D key | NE, SW |
| `=` `+` | F key | NE, SW |
| `!` `!=` | G key | NW, SW |

---

## 3. Termux Commands

**Best for:** Command-line operations and shell scripting

### Features
- Command-line focused with easy access to shell operators
- Quick access to pipe and redirect operators: `|`, `&`, `>`, `<`, `>>`, `&&`, `||`
- Path navigation characters: `/`, `\`, `~`, `.`
- Command separators: `;`, `|`, `&`, `&&`
- File operation symbols readily available
- Optimized for bash/shell scripting

### Key Highlights
- **Pipe operators:** `|`, `||` (H key: NE, SW)
- **Redirect operators:** `>`, `>>`, `<`, `<<` (U and I keys)
- **Command chaining:** `&&`, `&`, `;` (R, H, G keys)
- **Path symbols:** `/`, `\`, `~`, `.` (S, W, K keys)

### Symbol Access
| Symbol | Location | Swipe Direction |
|--------|----------|-----------------|
| `\|` (pipe) | Y key | NW |
| `&&` (and) | Y key | SW |
| `>` | U key | NW |
| `>>` | U key | SW |
| `<` | I key | NW |
| `<<` | I key | SW |
| `\|` (pipe) | H key | NE |
| `\|\|` (or) | H key | SW |

---

## 4. Termux Hybrid

**Best for:** Mixed usage - both text entry and terminal work

### Features
- Balanced layout combining text entry and terminal use
- Standard QWERTY base for familiar text typing
- Programming symbols available on swipes
- Terminal operators easily accessible
- Traditional number placement (NE swipes)
- Suitable for documentation, scripting, and terminal work
- Good balance between typing prose and executing commands

### Key Highlights
- **Familiar QWERTY layout** for comfortable text entry
- **Programming symbols** available without sacrificing typing comfort
- **Terminal operators** accessible but not intrusive
- **Balanced design** works well for both code and prose

### Symbol Access
| Symbol | Location | Swipe Direction |
|--------|----------|-----------------|
| `-` `_` | Y key | NW, SW |
| `=` `+` | U key | NW, SW |
| `[` `]` | I key | NW, SW |
| `(` `)` | O key | NW, SW |
| `{` `}` | P key | NW, SW |
| `/` `\` | D key | NE, SW |
| `\|` `&` | F key | NE, SW |

---

## How to Select a Termux Layout

### In Unexpected Keyboard Settings:

1. Open Unexpected Keyboard settings (swipe up on the keyboard or open the app)
2. Tap **Layout** at the top of the settings page
3. Scroll down to find the Termux layouts:
   - **Termux Commands** - for shell operations
   - **Termux Compact** - for minimal design
   - **Termux Developer** - for programming
   - **Termux Hybrid** - for balanced usage
4. Tap your preferred layout
5. The keyboard will immediately switch to the selected layout

### Portrait vs. Landscape

Unexpected Keyboard remembers layout preferences separately for portrait and landscape orientations. You can use different Termux layouts for each orientation if desired.

---

## Common Features Across All Layouts

### Navigation Keys
All layouts include these navigation keys on the bottom row (Row 3):

- **Cursor movement:** Arrow keys via C and V keys (NE/SW swipes)
- **Home/End:** B key (NE/SW swipes)
- **Page Up/Down:** N key (NE/SW swipes)
- **Function keys:** M key (NE/SW for F11/F12)

### Text Operations
- **Copy:** X key (NE swipe) or Z key (NE swipe)
- **Paste:** X key (SW swipe)
- **Cut:** Z key (SW swipe) (Developer layout)
- **Select All:** X key (SW swipe) (Developer layout)

### Special Keys
- **Tab:** A key (NW swipe) - `loc tab` placeholder
- **Esc:** Q key (SE swipe) - `loc esc` placeholder
- **Termux Run:** Available on H key (Developer), L key (Developer), K key (Commands), or separate key

> **Note:** Keys with `loc` prefix (like `loc tab`, `loc esc`) are placeholders that can be enabled through the "Add keys to keyboard" option in Settings or are automatically enabled based on your device language.

---

## Termux Run Key

All layouts include quick access to the **Termux Run** feature, which allows you to execute shell commands directly from the keyboard.

### Using Termux Run:
1. Tap the Termux Run key on your keyboard
2. Enter your command or script in the dialog
3. Choose whether to show the output in the Termux terminal window
4. Tap "Run in Termux" to execute

For complete setup instructions, see [Termux-Run-Setup.md](Termux-Run-Setup.md).

---

## Choosing the Right Layout

| If you want... | Use this layout |
|----------------|-----------------|
| Maximum screen space for terminal | **Termux Compact** |
| Heavy programming/coding work | **Termux Developer** |
| Running lots of shell commands | **Termux Commands** |
| Mix of text and terminal work | **Termux Hybrid** |
| Most familiar typing experience | **Termux Hybrid** |
| Fastest symbol access | **Termux Developer** or **Termux Commands** |

---

## Customization

All Termux layouts use the standard 3-row design with the built-in bottom row. If you want to customize them further:

1. Copy the layout file from `srcs/layouts/latn_termux_*.xml`
2. Modify it according to your needs
3. Use the custom layout feature in Unexpected Keyboard settings

For a complete guide on creating and editing layouts, see [Custom-layouts.md](Custom-layouts.md).

---

## Additional Resources

- [Termux Run Setup Guide](Termux-Run-Setup.md) - Setup and usage of Termux integration
- [Custom Layouts Guide](Custom-layouts.md) - How to create and edit keyboard layouts
- [Possible Key Values](Possible-key-values.md) - Complete reference of available key values
- [Termux Wiki](https://wiki.termux.com/) - Official Termux documentation

---

## Contributing

Found a bug or have suggestions for improving these layouts? Please contribute!

- Report issues on the [GitHub issue tracker](https://github.com/Julow/Unexpected-Keyboard/issues)
- Submit improvements via pull requests
- Share your custom Termux layouts at [Unexpected-Keyboard-layouts](https://github.com/Julow/Unexpected-Keyboard-layouts)

---

## Layout Files

The source files for these layouts are located at:
- `srcs/layouts/latn_termux_compact.xml`
- `srcs/layouts/latn_termux_dev.xml`
- `srcs/layouts/latn_termux_cmd.xml`
- `srcs/layouts/latn_termux_hybrid.xml`

# Feature Overview

Unexpected Keyboard was originally designed for programmers using Termux and other terminal emulators. The features below are ranked from most to least important, with terminal and shell usage as the primary lens.

---

## 1. Swipe Gesture Input

Every key exposes up to 8 additional characters by swiping towards any compass direction (N, NE, E, SE, S, SW, W, NW). This is the foundational feature of the keyboard. In a terminal context it puts characters like `|`, `&`, `>`, `<`, `` ` ``, `~`, `$`, `\`, `/`, `;`, `#`, and `!` a single swipe away, eliminating the need to hunt through multiple layers for the most commonly used shell operators.

## 2. Ctrl / Alt / Meta Modifiers

Hardware-quality modifier keys send real Ctrl, Alt, and Meta key events to the receiving application. Terminal shortcuts that depend on these modifiers — `Ctrl+C` (interrupt), `Ctrl+Z` (suspend), `Ctrl+D` (EOF/logout), `Ctrl+L` (clear screen), `Ctrl+R` (reverse history search), `Alt+.` (last argument), and many more — work exactly as they do on a physical keyboard.

## 3. Escape Key

A dedicated `esc` key is essential for modal editors (vi/vim/neovim, nano shortcuts, emacs) and for cancelling partial commands in shells. The key is always reachable without leaving the main layer.

## 4. Function Keys (F1 – F12)

`f1` through `f12` are available and fully functional. They are required by many terminal programs: `man` pages, `htop`, `mc` (Midnight Commander), `cmus`, `tmux` bindings, and the readline library all make heavy use of the function row. `f11` and `f12` are defined as `f11_placeholder` / `f12_placeholder` in the built-in layouts; they are hidden in the normal view and become visible only when the `fn` modifier is active.

## 5. Tab Key with Shell Completion

The `tab` key sends a real Tab character, which triggers auto-completion in bash, zsh, and fish. Quick, reliable access to Tab reduces typing effort for long paths, command names, and option flags.

## 6. Arrow Keys (Up, Down, Left, Right)

Directional keys on the bottom row let you navigate command history (`↑`/`↓`), move the cursor inside the current command line (`←`/`→`), and move through menus in full-screen terminal applications such as vim or tmux.

## 7. Special Shell Characters via Swipe

Beyond letters and numbers the swipe system makes the following characters instantly accessible without layer-switching: `` ` `` (backtick/command substitution), `~` (home directory), `$` (variable expansion), `\` (escape/line continuation), `/` (path separator), `|` (pipe), `&` (background), `;` (command separator), `#` (comment), `!` (history expansion), `*` (glob), `?` (single-char glob), `[`, `]`, `{`, `}`, `(`, `)`. Having all of them on the primary layer with a single swipe dramatically accelerates shell scripting.

## 8. 88 Built-in Keyboard Layouts

The keyboard ships with 88 layouts covering more than 50 writing systems, from QWERTY and QWERTZ variants to Dvorak, Colemak, Bépo, Cyrillic, Arabic, Devanagari, and beyond. Each layout is optimized for the character set it serves, keeping the most-needed characters closest to the home row.

## 9. Compose Key (Linux-style)

The `compose` key replicates the compose-key sequences familiar from Linux desktops. After pressing Compose you type a short mnemonic sequence to produce accented or special characters — for example `Compose` `A` `'` → `Á`. This is invaluable when writing code with Unicode identifiers, documentation with non-ASCII characters, or locale-specific terminal output.

## 10. Fn Mode

Activating the `fn` modifier overlays every key with an alternate symbol set: maths symbols, arrows, box-drawing characters, subscripts, superscripts, and rarely-needed punctuation. This avoids adding extra rows or layers while still providing a complete symbol palette.

## 11. Custom Layouts via XML

Users can create or modify layouts using a simple XML format, either inside the app or with the [web-based editor](https://domportera.github.io/app-unexpected-keyboard-layout-editor/). This makes it possible to build a layout that is perfectly tuned for a specific shell workflow — placing common operators, macros, or snippets exactly where you want them.

## 12. Macro Keys

A key can be defined as a sequence of other key events: `legend:key1,key2,...`. This allows one-tap execution of compound terminal operations such as `CA:ctrl,a,ctrl,c` (select all and copy), `Cd:ctrl,backspace` (delete word), or a full shell command template. Macros reduce repetitive typing for operators who run the same commands frequently.

## 13. Text Snippets

The snippet system stores reusable blocks of text (folder-organised and searchable) that can be inserted with a single tap. For terminal users this means frequently-used command strings, SSH host names, configuration snippets, or script templates are always one tap away.

## 14. Clipboard History

Every item copied to the clipboard is preserved in a scrollable history pane (`switch_clipboard` key). When working in a terminal — copying paths, command output, error messages, or connection strings — being able to paste any previous clip without re-running the original command saves significant time.

## 15. Home / End Keys

`home` and `end` jump to the beginning or end of the current line. In a shell this is the equivalent of `Ctrl+A` / `Ctrl+E` and is essential for correcting commands without deleting and retyping.

## 16. Page Up / Page Down

`page_up` and `page_down` scroll through terminal output and allow navigation inside pagers (`less`, `man`) and terminal multiplexers (`tmux`, `screen`).

## 17. Delete Word (`delete_word`, `forward_delete_word`)

Deleting the previous word with `delete_word` (equivalent to `Ctrl+W` in most shells) or the next word with `forward_delete_word` is faster than tapping backspace or delete repeatedly when correcting a mistyped command.

## 18. Haptic Feedback

Configurable vibration on each key press gives tactile confirmation that a keystroke has registered. This is especially valuable in a terminal where mistyped characters can produce unexpected command behaviour and the only visual feedback is the terminal output itself.

## 19. Adjustable Keyboard Height and Key Size

The keyboard height (as a percentage of screen height) and individual key sizes are configurable. Terminal sessions benefit from a taller keyboard for easier tap accuracy, or a shorter keyboard to show more terminal output at once.

## 20. Editor-Aware Layout Switching

The keyboard detects the input field type reported by the app (plain text, password, email, URI, number, etc.) and can adjust its layout accordingly. In Termux the input is always plain text, but this feature prevents the keyboard from auto-capitalising or offering word suggestions in contexts where those behaviours would interfere with command input.

## 21. Gemini AI — Text and Code Generation

The `gemini` key opens a built-in dialog that connects to Google's Gemini 2.0 Flash model via the free Gemini REST API. In **Generate Text** mode you can ask any open-ended question, rephrase text, or request a summary. In **Generate Code** mode you describe the code you need and receive only the code — no prose — ready to paste into an editor or directly into a Termux session. The result can be inserted at the cursor position with a single tap. A free API key from [Google AI Studio](https://aistudio.google.com/app/apikey) is required; the key is stored locally in the keyboard's private storage and is never shared.

Setup instructions: [doc/Gemini-AI-Setup.md](Gemini-AI-Setup.md)

## 22. Termux Run — Execute Shell Scripts from the Keyboard

The `termux_run` key opens a dialog that sends any shell command or multi-line script to [Termux](https://termux.dev) for immediate execution via the `com.termux.RUN_COMMAND` intent. Choosing **Show in Termux terminal window** brings Termux to the foreground so you can read the output; unchecking it runs the command silently in the background. This eliminates the round-trip of switching to Termux, typing (or pasting) a command, and switching back — everything stays in the keyboard dialog.

Setup instructions: [doc/Termux-Run-Setup.md](Termux-Run-Setup.md)

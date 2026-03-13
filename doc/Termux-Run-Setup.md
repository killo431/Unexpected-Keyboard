# Termux Run — Setup Guide

Unexpected Keyboard includes a built-in dialog for **running shell commands and scripts in Termux** directly from your keyboard. Once set up, you can tap a single key, type or paste a command, and execute it in Termux without switching apps.

> 💡 **New Feature**: [Termux Command Autosuggestions](Termux-Command-Suggestions.md) - Get intelligent command completions as you type in Termux!

---

## What it does

Pressing the `termux_run` key opens a dialog where you can:

- Type or paste any shell command or multi-line script.
- Choose whether to **show the Termux terminal window** while the command runs (useful for commands with output you want to read) or run it silently in the background.
- Tap **Run in Termux** to execute the script immediately.

The command is sent to Termux via the official `com.termux.RUN_COMMAND` intent and executed by `bash -c`.

---

## Prerequisites

- [Termux](https://termux.dev) installed from **F-Droid** (recommended) or the Google Play Store.
  - The F-Droid version receives updates more frequently and is the most compatible.
- Termux version **0.109 or later** (released 2021-10-11).
- "Allow External Apps" enabled in Termux (required once — see Step 2).

---

## Step 1 — Install Termux

### From F-Droid (recommended)

1. Install [F-Droid](https://f-droid.org) if you have not already.
2. Open F-Droid and search for **Termux**.
3. Tap **Install** on the result published by *Fredrik Fornwall*.
4. Wait for the installation to complete, then open Termux once to finish first-run setup.

### From Google Play

1. Open the Play Store and search for **Termux**.
2. Install the app published by *Fredrik Fornwall*.

> **Note:** The Play Store version may lag behind the F-Droid version. If you experience issues, prefer the F-Droid version.

---

## Step 2 — Enable "Allow External Apps"

Termux requires explicit permission before any external app (including Unexpected Keyboard) can send it commands. This is a one-time setup.

### Method A — Using the Termux Settings menu (Termux ≥ 0.118)

1. Open Termux.
2. Swipe from the left edge to open the sidebar, then tap the gear icon, or navigate to **Termux → Settings**.
3. Enable **Allow External Apps**.

### Method B — Using the Termux command line (all versions)

Open Termux and run:

```bash
touch ~/.termux/allow-external-apps
```

Then restart Termux (swipe up, close the app, reopen it) for the change to take effect.

You only need to do this once.

---

## Step 3 — Add the `termux_run` key to a layout

### Option A — Add it as an extra key (easiest)

1. Open Unexpected Keyboard settings (swipe the gear icon, or open the app).
2. Tap **Layout**, then tap **Extra keys**.
3. Find a free slot or tap **Add**.
4. Type `termux_run` as the key value and save.

The key (labelled **Tmx**) will appear in the bottom row of your keyboard.

### Option B — Add it to a custom layout

If you use a custom layout file, add a key with `c="termux_run"` where you want it:

```xml
<key c="termux_run" />
```

For example, beside the existing developer key:

```xml
<row>
    <key c="switch_developer" />
    <key c="termux_run" />
</row>
```

See [Custom-layouts.md](Custom-layouts.md) for a full guide to editing layouts.

---

## Using the Termux Run dialog

1. Tap the `termux_run` key on your keyboard (`Tmx` label).
2. The **Run in Termux** dialog opens.
3. Type or paste your command or script into the input field.
4. If you want to see the output in the Termux terminal window, leave **Show in Termux terminal window** checked. To run the command silently in the background, uncheck it.
5. Tap **Run in Termux**.
6. The status line shows **Sent to Termux ✓** when the command has been dispatched.
7. Tap **Close** to dismiss the dialog.

If Termux is in the background, it will come to the foreground automatically when **Show in Termux terminal window** is checked.

---

## Example commands and scripts

### Single commands

```bash
# List files in the home directory
ls -la ~/

# Print the current working directory
pwd

# Show system information
uname -a

# Check network connectivity
ping -c 3 8.8.8.8
```

### Package management

```bash
# Update all packages
pkg update -y && pkg upgrade -y

# Install a package
pkg install python -y

# Search for a package
pkg search curl
```

### Multi-line scripts

You can paste entire scripts into the command field. Newlines are preserved.

```bash
#!/bin/bash
echo "=== System Info ==="
uname -a
echo ""
echo "=== Disk Usage ==="
df -h
echo ""
echo "=== Memory Usage ==="
free -m
```

### Running a script file from Termux storage

If you have a script saved in Termux's home directory:

```bash
bash ~/my_script.sh
```

Or if the script is executable:

```bash
~/my_script.sh
```

### Working with files

```bash
# Create a new file with content
cat > ~/notes.txt << 'EOF'
This text was created from Unexpected Keyboard.
EOF

# Append to an existing file
echo "New line" >> ~/notes.txt

# Read a file
cat ~/notes.txt
```

---

## Troubleshooting

| Symptom | Cause | Fix |
|---------|-------|-----|
| Toast: *"Could not reach Termux. Is it installed and Allow External Apps enabled?"* | Termux is not installed, is not running, or "Allow External Apps" has not been enabled. | Install Termux and complete Step 2 above. |
| Nothing happens when tapping Run | The command field is empty. | Enter a command before tapping Run. |
| Status shows `Error: …` | An exception occurred while dispatching the intent. | Ensure Termux is installed and "Allow External Apps" is enabled. Restart Termux and try again. |
| Command runs but produces no visible output | **Show in Termux terminal window** was unchecked. | Check the box before running to see output. |
| The `termux_run` key does not appear on the keyboard | The key has not been added to the active layout. | Follow Step 3 above. |
| Commands work but file paths fail | The working directory defaults to Termux's home directory (`~/`). | Use absolute paths starting with `/data/data/com.termux/files/home/` or `~` when referring to files in Termux home. |
| `permission denied` errors | The script file is not executable. | Run `chmod +x ~/your_script.sh` in Termux once. |

---

## Security notes

- Shell commands run with the same permissions as the Termux process (your user account, not root, unless you have root access enabled in Termux).
- Only Unexpected Keyboard can send commands to Termux via this integration — other apps also require the `com.termux.permission.RUN_COMMAND` permission, which Android grants only if explicitly declared in the sending app's manifest.
- Do not run commands from untrusted sources. Because commands execute with access to your Termux home directory and any storage permissions granted to Termux, a malicious command could delete files or exfiltrate data.
- You can revoke the integration at any time by disabling "Allow External Apps" in Termux settings (see Step 2, Method A).

---

## See also

- **[Termux Command Suggestions](Termux-Command-Suggestions.md)** - Intelligent command autosuggestions for faster typing
- [Possible-key-values.md](Possible-key-values.md) — full reference for key values, including `gemini` and `termux_run`
- [Custom-layouts.md](Custom-layouts.md) — how to build or modify a keyboard layout
- [Termux-Layouts.md](Termux-Layouts.md) — optimized keyboard layouts for Termux
- [Gemini-AI-Setup.md](Gemini-AI-Setup.md) — generate AI text and code from your keyboard
- [Termux documentation](https://wiki.termux.com/wiki/Main_Page) — official Termux wiki

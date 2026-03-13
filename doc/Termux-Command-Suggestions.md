# Termux Command Autosuggestions

Unexpected Keyboard now provides **intelligent command autosuggestions** specifically for Termux terminal usage. When typing in Termux, the keyboard automatically detects the context and offers relevant command completions based on what you're typing.

---

## Features

### 📝 Command Suggestions
- **100+ Common Commands**: Automatically suggests frequently used Termux and Linux commands
- **Smart Matching**: Finds commands by prefix, partial match, and context
- **Real-time Suggestions**: Updates as you type (minimum 2 characters)

### 🔧 Command Categories

The suggestion system includes commands from these categories:

- **Package Management**: `pkg`, `apt`, package installation and updates
- **File Operations**: `ls`, `cd`, `cp`, `mv`, `rm`, `find`, `grep`
- **Text Editors**: `nano`, `vim`, `vi`, `emacs`
- **Process Management**: `ps`, `top`, `htop`, `kill`
- **Network Tools**: `ping`, `curl`, `wget`, `ssh`, `scp`
- **System Information**: `uname`, `df`, `du`, `free`, `uptime`
- **Archives**: `tar`, `zip`, `unzip`, `gzip`
- **Development**: `git`, `python`, `node`, `npm`, `gcc`, `make`
- **Shell Utilities**: `echo`, `export`, `alias`, `source`
- **Termux-Specific**: `termux-setup-storage`, `termux-notification`, `termux-clipboard-*`, `termux-share`

### 🎯 Snippet Integration

The suggestion system also integrates with your **personal snippets**:
- Searches through your saved snippets automatically
- Matches snippet names and content
- Includes snippets tagged with relevant keywords
- Seamless integration with the built-in snippet system

---

## How It Works

### Automatic Detection

When you open Termux, the keyboard automatically:
1. Detects the Termux package name (`com.termux`)
2. Switches to command suggestion mode
3. Provides relevant command completions instead of dictionary suggestions

### Using Suggestions

1. **Type at least 2 characters** in Termux
2. **View suggestions** in the candidates bar above the keyboard
3. **Tap a suggestion** to complete the command
4. The command is inserted with a space for easy chaining

### Example Usage

**Typing: `pk`**
```
Suggestions: pkg | pkg install | pkg update
```

**Typing: `git c`**
```
Suggestions: git clone | git commit | git checkout
```

**Typing: `ls -`**
```
Suggestions: ls -la | ls -lh | ls -R
```

**Typing: `termux`**
```
Suggestions: termux-setup-storage | termux-clipboard-get | termux-notification
```

---

## Command Database

### Package Management
| Command | Description |
|---------|-------------|
| `pkg install` | Install a package |
| `pkg update` | Update package lists |
| `pkg upgrade` | Upgrade installed packages |
| `pkg search` | Search for packages |
| `apt update` | Update with APT |
| `apt install` | Install with APT |

### File Operations
| Command | Description |
|---------|-------------|
| `ls -la` | List all files with details |
| `cp -r` | Copy recursively |
| `rm -rf` | Remove recursively (use carefully!) |
| `find` | Find files |
| `grep` | Search text patterns |
| `tail -f` | Follow file updates |

### Development
| Command | Description |
|---------|-------------|
| `git clone` | Clone a repository |
| `git pull` | Pull changes |
| `git push` | Push changes |
| `git status` | Show repository status |
| `python3` | Python 3 interpreter |
| `npm install` | Install Node packages |

### Termux-Specific
| Command | Description |
|---------|-------------|
| `termux-setup-storage` | Setup storage access |
| `termux-clipboard-get` | Get clipboard content |
| `termux-clipboard-set` | Set clipboard content |
| `termux-notification` | Show system notification |
| `termux-share` | Share with other apps |
| `termux-open` | Open file externally |
| `termux-camera-photo` | Take a photo |
| `termux-location` | Get device location |

---

## Adding Custom Commands with Snippets

You can extend the suggestion system with your own commands using the snippet feature:

### 1. Open Snippet Manager
- Swipe up on the keyboard to access settings
- Navigate to **Snippets** or **Clipboard History**

### 2. Create Command Snippets

Example snippets for common workflows:

```bash
# Update and upgrade system
pkg update && pkg upgrade -y

# Install common development tools
pkg install python git nodejs

# Start SSH server
sshd && echo "SSH server started"

# Git shortcuts
git add . && git commit -m "update" && git push

# Python virtual environment
python -m venv venv && source venv/bin/activate

# Quick file server
python -m http.server 8000
```

### 3. Organize with Folders
- Create folders like "Development", "System", "Scripts"
- Group related commands together
- Use descriptive names for easy searching

### 4. Tag Your Snippets
- Add tags like `termux`, `git`, `python`, `sysadmin`
- Tags improve search accuracy
- Make snippets easier to find while typing

---

## Tips and Best Practices

### Efficient Command Entry
- **Type just 2-3 characters** and let suggestions complete the rest
- **Use common prefixes**: `ls`, `cd`, `git`, `pkg`
- **Combine with keyboard shortcuts**: Use the Termux Run key for complex scripts

### Organizing Snippets
- **Keep snippets short**: One command or simple chain
- **Use descriptive names**: "Update System" instead of "us"
- **Add context in tags**: Use tags for discovery

### Common Workflows

**Package Management:**
```bash
# Save these as snippets:
pkg update && pkg upgrade -y
pkg install [package-name]
pkg search [query]
```

**Git Workflows:**
```bash
# Common git operations:
git status
git add . && git commit -m "message"
git pull && git push
```

**File Management:**
```bash
# Useful file operations:
ls -lah
find . -name "*.txt"
grep -r "pattern" .
```

---

## Configuration

### Enable/Disable Suggestions

Termux command suggestions work alongside the main suggestion system:

1. Open **Settings** (swipe gear icon)
2. Navigate to **Suggestions** settings
3. Toggle **Enable suggestions** on/off

**Note**: When in Termux, the keyboard automatically provides command suggestions instead of dictionary word suggestions.

### Snippet Management

Configure snippet behavior:

1. Open **Settings**
2. Go to **Snippets**
3. Customize:
   - Create folders for organization
   - Add/edit snippets
   - Tag snippets for better search
   - Import/export snippet collections

---

## Troubleshooting

### Suggestions Not Showing

**Problem**: No suggestions appear when typing in Termux

**Solutions**:
1. Ensure you've typed at least 2 characters
2. Check that suggestions are enabled in Settings
3. Verify you're actually in the Termux app (package: `com.termux`)
4. Try restarting the keyboard input method

### Wrong Suggestions

**Problem**: Getting word suggestions instead of commands

**Solutions**:
1. Confirm you're in Termux (not another terminal app)
2. Check if Termux is properly installed from F-Droid
3. Try typing a common command prefix like `pkg` or `ls`

### Snippet Not Appearing

**Problem**: Custom snippet doesn't show in suggestions

**Solutions**:
1. Verify the snippet is saved correctly
2. Check that snippet content matches what you're typing
3. Try using tags to make snippets more discoverable
4. Reload snippets from Settings

---

## Technical Details

### Detection Mechanism

The keyboard identifies Termux through:
- **Package Name**: Checks if `packageName == "com.termux"`
- **Input Type**: Termux uses `InputType.TYPE_NULL` (raw input)
- **Context Switching**: Automatically switches to command mode

### Suggestion Algorithm

The system uses three strategies:
1. **Exact Match**: Prioritizes exact command matches
2. **Prefix Match**: Finds commands starting with the typed text
3. **Partial Match**: Searches for commands containing the text
4. **Snippet Search**: Queries user snippets by name, content, and tags

### Performance

- **Minimal Overhead**: Commands are stored in memory (no file I/O)
- **Fast Lookup**: HashMap-based command matching
- **Lazy Loading**: Snippet search only when needed
- **Efficient**: Processes 100+ commands in < 1ms

---

## Advanced Usage

### Creating Command Collections

Organize snippets into themed collections:

**Collection: "Termux Setup"**
```bash
termux-setup-storage
pkg update && pkg upgrade -y
pkg install vim git python nodejs
```

**Collection: "Server Management"**
```bash
sshd
python -m http.server 8000
nginx -t && nginx -s reload
```

**Collection: "Development"**
```bash
git clone [repo-url]
python -m venv venv
source venv/bin/activate
npm init -y
```

### Keyboard Shortcuts

Combine with Termux keyboard layouts for maximum efficiency:
- Use **Termux Developer** layout for programming symbols
- Use **Termux Commands** layout for shell operators
- Use **Termux Compact** layout for minimal screen usage

See [Termux-Layouts.md](Termux-Layouts.md) for more information.

---

## Related Features

- **[Termux Layouts](Termux-Layouts.md)** - Optimized keyboard layouts for terminal work
- **[Termux Run Setup](Termux-Run-Setup.md)** - Execute commands directly from keyboard
- **[Custom Layouts](Custom-layouts.md)** - Create your own keyboard layouts
- **[Snippets](../README.md#snippets)** - General snippet system documentation

---

## Contributing

### Adding New Commands

You can contribute additional commands to the suggestion database:

1. Fork the repository
2. Edit `srcs/juloo.keyboard2/suggestions/TermuxCommandsProvider.java`
3. Add commands to the `TERMUX_COMMANDS` array
4. Submit a pull request

### Reporting Issues

Found a bug or have a suggestion?
- [GitHub Issues](https://github.com/Julow/Unexpected-Keyboard/issues)
- Include your Termux version
- Describe the expected vs. actual behavior

---

## FAQ

**Q: Does this work with other terminal apps?**
A: Currently optimized for Termux. Other terminal emulators could be added by extending the package name detection in `EditorConfig.java`.

**Q: Can I disable command suggestions in Termux?**
A: Yes, disable "Enable suggestions" in Settings. This affects all apps, not just Termux.

**Q: Do suggestions require internet connection?**
A: No, all commands are stored locally. Snippet search is also local.

**Q: Can I export my snippets?**
A: Snippets are stored in app preferences. Future updates may add import/export functionality.

**Q: Will this slow down my keyboard?**
A: No, the command lookup is very fast (< 1ms) and runs asynchronously.

---

## Summary

Termux command autosuggestions make terminal work faster and more efficient by:
- Providing instant command completions
- Reducing typing errors
- Integrating with personal snippets
- Supporting 100+ common commands
- Working seamlessly with Termux layouts

Combine this with the [Termux Run](Termux-Run-Setup.md) feature and [specialized layouts](Termux-Layouts.md) for the ultimate Termux keyboard experience!

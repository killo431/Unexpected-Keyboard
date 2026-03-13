package juloo.keyboard2.suggestions;

import android.content.Context;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import juloo.keyboard2.Snippet;
import juloo.keyboard2.SnippetManager;

/**
 * Provides Termux command suggestions based on what the user is typing.
 * Includes common Termux commands and integrates with user's snippets.
 */
public final class TermuxCommandsProvider
{
  // Common Termux commands with descriptions
  private static final String[][] TERMUX_COMMANDS = {
    // Package management
    {"pkg", "Package manager"},
    {"pkg install", "Install package"},
    {"pkg update", "Update package lists"},
    {"pkg upgrade", "Upgrade packages"},
    {"pkg uninstall", "Uninstall package"},
    {"pkg search", "Search for package"},
    {"pkg show", "Show package info"},
    {"pkg list-all", "List all packages"},
    {"pkg list-installed", "List installed packages"},
    {"apt", "APT package manager"},
    {"apt update", "Update package lists"},
    {"apt upgrade", "Upgrade packages"},
    {"apt install", "Install package"},
    {"apt remove", "Remove package"},
    {"apt search", "Search packages"},

    // File operations
    {"ls", "List files"},
    {"ls -la", "List all files with details"},
    {"cd", "Change directory"},
    {"pwd", "Print working directory"},
    {"mkdir", "Make directory"},
    {"rmdir", "Remove directory"},
    {"rm", "Remove file"},
    {"rm -rf", "Remove recursively"},
    {"cp", "Copy file"},
    {"cp -r", "Copy recursively"},
    {"mv", "Move/rename file"},
    {"cat", "Display file content"},
    {"touch", "Create empty file"},
    {"find", "Find files"},
    {"grep", "Search text"},
    {"less", "View file page by page"},
    {"head", "Show first lines"},
    {"tail", "Show last lines"},
    {"tail -f", "Follow file updates"},

    // Text editors
    {"nano", "Text editor"},
    {"vi", "Vi editor"},
    {"vim", "Vim editor"},
    {"emacs", "Emacs editor"},

    // File permissions
    {"chmod", "Change file mode"},
    {"chmod +x", "Make executable"},
    {"chown", "Change owner"},

    // Process management
    {"ps", "List processes"},
    {"ps aux", "List all processes"},
    {"top", "Process monitor"},
    {"htop", "Interactive process viewer"},
    {"kill", "Kill process"},
    {"killall", "Kill processes by name"},
    {"bg", "Background process"},
    {"fg", "Foreground process"},
    {"jobs", "List jobs"},

    // Network
    {"ping", "Ping host"},
    {"curl", "Transfer data from URL"},
    {"wget", "Download files"},
    {"ssh", "Secure shell"},
    {"scp", "Secure copy"},
    {"netstat", "Network statistics"},
    {"ifconfig", "Network interface config"},

    // System info
    {"uname", "System information"},
    {"uname -a", "All system info"},
    {"df", "Disk free space"},
    {"df -h", "Disk space human-readable"},
    {"du", "Disk usage"},
    {"du -sh", "Summary disk usage"},
    {"free", "Memory usage"},
    {"free -h", "Memory usage human-readable"},
    {"uptime", "System uptime"},
    {"whoami", "Current user"},

    // Archive and compression
    {"tar", "Archive tool"},
    {"tar -xzf", "Extract tar.gz"},
    {"tar -czf", "Create tar.gz"},
    {"unzip", "Extract zip"},
    {"zip", "Create zip"},
    {"gzip", "Compress file"},
    {"gunzip", "Decompress file"},

    // Development tools
    {"git", "Git version control"},
    {"git clone", "Clone repository"},
    {"git pull", "Pull changes"},
    {"git push", "Push changes"},
    {"git commit", "Commit changes"},
    {"git status", "Show status"},
    {"git add", "Add files"},
    {"git log", "Show log"},
    {"git diff", "Show differences"},
    {"python", "Python interpreter"},
    {"python3", "Python 3 interpreter"},
    {"node", "Node.js runtime"},
    {"npm", "Node package manager"},
    {"gcc", "C compiler"},
    {"make", "Build automation"},
    {"clang", "C/C++ compiler"},

    // Shell scripting
    {"echo", "Print text"},
    {"printf", "Formatted print"},
    {"export", "Set environment variable"},
    {"alias", "Create command alias"},
    {"source", "Execute script in current shell"},
    {"bash", "Bash shell"},
    {"sh", "Shell"},

    // Pipes and redirection
    {"|", "Pipe output"},
    {">", "Redirect output"},
    {">>", "Append output"},
    {"<", "Redirect input"},
    {"2>", "Redirect stderr"},
    {"&&", "And operator"},
    {"||", "Or operator"},

    // Termux specific
    {"termux-setup-storage", "Setup storage access"},
    {"termux-notification", "Show notification"},
    {"termux-toast", "Show toast message"},
    {"termux-clipboard-get", "Get clipboard content"},
    {"termux-clipboard-set", "Set clipboard content"},
    {"termux-share", "Share with other apps"},
    {"termux-open", "Open file with external app"},
    {"termux-camera-photo", "Take photo"},
    {"termux-location", "Get device location"},
    {"termux-battery-status", "Battery status"},
    {"termux-wifi-connectioninfo", "WiFi info"},

    // Utilities
    {"clear", "Clear screen"},
    {"history", "Command history"},
    {"man", "Manual pages"},
    {"which", "Locate command"},
    {"date", "Show date/time"},
    {"cal", "Calendar"},
    {"bc", "Calculator"},
    {"tee", "Write to file and stdout"},
    {"wc", "Word count"},
    {"sort", "Sort lines"},
    {"uniq", "Remove duplicates"},
    {"awk", "Text processing"},
    {"sed", "Stream editor"},
    {"xargs", "Build command lines"},
  };

  private final Map<String, String> commandsMap;
  private final Context context;

  public TermuxCommandsProvider(Context ctx)
  {
    context = ctx;
    commandsMap = new HashMap<>();
    for (String[] cmd : TERMUX_COMMANDS)
    {
      commandsMap.put(cmd[0], cmd.length > 1 ? cmd[1] : "");
    }
  }

  /**
   * Get command suggestions for the given prefix.
   * @param prefix The text typed so far
   * @param maxResults Maximum number of results to return
   * @return List of matching commands
   */
  public List<String> getSuggestions(String prefix, int maxResults)
  {
    List<String> results = new ArrayList<>();

    if (prefix == null || prefix.isEmpty())
      return results;

    String lowerPrefix = prefix.toLowerCase();

    // First, try to find exact and prefix matches
    for (String[] cmd : TERMUX_COMMANDS)
    {
      String command = cmd[0];
      if (command.equals(lowerPrefix))
      {
        // Exact match - add first
        if (!results.contains(command))
          results.add(0, command);
      }
      else if (command.startsWith(lowerPrefix))
      {
        // Prefix match
        if (!results.contains(command) && results.size() < maxResults)
          results.add(command);
      }
    }

    // If we don't have enough results, try partial matches
    if (results.size() < maxResults)
    {
      for (String[] cmd : TERMUX_COMMANDS)
      {
        String command = cmd[0];
        if (command.contains(lowerPrefix) && !results.contains(command))
        {
          results.add(command);
          if (results.size() >= maxResults)
            break;
        }
      }
    }

    // Also search user's snippets if SnippetManager is available
    try
    {
      SnippetManager snippetMgr = SnippetManager.get(context);
      if (snippetMgr != null)
      {
        List<juloo.keyboard2.SnippetItem> snippetResults = snippetMgr.findSnippets(lowerPrefix);
        for (juloo.keyboard2.SnippetItem item : snippetResults)
        {
          if (item instanceof Snippet && results.size() < maxResults)
          {
            Snippet snippet = (Snippet) item;
            // Add snippet content if not already in results
            if (snippet.content != null && !results.contains(snippet.content))
            {
              results.add(snippet.content);
            }
          }
        }
      }
    }
    catch (Exception e)
    {
      // Ignore snippet errors - continue with command suggestions
    }

    return results;
  }

  /**
   * Check if a given text is a known Termux command.
   */
  public boolean isKnownCommand(String text)
  {
    return commandsMap.containsKey(text);
  }

  /**
   * Get description for a command.
   */
  public String getCommandDescription(String command)
  {
    return commandsMap.get(command);
  }
}

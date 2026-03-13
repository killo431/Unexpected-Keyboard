package juloo.keyboard2.suggestions;

import android.content.Context;
import java.util.Arrays;
import java.util.List;
import juloo.cdict.Cdict;
import juloo.keyboard2.dict.Dictionaries;
import juloo.keyboard2.Config;

/** Keep track of the word being typed and provide suggestions for
    [CandidatesView]. */
public final class Suggestions
{
  Callback _callback;
  Config _config;
  TermuxCommandsProvider _termuxCommands;

  public Suggestions(Callback c, Config conf)
  {
    _callback = c;
    _config = conf;
    _termuxCommands = null;
  }

  public void setContext(Context context)
  {
    if (context != null && _termuxCommands == null)
    {
      _termuxCommands = new TermuxCommandsProvider(context);
    }
  }

  public void currently_typed_word(String word)
  {
    // Check if we're in Termux and should provide command suggestions
    if (_config.editor_config != null && _config.editor_config.is_termux && _termuxCommands != null)
    {
      provide_termux_suggestions(word);
    }
    else
    {
      provide_dictionary_suggestions(word);
    }
  }

  private void provide_termux_suggestions(String word)
  {
    if (word.length() < 2)
    {
      _callback.set_suggestions(NO_SUGGESTIONS);
      return;
    }

    // Get Termux command suggestions
    List<String> termuxSuggestions = _termuxCommands.getSuggestions(word, 3);
    _callback.set_suggestions(termuxSuggestions);
  }

  private void provide_dictionary_suggestions(String word)
  {
    Cdict dict = _config.current_dictionary;
    if (word.length() < 2 || dict == null)
    {
      _callback.set_suggestions(NO_SUGGESTIONS);
    }
    else
    {
      Cdict.Result r = dict.find(word);
      String[] suggestions = new String[3];
      int i = 0;
      if (r.found)
        suggestions[i++] = word;
      int[] suffixes = dict.suffixes(r, 3);
      int[] dist = dict.distance(word, 1, 3);
      for (int j = 0; j < 3 && i < 3; j++)
      {
        if (suffixes.length > j)
          suggestions[i++] = dict.word(suffixes[j]);
        if (dist.length > j && i < 3)
          suggestions[i++] = dict.word(dist[j]);
      }
      _callback.set_suggestions(Arrays.asList(suggestions));
    }
  }

  static final List<String> NO_SUGGESTIONS = Arrays.asList();

  public static interface Callback
  {
    public void set_suggestions(List<String> suggestions);
  }
}

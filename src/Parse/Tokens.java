package Parse;

import static Parse.PandaSym.*;

public class Tokens {
   public static String dumpToken(java_cup.runtime.Symbol tok) {
      switch (tok.sym) {
      case LITINT    : return "LITINT(" + tok.value + ")";
      case LITREAL   : return "LITREAL(" + tok.value + ")";
      case LITBOOL   : return "LITBOOL(" + tok.value + ")";
      case LITCHAR   : return "LITCHAR(" + tok.value + ")";
      case LITSTRING : return "LITSTRING(" + tok.value + ")";
      case ID        : return "ID(" + tok.value + ")";
      default        : return PandaSym.terminalNames[tok.sym];
      }
   }
}

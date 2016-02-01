package panda.Parse;

import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;

public class Tokens {

   public static String dumpToken(java_cup.runtime.Symbol tok) {
      if (tok instanceof ComplexSymbol) {
         ComplexSymbol t = (ComplexSymbol) tok;
         if (tok.value == null)
            return String.format("%s  %s-%s", t.getName(), t.xleft, t.xright);
         else
            return String.format("%s(%s)  %s-%s", t.getName(), t.value, t.xleft, t.xright);
      }
      else
         if (tok.value == null)
            return String.format("%s", PandaSym.terminalNames[tok.sym]);
         else
            return String.format("%s(%s)", PandaSym.terminalNames[tok.sym], tok.value);
   }

}

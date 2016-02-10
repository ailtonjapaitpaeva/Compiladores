package panda.Parse;

import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;
import panda.ErrorMsg.Loc;

public class Tokens {

   public static String dumpToken(java_cup.runtime.Symbol tok) {
      if (tok instanceof ComplexSymbol) {
         ComplexSymbol t = (ComplexSymbol) tok;
         Loc loc = new Loc(t.xleft, t.xright);
         if (tok.value == null)
            return String.format("%s  %s", t.getName(), loc);
         else
            return String.format("%s(%s)  %s", t.getName(), t.value, loc);
      }
      else
         if (tok.value == null)
            return String.format("%s", PandaSym.terminalNames[tok.sym]);
         else
            return String.format("%s(%s)", PandaSym.terminalNames[tok.sym], tok.value);
   }

}

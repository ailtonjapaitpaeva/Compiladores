package panda.Main;

import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;
import panda.Absyn.Absyn;
import panda.Parse.PandaLex;
import panda.Parse.PandaSym;
import panda.Parse.Tokens;


public class TestLexer {
   
   public static void main(String[] args) throws Exception {
      java.io.Reader input;
      String fileName;

      if (args.length == 0) {
         fileName = "stdin";
         input = new java.io.InputStreamReader(System.in);
      } else {
         fileName = args[0];
         input = new java.io.FileReader(fileName);
      }

      ComplexSymbolFactory symbolFactory = new ComplexSymbolFactory();      
      PandaLex lex = new PandaLex(fileName, input, symbolFactory, Absyn.E);
      ComplexSymbol tok;
      do {
         tok = (ComplexSymbol) lex.next_token();
         System.out.println(Tokens.dumpToken(tok));
      } while (tok.sym != PandaSym.EOF);
   }

}

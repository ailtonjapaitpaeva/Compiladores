package Main;

import Absyn.Absyn;
import ErrorMsg.Source;
import Parse.PandaLex;
import Parse.PandaSym;
import Parse.Tokens;

public class TestLexer {
   public static void main(String[] args) throws Exception {
      java.io.Reader input;
      String fileName;
      Source src;

      if (args.length == 0) {
         fileName = "stdin";
         input = new java.io.InputStreamReader(System.in);
         src = new Source("-");
      } else {
         fileName = args[0];
         input = new java.io.FileReader(fileName);
         src = new Source(fileName);
      }

      PandaLex lex = new PandaLex(input, src, Absyn.E);
      java_cup.runtime.Symbol tok;
      do {
         tok = lex.next_token();
         System.out.printf("%3d.%3d %s\n", tok.left, tok.right, Tokens.dumpToken(tok));
      } while (tok.sym != PandaSym.EOF);
   }
}

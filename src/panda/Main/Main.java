package panda.Main;


import java_cup.runtime.ComplexSymbolFactory;
import panda.Absyn.Absyn;
import panda.Absyn.Exp;
import panda.Parse.PandaCup;
import panda.Parse.PandaLex;
import panda.Util.BoxTree;
import javaslang.render.text.Boxes;

public class Main {

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
      PandaCup p = new PandaCup(lex, symbolFactory, Absyn.E);

      try {
         java_cup.runtime.Symbol prog = p.parse();

         Exp parseTree = (Exp) prog.value;

         System.out.println("===Abstract syntax tree:===========");
         // System.out.println(parseTree.toTree().prettyPrint());
         // System.out.println();
         System.out.println(Boxes.box(parseTree.toTree()));
         BoxTree.dot(parseTree.toTree(), "test1.dot");
         System.out.println();

         parseTree.semant();
         System.out.println();

         System.out.println("===Infered types:==================");
         // System.out.println(parseTree.toTree().prettyPrint());
         // System.out.println();
         System.out.println(Boxes.box(parseTree.toTree()));
         // parseTree.toTree().map(new Function<String,String>() {
         // public String apply(String s) { return s.replace('\n', ':'); }
         // }).dot(new File(fileName).getName() + ".dot");
         // System.out.println();
      } catch (Exception e) {
         System.out.println("Compilation failed");
         throw e;
//         System.exit(1);
      }
   }

}

package Main;

import Absyn.Absyn;
import Absyn.Exp;
import ErrorMsg.Source;
import Parse.PandaCup;
import Parse.PandaLex;

public class Main
{
  public static void main(String[] args) throws Exception
  {
    java.io.Reader input;
    String fileName;
    Source src;

    if (args.length == 0)
    {
      fileName = "stdin";
      input = new java.io.InputStreamReader(System.in);
      src = new Source("-");
    }
    else
    {
      fileName = args[0];
      input = new java.io.FileReader(fileName);
      src = new Source(fileName);
    }

    PandaLex lex = new PandaLex(input, src, Absyn.e);
    PandaCup p = new PandaCup(lex, Absyn.e);

    try
    {
      java_cup.runtime.Symbol prog = p.parse();

      Exp parseTree = (Exp) prog.value;

      System.out.println("===Abstract syntax tree:===========");
      // System.out.println(parseTree.toTree().prettyPrint());
      // System.out.println();
      System.out.println(parseTree.toTree().box());
      parseTree.toTree().dot("test1.dot");
      System.out.println();

      parseTree.semant();
      System.out.println();

      System.out.println("===Infered types:==================");
      // System.out.println(parseTree.toTree().prettyPrint());
      // System.out.println();
      System.out.println(parseTree.toTree().box());
      // parseTree.toTree().map(new Function<String,String>() {
      // public String apply(String s) { return s.replace('\n', ':'); }
      // }).dot(new File(fileName).getName() + ".dot");
      // System.out.println();
    }
    catch (Exception e)
    {
      // throw e;
      System.out.println("Compilation failed");
      System.exit(1);
    }
  }
}

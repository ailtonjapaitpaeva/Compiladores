package Main;

import Util.Tree_;
import javaslang.collection.Iterator;
import javaslang.collection.Tree;

public class TestTree {

   public static <T> String prettyPrint(Tree<T> t) {
      if (t.isEmpty())
         return "";
      else {
         StringBuilder builder = new StringBuilder();
         prettyPrint((Tree.Node<T>)t, "", builder);
         return builder.toString();
      }
   }

   private static <T> void prettyPrint(Tree.Node<T> t, String indent, StringBuilder s) {
      s.append(t.getValue().toString());
      Iterator<Tree.Node<T>> i = t.getChildren().iterator();
      while (i.hasNext()) {
         final Tree.Node<T> c = i.next();
         final boolean last = ! i.hasNext();
         s.append('\n')
          .append(indent)
          .append(last ? "└──" : "├──");
         prettyPrint(c, indent + (last ? "   " : "│  "), s);
      }
   }

   public static void main(String[] args) {
      Tree<String> t = Tree.of("Maria", Tree.of("Paulo", Tree.of("Manoel", Tree.of("Silva")), Tree.of("Zica")), Tree.of("Ana"));
      System.out.println(t);
      System.out.println(prettyPrint(t));
   }

}

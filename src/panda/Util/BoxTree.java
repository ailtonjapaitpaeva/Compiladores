package panda.Util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.tree.DefaultMutableTreeNode;

import javaslang.collection.List;
import javaslang.collection.Tree;

public class BoxTree {

   public static <T> Box box(Tree<T> t) {
      Box b = new Box(t.get().toString()).frame();
      List<Tree.Node<T>> children = t.getChildren();
      if (children.isEmpty())
         return b;

      LinkedList<Box> lst = new LinkedList<>();
      for (Tree.Node<T> child : children)
         lst.add(box(child));
      return b.connect(lst);
   }

   public static <T> void dot(Tree<T> t, File file) throws IOException {
      StringBuffer buffer = new StringBuffer();
      buffer.append("digraph AST {\n");
      dot(t, buffer, "root", "\t");
      buffer.append("}\n");
      FileWriter writer = new FileWriter(file);
      writer.write(buffer.toString());
      writer.close();
   }

   public static <T> void dot(Tree<T> t, String fileName) throws IOException {
      dot(t, new File(fileName));
   }

   private static <T> void dot(Tree<T> t, StringBuffer buffer, String nodeName, String ident) {
      buffer.append(String.format("%s%s [label=\"%s\"];\n", ident, nodeName, t.get().toString()));
      int i = 1;
      for (Tree.Node<T> child : t.getChildren()) {
         String node = nodeName + "_" + i++;
         dot(child, buffer, node, ident + "\t");
         buffer.append(String.format("%s%s -> %s;\n", ident, nodeName, node));
      }
   }

   public static <T> DefaultMutableTreeNode toDefaultMutableTreeNode(Tree<T> tree) {
      DefaultMutableTreeNode mt = new DefaultMutableTreeNode(tree.get());
      for (Tree.Node<T> child : tree.getChildren())
         mt.add(toDefaultMutableTreeNode(child));
      return mt;
   }

}

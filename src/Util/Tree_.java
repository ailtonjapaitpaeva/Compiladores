package Util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import javaslang.collection.Iterator;
import javaslang.collection.List;

public class Tree_<E> {
   public E info;
   public List<Tree_<E>> children;

   public Tree_(E info) {
      this(info, List.empty());
   }

   public Tree_(E info, List<Tree_<E>> children) {
      this.info = info;
      this.children = children;
   }

   public Tree_<E> addChild(Tree_<E> child) {
      children = children.append(child);
      return this;
   }

   public <F> Tree_<F> map(Function<E, F> f) {
      return new Tree_<F>(f.apply(info), children.map(t -> t.map(f)));
   }

   public String prettyPrint() {
      return prettyPrint("").toString();
   }

   private StringBuilder prettyPrint(String indent) {
      StringBuilder builder = new StringBuilder(info.toString());
      Iterator<Tree_<E>> i = children.iterator();
      while (i.hasNext()) {
         final boolean last = ! i.hasNext();
         builder.append('\n').append(indent).append("+--")
                .append(i.next().prettyPrint(indent + (last ? "|  " : "   ")));
      }
      return builder;
   }

   public Box box() {
      Box b = new Box(info.toString()).frame();
      if (children == null)
         return b;

      LinkedList<Box> lst = new LinkedList<Box>();
      for (Tree_<E> t : children)
         lst.add(t.box());
      return b.connect(lst);
   }

   public void dot(File file) throws IOException {
      StringBuffer buffer = new StringBuffer();
      buffer.append("digraph AST {\n");
      dot(buffer, "root", "\t");
      buffer.append("}\n");
      FileWriter writer = new FileWriter(file);
      writer.write(buffer.toString());
      writer.close();
   }

   public void dot(String fileName) throws IOException {
      dot(new File(fileName));
   }

   private void dot(StringBuffer buffer, String nodeName, String ident) {
      buffer.append(String.format("%s%s [label=\"%s\"];\n", ident, nodeName, info.toString()));
      int i = 1;
      for (Tree_<E> t : children) {
         String node = nodeName + "_" + i++;
         t.dot(buffer, node, ident + "\t");
         buffer.append(String.format("%s%s -> %s;\n", ident, nodeName, node));
      }
   }
}

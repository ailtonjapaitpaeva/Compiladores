package panda.Types;

import javaslang.collection.Tree;
import javaslang.collection.Tree.Node;
import panda.Util.ToTree;

public abstract class Type implements ToTree<String> {

   public Type actual() {
      return this;
   }

   @Override
   public String toString() {
      return String.format("Type []");
   }

   public boolean coerceTo(Type t) {
      return t.actual() == this;
   }

   @Override
   public Node<String> toTree() {
      return Tree.of(toString());
   }
}

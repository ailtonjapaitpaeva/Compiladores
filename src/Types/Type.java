package Types;

import Util.ToTree;
import Util.Tree;

public abstract class Type implements ToTree<String> {

   public Type actual() {
      return this;
   }

   public boolean coerceTo(Type t) {
      return t.actual() == this;
   }

   @Override
   public Tree<String> toTree() {
      return new Tree<>(toString());
   }
}

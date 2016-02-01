package panda.Absyn;

import javaslang.collection.Tree;
import javaslang.collection.Tree.Node;
import panda.ErrorMsg.Loc;
import panda.Types.BOOL;
import panda.Types.Type;

public class BoolExp extends Exp {

   public final Boolean val;

   public BoolExp(Loc loc, Boolean val) {
      super(loc);
      this.val = val;
   }

   @Override
   public String toString() {
      return String.format("BoolExp [val=%s]", val);
   }

   @Override
   public Node<String> toTree() {
      return Tree.of(tp("BoolExp: " + val));
   }

   @Override
   protected Type typeCheck() {
      return BOOL.T;
   }

}

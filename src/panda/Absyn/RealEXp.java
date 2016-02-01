package panda.Absyn;

import javaslang.collection.Tree;
import javaslang.collection.Tree.Node;
import panda.ErrorMsg.Loc;
import panda.Types.INT;
import panda.Types.Type;

public class RealEXp extends Exp {

   public final Long val;

   public RealEXp(Loc loc, Long val) {
      super(loc);
      this.val = val;
   }

   @Override
   public String toString() {
      return String.format("IntExp [val=%s]", val);
   }

   @Override
   public Node<String> toTree() {
      return Tree.of(tp("IntExp: " + val));
   }

   @Override
   protected Type typeCheck() {
      return INT.T;
   }

}

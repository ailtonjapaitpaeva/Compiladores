package panda.Absyn;

import javaslang.collection.Tree;
import javaslang.collection.Tree.Node;
import panda.ErrorMsg.Loc;
import panda.Types.INT;
import panda.Types.REAL;
import panda.Types.Type;

public class RealExp extends Exp {

   public final Double val;

   public RealExp(Loc loc, Double val) {
      super(loc);
      this.val = val;
   }

   @Override
   public String toString() {
      return String.format("RealExp [val=%s]", val);
   }

   @Override
   public Node<String> toTree() {
      return Tree.of(tp("RealExp: " + val));
   }

   @Override
   protected Type typeCheck() {
      return REAL.T;
   }

}

package panda.Absyn;

import javaslang.collection.Tree;
import javaslang.collection.Tree.Node;
import panda.ErrorMsg.Loc;
import panda.Types.CHAR;
import panda.Types.Type;

public class CharExp extends Exp {

   public final Character val;

   public CharExp(Loc loc, Character val) {
      super(loc);
      this.val = val;
   }

   @Override
   public String toString() {
      return String.format("CharExp [val=%s]", val);
   }

   @Override
   public Node<String> toTree() {
      return Tree.of(tp("CharExp: " + val));
   }

   @Override
   protected Type typeCheck() {
      return CHAR.T;
   }

}

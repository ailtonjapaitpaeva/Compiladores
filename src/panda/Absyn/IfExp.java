package panda.Absyn;

import javaslang.collection.Tree;
import javaslang.collection.Tree.Node;
import panda.ErrorMsg.Loc;
import panda.Types.BOOL;
import panda.Types.Type;
import panda.Types.UNIT;

public class IfExp extends Exp {

   public final Exp test;
   public final Exp alt1;
   public final Exp alt2;

   public IfExp(Loc loc, Exp test, Exp alt1, Exp alt2) {
      super(loc);
      this.test = test;
      this.alt1 = alt1;
      this.alt2 = alt2;
   }

   @Override
   public String toString() {
      return String.format("IfExp [test=%s, alt1=%s, alt2=%s]", test, alt1, alt2);
   }

   @Override
   public Node<String> toTree() {
      if (alt2 == null)
         return Tree.of(tp("IfExp"), test.toTree(), alt1.toTree());
      else
         return Tree.of(tp("IfExp"), test.toTree(), alt1.toTree(), alt2.toTree());
   }

   @Override
   protected Type typeCheck() {
      Type t_test = test.semant();
      if (!t_test.coerceTo(BOOL.T))
         test.error("type mismatch in test of conditional should be %s, found %s",
               BOOL.T, t_test);
      Type t_alt1 = alt1.semant();
      if (alt2 == null) {
         if (!t_alt1.coerceTo(UNIT.T))
            alt1.error("type mismatch in conditional should be %s, found %s",
                  UNIT.T, t_alt1);
         return UNIT.T; 
      }
      else {
         Type t_alt2 = alt2.semant();
         if (t_alt1.coerceTo(t_alt2))
            return t_alt2;
         else if (t_alt2.coerceTo(t_alt1))
            return t_alt1;
         error("type mismatch in alternatives of conditional: %s and %s",
               t_alt1, t_alt2);
         return t_alt1;
      }
   }

}

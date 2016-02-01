package panda.Absyn;

import panda.ErrorMsg.Loc;
import panda.Types.Type;

public abstract class Var extends Absyn {

   public Type type; // the type of the expression

   public Var(Loc loc) {
      super(loc);
   }

   // do semantic analysis of the variable
   public Type semant() {
      type = typeCheck();
      return type;
   }

   // type check the variable
   protected Type typeCheck() {
      throw new RuntimeException("unimplemented");
   }

   // obtain the type of the variable as a string
   protected String tp(String x) {
      return x + (type == null ? "" : "\n<" + type + ">");
   }

}

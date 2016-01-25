package Absyn;

import Types.Type;
import ErrorMsg.Loc;

public abstract class Exp extends Absyn {
   public Type type; // the type of the expression

   public Exp(Loc loc) {
      super(loc);
   }

   // do semantic analysis of the expression
   public Type semant() {
      type = typeCheck();
      return type;
   }

   // type check the expression
   protected Type typeCheck() {
      throw new RuntimeException("unimplemented");
   }

   // obtain the type of the expression as a string
   protected String tp(String x) {
      return x + (type == null ? "" : "\n<" + type + ">");
   }
}

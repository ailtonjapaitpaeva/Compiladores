package Absyn;

import ErrorMsg.Loc;

public abstract class Dec extends Absyn {
   public Dec(Loc loc) {
      super(loc);
   }

   // do semantic analysis of the declaration
   public void semant() {
      throw new RuntimeException("unimplemented");
   }
}

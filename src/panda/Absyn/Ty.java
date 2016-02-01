package panda.Absyn;

import panda.ErrorMsg.Loc;
import panda.Types.Type;

public abstract class Ty extends Absyn {

   public Ty(Loc loc) {
      super(loc);
   }

   // do semantic analysis of the type expression
   public Type semant() {
      throw new RuntimeException("unimplemented");
   }

}

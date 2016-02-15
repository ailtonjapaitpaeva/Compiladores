package panda.Absyn;

import panda.ErrorMsg.Loc;

public abstract class Dec extends Absyn {

   public Dec(Loc loc) {
      super(loc);
   }

   // do semantic analysis of the declaration
   public abstract void semant() ;

}

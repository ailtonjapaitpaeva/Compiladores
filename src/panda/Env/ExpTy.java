package panda.Env;

import panda.Types.Type;

public class ExpTy {

   panda.Translate.Exp exp;
   Type ty;

   public ExpTy(panda.Translate.Exp exp, Type ty) {
      this.exp = exp;
      this.ty = ty;
   }

}

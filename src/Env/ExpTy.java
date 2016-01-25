package Env;

class ExpTy
{
  Translate.Exp exp;
  Types.Type ty;

  public ExpTy(Translate.Exp exp, Types.Type ty)
  {
    this.exp = exp;
    this.ty = ty;
  }
}

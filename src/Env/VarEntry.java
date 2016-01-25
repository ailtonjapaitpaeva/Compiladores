package Env;

import Types.Type;

public class VarEntry extends Entry
{
  public Type ty;

  public VarEntry(Type ty)
  {
    this.ty = ty;
  }
}

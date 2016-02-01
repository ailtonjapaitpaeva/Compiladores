package panda.Env;

import panda.Types.Type;

public class VarEntry extends Entry {

   public Type ty;

   public VarEntry(Type ty) {
      this.ty = ty;
   }

   @Override
   public String toString() {
      return String.format("VarEntry [ty=%s]", ty);
   }

}

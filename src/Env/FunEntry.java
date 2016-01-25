package Env;

import Types.Type;
import util.List;

public class FunEntry extends Entry {
   public Type result;
   public List<Type> formals;

   public FunEntry(Type result, List<Type> formals) {
      this.result = result;
      this.formals = formals;
   }

   public FunEntry(Type result, Type... formals) {
      this(result, List.of(formals));
   }
}

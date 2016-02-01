package panda.Env;

import javaslang.collection.List;
import panda.Types.Type;

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

   @Override
   public String toString() {
      return String.format("FunEntry [result=%s, formals=%s]", result, formals);
   }

}

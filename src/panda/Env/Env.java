package panda.Env;

import panda.Symbol.Symbol;
import panda.Symbol.Table;
import panda.Types.BOOL;
import panda.Types.CHAR;
import panda.Types.INT;
import panda.Types.REAL;
import panda.Types.Type;
import panda.Types.UNIT;

public class Env {

   public Table<Type> tenv;
   public Table<Entry> venv;

   public Env() {
      tenv = new Table<Type>();
      put(tenv, "unit", UNIT.T);
      put(tenv, "int", INT.T);
      put(tenv, "bool", BOOL.T);
      put(tenv, "real", REAL.T);
      put(tenv, "char", CHAR.T);
      
      
      // COMPLETAR COM OS DEMAIS TIPOS PRE-DEFINIDOS

      venv = new Table<Entry>();
      put(venv, "print_int", new FunEntry(UNIT.T, INT.T));
      put(venv, "print_bool", new FunEntry(UNIT.T, BOOL.T));
      put(venv, "not", new FunEntry(BOOL.T, BOOL.T));
   }

   @Override
   public String toString() {
      return String.format("Env [tenv=%s, venv=%s]", tenv, venv);
   }

   private static <E> void put(Table<E> table, String name, E value) {
      table.put(Symbol.symbol(name), value);
   }

}

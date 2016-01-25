package Env;

import Symbol.Symbol;
import Symbol.Table;
/*import Types.BOOL;
import Types.INT;
import Types.REAL;
import Types.STRING;*/
import Types.Type;
import Types.UNIT;

public class Env
{
  public Table<Type> tenv;
  public Table<Entry> venv;

  public Env()
  {
    tenv = new Table<Type>();
    put(tenv, "unit", UNIT.T);
    put(tenv, "int", INT.T);
    put(tenv, "real", REAL.T);
    put(tenv, "bool", BOOL.T);
    put(tenv, "string", STRING.T);

    venv = new Table<Entry>();
    put(venv, "print_bool", new FunEntry(UNIT.T, BOOL.T));
    put(venv, "print_int", new FunEntry(UNIT.T, INT.T));
    put(venv, "print_real", new FunEntry(UNIT.T, REAL.T));
    put(venv, "print", new FunEntry(UNIT.T, STRING.T));
    put(venv, "not", new FunEntry(BOOL.T, BOOL.T));
    put(venv, "round", new FunEntry(INT.T, REAL.T));
    put(venv, "ceil", new FunEntry(INT.T, REAL.T));
    put(venv, "floor", new FunEntry(INT.T, REAL.T));
    put(venv, "real", new FunEntry(REAL.T, INT.T));
    put(venv, "length", new FunEntry(INT.T, STRING.T));
    put(venv, "substring", new FunEntry(STRING.T, STRING.T, INT.T, INT.T));
  }

  private static <E> void put(Table<E> table, String name, E value)
  {
    table.put(Symbol.symbol(name), value);
  }
}

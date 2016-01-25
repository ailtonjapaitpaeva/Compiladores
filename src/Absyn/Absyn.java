package Absyn;

import Env.Env;
import ErrorMsg.ErrorMsg;
import ErrorMsg.Loc;
import util.ToTree;

public abstract class Absyn implements ToTree<String> {
   protected final Loc loc;

   public Absyn(Loc loc) {
      this.loc = loc;
   }

   protected void error(String msg) {
      error(loc, msg);
   }

   protected static void error(Loc loc, String msg) {
      E.error(loc, msg);
   }

   // a global error object
   public static final ErrorMsg E = new ErrorMsg();

   // a global environment (standard library)
   protected static final Env ENV = new Env();
}

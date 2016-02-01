package panda.Absyn;

import panda.Env.Env;
import panda.ErrorMsg.ErrorMsg;
import panda.ErrorMsg.Loc;
import panda.Util.ToTree;

public abstract class Absyn implements ToTree< String > {
   
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
   public static ErrorMsg E = new ErrorMsg();

   // a global environment (standard library)
   protected static final Env ENV = new Env();

}

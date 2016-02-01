package panda.ErrorMsg;

public class ErrorMsg {

   public int errorCounter; // has any error been found?

   public ErrorMsg() {
      errorCounter = 0;
   }

   @Override
   public String toString() {
      return String.format("ErrorMsg [anyErrors=%s]", errorCounter);
   }

   public int anyErrors() {
      return errorCounter;
   }

   public void error(Loc loc, String msg) {
      errorCounter++;
      System.out.printf("%s %s\n", loc, msg);
   }
}

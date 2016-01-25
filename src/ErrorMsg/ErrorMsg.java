package ErrorMsg;

public class ErrorMsg {
   
   private int anyErrors; // has any error been found?
   
   public ErrorMsg() {
      anyErrors = 0;
   }

   @Override
   public String toString() {
      return String.format("ErrorMsg [anyErrors=%s]", anyErrors);
   }

   public int isAnyErrors() {
      return anyErrors;
   }

   public void error(Loc loc, String msg) {
      anyErrors++;
      System.out.printf("%s %s\n", loc, msg);
   }
}

package panda.ErrorMsg;

import java_cup.runtime.ComplexSymbolFactory.Location;

public class Loc {
   
   public Location left;
   public Location right;

   public Loc() {
      this(new Location(-1, -1), new Location(-1, -1));
   }

   public Loc(Location left, Location right) {
      this.left = left;
      this.right = right;
   }

   @Override
   public String toString() {
      return String.format("%s - %s", left.getUnit(), left, right);
   }

}

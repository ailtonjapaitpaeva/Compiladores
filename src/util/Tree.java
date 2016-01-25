package util;

public interface Tree<T> {
   
   final class Empty<T> implements Tree<T> {
      
      private Empty() {
      }
      
      private static final Empty<?> INSTANCE = new Empty<>();
      
      @SuppressWarnings("unchecked")
      public static <T> Empty<T> instance() {
         return (Empty<T>) INSTANCE;
      }
      
      public boolean isEmpty() {
         return true;
      }
      
   }

}

package Types;

import util.ToTree;
import util.Tree;
import util.Tree_;

public abstract class Type implements ToTree<String> {

   public Type actual() {
      return this;
   }

   public boolean coerceTo(Type t) {
      return t.actual() == this;
   }

   	@Override
   	public Tree_<String> toTree() {
	// TODO Auto-generated method stub
   		//Tree<String> arvore =  new Tree<String>(toString());
   		
   		
   		return null;
   	}

 /*
   public Tree<String> toTree() {
      return new Tree<>(toString());
   }*/
}

package panda.Absyn;


import javaslang.collection.Tree;
import javaslang.collection.Tree.Node;
import panda.ErrorMsg.Loc;
import panda.Types.BOOL;
import panda.Types.Type;

public class IfExp extends Exp {
	
	public final Exp test;
	public final Exp alt1;
	public final Exp alt2;
	
	
	
	@Override
	public Node<String> toTree() {
	
		
		// TODO Auto-generated method stub
		return Tree.of(tp("ifExp"), test.toTree(), alt1.toTree(), alt2.toTree() );
	}


	public IfExp(Loc loc, Exp test, Exp alt1, Exp alt2) {
		super(loc);
		this.test = test;
		this.alt1 = alt1;
		this.alt2 = alt2;
	}


	@Override
	public String toString() {
		return "IfExp [test=" + test + ", alt1=" + alt1 + ", alt2=" + alt2 + "]";
	}
	
	protected Type  typeCheck() {
		
		Type t_test = test.semant();
		Type t_alt1 = alt1.semant();
		Type t_alt2 = alt2.semant();
		
		if(!t_test.coerceTo(BOOL.T))
		{
			
			
			
		}else{
			
			
		}
		
		return null;
	}
}

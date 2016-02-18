package panda.Absyn;

import javaslang.collection.Tree;
import javaslang.collection.Tree.Node;
import panda.ErrorMsg.Loc;
import panda.Types.Type;

public class VarExp extends Exp{

	
	public VarExp(Loc loc, Var var) {
		super(loc);
		this.var = var;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "VarExp [var=" + var + "]";
	}

	public final Var var;

	@Override
	public Node<String> toTree() {
		
		
		return Tree.of(tp("VarExp"), var.toTree());
		
	}
	
	protected Type typeCheck(){
		
		return var.semant();
	}
	
	
}

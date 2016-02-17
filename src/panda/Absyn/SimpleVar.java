package panda.Absyn;

import javaslang.collection.Tree;
import javaslang.collection.Tree.Node;
import panda.Env.Entry;
import panda.Env.VarEntry;
import panda.ErrorMsg.Loc;
import panda.Symbol.Symbol;
import panda.Types.INT;
import panda.Types.Type;
public class SimpleVar extends Var{
	
	
	public final Symbol varName;

	public SimpleVar(Loc loc, Symbol varName) {
		super(loc);
		this.varName = varName;
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public String toString() {
		return "SimpleVar [varName=" + varName + "]";
	}



	@Override
	public Node<String> toTree() {
		// TODO Auto-generated method stub
		return Tree.of(tp("SimpleVar : " + varName));
	}
	
	protected Type typeCheck() {
		
		Entry entry = ENV.venv.get(varName);
		Type t	;
		
		if(entry == null)
		{
			error("unknow variable %s", varName);
			return INT.T;
		}
		else if(entry instanceof VarEntry)
				{
					return ((VarEntry)entry).ty;
				}
				else{
					error("funcion %s is used as a variable", varName);
					return INT.T;
				}
		
	}

}

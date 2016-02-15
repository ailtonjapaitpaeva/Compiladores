package panda.Absyn;


import java.awt.Window.Type;

import javaslang.collection.Tree;
import javaslang.collection.Tree.Node;
import panda.ErrorMsg.Loc;
import panda.Symbol.Symbol;
import panda.Types.Types;
import panda.Util.*;

public class VarDec extends Dec {

	public Symbol varName;
	public Symbol typeName;
	public Exp init;
	
	public VarDec(Loc loc) {
		super(loc);
		// TODO Auto-generated constructor stub
	}
	
	public VarDec(Loc loc, Symbol varName, Symbol typeName, Exp init) {
		super(loc);
		this.init = init;
		this.typeName = typeName;
		this.varName = varName;
		// TODO Auto-generated constructor stub
	}
	
	public VarDec(Loc loc, Symbol varName, Exp init) {
		super(loc);
		this.init = init;
		this.varName = varName;
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "VarDec [varName=" + varName + ", typeName=" + typeName
				+ ", init=" + init + "]";
	}

	@Override
	public Node<String> toTree() {
		return this.init.toTree();
	}

	@Override
	public void semant() {
		
		panda.Types.Type t = ENV.tenv.get(this.typeName);
		if(this.typeName == null)
		{
			//this.typeName = env.tenv.get(this.init.);
		}
		
		
		if(t == null)
		{
			System.out.println("function type error");
			return;
		}
		if(!t.coerceTo(this.init.semant()))
		{
			System.out.println("Exp error");
			return;
		}
		ENV.tenv.put(this.varName, t);
		
	}

}

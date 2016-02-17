package panda.Absyn;



import javaslang.collection.Tree;
import javaslang.collection.Tree.Node;
import panda.Env.Env;
import panda.Env.VarEntry;
import panda.ErrorMsg.Loc;
import panda.Symbol.Symbol;
import panda.Types.INT;
import panda.Types.Type;
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
		if(typeName != null)
		{
			return 	Tree.of("VarDec"  ,
					Tree.of(varName.toString()),
					Tree.of(typeName.toString()) , 
					init.toTree());
		}
		return 	Tree.of("VarDec"  ,
				Tree.of(varName.toString()),
				init.toTree());
	}

	@Override
	public void semant() {

		Type t_init = init.semant();
		Type t_var;


		//panda.Types.Type t = ENV.tenv.get(this.typeName);
		if(this.typeName == null)
		{
			t_var = t_init;
		}
		else
		{
			t_var = ENV.tenv.get(typeName);

			if(t_var == null)
			{
				error("unknow type %s", typeName);
				t_var = INT.T;
			}
			if(!t_init.coerceTo(t_var) )
			{
				init.error("type mismatch : expected %s but is %s ", t_var, t_init);
			}
		}
		
		ENV.venv.put(varName, new VarEntry(t_var) );

	}

}

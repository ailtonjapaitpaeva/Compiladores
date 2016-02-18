package panda.Absyn;

import javaslang.collection.List;
import javaslang.collection.Tree;
import javaslang.collection.Tree.Node;
import panda.ErrorMsg.Loc;
import panda.Types.Type;

public class LetExp extends Exp {
	
	
	public final  List<Dec> decs;
	public final Exp body;
	
	
	public LetExp(Loc loc, List<Dec> decs, Exp body) {
		super(loc);
		
		this.decs = decs;
		this.body = body;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return String.format("LetExp [decs=%s, body =%s]", decs, body);
	}
	@Override
	public Node<String> toTree() {
			
		
		return Tree.of(tp("LetExp"), 
				Tree.of("Decs",decs.map(Dec::toTree )), 
				body.toTree()
				);
		
	}
	
	protected Type typeCheck() {
		
		ENV.tenv.beginScope();
		ENV.venv.beginScope();
		
		decs.forEach(Dec::semant);
		Type t_body = body.semant();
		
		ENV.tenv.endScope();
		ENV.venv.endScope();
		
		return t_body;
		
		
		
	}
	
	
	
	

}

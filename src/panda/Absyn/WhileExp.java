package panda.Absyn;

import javaslang.collection.Tree;
import javaslang.collection.Tree.Node;
import panda.ErrorMsg.Loc;
import panda.Types.BOOL;
import panda.Types.Type;
import panda.Types.UNIT;

public class WhileExp extends Exp {


	public final Exp test;
	public final Exp result;

	public WhileExp(Loc loc, Exp test, Exp result) {
		super(loc);
		this.test = test;
		this.result = result;

	}

	@Override
	public String toString() {
		return String.format("WhileExp [test=%s, result=%s, alt2=%s]", test, result);
	}

	@Override
	public Node<String> toTree() {

		return Tree.of(tp("WhileExp"), test.toTree(), result.toTree() );
	}

	@Override
	protected Type typeCheck() {
		Type t_test = test.semant();
		if (!t_test.coerceTo(BOOL.T))
			test.error("type mismatch in test of conditional should be %s, found %s",
					BOOL.T, t_test);
		Type t_alt1 = result.semant();

		if (!t_alt1.coerceTo(UNIT.T))
			result.error("type mismatch in conditional should be %s, found %s",
					UNIT.T, t_alt1);
		return UNIT.T; 

	}

}

package maxcsp.tests;

import java.util.Vector;

import maxcsp.Constraint;
import maxcsp.Problem;
import maxcsp.Util;
import maxcsp.Variable;

public class TestsUtil {
	
	private static final int VARS_COUNT = 3;
	private static final int DOMAIN_SIZE = 2;
	private Variable[] _vars;
	public Problem _problem;
	public Variable v0;
	public Variable v1;
	public Variable v2;
	
	public TestsUtil(){
		_vars = new Variable[VARS_COUNT];
		for(int i=0;i<VARS_COUNT;i++){
			_vars[i]=new Variable(i);
		}
		Integer values0_1[][] = {{0,0},{1,1}};
		Integer values1_2[][] = {{1,0},{0,1},{1,1}};
		Integer values0_2[][] = {{0,1},{1,0}};
		
		Vector<Constraint> constraints = new java.util.Vector<Constraint>();
		constraints.add(new Constraint(_vars[1],_vars[2],Util.quickPV(values1_2)));
		constraints.add(new Constraint(_vars[0],_vars[1],Util.quickPV(values0_1)));
		constraints.add(new Constraint(_vars[0],_vars[2],Util.quickPV(values0_2)));
		
		_problem=new Problem(VARS_COUNT,DOMAIN_SIZE,constraints);
		v0=new Variable(0);
		v1=new Variable(1);
		v2=new Variable(2);
	}
}

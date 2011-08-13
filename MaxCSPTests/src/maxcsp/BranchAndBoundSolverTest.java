package maxcsp;

import static org.junit.Assert.*;

import java.util.Vector;


import org.junit.Before;
import org.junit.Test;

public class BranchAndBoundSolverTest {
	private static final int VARS_COUNT = 3;
	private static final int DOMAIN_SIZE = 2;
	Problem _problem;
	BranchAndBoundSolver _solver;
	private TestsUtil _u;

	@Before
	public void setUp() throws Exception {
		_u= new TestsUtil();
		_problem=_u._problem;
		_solver = new BranchAndBoundSolver(_problem);
	}

	@Test
	public final void testSolve() {
		_solver.solve();
		assertEquals(_solver.solutionCost(),0);
	}
	@Test
	public final void testSolve2() {
		final int VARS=10;
		java.util.Collection<Constraint> cs = new Vector<Constraint>();
		for (int i=0;i<VARS;i++){
			for (int j=i+1;j<VARS;j++){
				cs.add(new Constraint(i,j,new Vector<IntPair>()));
			}
		}
		_solver = new BranchAndBoundSolver(new Problem(VARS, DOMAIN_SIZE,cs));
		_solver.solve();
		assertEquals(_solver.solutionCost()*2,VARS*(VARS-1));
	}

	@Test
	public final void testcalc() {
		Assignment ass = new Assignment(VARS_COUNT);
		ass.assign(_u.v0,0);
		ass.assign(_u.v1,1);
		ass.assign(_u.v2,1);
		assertEquals(0,_solver.calcSingleVariableDistance(_u.v2,1,ass));
		
		ass.assign(_u.v1,0);
		assertEquals(0,_solver.calcSingleVariableDistance(_u.v2,1,ass));
		
		ass.assign(_u.v0,1);
		assertEquals(1,_solver.calcSingleVariableDistance(_u.v2,1,ass));
		
		ass.assign(_u.v0,0);
		ass.assign(_u.v1,0);
		ass.assign(_u.v2,0);
		assertEquals(1,_solver.calcSingleVariableDistance(_u.v1,0,ass));
		
		ass.assign(_u.v0,1);
		ass.assign(_u.v1,1);
		ass.assign(_u.v2,1);
		assertEquals(1,_solver.calcSingleVariableDistance(_u.v0,1,ass));
		
	}
	private static final int DOMAIN_MIN = 4;
	private static final int DOMAIN_MAX = 8;
	private static final int VARS_MIN=4;
	private static final int VARS_MAX=8;	
	private static final int RANDOM_TESTS_COUNT=50;
	private static final int NQUEENS_TESTS_COUNT=8;
	@Test
	public void testNQueensDistanceSanity(){
		for(int n=4;n<=NQUEENS_TESTS_COUNT;n++){
			NQueensProblem p = new NQueensProblem(n);
			_solver = new BranchAndBoundSolver(p);
			_solver.solve();
			assertEquals(_solver.solutionCost(),0);
		}
	}
	
	@Test
	public void testRandomDistanceSanity(){
		for(int i=0;i<RANDOM_TESTS_COUNT;i++){
			int vars = Util.randBetween(VARS_MIN, VARS_MAX);
			int domainSize = Util.randBetween(DOMAIN_MIN, DOMAIN_MAX);
			double p1 = Math.random();
			double p2 = Math.random();
			Problem p = new Problem(vars,domainSize,p1,p2);
//			Logger.inst().debug("Testing distance for " + p + "... ",false);
			BranchAndBoundSolver solver = new BranchAndBoundSolver(p);
			Assignment sol = solver.solve();
			int mydistance=calcAssignmentDistance(p,sol);
			assertEquals(mydistance,solver.solutionCost());
			
		}
	}

	private int calcAssignmentDistance(Problem p, Assignment sol) {
		int ans=0;
		for(int varLeft = 0;varLeft<p._varCount;varLeft++)
			for(int varRight = varLeft+1;varRight<p._varCount;varRight++){
				if(!p.check(varLeft,sol.value(varLeft),
						varRight,sol.value(varRight)))
					ans++;
		}
		return ans;
	}

}

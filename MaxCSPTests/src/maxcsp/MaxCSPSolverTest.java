package maxcsp;

import static org.junit.Assert.*;

import java.util.Vector;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MaxCSPSolverTest {
	private static final int VARS_COUNT = 3;
	private static final int DOMAIN_SIZE = 2;
	Problem _problem;
	MaxCSPSolver _solver;
	private TestsUtil _u;

	@Before
	public void setUp() throws Exception {
		_u= new TestsUtil();
		_problem=_u._problem;
		_solver = new MaxCSPSolver(_problem);
	}

	@Test
	public final void testSolve() {
		Assignment ans = _solver.solve();
		assertEquals(ans._distance,0);
		assertEquals(_solver.solutionDistance(),0);
	}
	@Test
	public final void testSolve2() {
		final int VARS=10;
		java.util.Collection<Constraint> cs = new Vector<Constraint>();
		for (int i=0;i<VARS;i++){
			for (int j=i+1;j<VARS;j++){
				cs.add(new Constraint(new Variable(i),new Variable(j),new Vector<OrderedPair<Integer>>()));
			}
		}
		_solver = new MaxCSPSolver(new Problem(VARS, DOMAIN_SIZE,cs));
		_solver.solve();
		assertEquals(_solver.solutionDistance()*2,VARS*(VARS-1));
	}

	@Test
	public final void testcalc() {
		Assignment ass = new Assignment(VARS_COUNT);
		ass.assign(_u.v0.assign(0));
		ass.assign(_u.v1.assign(1));
		ass.assign(_u.v2.assign(1));
		assertEquals(0,_solver.calcSingleVariableDistance(_u.v2,ass));
		
		ass.assign(_u.v1.assign(0));
		assertEquals(0,_solver.calcSingleVariableDistance(_u.v2,ass));
		
		ass.assign(_u.v0,1);
		assertEquals(1,_solver.calcSingleVariableDistance(_u.v2,ass));
		
		ass.assign(_u.v0.assign(0));
		ass.assign(_u.v1.assign(0));
		ass.assign(_u.v2.assign(0));
		assertEquals(1,_solver.calcSingleVariableDistance(_u.v1,ass));
		
		ass.assign(_u.v0.assign(1));
		ass.assign(_u.v1.assign(1));
		ass.assign(_u.v2.assign(1));
		assertEquals(1,_solver.calcSingleVariableDistance(_u.v0,ass));
		
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
			_solver = new MaxCSPSolver(p);
			Assignment sol = _solver.solve();
			assertEquals(_solver.solutionDistance(),0);
			assertEquals(_solver.solutionDistance(),sol._distance);
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
			Logger.inst().debug("Testing distance for " + p + "... ",false);
			Assignment sol = new MaxCSPSolver(p).solve();
			int mydistance=calcAssignmentDistance(p,sol);
			
			boolean equalDistance = mydistance==sol._distance;
			assertTrue(equalDistance);
			if(equalDistance)
				Logger.inst().debug("Equal distance. ");
			
		}
	}

	private int calcAssignmentDistance(Problem p, Assignment sol) {
		int ans=0;
		java.util.Iterator<OrderedPair<Variable>> itr = Util.differentPairsIterator(p._vars);
		while(itr.hasNext()){
			OrderedPair<Variable> vp = itr.next();
			if(!p.check(vp._left.assign(sol.value(vp._left)),
					vp._right.assign(sol.value(vp._right))))
				ans++;
		}
		return ans;
	}

}

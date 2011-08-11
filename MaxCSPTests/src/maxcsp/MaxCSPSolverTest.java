package maxcsp;

import static org.junit.Assert.*;

import java.util.Vector;

import maxcsp.tests.TestsUtil;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MaxCSPSolverTest {
	private static final int VARS_COUNT = 3;
	private static final int DOMAIN_SIZE = 2;
	Problem _problem;
	MaxCSPSolver _solver;
	private TestsUtil _u;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		_u= new TestsUtil();
		_problem=_u._problem;
		_solver = new MaxCSPSolver(_problem);
	}

	

	@Test
	public final void testSolve() {
		Solution ans = _solver.solve();
		assertEquals(ans._distance,0);
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
		Solution ans = _solver.solve();
		assertEquals(ans._distance*2,VARS*(VARS-1));
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

}

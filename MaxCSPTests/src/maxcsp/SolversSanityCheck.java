package maxcsp;
import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;


public class SolversSanityCheck {
	private static final int RANDOM_TESTS_COUNT = 30;
	private static final int DOMAIN_MIN = 5;
	private static final int DOMAIN_MAX = 7;
	private static final int VARS_MIN=5;
	private static final int VARS_MAX=7;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void randomTests() {
		for(int test=0;test<RANDOM_TESTS_COUNT;test++){
			Problem p = TestsUtil.randomCSP(VARS_MIN, VARS_MAX, DOMAIN_MIN, DOMAIN_MAX);
			Logger.inst().debug("Distansanity test: problem: " + p);
			Vector<MaxCSPSolver> solvers = Util.makeSolvers(p);
			Vector<Integer> distances = new Vector<Integer>();
			for(MaxCSPSolver s : solvers){
				Logger.inst().debug("Distansanity test: algorithm: " + s.getClass().getName(),false);
				s.solve();
				int distance = s.solutionDistance();
				Logger.inst().debug("\tdistance: " + distance);
				distances.add(distance);
			}
			java.util.Iterator<OrderedPair<Integer>> itr = Util.differentPairsIterator(distances);
			while(itr.hasNext()){
				OrderedPair<Integer> pair = itr.next();
				assertEquals(pair._left,pair._right);
			}
		}
	}

}

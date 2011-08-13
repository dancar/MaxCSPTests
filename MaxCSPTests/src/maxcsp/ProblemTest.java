/**
 * 
 */
package maxcsp;

import static org.junit.Assert.*;

import java.util.Vector;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author kits
 *
 */
public class ProblemTest {
	private static final int RANDOM_TESTS_COUNT = 50;
	private static final int DOMAIN_MIN = 5;
	private static final int DOMAIN_MAX = 20;
	private static final int VARS_MIN=10;
	private static final int VARS_MAX=20;
	private static final double REASONABLE_DISTANCE=0.1;
	private Problem p;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		 int var1 = 1;
		 int var2 = 2;
		 int var3 = 3;
		
		Vector<Constraint> constraints = new Vector<Constraint>();
		
		Vector<IntPair> possibleValues1_2 = new Vector<IntPair>();
		possibleValues1_2.add(new IntPair(0,1));
		possibleValues1_2.add(new IntPair(0,2));
		possibleValues1_2.add(new IntPair(1,0));
		Constraint c1=new Constraint(var1,var2,possibleValues1_2);
		constraints.add(c1);
		
		Vector<IntPair> possibleValues1_3 = new Vector<IntPair>();
		possibleValues1_3.add(new IntPair(3,2));
		possibleValues1_3.add(new IntPair(4,1));
		Constraint c2=new Constraint(var1, var3,possibleValues1_3);
		constraints.add(c2);
		
		Vector<IntPair> possibleValues2_3 = new Vector<IntPair>();
		possibleValues2_3.add(new IntPair(0,0));
		possibleValues2_3.add(new IntPair(1,1));
		possibleValues2_3.add(new IntPair(2,2));
		Constraint c3=new Constraint(var2, var3,possibleValues2_3);
		constraints.add(c3);
		
		p = new Problem(4,10,constraints);
		
	}
	
	@Test
	public void test1() throws Exception{
		assertTrue(p.check(1,0, 2,1));
		assertTrue(p.check(1,0, 2,2));
		assertTrue(p.check(1,1, 2,0));
		
		assertTrue(p.check(1,3, 3,2));
		assertTrue(p.check(1,4, 3,1));
		
		assertTrue(p.check(2,0, 3,0));
		assertTrue(p.check(2,1, 3,1));
		assertTrue(p.check(2,2, 3,2));
	}
	@Test
	public void test1f() throws Exception{
		assertFalse(p.check(1,11, 2,0));
		assertFalse(p.check(1,2, 2,0));
		assertFalse(p.check(1,7, 2,0));
		
		assertFalse(p.check(1,3, 3,3));
		assertFalse(p.check(1,4, 3,19));
		
		assertFalse(p.check(2,1, 3,0));
		assertFalse(p.check(2,0, 3,1));
		assertFalse(p.check(2,3, 3,2));
	}
	
	@Test
	public void test2() throws Exception{
		assertTrue(p.check(2,0, 1,1));
		assertTrue(p.check(2,2, 1,0));
		
		assertTrue(p.check(3,2, 1,3));
		assertTrue(p.check(3,1, 1,4));
		
		assertTrue(p.check(3,0, 2,0));
		assertTrue(p.check(3,1, 2,1));
		assertTrue(p.check(3,2, 2,2));
	}
	
	public void test2f() throws Exception{
		
		assertFalse(p.check(3,3, 1,2));
		assertFalse(p.check(3,4, 1,1));
		
	}
	@Test
	public void test3f() throws Exception{
		assertTrue(p.check(0,19, 1,2));
	
	}
	
	@Test
	public void testU() throws Exception{
		TestsUtil u = new TestsUtil();
		assertTrue(u._problem.check(0,0, 1,0));
		assertTrue(u._problem.check(0,1, 1,1));
		assertFalse(u._problem.check(0,0, 1,1));
		assertFalse(u._problem.check(0,1, 1,0));
		
		assertTrue(u._problem.check(0,0, 2,1));
		assertTrue(u._problem.check(0,1, 2,0));
		assertFalse(u._problem.check(0,0, 2,0));
		assertFalse(u._problem.check(0,1, 2,1));
		
		assertTrue(u._problem.check(2,0, 1,1));
		assertTrue(u._problem.check(2,1, 1,0));
		assertTrue(u._problem.check(2,1, 1,1));
		assertFalse(u._problem.check(2,0, 1,0));
	}
	
	public double[] testRandom() throws Exception{
		double p1 = Math.random();
		double p2 = Math.random();
		int vars = Util.randBetween(VARS_MIN, VARS_MAX);
		int domain = Util.randBetween(DOMAIN_MIN, DOMAIN_MAX);
		Problem p = new Problem(vars,domain,p1,p2);
		TestsUtil.ProblemInfo pi = TestsUtil.calcProblemEffectivePs(p);
		double distance_p1 = Math.abs(p1-pi.p1);
		double distance_p2 = (pi.constraintsCount==0 ? 0 : Math.abs(p2-pi.p2));
		double[] ans = new double[2];
		ans[0]=distance_p1;
		ans[1]=distance_p2;
		return ans;
	}
	
	@Test
	public void testRandomAlot()throws Exception{
		double total_distance_p1=0;
		double total_distance_p2=0;
		for (int i=0;i<RANDOM_TESTS_COUNT;i++){
			double distances[] = testRandom();
			total_distance_p1+=distances[0];
			total_distance_p2+=distances[1];
			Logger.inst().debug("Random Test " + i + " ok.");
		}
		double average_distance_p1 = total_distance_p1 / RANDOM_TESTS_COUNT;
		double average_distance_p2 = total_distance_p2 / RANDOM_TESTS_COUNT;
		assertTrue(average_distance_p1<REASONABLE_DISTANCE);
		assertTrue(average_distance_p2<REASONABLE_DISTANCE);
		Logger.inst().debug("Created " + RANDOM_TESTS_COUNT + " problems");
		Logger.inst().debug("Average distance p1: " + average_distance_p1);
		Logger.inst().debug("Average distance p2: " + average_distance_p2);
	}
	
	private static final String TEST_FILE = "testcp.txt";
	@Test
	public void testFiling()throws Exception{
		for(int i=0;i<RANDOM_TESTS_COUNT;i++){
			int varsCount,domainSize;
			double p1,p2;
			varsCount = Util.randBetween(VARS_MIN, VARS_MAX);
			domainSize = Util.randBetween(DOMAIN_MIN, DOMAIN_MAX);
			p1=Math.random();
			p2=Math.random();
			Problem p = new Problem(varsCount, domainSize, p1, p2);
			p.toFile(TEST_FILE);
			Problem newp = Problem.fromFile(TEST_FILE);
			assertEquals(varsCount, newp._varCount);
			assertEquals(domainSize, newp._domainSize);
			assertTrue(newp._P1==p1);
			assertTrue(newp._P2==p2);
			TestsUtil.ProblemInfo pInfo = TestsUtil.calcProblemEffectivePs(p);
			TestsUtil.ProblemInfo newpInfo = TestsUtil.calcProblemEffectivePs(newp);
			boolean cond = pInfo.equals(newpInfo);
			if(!cond){
				Logger.inst().debug("SERIALIZATION ERROR");
				Logger.inst().debug("Original:");
				Logger.inst().debug(p.print());
				Logger.inst().debug("******************");
				Logger.inst().debug("Remake:");
				Logger.inst().debug(newp.print());
			}
			assertTrue(cond);
			Logger.inst().debug("Serialization test succesful for problem " + p);
		}
	}


}

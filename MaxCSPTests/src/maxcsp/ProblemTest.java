/**
 * 
 */
package maxcsp;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Collection;
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
		Variable var1 = new Variable(1);
		Variable var2 = new Variable(2);
		Variable var3 = new Variable(3);
		
		Collection<Constraint> constraints = new Vector<Constraint>();
		
		Collection<OrderedPair<Integer>> possibleValues1_2 = new Vector<OrderedPair<Integer>>();
		possibleValues1_2.add(new OrderedPair<Integer>(0,1));
		possibleValues1_2.add(new OrderedPair<Integer>(0,2));
		possibleValues1_2.add(new OrderedPair<Integer>(1,0));
		Constraint c1=new Constraint(var1,var2,possibleValues1_2);
		constraints.add(c1);
		
		Collection<OrderedPair<Integer>> possibleValues1_3 = new Vector<OrderedPair<Integer>>();
		possibleValues1_3.add(new OrderedPair<Integer>(3,2));
		possibleValues1_3.add(new OrderedPair<Integer>(4,1));
		Constraint c2=new Constraint(var1, var3,possibleValues1_3);
		constraints.add(c2);
		
		Collection<OrderedPair<Integer>> possibleValues2_3 = new Vector<OrderedPair<Integer>>();
		possibleValues2_3.add(new OrderedPair<Integer>(0,0));
		possibleValues2_3.add(new OrderedPair<Integer>(1,1));
		possibleValues2_3.add(new OrderedPair<Integer>(2,2));
		Constraint c3=new Constraint(var2, var3,possibleValues2_3);
		constraints.add(c3);
		
		p = new Problem(4,10,constraints);
		
	}
	
	@Test
	public void test1() throws Exception{
		assertTrue(p.check(new Variable(1).assign(0), new Variable(2).assign(1)));
		assertTrue(p.check(new Variable(1).assign(0), new Variable(2).assign(2)));
		assertTrue(p.check(new Variable(1).assign(1), new Variable(2).assign(0)));
		
		assertTrue(p.check(new Variable(1).assign(3), new Variable(3).assign(2)));
		assertTrue(p.check(new Variable(1).assign(4), new Variable(3).assign(1)));
		
		assertTrue(p.check(new Variable(2).assign(0), new Variable(3).assign(0)));
		assertTrue(p.check(new Variable(2).assign(1), new Variable(3).assign(1)));
		assertTrue(p.check(new Variable(2).assign(2), new Variable(3).assign(2)));
	}
	@Test
	public void test1f() throws Exception{
		assertFalse(p.check(new Variable(1).assign(11), new Variable(2).assign(0)));
		assertFalse(p.check(new Variable(1).assign(2), new Variable(2).assign(0)));
		assertFalse(p.check(new Variable(1).assign(7), new Variable(2).assign(0)));
		
		assertFalse(p.check(new Variable(1).assign(3), new Variable(3).assign(3)));
		assertFalse(p.check(new Variable(1).assign(4), new Variable(3).assign(19)));
		
		assertFalse(p.check(new Variable(2).assign(1), new Variable(3).assign(0)));
		assertFalse(p.check(new Variable(2).assign(0), new Variable(3).assign(1)));
		assertFalse(p.check(new Variable(2).assign(3), new Variable(3).assign(2)));
	}
	
	@Test
	public void test2() throws Exception{
		assertTrue(p.check(new Variable(2).assign(0), new Variable(1).assign(1)));
		assertTrue(p.check(new Variable(2).assign(2), new Variable(1).assign(0)));
		
		assertTrue(p.check(new Variable(3).assign(2), new Variable(1).assign(3)));
		assertTrue(p.check(new Variable(3).assign(1), new Variable(1).assign(4)));
		
		assertTrue(p.check(new Variable(3).assign(0), new Variable(2).assign(0)));
		assertTrue(p.check(new Variable(3).assign(1), new Variable(2).assign(1)));
		assertTrue(p.check(new Variable(3).assign(2), new Variable(2).assign(2)));
	}
	
	public void test2f() throws Exception{
		
		assertFalse(p.check(new Variable(3).assign(3), new Variable(1).assign(2)));
		assertFalse(p.check(new Variable(3).assign(4), new Variable(1).assign(1)));
		
	}
	@Test
	public void test3f() throws Exception{
		assertTrue(p.check(new Variable(0).assign(19), new Variable(1).assign(2)));
	
	}
	
	@Test
	public void testU() throws Exception{
		TestsUtil u = new TestsUtil();
		assertTrue(u._problem.check(u.v0.assign(0), u.v1.assign(0)));
		assertTrue(u._problem.check(u.v0.assign(1), u.v1.assign(1)));
		assertFalse(u._problem.check(u.v0.assign(0), u.v1.assign(1)));
		assertFalse(u._problem.check(u.v0.assign(1), u.v1.assign(0)));
		
		assertTrue(u._problem.check(u.v0.assign(0), u.v2.assign(1)));
		assertTrue(u._problem.check(u.v0.assign(1), u.v2.assign(0)));
		assertFalse(u._problem.check(u.v0.assign(0), u.v2.assign(0)));
		assertFalse(u._problem.check(u.v0.assign(1), u.v2.assign(1)));
		
		assertTrue(u._problem.check(u.v2.assign(0), u.v1.assign(1)));
		assertTrue(u._problem.check(u.v2.assign(1), u.v1.assign(0)));
		assertTrue(u._problem.check(u.v2.assign(1), u.v1.assign(1)));
		assertFalse(u._problem.check(u.v2.assign(0), u.v1.assign(0)));
	}
	
	public OrderedPair<Double> testRandom() throws Exception{
		double p1 = Math.random();
		double p2 = Math.random();
		int vars = Util.randBetween(VARS_MIN, VARS_MAX);
		int domain = Util.randBetween(DOMAIN_MIN, DOMAIN_MAX);
		Problem p = new Problem(vars,domain,p1,p2);
		TestsUtil.ProblemInfo pi = TestsUtil.calcProblemEffectivePs(p);
		double distance_p1 = Math.abs(p1-pi.p1);
		double distance_p2 = (pi.constraintsCount==0 ? 0 : Math.abs(p2-pi.p2));
		return new OrderedPair<Double>(distance_p1,distance_p2);
		
	}
	
	@Test
	public void testRandomAlot()throws Exception{
		double total_distance_p1=0;
		double total_distance_p2=0;
		for (int i=0;i<RANDOM_TESTS_COUNT;i++){
			OrderedPair<Double> res = testRandom();
			total_distance_p1+=res._left;
			total_distance_p2+=res._right;
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

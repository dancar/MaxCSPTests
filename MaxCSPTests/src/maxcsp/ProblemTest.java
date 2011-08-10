/**
 * 
 */
package maxcsp;

import static org.junit.Assert.*;

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
		
		p = new Problem(4,constraints);
		
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


}

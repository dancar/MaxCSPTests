/**
 * 
 */
package maxcsp.tests;

import static org.junit.Assert.*;
import junit.framework.Assert;

import maxcsp.Variable;

import org.junit.Before;
import org.junit.Test;

/**
 * @author kits
 *
 */
public class VariableTest {
	private static final int ID=7;
	private static final int ID2=19;
	private static final int VALUE=200;
	private Variable v1;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		v1 = new Variable(ID);
		v1.assign(VALUE);
	}
	private void a(boolean cond){
		Assert.assertTrue(cond);
	}
	/**
	 * Test method for {@link maxcsp.Variable#Variable(int)}.
	 */
	@Test
	public void testVariableInt() {
		Assert.assertTrue(new Variable(ID)._id==ID);
	}

	/**
	 * Test method for {@link maxcsp.Variable#Variable(maxcsp.Variable)}.
	 */
	@Test
	public void testVariableVariable() {
		Variable v2 = new Variable(v1);
		assertEquals(v2._id,ID);
		assertEquals(v2.value(),VALUE);
	}

	/**
	 * Test method for {@link maxcsp.Variable#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		Variable v2 = new Variable(ID);
		assertEquals(v1,v2);
		Variable v3 = new Variable(ID2);
		assertFalse(v2.equals(v3));
		assertFalse(v1.equals(v3));
	}
	
	

	/**
	 * Test method for {@link maxcsp.Variable#isAssigned()}.
	 */
	@Test
	public void testIsAssigned() {
		assertTrue(v1.isAssigned());
		v1.unassign();
		assertFalse(v1.isAssigned());
	}

	
	/**
	 * Test method for {@link maxcsp.Variable#assign(int)}.
	 */
	@Test
	public void testAssign() {
		assertEquals(VALUE, v1.value());
	}
	
}

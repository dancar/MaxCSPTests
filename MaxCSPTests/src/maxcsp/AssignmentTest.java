/**
 * 
 */
package maxcsp;

import static org.junit.Assert.*;

import maxcsp.Assignment;
import maxcsp.Variable;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author kits
 *
 */
public class AssignmentTest {
	private static final int DOMAIN_SIZE = 10;
	private static final int ID=8;
	private static final int VALUE=200;
	private static final int ID2=9;
	private static final int VALUE2=300;
	private Assignment _ass;
	private Variable v1;
	private Variable v2;
	

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		_ass=new Assignment(DOMAIN_SIZE);
		v1=new Variable(ID);
		v2=new Variable(ID2);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	

	/**
	 * Test method for {@link maxcsp.Assignment#Assignment(maxcsp.Assignment)}.
	 */
	@Test
	public  void testAssignmentAssignment() {
		_ass.assign(v2, VALUE2);
		Assignment ass2 = new Assignment(_ass);
		assertEquals(VALUE2, ass2.value(v2));
		assertFalse(ass2.isAssigned(v1));
	}

	/**
	 * Test method for {@link maxcsp.Assignment#assign(maxcsp.Variable, int)}.
	 */
	@Test
	public final void testAssign() {
		_ass.assign(v1, VALUE);
		assertEquals(VALUE, _ass.value(v1));
		_ass.assign(v2, VALUE2);
		assertFalse(_ass.value(v2)==VALUE);
		assertEquals(VALUE2, _ass.value(v2));
	}

	/**
	 * Test method for {@link maxcsp.Assignment#unassign(maxcsp.Variable)}.
	 */
	@Test
	public final void testUnassign() {
		assertFalse(_ass.isAssigned(v1));
		_ass.assign(v1, VALUE);
		assertTrue(_ass.isAssigned(v1));
		_ass.unassign(v1);
		assertFalse(_ass.isAssigned(v1));
		
	}

	/**
	 * Test method for {@link maxcsp.Assignment#value(maxcsp.Variable)}.
	 */
	@Test
	public final void testValue() {
		_ass.assign(v1, VALUE);
		assertEquals(VALUE,_ass.value(v1));
	}

	/**
	 * Test method for {@link maxcsp.Assignment#assignedVariablesIterator()}.
	 */
	@Test
	public final void testAssignedVariablesIterator() {
		assertFalse(_ass.assignedVariablesIterator().hasNext());
		_ass.assign(v1, VALUE);
		assertTrue(_ass.assignedVariablesIterator().hasNext());
		assertTrue(_ass.assignedVariablesIterator().next().equals(v1));
		assertEquals(_ass.assignedVariablesIterator().next().value(),VALUE);
		_ass.assign(v2,VALUE2);
		java.util.Iterator<Variable> itr = _ass.assignedVariablesIterator();
		assertTrue(itr.hasNext());
		Variable next = itr.next();
		assertTrue(next.equals(v1));
		assertEquals(next.value(),VALUE);
		assertTrue(itr.hasNext());
		next=itr.next();
		assertFalse(itr.hasNext());
		assertTrue(next.equals(v2));
		assertEquals(next.value(),VALUE2);
	}
	
	@Test
	public final void testPickUnassigned(){
		Assignment ass = new Assignment(2);
		Variable v0 = new Variable(0);
		Variable v1 = new Variable(1);
		Variable v = ass.pickUnassignedVariable();
		assertTrue(v.equals(v1) | v.equals(v0));
		ass.assign(v1.assign(VALUE));
		v=ass.pickUnassignedVariable();
		assertTrue(v.equals(v0));
		ass.unassign(v1);
		ass.assign(v0.assign(VALUE2));
		v=ass.pickUnassignedVariable();
		assertTrue(v.equals(v1));
	}
	

}

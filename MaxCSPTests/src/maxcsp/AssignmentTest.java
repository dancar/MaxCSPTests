/**
 * 
 */
package maxcsp;

import static org.junit.Assert.*;
import maxcsp.Assignment;
import org.junit.Before;
import org.junit.Test;

/**
 * @author kits
 *
 */
public class AssignmentTest {
	private static final int VARS_COUNT = 10;
	private static final int VALUE=200;
	private static final int VALUE2=300;
	private static int v1=8;
	private static int v2=9;
	private Assignment _ass;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		_ass=new Assignment(VARS_COUNT);
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

//	/**
//	 * Test method for {@link maxcsp.Assignment#unassign(maxcsp.Variable)}.
//	 */
//	@Test
//	public final void testUnassign() {
//		assertFalse(_ass.isAssigned(v1));
//		_ass.assign(v1, VALUE);
//		assertTrue(_ass.isAssigned(v1));
//		_ass.unassign(v1);
//		assertFalse(_ass.isAssigned(v1));
//		
//	}

	/**
	 * Test method for {@link maxcsp.Assignment#value(maxcsp.Variable)}.
	 */
	@Test
	public final void testValue() {
		_ass.assign(v1, VALUE);
		assertEquals(VALUE,_ass.value(v1));
	}

}

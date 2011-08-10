package maxcsp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UnorderedPairTest {

	@Test
	public final void testEqualsObject() {
		UnorderedPair<Variable> p1 = new UnorderedPair<Variable>(new Variable(1), new Variable(2));
		UnorderedPair<Variable> p2 = new UnorderedPair<Variable>(new Variable(2), new Variable(1));
		UnorderedPair<Variable> p3 = new UnorderedPair<Variable>(new Variable(2), new Variable(3));
		assertTrue(p1.equals(p2));
		assertTrue(p2.equals(p1));
		assertFalse(p1.equals(p3));
		assertFalse(p3.equals(p1));
		assertFalse(p3.equals(p2));
		assertFalse(p2.equals(p3));
	}

}

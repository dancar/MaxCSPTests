package maxcsp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	AssignmentTest.class, 
	MaxCSPSolverTest.class,
	ProblemTest.class,
	UnorderedPairTest.class, 
	VariableTest.class })
public class AllTests {

}

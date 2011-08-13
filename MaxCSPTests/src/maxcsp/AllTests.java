package maxcsp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	AssignmentTest.class, 
	BranchAndBoundSolverTest.class,
	ProblemTest.class,
	SolversSanityCheck.class,
	})
public class AllTests {

}

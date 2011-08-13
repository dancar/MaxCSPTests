package maxcsp;

import java.util.Vector;
import maxcsp.Constraint;
import maxcsp.Problem;
import maxcsp.Util;

public class TestsUtil {
	
	private static final int VARS_COUNT = 3;
	private static final int DOMAIN_SIZE = 2;
	private int[] _vars;
	public Problem _problem;
	public int v0;
	public int v1;
	public int v2;
	
	public TestsUtil(){
		_vars = new int [VARS_COUNT];
		for(int i=0;i<VARS_COUNT;i++){
			_vars[i]=i;
		}
		int values0_1[][] = {{0,0},{1,1}};
		int values1_2[][] = {{1,0},{0,1},{1,1}};
		int values0_2[][] = {{0,1},{1,0}};
		
		Vector<Constraint> constraints = new java.util.Vector<Constraint>();
		constraints.add(new Constraint(_vars[1],_vars[2],Util.quickPV(values1_2)));
		constraints.add(new Constraint(_vars[0],_vars[1],Util.quickPV(values0_1)));
		constraints.add(new Constraint(_vars[0],_vars[2],Util.quickPV(values0_2)));
		
		_problem=new Problem(VARS_COUNT,DOMAIN_SIZE,constraints);
		v0=0;
		v1=1;
		v2=2;
	}
	public final static class ProblemInfo{
		public final double p1;
		public final double p2;
		public final int constraintsCount;
		public final int forbiddenPairsCount;
		public ProblemInfo(double p1, double p2, int constraintsCount,
				int forbiddenPairs) {
			super();
			this.p1 = p1;
			this.p2 = p2;
			this.constraintsCount = constraintsCount;
			this.forbiddenPairsCount = forbiddenPairs;
		}
		@Override
		public boolean equals(Object other){
			boolean ans = false;
			if (other instanceof ProblemInfo){
				ProblemInfo o = (ProblemInfo) other;
				ans &= this.constraintsCount == o.constraintsCount;
				ans &= this.forbiddenPairsCount == o.forbiddenPairsCount;
				ans =  this.p1==o.p1;
				ans &= this.constraintsCount==0 |this.p2 == o.p2;
			}
			if(!ans)
				Logger.inst().debug("yo");
			return ans;
			
		}
	}
	
	/**
	 * Calculates actual P1 and P2 values for a given problem
	 * @param p problem
	 * @return complete ProblemInfo class for the given problem.
	 */
	public static ProblemInfo calcProblemEffectivePs(Problem p){
		int constrainedPairs=0;
		int forbiddenPairs = 0;
		for(int varLeft = 0;varLeft<p._varCount;varLeft++)
			for(int varRight=varLeft+1;varRight<p._varCount;varRight++){
				boolean constrained=false;
				for(int valueLeft : p._domain)for(int valueRight : p._domain){
					if(!p.check(varLeft,valueLeft,varRight,valueRight)){
						forbiddenPairs++;
						constrained=true;
					}
				}
			if(constrained){
				constrainedPairs++;
			}
		}
		
		double effective_p1 = (double)constrainedPairs / Util.overTwo(p._varCount);
		double effective_p2 = (double)forbiddenPairs / (constrainedPairs * Math.pow(p._domainSize, 2));

		return new ProblemInfo(effective_p1, effective_p2, constrainedPairs, forbiddenPairs);
	}
	
	public static Problem randomCSP(int varsMin, int varsMax, int domainMin, int domainMax){
		return new Problem(
				Util.randBetween(varsMin, varsMax),
				Util.randBetween(domainMin, domainMax),
				Math.random(),
				Math.random()
				);
	}
}

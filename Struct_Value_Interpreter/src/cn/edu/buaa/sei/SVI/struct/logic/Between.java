package cn.edu.buaa.sei.SVI.struct.logic;

public interface Between extends QuantifierOperator{
	/**
	 * UNBOUNDED presents no limited.
	 * */
	public static final int UNBOUNDED = -1;
	/**
	 * Return the lower bound of the quantifier.
	 * */
	public int getLowerBound();
	/**
	 * Return the upper bound of the quantifier.
	 * */
	public int getUpperBound();
	/**
	 * Set the lower bound and upper bound of the quantifier.
	 * @exception Exception lower<0
	 * @exception Exception upper<0 && upper!=UNBOUNDED
	 * @exception Exception upper!=UNBOUNDED && lower>upper
	 * */
	public void setBound(int lower,int upper) throws Exception;
}

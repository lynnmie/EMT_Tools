package cn.edu.buaa.sei.SVI.struct.logic;

public interface AtMost extends QuantifierOperator{
	/**
	 * UNBOUNDED present no limited.
	 * */
	public static final int UNBOUNDED = -1;
	/**
	 * Return the upper bound of the quantifier
	 * */
	public int getUpperBound();
	
	/**
	 * Set the upper bound of the quantifier.
	 * @exception Exception upper<0||upper!=UNBOUNDED
	 * */
	public void setUpperBound(int upper) throws Exception;
}

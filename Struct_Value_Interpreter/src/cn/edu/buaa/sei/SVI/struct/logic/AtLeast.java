package cn.edu.buaa.sei.SVI.struct.logic;

public interface AtLeast extends QuantifierOperator{
	/**
	 * Return the lower bound of the quantifier.
	 * */
	public int getLowerBound();
	/**
	 * Set the lower bound of the quantifier.
	 * @exception Exception lower<0
	 * */
	public void setLowerBound(int lower) throws Exception;
}

package cn.edu.buaa.sei.SVI.struct.numeric;


public interface RealVariable extends NumericVariable{
	/**
	 * Return the value to which the bindable refers.
	 * @exception Exception {Referencer} null referring variable.
	 * */
	public Double read() throws Exception;
	/**
	 * Write the value to the bindable space.
	 * @exception Exception {TypedVariable} class cast failed.
	 * */
	public void assign(Double val) throws Exception;
	/**
	 * Translate the number {int/long/float} to double by using doubleValue()
	 * */
	public void assign(Number val) throws Exception;
}

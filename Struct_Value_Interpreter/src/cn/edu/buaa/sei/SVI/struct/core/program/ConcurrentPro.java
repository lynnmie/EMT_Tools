package cn.edu.buaa.sei.SVI.struct.core.program;

/**
 * ConcurrentPro presents the Concurrent Program Structure.
 * */
public interface ConcurrentPro extends Program{
	/**
	 * Return all the children programs that need to be executed concurrently.
	 * */
	public Program[] getExecutions();
}

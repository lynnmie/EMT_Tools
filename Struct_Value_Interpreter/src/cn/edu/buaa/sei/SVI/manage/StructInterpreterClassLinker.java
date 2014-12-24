package cn.edu.buaa.sei.SVI.manage;

public interface StructInterpreterClassLinker {
	/**
	 * Checking whether the stype has been linked with the itype.
	 * */
	@SuppressWarnings("rawtypes")
	public boolean isLinked(Class stype,Class itype);
	/**
	 * Link two type of S&I together so that the interpreter of itype would be 
	 * used to interpret Structs of stype. Overwriting would failed.
	 * */
	@SuppressWarnings("rawtypes")
	public void link(Class stype,Class itype) throws Exception;
	/**
	 * Linked two types by force {overwrite the original links}
	 * */
	@SuppressWarnings("rawtypes")
	public void Flink(Class stype,Class itype) throws Exception;
	/**
	 * Return the interpreter type which has been linked by the stype in shortest distance.
	 * */
	@SuppressWarnings("rawtypes")
	public Class getTarget(Class stype) throws Exception;
}

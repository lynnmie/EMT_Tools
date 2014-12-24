package cn.edu.buaa.sei.SVI.manage;

/**
 * IStructPrinter is an outputer which output the Struct to store them in the file/xml/database.
 * */
public interface IStructPrinter {
	/**
	 * Set the output target resource, could be file(.s)/xml/database.
	 * @exception Exception out==null
	 * @exception Exception out cannot be written
	 * */
	public void setOutputStream(SVIResource out) throws Exception;
	/**
	 * Write a group of Structs into the resource.
	 * */
	public void write(StructManager struct) throws Exception;
}

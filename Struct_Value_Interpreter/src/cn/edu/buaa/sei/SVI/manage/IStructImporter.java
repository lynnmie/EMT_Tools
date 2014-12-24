package cn.edu.buaa.sei.SVI.manage;

/**
 * IStructImporter is an importer for reading Struct from file/xml/database and reconstruct
 * them in the system.
 * */
public interface IStructImporter {
	/**
	 * Set the input source, which could be file(.s)/xml/database.
	 * @exception Exception in==null
	 * @exception Exception in cannot be read
	 * */
	public void setInput(SVIResource in) throws Exception;
	/**
	 * Return all the Struct that has been stored and coded in the resource.
	 * */
	public StructManager read() throws Exception;
}

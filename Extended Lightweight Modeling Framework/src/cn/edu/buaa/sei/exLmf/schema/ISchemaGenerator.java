package cn.edu.buaa.sei.exLmf.schema;

import java.io.OutputStream;

import cn.edu.buaa.sei.exLmf.metamodel.LPackage;

/**
 *	ISchemaGenerator
 *		- description: receive a LPackage as model and translate it to a schema and output to the pipe.
 * 		- setModel/setOPipe: set the model and output pipe.
 * 		- validate: check whether the output pipe and the model have been configurated well.
 * 		- generateSchema(): translate the model to schema and output to the pipe.
 */
public interface ISchemaGenerator {
	public void setModel(LPackage p);
	public void setOPipe(OutputStream out);
	public boolean validate();
	public void generateSchema() throws Exception;
}

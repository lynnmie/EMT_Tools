package cn.edu.buaa.sei.SVI.struct.core.variable;

/**
 * ReferenceVariable is like a reference --> refer to another variable.<br>
 * 1. <b>Assignment</b>: any value set to the referencer would be set to the original variable. 
 * <i>{like in C: *p=1}</i><br>
 * 2. <b>Read</b>: the value of the original variable would be read.<br>
 * 3. <b>Null Referring</b>: if the referred variable is null, then read()|assign()|getAccessMode() would failed.<br>
 * 4. <b>getName</b>: the referenced variable has a different name with original variable.
 * */
public interface ReferenceVariable extends Variable{
	/**
	 * Set to a new referring Variable.
	 * */
	public void refer(Variable variable);
	public Variable getRefer();
}

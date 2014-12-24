package cn.edu.buaa.sei.SVI.struct.core.program;

/**
 * SequencePro presents the sequence structure of program.
 * */
public interface SequencePro extends Program{
	/**
	 * Return all the direct-children of the sequence programs.
	 * */
	public Program[] getSequences();
}

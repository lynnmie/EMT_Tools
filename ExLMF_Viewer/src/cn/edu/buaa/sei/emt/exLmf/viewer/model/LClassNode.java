package cn.edu.buaa.sei.emt.exLmf.viewer.model;

import javax.swing.JTree;

public class LClassNode extends ModelNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LClassNode(JTree tree, String name) {
		super(tree, name, IconSet.CLASS_ICON);
	}
}

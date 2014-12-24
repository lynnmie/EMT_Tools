package cn.edu.buaa.sei.emt.exLmf.viewer.model;

import javax.swing.JTree;

public class LEnumLiteralNode extends ModelNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LEnumLiteralNode(JTree tree, String name) {
		super(tree, name, IconSet.LITERAL_ICON);
	}
}

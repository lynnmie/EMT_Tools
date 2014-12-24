package cn.edu.buaa.sei.emt.exLmf.viewer.model;

import javax.swing.JTree;

public class LAttributeNode extends ModelNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LAttributeNode(JTree tree, String name) {
		super(tree, name, IconSet.ATTRIBUTE_ICON);
	}
}

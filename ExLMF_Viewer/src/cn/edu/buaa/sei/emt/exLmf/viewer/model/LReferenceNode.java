package cn.edu.buaa.sei.emt.exLmf.viewer.model;

import javax.swing.JTree;

public class LReferenceNode extends ModelNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LReferenceNode(JTree tree, String name) {
		super(tree, name, IconSet.REFERENCE_ICON);
	}
}

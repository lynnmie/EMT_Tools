package cn.edu.buaa.sei.SVI.editor.treeNode.numeric;
import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.treeNode.IconSet;

public class RationalTemplateTreeNode extends NumericTemplateTreeNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RationalTemplateTreeNode(JTree tree, String name) {
		super(tree, name, IconSet.RATIONAL_TEMP_ICON);
	}

}

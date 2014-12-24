package cn.edu.buaa.sei.SVI.editor.treeNode.group;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.treeNode.IconSet;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.TemplateTreeNode;

public class GroupTemplateTreeNode extends TemplateTreeNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GroupTemplateTreeNode(JTree tree, String name) {
		super(tree, name, IconSet.GROUP_TEMP_ICON);
	}
}

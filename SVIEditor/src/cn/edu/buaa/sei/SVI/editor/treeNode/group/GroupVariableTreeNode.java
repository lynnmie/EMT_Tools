package cn.edu.buaa.sei.SVI.editor.treeNode.group;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.treeNode.IconSet;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.VariableTreeNode;

public class GroupVariableTreeNode extends VariableTreeNode{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GroupVariableTreeNode(JTree tree,String name) {
		super(tree,name,IconSet.GROUP_VAR_ICON);
	}

	@Override
	public boolean validate() {
		return true;
	}
	@Override
	public int requiredChildrenCount() {return 0;}
}

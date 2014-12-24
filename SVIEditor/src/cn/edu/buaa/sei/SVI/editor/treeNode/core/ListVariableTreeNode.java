package cn.edu.buaa.sei.SVI.editor.treeNode.core;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.treeNode.IconSet;

public class ListVariableTreeNode extends VariableTreeNode{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ListVariableTreeNode(JTree tree,String name) {
		super(tree,name,IconSet.LIST_VAR_ICON);
	}

	@Override
	public boolean validate() {return true;}
	@Override
	public int requiredChildrenCount() {return 0;}
	

}

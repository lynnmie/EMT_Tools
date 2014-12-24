package cn.edu.buaa.sei.SVI.editor.treeNode.core;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.treeNode.IconSet;

public class FloatVariableTreeNode extends VariableTreeNode{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FloatVariableTreeNode(JTree tree,String name) {
		super(tree,name,IconSet.FLOAT_VAR_ICON);
	}

	@Override
	public boolean validate() {return true;}
	@Override
	public int requiredChildrenCount() {return 0;}
	

}

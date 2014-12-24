package cn.edu.buaa.sei.SVI.editor.treeNode.core;

import javax.swing.ImageIcon;
import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;

public abstract class FlexibleOperatorTreeNode extends OperatorTreeNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FlexibleOperatorTreeNode(JTree tree, String name, ImageIcon icon) {
		super(tree, name, icon);
	}
	
	@Override
	public boolean validate(){
		int n = this.getChildCount();
		if(n>0){
			for(int i=0;i<n;i++){
				SVITreeNode node = (SVITreeNode) this.getChildAt(i);
				if(!node.validate())return false;
			}
			return true;
		}
		return false;
	}
	@Override
	public int requiredChildrenCount() {return 2;}
}

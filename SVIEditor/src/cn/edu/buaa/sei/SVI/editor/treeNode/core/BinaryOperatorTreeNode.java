package cn.edu.buaa.sei.SVI.editor.treeNode.core;

import javax.swing.ImageIcon;
import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;

public abstract class BinaryOperatorTreeNode extends OperatorTreeNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BinaryOperatorTreeNode(JTree tree, String name, ImageIcon icon) {
		super(tree, name, icon);
		this.init();
	}
	public void init(){
		/*JMenu item0 = (JMenu) this.menu.getComponent(0);
		item0.add(new JMenu("left"));
		item0.add(new JMenu("right"));*/
	}
	
	@Override
	public boolean validate() {
		if(this.getChildCount()==2){
			for(int i=0;i<this.getChildCount();i++){
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

package cn.edu.buaa.sei.SVI.editor.treeNode.core;

import javax.swing.ImageIcon;
import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;

public abstract class UnaryOperatorTreeNode extends OperatorTreeNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnaryOperatorTreeNode(JTree tree, String name, ImageIcon icon) {
		super(tree, name, icon);
		this.init();
	}
	protected void init(){
		/*final JMenu item0 = (JMenu) this.menu.getComponent(0);
		
		final SVITreeNode node = this;
		item0.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(node.getChildCount()>=1)
					item0.setEnabled(false);
				else item0.setEnabled(true);
			}});*/
	}

	@Override
	public boolean validate() {
		if(this.getChildCount()==1){
			SVITreeNode node = (SVITreeNode) this.getChildAt(0);
			return node.validate();
		}
		return false;
	}
	@Override
	public int requiredChildrenCount() {return 1;}
	
}

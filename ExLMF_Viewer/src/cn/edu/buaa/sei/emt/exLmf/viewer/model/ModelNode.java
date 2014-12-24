package cn.edu.buaa.sei.emt.exLmf.viewer.model;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public abstract class ModelNode extends DefaultMutableTreeNode{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected ImageIcon icon;
	protected ImageIcon _icon;
	protected JTree tree;
	
	public ModelNode(JTree tree,String name,ImageIcon icon){
		super(name);this.icon = icon;this.tree = tree;
		this.back();
	}
	
	public JTree getTree(){return this.tree;}
	public void setTree(JTree tree){this.tree = tree;}
	public ImageIcon getIcon() {
		return icon;
	}
	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}
	
	public void back(){this._icon = icon;}
	public void recover(){this.icon = this._icon;}
}

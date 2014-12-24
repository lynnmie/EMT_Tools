package cn.edu.buaa.sei.SVI.editor.action.core;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

import cn.edu.buaa.sei.SVI.editor.action.SVIEditorAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;

public abstract class SVIEditorCreateAction extends SVIEditorAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SVIEditorCreateAction(SVITreeNode node) {
		super(node);
	}
	@Override
	public void act() throws Exception {
		if(node==null)throw new Exception("Null node is invalid");
		
		SVITreeNode newOne = create(node.getTree());
		DefaultTreeModel model = (DefaultTreeModel) node.getTree().getModel();
		model.insertNodeInto(newOne, node, node.getChildCount());
		
		System.out.println("Add New Node...");
		//model.reload();
		node.getTree().repaint();
	}
	protected abstract SVITreeNode create(JTree tree);

}

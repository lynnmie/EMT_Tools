package cn.edu.buaa.sei.SVI.editor.action.core;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

import cn.edu.buaa.sei.SVI.editor.action.SVIEditorAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;

public class SVIEditorRemoveAction extends SVIEditorAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SVIEditorRemoveAction(SVITreeNode node) {
		super(node);
		this.setText("remove");
		this.setName("remove");
	}

	@Override
	public void act() throws Exception {
		if(node==null)throw new Exception("Null node is invalid");
		JTree tree = node.getTree();
		final DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		model.removeNodeFromParent(node);
		
		//model.reload();
		node.getTree().repaint();
	}

}

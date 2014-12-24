package cn.edu.buaa.sei.SVI.editor.action.op;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.AtLeastTreeNode;

public class CreateAtLeast extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateAtLeast(SVITreeNode node) {
		super(node);
		this.setName("at least");
		this.setText("AtLeast");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new AtLeastTreeNode(tree,DefaultNodeNames.ATLEAST);
	}
}

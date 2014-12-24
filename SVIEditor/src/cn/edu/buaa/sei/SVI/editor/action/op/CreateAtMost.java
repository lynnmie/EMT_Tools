package cn.edu.buaa.sei.SVI.editor.action.op;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.AtMostTreeNode;

public class CreateAtMost extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateAtMost(SVITreeNode node) {
		super(node);
		this.setName("at most");
		this.setText("AtMost");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new AtMostTreeNode(tree,DefaultNodeNames.ATMOST);
	}
}

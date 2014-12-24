package cn.edu.buaa.sei.SVI.editor.action.op;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.DisjunctionTreeNode;

public class CreateDisjunction extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateDisjunction(SVITreeNode node) {
		super(node);
		this.setName("disjunction");
		this.setText("Disjunction");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new DisjunctionTreeNode(tree,DefaultNodeNames.DISJUNCTION);
	}
}

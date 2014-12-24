package cn.edu.buaa.sei.SVI.editor.action.op;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.ImplicationTreeNode;

public class CreateImplication extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateImplication(SVITreeNode node) {
		super(node);this.setName("implication");
		this.setText("Implication");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new ImplicationTreeNode(tree,DefaultNodeNames.IMPLICATION);
	}
}

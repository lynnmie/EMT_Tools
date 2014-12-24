package cn.edu.buaa.sei.SVI.editor.action.op;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.ConjunctionTreeNode;

public class CreateConjunction extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateConjunction(SVITreeNode node) {
		super(node);
		this.setName("conjunction");
		this.setText("Conjunction");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new ConjunctionTreeNode(tree,DefaultNodeNames.CONJUNCTION);
	}
}

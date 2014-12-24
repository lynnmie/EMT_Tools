package cn.edu.buaa.sei.SVI.editor.action.op;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.ExistentialTreeNode;

public class CreateExistential extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateExistential(SVITreeNode node) {
		super(node);this.setName("exist");
		this.setText("Existential");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new ExistentialTreeNode(tree,DefaultNodeNames.EXISTENTIAL);
	}
}

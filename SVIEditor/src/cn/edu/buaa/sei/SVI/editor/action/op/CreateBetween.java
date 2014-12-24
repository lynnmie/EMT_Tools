package cn.edu.buaa.sei.SVI.editor.action.op;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.BetweenTreeNode;

public class CreateBetween extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateBetween(SVITreeNode node) {
		super(node);
		this.setName("between");
		this.setText("Between");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new BetweenTreeNode(tree,DefaultNodeNames.BETWEEN);
	}
}

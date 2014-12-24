package cn.edu.buaa.sei.SVI.editor.action.op;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.BiggerTreeNode;

public class CreateBigger extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateBigger(SVITreeNode node) {
		super(node);
		this.setName("bigger");
		this.setText("Bigger");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new BiggerTreeNode(tree,DefaultNodeNames.BIGGER);
	}
}

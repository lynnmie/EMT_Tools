package cn.edu.buaa.sei.SVI.editor.action.op;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.EBiggerTreeNode;

public class CreateEBigger extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateEBigger(SVITreeNode node) {
		super(node);this.setName(">=");
		this.setText("EBigger");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new EBiggerTreeNode(tree,DefaultNodeNames.EBIGGER);
	}
}

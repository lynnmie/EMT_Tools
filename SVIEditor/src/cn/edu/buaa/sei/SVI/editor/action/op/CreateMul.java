package cn.edu.buaa.sei.SVI.editor.action.op;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.MulTreeNode;

public class CreateMul extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateMul(SVITreeNode node) {
		super(node);this.setName("multiplication");
		this.setText("Mul");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new MulTreeNode(tree,DefaultNodeNames.MUL);
	}
}

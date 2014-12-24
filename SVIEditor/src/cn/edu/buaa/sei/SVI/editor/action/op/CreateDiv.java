package cn.edu.buaa.sei.SVI.editor.action.op;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.DivTreeNode;

public class CreateDiv extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateDiv(SVITreeNode node) {
		super(node);
		this.setName("div");
		this.setText("Div");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new DivTreeNode(tree,DefaultNodeNames.DIV);
	}
}

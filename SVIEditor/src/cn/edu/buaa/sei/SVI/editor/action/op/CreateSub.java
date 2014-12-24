package cn.edu.buaa.sei.SVI.editor.action.op;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.SubTreeNode;

public class CreateSub extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateSub(SVITreeNode node) {
		super(node);this.setName("sub");
		this.setText("Sub");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new SubTreeNode(tree,DefaultNodeNames.SUB);
	}
}

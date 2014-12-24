package cn.edu.buaa.sei.SVI.editor.action.op;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.AddTreeNode;

public class CreateAdd extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateAdd(SVITreeNode node) {
		super(node);
		this.setName("add");
		this.setText("Add");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new AddTreeNode(tree,DefaultNodeNames.ADD);
	}
}

package cn.edu.buaa.sei.SVI.editor.action.op;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.SmallerTreeNode;

public class CreateSmaller extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateSmaller(SVITreeNode node) {
		super(node);this.setName("<");
		this.setText("Smaller");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new SmallerTreeNode(tree,DefaultNodeNames.SMALLER);
	}
}

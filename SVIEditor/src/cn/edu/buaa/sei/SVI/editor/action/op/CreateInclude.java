package cn.edu.buaa.sei.SVI.editor.action.op;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.IncludeTreeNode;

public class CreateInclude extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateInclude(SVITreeNode node) {
		super(node);this.setName("include");
		this.setText("Include");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new IncludeTreeNode(tree,DefaultNodeNames.INCLUDE);
	}
}

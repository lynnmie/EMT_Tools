package cn.edu.buaa.sei.SVI.editor.action.variable;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.StringVariableTreeNode;

public class CreateStringVariable extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateStringVariable(SVITreeNode node) {
		super(node);this.setName("String");
		this.setText("String");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new StringVariableTreeNode(tree,DefaultNodeNames.STRING_VAR);
	}
}

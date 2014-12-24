package cn.edu.buaa.sei.SVI.editor.action.variable;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.IntVariableTreeNode;

public class CreateIntegerVariable extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateIntegerVariable(SVITreeNode node) {
		super(node);this.setName("int");
		this.setText("Integer");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new IntVariableTreeNode(tree,DefaultNodeNames.INT_VAR);
	}
}

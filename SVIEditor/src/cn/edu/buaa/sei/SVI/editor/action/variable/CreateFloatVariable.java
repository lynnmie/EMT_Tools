package cn.edu.buaa.sei.SVI.editor.action.variable;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.FloatVariableTreeNode;

public class CreateFloatVariable extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateFloatVariable(SVITreeNode node) {
		super(node);this.setName("float");
		this.setText("Float");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new FloatVariableTreeNode(tree,DefaultNodeNames.FLOAT_VAR);
	}
}

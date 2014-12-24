package cn.edu.buaa.sei.SVI.editor.action.variable;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.DoubleVariableTreeNode;

public class CreateDoubleVariable extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateDoubleVariable(SVITreeNode node) {
		super(node);this.setName("double");
		this.setText("Double");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new DoubleVariableTreeNode(tree,DefaultNodeNames.DOUBLE_VAR);
	}
}

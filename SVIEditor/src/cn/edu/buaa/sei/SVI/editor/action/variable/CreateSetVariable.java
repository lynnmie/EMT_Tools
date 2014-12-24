package cn.edu.buaa.sei.SVI.editor.action.variable;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.SetVariableTreeNode;

public class CreateSetVariable extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateSetVariable(SVITreeNode node) {
		super(node);this.setName("Set");
		this.setText("Set");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new SetVariableTreeNode(tree,DefaultNodeNames.SET_VAR);
	}
}

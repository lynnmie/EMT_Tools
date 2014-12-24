package cn.edu.buaa.sei.SVI.editor.action.variable;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.LogicVariableTreeNode;

public class CreateLogicVariable extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateLogicVariable(SVITreeNode node) {
		super(node);this.setName("logic");
		this.setText("LogicVariable");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new LogicVariableTreeNode(tree,DefaultNodeNames.LOGIC_VAR);
	}
}

package cn.edu.buaa.sei.SVI.editor.action.function;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.LogicFunctionTreeNode;

public class CreateLogicFunction extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateLogicFunction(SVITreeNode node) {
		super(node);
		this.setName("logic function");
		this.setText("LogicFunction");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new LogicFunctionTreeNode(tree,DefaultNodeNames.LOGIC_FUNC);
	}
}

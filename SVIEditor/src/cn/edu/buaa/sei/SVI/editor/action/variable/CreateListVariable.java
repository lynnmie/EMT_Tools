package cn.edu.buaa.sei.SVI.editor.action.variable;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.ListVariableTreeNode;

public class CreateListVariable extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateListVariable(SVITreeNode node) {
		super(node);this.setName("List");
		this.setText("List");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new ListVariableTreeNode(tree,DefaultNodeNames.LIST_VAR);
	}
}

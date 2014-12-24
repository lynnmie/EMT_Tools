package cn.edu.buaa.sei.SVI.editor.action.variable;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.FreeVariableTreeNode;

public class CreateFreeVariable extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateFreeVariable(SVITreeNode node) {
		super(node);this.setName("free");
		this.setText("Free");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new FreeVariableTreeNode(tree,DefaultNodeNames.FREE_VAR);
	}
}

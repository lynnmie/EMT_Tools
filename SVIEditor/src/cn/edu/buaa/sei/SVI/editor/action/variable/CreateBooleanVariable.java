package cn.edu.buaa.sei.SVI.editor.action.variable;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.BooleanVariableTreeNode;

public class CreateBooleanVariable extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateBooleanVariable(SVITreeNode node) {
		super(node);this.setName("boolean");
		this.setText("Boolean");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new BooleanVariableTreeNode(tree,DefaultNodeNames.BOOL_VAR);
	}

}

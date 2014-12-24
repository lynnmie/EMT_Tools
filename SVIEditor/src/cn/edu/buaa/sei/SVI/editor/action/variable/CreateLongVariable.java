package cn.edu.buaa.sei.SVI.editor.action.variable;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.LongVariableTreeNode;

public class CreateLongVariable extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateLongVariable(SVITreeNode node) {
		super(node);this.setName("long");
		this.setText("Long");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new LongVariableTreeNode(tree,DefaultNodeNames.LONG_VAR);
	}
}

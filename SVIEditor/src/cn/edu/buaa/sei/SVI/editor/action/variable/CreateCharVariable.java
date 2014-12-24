package cn.edu.buaa.sei.SVI.editor.action.variable;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.CharVariableTreeNode;

public class CreateCharVariable extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateCharVariable(SVITreeNode node) {
		super(node);this.setName("char");
		this.setText("Char");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new CharVariableTreeNode(tree,DefaultNodeNames.CHAR_VAR);
	}
}

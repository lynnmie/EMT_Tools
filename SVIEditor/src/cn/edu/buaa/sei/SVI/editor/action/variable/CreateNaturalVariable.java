package cn.edu.buaa.sei.SVI.editor.action.variable;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.NaturalVariableTreeNode;

public class CreateNaturalVariable extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateNaturalVariable(SVITreeNode node) {
		super(node);this.setName("natural");
		this.setText("Natural");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new NaturalVariableTreeNode(tree,DefaultNodeNames.NATURAL_VAR);
	}
}

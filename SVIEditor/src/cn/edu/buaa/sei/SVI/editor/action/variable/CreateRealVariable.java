package cn.edu.buaa.sei.SVI.editor.action.variable;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.RealVariableTreeNode;

public class CreateRealVariable extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateRealVariable(SVITreeNode node) {
		super(node);this.setName("real");
		this.setText("Real");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new RealVariableTreeNode(tree,DefaultNodeNames.REAL_VAR);
	}
}

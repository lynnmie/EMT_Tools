package cn.edu.buaa.sei.SVI.editor.action.op;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.CardinalityTreeNode;

public class CreateCardinality extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateCardinality(SVITreeNode node) {
		super(node);
		this.setName("cardinality");
		this.setText("Cardinality");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new CardinalityTreeNode(tree,DefaultNodeNames.CARDINALITY);
	}
}

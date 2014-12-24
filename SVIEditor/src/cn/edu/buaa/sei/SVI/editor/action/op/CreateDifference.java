package cn.edu.buaa.sei.SVI.editor.action.op;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.group.DifferenceTreeNode;

public class CreateDifference extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateDifference(SVITreeNode node) {
		super(node);
		this.setName("difference");
		this.setText("Difference");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new DifferenceTreeNode(tree,DefaultNodeNames.DIFFERENCE);
	}
}

package cn.edu.buaa.sei.SVI.editor.action.op;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.EquivalenceTreeNode;

public class CreateEquivalence extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateEquivalence(SVITreeNode node) {
		super(node);this.setName("equivalence");
		this.setText("Equilvalence");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new EquivalenceTreeNode(tree,DefaultNodeNames.EQUIVALENCE);
	}
}

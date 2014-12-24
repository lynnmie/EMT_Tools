package cn.edu.buaa.sei.SVI.editor.action.op;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.logic.NegationTreeNode;

public class CreateNegation extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateNegation(SVITreeNode node) {
		super(node);this.setName("negation");
		this.setText("Negation");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new NegationTreeNode(tree,DefaultNodeNames.NEGATION);
	}

}

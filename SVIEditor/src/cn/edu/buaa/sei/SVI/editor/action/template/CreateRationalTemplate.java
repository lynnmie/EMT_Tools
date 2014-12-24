package cn.edu.buaa.sei.SVI.editor.action.template;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.RationalTemplateTreeNode;

public class CreateRationalTemplate extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateRationalTemplate(SVITreeNode node) {
		super(node);this.setName("rational template");
		this.setText("RationalTemplate");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new RationalTemplateTreeNode(tree,DefaultNodeNames.RATIONAL_TEMP);
	}
}

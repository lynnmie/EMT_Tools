package cn.edu.buaa.sei.SVI.editor.action.template;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.NaturalTemplateTreeNode;

public class CreateNaturalTemplate extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateNaturalTemplate(SVITreeNode node) {
		super(node);this.setName("natural template");
		this.setText("NaturalTemplate");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new NaturalTemplateTreeNode(tree,DefaultNodeNames.NATURAL_TEMP);
	}
}

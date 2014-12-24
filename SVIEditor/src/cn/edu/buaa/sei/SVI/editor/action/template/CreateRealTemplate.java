package cn.edu.buaa.sei.SVI.editor.action.template;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.RealTemplateTreeNode;

public class CreateRealTemplate extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateRealTemplate(SVITreeNode node) {
		super(node);this.setName("real template");
		this.setText("RealTemplate");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new RealTemplateTreeNode(tree,DefaultNodeNames.REAL_TEMP);
	}
}

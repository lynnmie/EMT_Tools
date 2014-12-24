package cn.edu.buaa.sei.SVI.editor.action.op;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.ModTreeNode;

public class CreateMod extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateMod(SVITreeNode node) {
		super(node);this.setName("mod");
		this.setText("Mod");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new ModTreeNode(tree,DefaultNodeNames.MOD);
	}
}

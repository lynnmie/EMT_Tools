package cn.edu.buaa.sei.SVI.editor.action.variable;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.MapVariableTreeNode;

public class CreateMapVariable extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateMapVariable(SVITreeNode node) {
		super(node);this.setName("Map");
		this.setText("Map");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new MapVariableTreeNode(tree,DefaultNodeNames.MAP_VAR);
	}
}

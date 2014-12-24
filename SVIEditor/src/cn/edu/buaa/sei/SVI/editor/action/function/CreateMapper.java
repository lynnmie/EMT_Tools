package cn.edu.buaa.sei.SVI.editor.action.function;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.group.MapperTreeNode;

public class CreateMapper extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateMapper(SVITreeNode node) {
		super(node);
		this.setName("mapper");
		this.setText("Mapper");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new MapperTreeNode(tree,DefaultNodeNames.MAPPER);
	}
}

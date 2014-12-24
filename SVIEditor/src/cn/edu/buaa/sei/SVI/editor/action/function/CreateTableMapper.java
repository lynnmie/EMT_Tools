package cn.edu.buaa.sei.SVI.editor.action.function;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.core.SVIEditorCreateAction;
import cn.edu.buaa.sei.SVI.editor.treeNode.DefaultNodeNames;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.group.TableMapperTreeNode;

public class CreateTableMapper extends SVIEditorCreateAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateTableMapper(SVITreeNode node) {
		super(node);
		this.setName("table mapper");
		this.setText("TableMapper");
	}

	@Override
	protected SVITreeNode create(JTree tree) {
		return new TableMapperTreeNode(tree,DefaultNodeNames.TABLE_MAPPER);
	}
}

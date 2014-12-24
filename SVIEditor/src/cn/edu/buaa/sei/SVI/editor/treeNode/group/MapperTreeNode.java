package cn.edu.buaa.sei.SVI.editor.treeNode.group;

import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.treeNode.IconSet;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.FunctionTreeNode;

public class MapperTreeNode extends FunctionTreeNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MapperTreeNode(JTree tree, String name) {
		super(tree, name, IconSet.MAPPER_ICON);
		this.temp_init();
	}
	protected void temp_init(){
		this.menu.remove(this.menu.getComponent(0));
	}

	@Override
	public boolean validate() {return true;}
}

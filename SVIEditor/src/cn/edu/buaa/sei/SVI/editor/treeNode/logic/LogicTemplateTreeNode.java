package cn.edu.buaa.sei.SVI.editor.treeNode.logic;
import javax.swing.JTree;
import cn.edu.buaa.sei.SVI.editor.treeNode.IconSet;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.TemplateTreeNode;

public class LogicTemplateTreeNode extends TemplateTreeNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LogicTemplateTreeNode(JTree tree, String name) {
		super(tree, name, IconSet.LOGIC_TEMP_ICON);
	}

}

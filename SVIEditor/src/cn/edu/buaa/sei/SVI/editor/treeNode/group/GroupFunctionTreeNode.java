package cn.edu.buaa.sei.SVI.editor.treeNode.group;
import javax.swing.JMenu;
import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.template.CreateGroupTemplate;
import cn.edu.buaa.sei.SVI.editor.treeNode.IconSet;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.FunctionTreeNode;

public class GroupFunctionTreeNode extends FunctionTreeNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GroupFunctionTreeNode(JTree tree, String name) {
		super(tree, name, IconSet.GROUP_FUNC_ICON);
		this.regist();
	}
	protected void regist(){
		JMenu item0 = (JMenu) this.menu.getComponent(0);
		
		item0.add(new CreateGroupTemplate(this));
	}

	@Override
	public boolean validate() {
		if(this.getChildCount()==1&&(this.getChildAt(0) instanceof GroupTemplateTreeNode)){
			SVITreeNode node = (SVITreeNode) this.getChildAt(0);
			return node.validate();
		}
		return false;
	}

}

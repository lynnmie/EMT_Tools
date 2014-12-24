package cn.edu.buaa.sei.SVI.editor.treeNode.numeric;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.template.CreateNaturalTemplate;
import cn.edu.buaa.sei.SVI.editor.action.template.CreateRationalTemplate;
import cn.edu.buaa.sei.SVI.editor.action.template.CreateRealTemplate;
import cn.edu.buaa.sei.SVI.editor.action.template.CreateZIntTemplate;
import cn.edu.buaa.sei.SVI.editor.treeNode.IconSet;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.FunctionTreeNode;

public class NumericFunctionTreeNode extends FunctionTreeNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NumericFunctionTreeNode(JTree tree, String name) {
		super(tree, name, IconSet.NUM_FUNC_ICON);
		this.regist();
	}
	protected void regist(){
		JMenu item0 = (JMenu) this.menu.getComponent(0);
		
		JMenuItem item01 = new CreateNaturalTemplate(this);
		JMenuItem item02 = new CreateRationalTemplate(this);
		JMenuItem item03 = new CreateZIntTemplate(this);
		JMenuItem item04 = new CreateRealTemplate(this);
		
		item0.add(item01);
		item0.add(item02);
		item0.add(item03);
		item0.add(item04);
	}

	@Override
	public boolean validate() {
		if((this.getChildCount()==1)&&(this.getChildAt(0) instanceof NumericTemplateTreeNode)){
			SVITreeNode node = (SVITreeNode) this.getChildAt(0);
			return node.validate();
		}
		return false;
	}

}

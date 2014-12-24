package cn.edu.buaa.sei.SVI.editor.treeNode.logic;

import javax.swing.JMenu;
import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.expr.CreateLogicExpression;
import cn.edu.buaa.sei.SVI.editor.action.function.CreateLogicFunction;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateLogicVariable;
import cn.edu.buaa.sei.SVI.editor.treeNode.IconSet;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.OperatorTreeNode;

public class ExistentialTreeNode extends OperatorTreeNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExistentialTreeNode(JTree tree, String name) {
		super(tree, name, IconSet.OP_EXISTENTIAL_ICON);
		this.op_init();
		DiscourseDomainTreeNode node = new DiscourseDomainTreeNode(tree,"domain");
		this.add(node);
	}
	
	protected void op_init(){
		JMenu item0 = (JMenu) this.menu.getComponent(0);
		
		item0.add(new CreateLogicVariable(this));
		item0.add(new CreateLogicExpression(this));
		item0.add(new CreateLogicFunction(this));
	}

	@Override
	public boolean validate() {
		if(this.getChildCount()==2){
			for(int i=0;i<2;i++){
				SVITreeNode node = (SVITreeNode) this.getChildAt(i);
				if(!node.validate())return false;
			}
			return true;
		}
		return false;
	}
	@Override
	public int requiredChildrenCount() {return 2;}
}

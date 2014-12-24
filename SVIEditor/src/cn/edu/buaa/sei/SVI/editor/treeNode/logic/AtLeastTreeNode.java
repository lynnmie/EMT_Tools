package cn.edu.buaa.sei.SVI.editor.treeNode.logic;

import javax.swing.JMenu;
import javax.swing.JTree;
import cn.edu.buaa.sei.SVI.editor.action.expr.CreateLogicExpression;
import cn.edu.buaa.sei.SVI.editor.action.function.CreateLogicFunction;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateLogicVariable;
import cn.edu.buaa.sei.SVI.editor.treeNode.IconSet;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.OperatorTreeNode;

public class AtLeastTreeNode extends OperatorTreeNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AtLeastTreeNode(JTree tree, String name) {
		super(tree, name, IconSet.OP_ATLEAST_ICON);
		this.op_init();
		
		LowerTreeNode lower = new LowerTreeNode(tree,"0");
		this.add(lower);
		
		DiscourseDomainTreeNode node = new DiscourseDomainTreeNode(tree,"domain");
		this.add(node);
	}
	
	
	@Override
	public boolean validate(){
		if(this.getChildCount()==3){
			for(int i=0;i<this.getChildCount();i++){
				SVITreeNode child = (SVITreeNode) this.getChildAt(i);
				if(!child.validate())return false;
			}
			return true;
		}
		return false;
	}
	@Override
	public int requiredChildrenCount(){return 3;}
	
	protected void op_init(){
		JMenu item0 = (JMenu) this.menu.getComponent(0);
		
		item0.add(new CreateLogicVariable(this));
		item0.add(new CreateLogicExpression(this));
		item0.add(new CreateLogicFunction(this));
	}
}

package cn.edu.buaa.sei.SVI.editor.treeNode.logic;

import javax.swing.JMenu;
import javax.swing.JTree;
import cn.edu.buaa.sei.SVI.editor.action.expr.CreateLogicExpression;
import cn.edu.buaa.sei.SVI.editor.action.function.CreateLogicFunction;
import cn.edu.buaa.sei.SVI.editor.action.variable.CreateLogicVariable;
import cn.edu.buaa.sei.SVI.editor.treeNode.IconSet;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.OperatorTreeNode;

public class BetweenTreeNode extends OperatorTreeNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BetweenTreeNode(JTree tree, String name) {
		super(tree, name, IconSet.OP_BETWEEN_ICON);
		this.op_init();
		
		LowerTreeNode lower = new LowerTreeNode(tree,"0");
		this.add(lower);
		
		UpperTreeNode upper = new UpperTreeNode(tree,"-1");
		this.add(upper);
		
		DiscourseDomainTreeNode node = new DiscourseDomainTreeNode(tree,"domain");
		this.add(node);
	}
	
	
	@Override
	public boolean validate(){
		if(this.getChildCount()==4){
			LowerTreeNode lower = (LowerTreeNode) this.getChildAt(0);
			UpperTreeNode upper = (UpperTreeNode) this.getChildAt(1);
			
			if(!lower.validate()||!upper.validate())return false;
			
			Integer l = Integer.parseInt(lower.getUserObject().toString());
			Integer u = Integer.parseInt(upper.getUserObject().toString());
			if(l>u&&u!=-1)return false;
			
			for(int i=2;i<this.getChildCount();i++){
				SVITreeNode child = (SVITreeNode) this.getChildAt(i);
				if(!child.validate())return false;
			}
			return true;
		}
		return false;
	}
	@Override
	public int requiredChildrenCount(){return 4;}
	
	protected void op_init(){
		JMenu item0 = (JMenu) this.menu.getComponent(0);
		
		item0.add(new CreateLogicVariable(this));
		item0.add(new CreateLogicExpression(this));
		item0.add(new CreateLogicFunction(this));
	}
}

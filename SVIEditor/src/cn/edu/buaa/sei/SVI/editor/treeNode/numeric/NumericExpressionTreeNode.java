package cn.edu.buaa.sei.SVI.editor.treeNode.numeric;
import javax.swing.JMenu;
import javax.swing.JTree;
import cn.edu.buaa.sei.SVI.editor.action.op.CreateAdd;
import cn.edu.buaa.sei.SVI.editor.action.op.CreateCardinality;
import cn.edu.buaa.sei.SVI.editor.action.op.CreateDiv;
import cn.edu.buaa.sei.SVI.editor.action.op.CreateMod;
import cn.edu.buaa.sei.SVI.editor.action.op.CreateMul;
import cn.edu.buaa.sei.SVI.editor.action.op.CreateSub;
import cn.edu.buaa.sei.SVI.editor.treeNode.IconSet;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.ExpressionTreeNode;

public class NumericExpressionTreeNode extends ExpressionTreeNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NumericExpressionTreeNode(JTree tree, String name) {
		super(tree, name, IconSet.NUM_EXPR_ICON);
		this.num_init();
	}
	
	protected void num_init(){
		JMenu item0 = (JMenu) this.menu.getComponent(0);
		
		item0.add(new CreateAdd(this));
		item0.add(new CreateSub(this));
		item0.add(new CreateMul(this));
		item0.add(new CreateDiv(this));
		item0.add(new CreateMod(this));
		item0.add(new CreateCardinality(this));
	}
}

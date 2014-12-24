package cn.edu.buaa.sei.SVI.editor.treeNode.logic;
import javax.swing.JMenu;
import javax.swing.JTree;

import cn.edu.buaa.sei.SVI.editor.action.op.CreateAtLeast;
import cn.edu.buaa.sei.SVI.editor.action.op.CreateAtMost;
import cn.edu.buaa.sei.SVI.editor.action.op.CreateBetween;
import cn.edu.buaa.sei.SVI.editor.action.op.CreateBigger;
import cn.edu.buaa.sei.SVI.editor.action.op.CreateConjunction;
import cn.edu.buaa.sei.SVI.editor.action.op.CreateContain;
import cn.edu.buaa.sei.SVI.editor.action.op.CreateDisjunction;
import cn.edu.buaa.sei.SVI.editor.action.op.CreateEBigger;
import cn.edu.buaa.sei.SVI.editor.action.op.CreateESmaller;
import cn.edu.buaa.sei.SVI.editor.action.op.CreateEqual;
import cn.edu.buaa.sei.SVI.editor.action.op.CreateEquivalence;
import cn.edu.buaa.sei.SVI.editor.action.op.CreateExistential;
import cn.edu.buaa.sei.SVI.editor.action.op.CreateGroupEqual;
import cn.edu.buaa.sei.SVI.editor.action.op.CreateImplication;
import cn.edu.buaa.sei.SVI.editor.action.op.CreateInclude;
import cn.edu.buaa.sei.SVI.editor.action.op.CreateNegation;
import cn.edu.buaa.sei.SVI.editor.action.op.CreateSmaller;
import cn.edu.buaa.sei.SVI.editor.action.op.CreateUniversal;
import cn.edu.buaa.sei.SVI.editor.treeNode.IconSet;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.ExpressionTreeNode;

public class LogicExpressionTreeNode extends ExpressionTreeNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LogicExpressionTreeNode(JTree tree, String name) {
		super(tree, name, IconSet.LOGIC_EXPR_ICON);
		this.logic_init();
	}

	protected void logic_init(){
		JMenu item0 = (JMenu) this.menu.getComponent(0);
		
		item0.add(new CreateNegation(this));
		item0.add(new CreateConjunction(this));
		item0.add(new CreateDisjunction(this));
		item0.add(new CreateImplication(this));
		item0.add(new CreateEquivalence(this));
		item0.add(new CreateUniversal(this));
		item0.add(new CreateExistential(this));
		item0.add(new CreateAtLeast(this));
		item0.add(new CreateAtMost(this));
		item0.add(new CreateBetween(this));
		item0.add(new CreateSmaller(this));
		item0.add(new CreateESmaller(this));
		item0.add(new CreateEqual(this));
		item0.add(new CreateEBigger(this));
		item0.add(new CreateBigger(this));
		item0.add(new CreateGroupEqual(this));
		item0.add(new CreateContain(this));
		item0.add(new CreateInclude(this));
	}
	
}

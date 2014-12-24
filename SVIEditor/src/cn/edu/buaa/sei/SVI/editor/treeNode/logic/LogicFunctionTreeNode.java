package cn.edu.buaa.sei.SVI.editor.treeNode.logic;
import javax.swing.JMenu;
import javax.swing.JTree;
import cn.edu.buaa.sei.SVI.editor.action.template.CreateLogicTemplate;
import cn.edu.buaa.sei.SVI.editor.treeNode.IconSet;
import cn.edu.buaa.sei.SVI.editor.treeNode.SVITreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.FunctionTreeNode;

public class LogicFunctionTreeNode extends FunctionTreeNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LogicFunctionTreeNode(JTree tree, String name) {
		super(tree, name, IconSet.LOGIC_FUNC_ICON);
		this.regist();
	}
	protected void regist(){
		JMenu item0 = (JMenu) this.menu.getComponent(0);
		
		item0.add(new CreateLogicTemplate(this));
		//JMenuItem item01 = new JMenuItem("LogicTemplate");
		//JMenuItem item02 = new JMenuItem("RationalTemplate");
		//JMenuItem item03 = new JMenuItem("ZIntegerTemplate");
		//JMenuItem item04 = new JMenuItem("RealTemplate");
		
		//item0.add(item01);
		//item0.add(item02);
		//item0.add(item03);
		//item0.add(item04);
	}

	@Override
	public boolean validate() {
		if(this.getChildCount()==1&&this.getChildAt(0) instanceof LogicTemplateTreeNode){
			SVITreeNode node = (SVITreeNode) this.getChildAt(0);
			return node.validate();
		}
		else return false;
	}

}

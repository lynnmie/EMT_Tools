package cn.edu.buaa.sei.SVI.view.test;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

public class Test {

	public static void main(String[] args) {
		MH();
		//testTreeAppend();
		//testJMenu();
		//testTreeIcon();
		//testJMenu2();
	}
	
	public static void testJMenu2(){
		JMenu item0 = new JMenu("create");
		JMenuItem item1 = new JMenuItem("remove");
		JMenuItem item2 = new JMenuItem("validate");
		
		JPopupMenu menu = new JPopupMenu();
		menu.add(item0);menu.add(item1);menu.add(item2);
		item0.add(item0);
		
		System.out.println(menu.getComponent(0).hashCode()+": "+item0.hashCode());
		System.out.println(menu.getComponent(1).hashCode()+": "+item1.hashCode());
		System.out.println(menu.getComponent(2).hashCode()+": "+item2.hashCode());
	}
	
	@SuppressWarnings("static-access")
	public static void MH(){
		try {
			BeautyEyeLNFHelper.launchBeautyEyeLNF();
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.translucencyAppleLike;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void testJMenu(){
		final JPanel pan = new JPanel();
		
		final JMenuItem item1 = new JMenuItem(); item1.setText("addition");
		final JMenuItem item2 = new JMenuItem(); item2.setText("Remove");
		final JMenuItem item3 = new JMenuItem(); item3.setText("substract");
		final JMenu sub = new JMenu("Add");
		sub.add(item1); sub.add(item3);
		final JPopupMenu menu = new JPopupMenu();
		menu.add(sub); menu.add(item2);
		
		pan.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON3){
					System.out.println("["+e.getX()+","+e.getY()+"]");
					
					menu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
		});
		
		JFrame f = new JFrame();
		f.setSize(300, 300);
		f.add(new JScrollPane(pan));
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void testTreeAppend(){
		DefaultMutableTreeNode node = new DefaultMutableTreeNode("Root");
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("LogicExpression");
		DefaultMutableTreeNode op = new DefaultMutableTreeNode("Universal");
		DefaultMutableTreeNode domain = new DefaultMutableTreeNode("HLR");
		DefaultMutableTreeNode iter = new DefaultMutableTreeNode("HLR.iter");
		DefaultMutableTreeNode scope = new DefaultMutableTreeNode("LogicFunction");
		DefaultMutableTreeNode template = new DefaultMutableTreeNode("traceable");
		DefaultMutableTreeNode ref = new DefaultMutableTreeNode("hlr");
		
		node.add(top); top.add(op); op.add(domain); op.add(scope); 
		domain.add(iter); scope.add(template); template.add(ref);
		
		
		final JTree tree = new JTree(node);
		final DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		
		System.out.println(tree.getComponent(0).hashCode()+": "+node.hashCode());
		
		tree.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if(node==null)return;
				//System.out.println("Click #"+arg0.getButton()+": "+node.getUserObject().toString());
				if(arg0.getButton()==MouseEvent.BUTTON3){
					System.out.println("Try to add new node at: #"+node.getUserObject().toString());
					model.insertNodeInto(new DefaultMutableTreeNode("NewOne"), node, node.getChildCount());
				}
				else if(arg0.getButton()==MouseEvent.BUTTON2){
					System.out.println("Try to delete the selected node at: #"+node.getUserObject().toString());
					model.removeNodeFromParent(node);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});
		
		JFrame f = new JFrame();
		f.setSize(300, 300);
		f.add(new JScrollPane(tree));
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void testTreeIcon(){
		DefaultMutableTreeNode node = new DefaultMutableTreeNode("Root");
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("LogicExpression");
		DefaultMutableTreeNode op = new DefaultMutableTreeNode("Universal");
		DefaultMutableTreeNode domain = new DefaultMutableTreeNode("HLR");
		DefaultMutableTreeNode iter = new DefaultMutableTreeNode("HLR.iter");
		DefaultMutableTreeNode scope = new DefaultMutableTreeNode("LogicFunction");
		DefaultMutableTreeNode template = new DefaultMutableTreeNode("traceable");
		DefaultMutableTreeNode ref = new DefaultMutableTreeNode("hlr");
		
		node.add(top); top.add(op); op.add(domain); op.add(scope); 
		domain.add(iter); scope.add(template); template.add(ref);
		
		System.out.println(node.getUserObject()+": "+node.getChildAt(0).toString());
		
		final JTree tree = new JTree(node);
		final DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		tree.setCellRenderer(new MyCellRenderer());
		
		tree.setEditable(true);
		tree.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if(node==null)return;
				//System.out.println("Click #"+arg0.getButton()+": "+node.getUserObject().toString());
				if(arg0.getButton()==MouseEvent.BUTTON3){
					System.out.println("Try to add new node at: #"+node.getUserObject().toString());
					model.insertNodeInto(new DefaultMutableTreeNode("NewOne"), node, node.getChildCount());
				}
				else if(arg0.getButton()==MouseEvent.BUTTON2){
					System.out.println("Try to delete the selected node at: #"+node.getUserObject().toString());
					model.removeNodeFromParent(node);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});
		
		
		JFrame f = new JFrame();
		f.setSize(300, 300);
		f.add(new JScrollPane(tree));
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	static class MyCellRenderer extends DefaultTreeCellRenderer{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override  
	    public Component getTreeCellRendererComponent(JTree tree, Object value,  
	            boolean sel, boolean expanded, boolean leaf, int row,  
	            boolean hasFocus)
	    {
			super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,  
	                row, hasFocus);
			setText(value.toString()); 
			if(sel)setForeground(getTextSelectionColor());
	        else setForeground(getTextNonSelectionColor());
			
			
			Font f = new Font(Font.SANS_SERIF, Font.BOLD, 12);
			this.setFont(f);
			
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
			
			if(node.isLeaf()){this.setIcon(new ImageIcon("icons/variable.gif"));}
			else if(node.isRoot()){this.setIcon(new ImageIcon("icons/expression.gif"));}
			else{this.setIcon(new ImageIcon("icons/add.gif"));}
			/*String str = node.toString();         
	          
	        if (str == "a")
	        	this.setIcon(new ImageIcon("treeimg/a.GIF"));
	        if (str == "b")
	        	this.setIcon(new ImageIcon("treeimg/b.GIF"));
	        if (str == "c")
	        	this.setIcon(new ImageIcon("treeimg/c.GIF"));*/
	        return this;  
	    }
		
	}
}

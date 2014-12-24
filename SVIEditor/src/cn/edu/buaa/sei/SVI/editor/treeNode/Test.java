package cn.edu.buaa.sei.SVI.editor.treeNode;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import cn.edu.buaa.sei.SVI.editor.translator.Struct2TreeNode;
import cn.edu.buaa.sei.SVI.editor.translator.TreeNode2Struct;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.StructRootTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.AddTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.NaturalVariableTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.NumericExpressionTreeNode;
import cn.edu.buaa.sei.SVI.editor.treeNode.numeric.ZIntVariableTreeNode;
import cn.edu.buaa.sei.SVI.manage.IStructImporter;
import cn.edu.buaa.sei.SVI.manage.IStructPrinter;
import cn.edu.buaa.sei.SVI.manage.StructManager;
import cn.edu.buaa.sei.SVI.manage.impl.SVIStream;
import cn.edu.buaa.sei.SVI.manage.impl.xml_struct.XMLStructImporter;
import cn.edu.buaa.sei.SVI.manage.impl.xml_struct.XMLStructPrinter;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.variable.ReferenceVariable;

public class Test {

	public static void main(String[] args) {
		MH();
		final SVIEditorPanel svi_pan = new SVIEditorPanel();
		JFrame f = new JFrame();
		f.add(svi_pan);
		
		f.setTitle("DO178Logic");
		f.setSize(300, 300);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//testWindow();
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
	public static void testWindow(){
		MH();
		/***/
		final StructRootTreeNode root = new StructRootTreeNode(null,"root");
		final JTree tree = new JTree(root);root.setTree(tree);
		System.out.println(tree.getComponent(0).hashCode()+":"+root.hashCode());
		tree.setCellRenderer(new SVICellRenderer());
		
		tree.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if(node==null)return;
				if(!(node instanceof SVITreeNode))return;
				
				SVITreeNode snode = (SVITreeNode) node;
				if(e.getButton()==MouseEvent.BUTTON3){
					JPopupMenu menu = snode.getPopupMenu();
					menu.show(e.getComponent(), e.getX(), e.getY());
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
		
		tree.setEditable(true);
		
		JPanel pan = new JPanel();
		pan.setLayout(new BorderLayout());
		pan.add(BorderLayout.CENTER,tree);
		pan.add(BorderLayout.EAST,new JScrollBar());
		pan.add(BorderLayout.SOUTH,new JScrollBar(JScrollBar.HORIZONTAL));
		
		JButton save = new JButton("save");
		JButton load = new JButton("load");
		JPanel cp = new JPanel();
		cp.add(save); cp.add(load);
		
		
		/***/
		final JFrame f = new JFrame();
		f.setSize(300, 300);
		f.setLayout(new BorderLayout());
		f.add(BorderLayout.CENTER,pan);
		f.add(BorderLayout.SOUTH,cp);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		save.addActionListener(new ActionListener(){
			TreeNode2Struct translator = new TreeNode2Struct();
			IStructPrinter printer = new XMLStructPrinter();
			SVIStream resource = new SVIStream();
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					StructManager manager = translator.getFromRoot(root);
					
					Iterator<Struct> itor = manager.getTopStructs().iterator();
					while(itor.hasNext()){
						Struct top = itor.next();
						if(top instanceof ReferenceVariable){
							System.out.println(((ReferenceVariable) top).getName()+" --> "
									+ ((ReferenceVariable) top).getRefer().hashCode());
						}
						else{
							System.out.println(top.getClass().getName()+": "+top.hashCode());
						}
					}
					System.out.println("Ready to save...");
					
					JFileChooser dialog = new JFileChooser();
					int ret = dialog.showSaveDialog(f);
					if (ret != JFileChooser.APPROVE_OPTION) {
						return;
					}
					
					File file = dialog.getSelectedFile();
					resource.setOutputStream(new FileOutputStream(file));
					printer.setOutputStream(resource);
					printer.write(manager);
					System.out.println("Writting file: "+file.getAbsolutePath());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}});
		
		load.addActionListener(new ActionListener(){
			SVIStream resource = new SVIStream();
			Struct2TreeNode translater = new Struct2TreeNode();
			IStructImporter reader = new XMLStructImporter();
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser dialog = new JFileChooser();
				int ret = dialog.showOpenDialog(f);
				if (ret != JFileChooser.APPROVE_OPTION) {
					return;
				}
				
				try {
					File file = dialog.getSelectedFile();
					System.out.println("Reading File: "+file.getAbsolutePath());
					resource.setInputStream(new FileInputStream(file));
					reader.setInput(resource);
					System.out.println("Ready to read...");
					StructManager manager = reader.read();
					System.out.println("Reading complete!");
					StructRootTreeNode _root = this.translater.tranlate(manager);
					root.reset(_root);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}});
	}

	public static JTree test1(){
		StructRootTreeNode root = new StructRootTreeNode(null,"root");
		final JTree tree = new JTree(root);root.setTree(tree);
		
		NumericExpressionTreeNode item1 = new NumericExpressionTreeNode(tree,"expression");
		AddTreeNode item11 = new AddTreeNode(tree,"addition");
		NaturalVariableTreeNode item111 = new NaturalVariableTreeNode(tree,"x");
		ZIntVariableTreeNode item112 = new ZIntVariableTreeNode(tree,"y");
		item1.add(item11);
		item11.add(item111); item11.add(item112);
		root.add(item1);
		
		//final DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		tree.setCellRenderer(new SVICellRenderer());
		
		tree.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if(node==null)return;
				if(!(node instanceof SVITreeNode))return;
				
				SVITreeNode snode = (SVITreeNode) node;
				if(e.getButton()==MouseEvent.BUTTON3){
					/*System.out.println("Try to add new node at: #"+node.getUserObject().toString());
					model.insertNodeInto(new DefaultMutableTreeNode("NewOne"), node, node.getChildCount());*/
					JPopupMenu menu = snode.getPopupMenu();
					menu.show(e.getComponent(), e.getX(), e.getY());
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
		
		tree.setEditable(true);
		
		return tree;
	}
	
	
}

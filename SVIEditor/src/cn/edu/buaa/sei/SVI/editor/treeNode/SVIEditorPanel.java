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
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import cn.edu.buaa.sei.SVI.editor.translator.Struct2TreeNode;
import cn.edu.buaa.sei.SVI.editor.translator.TreeNode2Struct;
import cn.edu.buaa.sei.SVI.editor.treeNode.core.StructRootTreeNode;
import cn.edu.buaa.sei.SVI.manage.IStructImporter;
import cn.edu.buaa.sei.SVI.manage.IStructPrinter;
import cn.edu.buaa.sei.SVI.manage.StructManager;
import cn.edu.buaa.sei.SVI.manage.impl.SVIStream;
import cn.edu.buaa.sei.SVI.manage.impl.xml_struct.XMLStructImporter;
import cn.edu.buaa.sei.SVI.manage.impl.xml_struct.XMLStructPrinter;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.variable.ReferenceVariable;

public class SVIEditorPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected StructRootTreeNode root = new StructRootTreeNode(null,"root");
	protected JTree tree;
	protected StructManager manager;
	
	public SVIEditorPanel(){
		this.tree = new JTree(root);root.setTree(tree);
		
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
		this.setLayout(new BorderLayout());
		
		this.add(BorderLayout.CENTER,new JScrollPane(tree));
		
		JButton save = new JButton("Save Expressions");
		JButton load = new JButton("Load Expressions");
		JButton trans = new JButton("Update Expressions");
		JPanel cp = new JPanel();
		cp.add(save); cp.add(load);cp.add(trans);
		this.add(BorderLayout.SOUTH,cp);
		
		save.addActionListener(new ActionListener(){
			TreeNode2Struct translator = new TreeNode2Struct();
			IStructPrinter printer = new XMLStructPrinter();
			SVIStream resource = new SVIStream();
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					manager = translator.getFromRoot(root);
					
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
					int ret = dialog.showSaveDialog(null);
					if (ret != JFileChooser.APPROVE_OPTION) {
						return;
					}
					
					File file = dialog.getSelectedFile();
					resource.setOutputStream(new FileOutputStream(file));
					printer.setOutputStream(resource);
					printer.write(manager);
					System.out.println("Writting file: "+file.getAbsolutePath());
					JOptionPane.showMessageDialog(null, printMessage(file), 
							"Save Logic Propositions", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
			protected String printMessage(File file){
				StringBuilder code = new StringBuilder();
				code.append("Save to file: ").append(file.getAbsolutePath()).append("\n");
				code.append("**************************************\n");
				
				Set<Struct> tops = manager.getTopStructs();
				int i=0;
				for(Struct top:tops){
					code.append("[").append(++i).append("]: ").append(top.toString()).append("\n");
				}
				
				code.append("**************************************\n");
				return code.toString();
			}
		});
		load.addActionListener(new ActionListener(){
			SVIStream resource = new SVIStream();
			Struct2TreeNode translater = new Struct2TreeNode();
			IStructImporter reader = new XMLStructImporter();
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser dialog = new JFileChooser();
				int ret = dialog.showOpenDialog(null);
				if (ret != JFileChooser.APPROVE_OPTION) {
					return;
				}
				
				try {
					File file = dialog.getSelectedFile();
					System.out.println("Reading File: "+file.getAbsolutePath());
					resource.setInputStream(new FileInputStream(file));
					reader.setInput(resource);
					System.out.println("Ready to read...");
					
					manager = reader.read();
					System.out.println("Reading complete!");
					StructRootTreeNode _root = this.translater.tranlate(manager);
					root.reset(_root);
					JOptionPane.showMessageDialog(null, printMessage(file), 
							"Save Logic Propositions", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
			protected String printMessage(File file){
				StringBuilder code = new StringBuilder();
				code.append("Read from file: ").append(file.getAbsolutePath()).append("\n");
				code.append("**************************************\n");
				
				Set<Struct> tops = manager.getTopStructs();
				int i=0;
				for(Struct top:tops){
					code.append("[").append(++i).append("]: ").append(top.toString()).append("\n");
				}
				
				code.append("**************************************\n");
				return code.toString();
			}
		});
		trans.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				TreeNode2Struct translator = new TreeNode2Struct();
				try {
					manager = translator.getFromRoot(root);
					if(manager==null)throw new Exception();
					JOptionPane.showMessageDialog(null, this.printMessage(), "Update propositions", JOptionPane.INFORMATION_MESSAGE);
					
					Set<Struct> tops = manager.getTopStructs();
					System.out.println("****************************");
					for(Struct top:tops)
						System.out.println(top.toString());
					System.out.println("****************************");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Translation Failed!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			protected String printMessage(){
				StringBuilder code = new StringBuilder();
				code.append("Updating propositions: ").append(manager.getTopStructs().size()).append("\n");
				code.append("**************************************\n");
				
				Set<Struct> tops = manager.getTopStructs();
				int i=0;
				for(Struct top:tops){
					code.append("[").append(++i).append("]: ").append(top.toString()).append("\n");
				}
				
				code.append("**************************************\n");
				return code.toString();
			}
		});
		
	}
	
	public StructManager getResult(){return this.manager;}
	public StructRootTreeNode getRoot(){return this.root;}

}

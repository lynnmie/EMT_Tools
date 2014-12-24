package cn.edu.buaa.sei.emt.exLmf.viewer.model;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassifier;
import cn.edu.buaa.sei.exLmf.metamodel.LEnum;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;
import cn.edu.buaa.sei.exLmf.ogm.IObjectGroup;
import cn.edu.buaa.sei.exLmf.ogm.IObjectWorld;
import cn.edu.buaa.sei.exLmf.ogm.OGResourceReader;
import cn.edu.buaa.sei.exLmf.ogm.OGResourceWriter;
import cn.edu.buaa.sei.exLmf.ogm.impl.OG_DirectoryImpl;
import cn.edu.buaa.sei.exLmf.ogm.impl.OG_XMLFile;
import cn.edu.buaa.sei.exLmf.ogm.impl.ObjectWorld;
import cn.edu.buaa.sei.exLmf.ogm.impl.XMLDBReader;
import cn.edu.buaa.sei.exLmf.ogm.impl.XMLDBWriter;
import cn.edu.buaa.sei.exLmf.ogm.impl.XMLFileReader;
import cn.edu.buaa.sei.exLmf.translater.EcoreModelReader;
import cn.edu.buaa.sei.exLmf.translater.IModelReader;

public class ExLMFEditorPane extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LPackage root;
	public IObjectWorld cache;
	
	public ExLMFEditorPane(){
		final ModelPanel pan = new ModelPanel();
		JButton load_model = new JButton("Load Model");
		//JButton exit = new JButton("Exit");
		JButton read_data = new JButton("Import Data");
		JButton read_DB = new JButton("Read DB");
		JButton write_DB = new JButton("Write DB");
		JPanel cp = new JPanel();
		cp.add(load_model);cp.add(read_data);cp.add(write_DB);
		cp.add(read_DB);
		//cp.add(exit);
		
		//final JFrame f = new JFrame();
		//f.setSize(300, 300);
		this.setLayout(new BorderLayout());
		this.add(BorderLayout.CENTER,new JScrollPane(pan));
		this.add(BorderLayout.SOUTH,cp);
		
		load_model.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser dialog = new JFileChooser();
				dialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int ret = dialog.showOpenDialog(null);
				if (ret != JFileChooser.APPROVE_OPTION) {
					return;
				}
				
				File file = dialog.getSelectedFile();
				Date start = new Date();
				try {
					readModel(file);
					if(root!=null)
						pan.update(root);
					else
						JOptionPane.showMessageDialog(null, "Model Reading failed at: "+file.getAbsolutePath(), "alert", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Date end = new Date();
				JOptionPane.showMessageDialog(null, message(file), "Load Model", JOptionPane.INFORMATION_MESSAGE);
				JOptionPane.showMessageDialog(null, "Using time for: "+(end.getTime()-start.getTime())+" msecs", "Time for Load Model", JOptionPane.INFORMATION_MESSAGE);
			}
			protected String message(File file){
				StringBuilder code = new StringBuilder();
				
				code.append("Read from: "+file.getAbsolutePath());
				code.append(root.getAbsolutePath()).append("{").append(root.getTypes().size()).append(" types}\n");
				code.append("***************************************\n");
				
				List<LClassifier> types = root.getTypes();
				int k=0;
				for(int i=0;i<types.size();i++){
					code.append("\t-[").append(++k).append("] ").append(types.get(i).getAbsolutePath());
					if(types.get(i) instanceof LClass)
						code.append(": ").append("class");
					else if(types.get(i) instanceof LEnum)
						code.append(": enum");
					code.append("\n");
				}
				
				code.append("***************************************\n");
				return code.toString();
			}
		});
		read_data.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser dialog = new JFileChooser();
				dialog.setMultiSelectionEnabled(true);
				dialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int ret = dialog.showOpenDialog(null);
				if (ret != JFileChooser.APPROVE_OPTION) {
					return;
				}
				
				File[] files = dialog.getSelectedFiles();
				Date start = new Date();
				try {
					readDataFromFiles(files);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				Date end = new Date();
				JOptionPane.showMessageDialog(null, message(files), "Import Data", JOptionPane.INFORMATION_MESSAGE);
				
				JOptionPane.showMessageDialog(null, "Using time for: "+(end.getTime()-start.getTime())+" msecs", "Time for Importing Data", JOptionPane.INFORMATION_MESSAGE);
			}
			protected String message(File[] files){
				StringBuilder code = new StringBuilder();
				
				code.append("Read from: [");
				for(int i=0;i<files.length;i++){
					code.append(files[i].getName());
					if(i!=files.length-1)
						code.append("; ");
				}
				code.append("]\n");
				
				code.append("************************************\n");
				Map<LClass,IObjectGroup> map = cache.getGroups();
				Set<LClass> keys = map.keySet();
				int k=0;
				for(LClass key:keys){
					code.append("[").append(++k).append("] ").append(key.getAbsolutePath()).append(": ");
					code.append(map.get(key).getObjects().size()).append(" objects\n");
				}
				code.append("************************************\n");
				return code.toString();
			}
		});
		read_DB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser dialog = new JFileChooser();
				dialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int ret = dialog.showOpenDialog(null);
				if (ret != JFileChooser.APPROVE_OPTION) {
					return;
				}
				
				File file = dialog.getSelectedFile();
				if(!file.isDirectory()){
					JOptionPane.showMessageDialog(null, "Not a directory: "+file.getAbsolutePath(), "alert", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Date start = new Date();
				try {
					readDB(file);
					pan.update(root);
					
					System.out.println("Reading successfully from: "+file.getAbsolutePath());
					//JOptionPane.showInternalMessageDialog(f, "Reading from DB: "+cache.getGroups().size()+" types imported","Reading Successfully!", JOptionPane.INFORMATION_MESSAGE); 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Date end = new Date();
				JOptionPane.showMessageDialog(null, message(file), "Read DB", JOptionPane.INFORMATION_MESSAGE);
				
				JOptionPane.showMessageDialog(null, "Using time for: "+(end.getTime()-start.getTime())+" msecs", "Time for Read DB", JOptionPane.INFORMATION_MESSAGE);
			}
			protected String message(File file){
				StringBuilder code = new StringBuilder();
				
				code.append("Read from database: "+file.getAbsolutePath()).append("\n");
				
				code.append("************************************\n");
				Map<LClass,IObjectGroup> map = cache.getGroups();
				Set<LClass> keys = map.keySet();
				int k=0;
				for(LClass key:keys){
					code.append("[").append(++k).append("] ").append(key.getAbsolutePath()).append(": ");
					code.append(map.get(key).getObjects().size()).append(" objects\n");
				}
				code.append("************************************\n");
				return code.toString();
			}
		});
		write_DB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser dialog = new JFileChooser();
				dialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int ret = dialog.showOpenDialog(null);
				if (ret != JFileChooser.APPROVE_OPTION) {
					return;
				}
				
				File file = dialog.getSelectedFile();
				if(!file.isDirectory()){
					JOptionPane.showMessageDialog(null, "Not a directory: "+file.getAbsolutePath(), "alert", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Date start = new Date();
				try {
					writeDB(file);
					System.out.println("Writting successfully into: "+file.getAbsolutePath());
					//JOptionPane.showInternalMessageDialog(null, "Writing to DB: "+file.getAbsolutePath(),"Writting Successfully!", JOptionPane.INFORMATION_MESSAGE); 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Date end = new Date();
				
				JOptionPane.showMessageDialog(null, message(file), "Write DB", JOptionPane.INFORMATION_MESSAGE);
				JOptionPane.showMessageDialog(null, "Using time for: "+(end.getTime()-start.getTime())+" msecs", "Time for writing DB", JOptionPane.INFORMATION_MESSAGE);
			}
			protected String message(File file){
				StringBuilder code = new StringBuilder();
				
				code.append("Write into database: "+file.getAbsolutePath()).append("\n");
				
				code.append("************************************\n");
				Map<LClass,IObjectGroup> map = cache.getGroups();
				Set<LClass> keys = map.keySet();
				int k=0;
				for(LClass key:keys){
					code.append("[").append(++k).append("] ").append(key.getAbsolutePath()).append(": ");
					code.append(map.get(key).getObjects().size()).append(" objects\n");
				}
				code.append("************************************\n");
				return code.toString();
			}
		});
		/*exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}});*/
	}
	
	public void readModel(File file) throws Exception{
		IModelReader im = new EcoreModelReader("Ecore_Reader");
		im.setInputStream(file);
		root = im.read();
		if(cache!=null)cache.clearObjectSpace();
		cache = new ObjectWorld(root);
		System.out.println("Model&World updated");
	}
	public void readDataFromFiles(File[] fs) throws Exception{
		OG_XMLFile[] files = new OG_XMLFile[fs.length];
		for(int i=0;i<fs.length;i++)
			files[i] = new OG_XMLFile(fs[i]);
		
		OGResourceReader reader = new XMLFileReader(cache);
		for(int i=0;i<fs.length;i++){
			reader.setResource(files[i]);
			reader.read();
		}
		for(int i=0;i<fs.length;i++){
			reader.setResource(files[i]);
			reader.link();
		}
		
		Map<LClass,IObjectGroup> map = cache.getGroups();
		Set<LClass> keys = map.keySet();
		System.out.println("***********************"+fs.length+"*********************");
		for(LClass k:keys){
			System.out.println(k.getAbsolutePath()+":"+map.get(k).getObjects().size());
		}
		System.out.println("********************************************");
	}
	public void writeDB(File db) throws Exception{
		OGResourceWriter writer = new XMLDBWriter(cache);
		writer.setResource(new OG_DirectoryImpl(db));
		writer.write();
	}
	public void readDB(File db) throws Exception{
		XMLDBReader reader = new XMLDBReader();
		reader.setResource(new OG_DirectoryImpl(db));
		reader.read();
		
		cache = reader.getCache();
		root = cache.getModelSpace();
		
		System.out.println("DB Synchronization Complete!!!");
	}

	public LPackage getModel(){return this.root;}
	public IObjectWorld getData(){return this.cache;}
	
}

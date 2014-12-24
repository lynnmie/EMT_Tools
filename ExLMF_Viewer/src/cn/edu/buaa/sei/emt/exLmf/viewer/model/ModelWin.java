package cn.edu.buaa.sei.emt.exLmf.viewer.model;
import java.io.File;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import cn.edu.buaa.sei.exLmf.metamodel.LClass;
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

public class ModelWin {
	
	public static LPackage root;
	public static IObjectWorld cache;
	
	public static void main(String[] args){
		MH();
		JFrame f = new JFrame();
		f.setSize(300, 300);
		f.add(new ExLMFEditorPane());
		f.setVisible(true);
		f.setTitle("Modeling Data Manager");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*final ModelPanel pan = new ModelPanel();
		
		JButton load_model = new JButton("Load Model");
		JButton exit = new JButton("Exit");
		JButton read_data = new JButton("Import Data");
		JButton read_DB = new JButton("Read DB");
		JButton write_DB = new JButton("Write DB");
		JPanel cp = new JPanel();
		cp.add(load_model);cp.add(read_data);cp.add(write_DB);
		cp.add(read_DB);cp.add(exit);
		
		final JFrame f = new JFrame();
		f.setSize(300, 300);
		f.setLayout(new BorderLayout());
		f.add(BorderLayout.CENTER,pan);
		f.add(BorderLayout.SOUTH,cp);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		load_model.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser dialog = new JFileChooser();
				dialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int ret = dialog.showOpenDialog(f);
				if (ret != JFileChooser.APPROVE_OPTION) {
					return;
				}
				
				File file = dialog.getSelectedFile();
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
			}});
		read_data.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser dialog = new JFileChooser();
				dialog.setMultiSelectionEnabled(true);
				dialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int ret = dialog.showOpenDialog(f);
				if (ret != JFileChooser.APPROVE_OPTION) {
					return;
				}
				
				File[] files = dialog.getSelectedFiles();
				try {
					readDataFromFiles(files);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		read_DB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser dialog = new JFileChooser();
				dialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int ret = dialog.showOpenDialog(f);
				if (ret != JFileChooser.APPROVE_OPTION) {
					return;
				}
				
				File file = dialog.getSelectedFile();
				if(!file.isDirectory()){
					JOptionPane.showMessageDialog(f, "Not a directory: "+file.getAbsolutePath(), "alert", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try {
					readDB(file);
					pan.update(root);
					
					System.out.println("Reading successfully from: "+file.getAbsolutePath());
					//JOptionPane.showInternalMessageDialog(f, "Reading from DB: "+cache.getGroups().size()+" types imported","Reading Successfully!", JOptionPane.INFORMATION_MESSAGE); 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}});
		write_DB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser dialog = new JFileChooser();
				dialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int ret = dialog.showOpenDialog(f);
				if (ret != JFileChooser.APPROVE_OPTION) {
					return;
				}
				
				File file = dialog.getSelectedFile();
				if(!file.isDirectory()){
					JOptionPane.showMessageDialog(null, "Not a directory: "+file.getAbsolutePath(), "alert", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try {
					writeDB(file);
					System.out.println("Writting successfully into: "+file.getAbsolutePath());
					//JOptionPane.showInternalMessageDialog(null, "Writing to DB: "+file.getAbsolutePath(),"Writting Successfully!", JOptionPane.INFORMATION_MESSAGE); 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}});
		exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}});*/
		
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
	
	public static void readModel(File file) throws Exception{
		IModelReader im = new EcoreModelReader("Ecore_Reader");
		im.setInputStream(file);
		root = im.read();
		if(cache!=null)cache.clearObjectSpace();
		cache = new ObjectWorld(root);
		System.out.println("Model&World updated");
	}
	public static void readDataFromFiles(File[] fs) throws Exception{
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
	public static void writeDB(File db) throws Exception{
		OGResourceWriter writer = new XMLDBWriter(cache);
		writer.setResource(new OG_DirectoryImpl(db));
		writer.write();
	}
	public static void readDB(File db) throws Exception{
		XMLDBReader reader = new XMLDBReader();
		reader.setResource(new OG_DirectoryImpl(db));
		reader.read();
		
		cache = reader.getCache();
		root = cache.getModelSpace();
		
		System.out.println("DB Synchronization Complete!!!");
	}
	
}

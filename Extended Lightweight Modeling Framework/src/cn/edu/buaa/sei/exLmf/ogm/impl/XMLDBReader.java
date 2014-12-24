package cn.edu.buaa.sei.exLmf.ogm.impl;

import java.io.File;
import java.util.Map;
import java.util.Set;

import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;
import cn.edu.buaa.sei.exLmf.ogm.IObjectGroup;
import cn.edu.buaa.sei.exLmf.ogm.IObjectWorld;
import cn.edu.buaa.sei.exLmf.ogm.OGResource;
import cn.edu.buaa.sei.exLmf.ogm.OGResourceReader;
import cn.edu.buaa.sei.exLmf.ogm.OG_Directory;
import cn.edu.buaa.sei.exLmf.translater.EcoreModelReader;
import cn.edu.buaa.sei.exLmf.translater.IModelReader;

public class XMLDBReader implements OGResourceReader{
	
	OG_Directory resource;
	IObjectWorld cache=null;
	XMLFileReader reader;
	
	public XMLDBReader(){
	}
	
	@Override
	public IObjectWorld getCache() {return this.cache;}

	@Override
	public void setResource(OGResource resource) throws Exception {
		if(resource==null)throw new Exception("Null resource is invalid");
		if(!(resource instanceof OG_Directory))throw new Exception("OG_Directory required");
		this.resource = (OG_Directory) resource;
	}
	@Override
	public OGResource getResource() {return this.resource;}

	@Override
	public void read() throws Exception {
		if(this.resource==null)throw new Exception("Null resource is invalid");
		this.loadModel();
		
		Map<LClass,IObjectGroup> groups = this.cache.getGroups();
		Set<LClass> keys = groups.keySet();
		for(LClass key:keys){
			String file_name = this.getFileName(key);
			File fi = new File(this.resource.getDirectory().getAbsoluteFile()+"\\"+file_name+".xml");
			if(!fi.exists()){
				System.err.println("!Error!: "+key.getAbsolutePath()+" has no database set referred...");
				continue;
			}
			
			reader.setResource(new OG_XMLFile(fi));
			reader.readRoot();reader.analysis();reader.updateReference();
			System.out.println(fi.getAbsolutePath()+" analysis complete...");
		}
		System.out.println("Initial Analysis Completed...");
		
		for(LClass key:keys){
			String file_name = this.getFileName(key);
			File fi = new File(this.resource.getDirectory().getAbsoluteFile()+"\\"+file_name+".xml");
			if(!fi.exists()){
				System.err.println("!Error!: "+key.getAbsolutePath()+" has no database set referred...");
				continue;
			}
			
			reader.setResource(new OG_XMLFile(fi));
			reader.readRoot();reader.link();
			System.out.println(fi.getAbsolutePath()+" analysis complete...");
		}
		System.out.println("Linking Files Completed...");
		
	}
	@Override
	public void link() throws Exception {
		if(this.resource==null)throw new Exception("Null resource is invalid");
		if(this.cache==null)throw new Exception("Unready for linking...{Null Cache}");
		
		Map<LClass,IObjectGroup> groups = this.cache.getGroups();
		Set<LClass> keys = groups.keySet();
		for(LClass key:keys){
			String file_name = this.getFileName(key);
			File fi = new File(this.resource.getDirectory().getAbsoluteFile()+"\\"+file_name+".xml");
			if(!fi.exists()){
				System.err.println("!Error!: "+key.getAbsolutePath()+" has no database set referred...");
				continue;
			}
			
			reader.setResource(new OG_XMLFile(fi));
			reader.readRoot();reader.link();
			System.out.println(fi.getAbsolutePath()+" analysis complete...");
		}
		System.out.println("Linking Files Completed...");
	}

	protected void loadModel() throws Exception{
		File dir = this.resource.getDirectory();
		File mfile = new File(dir.getAbsoluteFile()+"\\model.ecore");
		
		if(!mfile.exists())throw new Exception("Model is undefined: %db%\\model.ecore is required");
		IModelReader mreader = new EcoreModelReader("EcoreReader");
		mreader.setInputStream(mfile);
		LPackage root = mreader.read();
		if(this.cache==null){
			this.cache = new ObjectWorld(root);
			this.reader = new XMLFileReader(this.cache);
		}
		else{this.cache.load(root);}
		System.out.println("Model have been reloaded into the cache...");
	}
	
	
	
	/*int links = 0;int count = 0, newOne = 0;
	protected void readRoot(File file) throws Exception{
		if(file==null)throw new Exception("Null file is invalid");
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(file);
		NodeList list = document.getElementsByTagName(OG_XMLFile.ROOT);
		
		if(list==null||list.getLength()!=1)
			throw new Exception("Invalid Structure: exactly 1 <root> is required in XML file: "+file.getAbsolutePath());
		
		this.root = (Element) list.item(0);
		System.out.println("XML Resource \""+file.getAbsolutePath()+"\" has been imported into JDOM...");
	}
	protected boolean analysis(File file){
		if(this.root==null)return false;
		this.count = 0; this.newOne = 0;
		
		NodeList tops = root.getChildNodes();
		boolean res = true;
		for(int i=0;i<tops.getLength();i++){
			if(tops.item(i) instanceof Element){
				Element ei = (Element) tops.item(i); count++;
				LClassObject val = this.generateObject(ei);
				if(val==null){
					System.err.println("Object Generation Failed at <"+ei.getTagName()+"> {"+count+"}");
					//return false;
					res = false;
					continue;
				}
				
				NodeList alist = ei.getChildNodes();
				for(int j=0;j<alist.getLength();j++){
					if(alist.item(j) instanceof Element){
						Element a_elm = (Element) alist.item(j);
						if(!this.addAttribute(val, a_elm)){
							System.err.println("Attribute Value Generation Failed at <"+a_elm.getTagName()+">");
							res = false;
							continue;
						}
					}
				}
			}
		}
		System.out.println(count+" elements are processed and "+this.newOne+
				" objects are created from file: "+file.getAbsolutePath());
		return res;
	}
	protected boolean updateReference(){
		if(this.root==null)return false;
		this.links = 0;
		
		NodeList tops = root.getChildNodes();
		boolean res = true;
		for(int i=0;i<tops.getLength();i++){
			if(tops.item(i) instanceof Element){
				Element ei = (Element) tops.item(i);
				LClassObject val = this.generateObject(ei);
				if(val==null){
					System.err.println("Object Generation Failed at <"+ei.getTagName()+"> {"+count+"}");
					//return false;
					res = false;
					continue;
				}
				
				NodeList rlist = ei.getChildNodes();
				for(int j=0;j<rlist.getLength();j++){
					if(rlist.item(j) instanceof Element){
						Element r_elm = (Element) rlist.item(j);
						if(!this.addReference(val, r_elm)){
							System.err.println("Reference Value Generation Failed at <"+r_elm.getTagName()+">");
							res = false;
							continue;
						}
					}
				}
			}
		}
		
		System.out.println(links+" references have been linked to the objects in cache...");
		return res;
	}
	
	protected LClassObject generateObject(Element ei){
		String tag = ei.getTagName();
		if(!this.cache.containModelClass(tag)){
			System.err.println("Error Tag Name <"+tag+"> for no such class is defined in template...");
			return null;
		}
		String id = ei.getAttribute(OG_XMLFile.ID);
		if(id==null){
			System.err.println("Error <"+tag+"> element: _id attribute is lost...");
			return null;
		}
		
		LClass type = null;
		try {
			type = this.cache.getModelClass(tag);
		} catch (Exception e1) {
			System.err.println("Getting Class failed: "+tag);
			return null;
		}
		if(type==null){
			System.err.println("Getting Class failed: "+tag);
			return null;
		}
		
		LClassObject val = null;
		try {
			IObjectGroup group = this.cache.getObjectGroup(tag);
			if(!group.isRegistered(id)){
				LClassObject obj = group.newOne();
				group.tag(id, obj);
				this.newOne++;
			}
			val = group.get(id);
			return val;
		} catch (Exception e) {
			System.err.println("Error happened at getting object of <"+tag+"> for: "+e.getMessage());
			return null;
		}
	}
	protected boolean addAttribute(LClassObject val,Element a_elm){
		String atag = a_elm.getTagName();
		LClass type = val.getType();
		
		LStructuralFeature f = type.getFeatureByName(atag);
		if(f==null){
			System.err.println("No attribute \""+atag+"\" is defined in class: "+type.getAbsolutePath());
			return false;
		}
		if(!(f instanceof LAttribute))return true;
		LAttribute ai = (LAttribute) f;
		
		if(f.getUpperBound()>1||f.getUpperBound()==LMultipleObject.UNBOUNDED){
			NodeList items = a_elm.getElementsByTagName(OG_XMLFile.ITEM);
			boolean res = true;
			for(int k=0;k<items.getLength();k++){
				Element item_elm = (Element) items.item(k);
				String code = item_elm.getTextContent();
				
				try {
					LDataObject item_val = type.getContainer().getFactory().create(ai.getDataType(), code);
					val.add(ai, item_val);
				} catch (Exception e) {
					System.err.println("Attribute Value Generated Failed: "+code+" in <__item>{"+k+"}");
					res = false;
					continue;
				}
			}
			
			return res;
		}
		else{
			String code = a_elm.getTextContent();
			try {
				LDataObject aval = type.getContainer().getFactory().create(ai.getDataType(), code);
				val.set(ai, aval);
				return true;
			} catch (Exception e) {
				System.err.println("Attribute Value Generated Failed: "+code+" in <"+atag+">");
				return false;
			}
		}
	}
	protected boolean addReference(LClassObject val,Element r_elm){
		String atag = r_elm.getTagName();
		LClass type = val.getType();
		
		IObjectGroup group = null;
		try {
			group = this.cache.getObjectGroup(type);
		} catch (Exception e) {
			System.err.println("Unknown Type {no object group is get}: "+type.getAbsolutePath());
			return false;
		}
		
		LStructuralFeature f = type.getFeatureByName(atag);
		if(f==null){
			System.err.println("No attribute \""+atag+"\" is defined in class: "+type.getAbsolutePath());
			return false;
		}
		if(!(f instanceof LReference))return true;
		LReference r = (LReference) f;
		
		if(r.getUpperBound()>1||r.getUpperBound()==LMultipleObject.UNBOUNDED){
			NodeList items = r_elm.getElementsByTagName(OG_XMLFile.ITEM);
			boolean res = true;
			for(int k=0;k<items.getLength();k++){
				Element item_elm = (Element) items.item(k);
				String code = item_elm.getTextContent();
				try {
					LClassObject item = this.getCompatibleGroup(r.getLClass(), code, group.getContainer());
					val.add(r, item);this.links++;
				} catch (Exception e) {
					System.err.println("Updating reference failed at <__item>{"+k+"} of <"+atag+">: Unknown ID {"+code+"}");
					res = false;
				}
			}
			
			return res;
		}
		else{
			String code = r_elm.getTextContent();
			try {
				LClassObject item = this.getCompatibleGroup(r.getLClass(), code, group.getContainer());
				val.set(r, item); this.links++;
			} catch (Exception e) {
				System.err.println("Updating reference failed: Unknown ID {"+code+"}");
				return false;
			}
		}
		
		return true;
	}
	protected LClassObject getCompatibleGroup(LClass type,String id,IObjectWorld context) throws Exception{
		if(type==null||id==null||context==null)throw new Exception("Null type|id|cache is invalid");
		
		Queue<LClass> queue = new LinkedList<LClass>();
		queue.add(type);
		Set<LClass> records = new HashSet<LClass>();
		
		while(!queue.isEmpty()){
			type = queue.poll();
			if(records.contains(type))continue;
			records.add(type);
			
			if(context.containModelClass(type)){
				IObjectGroup group = context.getObjectGroup(type);
				if(group.isRegistered(id))return group.get(id);
			}
			
			queue.addAll(type.getSubClasses());
		}
		return null;
	}*/
	
	protected String getTypeName(LClass type){
		String type_name = type.getAbsolutePath();
		int k = type_name.indexOf(".");
		if(k>=0)type_name = type_name.substring(k+1).trim();
		else type_name = type_name.trim();
		
		return type_name;
	}
	protected String getFileName(LClass type){
		String type_name = this.getTypeName(type);
		if(type_name==null)return null;
		return type_name.replaceAll("\\.", "_");
	}
}

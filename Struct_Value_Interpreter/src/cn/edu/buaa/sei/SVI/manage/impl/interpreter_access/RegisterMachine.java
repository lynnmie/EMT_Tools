package cn.edu.buaa.sei.SVI.manage.impl.interpreter_access;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import cn.edu.buaa.sei.SVI.interpreter.core.Interpreter;
import cn.edu.buaa.sei.SVI.manage.InterpreterClassLib;
import cn.edu.buaa.sei.SVI.manage.InterpreterRegisterMachine;
import cn.edu.buaa.sei.SVI.manage.SVIResource;
import cn.edu.buaa.sei.SVI.manage.StructClassLib;
import cn.edu.buaa.sei.SVI.manage.StructInterpreterClassLinker;
import cn.edu.buaa.sei.SVI.manage.impl.SVIStream;
import cn.edu.buaa.sei.SVI.struct.core.Struct;

public class RegisterMachine implements InterpreterRegisterMachine{
	
	public static final String XMLROOT = "RegisterMap";
	public static final String XMLPAIR = "Pair";
	public static final String XMLSTRUCT = "Struct";
	public static final String XMLINTERPRETER = "Interpreter";
	
	public static InterpreterRegisterMachine rm = new RegisterMachine();
	public static InterpreterRegisterMachine getMachine(){return rm;}
	
	static{
		SVIStream resource = new SVIStream();
		resource.setInputStream(Test.class.getClassLoader().getResourceAsStream("config/regist.xml"));
		try {
			rm.loadRegisterMap(resource, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Library loading complete...");
	}
	
	StructClassLib slib = StructLib.getStructLibrary();
	InterpreterClassLib ilib = SInterpreterLib.getLibrary();
	StructInterpreterClassLinker linker = new ClassLinker();

	@Override
	public StructClassLib getStructClassLibrary() {return this.slib;}
	@Override
	public InterpreterClassLib getInterpreterClassLibrary() {return this.ilib;}
	@Override
	public StructInterpreterClassLinker getLinker() {return this.linker;}

	@SuppressWarnings("rawtypes")
	@Override
	public void loadRegisterMap(SVIResource resource, boolean force)
			throws Exception {
		if(resource==null)throw new Exception("Null resource is invalid");
		if(resource instanceof SVIStream){
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			Element root=null;
			try{
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.parse(((SVIStream) resource).getInputStream());
				root = (Element) doc.getElementsByTagName(XMLROOT).item(0);
			}
			catch(Exception ex){
				System.err.println(ex.getMessage());
				System.err.println("XML Interpretation Failed, the program try to exit...");
				return;
			}
			
			NodeList pairs = root.getElementsByTagName(XMLPAIR);
			for(int i=0;i<pairs.getLength();i++){
				Element pair = (Element) pairs.item(i);
				NodeList slist = pair.getElementsByTagName(XMLSTRUCT);
				NodeList ilist = pair.getElementsByTagName(XMLINTERPRETER);
				if(slist==null||slist.getLength()!=1){
					System.err.println("Structure Error: exactly 1 <"+XMLSTRUCT+"> required at <"+pair.getTagName()+">");
					continue;
				}
				if(ilist==null||ilist.getLength()!=1){
					System.err.println("Structure Error: exactly 1 <"+XMLINTERPRETER+"> required at <"+pair.getTagName()+">");
					continue;
				}
				
				String struct_name = slist.item(0).getTextContent();
				String interpreter_name = ilist.item(0).getTextContent();
				Class stype = null;
				Class itype = null;
				
				try{
					stype = Class.forName(struct_name);
				}catch(Exception ex){
					System.err.println(ex.getMessage());
					System.err.println(struct_name+" is not found in current project");
					continue;
				}
				
				try{
					itype = Class.forName(interpreter_name);
				}catch(Exception ex){
					System.err.println(ex.getMessage());
					System.err.println(interpreter_name+" is not found in current project");
					continue;
				}
				
				try{
					this.slib.load(stype);
				}catch(Exception ex){
					System.err.println(ex.getMessage());
					System.err.println("Loading class {"+stype.getCanonicalName()+"} failed");
					continue;
				}
				
				try{
					this.ilib.load(itype);
				}catch(Exception ex){
					System.err.println(ex.getMessage());
					System.err.println("Loading class {"+itype.getCanonicalName()+"} failed");
					continue;
				}
				
				if(!force){
					try{
						this.linker.link(stype, itype);
					}catch(Exception ex){
						System.err.println(ex.getMessage());
						System.err.println("Try to overwrite original link");
					}
				}
				else{
					this.linker.Flink(stype, itype);
				}
			}
			
		}
		else{
			throw new Exception("SVIStream required");
		}
	}

	@Override
	public Interpreter getInterpreter(Struct element) throws Exception {
		if(element==null)throw new Exception("Null element is invalid");
		return this.getInterpreter(element.getClass());
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Interpreter getInterpreter(Class stype) throws Exception {
		if(stype==null)throw new Exception("Null Struct Class is invalid");
		if(!this.slib.isLoaded(stype))
			throw new Exception(stype.getCanonicalName()+" has not been loaded");
		
		Class itype = null;
		try{itype = this.linker.getTarget(stype);}
		catch(Exception ex){itype=null;}
		
		if(itype==null)throw new Exception(stype.getCanonicalName()+" links to no classes");
		if(!this.ilib.isLoaded(itype))
			throw new Exception(itype.getCanonicalName()+" has not been loaded");
		
		return this.ilib.createInterpreter(itype);
	}
	@Override
	public Interpreter get(Struct element) throws Exception {return this.getInterpreter(element);}
	@SuppressWarnings("rawtypes")
	@Override
	public Interpreter get(Class stype) throws Exception {return this.getInterpreter(stype);}

}

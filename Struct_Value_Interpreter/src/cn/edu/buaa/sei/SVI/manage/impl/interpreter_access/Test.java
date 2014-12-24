package cn.edu.buaa.sei.SVI.manage.impl.interpreter_access;

import java.util.Set;

import cn.edu.buaa.sei.SVI.manage.impl.SVIStream;

public class Test {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		SVIStream resource = new SVIStream();
		resource.setInputStream(Test.class.getClassLoader().getResourceAsStream("config/regist.xml"));
		RegisterMachine rm = new RegisterMachine();
		try {
			rm.loadRegisterMap(resource, false);
			
			Set<Class> scs = rm.getInterpreterClassLibrary().getLoadedInterpreters();
			for(Class stype:scs)
				System.out.println(stype.getCanonicalName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

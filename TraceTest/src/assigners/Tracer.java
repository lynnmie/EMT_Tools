package assigners;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import cn.edu.buaa.sei.exLmf.metamodel.LClassObject;
import cn.edu.buaa.sei.exLmf.metamodel.LMultipleObject;
import cn.edu.buaa.sei.exLmf.metamodel.LObject;

public class Tracer {
	public static Set<LClassObject> SR2HLR(Set<LClassObject> srs) throws Exception{
		Set<LClassObject> hlrs = new HashSet<LClassObject>();
		
		for(LClassObject sr:srs){
			LMultipleObject hs = (LMultipleObject) sr.get(sr.getType().getFeatureByName("hlrs"));
			Collection<LObject> hss=hs.getAllObjects();
			for(LObject obj:hss)
				hlrs.add((LClassObject) obj);
		}
		
		return hlrs;
	}
	public static Set<LClassObject> HLR2LLR(Set<LClassObject> hlrs) throws Exception{
		Set<LClassObject> llrs = new HashSet<LClassObject>();
		
		for(LClassObject hlr:hlrs){
			LMultipleObject ls = (LMultipleObject) hlr.get(hlr.getType().getFeatureByName("llrs"));
			Collection<LObject> lss=ls.getAllObjects();
			for(LObject obj:lss)
				llrs.add((LClassObject) obj);
		}
		
		return llrs;
	}
	public static Set<LClassObject> LLR2Design(Set<LClassObject> llrs) throws Exception{
		Set<LClassObject> designs = new HashSet<LClassObject>();
		
		for(LClassObject sr:llrs){
			LMultipleObject hs = (LMultipleObject) sr.get(sr.getType().getFeatureByName("designs"));
			Collection<LObject> hss=hs.getAllObjects();
			for(LObject obj:hss)
				designs.add((LClassObject) obj);
		}
		
		return designs;
	}
	public static Set<LClassObject> Design2Func(Set<LClassObject> designs) throws Exception{
		Set<LClassObject> functions = new HashSet<LClassObject>();
		
		for(LClassObject sr:designs){
			LMultipleObject hs = (LMultipleObject) sr.get(sr.getType().getFeatureByName("functions"));
			Collection<LObject> hss=hs.getAllObjects();
			for(LObject obj:hss)
				functions.add((LClassObject) obj);
		}
		
		return functions;
	}
	public static Set<LClassObject> UTestCase2Func(Set<LClassObject> utcs) throws Exception{
		Set<LClassObject> functions = new HashSet<LClassObject>();
		
		for(LClassObject sr:utcs){
			LClassObject target = (LClassObject) sr.get(sr.getType().getFeatureByName("target"));
			functions.add((LClassObject) target);
		}
		
		return functions;
	}
	public static Set<LClassObject> Coverage2Func(Set<LClassObject> coverages) throws Exception{
		Set<LClassObject> functions = new HashSet<LClassObject>();
		
		for(LClassObject sr:coverages){
			LClassObject target = (LClassObject) sr.get(sr.getType().getFeatureByName("target"));
			if(target==null)continue;
			functions.add((LClassObject) target);
		}
		
		return functions;
	}
	public static Set<LClassObject> CTF2LLR(Set<LClassObject> ctfs) throws Exception{
		Set<LClassObject> llrs = new HashSet<LClassObject>();
		
		for(LClassObject sr:ctfs){
			/*LMultipleObject testcases = (LMultipleObject) sr.get(sr.getType().getFeatureByName("testcases"));
			if(testcases.getAllObjects().isEmpty())continue;*/
			
			LClassObject target = (LClassObject) sr.get(sr.getType().getFeatureByName("llr"));
			if(target==null)continue;
			llrs.add(target);
		}
		
		return llrs;
	}
	public static Set<LClassObject> CTF2CTC(Set<LClassObject> ctfs) throws Exception{
		Set<LClassObject> ctcs = new HashSet<LClassObject>();
		
		for(LClassObject sr:ctfs){
			LMultipleObject hs = (LMultipleObject) sr.get(sr.getType().getFeatureByName("testcases"));
			Collection<LObject> hss=hs.getAllObjects();
			for(LObject obj:hss)
				ctcs.add((LClassObject) obj);
		}
		
		return ctcs;
	}
	
	
	public static boolean traceable(LClassObject target,Set<LClassObject> domain,String attr) throws Exception{
		if(target==null)return false;
		
		LMultipleObject members = (LMultipleObject) target.get(target.getType().getFeatureByName(attr));
		Collection<LObject> set = members.getAllObjects();
		for(LObject obj:set){
			if(domain.contains(obj))return true;
		}
		
		// strong
		LClassObject cur = target;
		while(cur!=null){
			cur = (LClassObject) cur.get(cur.getType().getFeatureByName("parent"));
			members = (LMultipleObject) target.get(target.getType().getFeatureByName(attr));
			set = members.getAllObjects();
			for(LObject obj:set){
				if(domain.contains(obj))return true;
			}
		}
		
		LMultipleObject children = (LMultipleObject) target.get(target.getType().getFeatureByName("children"));
		if(children!=null){
			set = children.getAllObjects();
			if(set.isEmpty())return false;
			for(LObject obj:set){
				if(!traceable((LClassObject)obj,domain,attr))return false;
			}
			return true;
		}
		return false;
	}
	
	public static boolean hlr2LLR(LClassObject hlr,Set<LClassObject> llrs) throws Exception{
		return traceable(hlr,llrs,"llrs");
	}
	public static boolean llr2Design(LClassObject llr,Set<LClassObject> designs) throws Exception{
		return traceable(llr,designs,"designs");
	}
	public static boolean design2Function(LClassObject design,Set<LClassObject> functions) throws Exception{
		return traceable(design,functions,"functions");
	}
	public static boolean func2UTC(LClassObject function,Set<LClassObject> utcs) throws Exception{
		return UTestCase2Func(utcs).contains(function);
	}
	public static boolean UTC2UTR(LClassObject utc)throws Exception{
		return utc.get(utc.getType().getFeatureByName("result"))!=null;
	}
	public static boolean llr2CTF(LClassObject llr,Set<LClassObject> ctfs) throws Exception{
		return CTF2LLR(ctfs).contains(llr);
	}
	public static boolean ctf2CTR(LClassObject ctf) throws Exception{
		LMultipleObject ctcs = (LMultipleObject) ctf.get(ctf.getType().getFeatureByName("testcases"));
		return ctcs!=null&&!ctcs.getAllObjects().isEmpty();
	}
	
	
	public static Set<LClassObject> retrace(Set<LClassObject> src,Set<LClassObject> domain,String attr) throws Exception{
		Set<LClassObject> trg = new HashSet<LClassObject>();
		
		for(LClassObject t:domain)
			if(traceable(t,src,attr))
				trg.add(t);
		
		return trg;
	}
	public static Set<LClassObject> HLR2SR(Set<LClassObject> hlrs,Set<LClassObject> domain) throws Exception{
		return retrace(hlrs,domain,"hlrs");
	}
	public static Set<LClassObject> LLR2HLR(Set<LClassObject> llrs,Set<LClassObject> domain) throws Exception{
		return retrace(llrs,domain,"llrs");
	}
	public static Set<LClassObject> Design2LLR(Set<LClassObject> designs,Set<LClassObject> domain) throws Exception{
		return retrace(designs,domain,"designs");
	}
	public static Set<LClassObject> Function2Design(Set<LClassObject> functions,Set<LClassObject> domain) throws Exception{
		return retrace(functions,domain,"functions");
	}
	
	
}

package cn.edu.buaa.sei.exLmf.manager.impl;

import java.util.List;

import cn.edu.buaa.sei.exLmf.manager.IModelCreator;
import cn.edu.buaa.sei.exLmf.manager.IModelModifier;
import cn.edu.buaa.sei.exLmf.metamodel.LAttribute;
import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LEnum;
import cn.edu.buaa.sei.exLmf.metamodel.LEnumLiteral;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;
import cn.edu.buaa.sei.exLmf.metamodel.LReference;

public class ModelModifierImpl implements IModelModifier{
	
	IModelCreator space;
	String name;
	
	public ModelModifierImpl(String name,IModelCreator space){
		this.name=name;
		this.space=space;
	}

	/**Tool Functions*/
	Boolean containSubPackage(LPackage parant,LPackage child){
		if(parant==null||child==null)return false;
		if(parant.getSubPackages().contains(child)&&child.getContainer()==parant)
			return true;
		
		List<LPackage> subs = parant.getSubPackages();
		for(int i=0;i<subs.size();i++)
			if(this.containSubPackage(subs.get(i), child))
				return true;
		return false;
	}
	
	/**Core Functions
	 * @throws Exception */
	@Override
	public Boolean generalize(LClass parant, LClass child) throws Exception {
		if(parant==null||child==null)return false;
		if(parant.isSubOf(child))return false;
		if(parant.isSuperOf(child))return true;
		child.addSuperType(parant);
		return true;
	}
	@Override
	public Boolean removeGeneralize(LClass parant, LClass child) throws Exception {
		if(parant==null||child==null)return false;
		if(child.getSuperTypes().contains(parant)){
			child.removeSuperType(parant);
			return true;
		}
		return false;
	}

	@Override
	public Boolean appendAttribute(LClass type, LAttribute attribute) throws Exception {
		if(type==null||attribute==null)return false;
		if(attribute.getContainer()==type)return true;
		if(attribute.getContainer()!=null)
			this.removeAttribute((LClass) attribute.getContainer(), attribute);
		
		attribute.setContainer(type);
		type.addAttribute(attribute);
		return true;
	}
	@Override
	public Boolean appendReference(LClass type, LReference reference) throws Exception {
		if(type==null||reference==null)return false;
		if(type==reference.getContainer())return true;
		if(reference.getContainer()!=null)
			this.removeReference(type, reference);
		
		reference.setContainer(type);
		type.addReference(reference);
		return true;	
	}
	@Override
	public Boolean removeAttribute(LClass type, LAttribute attribute) throws Exception {
		if(type==null||attribute==null)return false;
		if(type!=attribute.getContainer())return false;
		
		type.removeAttribute(attribute);
		attribute.setContainer(null);
		return true;
	}
	@Override
	public Boolean removeReference(LClass type, LReference reference) throws Exception {
		if(type==null||reference==null)return false;
		if(type!=reference.getContainer())return false;
		
		type.removeReference(reference);
		reference.setContainer(null);
		return true;
	}

	@Override
	public Boolean appendLiteral(LEnum type, LEnumLiteral literal) throws Exception {
		if(type==null||literal==null)return false;
		if(type==literal.getContainer())return true;
		if(literal.getContainer()!=null)
			this.removeLiteral((LEnum) literal.getContainer(), literal);
		
		literal.setContainer(type);
		type.addLiteral(literal);
		return true;
	}
	@Override
	public Boolean removeLiteral(LEnum type, LEnumLiteral literal) throws Exception {
		if(type==null||literal==null)return false;
		if(type!=literal.getContainer())return false;
		
		type.removeLiteral(literal);
		literal.setContainer(null);
		return true;
	}

	@Override
	public Boolean appendClass(LPackage p, LClass type) throws Exception {
		if(p==null||type==null)return false;
		if(p==type.getContainer())return true;
		if(type.getContainer()!=null)
			this.removeClass(type.getContainer(), type);
		p.addType(type);
		return true;
	}
	@Override
	public Boolean removeClass(LPackage p, LClass type) throws Exception {
		if(p==null||type==null)return false;
		if(p!=type.getContainer())return false;
		
		p.removeType(type);
		type.setContainer(null);
		return true;
	}
	@Override
	public Boolean appendEnum(LPackage p, LEnum type) throws Exception {
		if(p==null||type==null)return false;
		if(p==type.getContainer())return true;
		
		p.addType(type);
		type.setContainer(p);
		return true;
	}
	@Override
	public Boolean removeEnum(LPackage p, LEnum type) throws Exception {
		if(p==null||type==null)return false;
		if(p!=type.getContainer())return false;
		
		p.removeType(type);
		type.setContainer(null);
		return true;
	}
	@Override
	public Boolean appendPackage(LPackage parant, LPackage child) throws Exception {
		if(parant==null||child==null)return null;
		if(parant==child.getContainer())return true;
		
		if(this.containSubPackage(parant, child))return false;
		parant.addSubPackage(child);
		child.setContainer(parant);
		return true;
	}
	@Override
	public Boolean removePackage(LPackage parant, LPackage child) throws Exception {
		if(parant==null||child==null)return false;
		if(child.getContainer()!=parant)return false;
		
		parant.removeSubPackage(child);
		child.setContainer(null);
		return true;
	}

	@Override
	public LReference addAssociation(String name, LClass type1, LClass type2,
			Boolean bi_direct) {
		if(name==null||type1==null||type2==null||bi_direct==null)return null;
		try {
			LReference r1 = this.space.createReference(type1, name+"_"+type2.getName(), type2);
			
			if(bi_direct){
				LReference r2 = this.space.createReference(type2, name+"_"+type1.getName(), type1);
				r1.setOpposite(r2);
				r2.setContainer(type2);
			}
			r1.setContainer(type1);
			return r1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Boolean removeAssociation(LReference ref) throws Exception {
		if(ref==null)return false;
		if(ref.getContainer()==null)return true;
		
		LClass type = (LClass) ref.getContainer();
		type.removeReference(ref);
		if(ref.getOpposite()!=null)
			ref.setOpposite(null);
		ref.setContainer(null);
		
		return true;
	}

}

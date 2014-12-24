package cn.edu.buaa.sei.exLmf.manager;

import cn.edu.buaa.sei.exLmf.metamodel.LAttribute;
import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LEnum;
import cn.edu.buaa.sei.exLmf.metamodel.LEnumLiteral;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;
import cn.edu.buaa.sei.exLmf.metamodel.LReference;

public interface IModelModifier {
	/***
	 * IModelModifier never throws any exceptions but false as result
	 * 1. generalize(parant,child)
	 * 	- description: to build the generalization between parant and child class.
	 * 	- conditions:
	 * 		- Null Arguments: return false;
	 * 		- child is super of parant: return false.
	 * 		- parant is super of child: return true. [do nothing]
	 * 		- !(child.isSuperOf(parant)&&!(parant.isSuperOf(child))): parant.addSuper(child)
	 * 2. removeGeneralize(parant,child)
	 * 	-description: remove the generalization between parant and child class
	 * 	- failure conditions:
	 * 		- Null Arguments
	 * 		- parant is not the direct super class of class child.
	 *  - execution:
	 *  	child.removeSuper(parant)
	 * 3. appendAttribute/appendReference
	 * 	- description: move an attribute/reference to another container class.
	 * 	- failure condition:
	 * 		- Null Argument
	 * 		- type == attribute.getContainer(): return true;
	 * 	- execution:
	 * 		- if attribute.getContainer()!=null && attribute.getContainer!=type: attribute.getContainer.removeAttribute(attribute)
	 * 		- type.addAttribute(attribute)
	 * 4. removeAttribute/removeReference()
	 * 	- description: remove attribute/reference from its container class.
	 * 	- failure conditions:
	 * 		- Null Arguments
	 * 		- attribute.getContainer()!=type: return false;
	 * 	- execution:
	 * 		- type.removeAttribute(attribute)
	 * 		- attribute.setContainer(null);
	 * 5. appendLiteral()
	 * 	- description: move literal from original enumeration to the target enumeration.
	 *  - failure conditions:
	 *  	- Null Arguments
	 *  	- type == literal.getContainer();
	 *  - execution:
	 *  	- type.addLiteral(literal);
	 *  	- if literal.getContainer()!=type && literal.getContainer()!=null:
	 *  		literal.getContainer.removeLiteral(literal);
	 *  	- literal.setContainer(type)
	 * 6. removeLiteral()
	 * 	- description: release the literal from its original container enumeration,
	 * 	- failure conditions:
	 * 		- Null Arguments
	 * 		- type != literal.getContainer(): it must be the container of the literal.
	 * 	- execution:
	 * 		- type.removeLiteral(literal)
	 * 		- literal.setContainer(null).
	 * 7. appendClass/appendEnum()
	 * 	- description: move a classifier from its original container to another new container package.
	 * 	- failure conditions:
	 * 		- Null Arguments
	 * 		- type.getContainer() == package: return true [do nothing]
	 * 	- execution:
	 * 		- if type.getContainer()!=package: this.removeClass(type.getContainer(),type)
	 * 		- package.addType(type);
	 * 8. removeClass/removeEnum():
	 * 	- description: release the type from its container pacakge.
	 * 	- failure condition:
	 * 		- Null Argument.
	 * 		- type.getContainer() != package: return false;
	 * 	- executions:
	 * 		- package.removeType(type)
	 * 		- type.setContainer(null)
	 * 9. appendPackage()
	 * 	- description: move the package from its original container to another container
	 * 	- failure conditions:
	 * 		- Null Arguments.
	 * 		- child.getContainer() == parant: return true. [do nothing]
	 * 	- executions:
	 * 		- if child.getContainer()!=null: this.removePackage(child.getContainer(),child);
	 * 		- parant.addSubPackage(child); child.setContainer(parant);
	 * 10. removePackage()
	 * 	- description: release the child package from its original container package and set the container as null.
	 * 11. addAssociation(name,type1,type2,bi_direct)
	 * 	- description: create an association {name} from type1 to type2 (if not bi-directional) or between type1 and type2 (if bi-directional)
	 * 	- failure condition:
	 * 		- Null Arguments
	 * 		- Name Conflict:
	 * 			- type1: contains reference with name "{name}_type2".
	 * 			- type2: [if bi-direct], contains with name "{name}_type1"
	 * 			- example: HLR -(trace)- LLR ==> addReference(HLR,"trace_LLR",LLR); addReference(LLR,"trace_HLR",HLR)
	 * 			- default: set the reference as (0..1)
	 * 	- execution:
	 * 		- addReference(type1,name+"_"+type2.getName(),type2);
	 * 		- if bi-direct: addReference(type2,name+"_"+type1.getName(),type1); r1.setOpposite(r2);
	 * @throws Exception 
	 * 
	 */
	public Boolean generalize(LClass parant,LClass child) throws Exception;
	public Boolean removeGeneralize(LClass parant,LClass child) throws Exception;
	
	public Boolean appendAttribute(LClass type,LAttribute attribute) throws Exception;
	public Boolean appendReference(LClass type,LReference reference) throws Exception;
	public Boolean removeAttribute(LClass type,LAttribute attribute) throws Exception;
	public Boolean removeReference(LClass type,LReference reference) throws Exception;
	public Boolean appendLiteral(LEnum type,LEnumLiteral literal) throws Exception;
	public Boolean removeLiteral(LEnum type,LEnumLiteral literal) throws Exception;
	
	public Boolean appendClass(LPackage p,LClass type) throws Exception;
	public Boolean removeClass(LPackage p,LClass type) throws Exception;
	public Boolean appendEnum(LPackage p,LEnum type) throws Exception;
	public Boolean removeEnum(LPackage p,LEnum type) throws Exception;
	public Boolean appendPackage(LPackage parant,LPackage child) throws Exception;
	public Boolean removePackage(LPackage parant,LPackage child) throws Exception;
	
	public LReference addAssociation(String name,LClass type1,LClass type2,Boolean bi_direct);
	public Boolean removeAssociation(LReference ref) throws Exception;
}

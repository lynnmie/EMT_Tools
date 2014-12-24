package cn.edu.buaa.sei.exLmf.manager;

import java.util.Collection;
import java.util.Set;

import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassObject;
import cn.edu.buaa.sei.exLmf.metamodel.LClassifier;
import cn.edu.buaa.sei.exLmf.metamodel.LDataObject;
import cn.edu.buaa.sei.exLmf.metamodel.LDataType;
import cn.edu.buaa.sei.exLmf.metamodel.LObject;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;

public interface IObjectSpace {
	/**
	 * 	IObjectSpace provide APIs to create/release/manage objects based on exLMF
	 * 	1. register(LPackage)
	 * 		- description: register the model of the object space so that all the objects created 
	 * 				in this system must be instance of types in specified package.
	 * 		- exception:
	 * 			- Null Model
	 * 		- specification:
	 * 			- Release all the objects in original model space.
	 * 			- Initial the space (clear) at first.
	 * 	2. isInstancable(type)
	 * 		- description: return true when type is one type in the package, and it is not abstract class, and it is not a Primitive Type.
	 * 		- condition for true:
	 * 			- Primitive Type: Integer...
	 * 			- Class in package and not abstract.
	 * 			- Enumeration in package.
	 * 	3. createClassObject(type)
	 * 		- description: generate a new object with new id in the space for the type in the model package.
	 * 		- condition:
	 * 			- type must be instancable <br>
	 * 			- create new object and put it into the objects set of specified type <br>
 	 * 			- the generated objects is put into space map. <integer,LObject> <br>
 	 * 	4. createDataObject(LDataType,String)
 	 * 		- description: generate a new data object based on the string code. but not put into space.
 	 *  5. removeObject(obj)
 	 *  	- description: only remove the object in object space (only class object).
 	 *  	- postcondition: the id is released.
	 * 	6. containObject(obj)/containObject(id)
	 * 		- description: return whether the object is in object space(only for class object) or the id of object is in space. <br>
	 * 	7. getIDof(obj)/getObject(id)
	 * 		- description: get id of object (if it is in the space), and get object based on id (if there is such id).
	 * 	8. getObjects(LClass)
	 * 		- description: get all objects for the type in space.
	 * 	9. getAllObjects()
	 * 		- description: get all the generated objects in the space.
	 * 	10. clearSpace()
	 * 		- description: clear the object space.
	 * */
	public void register(LPackage p);
	
	public Boolean isInstancable(LClassifier type);
	public LClassObject createClassObject(LClass type) throws Exception;
	public LDataObject createDataObject(LDataType type,String code) throws Exception;
	public Boolean removeObject(LObject obj);
	
	public Boolean containObject(LObject obj);
	public Boolean containObject(int id);
	
	public Integer getIDof(LObject obj);
	public LObject getObject(int id);
	public Set<LClassObject> getObjects(LClass type);
	public Collection<LClassObject> getAllObjects();
	
	public void clearSpace();
}

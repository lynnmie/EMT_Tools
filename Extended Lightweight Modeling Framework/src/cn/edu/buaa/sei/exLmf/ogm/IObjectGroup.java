package cn.edu.buaa.sei.exLmf.ogm;

import java.util.Map;
import java.util.Set;

import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassObject;

/**
 * IObjectGroup is an manager to manage group of objects of exLMF.<br>
 * There are 2 space to stored object in IObjectGroup: <b>No-Name Space {Universal Space}</b>
 * and <b>Name Space {ID Space}</b>. Name Space is included by Universal Space.<br>
 * Any Object are created in No-Name Space, and any object that tried to be added in
 * Name Space would be sured about their position in No-Name Space.
 * */
public interface IObjectGroup {
	/**
	 * Return the type.
	 * */
	public LClass getType();
	/**
	 * No-Name Space {no ID}: all the objects are created and stored in a memory part
	 * in which no id is tagged with them. If needed, it would be added to name-space
	 * in which a unique id is tagged with it.
	 */
	public LClassObject newOne() throws Exception;
	/**
	 * Delete the object from no-name space and name-space if possible.
	 * @exception Exception the val is not in the no-name space
	 * */
	public void delete(LClassObject val) throws Exception;
	/**
	 * Checking whether the object is in the No-Name Space.
	 * */
	public boolean in(LClassObject val);
	/**
	 * Remove all the object from the group {and name-space}
	 * */
	public void clearGroup();
	
	/**
	 * Tag an object with a unique id.<br>
	 * 1) id==null || val==null: throw exception;<br>
	 * 2) there existing object tagged with id: throw exception<br>
	 * 3) val is not in the No-Name Space: add(val,No-Name); add(val,Name,id);<br>
	 * 4) val is in No-Name but not in Name: add(val,Name);<br>
	 * 5) val is in Name but val.id != id: changeToNewID(val,id).<br>
	 * */
	public void tag(String id,LClassObject val) throws Exception;
	/**
	 * Checking whether an object is in the Name-Space.
	 * */
	public boolean isTagged(LClassObject val);
	/**
	 * Return whether a specifier id is in the Name-Space
	 * */
	public boolean isRegistered(String id);
	/**
	 * Return the id of a specified object.
	 * @exception Exception val==null||!this.isTagged(val)
	 * */
	public String getTag(LClassObject val) throws Exception;
	/**
	 * Return the object that is in current Name-Space with specified id.
	 * */
	public LClassObject get(String id) throws Exception;
	/**
	 * Remove a specified object from Name-Space, and return its id as result.
	 * @exception Exception val==null||!this.isTagged(val)
	 * */
	public String untag(LClassObject val) throws Exception;
	/**
	 * Remove all the objects in Name-Space
	 * */
	public void clearNameSpace();
	
	/**
	 * Return all objects {Name}
	 * */
	public Map<String,LClassObject> getNSpace();
	/**
	 * Return all objects {No-Name + Name}
	 * */
	public Set<LClassObject> getObjects();
	/**
	 * Return the container of the group: IObjectWorld
	 * */
	public IObjectWorld getContainer();
}

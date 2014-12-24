package cn.edu.buaa.sei.SVI.struct.group.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import cn.edu.buaa.sei.SVI.struct.group.Group;

public class SetGroup implements Group{
	Set<Object> set = new HashSet<Object>();

	@Override
	public int size() {return this.set.size();}
	@Override
	public Boolean contains(Object val) {return this.set.contains(val);}

	@Override
	public void add(Object obj) {this.set.add(obj);}
	@Override
	public void remove(Object obj) {this.set.remove(obj);}
	@Override
	public void addAll(Group grp) {
		if(grp==null)return;
		Iterator<Object> itor = grp.iterator();
		while(itor.hasNext())
			this.add(itor.next());
	}
	@Override
	public void removeAll(Group grp) {
		if(grp==null)return;
		Iterator<Object> itor = grp.iterator();
		while(itor.hasNext())
			this.remove(itor.next());
	}

	@Override
	public Iterator<Object> iterator() {return this.set.iterator();}
	
}

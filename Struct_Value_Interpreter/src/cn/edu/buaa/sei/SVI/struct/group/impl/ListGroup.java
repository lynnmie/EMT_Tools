package cn.edu.buaa.sei.SVI.struct.group.impl;

import java.util.Iterator;

import cn.edu.buaa.sei.SVI.struct.group.Group;

public class ListGroup implements Group{
	static final int MAXSIZE = 4048*4;
	int length=0;
	Object[] list=new Object[MAXSIZE];

	@Override
	public int size() {return this.length;}
	@Override
	public Boolean contains(Object val) {
		int t = this.search(val, 0);
		return t>=0;
	}

	@Override
	public void add(Object obj) {
		if(this.contains(obj))return;
		else{
			if(this.length>=this.list.length)
				try {
					throw new Exception("Out of Limitation: "+this.length);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			
			this.list[this.length++]=obj;
			this.adjust();
		}
	}
	@Override
	public void remove(Object obj) {
		int t = this.search(obj, 0);
		if(t<0)return;
		
		this.list[t]=this.list[this.length-1];
		this.adjust2(t);
		this.length--;
	}
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
	public Iterator<Object> iterator() {
		try {
			return new _Iter(this);
		} catch (Exception e) {
			return null;
		}
	}
	
	protected int compare(Object a,Object b){
		if(a==null){
			if(b==null)return 0;
			else return -1;
		}
		else if(b==null)return 1;
		else{
			if(a.hashCode()==b.hashCode())return 0;
			else if(a.hashCode()>b.hashCode())return 1;
			else return -1;
		}
	}
	protected int search(Object val,int start){
		if(start>=this.length)return -1;
		int code = this.compare(val, this.list[start]);
		if(code==0)return start;
		else if(code>0)return -1;
		else{
			int k = this.search(val, start*2+1);
			if(k<0)
				k = this.search(val, start*2+2);
			return k;
		}
	}
	protected void adjust(){
		int p = this.length-1;
		while(p>0){
			int code = this.compare(this.list[p],this.list[(p-1)/2]);
			if(code>0)return;
			else{
				Object t = this.list[p];
				this.list[p]=this.list[(p-1)/2];
				this.list[(p-1)/2]=t;
			}
			p=(p-1)/2;
		}
	}
	protected void adjust2(int p){
		Object temp = this.list[p];
		int c = 2*p+1;
		while(c<this.length){
			if(c<this.length-1&&this.compare(this.list[c],this.list[c+1])>0)c++;
			
			if(this.compare(this.list[p], this.list[c])<=0)break;
			else{
				this.list[p]=this.list[c];
				p=c;
				c = 2*p+1;
			}
		}
		this.list[p]=temp;
	}
	
	static class _Iter implements Iterator<Object>{
		
		ListGroup grp;
		int cur = 0;
		
		_Iter(ListGroup grp) throws Exception{
			if(grp==null)
				throw new Exception("Null ListGroup is invalid");
			this.grp=grp;
		}
		
		@Override
		public boolean hasNext() {return this.cur<this.grp.length;}

		@Override
		public Object next() {
			if(this.cur>=this.grp.length)return null;
			else return this.grp.list[this.cur++];
		}

		@Override
		public void remove() {
			if(this.cur>=this.grp.length)return;
			else this.grp.remove(this.grp.list[this.cur]);
		}
	}
}

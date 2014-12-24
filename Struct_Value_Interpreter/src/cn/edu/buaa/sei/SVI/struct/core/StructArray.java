package cn.edu.buaa.sei.SVI.struct.core;


/**
 * StructArray is a Tool-Class used for managing the children of each <i>CompositeStruct</i> Object.
 * */
public class StructArray implements CompositeStruct{
	public static final int INIT_SIZE = 16;
	public static final int TIMES = 2;
	public static final int MAX_SIZE = 256;
	
	int length=0;
	Struct[] children=new Struct[INIT_SIZE];
	
	public boolean containChildStruct(Struct child){
		if(child==null)return false;
		for(int i=0;i<this.length;i++)
			if(this.children[i]==child)
				return true;
		return false;
	}
	public void addChildStruct(Struct child) throws Exception {
		if(child==null)throw new Exception("Null Child is invalid to be added into Array of Structs");
		if(this.containChildStruct(child))return;
		
		if(this.length>=MAX_SIZE)
			throw new Exception("Out of Limitation: "+this.length);
		if(this.length>=children.length){
			Struct[] nc = new Struct[this.children.length*TIMES];
			for(int i=0;i<this.length;i++)
				nc[i]=this.children[i];
			this.children=nc;
		}
		
		this.children[this.length++]=child;
	}
	public void removeChildStruct(Struct child) throws Exception {
		if(child==null)
			throw new Exception("Null Child is invalid in the Array");
		
		int i;
		for(i=0;i<this.length;i++)
			if(this.children[i]==child)break;
		
		if(i>=this.length)
			throw new Exception("Struct child is not in the array.");
		
		while(i<this.length-1){
			this.children[i]=this.children[i+1];
			i++;
		}
		this.length--;
	}

	public Struct[] getChildrenStructs(){return this.children;}
	public int getChildrenStructSize(){return this.length;}
	
}

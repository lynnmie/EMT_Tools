package cn.edu.buaa.sei.SVI.manage.impl;

import java.io.InputStream;
import java.io.OutputStream;

import cn.edu.buaa.sei.SVI.manage.SVIResource;

public class SVIStream implements SVIResource{
	InputStream in;
	OutputStream out;
	
	public SVIStream(){}
	
	public void setInputStream(InputStream in){this.in=in;}
	public void setOutputStream(OutputStream out){this.out=out;}
	
	public InputStream getInputStream(){return this.in;}
	public OutputStream getOutputStream(){return this.out;}
}

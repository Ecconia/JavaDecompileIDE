package de.ecconia.java.decompileide.structure.bytecode;

import java.io.IOException;

import de.ecconia.java.decompileide.CustomDataInput;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;

public class Bytecode
{
	protected final String name;
	
	public Bytecode(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String[] parse(CustomDataInput d, ConstantPool pool) throws IOException
	{
		return new String[] {};
	}
}

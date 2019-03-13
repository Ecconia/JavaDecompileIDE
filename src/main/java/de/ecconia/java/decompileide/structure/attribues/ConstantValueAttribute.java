package de.ecconia.java.decompileide.structure.attribues;

import java.io.DataInput;
import java.io.IOException;

import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;

public class ConstantValueAttribute extends Attribute
{
	private final Object value;
	
	public ConstantValueAttribute(String name, DataInput d, ConstantPool pool) throws IOException
	{
		super(name);
		
		int length = d.readInt();
		if(length != 2)
		{
			throw new RuntimeException("Malformed ConstantValueAttribute, non-two content length.");
		}
		
		value = pool.getPrimitve(d.readUnsignedShort());
	}
	
	public Object getValue()
	{
		return value;
	}
	
	@Override
	public String toString()
	{
		return "ConstantValue{" + value + "}";
	}
}

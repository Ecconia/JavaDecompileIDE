package de.ecconia.java.decompileide.structure.attribues;

import java.io.DataInput;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;

public class CodeAttribute extends Attribute
{
	private final byte[] code;
	private final Map<String, Attribute> attributes = new HashMap<>();
	
	public CodeAttribute(String name, DataInput d, ConstantPool pool) throws IOException
	{
		super(name);
		
		d.readInt();
		
		d.readUnsignedShort();
		d.readUnsignedShort();
		
		code = new byte[d.readInt()];
		d.readFully(code);
		
		int exceptionTableLength = d.readUnsignedShort();
		for(int i = 0; i < exceptionTableLength; i++)
		{
			d.readUnsignedShort();
			d.readUnsignedShort();
			d.readUnsignedShort();
			int catcher = d.readUnsignedShort();
			if(catcher != 0)
			{
				pool.getClass(catcher);
			}
		}
		
		int attributesCount = d.readUnsignedShort();
		for(int i = 0; i < attributesCount; i++)
		{
			Attribute a = Attributes.parse(d, pool);
			attributes.put(a.getName(), a);
		}
	}
	
	public byte[] getCode()
	{
		return code;
	}
	
	public Map<String, Attribute> getAttributes()
	{
		return attributes;
	}
}

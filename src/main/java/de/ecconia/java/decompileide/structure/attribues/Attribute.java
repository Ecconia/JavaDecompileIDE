package de.ecconia.java.decompileide.structure.attribues;

import java.io.DataInput;
import java.io.IOException;

public class Attribute
{
	protected final String name;
	
	public Attribute(String name)
	{
		this.name = name;
	}
	
	public Attribute(String name, DataInput d) throws IOException
	{
		this(name);
		
		int attributeLength = d.readInt();
		for(int k = 0; k < attributeLength; k++)
		{
			d.readUnsignedByte();
		}
	}

	public String getName()
	{
		return name;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}

package de.ecconia.java.decompileide.structure.attribues;

import java.io.DataInput;
import java.io.IOException;

public class DeprecatedAttribute extends Attribute
{
	public DeprecatedAttribute(String name, DataInput d) throws IOException
	{
		super(name);
		
		if(d.readInt() != 0)
		{
			throw new RuntimeException("Malformed DeprecatedAttribute, non-zero content length.");
		}
	}
}

package de.ecconia.java.decompileide.structure.attribues;

import java.io.DataInput;
import java.io.IOException;

import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;

public class SourceFileAttribute extends Attribute
{
	private final String sourceFile;
	
	public SourceFileAttribute(String name, DataInput d, ConstantPool pool) throws IOException
	{
		super(name);
		
		int length = d.readInt();
		if(length != 2)
		{
			throw new RuntimeException("Malformed SourceFileAttribute, non-two content length.");
		}
		
		sourceFile = pool.getUtf8(d.readUnsignedShort());
	}
	
	@Override
	public String toString()
	{
		return name + '{' + sourceFile + "}";
	}
}

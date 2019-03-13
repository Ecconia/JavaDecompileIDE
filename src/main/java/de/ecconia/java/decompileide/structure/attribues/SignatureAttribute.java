package de.ecconia.java.decompileide.structure.attribues;

import java.io.DataInput;
import java.io.IOException;

import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;

public class SignatureAttribute extends Attribute
{
	private final String descriptor;
	
	public SignatureAttribute(String name, DataInput d, ConstantPool pool) throws IOException
	{
		super(name);
		
		int length = d.readInt();
		if(length != 2)
		{
			throw new RuntimeException("Malformed SignatureAttribute, non-two content length.");
		}
		
		descriptor = pool.getUTF(d.readUnsignedShort());
	}
	
	@Override
	public String toString()
	{
		return name + '{' + descriptor + "}";
	}
}
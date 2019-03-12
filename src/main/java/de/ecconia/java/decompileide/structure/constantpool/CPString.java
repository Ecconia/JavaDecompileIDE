package de.ecconia.java.decompileide.structure.constantpool;

import java.io.DataInput;
import java.io.IOException;

public class CPString extends ConstantPoolEntry
{
	private final int stringIndex;
	
	private String string;
	
	public CPString(DataInput d) throws IOException
	{
		stringIndex = d.readUnsignedShort();
	}
	
	@Override
	public void resolve(ConstantPool pool)
	{
		if(string == null)
		{
			string = pool.getUtf8(stringIndex);
		}
	}
	
	public int getStringIndex()
	{
		return stringIndex;
	}
	
	@Override
	public String toString()
	{
		return "String: " + string + "@" + stringIndex;
	}
}

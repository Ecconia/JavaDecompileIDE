package de.ecconia.java.decompileide.structure.constantpool.entrytypes.direct;

import java.io.DataInput;
import java.io.IOException;

import de.ecconia.java.decompileide.structure.constantpool.CPEntry;
import de.ecconia.java.decompileide.structure.constantpool.special.CPRawType;

public class CPTUTF extends CPEntry implements CPRawType
{
	private final String text;
	
	public CPTUTF(int index, DataInput d) throws IOException
	{
		super(index);
		
		text = d.readUTF();
	}
	
	@Override
	public Object getValue()
	{
		return null;
	}
	
	public String getText()
	{
		return text;
	}
	
	@Override
	public String toString()
	{
		return this.getClass().getSimpleName() + "{" + text + "}";
	}
}

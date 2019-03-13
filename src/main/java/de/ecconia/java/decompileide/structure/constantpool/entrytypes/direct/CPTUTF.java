package de.ecconia.java.decompileide.structure.constantpool.entrytypes.direct;

import java.io.DataInput;
import java.io.IOException;

import de.ecconia.java.decompileide.structure.constantpool.CPEntry;

public class CPTUTF extends CPEntry
{
	private final String text;
	
	public CPTUTF(int index, DataInput d) throws IOException
	{
		super(index);
		
		text = d.readUTF();
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

package de.ecconia.java.decompileide.structure.constantpool;

import java.io.DataInput;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class CPEntry
{
	private final int index;
	
	public CPEntry(int index)
	{
		this.index = index;
	}
	
	public int getIndex()
	{
		return index;
	}
	
	public static CPEntry parse(int poolIndex, DataInput d) throws IOException
	{
		int typeIndex = d.readUnsignedByte();
		CPTInfo info = CPTInfo.getCPTInfo(typeIndex);
		if(info == null)
		{
			//TODO: Custom exception:
			throw new RuntimeException("Could not find pool entry type '" + typeIndex + "' @" + poolIndex + ".");
		}
		
		try
		{
			return info.getConstructor().newInstance(poolIndex, d);
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException e)
		{
//			e.printStackTrace();
			throw new Error("Something went wrong constructing ConstantPoolEntry.", e);
		}
		catch(InvocationTargetException e)
		{
			if(e.getCause() instanceof IOException)
			{
				throw (IOException) e.getCause();
			}
			else
			{
				e.printStackTrace();
				throw new Error("Something went wrong constructing ConstantPoolEntry.");
			}
		}
	}
}

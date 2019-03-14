package de.ecconia.java.decompileide.structure.constantpool;

import java.io.DataInput;
import java.io.IOException;

import de.ecconia.java.decompileide.structure.constantpool.contypes.BootstrapMethod;
import de.ecconia.java.decompileide.structure.constantpool.contypes.ClassTypeName;
import de.ecconia.java.decompileide.structure.constantpool.contypes.TypeName;
import de.ecconia.java.decompileide.structure.constantpool.entrytypes.direct.CPTDouble;
import de.ecconia.java.decompileide.structure.constantpool.entrytypes.direct.CPTLong;
import de.ecconia.java.decompileide.structure.constantpool.entrytypes.direct.CPTUTF;
import de.ecconia.java.decompileide.structure.constantpool.entrytypes.first.CPTClass;
import de.ecconia.java.decompileide.structure.constantpool.entrytypes.first.CPTTypeName;
import de.ecconia.java.decompileide.structure.constantpool.entrytypes.second.CPTField;
import de.ecconia.java.decompileide.structure.constantpool.entrytypes.second.CPTInterfaceMethod;
import de.ecconia.java.decompileide.structure.constantpool.entrytypes.second.CPTInvokeDynamic;
import de.ecconia.java.decompileide.structure.constantpool.entrytypes.second.CPTMethod;
import de.ecconia.java.decompileide.structure.constantpool.resolver.Resolve;
import de.ecconia.java.decompileide.structure.constantpool.resolver.ResolveFirst;
import de.ecconia.java.decompileide.structure.constantpool.resolver.ResolveLater;
import de.ecconia.java.decompileide.structure.constantpool.resolver.ResolveLatest;
import de.ecconia.java.decompileide.structure.constantpool.special.CPBigValue;
import de.ecconia.java.decompileide.structure.constantpool.special.CPMethodMayInterface;
import de.ecconia.java.decompileide.structure.constantpool.special.CPPrimitive;
import de.ecconia.java.decompileide.structure.constantpool.special.CPSmallValue;

public class ConstantPool
{
	private final CPEntry[] entries;
	
	public ConstantPool(DataInput d) throws IOException
	{
		int entrieAmount = d.readUnsignedShort();
		entries = new CPEntry[entrieAmount];
		
		for(int i = 1; i < entrieAmount; i++)
		{
			CPEntry entry = CPEntry.parse(i, d);
			entries[i] = entry;
			if(entry instanceof CPTLong || entry instanceof CPTDouble)
			{
				//Long and Double take two indices.
				i++;
			}
		}
		
		//Resolve the entries depending on direct entries:
		for(CPEntry entry : entries)
		{
			if(entry instanceof ResolveFirst)
			{
				((Resolve) entry).resolve(this);
			}
		}
		
		//Resolve the entries depending on the first indirect entries:
		for(CPEntry entry : entries)
		{
			if(entry instanceof ResolveLater)
			{
				((Resolve) entry).resolve(this);
			}
		}
		
		//Resolve the entries which depend on 
		for(CPEntry entry : entries)
		{
			if(entry instanceof ResolveLatest)
			{
				((Resolve) entry).resolve(this);
			}
		}
		
//		for(int i = 1; i < entrieAmount; i++)
//		{
//			CPEntry entry = entries[i];
//			if(entry != null)
//			{
//				System.out.println(i + ": " + entry);
//			}
//		}
	}
	
	//#########################################################################
	
	private CPEntry getEntry(int index)
	{
		if(index >= entries.length || index < 0)
		{
			throw new RuntimeException("Tried to access constant pool outside of its capacity " + index + "/" + entries.length + ".");
		}
		
		CPEntry entry = entries[index];
		if(entry == null)
		{
			throw new RuntimeException("Tried to access constant pool field " + index + ", but found null.");
		}
		
		return entry;
	}
	
	@SuppressWarnings("unchecked")
	private <T> T getEntry(int index, Class<?> clazz)
	{
		CPEntry entry = getEntry(index);
		if(clazz.isInstance(entry))
		{
			return (T) entry;
		}
		
		throw new RuntimeException("Tried to get (" + clazz + ") from constant pool, but found " + entry.getClass().getSimpleName());
	}
	
	//#########################################################################
	
	/**
	 * Returns a bare string from the pool.
	 */
	public String getUTF(int index)
	{
		CPTUTF entry = getEntry(index, CPTUTF.class);
		return entry.getText();
	}
	
	/**
	 * Returns a class name from the pool.
	 */
	public String getClass(int index)
	{
		CPTClass entry = getEntry(index, CPTClass.class);
		return entry.getClazz();
	}
	
	/**
	 * Returns a name with its type the pool.
	 */
	public TypeName getTypeName(int index)
	{
		CPTTypeName entry = getEntry(index, CPTTypeName.class);
		return entry.getTypeName();
	}
	
	public ClassTypeName getField(int index)
	{
		CPTField entry = getEntry(index, CPTField.class);
		return entry.getData();
	}
	
	public ClassTypeName getMethod(int index)
	{
		CPTMethod entry = getEntry(index, CPTMethod.class);
		return entry.getData();
	}
	
	public ClassTypeName getInterfaceMethod(int index)
	{
		CPTInterfaceMethod entry = getEntry(index, CPTInterfaceMethod.class);
		return entry.getData();
	}
	
	public ClassTypeName getMethodOrInterfaceMethod(int index)
	{
		CPMethodMayInterface entry = getEntry(index, CPMethodMayInterface.class);
		return entry.getData();
	}
	
	public Object getPrimitve(int index)
	{
		CPPrimitive entry = getEntry(index, CPPrimitive.class);
		return entry.getValue();
	}
	
	public BootstrapMethod getBootstrapMethod(int index)
	{
		CPTInvokeDynamic entry = getEntry(index, CPTInvokeDynamic.class);
		return entry.getData();
	}
	
	public Object getBigValue(int index)
	{
		CPBigValue val = getEntry(index, CPBigValue.class);
		return val.getValue();
	}
	
	public Object getSmallValue(int index)
	{
		CPSmallValue val = getEntry(index, CPSmallValue.class);
		return val.getValue();
	}
}

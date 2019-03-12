package de.ecconia.java.decompileide.structure.constantpool;

import java.io.DataInput;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public enum ConstantPoolLib
{
	Utf8(1, CPUtf8.class),
	Integer(3, CPInteger.class),
	Float(4, CPFloat.class),
	Long(5, CPLong.class),
	Double(6, CPDouble.class),
	Class(7, CPClass.class),
	String(8, CPString.class),
	FieldRef(9, CPFieldRef.class),
	MethodRef(10, CPMethodRef.class),
	InterfaceMethodRef(11, CPInterfaceMethodRef.class),
	NameAndType(12, CPNameAndType.class),
	MethodHandle(15, CPMethodHandle.class),
	MethodType(16, CPMethodType.class),
	InvokeDynamic(18, CPInvokeDynamic.class),
	;
	
	private final Constructor<? extends ConstantPoolEntry> constructor;
	private final int tag;
	
	private ConstantPoolLib(int id, Class<? extends ConstantPoolEntry> clazz)
	{
		this.tag = id;
		
		try
		{
			constructor = clazz.getConstructor(new Class[] { DataInput.class });
		}
		catch(NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
			throw new Error("Something went wrong initializing ConstantPoolLib.");
		}
	}
	
	public Constructor<? extends ConstantPoolEntry> getConstructor()
	{
		return constructor;
	}
	
	public int getTag()
	{
		return tag;
	}
	
	private ConstantPoolEntry generateNew(DataInput d) throws IOException
	{
		try
		{
			return constructor.newInstance(d);
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException e)
		{
			e.printStackTrace();
			throw new Error("Something went wrong constructing ConstantPoolEntry.");
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
	
	// ### //
	
	private static Map<Integer, ConstantPoolLib> lib = new HashMap<>();
	
	static
	{
		for(ConstantPoolLib entry : values())
		{
			lib.put(entry.getTag(), entry);
		}
	}
	
	public static ConstantPoolEntry parse(DataInput d) throws IOException
	{
		int id = d.readUnsignedByte();
		ConstantPoolLib type = lib.get(id);
		
		if(type == null)
		{
			throw new RuntimeException("Oops constant pool type " + id + " cannot be handled.");
		}
		
		return type.generateNew(d);
	}
}

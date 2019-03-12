package de.ecconia.java.decompileide.structure.constantpool;

import java.io.DataInput;
import java.io.IOException;

public class CPMethodHandle extends ConstantPoolEntry
{
	private final int type;
	private final int index;
	
	private String className;
	private String name;
	private String descriptor;
	
	public CPMethodHandle(DataInput d) throws IOException
	{
		type = d.readUnsignedByte();
		index = d.readUnsignedShort();
	}
	
	@SuppressWarnings("unused")
	@Override
	public void resolve(ConstantPool pool)
	{
		if(className == null)
		{
			if(type == 1 || type == 2 || type == 3 || type == 4)
			{
				className = pool.getFieldRefClassName(index);
				name = pool.getFieldRefName(index);
				descriptor = pool.getFieldRefDescriptor(index);
			}
			else if(type == 5 || type == 8)
			{
				className = pool.getMethodRefClassName(index);
				name = pool.getMethodRefName(index);
				descriptor = pool.getMethodRefDescriptor(index);
			}
			else if(type == 6 || type == 7)
			{
				//TODO: Inject real version.
				final int tmpVersion = 52;
				if(tmpVersion < 52)
				{
					className = pool.getMethodRefClassName(index);
					name = pool.getMethodRefName(index);
					descriptor = pool.getMethodRefDescriptor(index);
				}
				else
				{
					className = pool.getMethodRefOrInterfaceMethodRefClassName(index);
					name = pool.getMethodRefOrInterfaceMethodRefName(index);
					descriptor = pool.getMethodRefOrInterfaceMethodRefDescriptor(index);
				}
			}
			else if(type == 9)
			{
				className = pool.getInterfaceMethodRefClassName(index);
				name = pool.getInterfaceMethodRefName(index);
				descriptor = pool.getInterfaceMethodRefDescriptor(index);
			}
		}
	}
	
	@Override
	public String toString()
	{
		return "MethodHandle: " + className + ":" + name + ":" + descriptor + "@" + index;
	}
}

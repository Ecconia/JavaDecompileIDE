package de.ecconia.java.decompileide.structure.constantpool;

import java.io.DataInput;
import java.io.IOException;

public class CPInterfaceMethodRef extends ConstantPoolEntry
{
	private final int classIndex;
	private final int nameAndTypeIndex;
	
	private String className;
	private String name;
	private String descriptor;
	
	public CPInterfaceMethodRef(DataInput d) throws IOException
	{
		classIndex = d.readUnsignedShort();
		nameAndTypeIndex = d.readUnsignedShort();
	}
	
	@Override
	public void resolve(ConstantPool pool)
	{
		if(className == null)
		{
			className = pool.getClassName(classIndex);
			name = pool.getNameAndTypeName(nameAndTypeIndex);
			descriptor = pool.getNameAndTypeType(nameAndTypeIndex);
		}
	}
	
	public String getClassName()
	{
		return className;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDescriptor()
	{
		return descriptor;
	}
	
	@Override
	public String toString()
	{
		return "InterfaceMethodRef: " + className + "@" + classIndex + " " + name + ":" + descriptor + "@" + nameAndTypeIndex;
	}
}

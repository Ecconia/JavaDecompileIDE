package de.ecconia.java.decompileide.structure.constantpool;

import java.io.DataInput;
import java.io.IOException;

public class CPInvokeDynamic extends ConstantPoolEntry
{
	private final int bootstrapMethodAttrIndex;
	private final int nameAndTypeIndex;
	
	//TODO: Resolve the bootstrap method, once they are parsed.
	private String name;
	private String descriptor;
	
	public CPInvokeDynamic(DataInput d) throws IOException
	{
		bootstrapMethodAttrIndex = d.readUnsignedShort();
		nameAndTypeIndex = d.readUnsignedShort();
	}
	
	@Override
	public void resolve(ConstantPool pool)
	{
		if(name == null)
		{
			name = pool.getNameAndTypeName(nameAndTypeIndex);
			descriptor = pool.getNameAndTypeType(nameAndTypeIndex);
		}
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
		return "InvokeDynamic: @" + bootstrapMethodAttrIndex + " " + name + ":" + descriptor + "@" + nameAndTypeIndex;
	}
}

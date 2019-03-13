package de.ecconia.java.decompileide.structure.constantpool.contypes;

import de.ecconia.java.decompileide.structure.descriptor.Descriptor;

public class BootstrapMethod
{
	private final int index;
	private final TypeName typeName;
	
	public BootstrapMethod(int index, TypeName typeName)
	{
		this.index = index;
		this.typeName = typeName;
	}
	
	public int getIndex()
	{
		return index;
	}
	
	public TypeName getTypeName()
	{
		return typeName;
	}
	
	public String getName()
	{
		return typeName.getName();
	}
	
	public Descriptor getType()
	{
		return typeName.getType();
	}
}

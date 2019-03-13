package de.ecconia.java.decompileide.structure.constantpool.contypes;

import de.ecconia.java.decompileide.structure.descriptor.Descriptor;

public class ClassTypeName
{
	private final TypeName typeName;
	private final String clazz;
	
	public ClassTypeName(TypeName typeName, String clazz)
	{
		this.typeName = typeName;
		this.clazz = clazz;
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
	
	public String getClazz()
	{
		return clazz;
	}
}

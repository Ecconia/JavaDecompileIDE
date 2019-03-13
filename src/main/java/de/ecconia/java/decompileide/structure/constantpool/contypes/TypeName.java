package de.ecconia.java.decompileide.structure.constantpool.contypes;

import de.ecconia.java.decompileide.structure.descriptor.Descriptor;

public class TypeName
{
	private final String name;
	private final Descriptor type;
	
	public TypeName(String name, Descriptor type)
	{
		this.name = name;
		this.type = type;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Descriptor getType()
	{
		return type;
	}
}

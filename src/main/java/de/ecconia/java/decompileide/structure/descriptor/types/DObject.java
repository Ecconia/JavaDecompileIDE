package de.ecconia.java.decompileide.structure.descriptor.types;

import de.ecconia.java.decompileide.structure.descriptor.DescriptorType;

public class DObject implements DescriptorType
{
	private final String type;
	
	public DObject(String type)
	{
		this.type = type;
	}
	
	@Override
	public String toString()
	{
		return type;
	}
}

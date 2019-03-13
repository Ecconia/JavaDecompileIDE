package de.ecconia.java.decompileide.structure.descriptor;

public class ArrayWrapper implements DescriptorType
{
	private final DescriptorType type;
	private final int level;
	
	public ArrayWrapper(DescriptorType type, int level)
	{
		this.type = type;
		this.level = level;
	}
	
	@Override
	public String toString()
	{
		String tmp = type.toString();
		for(int i = 0; i < level; i++)
		{
			tmp += "[]";
		}
		return tmp;
	}
}

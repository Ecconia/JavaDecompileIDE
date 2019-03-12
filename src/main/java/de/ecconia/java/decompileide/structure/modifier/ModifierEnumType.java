package de.ecconia.java.decompileide.structure.modifier;

public interface ModifierEnumType
{
	int getBit();
	
	String getName();
	
	default boolean probe(int mask)
	{
		return (mask & getBit()) != 0;
	}
}

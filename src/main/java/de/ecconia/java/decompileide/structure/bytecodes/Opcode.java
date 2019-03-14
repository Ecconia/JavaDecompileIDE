package de.ecconia.java.decompileide.structure.bytecodes;

public abstract class Opcode
{
	public static String getName()
	{
		throw new RuntimeException("Do not call the super method, call the child methods.");
	}
}

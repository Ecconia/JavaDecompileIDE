package de.ecconia.java.decompileide.structure.bytecodes.generated;

import de.ecconia.java.decompileide.CustomDataInput;
import de.ecconia.java.decompileide.structure.bytecodes.Opcode;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import java.io.IOException;

/* Automatically generated class, do not edit. */
public class Ifeq extends Opcode
{
	private final int offset;
	
	public Ifeq(CustomDataInput reader, ConstantPool pool) throws IOException
	{
		offset = reader.readShort();
	}
	
	@Override
	public String toString()
	{
		return "ifeq " + offset + "";
	}
}

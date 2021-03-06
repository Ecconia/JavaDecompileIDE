package de.ecconia.java.decompileide.structure.bytecodes.generated;

import de.ecconia.java.decompileide.CustomDataInput;
import de.ecconia.java.decompileide.structure.bytecodes.Opcode;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import java.io.IOException;

/* Automatically generated class, do not edit. */
public class Jsr extends Opcode
{
	private final int offset;
	
	public Jsr(CustomDataInput reader, ConstantPool pool) throws IOException
	{
		offset = reader.readShort();
	}
	
	@Override
	public String toString()
	{
		return "jsr " + offset + "";
	}
}

package de.ecconia.java.decompileide.structure.bytecodes2.generated;

import de.ecconia.java.decompileide.structure.bytecodes2.Opcode;
import de.ecconia.java.decompileide.CustomDataInput;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import java.io.IOException;

/* Automatically generated class, do not edit. */
public class Goto extends Opcode
{
	private final int offset;
	
	public Goto(CustomDataInput reader, ConstantPool pool) throws IOException
	{
		offset = reader.readShort();
	}
	
	@Override
	public String toString()
	{
		return "goto " + offset + "";
	}
}

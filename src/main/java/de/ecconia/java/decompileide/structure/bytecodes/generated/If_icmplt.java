package de.ecconia.java.decompileide.structure.bytecodes.generated;

import de.ecconia.java.decompileide.CustomDataInput;
import de.ecconia.java.decompileide.structure.bytecodes.Opcode;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import java.io.IOException;

/* Automatically generated class, do not edit. */
public class If_icmplt extends Opcode
{
	private final int offset;
	
	public If_icmplt(CustomDataInput reader, ConstantPool pool) throws IOException
	{
		offset = reader.readShort();
	}
	
	@Override
	public String toString()
	{
		return "if_icmplt " + offset + "";
	}
}

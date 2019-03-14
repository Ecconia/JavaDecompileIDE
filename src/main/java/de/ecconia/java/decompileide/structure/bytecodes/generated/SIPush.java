package de.ecconia.java.decompileide.structure.bytecodes.generated;

import de.ecconia.java.decompileide.CustomDataInput;
import de.ecconia.java.decompileide.structure.bytecodes.Opcode;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import java.io.IOException;

/* Automatically generated class, do not edit. */
public class SIPush extends Opcode
{
	private final int var;
	
	public SIPush(CustomDataInput reader, ConstantPool pool) throws IOException
	{
		var = reader.readShort();
	}
	
	@Override
	public String toString()
	{
		return "sipush " + var + "";
	}
}

package de.ecconia.java.decompileide.structure.bytecodes2.generated;

import de.ecconia.java.decompileide.structure.bytecodes2.Opcode;
import de.ecconia.java.decompileide.CustomDataInput;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import java.io.IOException;

/* Automatically generated class, do not edit. */
public class NewArray extends Opcode
{
	private final int var;
	
	public NewArray(CustomDataInput reader, ConstantPool pool) throws IOException
	{
		var = reader.readUnsignedByte();
	}
	
	@Override
	public String toString()
	{
		return "newarray " + var + "";
	}
}

package de.ecconia.java.decompileide.structure.bytecodes.generated;

import de.ecconia.java.decompileide.CustomDataInput;
import de.ecconia.java.decompileide.structure.bytecodes.Opcode;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import java.io.IOException;

/* Automatically generated class, do not edit. */
public class DStore extends Opcode
{
	private final int varIndex;
	
	public DStore(CustomDataInput reader, ConstantPool pool) throws IOException
	{
		varIndex = reader.readUnsignedByte();
	}
	
	@Override
	public String toString()
	{
		return "dstore Var" + varIndex + "";
	}
}

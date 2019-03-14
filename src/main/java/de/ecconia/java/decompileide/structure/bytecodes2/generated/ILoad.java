package de.ecconia.java.decompileide.structure.bytecodes2.generated;

import de.ecconia.java.decompileide.structure.bytecodes2.Opcode;
import de.ecconia.java.decompileide.CustomDataInput;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import java.io.IOException;

/* Automatically generated class, do not edit. */
public class ILoad extends Opcode
{
	private final int varIndex;
	
	public ILoad(CustomDataInput reader, ConstantPool pool) throws IOException
	{
		varIndex = reader.readUnsignedByte();
	}
	
	@Override
	public String toString()
	{
		return "iload Var" + varIndex + "";
	}
}

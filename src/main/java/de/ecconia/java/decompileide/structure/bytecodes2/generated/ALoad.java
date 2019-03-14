package de.ecconia.java.decompileide.structure.bytecodes2.generated;

import de.ecconia.java.decompileide.structure.bytecodes2.Opcode;
import de.ecconia.java.decompileide.CustomDataInput;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import java.io.IOException;

/* Automatically generated class, do not edit. */
public class ALoad extends Opcode
{
	private final int varIndex;
	
	public ALoad(CustomDataInput reader, ConstantPool pool) throws IOException
	{
		varIndex = reader.readUnsignedByte();
	}
	
	@Override
	public String toString()
	{
		return "aload Var" + varIndex + "";
	}
}

package de.ecconia.java.decompileide.structure.bytecodes2.generated;

import de.ecconia.java.decompileide.structure.bytecodes2.Opcode;
import de.ecconia.java.decompileide.CustomDataInput;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import java.io.IOException;

/* Automatically generated class, do not edit. */
public class IInc extends Opcode
{
	private final int varIndex;
	private final int var;
	
	public IInc(CustomDataInput reader, ConstantPool pool) throws IOException
	{
		varIndex = reader.readUnsignedByte();
		var = reader.readByte();
	}
	
	@Override
	public String toString()
	{
		return "iinc Var" + varIndex + " " + var + "";
	}
}

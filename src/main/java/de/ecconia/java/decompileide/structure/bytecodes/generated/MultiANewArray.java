package de.ecconia.java.decompileide.structure.bytecodes.generated;

import de.ecconia.java.decompileide.CustomDataInput;
import de.ecconia.java.decompileide.structure.bytecodes.Opcode;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import java.io.IOException;

/* Automatically generated class, do not edit. */
public class MultiANewArray extends Opcode
{
	private final String clazz;
	private final int var;
	
	public MultiANewArray(CustomDataInput reader, ConstantPool pool) throws IOException
	{
		clazz = pool.getClass(reader.readUnsignedShort());
		var = reader.readUnsignedByte();
	}
	
	@Override
	public String toString()
	{
		return "multianewarray " + clazz.replace('/', '.') + " " + var + "";
	}
}

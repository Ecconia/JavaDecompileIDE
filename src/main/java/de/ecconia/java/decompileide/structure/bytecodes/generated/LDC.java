package de.ecconia.java.decompileide.structure.bytecodes.generated;

import de.ecconia.java.decompileide.CustomDataInput;
import de.ecconia.java.decompileide.structure.bytecodes.Opcode;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import java.io.IOException;

/* Automatically generated class, do not edit. */
public class LDC extends Opcode
{
	public final Object value;
	
	public LDC(CustomDataInput reader, ConstantPool pool) throws IOException
	{
		value = pool.getSmallValue(reader.readUnsignedByte());
	}
	
	@Override
	public String toString()
	{
		return "ldc '" + value + "'";
	}
}

package de.ecconia.java.decompileide.structure.bytecodes2.generated;

import de.ecconia.java.decompileide.structure.bytecodes2.Opcode;
import de.ecconia.java.decompileide.CustomDataInput;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import java.io.IOException;

/* Automatically generated class, do not edit. */
public class LDC_w extends Opcode
{
	public final Object value;
	
	public LDC_w(CustomDataInput reader, ConstantPool pool) throws IOException
	{
		value = pool.getSmallValue(reader.readUnsignedShort());
	}
	
	@Override
	public String toString()
	{
		return "ldc_w '" + value + "'";
	}
}

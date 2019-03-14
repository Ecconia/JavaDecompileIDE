package de.ecconia.java.decompileide.structure.bytecodes2.generated;

import de.ecconia.java.decompileide.structure.bytecodes2.Opcode;
import de.ecconia.java.decompileide.CustomDataInput;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import java.io.IOException;

/* Automatically generated class, do not edit. */
public class CheckCast extends Opcode
{
	private final String clazz;
	
	public CheckCast(CustomDataInput reader, ConstantPool pool) throws IOException
	{
		clazz = pool.getClass(reader.readUnsignedShort());
	}
	
	@Override
	public String toString()
	{
		return "checkcast " + clazz.replace('/', '.') + "";
	}
}

package de.ecconia.java.decompileide.structure.bytecodes.generated;

import de.ecconia.java.decompileide.CustomDataInput;
import de.ecconia.java.decompileide.structure.bytecodes.Opcode;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import java.io.IOException;

/* Automatically generated class, do not edit. */
public class New extends Opcode
{
	private final String clazz;
	
	public New(CustomDataInput reader, ConstantPool pool) throws IOException
	{
		clazz = pool.getClass(reader.readUnsignedShort());
	}
	
	@Override
	public String toString()
	{
		return "new " + clazz.replace('/', '.') + "";
	}
}

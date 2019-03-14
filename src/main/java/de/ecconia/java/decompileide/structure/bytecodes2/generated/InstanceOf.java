package de.ecconia.java.decompileide.structure.bytecodes2.generated;

import de.ecconia.java.decompileide.structure.bytecodes2.Opcode;
import de.ecconia.java.decompileide.CustomDataInput;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import java.io.IOException;

/* Automatically generated class, do not edit. */
public class InstanceOf extends Opcode
{
	private final String clazz;
	
	public InstanceOf(CustomDataInput reader, ConstantPool pool) throws IOException
	{
		clazz = pool.getClass(reader.readUnsignedShort());
	}
	
	@Override
	public String toString()
	{
		return "instanceof " + clazz.replace('/', '.') + "";
	}
}

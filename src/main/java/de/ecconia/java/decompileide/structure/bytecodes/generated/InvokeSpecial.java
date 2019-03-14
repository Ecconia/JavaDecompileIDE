package de.ecconia.java.decompileide.structure.bytecodes.generated;

import de.ecconia.java.decompileide.CustomDataInput;
import de.ecconia.java.decompileide.structure.bytecodes.Opcode;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import java.io.IOException;
import de.ecconia.java.decompileide.structure.constantpool.contypes.ClassTypeName;

/* Automatically generated class, do not edit. */
public class InvokeSpecial extends Opcode
{
	private final ClassTypeName classNameType;
	
	public InvokeSpecial(CustomDataInput reader, ConstantPool pool) throws IOException
	{
		classNameType = pool.getMethod(reader.readUnsignedShort());
	}
	
	@Override
	public String toString()
	{
		return "invokespecial " + (classNameType.getClazz() + ":" + classNameType.getName() + " {" + classNameType.getType() + "}").replace('/', '.') + "";
	}
}

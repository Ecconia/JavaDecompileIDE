package de.ecconia.java.decompileide.structure.bytecodes2.generated;

import de.ecconia.java.decompileide.structure.bytecodes2.Opcode;
import de.ecconia.java.decompileide.CustomDataInput;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import java.io.IOException;
import de.ecconia.java.decompileide.structure.constantpool.contypes.ClassTypeName;

/* Automatically generated class, do not edit. */
public class InvokeStatic extends Opcode
{
	private final ClassTypeName classNameType;
	
	public InvokeStatic(CustomDataInput reader, ConstantPool pool) throws IOException
	{
		classNameType = pool.getMethodOrInterfaceMethod(reader.readUnsignedShort());
	}
	
	@Override
	public String toString()
	{
		return "invokestatic " + (classNameType.getClazz() + ":" + classNameType.getName() + " {" + classNameType.getType() + "}").replace('/', '.') + "";
	}
}

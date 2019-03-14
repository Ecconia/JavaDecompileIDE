package de.ecconia.java.decompileide.structure.bytecodes.generated;

import de.ecconia.java.decompileide.CustomDataInput;
import de.ecconia.java.decompileide.structure.bytecodes.Opcode;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import java.io.IOException;
import de.ecconia.java.decompileide.structure.constantpool.contypes.ClassTypeName;

/* Automatically generated class, do not edit. */
public class InvokeInterface extends Opcode
{
	private final ClassTypeName classNameType;
	private final int var;
	
	public InvokeInterface(CustomDataInput reader, ConstantPool pool) throws IOException
	{
		classNameType = pool.getInterfaceMethod(reader.readUnsignedShort());
		var = reader.readUnsignedByte();
		reader.readByte();
	}
	
	@Override
	public String toString()
	{
		return "invokeinterface " + (classNameType.getClazz() + ":" + classNameType.getName() + " {" + classNameType.getType() + "}").replace('/', '.') + " " + var + "";
	}
}

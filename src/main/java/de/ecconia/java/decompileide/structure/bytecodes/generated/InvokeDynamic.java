package de.ecconia.java.decompileide.structure.bytecodes.generated;

import de.ecconia.java.decompileide.CustomDataInput;
import de.ecconia.java.decompileide.structure.bytecodes.Opcode;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import de.ecconia.java.decompileide.structure.constantpool.contypes.BootstrapMethod;
import java.io.IOException;

/* Automatically generated class, do not edit. */
public class InvokeDynamic extends Opcode
{
	private final BootstrapMethod bootstrapMethod;
	
	public InvokeDynamic(CustomDataInput reader, ConstantPool pool) throws IOException
	{
		bootstrapMethod = pool.getBootstrapMethod(reader.readUnsignedShort());
		reader.readByte();
		reader.readByte();
	}
	
	@Override
	public String toString()
	{
		return "invokedynamic " + (bootstrapMethod.getName() + " {" + bootstrapMethod.getType() + "} @" + bootstrapMethod.getIndex()).replace('/', '.') + "";
	}
}

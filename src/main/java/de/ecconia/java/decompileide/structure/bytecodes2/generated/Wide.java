package de.ecconia.java.decompileide.structure.bytecodes2.generated;

import de.ecconia.java.decompileide.structure.bytecodes2.Opcode;
import de.ecconia.java.decompileide.CustomDataInput;
import de.ecconia.java.decompileide.structure.bytecodes2.OpcodeInfo;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import java.io.IOException;

/* Automatically generated class, do not edit. */
public class Wide extends Opcode
{
	private final Opcode instruction;
	private final int varIndex;
	private final Integer constant;
	
	public Wide(CustomDataInput reader, ConstantPool pool) throws IOException
	{
		reader.alignTo4();
		
		instruction = OpcodeInfo.instructionFromOpcode(reader.readUnsignedByte(), reader, pool);
		varIndex = reader.readUnsignedShort();
		constant = instruction instanceof IInc ? (int) reader.readShort() : null;
	}
	
	@Override
	public String toString()
	{
		return "wide " + instruction + " Var" + varIndex + " (" + constant + ")";
	}
}

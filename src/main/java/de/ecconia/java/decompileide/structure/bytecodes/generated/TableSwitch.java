package de.ecconia.java.decompileide.structure.bytecodes.generated;

import java.util.Arrays;

import de.ecconia.java.decompileide.CustomDataInput;
import de.ecconia.java.decompileide.structure.bytecodes.Opcode;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import java.io.IOException;
import java.util.stream.Collectors;

/* Automatically generated class, do not edit. */
public class TableSwitch extends Opcode
{
	private final int defaultAddress;
	private final int[] entries;
	
	public TableSwitch(CustomDataInput reader, ConstantPool pool) throws IOException
	{
		reader.alignTo4();
		
		defaultAddress = reader.readInt();
		int min = reader.readInt();
		int max = reader.readInt();
		int amount = max - min + 1;
		entries = new int[amount];
		for(int i = 0; i < amount; i++)
		{
			entries[i] = reader.readInt();
		}
	}
	
	@Override
	public String toString()
	{
		return "tableswitch " + defaultAddress + " : {" + Arrays.stream(entries).mapToObj(v -> String.valueOf(v)).collect(Collectors.joining(", ")) + "}";
	}
}

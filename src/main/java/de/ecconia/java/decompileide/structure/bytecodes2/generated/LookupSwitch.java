package de.ecconia.java.decompileide.structure.bytecodes2.generated;

import java.util.Arrays;
import de.ecconia.java.decompileide.structure.bytecodes2.Opcode;
import de.ecconia.java.decompileide.CustomDataInput;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import java.io.IOException;
import java.util.stream.Collectors;

/* Automatically generated class, do not edit. */
public class LookupSwitch extends Opcode
{
	private final int defaultAddress;
	private final int[][] entries;
	
	public LookupSwitch(CustomDataInput reader, ConstantPool pool) throws IOException
	{
		reader.alignTo4();
		
		defaultAddress = reader.readInt();
		int amount = reader.readInt();
		entries = new int[amount][];
		for(int i = 0; i < amount; i++)
		{
			entries[i] = new int[2];
			entries[i][0] = reader.readInt();
			entries[i][1] = reader.readInt();
		}
	}
	
	@Override
	public String toString()
	{
		return "lookupswitch " + defaultAddress + " : { " + Arrays.stream(entries).map(t -> t[0] + ": " + t[1]).collect(Collectors.joining(", ")) + " }";
	}
}

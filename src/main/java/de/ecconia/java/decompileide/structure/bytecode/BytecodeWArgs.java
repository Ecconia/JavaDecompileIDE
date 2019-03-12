package de.ecconia.java.decompileide.structure.bytecode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.ecconia.java.decompileide.CustomDataInput;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;

public class BytecodeWArgs extends Bytecode
{
	private final int argBytes;
	
	public BytecodeWArgs(String name, int i)
	{
		super(name);
		
		this.argBytes = i;
	}
	
	@Override
	public String[] parse(CustomDataInput d, ConstantPool pool) throws IOException
	{
		List<String> ret = new ArrayList<>();
		
		for(int i = 0; i < argBytes; i++)
		{
			ret.add('\'' + String.valueOf(d.readUnsignedByte()) + '\'');
		}
		
		return ret.toArray(new String[0]);
	}
}

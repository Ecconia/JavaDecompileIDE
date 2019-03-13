package de.ecconia.java.decompileide.structure.constantpool.entrytypes.second;

import java.io.DataInput;
import java.io.IOException;

import de.ecconia.java.decompileide.structure.constantpool.CPEntry;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import de.ecconia.java.decompileide.structure.constantpool.contypes.BootstrapMethod;
import de.ecconia.java.decompileide.structure.constantpool.resolver.ResolveLater;

public class CPTInvokeDynamic extends CPEntry implements ResolveLater
{
	private final int index0;
	private final int index1;
	
	private BootstrapMethod data;
	
	public CPTInvokeDynamic(int index, DataInput d) throws IOException
	{
		super(index);
		
		index0 = d.readUnsignedShort();
		index1 = d.readUnsignedShort();
	}
	
	@Override
	public void resolve(ConstantPool pool)
	{
		data = new BootstrapMethod(index0, pool.getTypeName(index1));
	}
	
	public BootstrapMethod getData()
	{
		return data;
	}
	
	@Override
	public String toString()
	{
		return this.getClass().getSimpleName() + "{" + data.getName() + ", " + data.getType() + ", " + index0 + "}";
	}
}

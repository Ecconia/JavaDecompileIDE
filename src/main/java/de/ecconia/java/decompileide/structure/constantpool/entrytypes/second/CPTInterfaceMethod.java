package de.ecconia.java.decompileide.structure.constantpool.entrytypes.second;

import java.io.DataInput;
import java.io.IOException;

import de.ecconia.java.decompileide.structure.constantpool.CPEntry;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import de.ecconia.java.decompileide.structure.constantpool.contypes.ClassTypeName;
import de.ecconia.java.decompileide.structure.constantpool.resolver.ResolveLater;
import de.ecconia.java.decompileide.structure.constantpool.special.CPMethodMayInterface;

public class CPTInterfaceMethod extends CPEntry implements ResolveLater, CPMethodMayInterface
{
	private final int index1;
	private final int index2;
	
	private ClassTypeName data;
	
	public CPTInterfaceMethod(int index, DataInput d) throws IOException
	{
		super(index);
		
		index1 = d.readUnsignedShort();
		index2 = d.readUnsignedShort();
	}
	
	@Override
	public void resolve(ConstantPool pool)
	{
		data = new ClassTypeName(pool.getTypeName(index2), pool.getClass(index1));
	}
	
	public ClassTypeName getData()
	{
		return data;
	}
	
	@Override
	public String toString()
	{
		return this.getClass().getSimpleName() + "{" + data.getName() + ", " + data.getType() + ", " + data.getClazz() + "}";
	}
}

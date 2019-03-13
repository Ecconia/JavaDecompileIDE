package de.ecconia.java.decompileide.structure.constantpool.entrytypes.third;

import java.io.DataInput;
import java.io.IOException;

import de.ecconia.java.decompileide.structure.constantpool.CPEntry;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import de.ecconia.java.decompileide.structure.constantpool.contypes.ClassTypeName;
import de.ecconia.java.decompileide.structure.constantpool.resolver.ResolveLatest;
import de.ecconia.java.decompileide.structure.constantpool.special.CPSmallValue;

public class CPTMethodHandle extends CPEntry implements ResolveLatest, CPSmallValue
{
	private final int type;
	private final int index1;
	
	private ClassTypeName data;
	
	public CPTMethodHandle(int index, DataInput d) throws IOException
	{
		super(index);
		
		type = d.readUnsignedByte();
		index1 = d.readUnsignedShort();
	}
	
	@SuppressWarnings("unused")
	@Override
	public void resolve(ConstantPool pool)
	{
		if(type == 1 || type == 2 || type == 3 || type == 4)
		{
			data = pool.getField(index1);
		}
		else if(type == 5 || type == 8)
		{
			data = pool.getMethod(index1);
		}
		else if(type == 6 || type == 7)
		{
			//TODO: Inject real version.
			final int tmpVersion = 52;
			if(tmpVersion < 52)
			{
				data = pool.getMethod(index1);
			}
			else
			{
				data = pool.getMethodOrInterfaceMethod(index1);
			}
		}
		else if(type == 9)
		{
			data = pool.getInterfaceMethod(index1);
		}
		else
		{
			//TODO: Throw exception.
		}
	}
	
	public ClassTypeName getData()
	{
		return data;
	}
	
	@Override
	public Object getValue()
	{
		return data;
	}
	
	@Override
	public String toString()
	{
		return this.getClass().getSimpleName() + "{" + data.getName() + ", " + data.getType() + ", " + data.getClazz() + "}";
	}
}

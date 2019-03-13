package de.ecconia.java.decompileide.structure.constantpool.entrytypes.first;

import java.io.DataInput;
import java.io.IOException;

import de.ecconia.java.decompileide.structure.constantpool.CPEntry;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import de.ecconia.java.decompileide.structure.constantpool.contypes.TypeName;
import de.ecconia.java.decompileide.structure.constantpool.resolver.ResolveFirst;
import de.ecconia.java.decompileide.structure.descriptor.Descriptor;

public class CPTTypeName extends CPEntry implements ResolveFirst
{
	private final int index1;
	private final int index2;
	
	private TypeName data;
	
	public CPTTypeName(int index, DataInput d) throws IOException
	{
		super(index);
		
		this.index1 = d.readUnsignedShort();
		this.index2 = d.readUnsignedShort();
	}
	
	@Override
	public void resolve(ConstantPool pool)
	{
		data = new TypeName(pool.getUTF(index1), new Descriptor(pool.getUTF(index2)));
	}
	
	public TypeName getTypeName()
	{
		return data;
	}
	
	@Override
	public String toString()
	{
		return this.getClass().getSimpleName() + "{" + data.getName() + ", " + data.getType() + "}";
	}
}

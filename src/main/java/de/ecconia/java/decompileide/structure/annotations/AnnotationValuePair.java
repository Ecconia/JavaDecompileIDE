package de.ecconia.java.decompileide.structure.annotations;

import java.io.DataInput;
import java.io.IOException;

import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;

public class AnnotationValuePair
{
	private final String name;
	private final AnnotationValue value;
	
	public AnnotationValuePair(DataInput reader, ConstantPool pool) throws IOException
	{
		name = pool.getUTF(reader.readUnsignedShort());
		value = new AnnotationValue(reader, pool);
	}
	
	@Override
	public String toString()
	{
		return name + " = " + value;
	}
}

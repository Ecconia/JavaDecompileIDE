package de.ecconia.java.decompileide.structure.annotations;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import de.ecconia.java.decompileide.structure.descriptor.Descriptor;

public class Annotation
{
	private final Descriptor type;
	private final List<AnnotationValuePair> parameters = new ArrayList<>();
	
	public Annotation(DataInput reader, ConstantPool pool) throws IOException
	{
		type = new Descriptor(pool.getUTF(reader.readUnsignedShort()));
		int pairAmount = reader.readUnsignedShort();
		for(int j = 0; j < pairAmount; j++)
		{
			parameters.add(new AnnotationValuePair(reader, pool));
		}
	}
	
	@Override
	public String toString()
	{
		return "@" + type.getReturnType().toString().replace('/', '.') + (parameters.isEmpty() ? "" : "(" + parameters.stream().map(e -> e.toString()).collect(Collectors.joining(", ")) + ")");
	}
}

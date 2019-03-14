package de.ecconia.java.decompileide.structure.attribues;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.ecconia.java.decompileide.structure.annotations.Annotation;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;

public class RuntimeVisibleAnnotationsAttribute extends Attribute
{
	private final List<Annotation> annotations = new ArrayList<>();
	
	public RuntimeVisibleAnnotationsAttribute(String name, DataInput reader, ConstantPool pool) throws IOException
	{
		super(name);
		
		//TBI: Validate length?
		reader.readInt();
		
		int amount = reader.readUnsignedShort();
		for(int i = 0; i < amount; i++)
		{
			annotations.add(new Annotation(reader, pool));
		}
	}
	
	public List<Annotation> getAnnotations()
	{
		return annotations;
	}
}

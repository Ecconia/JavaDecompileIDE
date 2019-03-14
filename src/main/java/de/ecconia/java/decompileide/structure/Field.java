package de.ecconia.java.decompileide.structure;

import java.io.DataInput;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.ecconia.java.decompileide.structure.attribues.Attribute;
import de.ecconia.java.decompileide.structure.attribues.Attributes;
import de.ecconia.java.decompileide.structure.attribues.ConstantValueAttribute;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import de.ecconia.java.decompileide.structure.modifier.FieldModifier;
import de.ecconia.java.decompileide.structure.modifier.Modifier;

public class Field
{
	private final String name;
	private final String descriptor;
	private final Modifier modifier;
	
	private Map<String, Attribute> attributes = new HashMap<>();
	//Attribute properties:
	private Object value;
	
	public Field(DataInput reader, ConstantPool pool) throws IOException
	{
		modifier = new Modifier(reader.readUnsignedShort(), FieldModifier.getAll());
		name = pool.getUTF(reader.readUnsignedShort());
		descriptor = pool.getUTF(reader.readUnsignedShort());
		
		int attributesCount = reader.readUnsignedShort();
		for(int j = 0; j < attributesCount; j++)
		{
			addAttribute(Attributes.parse(reader, pool));
		}
	}

	public void addAttribute(Attribute attribute)
	{
		if(attribute instanceof ConstantValueAttribute)
		{
			value = ((ConstantValueAttribute) attribute).getValue();
		}
		else
		{
			attributes.put(attribute.getName(), attribute);
		}
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDescriptor()
	{
		return descriptor;
	}
	
	public Modifier getModifier()
	{
		return modifier;
	}
	
	public Object getConstantValue()
	{
		return value;
	}
	
	public boolean hasAttribute(String id)
	{
		return attributes.containsKey(id);
	}
	
	public Attribute getAttribute(String id)
	{
		return attributes.get(id);
	}
	
	public Map<String, Attribute> getAttributes()
	{
		return attributes;
	}
}

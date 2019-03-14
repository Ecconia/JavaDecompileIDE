package de.ecconia.java.decompileide.structure;

import java.io.DataInput;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.ecconia.java.decompileide.structure.attribues.Attribute;
import de.ecconia.java.decompileide.structure.attribues.Attributes;
import de.ecconia.java.decompileide.structure.attribues.DeprecatedAttribute;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import de.ecconia.java.decompileide.structure.modifier.MethodModifier;
import de.ecconia.java.decompileide.structure.modifier.Modifier;

public class Method
{
	private final String name;
	private final String descriptor;
	private final Modifier modifier;
	
	private boolean deprecated;
	
	private Map<String, Attribute> attributes = new HashMap<>();
	
	public Method(DataInput reader, ConstantPool pool) throws IOException
	{
		modifier = new Modifier(reader.readUnsignedShort(), MethodModifier.getAll());
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
		if(attribute instanceof DeprecatedAttribute)
		{
			deprecated = true;
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
	
	public boolean isDeprecated()
	{
		return deprecated;
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

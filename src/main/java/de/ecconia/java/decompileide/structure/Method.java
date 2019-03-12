package de.ecconia.java.decompileide.structure;

import java.util.HashMap;
import java.util.Map;

import de.ecconia.java.decompileide.structure.attribues.Attribute;
import de.ecconia.java.decompileide.structure.modifier.Modifier;

public class Method
{
	private final String name;
	private final String descriptor;
	private final Modifier modifier;
	
	private Map<String, Attribute> attributes = new HashMap<>();
	
	public Method(String name, String descriptor, Modifier modifier)
	{
		this.name = name;
		this.descriptor = descriptor;
		this.modifier = modifier;
	}
	
	public void addAttribute(Attribute attribute)
	{
		attributes.put(attribute.getName(), attribute);
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

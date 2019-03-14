package de.ecconia.java.decompileide.structure.attribues;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import de.ecconia.java.decompileide.structure.modifier.ClassModifier;
import de.ecconia.java.decompileide.structure.modifier.Modifier;

public class InnerClassesAttribute extends Attribute
{
	private final List<String> entries = new ArrayList<>();
	
	public InnerClassesAttribute(String name, DataInput reader, ConstantPool pool) throws IOException
	{
		super(name);
		
		reader.readInt();
		int amount = reader.readUnsignedShort();
		for(int i = 0; i < amount; i++)
		{
			String innerClass = pool.getClass(reader.readUnsignedShort()).replace('/', '.');
			
			String outerClass = null;
			int indexOuterClass = reader.readUnsignedShort();
			if(indexOuterClass != 0)
			{
				outerClass = pool.getClass(indexOuterClass).replace('/', '.');
			}
			
			String innerName = null;
			int indexInnerName = reader.readUnsignedShort();
			if(indexInnerName != 0)
			{
				innerName = pool.getUTF(indexInnerName);
			}
			
			Modifier innerClassAccessFlags = new Modifier(reader.readUnsignedShort(), ClassModifier.getAll());
			
			entries.add(innerClass + " " + outerClass + " " + innerName + " >" + innerClassAccessFlags.toString() + "<");
		}
	}
	
	public List<String> getEntries()
	{
		return entries;
	}
}

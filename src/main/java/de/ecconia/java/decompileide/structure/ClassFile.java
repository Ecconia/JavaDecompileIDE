package de.ecconia.java.decompileide.structure;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.ecconia.java.decompileide.structure.attribues.Attribute;
import de.ecconia.java.decompileide.structure.attribues.Attributes;
import de.ecconia.java.decompileide.structure.constantpool.CPDouble;
import de.ecconia.java.decompileide.structure.constantpool.CPLong;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPoolEntry;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPoolLib;
import de.ecconia.java.decompileide.structure.modifier.ClassModifier;
import de.ecconia.java.decompileide.structure.modifier.FieldModifier;
import de.ecconia.java.decompileide.structure.modifier.MethodModifier;
import de.ecconia.java.decompileide.structure.modifier.Modifier;

public class ClassFile
{
	public static ClassFile parse(File file)
	{
		//TODO: Better error handling.
		try(DataInputStream di = new DataInputStream(new FileInputStream(file)))
		{
			int magic = di.readInt();
			if(magic != 0xCAFEBABE)
			{
				System.out.println("Not a classfile, invalid magic value.");
				return null;
			}
			
			return new ClassFile(di);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File: " + file.getAbsolutePath() + " does not exist.");
			e.printStackTrace(System.out);
		}
		catch(IOException e)
		{
			System.out.println("File: " + file.getAbsolutePath() + " could not be read properly.");
			e.printStackTrace(System.out);
		}
		
		return null;
	}
	
	//Non-Static:
	
	private final int bigVersion;
	private final int smallVersion;
	private final ConstantPool pool;
	private final Modifier modifier;
	private final String className;
	private final String superClassName;
	private final List<String> interfaces = new ArrayList<>();
	private final List<Field> fields = new ArrayList<>();
	private final List<Method> methods = new ArrayList<>();
	private final Map<String, Attribute> attributes = new HashMap<>();
	
	public ClassFile(DataInput d) throws IOException
	{
		smallVersion = d.readUnsignedShort();
		bigVersion = d.readUnsignedShort();
		pool = readConstantPool(d);
		modifier = new Modifier(d.readUnsignedShort(), ClassModifier.getAll());
		className = pool.getClassName(d.readUnsignedShort());
		{
			int superClassIndex = d.readUnsignedShort();
			if(superClassIndex != 0)
			{
				superClassName = pool.getClassName(superClassIndex);
			}
			else
			{
				superClassName = null;
			}
		}
		readInterfaces(d);
		readFields(d);
		readMethods(d);
		readAttributes(d);
		
		try
		{
			d.readByte();
			throw new RuntimeException("Class file had more to read than exepcted.");
		}
		catch(EOFException e)
		{
		}
	}
	
	private ConstantPool readConstantPool(DataInput d) throws IOException
	{
		int constantPoolCount = d.readUnsignedShort();
		ConstantPoolEntry[] entries = new ConstantPoolEntry[constantPoolCount];
		for(int i = 1; i < constantPoolCount; i++)
		{
			ConstantPoolEntry entry = ConstantPoolLib.parse(d);
			
			entries[i] = entry;
			if(entry instanceof CPLong || entry instanceof CPDouble)
			{
				i++;
			}
		}
		
		return new ConstantPool(entries);
	}
	
	private void readInterfaces(DataInput d) throws IOException
	{
		int interfacesCount = d.readUnsignedShort();
		for(int i = 0; i < interfacesCount; i++)
		{
			interfaces.add(pool.getClassName(d.readUnsignedShort()));
		}
	}
	
	private void readFields(DataInput d) throws IOException
	{
		int fieldCount = d.readUnsignedShort();
		for(int i = 0; i < fieldCount; i++)
		{
			Modifier modifier = new Modifier(d.readUnsignedShort(), FieldModifier.getAll());
			String name = pool.getUtf8(d.readUnsignedShort());
			String descriptor = pool.getUtf8(d.readUnsignedShort());
			Field field = new Field(name, descriptor, modifier);
			
			int attributesCount = d.readUnsignedShort();
			for(int j = 0; j < attributesCount; j++)
			{
				field.addAttribute(Attributes.parse(d, pool));
			}
			
			fields.add(field);
		}
	}
	
	private void readMethods(DataInput d) throws IOException
	{
		int methodsCount = d.readUnsignedShort();
		for(int i = 0; i < methodsCount; i++)
		{
			Modifier modifier = new Modifier(d.readUnsignedShort(), MethodModifier.getAll());
			String name = pool.getUtf8(d.readUnsignedShort());
			String descriptor = pool.getUtf8(d.readUnsignedShort());
			
			Method method = new Method(name, descriptor, modifier);
			
			int attributesCount = d.readUnsignedShort();
			for(int j = 0; j < attributesCount; j++)
			{
				method.addAttribute(Attributes.parse(d, pool));
			}
			
			methods.add(method);
		}
	}
	
	private void readAttributes(DataInput d) throws IOException
	{
		int attributesCount = d.readUnsignedShort();
		for(int i = 0; i < attributesCount; i++)
		{
			Attribute attribute = Attributes.parse(d, pool);
			attributes.put(attribute.getName(), attribute);
		}
	}
	
	public int getBigVersion()
	{
		return bigVersion;
	}
	
	public int getSmallVersion()
	{
		return smallVersion;
	}
	
	public Modifier getModifier()
	{
		return modifier;
	}
	
	public String getClassName()
	{
		return className;
	}
	
	public String getSuperClassName()
	{
		return superClassName;
	}
	
	public List<String> getInterfaces()
	{
		return interfaces;
	}
	
	public List<Field> getFields()
	{
		return fields;
	}
	
	public List<Method> getMethods()
	{
		return methods;
	}
	
	public Map<String, Attribute> getAttributes()
	{
		return attributes;
	}

	public ConstantPool getPool()
	{
		return pool;
	}
}

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

import de.ecconia.java.decompileide.structure.annotations.Annotation;
import de.ecconia.java.decompileide.structure.attribues.Attribute;
import de.ecconia.java.decompileide.structure.attribues.Attributes;
import de.ecconia.java.decompileide.structure.attribues.RuntimeVisibleAnnotationsAttribute;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import de.ecconia.java.decompileide.structure.modifier.ClassModifier;
import de.ecconia.java.decompileide.structure.modifier.Modifier;

public class ClassFile
{
	public static ClassFile parse(File file)
	{
		//TODO: Better error handling. <- Lol for the whole project :/
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
	//Properties from attributes:
	private final List<Annotation> annotations = new ArrayList<>();
	
	public ClassFile(DataInput d) throws IOException
	{
		smallVersion = d.readUnsignedShort();
		bigVersion = d.readUnsignedShort();
		
		pool = new ConstantPool(d);
		modifier = new Modifier(d.readUnsignedShort(), ClassModifier.getAll());
		className = pool.getClass(d.readUnsignedShort());
		{
			int superClassIndex = d.readUnsignedShort();
			if(superClassIndex != 0)
			{
				superClassName = pool.getClass(superClassIndex);
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
	
	private void readInterfaces(DataInput d) throws IOException
	{
		int interfacesCount = d.readUnsignedShort();
		for(int i = 0; i < interfacesCount; i++)
		{
			interfaces.add(pool.getClass(d.readUnsignedShort()));
		}
	}
	
	private void readFields(DataInput d) throws IOException
	{
		int fieldCount = d.readUnsignedShort();
		for(int i = 0; i < fieldCount; i++)
		{
			fields.add(new Field(d, pool));
		}
	}
	
	private void readMethods(DataInput d) throws IOException
	{
		int methodsCount = d.readUnsignedShort();
		for(int i = 0; i < methodsCount; i++)
		{
			methods.add(new Method(d, pool));
		}
	}
	
	private void readAttributes(DataInput d) throws IOException
	{
		int attributesCount = d.readUnsignedShort();
		for(int i = 0; i < attributesCount; i++)
		{
			Attribute attribute = Attributes.parse(d, pool);
			if(attribute instanceof RuntimeVisibleAnnotationsAttribute)
			{
				annotations.addAll(((RuntimeVisibleAnnotationsAttribute) attribute).getAnnotations());
			}
			else
			{
				attributes.put(attribute.getName(), attribute);
			}
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
	
	public List<Annotation> getAnnotations()
	{
		return annotations;
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

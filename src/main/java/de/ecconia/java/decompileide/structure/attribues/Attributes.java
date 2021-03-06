package de.ecconia.java.decompileide.structure.attribues;

import java.io.DataInput;
import java.io.IOException;

import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;

public class Attributes
{
	public static Attribute parse(DataInput d, ConstantPool pool) throws IOException
	{
		String name = pool.getUTF(d.readUnsignedShort());
		switch(name)
		{
		//TODO: Implement some more stuff:
		case "Code":
			return new CodeAttribute(name, d, pool);
		case "SourceFile":
			return new SourceFileAttribute(name, d, pool);
		case "ConstantValue":
			return new ConstantValueAttribute(name, d, pool);
		case "Signature":
			return new SignatureAttribute(name, d, pool);
		case "Deprecated":
			return new DeprecatedAttribute(name, d);
		case "Exceptions":
			return new Attribute(name, d);
		case "BootstrapMethods":
			return new Attribute(name, d);
		case "InnerClasses":
			return new InnerClassesAttribute(name, d, pool);
		case "EnclosingMethod":
			return new Attribute(name, d);
		case "RuntimeVisibleAnnotations":
			return new RuntimeVisibleAnnotationsAttribute(name, d, pool);
		//MC-Attributes:
		case "RuntimeVisibleParameterAnnotations":
			return new Attribute(name, d);
		case "RuntimeInvisibleAnnotations":
			return new Attribute(name, d);
		//Code:
		case "LocalVariableTable":
			return new Attribute(name, d);
		case "LineNumberTable":
			return new Attribute(name, d);
		case "StackMapTable":
			return new Attribute(name, d);
		case "LocalVariableTypeTable":
			return new Attribute(name, d);
			
		default:
			throw new RuntimeException("Unknown Attribute encountered: " + name);
		}
	}
}

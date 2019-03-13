package de.ecconia.java.decompileide.structure.descriptor;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import de.ecconia.java.decompileide.CustomStringReader;
import de.ecconia.java.decompileide.structure.descriptor.types.DBoolean;
import de.ecconia.java.decompileide.structure.descriptor.types.DByte;
import de.ecconia.java.decompileide.structure.descriptor.types.DChar;
import de.ecconia.java.decompileide.structure.descriptor.types.DDouble;
import de.ecconia.java.decompileide.structure.descriptor.types.DFloat;
import de.ecconia.java.decompileide.structure.descriptor.types.DInt;
import de.ecconia.java.decompileide.structure.descriptor.types.DLong;
import de.ecconia.java.decompileide.structure.descriptor.types.DObject;
import de.ecconia.java.decompileide.structure.descriptor.types.DShort;
import de.ecconia.java.decompileide.structure.descriptor.types.DVoid;

public class Descriptor
{
	private DescriptorType[] args;
	private DescriptorType returnType;
	
	public Descriptor(String in)
	{
		List<DescriptorType> parameters = new ArrayList<>();
		
		CustomStringReader reader = new CustomStringReader(in);
		
		boolean parameterMode = false;
		
		char c = reader.peek();
		if(c == '(')
		{
			parameterMode = true;
			//Skip this char.
			c = reader.read();
		}
		
		int arrayIndex = 0;
		while(reader.hasMore())
		{
			c = reader.read();
			if(c == '[')
			{
				arrayIndex++;
			}
			else if(c == '(')
			{
				throw new RuntimeException("Descriptor had a Parameter section which didn't start at the beginning.");
			}
			else if(c == ')')
			{
				parameterMode = false;
			}
			else
			{
				DescriptorType type = descriptorTypeFrom(c, reader);
				
				if(arrayIndex != 0)
				{
					type = new ArrayWrapper(type, arrayIndex);
					arrayIndex = 0;
				}
				
				if(parameterMode)
				{
					parameters.add(type);
				}
				else
				{
					if(returnType == null)
					{
						returnType = type;
					}
					else
					{
						throw new RuntimeException("Descriptor has a second main type.");
					}
				}
			}
		}
		
		if(returnType == null)
		{
			throw new RuntimeException("Descriptor does not have a main type.");
		}
		
		args = parameters.toArray(new DescriptorType[0]);
	}
	
	private static DescriptorType descriptorTypeFrom(char c, CustomStringReader reader)
	{
		if(c == 'Z')
		{
			return new DBoolean();
		}
		else if(c == 'J')
		{
			return new DLong();
		}
		else if(c == 'I')
		{
			return new DInt();
		}
		else if(c == 'D')
		{
			return new DDouble();
		}
		else if(c == 'F')
		{
			return new DFloat();
		}
		else if(c == 'C')
		{
			return new DChar();
		}
		else if(c == 'B')
		{
			return new DByte();
		}
		else if(c == 'S')
		{
			return new DShort();
		}
		else if(c == 'L')
		{
			String objectType = "";
			
			//Get the next char.
			c = reader.read();
			while(c != ';')
			{
				objectType += c;
				c = reader.read();
			}
			
			if(objectType.isEmpty())
			{
				throw new RuntimeException("Object class string was empty.");
			}
			
			return new DObject(objectType);
		}
		else if(c == 'V')
		{
			//TODO: check if returntype
			return new DVoid();
		}
		else
		{
			throw new RuntimeException("Found unknown type in descriptor: " + c);
		}
	}
	
	public DescriptorType getReturnType()
	{
		return returnType;
	}
	
	public DescriptorType[] getArgs()
	{
		return args;
	}
	
	//Copied from String class and changed to accept Objects.
	public static String join(String delimiter, Object... elements)
	{
		StringJoiner joiner = new StringJoiner(delimiter);
		for(Object cs : elements)
		{
			joiner.add(cs.toString());
		}
		return joiner.toString();
	}
	
	@Override
	public String toString()
	{
		return "Descriptor{" + (args.length == 0 ? "" : "(" + join(", ", (Object[]) args) + "):") + returnType + "}";
	}
}

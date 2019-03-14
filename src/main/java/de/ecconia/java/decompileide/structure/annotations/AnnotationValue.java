package de.ecconia.java.decompileide.structure.annotations;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import de.ecconia.java.decompileide.structure.descriptor.Descriptor;

public class AnnotationValue
{
	private final Object value;
	
	public AnnotationValue(DataInput reader, ConstantPool pool) throws IOException
	{
		//Read value:
		char tag = (char) reader.readUnsignedByte();
		switch(tag)
		{
		case 'B': //byte
		case 'C': //char
		case 'D': //double
		case 'F': //float
		case 'I': //int
		case 'J': //long
		case 'S': //short
		case 'Z': //boolean
		case 's': //String
			int poolIndex = reader.readUnsignedShort();
			value = pool.getAnnotationValue(poolIndex);
			break;
		case 'e':
			Descriptor typeName = new Descriptor(pool.getUTF(reader.readUnsignedShort()));
			String constName = pool.getUTF(reader.readUnsignedShort());
			value = typeName.getReturnType().toString().replace('/', '.') + "." + constName;
			break;
		case 'c':
			int classIndex = reader.readUnsignedShort();
			//TODO: Its a descriptor type or so?
			value = pool.getUTF(classIndex);
			break;
		case '@':
			value = new Annotation(reader, pool);
			break;
		case '[':
			int amount = reader.readUnsignedShort();
			List<AnnotationValue> entries = new ArrayList<>();
			for(int i = 0; i < amount; i++)
			{
				entries.add(new AnnotationValue(reader, pool));
			}
			value = entries;
			break;
		default:
			throw new RuntimeException("Could not find annotation type for: '" + tag + "'.");
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String toString()
	{
		if(value instanceof List)
		{
			return "{" + ((List<AnnotationValue>) value).stream().map(v -> String.valueOf(v)).collect(Collectors.joining(", ")) + "}";
		}
		
		return value.toString();
	}
}

package de.ecconia.java.decompileide.metatools.printer;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import de.ecconia.java.decompileide.structure.ClassFile;
import de.ecconia.java.decompileide.structure.attribues.Attribute;
import de.ecconia.java.decompileide.structure.descriptor.Descriptor;

public class SimplePrinter
{
	public static void printClass(ClassFile c) throws IOException
	{
		int packageIndex = c.getClassName().lastIndexOf('/');
		if(packageIndex != -1)
		{
			String packageString = c.getClassName().substring(0, c.getClassName().lastIndexOf('/'));
			System.out.println("package " + packageString.replace('/', '.') + ";");
		}
		
		ClassPrinter.print("", c);
	}
	
	public static String generateAttributeStringFilter(Map<String, Attribute> map, String... toBeFiltered)
	{
		Set<String> reps = map.values().stream()
			.filter(e -> {
				for(String tbf : toBeFiltered)
				{
					if(tbf.equals(e.getName()))
					{
						return false;
					}
				}
				return true;
			})
			.map(a -> a.toString())
			.collect(Collectors.toSet());
		
		if(reps.isEmpty())
		{
			return null;
		}
		
		return String.join(", ", reps);
	}

	public static String printParameter(Descriptor descriptor)
	{
		String tmp = "";
		
		int index = 'a';
		for(int i = 0; i < descriptor.getArgs().length; i++)
		{
			String arg = descriptor.getArgs()[i].toString().replace('/', '.');
			
			tmp += arg + " " + (char) (index++);
			
			if(i < descriptor.getArgs().length - 1)
			{
				tmp += ", ";
			}
		}
		
		return tmp;
	}
}

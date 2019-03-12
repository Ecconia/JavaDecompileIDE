package de.ecconia.java.decompileide.metatools.printer;

import java.io.IOException;

import de.ecconia.java.decompileide.structure.ClassFile;
import de.ecconia.java.decompileide.structure.Field;
import de.ecconia.java.decompileide.structure.Method;
import de.ecconia.java.decompileide.structure.modifier.ClassModifier;

public class ClassPrinter
{
	public static void print(String prefix, ClassFile c) throws IOException
	{
		//Prepare:
		String ownPrefix = "    ";
		String commented = "";
		if(c.getModifier().check(ClassModifier.SYNTHETIC))
		{
			commented = "//";
			ownPrefix = "//  ";
		}
		ownPrefix = prefix + ownPrefix;
		
		//Print Attributes:
		String attributes = SimplePrinter.generateAttributeStringFilter(c.getAttributes(), "SourceFile");
		if(attributes != null)
		{
			System.out.println(prefix + "//Attributes: " + attributes);
		}
		
		//Print Signature:
		String start = c.getModifier().toString();
//		if(!start.contains("interface"))
//		{
			//TBI: Investigate:
			start = start.replace("super", "class");
//		}
		
		
		String className = c.getClassName().substring(c.getClassName().lastIndexOf('.') + 1);
		start += className;
		if(c.getSuperClassName() != null && !c.getSuperClassName().equals("java.lang.Object") && !c.getSuperClassName().equals("java.lang.Enum"))
		{
			start += " extends " + c.getSuperClassName();
		}
		if(!c.getInterfaces().isEmpty())
		{
			start += " implements " + String.join(", ", c.getInterfaces());
		}
		System.out.println(commented + start);
		
		//Body:
		System.out.println(commented + "{");
		
		//Print Fields:
		for(Field field : c.getFields())
		{
			FieldPrinter.print(ownPrefix, field);
		}
		
		//Print separator:
		if(!c.getFields().isEmpty() && !c.getMethods().isEmpty())
		{
			System.out.println(ownPrefix);
		}
		
		//Print Methods:
		for(Method method : c.getMethods())
		{
			MethodPrinter.print(ownPrefix, className, method, c);
		}
		
		System.out.println(commented + "}");
	}
}

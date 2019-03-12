package de.ecconia.java.decompileide.metatools.printer;

import java.io.IOException;

import de.ecconia.java.decompileide.CustomDataInput;
import de.ecconia.java.decompileide.structure.ClassFile;
import de.ecconia.java.decompileide.structure.Method;
import de.ecconia.java.decompileide.structure.attribues.CodeAttribute;
import de.ecconia.java.decompileide.structure.bytecode.Bytecodes;
import de.ecconia.java.decompileide.structure.modifier.MethodModifier;

public class MethodPrinter
{
	public static void print(String prefix, String className, Method m, ClassFile c) throws IOException
	{
		String name = m.getName();
		if(name.equals("<init>"))
		{
			name = className;
		}
		
		String commented = "";
		if(m.getModifier().isSynthetic())
		{
			commented = "//";
		}
		
		String body = ";";
		boolean hasCode = m.hasAttribute("Code");
		if(hasCode)
		{
			body = "";
		}
		
		String[] pDescriptor = SimplePrinter.parseMethodDescriptor(m.getDescriptor());
		
		String attributes = SimplePrinter.generateAttributeStringFilter(m.getAttributes(), "Code", "Deprecated");
		if(attributes != null)
		{
			System.out.println(prefix + "//Attributes: " + attributes);
		}
		
		if(m.hasAttribute("Deprecated"))
		{
			System.out.println(prefix + "@Deprecated");
		}
		
		String stuff = pDescriptor[0] + " " + name + "(" + pDescriptor[1] + ")";
		if(name.equals("<clinit>") && m.getModifier().check(MethodModifier.STATIC))
		{
			stuff = "";
		}
		
		System.out.println(prefix + commented + m.getModifier().toString() + stuff + body);
		if(hasCode)
		{
			System.out.println(prefix + commented + "{");
			System.out.println(prefix + commented + "}");
		}
	}
}

package de.ecconia.java.decompileide.metatools.printer;

import java.io.IOException;

import de.ecconia.java.decompileide.CustomDataInput;
import de.ecconia.java.decompileide.structure.ClassFile;
import de.ecconia.java.decompileide.structure.Method;
import de.ecconia.java.decompileide.structure.attribues.CodeAttribute;
import de.ecconia.java.decompileide.structure.bytecode.Bytecodes;
import de.ecconia.java.decompileide.structure.descriptor.Descriptor;
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
		
		Descriptor descriptor = new Descriptor(m.getDescriptor());
		
		String attributes = SimplePrinter.generateAttributeStringFilter(m.getAttributes(), "Code", "Deprecated");
		if(attributes != null)
		{
			System.out.println(prefix + "//Attributes: " + attributes);
		}
		
		if(m.hasAttribute("Deprecated"))
		{
			System.out.println(prefix + "@Deprecated");
		}
		
		String stuff = descriptor.getReturnType().toString().replace('/', '.') + " " + name + "(" + SimplePrinter.printParameter(descriptor) + ")";
		if(name.equals("<clinit>") && m.getModifier().check(MethodModifier.STATIC))
		{
			stuff = "";
		}
		
		System.out.println(prefix + commented + m.getModifier().toString() + stuff + body);
		if(hasCode)
		{
			System.out.println(prefix + commented + "{");
			
			byte[] code = ((CodeAttribute) m.getAttribute("Code")).getCode();
			int length = code.length;
			
			CustomDataInput d = new CustomDataInput(code);
			
//			try
//			{
				while(length > 0)
				{
					int lastPos = d.getPos();
					Bytecodes.printFormatted(prefix + commented + "    ", d, c.getPool());
					int read = d.getPos() - lastPos;
					
					length -= read;
				}
				
				if(length != 0)
				{
					System.out.println(prefix + "ERROR while reading, left bytes are: " + length);
				}
//			}
//			catch(Exception e)
//			{
//				System.out.println("PARSE-ERROR");
//				e.printStackTrace(System.out);
//			}
			
			System.out.println(prefix + commented + "}");
		}
	}
}

package de.ecconia.java.decompileide.metatools.printer;

import java.io.IOException;

import de.ecconia.java.decompileide.CustomDataInput;
import de.ecconia.java.decompileide.structure.ClassFile;
import de.ecconia.java.decompileide.structure.Method;
import de.ecconia.java.decompileide.structure.attribues.CodeAttribute;
import de.ecconia.java.decompileide.structure.bytecodes.OpcodeInfo;
import de.ecconia.java.decompileide.structure.descriptor.Descriptor;
import de.ecconia.java.decompileide.structure.modifier.MethodModifier;

public class MethodPrinter
{
	public static void print(String prefix, String className, Method m, ClassFile c) throws IOException
	{
		if(m.isDeprecated())
		{
			System.out.println(prefix + "@Deprecated");
		}
		
		String attributes = SimplePrinter.generateAttributeStringFilter(m.getAttributes(), "Code");
		if(attributes != null)
		{
			System.out.println(prefix + "//Attributes: " + attributes);
		}
		
		// ### Signature ### :
		
		String commented = "";
		if(m.getModifier().isSynthetic())
		{
			commented = "//";
		}
		
		String name = m.getName();
		if(name.equals("<init>"))
		{
			name = className;
		}
		
		Descriptor descriptor = new Descriptor(m.getDescriptor());
		
		String stuff = descriptor.getReturnType().toString().replace('/', '.') + " " + name + "(" + SimplePrinter.printParameter(descriptor) + ")";
		if(name.equals("<clinit>") && m.getModifier().check(MethodModifier.STATIC))
		{
			stuff = "";
		}
		
		boolean hasCode = m.hasAttribute("Code");
		
		System.out.println(prefix + commented + m.getModifier().toString() + stuff + (hasCode ? "" : ";"));
		
		
		// ### BODY ### :
		
		if(hasCode)
		{
			CodeAttribute codeAttribute = ((CodeAttribute) m.getAttribute("Code"));
			System.out.println(prefix + commented + "{");
			if(!codeAttribute.getAttributes().isEmpty())
			{
				System.out.println(prefix + commented + "    //Attributes: " + String.join(", ", codeAttribute.getAttributes().keySet()));
			}
			
			byte[] code = codeAttribute.getCode();
			int length = code.length;
			
			CustomDataInput d = new CustomDataInput(code);
			
//			try
//			{
				while(length > 0)
				{
					int lastPos = d.getPos();
					System.out.println(prefix + commented + "    " + OpcodeInfo.instructionFromOpcode(d.readUnsignedByte(), d, c.getPool()));
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

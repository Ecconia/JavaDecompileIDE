package de.ecconia.java.decompileide.metatools.printer;

import de.ecconia.java.decompileide.structure.Field;
import de.ecconia.java.decompileide.structure.attribues.ConstantValueAttribute;
import de.ecconia.java.decompileide.structure.descriptor.Descriptor;

public class FieldPrinter
{
	public static void print(String prefix, Field f)
	{
		String attributes = SimplePrinter.generateAttributeStringFilter(f.getAttributes(), "ConstantValue");
		if(attributes != null)
		{
			System.out.println(prefix + "//Attributes: " + attributes);
		}
		
		String value = "";
		ConstantValueAttribute cv = (ConstantValueAttribute) f.getAttribute("ConstantValue");
		if(cv != null)
		{
			value = " = " + cv.getValue();
		}
		
		String commented = "";
		if(f.getModifier().isSynthetic())
		{
			commented = "//";
		}
		System.out.println(prefix + commented + f.getModifier().toString() + new Descriptor(f.getDescriptor()).getReturnType().toString().replace('/', '.') + " " + f.getName() + value + ";");
	}
}

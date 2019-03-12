package de.ecconia.java.decompileide.metatools.printer;

import de.ecconia.java.decompileide.structure.Field;

public class FieldPrinter
{
	public static void print(String prefix, Field f)
	{
		String attributes = SimplePrinter.generateAttributeStringFilter(f.getAttributes());
		if(attributes != null)
		{
			System.out.println(prefix + "//Attributes: " + attributes);
		}
		
		String commented = "";
		if(f.getModifier().isSynthetic())
		{
			commented = "//";
		}
		System.out.println(prefix + commented + f.getModifier().toString() + SimplePrinter.parseDescriptor(f.getDescriptor()) + f.getName() + ";");
	}
}

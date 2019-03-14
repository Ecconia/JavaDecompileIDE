package de.ecconia.java.decompileide.metatools.printer;

import de.ecconia.java.decompileide.structure.Field;
import de.ecconia.java.decompileide.structure.annotations.Annotation;
import de.ecconia.java.decompileide.structure.descriptor.Descriptor;

public class FieldPrinter
{
	public static void print(String prefix, Field f)
	{
		String attributes = SimplePrinter.generateAttributeStringFilter(f.getAttributes());
		if(attributes != null)
		{
			System.out.println(prefix + "//Attributes: " + attributes);
		}
		
		for(Annotation annotation : f.getAnnotations())
		{
			System.out.println(prefix + annotation);
		}
		
		String commented = "";
		if(f.getModifier().isSynthetic())
		{
			commented = "//";
		}
		
		String value = "";
		if(f.getConstantValue() != null)
		{
			value = " = " + f.getConstantValue();
		}
		
		System.out.println(prefix + commented + f.getModifier().toString() + new Descriptor(f.getDescriptor()).getReturnType().toString().replace('/', '.') + " " + f.getName() + value + ";");
	}
}

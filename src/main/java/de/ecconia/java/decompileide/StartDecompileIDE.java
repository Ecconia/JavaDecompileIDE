package de.ecconia.java.decompileide;

import java.util.ArrayList;
import java.util.List;

import de.ecconia.java.decompileide.classfinder.ClassFinder;
import de.ecconia.java.decompileide.classfinder.FoundClass;
import de.ecconia.java.decompileide.metatools.printer.SimplePrinter;
import de.ecconia.java.decompileide.structure.ClassFile;

public class StartDecompileIDE
{
	public static void main(String[] args)
	{
		//Load classes from directory:
		ClassFinder classFinder = new ClassFinder("TestClassFiles/");
		
		//Parse all classes and store them in a list:
		List<ClassFile> classes = new ArrayList<>();
		for(FoundClass foundclass : classFinder.getFoundClasses())
		{
			ClassFile clazz = ClassFile.parse(foundclass.getFile());
			if(clazz == null)
			{
				System.out.println("Ecxception while parsing class '" + foundclass.getFile().getAbsolutePath() + "'.");
			}
			else
			{
				classes.add(clazz);
			}
		}
	}
}

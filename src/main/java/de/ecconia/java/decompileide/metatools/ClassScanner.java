package de.ecconia.java.decompileide.metatools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.ecconia.java.decompileide.structure.ClassFile;
import de.ecconia.java.decompileide.structure.Field;
import de.ecconia.java.decompileide.structure.Method;

public class ClassScanner
{
	Set<String> knownClasses = new HashSet<>();
	Set<String> unknownClasses = new HashSet<>();
	
	public ClassScanner(List<ClassFile> classes)
	{
		for(ClassFile clazz : classes)
		{
			knownClasses.add(clazz.getClassName());
		}
		
		System.out.println("Known classes:");
		List<String> list = new ArrayList<String>(knownClasses);
		Collections.sort(list);
		for(String s : list)
		{
			System.out.println(" -" + s);
		}
		
		for(ClassFile clazz : classes)
		{
			checkClass(clazz.getSuperClassName());
			for(String interfaceName : clazz.getInterfaces())
			{
				checkClass(interfaceName);
			}
			for(Field field : clazz.getFields())
			{
				scanDescriptor(field.getDescriptor());
			}
			for(Method method : clazz.getMethods())
			{
				scanDescriptor(method.getDescriptor());
			}
		}
		
		System.out.println();
		System.out.println("Unknown classes:");
		list = new ArrayList<String>(unknownClasses);
		Collections.sort(list);
		for(String s : list)
		{
			System.out.println(" -" + s);
		}
	}
	
	private void scanDescriptor(String descriptor)
	{
		descriptor = descriptor.replace('/', '.');
		
		for(int i = 0; i < descriptor.length(); i++)
		{
			char c = descriptor.charAt(i);
			
			if(c == 'L')
			{
				c = descriptor.charAt(++i);
				
				String className = "";
				while(c != ';')
				{
					className += c;
					
					c = descriptor.charAt(++i);
				}
				
				checkClass(className);
			}
		}
	}
	
	private void checkClass(String className)
	{
		if(!knownClasses.contains(className))
		{
			unknownClasses.add(className);
		}
	}
}

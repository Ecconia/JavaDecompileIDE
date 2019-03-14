package de.ecconia.java.decompileide.tools.javasourcegen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClassGen
{
	private final String thisPath;
	private final String thisClass;
	
	private String extendsClass;
	
	private final Set<String> imports = new HashSet<>();
	private final List<MethodGen> methods = new ArrayList<>();
	private final List<String> fields = new ArrayList<>();
	
	public ClassGen(String packageName, String className)
	{
		thisPath = packageName;
		thisClass = className;
	}
	
	public void addField(String field)
	{
		fields.add(field);
	}
	
	public void extend(String packageName, String className)
	{
		if(packageName != null)
		{
			imports.add(packageName + '.' + className);
		}
		
		extendsClass = className;
	}
	
	public void addMethod(MethodGen method)
	{
		methods.add(method);
		imports.addAll(method.getImports());
	}
	
	//Builder:
	
	String code = null;
	String prefix = "";
	
	public String build()
	{
		code = "";
		
		if(thisPath != null && !thisPath.isEmpty())
		{
			add("package " + thisPath + ";");
			nl();
		}
		
		if(!imports.isEmpty())
		{
			for(String i : imports)
			{
				add("import " + i + ";");
			}
			nl();
		}
		
		add("/* Automatically generated class, do not edit. */");
		
		String extendsString = "";
		if(extendsClass != null)
		{
			extendsString = " extends " + extendsClass;
		}
		
		add("public class " + thisClass + extendsString);
		add("{");
		prefix = "\t";
		
		if(fields.size() > 0)
		{
			for(int i = 0; i < fields.size(); i++)
			{
				add(fields.get(i));
			}
			
			if(!methods.isEmpty())
			{
				nl();
			}
		}
		
		for(int i = 0; i < methods.size(); i++)
		{
			MethodGen m = methods.get(i);
			
			for(String s : m.getLines())
			{
				add(s);
			}
			
			if(i < methods.size() - 1)
			{
				nl();
			}
		}
		
		
		prefix = "";
		add("}");
		
		return code;
	}
	
	private void add(String content)
	{
		code += prefix + content + '\n';
	}
	
	private void nl()
	{
		code += prefix + '\n';
	}
	
	public void write(File file) throws IOException
	{
		if(code == null)
		{
			build();
		}
		
		FileWriter fw = new FileWriter(file);
		fw.write(code);
		fw.close();
	}

	public void addImport(String string)
	{
		imports.add(string);
	}
}

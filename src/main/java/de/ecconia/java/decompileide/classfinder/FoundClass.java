package de.ecconia.java.decompileide.classfinder;

import java.io.File;

public class FoundClass
{
	private final File file;
	private final String path;
	private final String name;
	
	public FoundClass(String path, String name, File file)
	{
		this.file = file;
		this.path = path;
		this.name = name;
	}

	public File getFile()
	{
		return file;
	}

	public String getClassname()
	{
		return name;
	}

	public String getPackage()
	{
		return path;
	}
}

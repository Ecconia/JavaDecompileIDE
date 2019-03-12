package de.ecconia.java.decompileide.classfinder;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClassFinder
{
	private final int rootPathIndex;
	private List<FoundClass> foundClasses = new ArrayList<>();
	
	public ClassFinder(String string)
	{
		File root = new File(string);
		if(!root.exists())
		{
			throw new RuntimeException("Folder to be scanned for classes does not exist.");
		}
		
		if(!root.isDirectory())
		{
			throw new RuntimeException("Provided path to scan classes is not a directory.");
		}
		
		rootPathIndex = root.getAbsolutePath().length();
		
		scanFolder(root);
	}
	
	private void scanFolder(File folder)
	{
		for(File sub : folder.listFiles())
		{
			if(sub.isDirectory())
			{
				scanFolder(sub);
			}
			else
			{
				String filepath = sub.getAbsolutePath().substring(rootPathIndex);
				
				String filename = sub.getName();
				int index = filename.lastIndexOf('.');
				if(index == -1)
				{
					continue;
				}
				
				String fileending = filename.substring(index + 1);
				if(!fileending.equals("class"))
				{
					continue;
				}
				
				//Getting rid of the obvious.
				filename = filename.substring(0, index);
				
				index = filepath.lastIndexOf('/');
				if(index > 1)
				{
					filepath = filepath.substring(1, index).replace('/', '.');
				}
				
				addClass(filepath, filename, sub);
			}
		}
	}
	
	private void addClass(String filepath, String filename, File sub)
	{
		try
		{
			DataInputStream di = new DataInputStream(new FileInputStream(sub));
			int majik = di.readInt();
			if(majik != 0xCAFEBABE)
			{
				//File is not a classfile obmit.
				System.out.println("File: " + filepath + "." + filename + " Is not a class file, magic value wrong.");
				di.close();
			}
			else
			{
				di.close();
				
				foundClasses.add(new FoundClass(filepath, filename, sub));
			}
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public List<FoundClass> getFoundClasses()
	{
		return foundClasses;
	}
}

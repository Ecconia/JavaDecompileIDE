package de.ecconia.java.decompileide.metatools.printer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import de.ecconia.java.decompileide.structure.ClassFile;
import de.ecconia.java.decompileide.structure.attribues.Attribute;

public class SimplePrinter
{
	public static void printClass(ClassFile c) throws IOException
	{
		int packageIndex = c.getClassName().lastIndexOf('.');
		if(packageIndex != -1)
		{
			String packageString = c.getClassName().substring(0, c.getClassName().lastIndexOf('.'));
			System.out.println("package " + packageString + ";");
		}
		
		ClassPrinter.print("", c);
	}
	
	public static String generateAttributeStringFilter(Map<String, Attribute> map, String... toBeFiltered)
	{
		Set<String> reps = map.values().stream()
			.filter(e -> {
				for(String tbf : toBeFiltered)
				{
					if(tbf.equals(e.getName()))
					{
						return false;
					}
				}
				return true;
			})
			.map(a -> a.toString())
			.collect(Collectors.toSet());
		
		if(reps.isEmpty())
		{
			return null;
		}
		
		return String.join(", ", reps);
	}
	
	//TODO: Replace improve descriptor parsing.
	
	static String[] parseMethodDescriptor(String in)
	{
		in = in.replace('/', '.');
		
		String returnType = null;
		List<String> parameter = new ArrayList<>();
		
		boolean parameterScope = false;
		String tmp = "";
		
		for(int i = 0; i < in.length(); i++)
		{
			char c = in.charAt(i);
			if(c == '(')
			{
				parameterScope = true;
			}
			else if(c == ')')
			{
				parameterScope = false;
			}
			else if(c == '[')
			{
				tmp += "[]";
			}
			else
			{
				String type = descriptorTypeFrom(c);
				if(type == null)
				{
					//TODO:
					throw new RuntimeException("Finish implementing dat parseDescriptor: " + c + " <- " + in);
				}
				else if(type.equals("L"))
				{
					c = in.charAt(++i);
					
					String ftype = "";
					while(c != ';')
					{
						ftype += c;
						
						c = in.charAt(++i);
					}
					
					ftype += tmp;
					tmp = "";
					
					if(parameterScope)
					{
						parameter.add(ftype);
					}
					else
					{
						if(returnType != null)
						{
							throw new RuntimeException("Uff, the return type in a descriptor was already set: " + in);
						}
						else
						{
							returnType = ftype;
						}
					}
				}
				else
				{
					String ftype = type + tmp;
					tmp = "";
					if(parameterScope)
					{
						parameter.add(ftype);
					}
					else
					{
						if(returnType != null)
						{
							throw new RuntimeException("Uff, the return type in a descriptor was already set: " + in);
						}
						else
						{
							returnType = ftype;
						}
					}
				}
			}
		}
		
		int index = 'a';
		List<String> array = new ArrayList<>();
		for(String s : parameter)
		{
			array.add(s + " " + (char) (index++));
		}
		
		return new String[] {returnType, String.join(", ", array)};
	}
	
	public static String parseDescriptor(String in)
	{
		in = in.replace('/', '.');
		
		String out = "";
		for(int i = 0; i < in.length(); i++)
		{
			char c = in.charAt(i);
			if(c == 'L')
			{
				c = in.charAt(++i);
				
				while(c != ';')
				{
					out += c;
					
					c = in.charAt(++i);
				}
				
				out += ' ';
			}
			else if(c == '[')
			{
				String tmp = "[]";
				while((c = in.charAt(++i)) == '[')
				{
					tmp += "[]";
				}
				
				String next = descriptorTypeFrom(c);
				if(next.equals("L"))
				{
					next = "";
					c = in.charAt(++i);
					
					while(c != ';')
					{
						next += c;
						
						c = in.charAt(++i);
					}
				}
				
				out += next + tmp + ' ';
			}
			else if(c == '(')
			{
				out += '(';
			}
			else if(c == ')')
			{
				out += ')';
			}
			else
			{
				String type = descriptorTypeFrom(c);
				if(type == null)
				{
					throw new RuntimeException("Finish implementing dat parseDescriptor: " + c + " <- " + in);
					//TODO:
				}
				else
				{
					out += type + " ";
				}
			}
		}
		
		return out;
	}
	
	private static String descriptorTypeFrom(char c)
	{
		if(c == 'Z')
		{
			return "boolean";
		}
		else if(c == 'J')
		{
			return "long";
		}
		else if(c == 'I')
		{
			return "int";
		}
		else if(c == 'D')
		{
			return "double";
		}
		else if(c == 'F')
		{
			return "float";
		}
		else if(c == 'C')
		{
			return "char";
		}
		else if(c == 'B')
		{
			return "byte";
		}
		else if(c == 'S')
		{
			return "short";
		}
		else if(c == '[')
		{
			return "[]";
		}
		else if(c == 'L')
		{
			return "L";
		}
		else if(c == 'V')
		{
			return "void";
		}
		else
		{
			return null;
		}
	}
}

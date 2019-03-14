package de.ecconia.java.decompileide.tools.javasourcegen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MethodGen
{
	private final List<String> lines = new ArrayList<>();
	private final Map<String, String> replacers = new HashMap<>();
	//TODO: Support more than one occurances:
	private final Map<String, Integer> foundReplacings = new HashMap<>();
	
	private final String prefix;
	private final String name;
	
	private final Set<String> imports = new HashSet<>();
	private final Set<String> throwed = new HashSet<>();
	private final List<String> parameter = new ArrayList<>();
	
	public final Set<String> getImports()
	{
		return imports;
	}
	
	public MethodGen(String prefix, String methodName)
	{
		this.prefix = prefix;
		this.name = methodName;
	}
	
	public void addParameter(String path, String type, String name)
	{
		if(path != null)
		{
			imports.add(path + '.' + type);
		}
		parameter.add(type + ' ' + name);
	}

	private boolean doesOverwrite = false;
	
	public void doesOverwrite()
	{
		offset++;
		doesOverwrite = true;
	}
	
	public void addThrows(String path, String type)
	{
		if(path != null)
		{
			imports.add(path + '.' + type);
		}
		
		throwed.add(type);
	}

	private String[] code;
	private int offset = 1; 
	
	public void build()
	{
		code = new String[lines.size() + 3 + (doesOverwrite ? 1 : 0)];
		int pointer = 0;
		
		if(doesOverwrite)
		{
			code[pointer++] = "@Override";
		}
		code[pointer++] = prefix + ' ' + name + '(' + String.join(", ", parameter) + ')' + (throwed.isEmpty() ? "" : " throws " + String.join(", ", throwed));
		code[pointer++] = "{";
		
		for(String s : lines)
		{
			code[pointer++] = '\t' + s;
		}
		
		code[pointer++] = "}";
	}
	
	public void replace(String key, String value)
	{
		replacers.put(key, value);
	}
	
	public String[] getLines()
	{
		String[] code2 = new String[code.length];
		System.arraycopy(code, 0, code2, 0, code.length);
		
		for(Entry<String, String> entry : replacers.entrySet())
		{
			int position = foundReplacings.get(entry.getKey()) + offset;
			code2[position] = code2[position].replace("{{{" + entry.getKey() + "]]]", entry.getValue());
		}
		
		return code2;
	}
	
	public void addLine(String string)
	{
		lines.add(string);
		
		int i = 0;
		String key = "";
		for(char c : string.toCharArray())
		{
			if(i < 3)
			{
				if(c == '{')
				{
					//Inc counter, found opener.
					i++;
				}
				else
				{
					if(i > 0)
					{
						//Reset counter.
						i = 0;
					}
				}
			}
			else if(i == 3)
			{
				if(c == '{')
				{
					//Nothing.
				}
				else if(c == ']')
				{
					//Okay, next stage.
					i++;
				}
				else if((c >= 'a' && c <= 'z') ||(c >= 'A' && c <= 'Z') ||(c >= '0' && c <= '9'))
				{
					key += c;
				}
				else
				{
					i = 0;
					key = "";
				}
			}
			else if(i < 6)
			{
				if(c == ']')
				{
					//Inc counter, found opener.
					i++;
				}
				else
				{
					//Reset counter.
					i = 0;
					key = "";
				}
			}
			else
			{
				foundReplacings.put(key, lines.size());
				i = 0;
				key = "";
			}
		}
	}
}

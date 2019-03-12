package de.ecconia.java.decompileide.structure.constantpool;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ConstantPool
{
	private final ConstantPoolEntry[] entries;
	
	public ConstantPool(ConstantPoolEntry[] entries)
	{
		this.entries = entries;
		
		//TBI: Either pre-resolve here and be okay with a bit of useless method calls. Or on call with a bit more useless method call. Or an ordered resolve with once useless loops but then done...
		for(ConstantPoolEntry entry : entries)
		{
			if(entry != null)
			{
				entry.resolve(this);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T extends ConstantPoolEntry> T getEntry(int index, Class<? extends ConstantPoolEntry>... classes)
	{
		ConstantPoolEntry entry = getEntry(index);
		for(Class<? extends ConstantPoolEntry> clazz : classes)
		{
			if(clazz.isInstance(entry))
			{
				return (T) entry;
			}
		}
		
		throw new ConstantPoolException("Tried to get (" + Arrays.asList(classes).stream().map(Class::getSimpleName).collect(Collectors.joining(", ")) + ") from constant pool, but found " + entry.getClass().getSimpleName());
	}
	
	public ConstantPoolEntry getEntry(int index)
	{
		if(index >= entries.length)
		{
			throw new ConstantPoolException("Tried to access constant pool outside of its capacity " + index + "/" + entries.length + ".");
		}
		ConstantPoolEntry entry = entries[index];
		if(entry == null)
		{
			throw new ConstantPoolException("Tried to access constant pool field " + index + ", but found null.");
		}
		return entry;
	}
	
	//#########################################################################
	//Getters for whatever the heart desires.
	
	public String getUtf8(int index)
	{
		@SuppressWarnings("unchecked")
		CPUtf8 entry = getEntry(index, CPUtf8.class);
		//Always loaded.
		return entry.getValue();
	}
	
	public String getClassName(int index)
	{
		@SuppressWarnings("unchecked")
		CPClass entry = getEntry(index, CPClass.class);
		entry.resolve(this); //Ensure its loaded.
		return entry.getName();
	}
	
	public String getNameAndTypeName(int index)
	{
		@SuppressWarnings("unchecked")
		CPNameAndType entry = getEntry(index, CPNameAndType.class);
		entry.resolve(this); //Ensure its loaded.
		return entry.getName();
	}
	
	public String getNameAndTypeType(int index)
	{
		@SuppressWarnings("unchecked")
		CPNameAndType entry = getEntry(index, CPNameAndType.class);
		entry.resolve(this); //Ensure its loaded.
		return entry.getDescriptor();
	}

	public String getFieldRefName(int index)
	{
		@SuppressWarnings("unchecked")
		CPFieldRef entry = getEntry(index, CPFieldRef.class);
		entry.resolve(this); //Ensure its loaded.
		return entry.getName();
	}
	
	public String getFieldRefClassName(int index)
	{
		@SuppressWarnings("unchecked")
		CPFieldRef entry = getEntry(index, CPFieldRef.class);
		entry.resolve(this); //Ensure its loaded.
		return entry.getClassName();
	}
	
	public String getFieldRefDescriptor(int index)
	{
		@SuppressWarnings("unchecked")
		CPFieldRef entry = getEntry(index, CPFieldRef.class);
		entry.resolve(this); //Ensure its loaded.
		return entry.getDescriptor();
	}

	public String getMethodRefName(int index)
	{
		@SuppressWarnings("unchecked")
		CPMethodRef entry = getEntry(index, CPMethodRef.class);
		entry.resolve(this); //Ensure its loaded.
		return entry.getName();
	}
	
	public String getMethodRefClassName(int index)
	{
		@SuppressWarnings("unchecked")
		CPMethodRef entry = getEntry(index, CPMethodRef.class);
		entry.resolve(this); //Ensure its loaded.
		return entry.getClassName();
	}
	
	public String getMethodRefDescriptor(int index)
	{
		@SuppressWarnings("unchecked")
		CPMethodRef entry = getEntry(index, CPMethodRef.class);
		entry.resolve(this); //Ensure its loaded.
		return entry.getDescriptor();
	}
	
	public String getInterfaceMethodRefName(int index)
	{
		@SuppressWarnings("unchecked")
		CPInterfaceMethodRef entry = getEntry(index, CPInterfaceMethodRef.class);
		entry.resolve(this); //Ensure its loaded.
		return entry.getName();
	}
	
	public String getInterfaceMethodRefClassName(int index)
	{
		@SuppressWarnings("unchecked")
		CPInterfaceMethodRef entry = getEntry(index, CPInterfaceMethodRef.class);
		entry.resolve(this); //Ensure its loaded.
		return entry.getClassName();
	}
	
	public String getInterfaceMethodRefDescriptor(int index)
	{
		@SuppressWarnings("unchecked")
		CPInterfaceMethodRef entry = getEntry(index, CPInterfaceMethodRef.class);
		entry.resolve(this); //Ensure its loaded.
		return entry.getDescriptor();
	}

	public String getMethodRefOrInterfaceMethodRefClassName(int index)
	{
		ConstantPoolEntry entry = getEntry(index);
		entry.resolve(this);
		if(entry instanceof CPMethodRef)
		{
			return ((CPMethodRef) entry).getClassName();
		}
		else if(entry instanceof CPInterfaceMethodRef)
		{
			return ((CPInterfaceMethodRef) entry).getClassName();
		}
		else
		{
			throw new ConstantPoolException("Tried to get CPMethodRef or CPInterfaceMethodRef from constant pool, but found " + entry.getClass().getSimpleName());
		}
	}

	public String getMethodRefOrInterfaceMethodRefName(int index)
	{
		ConstantPoolEntry entry = getEntry(index);
		entry.resolve(this);
		if(entry instanceof CPMethodRef)
		{
			return ((CPMethodRef) entry).getName();
		}
		else if(entry instanceof CPInterfaceMethodRef)
		{
			return ((CPInterfaceMethodRef) entry).getName();
		}
		else
		{
			throw new ConstantPoolException("Tried to get CPMethodRef or CPInterfaceMethodRef from constant pool, but found " + entry.getClass().getSimpleName());
		}
	}

	public String getMethodRefOrInterfaceMethodRefDescriptor(int index)
	{
		ConstantPoolEntry entry = getEntry(index);
		entry.resolve(this);
		if(entry instanceof CPMethodRef)
		{
			return ((CPMethodRef) entry).getDescriptor();
		}
		else if(entry instanceof CPInterfaceMethodRef)
		{
			return ((CPInterfaceMethodRef) entry).getDescriptor();
		}
		else
		{
			throw new ConstantPoolException("Tried to get CPMethodRef or CPInterfaceMethodRef from constant pool, but found " + entry.getClass().getSimpleName());
		}
	}
}

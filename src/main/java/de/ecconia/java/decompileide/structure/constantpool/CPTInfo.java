package de.ecconia.java.decompileide.structure.constantpool;

import java.io.DataInput;
import java.lang.reflect.Constructor;

import de.ecconia.java.decompileide.structure.constantpool.entrytypes.direct.CPTDouble;
import de.ecconia.java.decompileide.structure.constantpool.entrytypes.direct.CPTFloat;
import de.ecconia.java.decompileide.structure.constantpool.entrytypes.direct.CPTInteger;
import de.ecconia.java.decompileide.structure.constantpool.entrytypes.direct.CPTLong;
import de.ecconia.java.decompileide.structure.constantpool.entrytypes.direct.CPTUTF;
import de.ecconia.java.decompileide.structure.constantpool.entrytypes.first.CPTClass;
import de.ecconia.java.decompileide.structure.constantpool.entrytypes.first.CPTMethodType;
import de.ecconia.java.decompileide.structure.constantpool.entrytypes.first.CPTString;
import de.ecconia.java.decompileide.structure.constantpool.entrytypes.first.CPTTypeName;
import de.ecconia.java.decompileide.structure.constantpool.entrytypes.second.CPTField;
import de.ecconia.java.decompileide.structure.constantpool.entrytypes.second.CPTInterfaceMethod;
import de.ecconia.java.decompileide.structure.constantpool.entrytypes.second.CPTInvokeDynamic;
import de.ecconia.java.decompileide.structure.constantpool.entrytypes.second.CPTMethod;
import de.ecconia.java.decompileide.structure.constantpool.entrytypes.third.CPTMethodHandle;

public class CPTInfo
{
	private final static CPTInfo[] types = {
		null,
		new CPTInfo(1, "UTF", 4, CPTUTF.class),
		null,
		new CPTInfo(3, "int", 4, CPTInteger.class),
		new CPTInfo(4, "float", 4, CPTFloat.class),
		new CPTInfo(5, "long", 8, CPTLong.class),
		new CPTInfo(6, "double", 8, CPTDouble.class),
		new CPTInfo(7, "Class", 2, CPTClass.class),
		new CPTInfo(8, "String", 2, CPTString.class),
		new CPTInfo(9, "Field", 4, CPTField.class),
		new CPTInfo(10, "Method", 4, CPTMethod.class),
		new CPTInfo(11, "InterfaceMethod", 4, CPTInterfaceMethod.class),
		new CPTInfo(12, "NameAndType", 4, CPTTypeName.class),
		null,
		null,
		new CPTInfo(15, "MethodHandle", 4, CPTMethodHandle.class),
		new CPTInfo(16, "MethodType", 2, CPTMethodType.class),
		null,
		new CPTInfo(18, "InvokeDynamic", 4, CPTInvokeDynamic.class),
	};
	
	public static CPTInfo getCPTInfo(int index)
	{
		if(index >= types.length || index < 0)
		{
			return null;
		}
		
		return types[index];
	}
	
	//#########################################################################
	
	private final Constructor<? extends CPEntry> constructor;
	
	public CPTInfo(int id, String name, int size, Class<? extends CPEntry> clazz)
	{
		try
		{
			constructor = clazz.getConstructor(new Class[] {Integer.TYPE, DataInput.class});
		}
		catch(NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
			throw new Error("Something went wrong initializing ConstantPoolLib.");
		}
	}
	
	public Constructor<? extends CPEntry> getConstructor()
	{
		return constructor;
	}
}

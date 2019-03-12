package de.ecconia.java.decompileide.structure.bytecode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.ecconia.java.decompileide.CustomDataInput;
import de.ecconia.java.decompileide.structure.constantpool.CPClass;
import de.ecconia.java.decompileide.structure.constantpool.CPDouble;
import de.ecconia.java.decompileide.structure.constantpool.CPFloat;
import de.ecconia.java.decompileide.structure.constantpool.CPInteger;
import de.ecconia.java.decompileide.structure.constantpool.CPInterfaceMethodRef;
import de.ecconia.java.decompileide.structure.constantpool.CPInvokeDynamic;
import de.ecconia.java.decompileide.structure.constantpool.CPLong;
import de.ecconia.java.decompileide.structure.constantpool.CPMethodHandle;
import de.ecconia.java.decompileide.structure.constantpool.CPMethodRef;
import de.ecconia.java.decompileide.structure.constantpool.CPMethodType;
import de.ecconia.java.decompileide.structure.constantpool.CPString;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPoolEntry;

public class BytecodeWTArgs extends Bytecode
{
	public static enum ArgType
	{
		PoolIndexInvokeDynamic,
		PoolIndexInterfaceMethodRef,
		PoolIndexMethodRef,
		PoolIndexMethodOrInterfaceMethodRef,
		PoolIndexClassRef,
		PoolIndexFieldRef,
		
		PoolIndexValue,
		PoolSmallIndexValue,
		PoolIndexBigValue,
		
		RelBranchLoc,
		RelBranchLocInt,
		Short,
		Byte,
		UByte,
		Var,
		
		Align,
		Int,
		NPairs,
		Range,
		CustomWide,
		;
	}
	
	private final ArgType[] argTypes;
	
	public BytecodeWTArgs(String name, ArgType... argTypes)
	{
		super(name);
		
		this.argTypes = argTypes;
	}
	
	@Override
	public String[] parse(CustomDataInput d, ConstantPool pool) throws IOException
	{
		List<String> ret = new ArrayList<>();
		
		for(ArgType type : argTypes)
		{
//			System.out.println(type + " " + name);
			switch(type)
			{
			case PoolIndexMethodOrInterfaceMethodRef:
			{
				int index = d.readUnsignedShort();
				@SuppressWarnings("unchecked")
				ConstantPoolEntry entry = pool.getEntry(index, CPMethodRef.class, CPInterfaceMethodRef.class);
				
				//TODO: Formatting, once parsed
				ret.add(entry.toString());
				break;
			}
			case PoolIndexInvokeDynamic:
			{
				int index = d.readUnsignedShort();
				@SuppressWarnings("unchecked")
				CPInvokeDynamic entry = pool.getEntry(index, CPInvokeDynamic.class);
				
				//TODO: Formatting, once parsed
				ret.add(entry.toString());
				break;
			}
			case PoolIndexInterfaceMethodRef:
			{
				int index = d.readUnsignedShort();
				String name = pool.getInterfaceMethodRefName(index);
				String className = pool.getInterfaceMethodRefClassName(index);
				String descriptor = pool.getInterfaceMethodRefDescriptor(index);
				
				ret.add(className + ":" + name + " {" + descriptor + "}");
				break;
			}
			case PoolIndexMethodRef:
			{
				int index = d.readUnsignedShort();
				String name = pool.getMethodRefName(index);
				String className = pool.getMethodRefClassName(index);
				String descriptor = pool.getMethodRefDescriptor(index);
				
				ret.add(className + ":" + name + " {" + descriptor + "}");
				break;
			}
			case PoolIndexClassRef:
			{
				int index = d.readUnsignedShort();
				String name = pool.getClassName(index);
				
				ret.add(name);
				break;
			}
			case PoolIndexFieldRef:
			{
				int index = d.readUnsignedShort();
				String name = pool.getFieldRefName(index);
				String className = pool.getFieldRefClassName(index);
				String descriptor = pool.getFieldRefDescriptor(index);
				
				ret.add(className + ":" + name + " {" + descriptor + "}");
				break;
			}
			case RelBranchLoc:
			{
				short amount = d.readShort();
				String sign = "";
				if(amount > 0)
				{
					sign = "+";
				}
				else if(amount == 0)
				{
					sign = "~";
				}
				
				ret.add(sign + String.valueOf(amount));
				break;
			}
			case RelBranchLocInt:
			{
				int amount = d.readInt();
				String sign = "";
				if(amount > 0)
				{
					sign = "+";
				}
				else if(amount == 0)
				{
					sign = "~";
				}
				
				ret.add(sign + String.valueOf(amount));
				break;
			}
			case Short:
			{
				//TBI signed?
				short amount = d.readShort();
				ret.add(String.valueOf(amount));
				break;
			}
			case Byte:
			{
				//TBI signed?
				short amount = d.readByte();
				ret.add(String.valueOf(amount));
				break;
			}
			case UByte:
			{
				//TBI signed?
				int amount = d.readUnsignedByte();
				ret.add(String.valueOf(amount));
				break;
			}
			case Var:
			{
				int index = d.readUnsignedByte();
				
				ret.add("Var" + index);
				break;
			}
			case PoolIndexValue:
			{
				int index = d.readUnsignedShort();
				@SuppressWarnings("unchecked")
				ConstantPoolEntry entry = pool.getEntry(index, CPString.class, CPInteger.class, CPFloat.class, CPClass.class, CPMethodType.class, CPMethodHandle.class);
				
				ret.add(entry.toString());
				break;
			}
			case PoolSmallIndexValue:
			{
				int index = d.readUnsignedByte();
				@SuppressWarnings("unchecked")
				ConstantPoolEntry entry = pool.getEntry(index, CPString.class, CPInteger.class, CPFloat.class, CPClass.class, CPMethodType.class, CPMethodHandle.class);
				
				ret.add(entry.toString());
				break;
			}
			case PoolIndexBigValue:
			{
				int index = d.readUnsignedShort();
				@SuppressWarnings("unchecked")
				ConstantPoolEntry entry = pool.getEntry(index, CPLong.class, CPDouble.class);
				
				ret.add(entry.toString());
				break;
			}
			case Align:
			{
				d.alignTo4();
				break;
			}
			case Int:
			{
				int amount = d.readInt();
				ret.add(String.valueOf(amount));
				break;
			}
			case NPairs:
			{
				int amount = d.readInt();
				String tmp = amount + " -> {";
					
				for(int i = 0; i < amount; i++)
				{
					tmp += d.readInt() + " - " + d.readInt();
					
					if(i < amount - 1)
					{
						tmp += ", ";
					}
				}
				
				tmp += "}";
				
				ret.add(tmp);
				break;
			}
			case Range:
			{
				int min = d.readInt();
				int max = d.readInt();
				
				int amount = max - min + 1;
				
				System.out.println(max + "-" + min + " -> " + amount + " ~ " + amount*4);
				System.out.println("At " + d.getPos() + "/" + d.getMax() + " ~ " + (d.getMax() - d.getPos()));
				
				String tmp = min + "-" + max + " -> {";
					
				for(int i = 0; i < amount; i++)
				{
					int v = d.readInt();
					System.out.println(v);
					tmp += v;
					
					if(i < amount - 1)
					{
						tmp += ", ";
					}
				}
				
				tmp += "}";
				
				ret.add(tmp);
				break;
			}
			case CustomWide:
			{
				int opcode = d.readUnsignedByte();
				String instructionName = Bytecodes.getNameByOpcode(opcode);
				ret.add(instructionName);
				
				int index = d.readUnsignedShort();
				ret.add("Var" + index);
				
				if(instructionName.equals("iirc"))
				{
					//Format "2"
					int constant = d.readUnsignedShort();
					ret.add(String.valueOf(constant));
				}
				else
				{
					//Format "1"
					//TODO: Opcode validation: iload, fload, aload, lload, dload, istore, fstore, astore, lstore, dstore, ret
				}
			}
			}
		}
		
		return ret.toArray(new String[0]);
	}
}

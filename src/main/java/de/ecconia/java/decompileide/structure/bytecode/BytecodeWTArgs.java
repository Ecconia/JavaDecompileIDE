package de.ecconia.java.decompileide.structure.bytecode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.ecconia.java.decompileide.CustomDataInput;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;
import de.ecconia.java.decompileide.structure.constantpool.contypes.BootstrapMethod;
import de.ecconia.java.decompileide.structure.constantpool.contypes.ClassTypeName;
import de.ecconia.java.decompileide.structure.descriptor.Descriptor;

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
				ClassTypeName data = pool.getMethodOrInterfaceMethod(index);
				ret.add((data.getClazz() + ":" + data.getName() + " {" + data.getType() + "}").replace('/', '.'));
				break;
			}
			case PoolIndexInvokeDynamic:
			{
				int index = d.readUnsignedShort();
				BootstrapMethod data = pool.getBootstrapMethod(index);
				ret.add((data.getName() + " {" + data.getType() + "} @" + data.getIndex()).replace('/', '.'));
				break;
			}
			case PoolIndexInterfaceMethodRef:
			{
				int index = d.readUnsignedShort();
				ClassTypeName data = pool.getInterfaceMethod(index);
				ret.add((data.getClazz() + ":" + data.getName() + " {" + data.getType() + "}").replace('/', '.'));
				break;
			}
			case PoolIndexMethodRef:
			{
				int index = d.readUnsignedShort();
				ClassTypeName data = pool.getMethod(index);
				ret.add((data.getClazz() + ":" + data.getName() + " {" + data.getType() + "}").replace('/', '.'));
				break;
			}
			case PoolIndexClassRef:
			{
				int index = d.readUnsignedShort();
				String name = pool.getClass(index);
				ret.add(name.replace('/', '.'));
				break;
			}
			case PoolIndexFieldRef:
			{
				int index = d.readUnsignedShort();
				ClassTypeName data = pool.getField(index);
				ret.add((data.getClazz() + ":" + data.getName() + " {" + data.getType() + "}").replace('/', '.'));
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
				short amount = d.readShort();
				ret.add(String.valueOf(amount));
				break;
			}
			case Byte:
			{
				short amount = d.readByte();
				ret.add(String.valueOf(amount));
				break;
			}
			case UByte:
			{
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
				Object value = pool.getSmallValue(index);
				
				if(value instanceof ClassTypeName)
				{
					ClassTypeName data = (ClassTypeName) value;
					ret.add((data.getClazz() + ":" + data.getName() + " {" + data.getType() + "}").replace('/', '.'));
				}
				else if(value instanceof Descriptor)
				{
					ret.add("{" + value.toString().replace('/', '.') + "}");
				}
				else if(value instanceof String)
				{
					ret.add("\"" + value.toString() + "\"");
				}
				else
				{
					ret.add(value.toString());
				}
				break;
			}
			case PoolSmallIndexValue:
			{
				int index = d.readUnsignedByte();
				Object value = pool.getSmallValue(index);
				
				if(value instanceof ClassTypeName)
				{
					ClassTypeName data = (ClassTypeName) value;
					ret.add((data.getClazz() + ":" + data.getName() + " {" + data.getType() + "}").replace('/', '.'));
				}
				else if(value instanceof Descriptor)
				{
					ret.add("{" + value.toString().replace('/', '.') + "}");
				}
				else if(value instanceof String)
				{
					ret.add("\"" + value.toString() + "\"");
				}
				else
				{
					ret.add(value.toString());
				}
				break;
			}
			case PoolIndexBigValue:
			{
				int index = d.readUnsignedShort();
				Object value = pool.getBigValue(index);
				ret.add(value.toString());
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
				
				String tmp = min + "-" + max + " -> {";
					
				for(int i = 0; i < amount; i++)
				{
					int v = d.readInt();
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

package de.ecconia.java.decompileide.structure.bytecodes2;

import java.io.File;
import java.io.IOException;

import de.ecconia.java.decompileide.tools.javasourcegen.ClassGen;
import de.ecconia.java.decompileide.tools.javasourcegen.MethodGen;

public class GenerateOpcodeClasses
{
	private static final String[] opcodes = {
		"Nop",
		"AConst_Null",
		"AConst_m1",
		"IConst_0",
		"IConst_1",
		"IConst_2",
		"IConst_3",
		"IConst_4",
		"IConst_5",
		"LConst_0",
		"LConst_1",
		"FConst_0",
		"FConst_1",
		"FConst_2",
		"DConst_0",
		"DConst_1",
		"BIPush Byte",
		"SIPush Short",
		"LDC PoolSmallIndexValue",
		"LDC_w PoolIndexValue",
		"LDC2_w PoolIndexBigValue",
		"ILoad Var",
		"LLoad Var",
		"FLoad Var",
		"DLoad Var",
		"ALoad Var",
		"ILoad_0",
		"ILoad_1",
		"ILoad_2",
		"ILoad_3",
		"LLoad_0",
		"LLoad_1",
		"LLoad_2",
		"LLoad_3",
		"FLoad_0",
		"FLoad_1",
		"FLoad_2",
		"FLoad_3",
		"DLoad_0",
		"DLoad_1",
		"DLoad_2",
		"DLoad_3",
		"ALoad_0",
		"ALoad_1",
		"ALoad_2",
		"ALoad_3",
		"IAload",
		"LAload",
		"FAload",
		"DAload",
		"AAload",
		"BAload",
		"CAload",
		"SAload",
		"IStore Var",
		"LStore Var",
		"FStore Var",
		"DStore Var",
		"AStore Var",
		"IStore_0",
		"IStore_1",
		"IStore_2",
		"IStore_3",
		"LStore_0",
		"LStore_1",
		"LStore_2",
		"LStore_3",
		"FStore_0",
		"FStore_1",
		"FStore_2",
		"FStore_3",
		"DStore_0",
		"DStore_1",
		"DStore_2",
		"DStore_3",
		"AStore_0",
		"AStore_1",
		"AStore_2",
		"AStore_3",
		"IAstore",
		"LAstore",
		"FAstore",
		"DAstore",
		"AAsStore",
		"BAstore",
		"CAstore",
		"SAstore",
		"Pop",
		"Pop2",
		"Dup",
		"Dup_x1",
		"Dup_x2",
		"Dup2",
		"Dup2_x1",
		"Dup2_x2",
		"Swap",
		"IAdd",
		"LAdd",
		"FAdd",
		"DAdd",
		"ISub",
		"LSub",
		"FSub",
		"DSub",
		"IMul",
		"LMul",
		"FMul",
		"DMul",
		"IDiv",
		"LDiv",
		"FDiv",
		"DDiv",
		"IRem",
		"LRem",
		"FRem",
		"DRem",
		"INeg",
		"LNeg",
		"FNeg",
		"DNeg",
		"IShl",
		"LShl",
		"IShr",
		"LShr",
		"IUShr",
		"LUShr",
		"IAnd",
		"LAnd",
		"IOr",
		"LOr",
		"IXor",
		"LXor",
		"IInc Var Byte",
		"I2L",
		"I2F",
		"I2D",
		"L2I",
		"L2F",
		"L2D",
		"F2I",
		"F2L",
		"F2D",
		"D2I",
		"D2L",
		"D2F",
		"I2B",
		"I2C",
		"I2S",
		"LCmp",
		"FCmpl",
		"FCmpg",
		"DCmpl",
		"DCmpg",
		"Ifeq RelBranchLoc",
		"Ifne RelBranchLoc",
		"Iflt RelBranchLoc",
		"Ifge RelBranchLoc",
		"Ifgt RelBranchLoc",
		"Ifle RelBranchLoc",
		"If_icmpeq RelBranchLoc",
		"If_icmpne RelBranchLoc",
		"If_icmplt RelBranchLoc",
		"If_icmpge RelBranchLoc",
		"If_icmpgt RelBranchLoc",
		"If_icmple RelBranchLoc",
		"If_acmpeq RelBranchLoc",
		"If_acmpne RelBranchLoc",
		"Goto RelBranchLoc",
		"Jsr RelBranchLoc",
		"Ret Var",
		"TableSwitch Align CustomTableSwitch",
		"LookupSwitch Align CustomLookupSwitch",
		"IReturn",
		"LReturn",
		"FReturn",
		"DReturn",
		"AReturn",
		"Return",
		"GetStatic PoolIndexFieldRef",
		"PutStatic PoolIndexFieldRef",
		"GetField PoolIndexFieldRef",
		"PutField PoolIndexFieldRef",
		"InvokeVirtual PoolIndexMethodRef",
		"InvokeSpecial PoolIndexMethodRef",
		"InvokeStatic PoolIndexMethodOrInterfaceMethodRef",
		"InvokeInterface PoolIndexInterfaceMethodRef UByte Null",
		"InvokeDynamic PoolIndexInvokeDynamic Null Null",
		"New PoolIndexClassRef",
		"NewArray UByte",
		"ANewArray PoolIndexClassRef",
		"ArrayLength",
		"AThrow",
		"CheckCast PoolIndexClassRef",
		"InstanceOf PoolIndexClassRef",
		"MonitorEnter",
		"MonitorExit",
		"Wide Align CustomWide",
		"MultiANewArray PoolIndexClassRef UByte",
		"Ifnull RelBranchLoc",
		"Ifnonnull RelBranchLoc",
		"Goto_w RelBranchLocInt",
		"Jsr_w RelBranchLocInt",
		"Breakpoint",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"-",
		"Impdep1",
		"Impdep2"
	};
	
	public static void main(String[] args)
	{
		String packet = "de.ecconia.java.decompileide.structure.bytecodes2.generated";
		
		File root = new File("./src/main/java/" + packet.replace('.', '/'));
		System.out.println("Path: " + root.getAbsolutePath());
		
		MethodGen toStringMethod = new MethodGen("public String", "toString");
		toStringMethod.doesOverwrite();
		toStringMethod.addLine("return \"{{{name]]]\";");
		toStringMethod.build();
		
		try
		{
			for(int i = 0; i < opcodes.length; i++)
			{
				String classCode = opcodes[i];
				
				if(!classCode.equals("-"))
				{
					String[] parts = classCode.split(" ", 2);
					
					ClassGen gen = new ClassGen(packet, parts[0]);
					
					boolean hasArgs = parts.length == 2;
					String[] arguments = null;
					if(hasArgs)
					{
						arguments = parts[1].split(" ");
					}
					
					gen.extend("de.ecconia.java.decompileide.structure.bytecodes2", "Opcode");
					
					MethodGen constructor = new MethodGen("public", parts[0]);
					constructor.addParameter("de.ecconia.java.decompileide", "CustomDataInput", "reader");
					constructor.addParameter("de.ecconia.java.decompileide.structure.constantpool", "ConstantPool", "pool");
					constructor.addThrows("java.io", "IOException");
					
					String toString = parts[0].toLowerCase();
					
					if(hasArgs)
					{
						for(String argument : arguments)
						{
							switch(argument)
							{
							case "PoolSmallIndexValue":
								gen.addField("public final Object value;");
								constructor.addLine("value = pool.getSmallValue(reader.readUnsignedByte());");
								toString += " '\" + value + \"'";
								break;
							case "PoolIndexValue":
								gen.addField("public final Object value;");
								constructor.addLine("value = pool.getSmallValue(reader.readUnsignedShort());");
								toString += " '\" + value + \"'";
								break;
							case "PoolIndexBigValue":
								gen.addField("public final Object value;");
								constructor.addLine("value = pool.getBigValue(reader.readUnsignedShort());");
								toString += " '\" + value + \"'";
								break;
							case "PoolIndexFieldRef":
								gen.addImport("de.ecconia.java.decompileide.structure.constantpool.contypes.ClassTypeName");
								gen.addField("private final ClassTypeName classNameType;");
								constructor.addLine("classNameType = pool.getField(reader.readUnsignedShort());");
								toString += " \" + (classNameType.getClazz() + \":\" + classNameType.getName() + \" {\" + classNameType.getType() + \"}\").replace('/', '.') + \"";
								break;
							case "PoolIndexMethodRef":
								gen.addImport("de.ecconia.java.decompileide.structure.constantpool.contypes.ClassTypeName");
								gen.addField("private final ClassTypeName classNameType;");
								constructor.addLine("classNameType = pool.getMethod(reader.readUnsignedShort());");
								toString += " \" + (classNameType.getClazz() + \":\" + classNameType.getName() + \" {\" + classNameType.getType() + \"}\").replace('/', '.') + \"";
								break;
							case "PoolIndexMethodOrInterfaceMethodRef":
								gen.addImport("de.ecconia.java.decompileide.structure.constantpool.contypes.ClassTypeName");
								gen.addField("private final ClassTypeName classNameType;");
								constructor.addLine("classNameType = pool.getMethodOrInterfaceMethod(reader.readUnsignedShort());");
								toString += " \" + (classNameType.getClazz() + \":\" + classNameType.getName() + \" {\" + classNameType.getType() + \"}\").replace('/', '.') + \"";
								break;
							case "PoolIndexInterfaceMethodRef":
								gen.addImport("de.ecconia.java.decompileide.structure.constantpool.contypes.ClassTypeName");
								gen.addField("private final ClassTypeName classNameType;");
								constructor.addLine("classNameType = pool.getInterfaceMethod(reader.readUnsignedShort());");
								toString += " \" + (classNameType.getClazz() + \":\" + classNameType.getName() + \" {\" + classNameType.getType() + \"}\").replace('/', '.') + \"";
								break;
							case "PoolIndexInvokeDynamic":
								gen.addImport("de.ecconia.java.decompileide.structure.constantpool.contypes.BootstrapMethod");
								gen.addField("private final BootstrapMethod bootstrapMethod;");
								constructor.addLine("bootstrapMethod = pool.getBootstrapMethod(reader.readUnsignedShort());");
								toString += " \" + (bootstrapMethod.getName() + \" {\" + bootstrapMethod.getType() + \"} @\" + bootstrapMethod.getIndex()).replace('/', '.') + \"";
								break;
							case "Null":
								constructor.addLine("reader.readByte();");
								break;
							case "PoolIndexClassRef":
								gen.addField("private final String clazz;");
								constructor.addLine("clazz = pool.getClass(reader.readUnsignedShort());");
								toString += " \" + clazz.replace('/', '.') + \"";
								break;
							case "RelBranchLocInt":
								gen.addField("private final int offset;");
								constructor.addLine("offset = reader.readInt();");
								toString += " \" + offset + \"";
								break;
							case "RelBranchLoc":
								gen.addField("private final int offset;");
								constructor.addLine("offset = reader.readShort();");
								toString += " \" + offset + \"";
								break;
							case "Short":
								gen.addField("private final int var;");
								constructor.addLine("var = reader.readShort();");
								toString += " \" + var + \"";
								break;
							case "Byte":
								gen.addField("private final int var;");
								constructor.addLine("var = reader.readByte();");
								toString += " \" + var + \"";
								break;
							case "UByte":
								gen.addField("private final int var;");
								constructor.addLine("var = reader.readUnsignedByte();");
								toString += " \" + var + \"";
								break;
							case "Var":
								gen.addField("private final int varIndex;");
								constructor.addLine("varIndex = reader.readUnsignedByte();");
								toString += " Var\" + varIndex + \"";
								break;
							case "Align":
								constructor.addLine("reader.alignTo4();");
								break;
							case "CustomLookupSwitch":
								gen.addField("private final int defaultAddress;");
								gen.addField("private final int[][] entries;");
								constructor.addLine(""); //Empty line.
								constructor.addLine("defaultAddress = reader.readInt();");
								constructor.addLine("int amount = reader.readInt();");
								constructor.addLine("entries = new int[amount][];");
								constructor.addLine("for(int i = 0; i < amount; i++)");
								constructor.addLine("{");
								constructor.addLine("	entries[i] = new int[2];");
								constructor.addLine("	entries[i][0] = reader.readInt();");
								constructor.addLine("	entries[i][1] = reader.readInt();");
								constructor.addLine("}");
								gen.addImport("java.util.Arrays");
								gen.addImport("java.util.stream.Collectors");
								toString += " \" + defaultAddress + \" : { \" + Arrays.stream(entries).map(t -> t[0] + \": \" + t[1]).collect(Collectors.joining(\", \")) + \" }";
								break;
							case "CustomTableSwitch":
								gen.addField("private final int defaultAddress;");
								gen.addField("private final int[] entries;");
								constructor.addLine(""); //Empty line.
								constructor.addLine("defaultAddress = reader.readInt();");
								constructor.addLine("int min = reader.readInt();");
								constructor.addLine("int max = reader.readInt();");
								constructor.addLine("int amount = max - min + 1;");
								constructor.addLine("entries = new int[amount];");
								constructor.addLine("for(int i = 0; i < amount; i++)");
								constructor.addLine("{");
								constructor.addLine("	entries[i] = reader.readInt();");
								constructor.addLine("}");
								gen.addImport("java.util.Arrays");
								gen.addImport("java.util.stream.Collectors");
								toString += " \" + defaultAddress + \" : {\" + Arrays.stream(entries).mapToObj(v -> String.valueOf(v)).collect(Collectors.joining(\", \")) + \"}";
								break;
							case "CustomWide":
								gen.addField("private final Opcode instruction;");
								gen.addField("private final int varIndex;");
								gen.addField("private final Integer constant;");
								constructor.addLine(""); //Empty line.
								gen.addImport("de.ecconia.java.decompileide.structure.bytecodes2.OpcodeInfo");
								constructor.addLine("instruction = OpcodeInfo.instructionFromOpcode(reader.readUnsignedByte(), reader, pool);");
								constructor.addLine("varIndex = reader.readUnsignedShort();");
								constructor.addLine("constant = instruction instanceof IInc ? (int) reader.readShort() : null;");
								toString += " \" + instruction + \" Var\" + varIndex + \" (\" + constant + \")";
								break;
							}
						}
					}
					
					toStringMethod.replace("name", toString);
					
					constructor.build();
					gen.addMethod(constructor);
					gen.addMethod(toStringMethod);
					gen.write(new File(root, parts[0] + ".java"));
				}
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Done.");
	}
}

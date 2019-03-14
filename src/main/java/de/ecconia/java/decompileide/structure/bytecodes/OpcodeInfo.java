package de.ecconia.java.decompileide.structure.bytecodes;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import de.ecconia.java.decompileide.CustomDataInput;
import de.ecconia.java.decompileide.structure.bytecodes.generated.*;
import de.ecconia.java.decompileide.structure.constantpool.ConstantPool;

public class OpcodeInfo
{
	private static final Class<?>[] classes = 
	{
		Nop.class,
		AConst_Null.class,
		AConst_m1.class,
		IConst_0.class,
		IConst_1.class,
		IConst_2.class,
		IConst_3.class,
		IConst_4.class,
		IConst_5.class,
		LConst_0.class,
		LConst_1.class,
		FConst_0.class,
		FConst_1.class,
		FConst_2.class,
		DConst_0.class,
		DConst_1.class,
		BIPush.class,
		SIPush.class,
		LDC.class,
		LDC_w.class,
		LDC2_w.class,
		ILoad.class,
		LLoad.class,
		FLoad.class,
		DLoad.class,
		ALoad.class,
		ILoad_0.class,
		ILoad_1.class,
		ILoad_2.class,
		ILoad_3.class,
		LLoad_0.class,
		LLoad_1.class,
		LLoad_2.class,
		LLoad_3.class,
		FLoad_0.class,
		FLoad_1.class,
		FLoad_2.class,
		FLoad_3.class,
		DLoad_0.class,
		DLoad_1.class,
		DLoad_2.class,
		DLoad_3.class,
		ALoad_0.class,
		ALoad_1.class,
		ALoad_2.class,
		ALoad_3.class,
		IAload.class,
		LAload.class,
		FAload.class,
		DAload.class,
		AAload.class,
		BAload.class,
		CAload.class,
		SAload.class,
		IStore.class,
		LStore.class,
		FStore.class,
		DStore.class,
		AStore.class,
		IStore_0.class,
		IStore_1.class,
		IStore_2.class,
		IStore_3.class,
		LStore_0.class,
		LStore_1.class,
		LStore_2.class,
		LStore_3.class,
		FStore_0.class,
		FStore_1.class,
		FStore_2.class,
		FStore_3.class,
		DStore_0.class,
		DStore_1.class,
		DStore_2.class,
		DStore_3.class,
		AStore_0.class,
		AStore_1.class,
		AStore_2.class,
		AStore_3.class,
		IAstore.class,
		LAstore.class,
		FAstore.class,
		DAstore.class,
		AAsStore.class,
		BAstore.class,
		CAstore.class,
		SAstore.class,
		Pop.class,
		Pop2.class,
		Dup.class,
		Dup_x1.class,
		Dup_x2.class,
		Dup2.class,
		Dup2_x1.class,
		Dup2_x2.class,
		Swap.class,
		IAdd.class,
		LAdd.class,
		FAdd.class,
		DAdd.class,
		ISub.class,
		LSub.class,
		FSub.class,
		DSub.class,
		IMul.class,
		LMul.class,
		FMul.class,
		DMul.class,
		IDiv.class,
		LDiv.class,
		FDiv.class,
		DDiv.class,
		IRem.class,
		LRem.class,
		FRem.class,
		DRem.class,
		INeg.class,
		LNeg.class,
		FNeg.class,
		DNeg.class,
		IShl.class,
		LShl.class,
		IShr.class,
		LShr.class,
		IUShr.class,
		LUShr.class,
		IAnd.class,
		LAnd.class,
		IOr.class,
		LOr.class,
		IXor.class,
		LXor.class,
		IInc.class,
		I2L.class,
		I2F.class,
		I2D.class,
		L2I.class,
		L2F.class,
		L2D.class,
		F2I.class,
		F2L.class,
		F2D.class,
		D2I.class,
		D2L.class,
		D2F.class,
		I2B.class,
		I2C.class,
		I2S.class,
		LCmp.class,
		FCmpl.class,
		FCmpg.class,
		DCmpl.class,
		DCmpg.class,
		Ifeq.class,
		Ifne.class,
		Iflt.class,
		Ifge.class,
		Ifgt.class,
		Ifle.class,
		If_icmpeq.class,
		If_icmpne.class,
		If_icmplt.class,
		If_icmpge.class,
		If_icmpgt.class,
		If_icmple.class,
		If_acmpeq.class,
		If_acmpne.class,
		Goto.class,
		Jsr.class,
		Ret.class,
		TableSwitch.class,
		LookupSwitch.class,
		IReturn.class,
		LReturn.class,
		FReturn.class,
		DReturn.class,
		AReturn.class,
		Return.class,
		GetStatic.class,
		PutStatic.class,
		GetField.class,
		PutField.class,
		InvokeVirtual.class,
		InvokeSpecial.class,
		InvokeStatic.class,
		InvokeInterface.class,
		InvokeDynamic.class,
		New.class,
		NewArray.class,
		ANewArray.class,
		ArrayLength.class,
		AThrow.class,
		CheckCast.class,
		InstanceOf.class,
		MonitorEnter.class,
		MonitorExit.class,
		Wide.class,
		MultiANewArray.class,
		Ifnull.class,
		Ifnonnull.class,
		Goto_w.class,
		Jsr_w.class,
		Breakpoint.class,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		Impdep1.class,
		Impdep2.class,
	};
	
	private static Constructor<?>[] constructors = new Constructor[classes.length];
	
	static
	{
		for(int i = 0; i < classes.length; i++)
		{
			Class<?> clazz = classes[i];
			
			if(clazz != null)
			{
				try
				{
					constructors[i] = clazz.getConstructor(new Class[] {CustomDataInput.class, ConstantPool.class});
				}
				catch(NoSuchMethodException | SecurityException e)
				{
					e.printStackTrace();
					throw new Error("Something went wrong initializing OpcodeInfo.");
				}
			}
		}
	}
	
	public static Opcode instructionFromOpcode(int opcode, CustomDataInput reader, ConstantPool pool) throws IOException
	{
		Constructor<?> constructor = constructors[opcode];
		if(constructor == null)
		{
			throw new RuntimeException("Attempted to create instruction with opcode: " + opcode);
		}
		else
		{
			try
			{
				return (Opcode) constructor.newInstance(reader, pool);
			}
			catch(InstantiationException | IllegalAccessException | IllegalArgumentException e)
			{
				throw new Error("Something went wrong constructing Opcode.", e);
			}
			catch(InvocationTargetException e)
			{
				if(e.getCause() instanceof IOException)
				{
					throw (IOException) e.getCause();
				}
				else
				{
					throw new Error("Something went wrong constructing Opcode.", e);
				}
			}
		}
	}
}

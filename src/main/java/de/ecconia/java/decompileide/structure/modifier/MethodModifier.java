package de.ecconia.java.decompileide.structure.modifier;

import java.util.ArrayList;
import java.util.List;

public enum MethodModifier implements ModifierEnumType
{
	PUBLIC(0x0001),
	PRIVATE(0x0002),
	PROTECTED(0x0004),
	STATIC(0x0008),
	FINAL(0x0010),
	SYNCRONIZED(0x0020),
	BRIDGE(0x0040),
	VARARGS(0x0080),
	NATIVE(0x0100),
	ABSTRACT(0x0400),
	STRICT(0x0800),
	SYNTHETIC(0x1000),
	;
	
	private final int bit;
	
	private MethodModifier(int bit)
	{
		this.bit = bit;
	}

	@Override
	public int getBit()
	{
		return bit;
	}

	@Override
	public String getName()
	{
		return name().toLowerCase();
	}

	private static final List<ModifierEnumType> types = new ArrayList<>();
	
	public static List<ModifierEnumType> getAll()
	{
		return types;
	}
	
	static
	{
		for(ModifierEnumType mod : values())
		{
			types.add(mod);
		}
	}
}

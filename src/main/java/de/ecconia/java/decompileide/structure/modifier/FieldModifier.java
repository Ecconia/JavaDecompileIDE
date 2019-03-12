package de.ecconia.java.decompileide.structure.modifier;

import java.util.ArrayList;
import java.util.List;

public enum FieldModifier implements ModifierEnumType
{
	PUBLIC(0x0001),
	PRIVATE(0x0002),
	PROTECTED(0x0004),
	STATIC(0x0008),
	FINAL(0x0010),
	VOLATILE(0x0040),
	TRANSISTENT(0x0080),
	SYNTHETIC(0x1000),
	ENUM(0x4000),
	;
	
	private final int bit;
	
	private FieldModifier(int bit)
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

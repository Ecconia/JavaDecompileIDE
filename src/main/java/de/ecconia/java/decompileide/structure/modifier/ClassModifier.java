package de.ecconia.java.decompileide.structure.modifier;

import java.util.ArrayList;
import java.util.List;

public enum ClassModifier implements ModifierEnumType
{
	PUBLIC(0x0001),
	PRIVATE(0x0002), //From innerClassAttribute
	PROTECTED(0x0004), //From innerClassAttribute
	STATIC(0x0008), //From innerClassAttribute
	FINAL(0x0010),
	SUPER(0x0020),
	INTERFACE(0x0200),
	ABSTRACT(0x0400),
	SYNTHETIC(0x1000),
	ANNOTATION(0x2000),
	ENUM(0x4000),
	;
	
	private final int bit;
	
	private ClassModifier(int bit)
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

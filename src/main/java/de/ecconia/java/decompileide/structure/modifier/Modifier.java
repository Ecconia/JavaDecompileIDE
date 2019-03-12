package de.ecconia.java.decompileide.structure.modifier;

import java.util.List;

public class Modifier
{
	private final int mask;
	private final List<ModifierEnumType> modifiers;
	
	public Modifier(int mask, List<ModifierEnumType> modifiers)
	{
		this.mask = mask;
		this.modifiers = modifiers;
	}
	
	public boolean isSynthetic()
	{
		//TODO: Find more elegant way to register and use global modifiers :/
		return ClassModifier.SYNTHETIC.probe(mask);
	}
	
	@Override
	public String toString()
	{
		String modifiers = "";
		for(ModifierEnumType modifier : this.modifiers)
		{
			if(modifier.probe(mask))
			{
				modifiers += modifier.getName() + " ";
			}
		}
		return modifiers;
	}

	public boolean check(ModifierEnumType modifier)
	{
		return modifier.probe(mask);
	}
}

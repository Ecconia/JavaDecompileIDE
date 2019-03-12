package de.ecconia.java.decompileide;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class CustomDataInput extends DataInputStream
{
	private final ByteArrayStreamWithPointer stream;
	
	public CustomDataInput(byte[] bytes)
	{
		super(new ByteArrayStreamWithPointer(bytes));
		
		stream = (ByteArrayStreamWithPointer) in;
	}
	
	public void alignTo4() throws IOException
	{
		int position = stream.getPos();
		int remainers = position % 4;
		if(remainers != 0)
		{
			int loops = 4 - remainers;
			
			for(int i = 0; i < loops; i++)
			{
				readByte();
			}
		}
	}
	
	public int getPos()
	{
		return stream.getPos();
	}
	
	public int getMax()
	{
		return stream.getMax();
	}
	
	private static class ByteArrayStreamWithPointer extends ByteArrayInputStream
	{
		public ByteArrayStreamWithPointer(byte[] bytes)
		{
			super(bytes);
		}
		
		private int getPos()
		{
			return pos;
		}
		
		private int getMax()
		{
			return buf.length;
		}
	}
}

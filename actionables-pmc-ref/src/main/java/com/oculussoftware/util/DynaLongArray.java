/*************************
DynaLongArray simulates a dynamic array of longs. It supports a limited set of methods that
we would ordinarily use on java.util.Vector & java.util.ArrayList. 

Note: Since we are dealing with primitives all method arguments are primitives 
		 and not java.lang.Object.

Following are the basic operations possible with DynaLongArray:-

0. Constructor call new DynaLongArray();
1. add(long value);
2. add(int index, long value);
3. remove(int index);
4 get(int index);
5 size();
6 contains(long value);
7 clear();
8 indexOf(long value);
9 isEmpty();
10 toArray(); returns  long[];

*************************/

package com.oculussoftware.util;

public class DynaLongArray extends ArrayBase
{
    protected long[] baseArray;

    public DynaLongArray()
		{
			this(0);		
		}
    
    protected DynaLongArray(int size, int growth) {
        super(size, growth, Integer.TYPE);
    }

    protected DynaLongArray(int size) {
        super(size, Integer.TYPE);
    }

    protected Object getArray() {
        return baseArray;
    }

    
    protected void setArray(Object array) {
        baseArray = (long[]) array;
    }
   
   /**
		* Removes values from a given index to another given index
     */
    protected void removeValues(int from, int to) {
        for (int i = from; i < to; i++) {
            baseArray[i] = 0;
        }
    }
    
    public String toString()
	{
		StringBuffer sbf = new StringBuffer();
		sbf.append("[");
		for(short i=0; i < size(); ++i)
		{
			sbf.append(baseArray[i]);
			sbf.append(" ");
		}
		
		sbf.append("]");	
	  return sbf.toString();		
	}
	
	  /**
		* Add an integer to the array
     */
	public void add(long value)
	{		
		set(getAddIndex(),value);
	}
	
	public void add(int index, long value)
	{		
		makeInsertSpace(index);
		set(index,value);
	}
	
	public boolean contains(long value)
	{
	  java.util.Arrays.sort((long[])getArray());	
	  return (java.util.Arrays.binarySearch((long[])getArray(),value) > 0);			
	}
	
	 /**
		* Sets the value of a particular  index to the integer value passed
     */
	public void set(int index, long value) 
	{			
		baseArray[index] = value;  
	}
	
	public long[] toArray()
	{		
	 return (long []) getArray();	
	}
	
	public long get(int index)
	{		
	 return baseArray[index];
	}
	
	public int indexOf(long value)
	{		
	  java.util.Arrays.sort(baseArray);
	  return java.util.Arrays.binarySearch(baseArray,value);	 
	}
	
	public boolean isEmpty() { return size()==0;}
}

    
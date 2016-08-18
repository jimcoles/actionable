/*************************
DynaIntArray simulates a dynamic array of integers. It supports a limited set of methods that
we would ordinarily use on java.util.Vector & java.util.ArrayList. 

Note: Since we are dealing with primitives all method arguments are primitives 
		 and not java.lang.Object.

Following are the basic operations possible with DynaIntArray:-

0. Constructor call new DynaIntArray();
1. add(int value);
2. add(int index, int value);
3. remove(int index);
4 get(int index);
5 size();
6 contains(int);
7 clear();
8 indexOf(int);
9 isEmpty();
10 toArray(); returns  int[];

*************************/

package com.oculussoftware.util;

public class DynaIntArray extends ArrayBase
{
    protected int[] baseArray;

    public DynaIntArray()
		{
			this(0);		
		}
    
    protected DynaIntArray(int size, int growth) {
        super(size, growth, Integer.TYPE);
    }

    protected DynaIntArray(int size) {
        super(size, Integer.TYPE);
    }

    protected Object getArray() {
        return baseArray;
    }

    
    protected void setArray(Object array) {
        baseArray = (int[]) array;
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
	public void add(int value)
	{		
		set(getAddIndex(),value);
	}
	
	public void add(int index, int value)
	{		
		makeInsertSpace(index);
		set(index,value);
	}
	
	public boolean contains(int value)
	{
	  java.util.Arrays.sort((int[])getArray());	
	  return (java.util.Arrays.binarySearch((int[])getArray(),value) > 0);			
	}
	
	 /**
		* Sets the value of a particular  index to the integer value passed
     */
	public void set(int index, int value) 
	{			
		baseArray[index] = value;  
	}

	

	
	public int[] toArray()
	{		
	 return (int []) getArray();	
	}
	
	public int get(int index)
	{		
	 return baseArray[index];
	}
	
	public int indexOf(int value)
	{		
	  java.util.Arrays.sort(baseArray);
	  return java.util.Arrays.binarySearch(baseArray,value);	 
	}
	
	public boolean isEmpty() { return size()==0;}
}

    
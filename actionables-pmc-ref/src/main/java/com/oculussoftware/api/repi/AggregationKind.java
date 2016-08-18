package com.oculussoftware.api.repi;

import com.oculussoftware.util.*;

/** An  enumeration.
*/
public class AggregationKind extends IntEnum
{
  public static final AggregationKind NONE   = new AggregationKind(1);
  public static final AggregationKind WEAK   = new AggregationKind(2);
  public static final AggregationKind STRONG = new AggregationKind(3);
  
  
  private AggregationKind(int i)
  {
    super(i);
  }    
  
  public static AggregationKind getInstance(int i)
	{
		AggregationKind prim = null;
		switch (i)
		{
			case 1:prim=AggregationKind.NONE;break;
			case 2:prim=AggregationKind.WEAK;break;
			case 3:prim=AggregationKind.STRONG;break;
			
			
	  }
	  
	  return prim;
	}

}
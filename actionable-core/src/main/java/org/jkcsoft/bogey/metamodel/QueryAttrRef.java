/*
 * Copyright (c) Jim Coles (jameskcoles@gmail.com) 2016. through present.
 *
 * Licensed under the following license agreement:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Also see the LICENSE file in the repository root directory.
 */

package org.jkcsoft.bogey.metamodel;

import java.util.List;

public class QueryAttrRef {
    //-------------------------------------------------------------------
    // Private instance vars
    //-------------------------------------------------------------------
    private Attribute attr = null;
    private AssocChain assocs = new AssocChain();

    //-------------------------------------------------------------------
    // Constructor(s)
    //-------------------------------------------------------------------
    QueryAttrRef(Association[] aAssocs, Attribute attr) {
        for (int idx = 0; idx < aAssocs.length; idx++) {
            assocs.add(aAssocs[idx]);
        }
        this.attr = attr;
    }

    public QueryAttrRef(List lAssocs, Attribute attr) {
        if (lAssocs != null) {
            assocs = new AssocChain(lAssocs);
        }
        this.attr = attr;
    }

    //-------------------------------------------------------------------
    // Public instance methods
    //-------------------------------------------------------------------
    public Attribute getAttr() {
        return attr;
    }

    public AssocChain getAssocs() {
        return assocs;
    }
  
/* 7/17/00 JKC Might need to override equals( ) if we let consuming objects
               create their own IQAttrRef's.  If we force them thru a factory
               that uses grabs from a common pool with no duplicates, etc. we
               might not need this.
               
  public boolean equals(Object obj)
  {
    boolean retVal = false;
  	if (obj instanceof IQAttrRef) {
      retVal = true;
      List lasstest = ((IQAttrRef)obj).getAssocs();
      List lassthis = getAssocs();
      if (lasstest.size() != ) 
      {
        iass = lass.iterator();
        while (iass.hasNext()) {
          
        }
      }
      retVal = ( ((Association) obj).getOid().getLongValue() == getOid().getLongValue() );
    }
    return retVal;
  }
*/
}

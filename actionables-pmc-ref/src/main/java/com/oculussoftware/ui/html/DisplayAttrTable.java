package com.oculussoftware.ui.html;

import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.ui.html.wrappers.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.repi.xmeta.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.bus.xmeta.XMen;
import com.oculussoftware.api.busi.common.reports.*;

import java.lang.*;
import java.util.*;

/**
* Filename:    DisplayAttrTable.java
* Date:        02.24.00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.0
*/
public class DisplayAttrTable extends Table implements IDisplayAttrTable
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
    
  private static int NUMCOLS = 3;
  private static String INDENT = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
  protected ISelect[] _select             = null;
  protected int       _numattrs           = 0;
  protected long _clsid;
  protected IObjectContext _context       = null;
  protected IXClass   _xcls               = null;
  protected IXMR      _xmr                = null;
  protected IBasicReport _report          = null;  
  
  
  // -- public constructor ----------------------------------------
    
  public DisplayAttrTable(IObjectContext context,long classid,IBasicReport report) throws OculusException
  {
	  super();
    _report = report;
    _clsid = classid;
    _context = context;
    _xcls = XMen.CLS_PRODUCT;//hack to init XMen first
    _xmr = _context.getRepository().getXMR();
    _xcls = _xmr.getClass(_clsid);
    _numattrs = MAX_NUM_ATTRS;
  } 
      
  public void init()
  {
    ITableRow tr = addTableRow();
    ITableData td = tr.addTableData();
    td.setColSpan(NUMCOLS).addAnchor("Display Attributes:").setClass(ClassKind.LABEL);
    _select = new ISelect[_numattrs];
    for(int i = 0; i < _select.length; i++)
    {
      if((i%NUMCOLS) == 0)
        tr = addTableRow();
      td = tr.addTableData();
      _select[i] = td.addSelect(SELECT_NAME+i,1);
      _select[i].appendOnChange("var idx = document.forms[0]."+SELECT_NAME+i+".selectedIndex; if(document.forms[0]."+SELECT_NAME+i+".options[idx].text.indexOf('*') != -1) { alert('Starred (*) values are not selectable Display Attributes.'); document.forms[0]."+SELECT_NAME+i+".options[0].selected = true; }");  
    }//end for
  }
  
  public IDisplayAttrTable buildTable() throws OculusException
  {
    int attrreflength = 0; 
    int attrrefidx = 0;
    Hashtable h = null; 
    if(_report != null)
    {
      IQAttrRef[] attrrefs = _report.getDisplayAttrs();
      h = new Hashtable();
      for(int i = 0; i < attrrefs.length; i++)
        if(i != 0)             //adjust for the system id (this sucks)
          h.put(""+(i-1),attrrefs[i]);
      attrreflength = attrrefs.length;
    }//end if
    if(attrreflength > 1) attrreflength -= 2;
    for(int j = attrreflength; j < _select.length; j++)
      addOption(j,"  ",""+-1).setSelected();
    // -- init the select boxes
    List chains = _xmr.getQueryAssocChains(_context, _xcls);
    if(chains != null)
    {
      Iterator ichains = chains.iterator();
      if(ichains != null) 
      {
        for(int i = 0; ichains.hasNext(); i++) 
        {
          IXAssocChain chain = (IXAssocChain) ichains.next();
          if(i != 0)
          {
            for(int j = 0; j < _select.length; j++)
            {
              IXAssoc asso = (IXAssoc) chain.get(chain.size()-1);
              addOption(j,asso.getDisplayName()+"*","-1");//can't display a class "c"+endxcls.getIID());
            }//end for
          }//end if
          List lattrs = _xmr.getAttrDisplayList(_context, _xcls, chain);
          if (lattrs != null) 
          {
            Iterator iattrs = lattrs.iterator();
            
            while ( iattrs.hasNext() ) 
            {
              IQAttrRef attrref = (IQAttrRef) iattrs.next();
              IXClassAttr clsattr = attrref.getAttr();
              for(int j = 0; j < _select.length; j++)
              {
                IIID id = null;
                IQAttrRef iqar = null;
                if(h != null)
                  iqar = ((IQAttrRef)h.get(""+j));
                if(iqar != null)
                  id = iqar.getAttr().getIID();
                  
                boolean selected = (id != null && id.equals(clsattr.getIID()));  
                String strVal = "";
                IXAssocChain asschain = attrref.getAssocs();
                Iterator assocs = asschain.iterator();
                while(assocs.hasNext())
                {
                  IXAssoc assoc = (IXAssoc)assocs.next();
                  strVal += assoc.getIID() + "_";
                }//end while
                IOption option = addOption(j,(i!=0?INDENT:"")+clsattr.getDisplayName(),strVal+clsattr.getIID());
                if(selected) { option.setSelected(); attrrefidx++; }                
              }//end for             
            }//end while           
          }//end if
        }//end while
      }//end if   
    }//end if
    else //don't think I need this 
    {
      List attrlist = _xmr.getAttrDisplayList(_context,_xcls,null);
      if(attrlist != null)
      {
        Iterator itr = attrlist.iterator();
        while(itr.hasNext())
        {
          IQAttrRef attrref = (IQAttrRef)itr.next();
          IXClassAttr clsattr = attrref.getAttr();
          for(int j = 0; j < _select.length; j++)
          {
            String strVal = "";
            IXAssocChain chain = attrref.getAssocs();
            Iterator assocs = chain.iterator();
            while(assocs.hasNext())
            {
              IXAssoc assoc = (IXAssoc)assocs.next();
              strVal += assoc.getIID() + "_";
            }//end while
            addOption(j,""+clsattr.getDisplayName(),strVal+clsattr.getIID());
          }//end for
        }//end while
      }//end if
    }//end else
      
    if(_select.length > 0)
      _select[0].setValidation(ValidationKind.NOSELECT,"the first Display Attribute");
    return this;
  }
  
  public int getNumDisplayAttrs()
  { return _numattrs; }
  
  public ISelect getSelect(int idx)
  {
    return _select[idx];
  }
  
  public IOption addOption(int idx)
  {
    return getSelect(idx).addOption();
  }
  
  public IOption addOption(int idx, String name, String value)
  {
    return getSelect(idx).addOption(name,value);
  }
  
}

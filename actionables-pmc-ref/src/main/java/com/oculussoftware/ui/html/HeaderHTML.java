package com.oculussoftware.ui.html;

import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import java.lang.*;
import java.util.*;

/**
* Filename:    PMHeader.java
* Date:        03.22.00
* Description: Represents an HTML element
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Saleem Shafi
* @version 1.0
*/

/*
* Change Activity
*
* Issue number    Programmer      Date        Description
* BUG00236        Saleem Shafi    5/18/00     Added safeguard to the toolbar image event to make sure the toolbar was initialized first.
*/

public class HeaderHTML extends com.oculussoftware.ui.html.wrappers.HTML implements IHeaderHTML
{
  protected IHeader _caption = null;  
  protected ITableData _left = null;
  protected ITableData _right = null;
  
  private int _menunumber = 0;
  private int _menutop = 0;
  private IDiv _titleLayer = null;
  private IDiv _menuLayer = null;
  private IDiv _toolbarLayer = null;
  private boolean _subtab = false;
  
  //----------------------------- Public Constructors -------------------------
  /** Basic default constructor */
  public HeaderHTML(BrowserKind browser)
    throws OculusException
    {
    super(browser);
    IHead head = this.addHead();
    head.addScript().setStringValue(
	"var menuFrame = parent.frames[1];"
    );
    IBody body = this.addBody().setBGColor("#C6C3C6");
    _titleLayer = body.addDiv();
    placeTitle(0);
    ITableRow headerRow = _titleLayer.addTable().setWidthRatio(100).setHeightRatio(100).setCellPadding(0).setBorder(0).addTableRow();
    _left = headerRow.addTableData().setWidthFixed(207);
    _caption = headerRow.addTableData().setVAlign(VAlignKind.TOP).setNowrap().setAlign(AlignKind.CENTER).addHeader(5);
    _right = headerRow.addTableData().setWidthFixed(200);
  }
  
  public boolean hasSubTab() { return _subtab; }
  public void addSubTab(boolean subtab)
  {
    _subtab = subtab;
    if (hasSubTab())
      placeTitle(_menutop+20);
  }

  public IHeaderHTML setCaption(String caption)
  {
    if (caption == null || caption.equals("null")) caption = "";
    _caption.setStringValue(caption);
    return this;
  }
  
  private void placeTitle(int top)
  {
    _menutop = top;
    top += -5;  
    if(getBrowserKind().equals(BrowserKind.NETSCAPE))
       top += -12;
    _titleLayer.setStyle("Position : Absolute ; Left : 0px ; Top : "+top+"px ;");
  }
  
  public void addMenu(String menu, boolean isToolbar)
  {
    String imgName = null;
    int cutoff = menu.lastIndexOf(" ")+1;
    imgName = menu.substring(cutoff);
    IDiv div = getBody().addDiv().setStyle("Position : Absolute ; Left : "+((63*_menunumber)+1)+"px ; Top : "+(isToolbar == false?_menutop:_menutop)+"px ; Width : 60px ; Height : 20px");
    IAnchor anchor = div.addAnchor().setHRef("javascript://").setOnClick("showMenuInFrame('"+menu+"',"+((60*_menunumber)+1)+",1);").setOnMouseOver("image"+_menunumber+".src='/system/OculusImages/menus/"+imgName+"_Up.gif';").setOnMouseOut("image"+_menunumber+".src='/system/OculusImages/menus/"+imgName+".gif';");
    anchor.addImg().setSrc("/system/OculusImages/menus/"+imgName+".gif").setBorder(0).setHeight(20).setWidth(60).setName("image"+_menunumber);
    _menunumber++;
  }

  public void addNewMenu(String menu, boolean isToolbar)
  {
  String imgName = null;
  String menuname = "parent.frames[1]."+menu.replace(' ','_');
  int cutoff = menu.lastIndexOf(" ")+1;
  imgName = menu.substring(cutoff);
  IDiv div = getBody().addDiv().setStyle("Position : Absolute ; Left : "+((63*_menunumber)+1)+"px ; Top : "+(isToolbar == false?_menutop:_menutop)+"px ; Width : 60px ; Height : 20px");
  IAnchor anchor = div.addAnchor().setHRef("javascript://").setOnClick("if (parent && parent.frames[1] && parent.frames[1].loadMenu) parent.frames[1].loadMenu("+menuname+","+_menunumber+");").setOnMouseOver("image"+_menunumber+".src='/system/OculusImages/menus/"+imgName+"_Up.gif';").setOnMouseOut("image"+_menunumber+".src='/system/OculusImages/menus/"+imgName+".gif';");
  anchor.addImg().setSrc("/system/OculusImages/menus/"+imgName+".gif").setBorder(0).setHeight(20).setWidth(60).setName("image"+_menunumber);
  _menunumber++;
  }

  
  public void addToolbar(int numIcons)
  {
	_toolbarLayer = getBody().addDiv().setID("toolbar").setStyle("Position : Absolute ; Left : 1px ; Top : "+_menutop+"px ;");
    placeTitle(_menutop+25);
    ITableRow row = _toolbarLayer.addTable().addTableRow();
    for (int i = 0; i < numIcons; i++)
      row.addTableData().setWidthFixed(23).addAnchor().setName("tag"+i).setHRef("javascript://").setOnMouseOut("if (window.toolbar && window.toolbar["+i+"] && window.toolbar["+i+"].active) rollOut("+i+"); window.status=''; if (window.nd) window.nd();return true;").setOnMouseOver("if (window.toolbar && window.toolbar["+i+"]) { if (window.toolbar["+i+"].active) rollOver("+i+"); window.status=''+window.toolbar["+i+"].toolTip; overlib(window.toolbar["+i+"].toolTip); } return true;").setOnClick("if (window.toolbar && window.toolbar["+i+"] && window.toolbar["+i+"].active) eval(window.toolbar["+i+"].method);").addImg().setID("toolButton"+i).setName("toolButton"+i).setSrc("/system/OculusImages/common/Blank.gif");
    this.getHead().addScript().setSrc("/system/OculusJS/toolbutton.js");
    this.getHead().addScript().setSrc("/system/OculusJS/imgselect2.js");

    this.getBody().appendOnLoad("window.toolbar = new Array(); if (parent.frames[1].loadToolbar) parent.frames[1].loadToolbar(); if (window.defineMenus) defineMenus(); if (window.onLoad) window.onResize = onLoad;");
//  	IDiv Tdiv = getBody().addDiv().setID("overDiv").setStyle("position:absolute; visibility:hide; z-index:1;");
//    this.getBody().addScript().setSrc("/system/OculusJS/overlib.js");
  }
  
  public ITableData getLeftTableData()
  {
    return _left;
  }
  
  public ITableData getRightTableData()
  {
    return _right;
  }
  
}
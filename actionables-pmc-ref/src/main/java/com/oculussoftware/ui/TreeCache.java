package com.oculussoftware.ui;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.oculussoftware.util.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.sysi.sec.*;
import com.oculussoftware.api.sysi.license.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.system.*;
import com.oculussoftware.repos.util.*;

/**
* Filename:    SvtAllUpdateTree.java
* Date:        10-23-1999
* Description: .
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Saleem Shafi
* @version 1.1
*/
public final class TreeCache
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  *                 C Johnston      6/26/00     Added search options to Folder_Tools to compass menubar
  *                 A Pota          6/7/00      Removed Q&A and Win/Loss from config screen
  * BUG00059        Saleem Shafi    5/15/00     Changed the onClick of the Baseline/BCategory
  *                                             to point to the feature frameset.
  * BUG00059        Saleem Shafi    5/16/00     Changed the getBaselineCategories() method to know about defaultCategory()
  * BUG00152        Saleem Shafi    5/16/00     Started checking to make sure the node isn't null before adding it.
  * ----            Saleem Shafi    5/22/00     Fixed the frame selection to use the new tree frameset.
  * DES00347        Zain Nemazie	  5/23/00			Added Report tree
  * 379							Zain Nemazie		5/23/00			added link from settings to Egan's license key servlet
  * BUG 102					Zain Nemazie		5/23/00			some parent.Attrib not set to parent.parent.Attrib (chris changed main frameset)
  * ---							Saleem Shafi		5/24/00			Eliminated the system context, using tree.getObjectContext() instead.
  * BUG 102					Zain Nemazie		5/23/00			added mailserver node  
	* BUG00615				Saleem Shafi		6/1/00			Added script to clear toolbuttons before changing pages.
	* BUG00159				Saleem Shafi		6/5/00			Changed the logic to filter out accolades version and category in compass.
	* BUG00995				Saleem Shafi		6/13/00			Added permissions logic to Move To.. command.
  * ---             Cuihua Zhang    6/20/2000   Added permissions checking for substd create, and owner.
  * ---             Dan Roberts     6/28/00     List of canned reports moved from tree to upper-right frame.
  * ---             Dan Roberts     6/28/00     Commented-out the getAccoladesReports function (see above entry).
  * BUG01537        Cuihua Zhang    7/25/2000   added toolbars
  * DES01447        Cuihua Zhang    8/7/2000    disabled all menu items for Trash
  * BUG01732        Cuihua Zhang    8/8/2000    changed setOnSelect to setOnClick for One_Spec Trash
  * BUG02027        Cuihua Zhang    8/23/00     setPermissions
  * 02307           Cuihua Zhang    9/5/2000    increased the toolbar numbers in CreateStandardNode
  * 02310           Cuihua Zhang    9/6/2000    checked for Baselines and changed the menu according
  * BUG02318        Cuihua Zhang    9/6/2000    checked for Baselines and changed the menu according
  * BUG02344        Cuihua Zhang    9/6/2000    checked for Baselines and changed the menu according
  */

	private static boolean ALWAYSCHECK = true;
  private static TreeCache _root;
  private ListMap _nodes;

  //  private IObjectContext _context;


  private IGrantSet getConfigPerms(IObjectContext context) throws OculusException
  {

    IAccessMgr acm = context.getCRM().getAccessMgr(context);
    IPermission[] perms = new IPermission[] {PermEnum.CONFIGURE_ALL,PermEnum.CONFIG_SPEC,PermEnum.CONFIG_MARKET_INPUT,PermEnum.CONFIG_PROCESS,PermEnum.CONFIG_PROFILE,PermEnum.CONFIG_ATTR_GRPS,PermEnum.CONFIG_OTHER_COMPASS,};    
    return acm.checkReposPerms(perms);      


  }


  private TreeCache()
  throws OculusException
  {
    _nodes = new HashVector();
    _nodes.put("-1",new Node());
    //	_context = new ObjectContext();
    //	_context.setConnection(_context.getCRM().connect("system","system"));
  }  


  public void addNode(Node child)
  throws OculusException
  {
    _nodes.put(child.getID(), child);
  }  


  private ListMap buildMapFor(Tree tree, Node parentNode)
  throws OculusException
  {
    ModuleKind module = null;
    if (parentNode.getFullNodeID().indexOf("onespec"+Node.delimiter) > -1)
      module = ModuleKind.ACCOLADES;
    else
    if (parentNode.getFullNodeID().indexOf("compass"+Node.delimiter) > -1)
      module = ModuleKind.COMPASS;

    ListMap list = new HashVector();
    if (parentNode.getType().equals(Node.ROOT))                                   // product
      list = getMainBranches(tree.getObjectContext(), parentNode, tree);
    if (parentNode.getType().equals(Node.TASKS))
      list = getTasks(tree.getObjectContext(), parentNode);
    if (parentNode.getType().equals(Node.ACTIVE))
      list = getProducts(tree.getObjectContext(), parentNode, tree);
    if (parentNode.getType().equals(Node.PUBLICFOLDER))
      list = getFolders(tree.getObjectContext(), parentNode,tree);
    if (parentNode.getType().equals(Node.STANDARDS))
      list = getStandardColls(tree.getObjectContext(), parentNode,tree);
    if (parentNode.getType().equals(Node.COMPASSTREE))
      list = getProducts(tree.getObjectContext(), parentNode, tree);
    if (parentNode.getType().equals(Node.STDCOLL))
      list = getStandardColls(tree.getObjectContext(), parentNode,tree);
    if (parentNode.getType().equals(Node.FOLDER))
      list = getFolders(tree.getObjectContext(), parentNode,tree);
    if (parentNode.getType().equals(Node.PRODUCT))                              // version
      list = getVersions(tree.getObjectContext(), parentNode, tree);
    if (parentNode.getType().equals(Node.BASELINE) || parentNode.getType().equals(Node.BASECATEGORY))    // baseline category
      list = getBaselineCategories(tree.getObjectContext(), parentNode, tree);
    if (parentNode.getType().equals(Node.CATEGORY))         // category
      list = getCategories(tree.getObjectContext(), parentNode, tree);
    //settings
    if (parentNode.getType().equals(Node.SETTINGS))         // category
      list = getSettings(tree.getObjectContext(), parentNode);
    
    //reports
    if (parentNode.getType().equals(Node.REPORTS_COMPASS))
      list = getCompassReports(tree.getObjectContext(), parentNode);
    if (parentNode.getType().equals(Node.REPORTS_ACCOLADES))
      list = getAccoladesReports(tree.getObjectContext(), parentNode);

    if (parentNode.getType().equals(Node.FORMS))         // category
      list = getForms(tree.getObjectContext(), parentNode);
    if (parentNode.getType().equals(Node.MARKETINPUTFORMS))         // category
      list = getMarketInputForms(tree.getObjectContext(), parentNode);
    if (parentNode.getType().equals(Node.SPECIFICATIONFORMS))         // category
      list = getSpecificationForms(tree.getObjectContext(), parentNode);
    if (parentNode.getType().equals(Node.OTHERCOMPASSFORMS))         // category
      list = getOtherCompassForms(tree.getObjectContext(), parentNode);  
    if (parentNode.getType().equals(Node.FORMROUTING))         // category
      list = getFormRouting(tree.getObjectContext(), parentNode);  
    if (parentNode.getType().equals(Node.PROFILEFORMS))         // category
      list = getProfileForms(tree.getObjectContext(), parentNode);    
    if (parentNode.getType().equals(Node.PROCESSES))         // category
      list = getProcesses(tree.getObjectContext(), parentNode);
    if (parentNode.getType().equals(Node.WORKFLOW))         // category
      list = getWorkflows(tree.getObjectContext(), parentNode);
    if (parentNode.getType().equals(Node.VERSION))
    {
      list = getSubVersions(tree.getObjectContext(), parentNode, tree);
      if (tree.getBaseline() && module.equals(ModuleKind.ACCOLADES))
        list.putAll(getBaselines(tree.getObjectContext(), parentNode, tree));
      else
        list.putAll(getCategories(tree.getObjectContext(), parentNode,tree));
    }
    return list;
  }


  public static Node createBaselineNode(IBusinessObject bObj, Node parentNode, Tree tree)
  throws OculusException
  {
    Node thisNode = null;
    IIID stateIID = bObj.getStateObject().getIID();
    ModuleKind module = null;
    if (parentNode.getFullNodeID().indexOf("onespec"+Node.delimiter) > -1)
      module = ModuleKind.ACCOLADES;
    else
    if (parentNode.getFullNodeID().indexOf("compass"+Node.delimiter) > -1)
      module = ModuleKind.COMPASS;

    if (true)
    {
      thisNode = new Node(bObj, Node.BASELINE);
      thisNode.setImg("Baseline");
      thisNode.setParentID(parentNode.getID());
      thisNode.setParentNodeID(parentNode.getFullNodeID());
      thisNode.setOnClick("parent.parent.Attrib.location='feat.SvtFeatFrameset?Parent.Type=Base&Parent.ID="+thisNode.getID()+"';");
      thisNode.setOnSelect("clearToolButtons();parent.Menu.location='/servlet/common.SvtTreeView?Page=Header&Menus=Baseline+Functions,Tree+Filters&ToolbarIcons=2'; enableAllMenus(false);BaselinePrintItem.enable(true);Tree_Filters.enableAll(true); setID('"+thisNode.getID()+"'); setType('"+thisNode.getType()+"');");
			if (!TreeCache.ALWAYSCHECK) TreeCache.getInstance().testForChildren(tree,thisNode);
    }

    return thisNode;
  }  


  public static Node createBaselineCategoryNode(IBusinessObject bObj, Node parentNode, Tree tree)
  throws OculusException
  {
    Node thisNode = null;
    IIID stateIID = bObj.getStateObject().getIID();
    ModuleKind module = null;
    if (parentNode.getFullNodeID().indexOf("onespec"+Node.delimiter) > -1)
      module = ModuleKind.ACCOLADES;
    else
    if (parentNode.getFullNodeID().indexOf("compass"+Node.delimiter) > -1)
      module = ModuleKind.COMPASS;

    if (true)
    {
      thisNode = new Node(bObj, Node.BASECATEGORY);
      thisNode.setImg("CategoryFolder");
      thisNode.setOnClick("parent.parent.Attrib.location='feat.SvtFeatFrameset?Parent.Type=BaseCat&Parent.ID="+thisNode.getID()+"';");
      thisNode.setOnSelect("clearToolButtons();parent.Menu.location='/servlet/common.SvtTreeView?Page=Header&Menus=Category+Functions,Attachments,Tree+Filters&ToolbarIcons=5'; setID('"+thisNode.getID()+"'); setType('"+thisNode.getType()+"'); enableAllMenus(true); ");
      thisNode.setParentID(parentNode.getID());
      thisNode.setParentNodeID(parentNode.getFullNodeID());
      if (!TreeCache.ALWAYSCHECK) TreeCache.getInstance().testForChildren(tree,thisNode);
    }

    return thisNode;
  }  


  public static Node createCategoryNode(IBusinessObject bObj, Node parentNode, Tree tree)
  throws OculusException
  {
    Node thisNode = null;
    ModuleKind module = null;
    if (parentNode.getFullNodeID().indexOf("onespec"+Node.delimiter) > -1)
      module = ModuleKind.ACCOLADES;
    else
    if (parentNode.getFullNodeID().indexOf("compass"+Node.delimiter) > -1)
      module = ModuleKind.COMPASS;

    IGrantSet gs = bObj.getPermissions();

    if (bObj.isVisible(module, tree.getSettingsMgr(), gs))
    {
      String numIcons = "11";
      if (module.equals(ModuleKind.COMPASS))
        numIcons = "10";
      String allowPerms = "enableAllMenus(false);CategoryPrintItem.enable(true);CategorySearchItem.enable(true);CategoryAdvSearchItem.enable(true);Attachments.enableAll(true);Tree_Filters.enableAll(true);";
      if (module.equals(ModuleKind.ACCOLADES))
        allowPerms += "CategoryReviewBoardItem.enable(true);";
			if (gs.contains(PermEnum.OWNER))
	      allowPerms += "CategoryMoveToItem.enable(true);";
      if (gs.contains(PermEnum.OWNER))
      {
        allowPerms += "CategoryDeleteItem.enable(true);";
        allowPerms += "CategoryPermissionItem.enable(true);";
      }
      if (gs.contains(PermEnum.SPEC_EDIT))
      {
        allowPerms += "SubCategoryCreateItem.enable(true);";
        allowPerms += "CategoryEditItem.enable(true);";
        allowPerms += "CategoryCopyItem.enable(true);";
        allowPerms += "CategoryMoveItem.enable(true);";
      }

	    thisNode = new Node(bObj, Node.CATEGORY);
	    thisNode.setName(((ICategory)bObj).getOrderNum()+": "+bObj.getName());
	    thisNode.setID(thisNode.getID());
	    thisNode.setImg("CategoryFolder");
	    thisNode.setParentID(parentNode.getID());
	    thisNode.setParentNodeID(parentNode.getFullNodeID());
	    thisNode.setOnClick("parent.parent.Attrib.location='feat.SvtFeatFrameset?Parent.Type=Cat&Parent.ID="+thisNode.getID()+"';");
	    thisNode.setOnSelect("clearToolButtons(); parent.Menu.location='/servlet/common.SvtTreeView?Page=Header&Caption=&Menus=Category+Functions,Category+Tools,Attachments,Tree+Filters&ToolbarIcons="+numIcons+"';"+allowPerms+" setID('"+thisNode.getID()+"'); setType('"+thisNode.getType()+"'); setNodeID('"+thisNode.getFullNodeID()+"');");
	    if (!TreeCache.ALWAYSCHECK) TreeCache.getInstance().testForChildren(tree,thisNode);
    }

    return thisNode;
  }  


  public static Node createFolderNode(IBusinessObject bObj, Node parentNode, Tree tree)
  throws OculusException
  {
    boolean isadmin = bObj.getObjectContext().getConnection().getUserIID().equals(IDCONST.USER_ADMIN.getIIDValue())?true:false;    
    Node thisNode = null;
    ModuleKind module = null;
    if (parentNode.getFullNodeID().indexOf("onespec"+Node.delimiter) > -1)
      module = ModuleKind.ACCOLADES;
    else
    if (parentNode.getFullNodeID().indexOf("compass"+Node.delimiter) > -1)
      module = ModuleKind.COMPASS;
    
    IGrantSet gs = null;
    if (!isadmin) gs = bObj.getPermissions();
    if (bObj.isVisible(module, tree.getSettingsMgr(), gs) || isadmin)
    {
      
      IIID stateIID = bObj.getStateObject().getIID();
      if (parentNode.getFullNodeID().indexOf("compass"+Node.delimiter+"") > -1)
      {
        thisNode = new Node(bObj, Node.FOLDER);
        thisNode.setImg("GenericFolder");
        thisNode.setParentID(parentNode.getID());
        thisNode.setParentNodeID(parentNode.getFullNodeID());
        thisNode.setOnClick("parent.parent.Attrib.location='folder.SvtMIPSFrameset?Folder.ID="+thisNode.getID()+"';");
        
        if (thisNode.getID().equals(IDCONST.UNFILED_FOLDER.getIIDValue().toString()))
        {
          String  menujs= "menu[Tree_Filters].enableAll(true); menu[Folder_Functions].enableAll(false);";
          thisNode.setOnSelect("clearToolButtons();parent.Menu.location='/servlet/common.SvtTreeView?Page=Header&Menus=Folder+Functions,Folder+Tools,Tree+Filters&ToolbarIcons=10';"+menujs+"setID('"+thisNode.getID()+"'); setType('"+thisNode.getType()+"'); setNodeID('"+thisNode.getFullNodeID()+"');menu[Folder_Tools].enableAll(true);");        
        }
        else if (thisNode.getID().equals(IDCONST.FILED_FOLDER.getIIDValue().toString()))
        { 
        
          String menujs="";
          if (isadmin) 
            menujs="menu[Tree_Filters].enableAll(true); menu[Folder_Functions].enableAll(false); menu[Folder_Functions].items[PERMISSION].enable(true);menu[Folder_Functions].items[CREATE_FOLDER].enable(true);";
          else
          {
            menujs= "menu[Tree_Filters].enableAll(true); menu[Folder_Functions].enableAll(false); menu[Folder_Functions].items[PERMISSION].enable(true);";
            if (gs.contains(PermEnum.FOLD_ADD))           
               menujs += "menu[Folder_Functions].items[CREATE_FOLDER].enable(true);";
          }
          thisNode.setOnSelect("clearToolButtons();parent.Menu.location='/servlet/common.SvtTreeView?Page=Header&Menus=Folder+Functions,Folder+Tools,Tree+Filters&ToolbarIcons=10';"+menujs+"setID('"+thisNode.getID()+"'); setType('"+thisNode.getType()+"'); setNodeID('"+thisNode.getFullNodeID()+"');menu[Folder_Tools].enableAll(true);");          
        }
        else           
        {        
          String menujs = "";
           if (isadmin) 
             menujs="menu[Tree_Filters].enableAll(true); menu[Folder_Functions].enableAll(true);";
          else   
          {
             menujs="menu[Tree_Filters].enableAll(true); menu[Folder_Functions].enableAll(false);";
             if (gs.contains(PermEnum.SUBFOLD_ADD))
               menujs +="menu[Folder_Functions].items[CREATE_FOLDER].enable(true);";
             //if (gs.contains(PermEnum.CONTENT_ADD))
             //  menujs +="menu[Folder_Functions].items[CSV].enable(true);";
             if (gs.contains(PermEnum.OWNER) || tree.getObjectContext().getConnection().getUserIID().equals(bObj.getCreatorObject().getIID()))                            
            {
               menujs +="menu[Folder_Functions].items[EDIT_FOLDER].enable(true);";
               menujs +="menu[Folder_Functions].items[MOVE_FOLDER].enable(true);";
               menujs +="menu[Folder_Functions].items[DELETE_FOLDER].enable(true);";
               menujs +="menu[Folder_Functions].items[PERMISSION].enable(true);";             
            }
            
            IGroupColl usrgrpList = (IGroupColl) tree.getObjectContext().getCRM().getCompObject(tree.getObjectContext(), "GroupUserColl", tree.getObjectContext().getConnection().getUserObject().getIID());
            while (usrgrpList.hasMoreGroups())
            {
              IGroup grp = usrgrpList.nextGroup();
              if (grp.getIID().equals(IDCONST.USER_GROUP1.getIIDValue()))                            
              {
                   menujs +="menu[Folder_Functions].items[PERMISSION].enable(true);";             
              }
            } 
          }
          thisNode.setOnSelect("clearToolButtons();parent.Menu.location='/servlet/common.SvtTreeView?Page=Header&Menus=Folder+Functions,Folder+Tools,Tree+Filters&ToolbarIcons=10';"+menujs+"setID('"+thisNode.getID()+"'); setType('"+thisNode.getType()+"'); setNodeID('"+thisNode.getFullNodeID()+"');menu[Folder_Tools].enableAll(true);");           
        }
                
	      if (!TreeCache.ALWAYSCHECK) TreeCache.getInstance().testForChildren(tree,thisNode);
      }
    }

    return thisNode;
  }  


  public static Node createProductNode(IBusinessObject bObj, Node parentNode, Tree tree)
  throws OculusException
  {
    Node thisNode = null;
    ModuleKind module = null;
    if (parentNode.getFullNodeID().indexOf("onespec"+Node.delimiter) > -1)
      module = ModuleKind.ACCOLADES;
    else
    if (parentNode.getFullNodeID().indexOf("compass"+Node.delimiter) > -1)
      module = ModuleKind.COMPASS;
    
    IGrantSet gs = bObj.getPermissions();

    if (bObj.isVisible(module, tree.getSettingsMgr(), gs))
    {
      String numIcons = "5";
      String menus = "Product+Functions";
      String allowPerms = "enableAllMenus(false);ProductPrintItem.enable(true);Attachments.enableAll(true); Tree_Filters.enableAll(true);";
      if (!tree.getBaseline() || module.equals(ModuleKind.COMPASS))
      {
        numIcons = "9";
        menus += ",Product+Tools";
        allowPerms += "ProductSearchItem.enable(true);ProductAdvSearchItem.enable(true);";
        if (gs.contains(PermEnum.ITEM_EDIT)) allowPerms += "ProductEditItem.enable(true);";
        if (gs.contains(PermEnum.OWNER))
        {
          allowPerms += "ProductMoveToItem.enable(true);";
          allowPerms += "ProductDeleteItem.enable(true);";
          allowPerms += "ProductChangeStateItem.enable(true);";
          allowPerms += "ProductPermissionItem.enable(true);";
        }
        if (gs.contains(PermEnum.VERSION_ADD)) allowPerms += "VersionCreateItem.enable(true);";
      }
      menus += ",Attachments,Tree+Filters";
      thisNode = new Node(bObj, Node.PRODUCT);
      thisNode.setImg("ProductFolder");
      thisNode.setParentID(parentNode.getID());
      thisNode.setParentNodeID(parentNode.getFullNodeID());
      thisNode.setOnClick("parent.parent.Attrib.location='prod.SvtProdView?Prod.ID="+thisNode.getID()+"';");
      thisNode.setOnSelect("clearToolButtons(); parent.Menu.location='/servlet/common.SvtTreeView?Page=Header&Caption=&Menus="+menus+"&ToolbarIcons="+numIcons+"'; "+allowPerms+" setID('"+thisNode.getID()+"'); setType('"+thisNode.getType()+"'); setNodeID('"+thisNode.getFullNodeID()+"');" );
  	  if (!TreeCache.ALWAYSCHECK) TreeCache.getInstance().testForChildren(tree,thisNode);
    }
    return thisNode;
  }  


  public static Node createStandardNode(IBusinessObject bObj, Node parentNode, Tree tree)
  throws OculusException
  {
    Node thisNode = null;
    ModuleKind module = null;
    if (parentNode.getFullNodeID().indexOf("onespec"+Node.delimiter) > -1)
      module = ModuleKind.ACCOLADES;
    else
    if (parentNode.getFullNodeID().indexOf("compass"+Node.delimiter) > -1)
      module = ModuleKind.COMPASS;
    IGrantSet gs = bObj.getPermissions();

    if (bObj.isVisible(module, tree.getSettingsMgr(), gs))
    {
      String numIcons = "5";
      String menus = "Standard+Functions";
      String allowPerms = "enableAllMenus(false);StandardPrintItem.enable(true);Tree_Filters.enableAll(true);";
      
      if (!(tree.getBaseline()))
      {
        numIcons = "6";
        menus  +=",Standard+Tools";
        allowPerms +="Standard_Tools.enableAll(true);";
      }
        if (gs.contains(PermEnum.OWNER))
        {
          allowPerms += "StandardDeleteItem.enable(true);";
          allowPerms += "StandardEditItem.enable(true);";
          allowPerms += "StandardPermissionItem.enable(true);";
        }
//        if (gs.contains(PermEnum.SCOLL_ADD))
//          allowPerms += "StandardCreateItem.enable(true);";

        if (gs.contains(PermEnum.SUBCOLL_ADD))
          allowPerms += "SubStandardCreateItem.enable(true);";
//      }
//      else
//      {
//        if (gs.contains(PermEnum.OWNER))
//        {
//          allowPerms += "StandardDeleteItem.enable(true);";
//          allowPerms += "StandardEditItem.enable(true);";
//          allowPerms += "StandardPermissionItem.enable(true);";
//        }
//
//        if (gs.contains(PermEnum.SUBSCOLL_ADD))
//         allowPerms += "SubStandardCreateItem.enable(true);";
//      }
      menus +=",Tree+Filters";
      allowPerms += "Tree_Filters.enableAll(true);";
      
   
      thisNode = new Node(bObj, Node.STDCOLL);
      thisNode.setImg("GenericFolder");
      thisNode.setParentID(parentNode.getID());
      thisNode.setParentNodeID(parentNode.getFullNodeID());
      thisNode.setOnClick("parent.parent.Attrib.location='feat.SvtFeatFrameset?Parent.Type=Standard&Parent.ID="+thisNode.getID()+"';");
      //      thisNode.setOnClick("parent.parent.Attrib.location='stdcoll.SvtStdCollFrameset?StdColl.ID="+thisNode.getID()+"';");
      thisNode.setOnSelect("clearToolButtons(); parent.Menu.location='/servlet/common.SvtTreeView?Page=Header&Menus="+menus+"&ToolbarIcons="+numIcons+"';"+allowPerms+"setID('"+thisNode.getID()+"'); setType('"+thisNode.getType()+"'); setNodeID('"+thisNode.getFullNodeID()+"');");
  	  if (!TreeCache.ALWAYSCHECK) TreeCache.getInstance().testForChildren(tree,thisNode);
    }

    return thisNode;
  }  

  public static Node createVersionNode(IBusinessObject bObj, Node parentNode, Tree tree)
  throws OculusException
  {
    return createVersionNode(bObj, parentNode, tree, false);
  }
  
  public static Node createVersionNode(IBusinessObject bObj, Node parentNode, Tree tree, boolean sub)
  throws OculusException
  {
    Node thisNode = null;
    ModuleKind module = null;
    if (parentNode.getFullNodeID().indexOf("onespec"+Node.delimiter) > -1)
      module = ModuleKind.ACCOLADES;
    else
    if (parentNode.getFullNodeID().indexOf("compass"+Node.delimiter) > -1)
      module = ModuleKind.COMPASS;

    IGrantSet gs = bObj.getPermissions();
    
    if (bObj.isVisible(module, tree.getSettingsMgr(), gs))
    {
      String numIcons = "5";
      String menus = "Version+Functions";
      if (sub) menus = "SubVersion+Functions";
      String allowPerms = "enableAllMenus(false);VersionPrintItem.enable(true);Attachments.enableAll(true);Tree_Filters.enableAll(true);";

      thisNode = new Node(bObj, Node.VERSION);

      if (!tree.getBaseline() || module.equals(ModuleKind.COMPASS))
      {
        menus += ",Version+Tools";
        if (module.equals(ModuleKind.COMPASS))
          numIcons = "10";
        else
          numIcons = "11";
        allowPerms += "VersionAdvSearchItem.enable(true);VersionSearchItem.enable(true);";
        
        if (module.equals(ModuleKind.ACCOLADES))
          allowPerms += "VersionWarningItem.enable(true);";
        allowPerms += "VersionPrintItem.enable(true);";
        if (gs.contains(PermEnum.ITEM_EDIT))
        {
          allowPerms += "VersionEditItem.enable(true);";
          allowPerms += "VersionChangeStateItem.enable(true);";
        }
        if (gs.contains(PermEnum.VERSION_REORDER))
          allowPerms += "VersionMoveItem.enable(true);";
        if (gs.contains(PermEnum.OWNER))
        {
          allowPerms += "VersionMoveToItem.enable(true);";
          allowPerms += "VersionDeleteItem.enable(true);";
          allowPerms += "VersionRemoveItem.enable(true);";
          allowPerms += "VersionPermissionItem.enable(true);";
        }
        if (gs.contains(PermEnum.SPEC_EDIT))
        {
          allowPerms += "CategoryCreateItem.enable(true);";
          allowPerms += "VersionAddItem.enable(true);";
  //        allowPerms += "VersionRemoveItem.enable(true);";
        }
        if (gs.contains(PermEnum.EXPORT_TO_PROJ) && module.equals(ModuleKind.ACCOLADES))
          allowPerms += "VersionExportItem.enable(true);";
        if (gs.contains(PermEnum.VERSION_ADD))
          allowPerms += "VersionCopyItem.enable(true);";
        if (gs.contains(PermEnum.SPEC_VIEW))
        {
          if (module.equals(ModuleKind.ACCOLADES))
          {
            allowPerms += "VersionReviewBoardItem.enable(true);";
            allowPerms += "VersionBaselineItem.enable(true);";
            allowPerms += "VersionAddedItem.enable(true);";
            allowPerms += "VersionRemovedItem.enable(true);";
          }
          allowPerms += "VersionWorkforceItem.enable(true);";
        }
        thisNode.setOnClick("parent.parent.Attrib.location='feat.SvtFeatFrameset?Parent.Type=Ver&Parent.ID="+thisNode.getID()+"';");
      }
      else
        thisNode.setOnClick("parent.parent.Attrib.location='ver.SvtVerBaselineFrameset?Ver.ID="+thisNode.getID()+"';");

      menus += ",Attachments,Tree+Filters";
      thisNode.setImg("VersionFolder");
      thisNode.setParentID(parentNode.getID());
      thisNode.setParentNodeID(parentNode.getFullNodeID());
      thisNode.setOnSelect("clearToolButtons(); parent.Menu.location='/servlet/common.SvtTreeView?Page=Header&Caption=&Menus="+menus+"&ToolbarIcons="+numIcons+"';"+allowPerms+" setID('"+thisNode.getID()+"'); setType('"+thisNode.getType()+"'); setNodeID('"+thisNode.getFullNodeID()+"');");
	    if (!TreeCache.ALWAYSCHECK) TreeCache.getInstance().testForChildren(tree,thisNode);
    }   
    return thisNode;
  }  

  private final ListMap getAccoladesReports(IObjectContext context, Node parentNode)
  throws OculusException
  {
    ListMap thisList = new HashVector();
    Node thisNode = null;
    IAccessMgr acm = null;
    IGrantSet gs = null;
    if(context != null)
      acm = context.getCRM().getAccessMgr(context);
    if(acm != null)
      gs = acm.checkReposPerms(new IPermission[]{PermEnum.RPT_VIEWSTD, PermEnum.RPT_VIEWCUSTOM, PermEnum.RPT_DESIGNCUSTOM});
    if(gs != null && context.getConnection().getUserObject().isAccolades())
    {
      if (gs.contains(PermEnum.RPT_VIEWSTD))
      {
        thisNode = new Node("reports"+Node.delimiter+"accoladesstandard","Standard Reports", Node.REPORTS_ACCOLADES_STANDARD);
        thisNode.setImg("reportstree/Reports");
        thisNode.setParentID(parentNode.getID());
        thisNode.setParentNodeID(parentNode.getFullNodeID());
        thisNode.putChildren(new HashVector());
        thisNode.setOnClick("parent.parent.Attrib.location='common.reports.SvtReportsFrameset?rpt=accolades_standard';");
        thisList.put(thisNode.getID(),thisNode);
        addNode(thisNode);
      }//end if
      
      if (gs.contains(PermEnum.RPT_VIEWCUSTOM) || gs.contains(PermEnum.RPT_DESIGNCUSTOM))
      {
        thisNode = new Node("reports"+Node.delimiter+"accoladescustom","Custom Reports", Node.REPORTS_ACCOLADES_CUSTOM);
        thisNode.setImg("reportstree/Reports");
        thisNode.setParentID(parentNode.getID());
        thisNode.setParentNodeID(parentNode.getFullNodeID());
        thisNode.putChildren(new HashVector());
        thisNode.setOnClick("parent.parent.Attrib.location='common.reports.SvtReportsFrameset?rpt=accolades_custom';");
        thisList.put(thisNode.getID(),thisNode);
        addNode(thisNode);
      
        thisNode = new Node("reports"+Node.delimiter+"accoladesmrd","Custom MRD Reports", Node.REPORTS_ACCOLADES_MRD);
        thisNode.setImg("reportstree/Reports");
        thisNode.setParentID(parentNode.getID());
        thisNode.setParentNodeID(parentNode.getFullNodeID());
        thisNode.putChildren(new HashVector());
        thisNode.setOnClick("parent.parent.Attrib.location='common.reports.SvtReportsFrameset?rpt=accolades_mrd';");
        thisList.put(thisNode.getID(),thisNode);
        addNode(thisNode);
      }//end if
    }//end if

    return thisList;
  }        

  /**
  *  Returns a vector of product information.  Each entry in the vector
  * is a delimited string of product name and a flag as to whether or not
  * the user owns the product. 
  */
  private final ListMap getBaselineCategories(IObjectContext context, Node parentNode, Tree tree)
  throws OculusException
  {
    ListMap thisList = new HashVector();
    IIID listIID = new SequentialIID(Long.parseLong(parentNode.getID()));
    if (parentNode.getType().equals(Node.BASELINE))
    {
      IVersionBaseline temp = (IVersionBaseline)context.getCRM().getCompObject(context,"ProductBaseline",listIID);
      listIID = temp.getDefaultCategory().getIID();
    }
    IBusinessObjectColl list = (IBusinessObjectColl)context.getCRM().getCompObject(context,"BaselineCategoryColl",listIID);
    while (list.hasNext())
    {
      IBusinessObject bObj = (IBusinessObject)list.next();
      Node thisNode = createBaselineCategoryNode(bObj, parentNode, tree);
      if (thisNode != null)
      {
        thisList.put(thisNode.getID(),thisNode);
        addNode(thisNode);
      }
    }
    return thisList;
  }  


  /**
  *  Returns a vector of product information.  Each entry in the vector
  * is a delimited string of product name and a flag as to whether or not
  * the user owns the product. 
  */
  private final ListMap getBaselines(IObjectContext context, Node parentNode, Tree tree)
  throws OculusException
  {
    ListMap thisList = new HashVector();
    IIID listIID = new SequentialIID(Long.parseLong(parentNode.getID()));
    IBusinessObjectColl list = (IBusinessObjectColl)context.getCRM().getCompObject(context,"BaselineColl",listIID);
    while (list.hasNext())
    {
      IBusinessObject bObj = (IBusinessObject)list.next();
      Node thisNode = createBaselineNode(bObj, parentNode, tree);
      if (thisNode != null)
      {
        thisList.put(thisNode.getID(),thisNode);
        addNode(thisNode);
      }
    }
    return thisList;
  }  


  /**
  *  Returns a vector of product information.  Each entry in the vector
  * is a delimited string of product name and a flag as to whether or not
  * the user owns the product. 
  */
  private final ListMap getCategories(IObjectContext context, Node parentNode, Tree tree)
  throws OculusException
  {
    ListMap thisList = new HashVector();
    IIID listIID = new SequentialIID(Long.parseLong(parentNode.getID()));
    if (parentNode.getType().equals(Node.VERSION))
    {
      IProductVersion temp = (IProductVersion)context.getCRM().getCompObject(context,"ProductVersion",listIID);
      listIID = temp.getDefaultCategory().getIID();
    }
    IBusinessObjectColl list = (IBusinessObjectColl)context.getCRM().getCompObject(context,"CategoryList",listIID);
    while (list.hasNext())
    {
      IBusinessObject bObj = (IBusinessObject)list.next();
      Node thisNode = createCategoryNode(bObj,parentNode,tree);

      if (thisNode != null)
      {
        thisList.put(thisNode.getID(),thisNode);
        addNode(thisNode);
      }
    }
    return thisList;
  }  


  public ListMap getChildren(Tree tree, Node parentNode)
  throws OculusException
  {
		if (parentNode == null)
			parentNode = (Node)_nodes.get("-1");
    //  USE THIS IF YOU WANT TO USE THE TREE CACHING 
    //    if (parentNode.getChildren() == null)
    parentNode.putChildren(buildMapFor(tree,parentNode));
    parentNode.isLoaded(true);
    if (parentNode.hasChildren())
      return parentNode.getChildren();
    else
      return new HashVector();    
  }  

  public void testForChildren(Tree tree, Node parentNode)
  throws OculusException
  {
  	if (parentNode == null)
  		parentNode = (Node)_nodes.get("-1");
    parentNode.putChildren(buildMapFor(tree,parentNode));
  }  


  private final ListMap getCompassReports(IObjectContext context, Node parentNode)
  throws OculusException
  {
    ListMap thisList = new HashVector();
    Node thisNode = null;
    IAccessMgr acm = null;
    IGrantSet gs = null;
    if(context != null)
      acm = context.getCRM().getAccessMgr(context);
    if(acm != null)
      gs = acm.checkReposPerms(new IPermission[]{PermEnum.RPT_VIEWSTD, PermEnum.RPT_VIEWCUSTOM,PermEnum.RPT_DESIGNCUSTOM});
    if(gs != null && context.getConnection().getUserObject().isCompass())
    {
      if (gs.contains(PermEnum.RPT_VIEWSTD))
      {
        thisNode = new Node("reports"+Node.delimiter+"compassstandard","Standard Reports", Node.REPORTS_COMPASS_STANDARD);
        thisNode.setImg("reportstree/Reports");
        thisNode.setParentID(parentNode.getID());
        thisNode.setParentNodeID(parentNode.getFullNodeID());
        thisNode.putChildren(new HashVector());
        thisNode.setOnClick("parent.parent.Attrib.location='common.reports.SvtReportsFrameset?rpt=compass_standard';");
        thisList.put(thisNode.getID(),thisNode);
        addNode(thisNode);
      }//end if
      
      if (gs.contains(PermEnum.RPT_VIEWCUSTOM) || gs.contains(PermEnum.RPT_DESIGNCUSTOM))
      {
        thisNode = new Node("reports"+Node.delimiter+"compasscustom","Custom Reports", Node.REPORTS_COMPASS_CUSTOM);
        thisNode.setImg("reportstree/Reports");
        thisNode.setParentID(parentNode.getID());
        thisNode.setParentNodeID(parentNode.getFullNodeID());
        thisNode.putChildren(new HashVector());
        thisNode.setOnClick("parent.parent.Attrib.location='common.reports.SvtReportsFrameset?rpt=compass_custom';");
        thisList.put(thisNode.getID(),thisNode);
        addNode(thisNode);
      
        thisNode = new Node("reports"+Node.delimiter+"compassmrd","Custom MRD Reports", Node.REPORTS_COMPASS_MRD);
        thisNode.setImg("reportstree/Reports");
        thisNode.setParentID(parentNode.getID());
        thisNode.setParentNodeID(parentNode.getFullNodeID());
        thisNode.putChildren(new HashVector());
        thisNode.setOnClick("parent.parent.Attrib.location='common.reports.SvtReportsFrameset?rpt=compass_mrd';");
        thisList.put(thisNode.getID(),thisNode);
        addNode(thisNode);
      }//end if
      
    }//end if

    return thisList;
  }  


  /**
  *  Returns a vector of product information.  Each entry in the vector
  * is a delimited string of product name and a flag as to whether or not
  * the user owns the product. 
  */
  private final ListMap getFolders(IObjectContext context, Node parentNode, Tree tree)
  throws OculusException
  { 
       
    ListMap thisList = new HashVector();
    IBusinessObjectColl list = null;
    if (parentNode.getType().equals(Node.PUBLICFOLDER))
    {
       list = (IBusinessObjectColl)context.getCRM().getCompObject(context,"FolderColl",new SequentialIID(0));       
    }
    else
      {
        IIID listIID = new SequentialIID(Long.parseLong(parentNode.getID()));      
        list = (IBusinessObjectColl)context.getCRM().getCompObject(context,"ChildFolderColl",listIID);
      }
    while (list.hasNext())
    {
       IBusinessObject bObj = (IBusinessObject)list.next();       
       Node thisNode = createFolderNode(bObj, parentNode, tree);
        if (thisNode != null)
        {
          thisList.put(thisNode.getID(),thisNode);
          addNode(thisNode);
        }
     }
    return thisList;
  }  

  private final ListMap getProfileForms(IObjectContext context, Node parentNode)
  throws OculusException
  {

    IGrantSet gs = getConfigPerms(context);  
    ListMap thisList = new HashVector();
    Node thisNode = null;

    if (gs.contains(PermEnum.CONFIGURE_ALL) || gs.contains(PermEnum.CONFIG_PROFILE))
    { 
      
      thisNode = new Node("config"+Node.delimiter+"userprof","Contact Profiles", Node.USERPROF);
      thisNode.setImg("configtree/Form");
      thisNode.setParentID(parentNode.getID());
      thisNode.setParentNodeID(parentNode.getFullNodeID());
      thisNode.putChildren(new HashVector());
      thisNode.setOnClick("parent.parent.Attrib.location='config.SvtConfigRHS?ConfigNavigateAction=form&entity=contact';");
      thisList.put(thisNode.getID(),thisNode);
      addNode(thisNode);    

      thisNode = new Node("config"+Node.delimiter+"orgprof","Contact Organization Profiles", Node.ORGPROF);
      thisNode.setImg("configtree/Form");
      thisNode.setParentID(parentNode.getID());
      thisNode.setParentNodeID(parentNode.getFullNodeID());
      thisNode.putChildren(new HashVector());
      thisNode.setOnClick("parent.parent.Attrib.location='config.SvtConfigRHS?ConfigNavigateAction=form&entity=org';");
      thisList.put(thisNode.getID(),thisNode);
      addNode(thisNode);      
      
    }
    return thisList;
  }

  private final ListMap getOtherCompassForms(IObjectContext context, Node parentNode)
  throws OculusException
  {

    IGrantSet gs = getConfigPerms(context);  
    ListMap thisList = new HashVector();
    Node thisNode = null;

    
    if (gs.contains(PermEnum.CONFIGURE_ALL) || gs.contains(PermEnum.CONFIG_OTHER_COMPASS))
    { 
      thisNode = new Node("config"+Node.delimiter+"reactform","Reaction Forms", Node.REACTIONFORM);
      thisNode.setImg("configtree/Form");
      thisNode.setParentID(parentNode.getID());
      thisNode.setParentNodeID(parentNode.getFullNodeID());
      thisNode.putChildren(new HashVector());
      thisNode.setOnClick("parent.parent.Attrib.location='config.SvtConfigRHS?ConfigNavigateAction=form&entity=reaction';");
      thisList.put(thisNode.getID(),thisNode);
      addNode(thisNode);

      thisNode = new Node("config"+Node.delimiter+"prbform","Problem Statement Forms", Node.PROBLEMSTATEMENTFORM);
      thisNode.setImg("configtree/Form");
      thisNode.setParentID(parentNode.getID());
      thisNode.setParentNodeID(parentNode.getFullNodeID());
      thisNode.putChildren(new HashVector());
      thisNode.setOnClick("parent.parent.Attrib.location='config.SvtConfigRHS?ConfigNavigateAction=form&entity=problemstmt';");
      thisList.put(thisNode.getID(),thisNode);
      addNode(thisNode);
    }
    
    if (gs.contains(PermEnum.CONFIGURE_ALL) || gs.contains(PermEnum.CONFIG_PROFILE))
    {
      thisNode = new Node("config"+Node.delimiter+"profileforms","Profile Forms", Node.PROFILEFORMS);
      thisNode.setImg("GenericFolder");
      thisNode.setParentID(parentNode.getID());
      thisNode.setParentNodeID(parentNode.getFullNodeID());
      thisList.put(thisNode.getID(),thisNode);
      addNode(thisNode);
    }  
    return thisList;
  }
  
  
   private final ListMap getFormRouting(IObjectContext context, Node parentNode)
  throws OculusException
  {

    IGrantSet gs = getConfigPerms(context);  
    ListMap thisList = new HashVector();
    Node thisNode = null;

      thisNode = new Node("config"+Node.delimiter+"contextlist","Context List", Node.CONTEXTLIST);
      thisNode.setImg("configtree/Form");
      thisNode.setParentID(parentNode.getID());
      thisNode.setParentNodeID(parentNode.getFullNodeID());
      thisNode.putChildren(new HashVector());
      thisNode.setOnClick("parent.parent.Attrib.location='config.SvtConfigRHS?ConfigNavigateAction=routing&entity=contextlist';");
      thisList.put(thisNode.getID(),thisNode);
      addNode(thisNode);

      thisNode = new Node("config"+Node.delimiter+"disposition","Disposition Table", Node.DISPOSITION);
      thisNode.setImg("configtree/Form");
      thisNode.setParentID(parentNode.getID());
      thisNode.setParentNodeID(parentNode.getFullNodeID());
      thisNode.putChildren(new HashVector());
      thisNode.setOnClick("parent.parent.Attrib.location='config.SvtConfigRHS?ConfigNavigateAction=routing&entity=disposition';");
      thisList.put(thisNode.getID(),thisNode);
      addNode(thisNode);

    return thisList;
  }
  
  private final ListMap getForms(IObjectContext context, Node parentNode)
  throws OculusException
  {

    IGrantSet gs = getConfigPerms(context);  
    ListMap thisList = new HashVector();
    Node thisNode = null;


    if (gs.contains(PermEnum.CONFIGURE_ALL) || gs.contains(PermEnum.CONFIG_MARKET_INPUT))
    { 
      thisNode = new Node("config"+Node.delimiter+"mktinpforms","Market Input Forms", Node.MARKETINPUTFORMS);
      thisNode.setImg("GenericFolder");
      thisNode.setParentID(parentNode.getID());
      thisNode.setParentNodeID(parentNode.getFullNodeID());
      thisList.put(thisNode.getID(),thisNode);
      addNode(thisNode);
    }

   
    
    if (gs.contains(PermEnum.CONFIGURE_ALL) || gs.contains(PermEnum.CONFIG_OTHER_COMPASS) || gs.contains(PermEnum.CONFIG_PROFILE))
    { 

      thisNode = new Node("config"+Node.delimiter+"othercompassforms","Compass System Forms", Node.OTHERCOMPASSFORMS);
      thisNode.setImg("GenericFolder");
      thisNode.setParentID(parentNode.getID());
      thisNode.setParentNodeID(parentNode.getFullNodeID());
      thisList.put(thisNode.getID(),thisNode);
      addNode(thisNode);
    }
    
    if (gs.contains(PermEnum.CONFIGURE_ALL) || gs.contains(PermEnum.CONFIG_SPEC))
    { 

      thisNode = new Node("config"+Node.delimiter+"specforms","MRD Forms", Node.SPECIFICATIONFORMS);
      thisNode.setImg("GenericFolder");
      thisNode.setParentID(parentNode.getID());
      thisNode.setParentNodeID(parentNode.getFullNodeID());
      thisList.put(thisNode.getID(),thisNode);
      addNode(thisNode);
    }

 if (gs.contains(PermEnum.CONFIGURE_ALL) || gs.contains(PermEnum.CONFIG_MARKET_INPUT))
    { 

      thisNode = new Node("config"+Node.delimiter+"routing","Form Routing", Node.FORMROUTING);
      thisNode.setImg("GenericFolder");
      thisNode.setParentID(parentNode.getID());
      thisNode.setParentNodeID(parentNode.getFullNodeID());
      thisList.put(thisNode.getID(),thisNode);
      addNode(thisNode);
    }

    /*
    if (gs.contains(PermEnum.CONFIGURE_ALL) || gs.contains(PermEnum.CONFIG_ATTR_GRPS))
    { 

      thisNode = new Node("config"+Node.delimiter+"attributegroup","Attribute Groups", Node.ATTRIBUTEGROUP);
      thisNode.setImg("configtree/AttributeGroup");
      thisNode.setParentID(parentNode.getID());
      thisNode.setParentNodeID(parentNode.getFullNodeID());
      thisNode.putChildren(new HashVector());
      thisNode.setOnClick("parent.parent.Attrib.location='config.SvtConfigRHS?ConfigNavigateAction=group';");
      thisList.put(thisNode.getID(),thisNode);
      addNode(thisNode);
    }
    */
    
    return thisList;
  }  


  public static TreeCache getInstance()
  throws OculusException
  {
    if (_root == null)
      _root = new TreeCache();
    return _root;
  }  


  private final ListMap getMainBranches(IObjectContext context, Node parentNode, Tree tree)
  throws OculusException
  {
    ListMap thisList = new HashVector();
    Node thisNode = null;
    IAccessMgr acm = tree.getObjectContext().getCRM().getAccessMgr(tree.getObjectContext());
    IGrantSet gs = null; 
    IGrantSet gsRepos = null;
    String allowedPerms = null;
    ModuleKind module = null;
    if (parentNode.getFullNodeID().indexOf("onespec"+Node.delimiter) > -1)
      module = ModuleKind.ACCOLADES;
    else
    if (parentNode.getFullNodeID().indexOf("compass"+Node.delimiter) > -1)
      module = ModuleKind.COMPASS;

    ///////////////////// OneSpec Startup Nodes //////////////////////
    gsRepos = acm.checkReposPerms(new IPermission[]
      {PermEnum.PROD_ADD
    });
    allowedPerms = "enableAllMenus(false);";
    thisNode = new Node("onespec"+Node.delimiter+"active","Products", Node.ACTIVE);
    thisNode.setImg("Active");
    thisNode.setParentID(parentNode.getID());
    thisNode.setParentNodeID(parentNode.getFullNodeID());
    if (!tree.getBaseline())
    {
      if (gsRepos.contains(PermEnum.PROD_ADD)) allowedPerms += "ProductCreateItem.enable(true); ProductFolder_Tools.enableAll(true); Tree_Filters.enableAll(true);";
      thisNode.setOnSelect("clearToolButtons();parent.Menu.location='/servlet/common.SvtTreeView?Page=Header&Menus=ProductFolder+Functions,ProductFolder+Tools,Tree+Filters&ToolbarIcons=3';"+allowedPerms+"setNodeID('"+thisNode.getFullNodeID()+"');setType('"+thisNode.getFullNodeID()+"');setID('"+thisNode.getFullNodeID()+"');");
    }
    else
    {
      if (gsRepos.contains(PermEnum.PROD_ADD)) allowedPerms += "Tree_Filters.enableAll(true);";
      thisNode.setOnSelect("clearToolButtons();parent.Menu.location='/servlet/common.SvtTreeView?Page=Header&Menus=Tree+Filters&ToolbarIcons=1';"+allowedPerms+"setNodeID('"+thisNode.getFullNodeID()+"');setType('"+thisNode.getFullNodeID()+"');setID('"+thisNode.getFullNodeID()+"');");
    }
    thisList.put(thisNode.getID(),thisNode);
    addNode(thisNode);
    
    
    gsRepos = acm.checkReposPerms(new IPermission[]
    {PermEnum.SCOLL_ADD
    });
    allowedPerms = "enableAllMenus(false);";
    

    thisNode = new Node("onespec"+Node.delimiter+"standards","Standards", Node.STANDARDS);
    thisNode.setImg("Standard");
    thisNode.setParentID(parentNode.getID());
    thisNode.setParentNodeID(parentNode.getFullNodeID());
    
    if (!tree.getBaseline())
    {
      if (gsRepos.contains(PermEnum.SCOLL_ADD)) 
        allowedPerms += "StandardCreateItem.enable(true); StandardsFolder_Tools.enableAll(true); Tree_Filters.enableAll(true);";
      thisNode.setOnSelect("clearToolButtons();parent.Menu.location='/servlet/common.SvtTreeView?Page=Header&Menus=StandardsFolder+Functions,StandardsFolder+Tools,Tree+Filters&ToolbarIcons=5';"+allowedPerms+"setNodeID('"+thisNode.getFullNodeID()+"');setType('"+thisNode.getFullNodeID()+"');setID('"+thisNode.getFullNodeID()+"');");
    }
    else
    {
      if (gsRepos.contains(PermEnum.SCOLL_ADD)) 
        allowedPerms += "StandardCreateItem.enable(true);Tree_Filters.enableAll(true);";
    thisNode.setOnSelect("clearToolButtons();parent.Menu.location='/servlet/common.SvtTreeView?Page=Header&Menus=StandardsFolder+Functions,Tree+Filters&ToolbarIcons=2';"+allowedPerms+" setNodeID('"+thisNode.getFullNodeID()+"');setType('"+thisNode.getFullNodeID()+"');setID('"+thisNode.getFullNodeID()+"');");
    }
    
    thisList.put(thisNode.getID(),thisNode);
    addNode(thisNode);

    thisNode = new Node("onespec"+Node.delimiter+"trash","Trash", Node.TRASH);
    thisNode.setImg("Trash");
    thisNode.setParentID(parentNode.getID());
    thisNode.setParentNodeID(parentNode.getFullNodeID());
    thisNode.putChildren(new HashVector());
    thisNode.setOnClick("enableAllMenus(false);parent.Menu.location='/servlet/common.SvtTreeView?Page=Header';parent.parent.Attrib.location='common.trash.SvtTrashFrameset';");
    thisList.put(thisNode.getID(),thisNode);
    addNode(thisNode);


    ///////////////////// OneSpec Startup Nodes //////////////////////
    thisNode = new Node("compass"+Node.delimiter+"myfolder","Public Folders", Node.PUBLICFOLDER);
    thisNode.setImg("compasstree/PublicFolder");
    thisNode.setParentID(parentNode.getID());
    thisNode.setParentNodeID(parentNode.getFullNodeID());
    thisNode.setOnSelect("clearToolButtons();parent.Menu.location='/servlet/common.SvtTreeView?Page=Header&Menus=Folder+Functions,Tree+Filters'; setID(''); setType('"+thisNode.getType()+"');");
    thisList.put(thisNode.getID(),thisNode);
    addNode(thisNode);

    thisNode = new Node("compass"+Node.delimiter+"comptree","Compass Feature Tree", Node.COMPASSTREE);
    thisNode.setImg("Active");
    thisNode.setParentID(parentNode.getID());
    thisNode.setParentNodeID(parentNode.getFullNodeID());
    thisList.put(thisNode.getID(),thisNode);
    addNode(thisNode);

    thisNode = new Node("compass"+Node.delimiter+"trash","Trash", Node.TRASH);
    thisNode.setImg("Trash");
    thisNode.setParentID(parentNode.getID());
    thisNode.setParentNodeID(parentNode.getFullNodeID());
    thisNode.putChildren(new HashVector());
    thisNode.setOnClick("enableAllMenus(false);parent.parent.Attrib.location='common.trash.SvtTrashFrameset';");
    thisList.put(thisNode.getID(),thisNode);
    addNode(thisNode);

    ///////////////////// Admin Startup Nodes //////////////////////
		gs = acm.checkReposPerms(new IPermission[]
			{PermEnum.USER_MAINT, PermEnum.ADMIN_SIMPLE, PermEnum.CONFIG_ATTR_GRPS
		});
    // if USER_MAINT permission
    if (gs.contains(PermEnum.USER_MAINT))
    {
      thisNode = new Node("admin"+Node.delimiter+"user","Users", Node.USER);
      thisNode.setImg("admintree/User");
      thisNode.setParentID(parentNode.getID());
      thisNode.setParentNodeID(parentNode.getFullNodeID());
      thisNode.putChildren(new HashVector());
      thisNode.setOnClick("parent.parent.Attrib.location='common.org.SvtUserFrameset';");
      thisList.put(thisNode.getID(),thisNode);
      addNode(thisNode);
    }

    if (gs.contains(PermEnum.ADMIN_SIMPLE))
    {
      //if ADMIN_SIMPLE
      thisNode = new Node("admin"+Node.delimiter+"usergroup","Groups", Node.USERGROUP);
      thisNode.setImg("admintree/UserGroups");
      thisNode.setParentID(parentNode.getID());
      thisNode.setParentNodeID(parentNode.getFullNodeID());
      thisNode.putChildren(new HashVector());
      thisNode.setOnClick("parent.parent.Attrib.location='common.org.SvtGroupFrameset';");
      thisList.put(thisNode.getID(),thisNode);
      addNode(thisNode);

      thisNode = new Node("admin"+Node.delimiter+"organizations","Organizations", Node.USERORGANIZATION);
      thisNode.setImg("admintree/UserOrganization");
      thisNode.setParentID(parentNode.getID());
      thisNode.setParentNodeID(parentNode.getFullNodeID());
      thisNode.putChildren(new HashVector());
      thisNode.setOnClick("parent.parent.Attrib.location='common.org.SvtOrganizationFrameset';");
      thisList.put(thisNode.getID(),thisNode);
      addNode(thisNode);


      thisNode = new Node("admin"+Node.delimiter+"settings","Settings", Node.SETTINGS);
      thisNode.setImg("admintree/Settings");
      thisNode.setParentID(parentNode.getID());
      thisNode.setParentNodeID(parentNode.getFullNodeID());
      thisList.put(thisNode.getID(),thisNode);
      addNode(thisNode);

      thisNode = new Node("admin"+Node.delimiter+"permissions","Global Permissions", Node.PERMISSIONS);
      thisNode.setImg("admintree/Permissions");
      thisNode.setParentID(parentNode.getID());
      thisNode.setParentNodeID(parentNode.getFullNodeID());
      thisNode.putChildren(new HashVector());
      thisNode.setOnClick("parent.parent.Attrib.location='admin.acm.SvtACMFrameset?Object.Type="+Node.PERMISSIONS+"&Object.ID="+IDCONST.OCUREPOS.getLongValue()+"';");
      thisList.put(thisNode.getID(),thisNode);
      addNode(thisNode);
      
		}
		if (gs.contains(PermEnum.CONFIG_ATTR_GRPS))
		{
			thisNode = new Node("admin"+Node.delimiter+"attributegroup","Attribute Group Permissions", Node.ATTRIBUTEGROUP);
			thisNode.setImg("configtree/AttributeGroup");
			thisNode.setParentID(parentNode.getID());
			thisNode.setParentNodeID(parentNode.getFullNodeID());
			thisNode.putChildren(new HashVector());
			thisNode.setOnClick("parent.parent.Attrib.location='config.SvtConfigRHS?ConfigNavigateAction=group';");
			thisList.put(thisNode.getID(),thisNode);
			addNode(thisNode);
		
		}

    thisNode = new Node("config"+Node.delimiter+"forms","Forms", Node.FORMS);
    thisNode.setImg("GenericFolder");
    thisNode.setParentID(parentNode.getID());
    thisNode.setParentNodeID(parentNode.getFullNodeID());
    thisList.put(thisNode.getID(),thisNode);
    addNode(thisNode);

    gs = getConfigPerms(context);    
    if (gs.contains(PermEnum.CONFIGURE_ALL) || gs.contains(PermEnum.CONFIG_PROCESS))
    { 

      thisNode = new Node("config"+Node.delimiter+"processes","Process ", Node.PROCESSES);
      thisNode.setImg("GenericFolder");
      thisNode.setParentID(parentNode.getID());
      thisNode.setParentNodeID(parentNode.getFullNodeID());
      thisList.put(thisNode.getID(),thisNode);
      addNode(thisNode);
    }
  
    /*
    if (gs.contains(PermEnum.CONFIGURE_ALL) || gs.contains(PermEnum.CONFIG_PROFILE))
    { 

      thisNode = new Node("config"+Node.delimiter+"userprof","Contact Profiles", Node.USERPROF);
      thisNode.setImg("configtree/Form");
      thisNode.setParentID(parentNode.getID());
      thisNode.setParentNodeID(parentNode.getFullNodeID());
      thisNode.putChildren(new HashVector());
      thisNode.setOnClick("parent.parent.Attrib.location='config.SvtConfigRHS?ConfigNavigateAction=form&entity=contact';");
      thisList.put(thisNode.getID(),thisNode);
      addNode(thisNode);    

      thisNode = new Node("config"+Node.delimiter+"orgprof","Contact Organization Profiles", Node.ORGPROF);
      thisNode.setImg("configtree/Form");
      thisNode.setParentID(parentNode.getID());
      thisNode.setParentNodeID(parentNode.getFullNodeID());
      thisNode.putChildren(new HashVector());
      thisNode.setOnClick("parent.parent.Attrib.location='config.SvtConfigRHS?ConfigNavigateAction=form&entity=org';");
      thisList.put(thisNode.getID(),thisNode);
      addNode(thisNode);      
    }
    */
    //report tree startup nodes
    if(context.getConnection().getUserObject().isAccolades())
    {
      thisNode = new Node("reports"+Node.delimiter+"accolades","Accolades Reports", Node.REPORTS_ACCOLADES);
      thisNode.setImg("reportstree/AccoladesReports");
      thisNode.setParentID(parentNode.getID());
      thisNode.setParentNodeID(parentNode.getFullNodeID());
      thisList.put(thisNode.getID(),thisNode);
      addNode(thisNode);
    }//end if

    if(context.getConnection().getUserObject().isCompass())
    {
      thisNode = new Node("reports"+Node.delimiter+"compass","Compass Reports", Node.REPORTS_COMPASS);
      thisNode.setImg("reportstree/CompassReports");
      thisNode.setParentID(parentNode.getID());
      thisNode.setParentNodeID(parentNode.getFullNodeID());
      thisList.put(thisNode.getID(),thisNode);
      addNode(thisNode);
    }//end if


    return thisList;
  }
  
    
  private final ListMap getMarketInputForms(IObjectContext context, Node parentNode)
  throws OculusException
  {
    ListMap thisList = new HashVector();
    Node thisNode = null;

    thisNode = new Node("config"+Node.delimiter+"stdinp","Standard Input Forms", Node.STANDARDINPUTFORM);
    thisNode.setImg("configtree/Form");
    thisNode.setParentID(parentNode.getID());
    thisNode.setParentNodeID(parentNode.getFullNodeID());
    thisNode.putChildren(new HashVector());
    thisNode.setOnClick("parent.parent.Attrib.location='config.SvtConfigRHS?ConfigNavigateAction=form&entity=stdinput';");
    thisList.put(thisNode.getID(),thisNode);
    addNode(thisNode);  


    thisNode = new Node("config"+Node.delimiter+"artinp","Attachment Input Forms", Node.ARTICLEINPUTFORM);
    thisNode.setImg("configtree/Form");
    thisNode.setParentID(parentNode.getID());
    thisNode.setParentNodeID(parentNode.getFullNodeID());
    thisNode.putChildren(new HashVector());
    thisNode.setOnClick("parent.parent.Attrib.location='config.SvtConfigRHS?ConfigNavigateAction=form&entity=artinput';");
    thisList.put(thisNode.getID(),thisNode);
    addNode(thisNode);  
 
    thisNode = new Node("config"+Node.delimiter+"winqainp","WinLoss Input Forms", Node.WINLOSSFORM);
    thisNode.setImg("configtree/Form");
    thisNode.setParentID(parentNode.getID());
    thisNode.setParentNodeID(parentNode.getFullNodeID());
    thisNode.putChildren(new HashVector());
    thisNode.setOnClick("parent.parent.Attrib.location='config.SvtConfigRHS?ConfigNavigateAction=form&entity=winqainput';");
    thisList.put(thisNode.getID(),thisNode);
    addNode(thisNode);  
 
    
    return thisList;
  }  


  private final ListMap getProcesses(IObjectContext context, Node parentNode)
  throws OculusException
  {
    ListMap thisList = new HashVector();
    Node thisNode = null;

    IGrantSet gs = getConfigPerms(context);  
    if (gs.contains(PermEnum.CONFIGURE_ALL) || gs.contains(PermEnum.CONFIG_PROCESS))
    { 
      thisNode = new Node("config"+Node.delimiter+"processrole","Process Roles", Node.PROCESSROLE);
      thisNode.setImg("configtree/Roles");
      thisNode.setParentID(parentNode.getID());
      thisNode.setParentNodeID(parentNode.getFullNodeID());
      thisNode.putChildren(new HashVector());
      thisNode.setOnClick("parent.parent.Attrib.location='config.process.SvtProcessRoleFrameset';");
      thisList.put(thisNode.getID(),thisNode);
      addNode(thisNode);  

      thisNode = new Node("config"+Node.delimiter+"workflow","Workflows", Node.WORKFLOW);
      thisNode.setImg("GenericFolder");
      thisNode.setParentID(parentNode.getID());
      thisNode.setParentNodeID(parentNode.getFullNodeID());
      //thisNode.putChildren(new HashVector());
      //thisNode.setOnClick("parent.parent.Attrib.location='config.process.SvtProcessRoleFrameset';");
      thisList.put(thisNode.getID(),thisNode);
      addNode(thisNode);  
    }
    return thisList;
  }
  
    
  /**
  * Returns a ClsTree object that contains the entire structure of the
  * tree.  The only actual processing that goes on here is that a list
  * of products is created and then a list of versions for each product
  * is asked for.
  */
  private final ListMap getProducts(IObjectContext context, Node parentNode, Tree tree)
  throws OculusException
  {
    ListMap thisList = new HashVector();
    long parentID = 1;
    //  if (parentNode.getID().indexOf("onespec") == -1)
    //    parentID = Long.parseLong(parentNode.getID());
    IIID listIID = new SequentialIID(parentID);
    IBusinessObjectColl list = (IBusinessObjectColl)context.getCRM().getCompObject(context,"ProductList",listIID);
    while (list.hasNext())
    {
      IBusinessObject bObj = (IBusinessObject)list.next();

      Node thisNode = createProductNode(bObj, parentNode, tree);
      if (thisNode != null)
      {
        thisList.put(thisNode.getID(),thisNode);
        addNode(thisNode);
      }
    }
    return thisList;
  }
  
  
  private final ListMap getSettings(IObjectContext context, Node parentNode)
  throws OculusException
  {
    ListMap thisList = new HashVector();
    Node thisNode = null;

    thisNode = new Node("settings"+Node.delimiter+"harddrivespace","Hard Drive Space", Node.HARD_DRIVE_SPACE);
    thisNode.setImg("admintree/HardDriveSpace");
    thisNode.setParentID(parentNode.getID());
    thisNode.setParentNodeID(parentNode.getFullNodeID());
    thisNode.putChildren(new HashVector());
    thisNode.setOnClick("parent.parent.Attrib.location='common.settings.SvtHardDriveSpace';");
    thisList.put(thisNode.getID(),thisNode);
    addNode(thisNode);
	
	thisNode = new Node("settings"+Node.delimiter+"engspecfolder","Eng Spec Folders", Node.ENG_SPEC_FOLDERS);
    thisNode.setImg("admintree/EngSpec");
    thisNode.setParentID(parentNode.getID());
    thisNode.setParentNodeID(parentNode.getFullNodeID());
    thisNode.putChildren(new HashVector());
    thisNode.setOnClick("parent.parent.Attrib.location='common.settings.SvtEngSpecFolderFrameset';");
    thisList.put(thisNode.getID(),thisNode);
    addNode(thisNode);

    thisNode = new Node("settings"+Node.delimiter+"loginlog","Login Log", Node.LOGIN_LOG);
    thisNode.setImg("admintree/LoginLog");
    thisNode.setParentID(parentNode.getID());
    thisNode.setParentNodeID(parentNode.getFullNodeID());
    thisNode.putChildren(new HashVector());
    thisNode.setOnClick("parent.parent.Attrib.location='common.settings.SvtUserLogList';");
    thisList.put(thisNode.getID(),thisNode);
    addNode(thisNode);

    thisNode = new Node("settings"+Node.delimiter+"mailserver","Mail Server", Node.MAIL_SERVER);
    thisNode.setImg("admintree/MailServer");
    thisNode.setParentID(parentNode.getID());
    thisNode.setParentNodeID(parentNode.getFullNodeID());
    thisNode.putChildren(new HashVector());
    thisNode.setOnClick("parent.parent.Attrib.location='common.settings.SvtMailServer';");
    thisList.put(thisNode.getID(),thisNode);
    addNode(thisNode);

    thisNode = new Node("settings"+Node.delimiter+"extraneturl","Extranet Location", Node.EXTRANET_URL);
    thisNode.setImg("admintree/ExtraNetTree");
    thisNode.setParentID(parentNode.getID());
    thisNode.setParentNodeID(parentNode.getFullNodeID());
    thisNode.putChildren(new HashVector());
    thisNode.setOnClick("parent.parent.Attrib.location='common.settings.SvtExtranetLocation';");
    thisList.put(thisNode.getID(),thisNode);
    addNode(thisNode);
/*
    thisNode = new Node("settings"+Node.delimiter+"userlicenses","User Licenses", Node.USER_LICENSES);
    thisNode.setImg("admintree/UserLicense");
    thisNode.setParentID(parentNode.getID());
    thisNode.setParentNodeID(parentNode.getFullNodeID());
    thisNode.putChildren(new HashVector());
    thisNode.setOnClick("parent.parent.Attrib.location='common.settings.SvtUserLicenses';");
    thisList.put(thisNode.getID(),thisNode);
    addNode(thisNode);
*/
    thisNode = new Node("settings"+Node.delimiter+"licensekey","License Key", Node.LICENSE_KEY);
    thisNode.setImg("admintree/LicenseKey");
    thisNode.setParentID(parentNode.getID());
    thisNode.setParentNodeID(parentNode.getFullNodeID());
    thisNode.putChildren(new HashVector());
    thisNode.setOnClick("parent.parent.Attrib.location='admin.license.SvtLicenseKeyFrameset';");
    thisList.put(thisNode.getID(),thisNode);
    addNode(thisNode);


    return thisList;
  }  
  
    
  private final ListMap getSpecificationForms(IObjectContext context, Node parentNode)
  throws OculusException
  {
    ListMap thisList = new HashVector();
    Node thisNode = null;


    thisNode = new Node("config"+Node.delimiter+"prodform","Product Forms", Node.PRODUCTFORM);
    thisNode.setImg("configtree/Form");
    thisNode.setParentID(parentNode.getID());
    thisNode.setParentNodeID(parentNode.getFullNodeID());
    thisNode.putChildren(new HashVector());
    thisNode.setOnClick("parent.parent.Attrib.location='config.SvtConfigRHS?ConfigNavigateAction=form&entity=product';");
    thisList.put(thisNode.getID(),thisNode);
    addNode(thisNode);

    thisNode = new Node("config"+Node.delimiter+"verform","Version Forms", Node.VERSIONFORM);
    thisNode.setImg("configtree/Form");
    thisNode.setParentID(parentNode.getID());
    thisNode.setParentNodeID(parentNode.getFullNodeID());
    thisNode.putChildren(new HashVector());
    thisNode.setOnClick("parent.parent.Attrib.location='config.SvtConfigRHS?ConfigNavigateAction=form&entity=version';");
    thisList.put(thisNode.getID(),thisNode);
    addNode(thisNode);

    thisNode = new Node("config"+Node.delimiter+"catform","Category Forms", Node.CATEGORYFORM);
    thisNode.setImg("configtree/Form");
    thisNode.setParentID(parentNode.getID());
    thisNode.setParentNodeID(parentNode.getFullNodeID());
    thisNode.putChildren(new HashVector());
    thisNode.setOnClick("parent.parent.Attrib.location='config.SvtConfigRHS?ConfigNavigateAction=form&entity=category';");
    thisList.put(thisNode.getID(),thisNode);
    addNode(thisNode);


    thisNode = new Node("config"+Node.delimiter+"featform","Feature Forms", Node.FEATUREFORM);
    thisNode.setImg("configtree/Form");
    thisNode.setParentID(parentNode.getID());
    thisNode.setParentNodeID(parentNode.getFullNodeID());
    thisNode.putChildren(new HashVector());
    thisNode.setOnClick("parent.parent.Attrib.location='config.SvtConfigRHS?ConfigNavigateAction=form&entity=feature';");
    thisList.put(thisNode.getID(),thisNode);
    addNode(thisNode);
     
    /*  
    thisNode = new Node("config"+Node.delimiter+"altform","Alternative Forms", Node.ALTERNATIVEFORM);
    thisNode.setImg("configtree/Form");
    thisNode.setParentID(parentNode.getID());
    thisNode.setParentNodeID(parentNode.getFullNodeID());
    thisNode.putChildren(new HashVector());
    thisNode.setOnClick("parent.parent.Attrib.location='config.SvtConfigRHS?ConfigNavigateAction=form&entity=alternative';");
    thisList.put(thisNode.getID(),thisNode);
    addNode(thisNode);
    */
    return thisList;
  }  
  
  
  /**
  *  Returns a vector of product information.  Each entry in the vector
  * is a delimited string of product name and a flag as to whether or not
  * the user owns the product. 
  */
  private final ListMap getStandardColls(IObjectContext context, Node parentNode, Tree tree)
  throws OculusException
  {
    ListMap thisList = new HashVector();
    IBusinessObjectColl list = null;
    if (parentNode.getType().equals(Node.STANDARDS))
      list = (IBusinessObjectColl)context.getCRM().getCompObject(context,"ParentStandardsCollectionColl",new SequentialIID(0));
    else
    {
      IIID listIID = new SequentialIID(Long.parseLong(parentNode.getID()));
      list = (IBusinessObjectColl)context.getCRM().getCompObject(context,"StandardsCollectionColl",listIID);
      //list = (IBusinessObjectColl)context.getCRM().getCompObject(context,"ChildStandardColl",listIID);
    }
    while (list.hasNext())
    {
      IBusinessObject bObj = (IBusinessObject)list.next();
      Node thisNode = createStandardNode(bObj,parentNode,tree);
      if (thisNode != null)
      {
        thisList.put(thisNode.getID(),thisNode);
        addNode(thisNode);
      }
    }
    return thisList;
  }  
  
  
  /**
  * Returns a list of versions visible to the current user for the
  * given product.
  */
  private final ListMap getSubVersions(IObjectContext context, Node parentNode, Tree tree)
  throws OculusException
  {
    ListMap thisList = new HashVector();
    IIID listIID = new SequentialIID(Long.parseLong(parentNode.getID()));
    IBusinessObjectColl list = (IBusinessObjectColl)context.getCRM().getCompObject(context,"SubProductVersionColl",listIID);
    while (list.hasNext())
    {
      IBusinessObject bObj = (IBusinessObject)list.next();
      Node thisNode = createVersionNode(bObj,parentNode,tree,true);
      if (thisNode != null)
      {
        thisList.put(thisNode.getID(),thisNode);
        addNode(thisNode);
      }
    }
    return thisList;
  }  
  
  
  private final ListMap getTasks(IObjectContext context, Node parentNode)
  throws OculusException
  {
    ListMap thisList = new HashVector();

    Node thisNode = new Node("1inbox","Inbox", Node.INBOX);
    thisNode.setImg("Inbox");
    thisNode.setOnClick("parent.parent.Attrib.location='/servlet/tasks.inbox.SvtInboxFrameset';");
    thisNode.setParentID(parentNode.getID());
    thisNode.setParentNodeID(parentNode.getFullNodeID());
    thisNode.putChildren(new HashVector());
    thisList.put(thisNode.getID(),thisNode);
    addNode(thisNode);

    thisNode = new Node("2outbox","Feature Tracking", Node.OUTBOX);
    thisNode.setImg("Forwarded");
    thisNode.setOnClick("parent.parent.Attrib.location='/servlet/tasks.outbox.SvtOutboxFrameset';");
    thisNode.setParentID(parentNode.getID());
    thisNode.setParentNodeID(parentNode.getFullNodeID());
    thisNode.putChildren(new HashVector());
    thisList.put(thisNode.getID(),thisNode);
    addNode(thisNode);

    thisNode = new Node("3topics","Notifications", Node.TOPICS);
    thisNode.setImg("Communications");
    thisNode.setOnClick("parent.parent.Attrib.location='/servlet/tasks.notification.SvtNotificationFrameset';");
    thisNode.setParentID(parentNode.getID());
    thisNode.setParentNodeID(parentNode.getFullNodeID());
    thisNode.putChildren(new HashVector());
    thisList.put(thisNode.getID(),thisNode);
    addNode(thisNode);

    return thisList;
  }  
  
  
  /**
  * Returns a list of versions visible to the current user for the
  * given product.
  */
  private final ListMap getVersions(IObjectContext context, Node parentNode, Tree tree)
  throws OculusException
  {
    ListMap thisList = new HashVector();
    IIID listIID = new SequentialIID(Long.parseLong(parentNode.getID()));
    IBusinessObjectColl list = (IBusinessObjectColl)context.getCRM().getCompObject(context,"ProductVersionList",listIID);
    while (list.hasNext())
    {
      IBusinessObject bObj = (IBusinessObject)list.next();
      Node thisNode = createVersionNode(bObj,parentNode,tree);
      if (thisNode != null)
      {
        thisList.put(thisNode.getID(),thisNode);
        addNode(thisNode);
      }
    }
    return thisList;
  }  
  
  
  private final ListMap getWorkflows(IObjectContext context, Node parentNode)
  throws OculusException
  {
    ListMap thisList = new HashVector();
    Node thisNode = null;

    IGrantSet gs = getConfigPerms(context);  
    if (gs.contains(PermEnum.CONFIGURE_ALL) || gs.contains(PermEnum.CONFIG_PROCESS))
    { 

      thisNode = new Node("config"+Node.delimiter+"statemachine","Feature Development WorkFlow", Node.STATEMACHINE);
      thisNode.setImg("configtree/StateMachine");
      thisNode.setParentID(parentNode.getID());
      thisNode.setParentNodeID(parentNode.getFullNodeID());
      thisNode.putChildren(new HashVector());
      thisNode.setOnClick("parent.parent.Attrib.location='config.process.SvtProcessFrameset';");
      thisList.put(thisNode.getID(),thisNode);
      addNode(thisNode);
    }

    return thisList;
  }  
  
  
  public void removeNode(Node child)
  throws OculusException
  {
    _nodes.remove(child.getID());
    ListMap children = child.getChildren();
    Iterator i = children.iterator();
    while (i.hasNext())
      removeNode((Node)i.next());
  }  
  
  
  public void updateNode(Node child)
  throws OculusException
  {
    addNode(child);
  }  



}
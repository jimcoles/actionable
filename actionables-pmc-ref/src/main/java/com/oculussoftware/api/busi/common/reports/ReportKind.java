package com.oculussoftware.api.busi.common.reports;

import com.oculussoftware.util.IntEnum;
import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.api.sysi.license.ModuleKind;
import com.oculussoftware.api.ui.html.GraphKind;
import java.util.Vector;

/*
* $Workfile: ReportKind.java $
* Description: Integer Enumeration of ReportKinds.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* PRB01789        Cuihua Zhang    8/9/2000    for VERION_CHANGES the configtype is changed to VERSIONCHANGES
* BUG02249        Ismail Syed     9/6/00      Removed development status report as it dosen't exist
* DES02331        Ismail Syed     9/6/00      Efficiency Report to "Development Efficiency Report"  
*/

/** 
* Integer Enumeration of Report Kinds.  A ReportKind houses 
* information about Standard Reports.  Name, Description, to 
* which Module(s) they belong, the ReportConfigType, whether or
* not the Report has a Graph, and the different possible types 
* of Graphs are all the pieces of information that this class
* contains.
*
* @author Egan Royal
*/
public final class ReportKind extends IntEnum
{
	private String           _name;
	private String           _description;
	private ModuleKind[]     _mk;
	private boolean          _isstandard;
  private ReportConfigType _rct;
  private boolean          _hasgraph;
  //this is what happens when requirements change after you've already
  //written code.  There really isn't a reason for having direct
  //references to the ui layer in this class.
  private com.oculussoftware.api.ui.html.GraphKind[] _gk;  //this sucks
	
  /**
  * The Baseline Display Report int value 0.
  */
  public final static ReportKind BASELINE_DISPLAY =
	  new ReportKind(0,
                  "Baseline Display Report",
                  "description", 
                  new ModuleKind[] { ModuleKind.ACCOLADES }, 
                  true, 
                  ReportConfigType.VERSION,
                  false,
                  null);
  
  /**
  * The Version Level Status Report int value 1.
  */
	public final static ReportKind VERSION_LEVEL_STATUS = 
	  new ReportKind(1,
                   "Version Level Status Report",
                   "description",
                   new ModuleKind[] { ModuleKind.ACCOLADES }, 
                   true,
                   ReportConfigType.VERSION,
                   true,
                   new GraphKind[] { GraphKind.HORIZONTAL_BAR, GraphKind.VERTICAL_BAR});
		
  /**
  * The Baseline Comparison Report int value 2.
  */
	public final static ReportKind BASELINE_COMPARISON = 
	  new ReportKind(2,
                   "Baseline Comparison Report",
                   "description", 
                   new ModuleKind[] { ModuleKind.ACCOLADES }, 
                   true,
                   ReportConfigType.BASELINE_COMP,
                   true,
                   new GraphKind[] { GraphKind.HORIZONTAL_BAR, GraphKind.VERTICAL_BAR});

  /**
  * The Feature Level Status Report int value 3.
  */
	public final static ReportKind FEATURE_LEVEL_STATUS = 
	  new ReportKind(3,
                   "Feature Level Status Report",
                   "description", 
                   new ModuleKind[] { ModuleKind.ACCOLADES }, 
                   true,
                   ReportConfigType.FEATURE_LEVEL_STATUS,
                   false,
                   null);

  /**
  * The Dependencies Report int value 4.
  */
	public final static ReportKind DEPENDENCIES = 
	  new ReportKind(4,
                   "Dependencies Report",
                   "description", 
                   new ModuleKind[] { ModuleKind.ACCOLADES, ModuleKind.COMPASS }, 
                   true,
                   ReportConfigType.VERSION_SINGLE_OPTIONS,
                   false,
                   null);

  /**
  * The Feature Revision Report int value 5.
  */
	public final static ReportKind FEATURE_REVISION = 
	  new ReportKind(5,
                   "Feature Revision Report",
                   "description", 
                   new ModuleKind[] { ModuleKind.ACCOLADES, ModuleKind.COMPASS }, 
                   true,
                   ReportConfigType.VERSION_FEATURE,
                   false,
                   null);

  /**
  * The Feature Score Report int value 6.
  */
	public final static ReportKind FEATURE_SCORE = 
	  new ReportKind(6,
                   "Feature Score Report",
                   "description", 
                   new ModuleKind[] { ModuleKind.ACCOLADES, ModuleKind.COMPASS }, 
                   true,
                   ReportConfigType.VERSION_OPTIONS,
                   false,
                   null);

  /**
  * The Efficiency Report int value 7.
  */
	public final static ReportKind EFFICIENCY = 
	  new ReportKind(7,
                   "Development Efficiency Report",
                   "description", 
                   new ModuleKind[] { ModuleKind.ACCOLADES }, 
                   true,
                   ReportConfigType.VERSION,
                   false,
                   null);

  /**
  * The Feature Transactions Report int value 8.
  */
	public final static ReportKind FEATURE_TRANSACTIONS = 
	  new ReportKind(8,
                   "Feature Transactions Report",
                   "description", 
                   new ModuleKind[] { ModuleKind.ACCOLADES }, 
                   true,
                   ReportConfigType.FEATURE,
                   false,
                   null);

  /**
  * The Version Changes Report int value 9.
  */
	public final static ReportKind VERSION_CHANGES = 
	  new ReportKind(9,
                   "Version Changes Report",
                   "description", 
                   new ModuleKind[] { ModuleKind.ACCOLADES }, 
                   true,
                   ReportConfigType.VERSIONCHANGES,
                   false,
                   null);

  /**
  * The ProblemStatement Score Report int value 10.
  */
	public final static ReportKind PROBLEM_STATEMENT_SCORE = 
	  new ReportKind(10,
                   "Problem Statement Score Report",
                   "description", 
                   new ModuleKind[] { ModuleKind.ACCOLADES, ModuleKind.COMPASS }, 
                   true,
                   ReportConfigType.VERSION_FOLDER,
                   false,
                   null);
	
  /**
  * The Standard MRD Report int value 11.
  */
	public final static ReportKind MRD = 
	  new ReportKind(11,
                   "Marketing Requirements Document (MRD)",
                   "description", 
                   new ModuleKind[] { ModuleKind.ACCOLADES, ModuleKind.COMPASS }, 
                   true,
                   ReportConfigType.MRD,
                   false,
                   null);
	
  /**
  * The Tabular MRD Report int value 12.
  */
	public final static ReportKind TABULAR_MRD = 
	  new ReportKind(12,
                   "Tabular MRD",
                   "description", 
                   new ModuleKind[] { ModuleKind.ACCOLADES, ModuleKind.COMPASS }, 
                   true,
                   ReportConfigType.VERSION_OPTIONS,
                   false,
                   null);

  /**
  * The Percent Complete Report int value 13.
  */
  public final static ReportKind PERCENT_COMPLETE = 
	  new ReportKind(13,
                   "Percent Complete by Priority Report",
                   "description", 
                   new ModuleKind[] { ModuleKind.ACCOLADES }, 
                   true,
                   ReportConfigType.VERSION,
                   true,
                   new GraphKind[] { GraphKind.HORIZONTAL_BAR, GraphKind.VERTICAL_BAR});
 	
  /**
  * The Q&A Summary Report int value 14.
  */
	public final static ReportKind Q_AND_A_SUMMARY = 
	  new ReportKind(14,
                   "Q&A Summary Report",
                   "description", 
                   new ModuleKind[] { ModuleKind.COMPASS }, 
                   true,
                   ReportConfigType.Q_AND_A,
                   true,
                   new GraphKind[] { GraphKind.HORIZONTAL_BAR, GraphKind.VERTICAL_BAR, GraphKind.PIE});
	
  /**
  * The Folder Inventory Report int value 15.
  */
	public final static ReportKind FOLDER_INVENTORY = 
	  new ReportKind(15,
                   "Folder Inventory Report",
                   "description", 
                   new ModuleKind[] { ModuleKind.COMPASS }, 
                   true,
                   ReportConfigType.FOLDER,
                   false,
                   null);

  /**
  * The Trace Report int value 16.
  */
	public final static ReportKind TRACE = 
	  new ReportKind(16,
                   "Trace Report",
                   "description", 
                   new ModuleKind[] { ModuleKind.COMPASS }, 
                   true,
                   ReportConfigType.TRACE,
                   false, 
                   null);

	/** Private constructor */
  private ReportKind(int s, String name, String desc, 
                      ModuleKind[] mk, boolean standard, 
                      ReportConfigType rct, boolean hasGraph,
                      GraphKind[] gk) 
	{ 
	  super(s);
		_name = name;
		_description = desc;
		_mk = mk;
		_isstandard = standard;
    _rct = rct; 
    _hasgraph = hasGraph;
    _gk = gk;
  }
	
  /**
  * Takes an int and returns a ReportKind iff the int is valid.
  *
  * @param d the int value
  * @return The ReportKind that corresponds to the int value.
  * @exception com.oculussoftware.api.sysi.OculusException 
  * If the int value does not correspond to a valid ReportKind
  */
  public static ReportKind getInstance(int d) throws OculusException
  {
    if(d == 0)
      return BASELINE_DISPLAY;
	  else if(d == 1)
		  return VERSION_LEVEL_STATUS;
		else if(d == 2)
		  return BASELINE_COMPARISON;
		else if(d == 3)
		  return FEATURE_LEVEL_STATUS;
		else if(d == 4)
		  return DEPENDENCIES;
		else if(d == 5)
		  return FEATURE_REVISION;
		else if(d == 6)
		  return FEATURE_SCORE;
		else if(d == 7)
		  return EFFICIENCY;
		else if(d == 8)
		  return FEATURE_TRANSACTIONS;
		else if(d == 9)
		  return VERSION_CHANGES;
		else if(d == 10)
		  return PROBLEM_STATEMENT_SCORE;
	  else if(d == 11)
		  return MRD;
		else if(d == 12)
		  return TABULAR_MRD;
		else if(d == 13)
		  return PERCENT_COMPLETE;
		else if(d == 14)
		  return Q_AND_A_SUMMARY;
		else if(d == 15)
		  return FOLDER_INVENTORY;
		else if(d == 16)
		  return TRACE;
    else
      throw new OculusException("Invalid ReportKind.");
  }//end getInstance
	
  /**
  * This accessor method returns the name (display String) of the ReportKind.
  * @return The name of the ReportKind.  
  */
	public String getName()
	{ return _name; }
   
  /**
  * This accessor method returns the description (display String) of the ReportKind.
  * @return The description of the ReportKind.  
  */ 
	public String getDescription() throws OculusException
	{ return _description; }
	
  /**
  * This accessor method returns an array of ModuleKinds to 
  * which the Report belongs.
  * @return An array of ModuleKinds.  
  */
	public ModuleKind[] getModuleKinds()
	{ return _mk; }
	
  /**
  * This accessor method returns an array of GraphKinds that 
  * are the possible options for the Report.
  * @return An array of ModuleKinds.  
  */
  public GraphKind[] getGraphKinds()
  { return _gk; }
  
  /**
  * This accessor method returns true iff the Report is a Standard Report.
  * This method is not currently being used since all of the ReportKinds 
  * are Standard Reports.
  * @return true.  
  */
	public boolean isStandard() 
	{ return _isstandard; }
  
  /**
  * This accessor method returns the ReportConfigType for this ReportKind.
  * @return The ReportConfigType for this ReportKind.  
  */
  public ReportConfigType getReportConfigType()
  { return _rct; }
  
  /**
  * This accessor method returns true iff this Standard Report can have a
  * Graph.
  * @return true iff this Standard Report can have a Graph.  
  */
  public boolean hasGraph()
  { return _hasgraph; }
  
  /**
  * This accessor method returns an array of all the ReportKinds.
  * @return An array of all the ReportKinds.  
  */
  public static ReportKind[] getReports()
  {
    return new ReportKind[] { BASELINE_DISPLAY, VERSION_LEVEL_STATUS,
                              BASELINE_COMPARISON, FEATURE_LEVEL_STATUS,
                              DEPENDENCIES, FEATURE_REVISION,
                              FEATURE_SCORE,
                              EFFICIENCY, FEATURE_TRANSACTIONS,
                              VERSION_CHANGES, PROBLEM_STATEMENT_SCORE,
                              MRD, TABULAR_MRD, PERCENT_COMPLETE,
                              Q_AND_A_SUMMARY, FOLDER_INVENTORY, TRACE };
  }
  
  /**
  * This accessor method returns an array of all the Accolades ReportKinds.
  * @return An array of all the Accolades ReportKinds.  
  */
  public static ReportKind[] getAccoladesStandardReports()
  {
    return getStandardReports(ModuleKind.ACCOLADES);   
  }
  
  /**
  * This accessor method returns an array of all the Compass ReportKinds.
  * @return An array of all the Compass ReportKinds.  
  */
  public static ReportKind[] getCompassStandardReports()
  {
    return getStandardReports(ModuleKind.COMPASS);
  }
  
  /**
  * This accessor method takes a ModuleKind and returns an array 
  * of all the ReportKinds.
  * @param mk The module for which the array of ReportKinds is desired.
  * @return An array of all the ReportKinds given a ModuleKind.  
  */
  public static ReportKind[] getStandardReports(ModuleKind mk)
  {
    ReportKind[] allrpts = getReports();
    Vector v = new Vector();
    for(int i = 0; i < allrpts.length; i++)
    {
      if(allrpts[i].isStandard())
      {
        ModuleKind[] mks = allrpts[i].getModuleKinds();
        for(int j = 0; j < mks.length; j++)
          if(mks[j].equals(mk))
            v.addElement(allrpts[i]);
      }//end if
    }//end for
    
    ReportKind[] rpts = new ReportKind[v.size()];
    for(int i = 0; i < rpts.length; i++)
      rpts[i] = (ReportKind)v.elementAt(i);
    
    return rpts;
  }
  
  /**
  * This accessor method returns an array of all the Accolades ReportKinds
  * for Custom Report.  This method is not currently being used because there
  * are no Custom ReportKinds.
  * @return An array of all the Accolades Custom ReportKinds.  
  */
  public static ReportKind[] getAccoladesCustomReports()
  {
    return getCustomReports(ModuleKind.ACCOLADES);   
  }
  
  /**
  * This accessor method returns an array of all the Compass ReportKinds
  * for Custom Report.  This method is not currently being used because there
  * are no Custom ReportKinds.
  * @return An array of all the Compass Custom ReportKinds.  
  */
  public static ReportKind[] getCompassCustomReports()
  {
    return getCustomReports(ModuleKind.COMPASS);
  }
  
  /**
  * This accessor method takes a ModuleKind and returns an array 
  * of all the Custom ReportKinds.
  * @param mk The module for which the array of Custom ReportKinds is desired.
  * @return An array of all the ReportKinds given a ModuleKind.  
  */
  public static ReportKind[] getCustomReports(ModuleKind mk)
  {
    ReportKind[] allrpts = getReports();
    Vector v = new Vector();
    for(int i = 0; i < allrpts.length; i++)
    {
      if(!allrpts[i].isStandard())
      {
        ModuleKind[] mks = allrpts[i].getModuleKinds();
        for(int j = 0; j < mks.length; j++)
          if(mks[j].equals(mk))
            v.addElement(allrpts[i]);
      }//end if
    }//end for
    
    ReportKind[] rpts = new ReportKind[v.size()];
    for(int i = 0; i < rpts.length; i++)
      rpts[i] = (ReportKind)v.elementAt(i);
    
    return rpts;
  }	
}
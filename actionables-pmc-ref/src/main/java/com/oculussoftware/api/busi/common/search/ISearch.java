package com.oculussoftware.api.busi.common.search;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.sysi.OculusException;
import java.util.*;
import java.sql.Timestamp;
import com.oculussoftware.api.repi.xmeta.*;
import com.oculussoftware.api.busi.*;

/**
* $Workfile: ISearch.java $
* Create Date: 6/25/2000
* Description: Supports AdvancedSearch and BasicReport.
*
* Copyright 7-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*
* $History: ISearch.java $
 * 
 * *****************  Version 9  *****************
 * User: Eroyal       Date: 9/22/00    Time: 3:26p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/search
 * javadoc
 * 
 * *****************  Version 8  *****************
 * User: Eroyal       Date: 8/08/00    Time: 1:31p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/search
 * 
 * *****************  Version 7  *****************
 * User: Eroyal       Date: 8/08/00    Time: 11:16a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/search
 * handle alias
 * 
 * *****************  Version 5  *****************
 * User: Eroyal       Date: 8/07/00    Time: 4:25p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/search
 * clean up
 * 
 * *****************  Version 3  *****************
 * User: Znemazie     Date: 7/30/00    Time: 3:38p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/search
 * 
 * *****************  Version 4  *****************
 * User: Eroyal       Date: 7/27/00    Time: 7:38p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/search
 * added more functionality to custom reports and added functionality to
 * advanced search
 * 
 * *****************  Version 3  *****************
 * User: Eroyal       Date: 7/13/00    Time: 2:19p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/search
 * ya gotta have import statements to compile.
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 7/13/00    Time: 12:30p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/search
 * Repackaged.  Still very much broken though.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 7/13/00    Time: 11:33a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/search
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 6/30/00    Time: 10:52a
 * Created in $/Unfinished code/JKC Work/Project - Socrates/DevSource1.2/Framework.java/com/oculussoftware/api/busi/search
*/

/**
* ISearch defines the interface for much of AdvancedSearch and BasicReport.
* Since BasicReport is really an AdvancedSearch with user selected Display
* Attributes that is persisted to the database, much of the implementation/functionality
* is shared. 
*
* @author Egan Royal
*/
public interface ISearch extends java.io.Serializable
{  
  //TargetClass
  /**
  * This accessor method returns the Target Class of the Search.
  * @return The Target IXClass of the Search.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public IXClass getTargetClass() throws OculusException;
  
  /**
  * This accessor method returns the IIID of the Target Class of the Search.
  * @return The IIID of the Target IXClass of the Search.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public IIID getTargetClassIID() throws OculusException;
  
  /**
  * This mutator method sets the Target Class of the Search.
  * @param cls The Target Class.
  * @return this.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public ISearch setTargetClass(IXClass cls) throws OculusException;
  
  /**
  * This mutator method sets the Target Class of the Search.
  * @param classid The long ID of the Target Class.
  * @return this.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public ISearch setTargetClass(long classid) throws OculusException;
  
  /**
  * This mutator method sets the Target Class of the Search.
  * @param classid The IIID of the Target Class.
  * @return this.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public ISearch setTargetClass(IIID classiid) throws OculusException;

  //DisplayAttr
  /**
  * This mutator method adds a Display Attribute to the Search.  The Display 
  * Attributes map to the SELECT clause in the SQL statement.
  * @param assocs An array of longs that are the IDs of the IXAssocs in
  * the AssocChain.
  * @param attr The long value of the Attribute ID.
  * @return this.
  */
  public ISearch addDisplayAttr(long[] assocs, long attr);
  
  /**
  * This mutator method adds a Display Attribute to the Search.  The Display 
  * Attributes map to the SELECT clause in the SQL statement.
  * @param assocs An array of longs that are the IDs of the IXAssocs in
  * the AssocChain.
  * @param attr The long value of the Attribute ID.
  * @param alias A String value that will set the alias to be returned in the
  * Result Set for this Attribute.
  * @return this.
  */
  public ISearch addDisplayAttr(long[] assocs, long attr, String alias);
  
  /**
  * This method returns the number of Display Attributes for this Search object.
  * @return The number of Display Attributes.
  */
  public int getNumDisplayAttrs();
  
  /**
  * This accessor method returns an array of IQAttrRefs that are the 
  * Display Attributes for the Search object.
  * @return this.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public IQAttrRef[] getDisplayAttrs() throws OculusException;
  
  //Dates
  /**
  * This mutator method sets a From Date for the Search object.
  * @return this.
  * @param date The From Date.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public ISearch setFromDate(Timestamp date) throws OculusException;
  
  /**
  * This accessor method returns the From Date of the Search object.
  * @return The From Date of the Search object. null if it has not been set.
  */
  public Timestamp getFromDate();
  
  /**
  * This mutator method sets a To Date for the Search object.
  * @return this.
  * @param date The To Date.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public ISearch setToDate(Timestamp date) throws OculusException;
  
  /**
  * This accessor method returns the To Date of the Search object.
  * @return The To Date of the Search object. null if it has not been set.
  */
  public Timestamp getToDate();

  //Sort
  /**
  * This method adds a Sort Attribute to the Search object.  Sort Attributes
  * map to the ORDER BY clause of the SQL statement.
  * @param assocs An array of longs that are the IDs of the IXAssocs in
  * the AssocChain.
  * @param attr The long value of the Attribute ID.
  * @param dir The direction of the sort.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException 
  */ 
  public ISearch addSort(long[] assocs, long attr, SortDir dir) throws OculusException;
  
  //Filter
  /**
  * This method adds a Filter Attribute to the Search object.  Filter Attributes
  * map to the WHERE clause of the SQL statement.
  * @param assocs An array of longs that are the IDs of the IXAssocs in
  * the AssocChain.
  * @param attr The long value of the Attribute ID.
  * @param op The comparison or boolean operator.
  * @param value The String value for a righthand operand.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public ISearch addFilter(long[] assocs, long attr, IOperator op, String value) throws OculusException;
  
  /**
  * This method adds a Filter Attribute to the Search object.  Filter Attributes
  * map to the WHERE clause of the SQL statement.
  * @param assocs An array of longs that are the IDs of the IXAssocs in
  * the AssocChain.
  * @param attr The long value of the Attribute ID.
  * @param op The comparison or boolean operator.
  * @param value The String value for a righthand operand.
  * @param syn true use synonyms false do not.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public ISearch addFilter(long[] assocs, long attr, IOperator op, String value, boolean useSynonyms) throws OculusException;
  
  /**
  * This method adds a Filter Attribute to the Search object.  Filter Attributes
  * map to the WHERE clause of the SQL statement.
  * @param assocs An array of longs that are the IDs of the IXAssocs in
  * the AssocChain.
  * @param attr The long value of the Attribute ID.
  * @param op The comparison or boolean operator.
  * @param value The long value for a righthand operand.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public ISearch addFilter(long[] assocs, long attr, IOperator op, long value) throws OculusException;
  
  /**
  * This method adds a Filter Attribute to the Search object.  Filter Attributes
  * map to the WHERE clause of the SQL statement.
  * @param assocs An array of longs that are the IDs of the IXAssocs in
  * the AssocChain.
  * @param attr The long value of the Attribute ID.
  * @param op The comparison or boolean operator.
  * @param value The array of longs value for a righthand operand.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public ISearch addFilter(long[] assocs, long attr, IOperator op, long[] value) throws OculusException;
  
  /**
  * This method adds a Filter Attribute to the Search object.  Filter Attributes
  * map to the WHERE clause of the SQL statement.
  * @param assocs An array of longs that are the IDs of the IXAssocs in
  * the AssocChain.
  * @param attr The long value of the Attribute ID.
  * @param op The comparison or boolean operator.
  * @param value The Timestamp value for a righthand operand.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public ISearch addFilter(long[] assocs, long attr, IOperator op, Timestamp value) throws OculusException;

  /**
  * This method adds a Filter Attribute to the Search object.  Filter Attributes
  * map to the WHERE clause of the SQL statement.
  * @param assocs An array of longs that are the IDs of the IXAssocs in
  * the AssocChain.
  * @param attr The long value of the Attribute ID.
  * @param op The comparison or boolean operator.
  * @param value The String value for a righthand operand.
  * @param syn true use synonyms false do not.
  * @param isQuestion true iff the Attribute is directly involved with a Question Input
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public ISearch addFilter(long[] assocs, long attr, IOperator op, String value, boolean useSynonyms, boolean isQuestion) throws OculusException;

  /**
  * This method adds a Filter Attribute to the Search object.  Filter Attributes
  * map to the WHERE clause of the SQL statement.
  * @param assocs An array of longs that are the IDs of the IXAssocs in
  * the AssocChain.
  * @param attr The long value of the Attribute ID.
  * @param op The comparison or boolean operator.
  * @param value The long value for a righthand operand.
  * @param isQuestion true iff the Attribute is directly involved with a Question Input
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public ISearch addFilter(long[] assocs, long attr, IOperator op, long value, boolean isQuestion) throws OculusException;
  
  /**
  * This method adds a Filter Attribute to the Search object.  Filter Attributes
  * map to the WHERE clause of the SQL statement.
  * @param assocs An array of longs that are the IDs of the IXAssocs in
  * the AssocChain.
  * @param attr The long value of the Attribute ID.
  * @param op The comparison or boolean operator.
  * @param value The array of longs value for a righthand operand.
  * @param isQuestion true iff the Attribute is directly involved with a Question Input
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public ISearch addFilter(long[] assocs, long attr, IOperator op, long[] value, boolean isQuestion) throws OculusException;
  
  /**
  * This method adds a Filter Attribute to the Search object.  Filter Attributes
  * map to the WHERE clause of the SQL statement.
  * @param assocs An array of longs that are the IDs of the IXAssocs in
  * the AssocChain.
  * @param attr The long value of the Attribute ID.
  * @param op The comparison or boolean operator.
  * @param value The Timestamp value for a righthand operand.
  * @param isQuestion true iff the Attribute is directly involved with a Question Input
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public ISearch addFilter(long[] assocs, long attr, IOperator op, Timestamp value, boolean isQuestion) throws OculusException;

  /**
  * This method adds a Filter Attribute to the Search object.  Filter Attributes
  * map to the WHERE clause of the SQL statement.  Calling this method normally
  * means that there already exhists a Filter Expression.  The original Filter
  * Expression becomes the LHS, the BoolOper is the operator, and the rest of the
  * values passed to this method are used to build a second Filter Expression
  * that will be the RHS.
  * @param bo The BoolOper used to join the exhisting expression with the one passed in.
  * @param assocs An array of longs that are the IDs of the IXAssocs in
  * the AssocChain.
  * @param attr The long value of the Attribute ID.
  * @param op The comparison or boolean operator.
  * @param value The String value for a righthand operand.
  * @param syn true use synonyms false do not.
  * @param isQuestion true iff the Attribute is directly involved with a Question Input
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public ISearch addFilter(BoolOper bo, long[] assocs, long attr, IOperator op, String value, boolean syn, boolean isQuestion) throws OculusException;
  
  /**
  * This method adds a Filter Attribute to the Search object.  Filter Attributes
  * map to the WHERE clause of the SQL statement.  Calling this method normally
  * means that there already exhists a Filter Expression.  The original Filter
  * Expression becomes the LHS, the BoolOper is the operator, and the rest of the
  * values passed to this method are used to build a second Filter Expression
  * that will be the RHS.
  * @param bo The BoolOper used to join the exhisting expression with the one passed in.
  * @param assocs An array of longs that are the IDs of the IXAssocs in
  * the AssocChain.
  * @param attr The long value of the Attribute ID.
  * @param op The comparison or boolean operator.
  * @param value The long value for a righthand operand.
  * @param isQuestion true iff the Attribute is directly involved with a Question Input
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public ISearch addFilter(BoolOper bo, long[] assocs, long attr, IOperator op, long value, boolean isQuestion) throws OculusException;
  
  /**
  * This method adds a Filter Attribute to the Search object.  Filter Attributes
  * map to the WHERE clause of the SQL statement.  Calling this method normally
  * means that there already exhists a Filter Expression.  The original Filter
  * Expression becomes the LHS, the BoolOper is the operator, and the rest of the
  * values passed to this method are used to build a second Filter Expression
  * that will be the RHS.
  * @param bo The BoolOper used to join the exhisting expression with the one passed in.
  * @param assocs An array of longs that are the IDs of the IXAssocs in
  * the AssocChain.
  * @param attr The array of longs value of the Attribute ID.
  * @param op The comparison or boolean operator.
  * @param value The long value for a righthand operand.
  * @param isQuestion true iff the Attribute is directly involved with a Question Input
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public ISearch addFilter(BoolOper bo, long[] assocs, long attr, IOperator op, long[] value, boolean isQuestion) throws OculusException;
  
  /**
  * This method adds a Filter Attribute to the Search object.  Filter Attributes
  * map to the WHERE clause of the SQL statement.  Calling this method normally
  * means that there already exhists a Filter Expression.  The original Filter
  * Expression becomes the LHS, the BoolOper is the operator, and the rest of the
  * values passed to this method are used to build a second Filter Expression
  * that will be the RHS.
  * @param bo The BoolOper used to join the exhisting expression with the one passed in.
  * @param assocs An array of longs that are the IDs of the IXAssocs in
  * the AssocChain.
  * @param attr The long value of the Attribute ID.
  * @param op The comparison or boolean operator.
  * @param value The Timestamp value for a righthand operand.
  * @param isQuestion true iff the Attribute is directly involved with a Question Input
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public ISearch addFilter(BoolOper bo, long[] assocs, long attr, IOperator op, Timestamp value, boolean isQuestion) throws OculusException;

  /**
  * This method adds a Filter Attribute to the Search object.  Filter Attributes
  * map to the WHERE clause of the SQL statement.  Calling this method normally
  * means that there already exhists a Filter Expression.  The original Filter
  * Expression becomes the LHS, the BoolOper is the operator, and the rest of the
  * values passed to this method are used to build a second Filter Expression
  * that will be the RHS.
  * @param bo The BoolOper used to join the exhisting expression with the one passed in.
  * @param assocs An array of longs that are the IDs of the IXAssocs in
  * the AssocChain.
  * @param attr The long value of the Attribute ID.
  * @param op The comparison or boolean operator.
  * @param value The String value for a righthand operand.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public ISearch addFilter(BoolOper bo, long[] assocs, long attr, IOperator op, String value) throws OculusException;
  
  /**
  * This method adds a Filter Attribute to the Search object.  Filter Attributes
  * map to the WHERE clause of the SQL statement.  Calling this method normally
  * means that there already exhists a Filter Expression.  The original Filter
  * Expression becomes the LHS, the BoolOper is the operator, and the rest of the
  * values passed to this method are used to build a second Filter Expression
  * that will be the RHS.
  * @param bo The BoolOper used to join the exhisting expression with the one passed in.
  * @param assocs An array of longs that are the IDs of the IXAssocs in
  * the AssocChain.
  * @param attr The long value of the Attribute ID.
  * @param op The comparison or boolean operator.
  * @param value The long value for a righthand operand.
  * @param syn true use synonyms false do not.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public ISearch addFilter(BoolOper bo, long[] assocs, long attr, IOperator op, String value, boolean syn) throws OculusException;
  
  /**
  * This method adds a Filter Attribute to the Search object.  Filter Attributes
  * map to the WHERE clause of the SQL statement.  Calling this method normally
  * means that there already exhists a Filter Expression.  The original Filter
  * Expression becomes the LHS, the BoolOper is the operator, and the rest of the
  * values passed to this method are used to build a second Filter Expression
  * that will be the RHS.
  * @param bo The BoolOper used to join the exhisting expression with the one passed in.
  * @param assocs An array of longs that are the IDs of the IXAssocs in
  * the AssocChain.
  * @param attr The long value of the Attribute ID.
  * @param op The comparison or boolean operator.
  * @param value The long value for a righthand operand.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public ISearch addFilter(BoolOper bo, long[] assocs, long attr, IOperator op, long value) throws OculusException;
  
  /**
  * This method adds a Filter Attribute to the Search object.  Filter Attributes
  * map to the WHERE clause of the SQL statement.  Calling this method normally
  * means that there already exhists a Filter Expression.  The original Filter
  * Expression becomes the LHS, the BoolOper is the operator, and the rest of the
  * values passed to this method are used to build a second Filter Expression
  * that will be the RHS.
  * @param bo The BoolOper used to join the exhisting expression with the one passed in.
  * @param assocs An array of longs that are the IDs of the IXAssocs in
  * the AssocChain.
  * @param attr The long value of the Attribute ID.
  * @param op The comparison or boolean operator.
  * @param value The array of longs value for a righthand operand.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public ISearch addFilter(BoolOper bo, long[] assocs, long attr, IOperator op, long[] value) throws OculusException;
  
  /**
  * This method adds a Filter Attribute to the Search object.  Filter Attributes
  * map to the WHERE clause of the SQL statement.  Calling this method normally
  * means that there already exhists a Filter Expression.  The original Filter
  * Expression becomes the LHS, the BoolOper is the operator, and the rest of the
  * values passed to this method are used to build a second Filter Expression
  * that will be the RHS.
  * @param bo The BoolOper used to join the exhisting expression with the one passed in.
  * @param assocs An array of longs that are the IDs of the IXAssocs in
  * the AssocChain.
  * @param attr The long value of the Attribute ID.
  * @param op The comparison or boolean operator.
  * @param value The Timestamp value for a righthand operand.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public ISearch addFilter(BoolOper bo, long[] assocs, long attr, IOperator op, Timestamp value) throws OculusException;
   
  //Scope 
  /**
  * This method sets the Scope of the Search object.
  * @param classid The long value of the Scope Object's Class ID.
  * @param objectid The long value of the Scope Object ID.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public ISearch setScope(long classid, long objectid) throws OculusException;
  
  /**
  * This method sets the Scope of the Search object.
  * @param classid The IIID of the Scope Object's Class.
  * @param objectid The IIID of the Scope Object.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public ISearch setScope(IIID classiid, IIID objectiid) throws OculusException;
  
  /**
  * This method sets the Scope of the Search object.
  * @param bo The Scope object.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public ISearch setScope(IBusinessObject bo) throws OculusException;
  
  //State
  /**
  * This method adds a Filter for MarketinInputState.  
  * @param open true for Open false for Closed.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public ISearch addMarketInputState(boolean open) throws OculusException;
  
  /**
  * This method adds a State Filter.
  * @param idc the IDCONST value for the State.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public ISearch addState(IDCONST idc) throws OculusException;
  
  /**
  * This accessor method returns true iff the Market Input State Open
  * Filter is set.
  * @return true iff the Market Input State Open Filter is set.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public boolean isMarketInputOpen() throws OculusException;
  
  /**
  * This accessor method returns true iff the Market Input State Closed
  * Filter is set.
  * @return true iff the Market Input State Closed Filter is set.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public boolean isMarketInputClosed() throws OculusException;
  
  /**
  * This method returns an array of IIIDs that are the IDs of the
  * States that are set as State Filters.
  * @return An array of IIIDsthat are the IDs of the
  * States that are set as State Filters.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public IIID[] getStates() throws OculusException;
  
  /**
  * This method returns an array of IQAttrRefs that are the Filter Attributes
  * for the Search object.  This method is mainly used in order to
  * repopulate the AdvancedSearch or the BasicReport GUIs.
  * @return An array of IQAttrRefs that are the Filter Attributes
  * for the Search object.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public IQAttrRef[] getFilterAttrs() throws OculusException;
  
  /**
  * This method returns an array of CompOpers that are the comparison operators
  * for the Search object.  This method is mainly used in order to
  * repopulate the AdvancedSearch or the BasicReport GUIs.
  * @return An array of CompOpers that are the comparison operators
  * for the Search object.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public CompOper[] getCompOpers() throws OculusException;
  
  /**
  * This method returns an array of Strings that are the operands
  * for the Search object.  This method is mainly used in order to
  * repopulate the AdvancedSearch or the BasicReport GUIs.
  * @return An array of Strings that are the operands
  * for the Search object.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public String[] getOperands() throws OculusException;
  
  /**
  * This method returns an array of BoolOpers that are the boolean operators
  * for the Search object.  This method is mainly used in order to
  * repopulate the AdvancedSearch or the BasicReport GUIs.
  * @return An array of BoolOpers that are the comparison operators
  * for the Search object.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public BoolOper[] getBoolOpers() throws OculusException;
  
  /**
  * This method returns an array of booleans that are the values
  * for whether or not to use synonyms for the FilterExpression on 
  * the Search object.  This method is mainly used in order to
  * repopulate the AdvancedSearch or the BasicReport GUIs.
  * @return An array of booleans.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public boolean[] getSyns() throws OculusException;
  
  /**
  * This method returns an array of booleans that are the values
  * for whether or not the Filter is a Question(Input) on 
  * the Search object.  This method is mainly used in order to
  * repopulate the AdvancedSearch or the BasicReport GUIs.
  * @return An array of booleans.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public boolean[] getQuests() throws OculusException; 
  
  //need to change this to IQSortItem
  /**
  * This method returns an array of IQSortItemKeys that are 
  * the Sort Attributes.  This method is mainly used in order to
  * repopulate the AdvancedSearch or the BasicReport GUIs.
  * @return An array of booleans.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public com.oculussoftware.api.repi.xml.IQSortItemKey[] getSortItemKeys() throws OculusException;
  
  /** 
  * Used for edit.  This method clears all of the Lists stored in the object
  * and in the XML objects. 
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public ISearch clear() throws OculusException;
  
  /**
  * This method returns the IQuery object for the Search.
  * @return the IQuery object for the Search.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public IQuery getQuery() throws OculusException;
  
  /**
  * This method executes the IQuery object and returns an IDataSet.
  * @return An IDataSet.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public IDataSet retrieve() throws OculusException;
  
  /**
  * This method returns the SQL statement for the Search object.
  * @return The SQL statement.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public String getSQL() throws OculusException;
      
}

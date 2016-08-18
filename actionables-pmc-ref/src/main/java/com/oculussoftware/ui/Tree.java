package com.oculussoftware.ui;

import java.io.*;
import java.util.*;
import java.net.*;
import java.sql.*;

import com.oculussoftware.util.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.common.org.IUser;

/**
* Filename:    Tree.java
* Date:        9-2-1999
* Description: .
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Saleem Shafi
* @version 1.1
*/
public class Tree implements Serializable
{
	/*
	* Change Activity
	*
	* Issue number  	Programmer    	Date      	Description
  * 00105           Saleem Shafi    5/16/00     Stopped adding duplicate nodes by checking to see if it already exists first. 
  * ---							Saleem Shafi		5/24/00			Added getObjectContext() method for TreeCache
	* BUG00642,648		Saleem Shafi		6/1/00			Changed removeNode to remove all children nodes as well.
	* BUG00886				Saleem Shafi		6/6/00			Added unload() method.
	* BUG00953				Saleem Shafi		6/8/00			The removeNode() method was bad.  I made it iterative instead of recursive.
	*
	*/
//	private Map _nodes;
//  private Iterator it;
    private ListMap _nodes;
    private Iterator e;
    private IObjectContext _context = null;
    private SettingsMgr _settingsmgr;
  
    private boolean _loaded = false;
    
    
    public void setActive(boolean set) { _settingsmgr.setActive(set); }
    public void setComplete(boolean set) { _settingsmgr.setComplete(set); }
    public void setNotComplete(boolean set) { _settingsmgr.setNotComplete(set); }
    public void setReview(boolean set) { _settingsmgr.setReview(set); }
    public void setPotFeat(boolean set) { _settingsmgr.setPotFeat(set); }
    public void setMyCept(boolean set) { _settingsmgr.setMyCept(set); }
    public void setBaseline(boolean set) { _settingsmgr.setBaseline(set); }
    public void setArchived(boolean set) { _settingsmgr.setArchived(set); }
    public void setInputs(boolean set) { _settingsmgr.setInputs(set); }
    public void setForms(boolean set) { _settingsmgr.setForms(set); }
    public void setInputNums(int set) { _settingsmgr.setInputNums(set); }
    public void setPS(boolean set) { _settingsmgr.setPS(set); }
    public void setOpen(boolean set) { _settingsmgr.setOpen(set); }
    public void setClosed(boolean set) { _settingsmgr.setClosed(set); }
    public void setBefore(Timestamp set) { _settingsmgr.setBefore(set); }
    public void setAfter(Timestamp set) { _settingsmgr.setAfter(set); }
  
    public boolean getActive() { return _settingsmgr.getActive(); }
    public boolean getComplete() { return _settingsmgr.getComplete(); }
    public boolean getNotComplete() { return _settingsmgr.getNotComplete(); }
    public boolean getReview() { return _settingsmgr.getReview(); }
    public boolean getPotFeat() { return _settingsmgr.getPotFeat(); }
    public boolean getMyCept() { return _settingsmgr.getMyCept(); }
    public boolean getBaseline() { return _settingsmgr.getBaseline(); }
    public boolean getArchived() { return _settingsmgr.getArchived(); }
    public boolean getInputs() { return _settingsmgr.getInputs(); }
    public int getInputNums() { return _settingsmgr.getInputNums(); }
    public boolean getPS() { return _settingsmgr.getPS(); }
    public boolean getForms() { return _settingsmgr.getForms(); }
    public boolean getOpen() { return _settingsmgr.getOpen(); }
    public boolean getClosed() { return _settingsmgr.getClosed(); }
    public Timestamp getBefore() { return _settingsmgr.getBefore(); }
    public Timestamp getAfter() { return _settingsmgr.getAfter(); }
    public long getPriority() { return _settingsmgr.getPriority(); }
    public long getState() { return _settingsmgr.getState(); }
    public String getFeatureType() { return _settingsmgr.getFeatureType(); }

  public Tree(IObjectContext context, SettingsMgr settingsmgr)
    throws Exception
  {
    _context = context;
    _settingsmgr = settingsmgr;
    reload();
  }

  public SettingsMgr getSettingsMgr()
  {
    return _settingsmgr;
  }

  public void reload()
    throws Exception
  {
		_nodes = new HashVector();
    loadChildren(_nodes, "-1");
    _loaded = true;
    reset();
  }


  public void addNode(Node newNode)
    throws Exception
  {
    if (!_nodes.containsKey(newNode.getFullNodeID()))
    {
      _nodes.put(newNode.getFullNodeID(),newNode);
      TreeCache.getInstance().addNode(newNode);
    }
  }
  
  public Node removeNode(Node thisNode)
    throws ORIOException
  {
    int index = _nodes.indexOf(thisNode);
    if (index == -1)
      throw new ORIOException(thisNode+" was not found in the tree and could not be removed.");
    Node node = (Node)_nodes.remove(index);

		boolean gotOne = true;
		while (gotOne)
		{
	  	gotOne = false;
		  reset();
		  while (hasMoreNodes() && !gotOne)
	    {
	      Node thisNode2 = nextNode();
	      if (thisNode2.getParentNodeID().startsWith(node.getFullNodeID()))
		  {
						_nodes.remove(_nodes.indexOf(thisNode2));
						gotOne = true;
		  }
	    }
		}
				
		return node;
  }
  
  public void saveSettings()
  {
    _settingsmgr.saveSettings();
  }

  public void loadSettings()
  {
    _settingsmgr.loadSettings();
  }
  
  public void loadChildren(String strID)
    throws Exception
  {
    loadChildren(_nodes,strID);
  }

  public boolean notLoaded() { return !_loaded; }
	public void unload() { _loaded = false; }
  
  public void loadChildren(ListMap nodes, String strID)
    throws Exception
  {
		Node parentNode = this.find(strID);
    ListMap children = TreeCache.getInstance().getChildren(this,parentNode);
    Iterator i = children.iterator();
    while (i.hasNext())
    {
      Node thisNode = (Node)i.next();
      if (!nodes.containsKey(thisNode.getFullNodeID()))
	  	{
				TreeCache.getInstance().testForChildren(this,thisNode);
        nodes.put(thisNode.getFullNodeID(),thisNode);
	  	}
//      _nodes.put(thisNode.getID(),thisNode);
//      if (thisNode.getChildren() != null)
//        loadChildren(thisNode.getID());
    }
  }

  public void reset()
  {
//    it = _nodes.keySet().iterator();
    e = _nodes.iterator();
  }

  public boolean hasMoreNodes()
  {
//    return it.hasNext();
    return e.hasNext();
  }
  
  public Node find(String nodeID)
  {
    reset();
    while (hasMoreNodes())
    {
      Node thisNode = nextNode();
      if (thisNode.getFullNodeID().equals(nodeID))
        return thisNode;
    }
    return null;
  }
  
  public Node find(String nodeID, String parentID)
  {
    reset();
    while (hasMoreNodes())
    {
      Node thisNode = nextNode();
      if (thisNode.getID().equals(nodeID) && thisNode.getParentNodeID().startsWith(parentID))
        return thisNode;
    }
    return null;
  }
  
  public Node nextNode()
  {
//    return (Node)_nodes.get(it.next());
    return (Node)e.next();
  }
  
  public void loadNode(String fullnodeID)
    throws Exception
  {
    Node thisNode = (Node)_nodes.get(fullnodeID);
		if (thisNode != null && !thisNode.isLoaded())
      loadChildren(thisNode.getFullNodeID());
  }
  
  public IObjectContext getObjectContext()
  {
  	return _context;
  }


  public String toString()
  {
		String text = "";
    reset();
    while (hasMoreNodes())
    {
      Node thisNode = nextNode();
			text += thisNode.toString() + "\n";
    }
    return text;
  }

}
package com.oculussoftware.service.mail;

import java.io.*;
import java.sql.*;
import java.net.*;
import java.util.*;

import com.oculussoftware.api.service.mail.*;

/**
* Filename:    SMTPClient.java
* Date:        8-11-1999
* Description: SMTPClient implements the IntSMTPClient interface.
* The class creates a simple SMTP client that is capable of sending
* text messages via the SMTP protocol.(RFC 821)
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.1
*/
public class SMTPClient implements ISMTPClient
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  *
  */
  
	//private vars
	private Socket jdtS;
	private DataOutputStream jdtDOS;
	private static final String strCrLf= new String("\r\n");
	private String strMailServer;
	private int intPort;
	private int debug = 0;
	
	/**
	*
	*/
	public SMTPClient(String strMS, int intP)
	{
		strMailServer = strMS; intPort = intP; debug = 1;
	}//end constructor
	/**
	* connect with user defined host and port
	*/
	public void connect(String strHost, int intPort) throws IOException, UnknownHostException
	{
		jdtS = new Socket(strHost, intPort);
		jdtDOS = new DataOutputStream(jdtS.getOutputStream());
	}//end connect
  /**
  *
  */
  public ISMTPMessage createSMTPMessage()
  {
	return new SMTPMessage();
  }  
	/**
	* the SMTP data message ("DATA" + crlf)
	*/
	public void data() throws IOException
	{
		String strData = new String("DATA" + strCrLf);
		byte[] bytData = getBytes(strData);
		jdtDOS.write(bytData, 0, bytData.length);
		if(debug == 1)
		{
			System.out.println("Client:  " + strData);
			readPrint();
		}//end if
		jdtDOS.flush();
	}//end data
	/**
	* the SMTP end of message marker (crlf.crlf)
	*/
	public void endOfMessage() throws IOException
	{
		String strEOM = strCrLf + "." + strCrLf;
		byte[] bytEOM = getBytes(strEOM);
		jdtDOS.write(bytEOM, 0, bytEOM.length);
		if(debug == 1)
		{
			System.out.println("Client:  " + strEOM);
			readPrint();
		}//end if
		jdtDOS.flush();
	}//end endOfMessage
	/**
	* getBytes takes a string and returns a byte array 
	*/
	private byte[] getBytes(String str)
	{
		byte[] bytes = new byte[str.length()];
		for(int i = 0; i < bytes.length; ++i)
			bytes[i] = (byte)str.charAt(i);
		return bytes;
	}//end getBytes
	/**
	* the SMTP helo message ("HELO " + localhostname)
	*/
	public void helo() throws IOException
	{
		String strHelo = new String("HELO ");
		String strHost = null;
		try
		{
			InetAddress jdtIA = InetAddress.getLocalHost();
			strHost = jdtIA.getHostName();
		}//end try
		catch(Exception e)
		{
			strHost = "";
		}//end catch
		strHelo =  strHelo + strHost + strCrLf;	
		byte[] bytHelo = getBytes(strHelo);
		jdtDOS.write(bytHelo, 0, bytHelo.length);	
		if(debug == 1)
		{
			System.out.println("Client:  " + strHelo);
			readPrint();
		}//end if
		jdtDOS.flush();
	}//end helo
	/**
	* the SMTP mail form message ("MAIL FROM: <from address>")
	*/
	public void mailFrom(String strFrom) throws IOException
	{
		String strMailFrom = new String("MAIL FROM: <");
		strMailFrom = strMailFrom + strFrom + ">" + strCrLf;
		byte[] bytMailFrom = getBytes(strMailFrom);
		jdtDOS.write(bytMailFrom, 0, bytMailFrom.length);
		if(debug == 1)
		{
			System.out.println("Client:  " + strMailFrom);
			readPrint();
		}//end if
		jdtDOS.flush();
	}//end mailFrom
	/**
	* parse takes a vector and returns a comma separated list as a String
	*/
	private String parse(Vector jdtV)
	{
		String strTemp = "";
		if(jdtV.size() > 0)
		{
			for(int i = 0; i < jdtV.size(); ++i)
			{
				if(i + 1 == jdtV.size())
					strTemp = strTemp + jdtV.elementAt(i).toString();
				else
					strTemp = strTemp + jdtV.elementAt(i).toString() + ", ";
			}//end for
		}//end if
		return strTemp;
	}//end parse
	/**
	* the SMTP quit message ("QUIT" + crlf)
	*/
	public void quit() throws IOException
	{
		String strQuit = new String("QUIT" + strCrLf);
		byte[] bytQuit = getBytes(strQuit);
		jdtDOS.write(bytQuit, 0, bytQuit.length);
		if(debug == 1)
		{
			System.out.println("Client:  " + strQuit);
			readPrint();
		}//end if
		jdtDOS.flush();
		//close the stream
		jdtDOS.close();
		//close the socket
		jdtS.close();
	}
	/**
	* the SMTP RCPT TO: message ("RCPT TO: <to address>")
	*/
	public void rcptTo(Vector jdtV) throws IOException
	{
		for(int i = 0; i < jdtV.size(); ++i)
		{
			String strRcptTo = new String("RCPT TO: <");
			strRcptTo = strRcptTo + jdtV.elementAt(i).toString() + ">" + strCrLf;
			byte[] bytRcptTo = getBytes(strRcptTo);
			jdtDOS.write(bytRcptTo, 0, bytRcptTo.length);
			if(debug == 1)
			{
				System.out.println("Client:  " + strRcptTo);
				readPrint();
			}//end if
			jdtDOS.flush();
		}//end for
	}//end rcptTo
	/**
	*
	*/
	private void readPrint() throws IOException
	{
		if(debug == 1)
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(jdtS.getInputStream()));
			String str = in.readLine();
			System.out.println("Server Response:  " + str);
		}//end if
	}//end readPrint
	/**
	* sends the entire message from connect to quit
	*/
	public void sendCompleteMessage(ISMTPMessage udtMsg) throws IOException
	{
		connect(strMailServer, intPort);
		
		helo();
		mailFrom(udtMsg.getFrom());
		rcptTo(udtMsg.getTo());
		rcptTo(udtMsg.getCc());
		data();
		
		String strDate = "Date: " + udtMsg.getDate() + strCrLf;
		String strFrom = "From: " + udtMsg.getFrom() + strCrLf;
		String strTo = "To: " + parse(udtMsg.getTo()) + strCrLf;
		String strCc = "Cc: " + parse(udtMsg.getCc()) + strCrLf;
		String strContType = "Content-Type: " + udtMsg.getContentType() + strCrLf;
		String strSubject = "Subject: " + udtMsg.getSubject() + strCrLf + strCrLf;
		String strBody = udtMsg.getBody();
		String strMessage = new String(strDate + strFrom + strTo + strCc + strContType + strSubject + strBody);
		byte[] bytMsg = getBytes(strMessage);
		jdtDOS.write(bytMsg, 0, bytMsg.length);
		if(debug == 1)
		{
			System.out.println("Client:  " + strMessage);
			readPrint();
		}//end if
		jdtDOS.flush();
		
		//
		endOfMessage();
		quit(); 
	}//end sendCompleteMessage
	/**
	* sends only the message part
	*/
	public void sendMessage(ISMTPMessage udtMsg) throws IOException
	{
		String strDate = "Date: " + udtMsg.getDate() + strCrLf;
		String strFrom = "From: " + udtMsg.getFrom() + strCrLf;
		String strTo = "To: " + parse(udtMsg.getTo()) + strCrLf;
		String strCc = "Cc: " + parse(udtMsg.getCc()) + strCrLf;
		String strContType = "Content-Type: " + udtMsg.getContentType() + strCrLf;		
		String strSubject = "Subject: " + udtMsg.getSubject() + strCrLf + strCrLf;
		String strBody = udtMsg.getBody();
		String strMessage = new String(strDate + strFrom + strTo + strCc + strContType + strSubject + strBody);
		byte[] bytMsg = getBytes(strMessage);
		jdtDOS.write(bytMsg, 0, bytMsg.length);
		if(debug == 1)
		{
			System.out.println("Client:  " + strMessage);
			readPrint();
		}//end if
		jdtDOS.flush();
	}//end sendMessage
}//end SMTPClient

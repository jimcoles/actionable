package com.oculussoftware.ui;

// Copyright (C) 1998 by Jason Hunter <jhunter@acm.org>.  All rights reserved.
// Use of this class is limited.  Please see the LICENSE for more information.

import java.io.*;
import java.util.*;
import javax.servlet.*;

// A class to hold information about an uploaded file.
//
class UploadedFile {

  private String dir;
  private String filename;
  private String type;

  UploadedFile(String dir, String filename, String type) {
	this.dir = dir;
	this.filename = filename;
	this.type = type;
  }  
  public String getContentType() {
	return type;
  }  
  public File getFile() {
	if (dir == null || filename == null) {
	  return null;
	}
	else {
	  return new File(dir + File.separator + filename);
	}
  }  
  public String getFilesystemName() {
	return filename;
  }  
}

/*
 *
 * Copyright 2001 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Sun Microsystems, Inc.  
 * Use is subject to license terms.
 * 
 */

package helloworld.wrappers;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;
import java.io.*;

public class CharResponseWrapper extends HttpServletResponseWrapper {
    private CharArrayWriter output;

    public class MyOutputStream extends ServletOutputStream {

    	 ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		public void write(int b) throws IOException {
			bos.write(b);
			
		}
    	
    }
    
    private MyOutputStream mos = null;
    
    public String toString() {
    	if (mos == null) {
    		return output.toString();
    	} else {
    		return new String(mos.bos.toByteArray());
    	}
    }
      
    public CharResponseWrapper(HttpServletResponse response) {
        super(response);
        
        output = new CharArrayWriter();
    }

    public PrintWriter getWriter() {
        return new PrintWriter(output);
    }
    
    public ServletOutputStream getOutputStream() {
    	mos = new MyOutputStream();
    	return mos;
    }
}

package bases;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class Comprimir {

	String OS = null;
	public Comprimir(){
		OS = System.getProperty("os.name");	
	}
	
	public String getOS(){
		return this.OS;
	}
	
	public void gzipFileWindows(String from, String to) throws IOException {
	    
		FileInputStream in = new FileInputStream(from);
	    GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(to));
	    byte[] buffer = new byte[4096];
	    int bytesRead;
	    while ((bytesRead = in.read(buffer)) != -1)
	      out.write(buffer, 0, bytesRead);
	    in.close();
	    out.close();
	  }
	
	public void gzipFileLinux(String command){
		
		Process p;
		
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
}

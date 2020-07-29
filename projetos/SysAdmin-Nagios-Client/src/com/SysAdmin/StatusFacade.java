package com.SysAdmin;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.util.Log;

/**
 * Downloads the current status.
 * 
 * @author Lukas Bernreiter
 * @version 0.8, 17/04/2012
 * @since 0.8
 */
public abstract class StatusFacade 
{
	public static void downloadStatus(String _url) throws Exception
	{
		InputStream inputStream = null;
		OutputStream outputStream = null;
		int current = 0;				
		
		// Check if the connection is OK
		try {
			URL url = new URL(_url);
			URLConnection connection = (URLConnection) url.openConnection();
			inputStream = new BufferedInputStream(connection.getInputStream(), 1024 * 5);
			File file = new File(FilePathFacade.GetRootDirectory());
			
			file.mkdirs(); 			
			
			outputStream = new FileOutputStream(FilePathFacade.GetTempFile());

			byte buff[] = new byte[1024 * 5];

			while((current = inputStream.read(buff)) != -1)
				outputStream.write(buff, 0, current);
			
		} catch (Exception _e) {
			Log.e(AppFacade.GetTag(), "download failure - "+ _e.getMessage());
			throw new Exception(_e.getMessage());
		}
		finally{
			try {
				if(null != inputStream)
				{
					inputStream.close();
				}				
			} catch (IOException _e) {
				Log.e(AppFacade.GetTag(), "write or close failure"+ _e.getMessage());
			}
		}
	}
}

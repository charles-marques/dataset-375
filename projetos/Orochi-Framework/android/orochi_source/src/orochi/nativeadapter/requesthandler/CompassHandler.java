/*
Copyright (c) 2012 Ronald Tsang, ronaldtsang@orochis-den.com

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package orochi.nativeadapter.requesthandler;

import java.util.HashMap;

import orochi.nativeadapter.NativeService;
import orochi.nativeadapter.RequestHandler;
import orochi.util.CompassListener;
import orochi.util.json.JSONException;
import orochi.util.json.JSONObject;

public class CompassHandler extends RequestHandler {

	private CompassListener compListener = null;
	
	@Override
	public void setNativeService(NativeService nativeService){
		super.setNativeService(nativeService);
		compListener = new CompassListener(nativeService);
	}
	
	@Override
	public void handle(HashMap<String,String> getParms, JSONObject jsonObject) throws JSONException {		
		if(getParms.containsKey("compassHandler")){
			String requestValue = getParms.get("compassHandler");
			HashMap<String, Object> jsonHM = new HashMap<String, Object>();
			
			if (requestValue.equals("getHeading")) {
				float heading = compListener.getHeading();
				if(heading>0){					
					jsonHM.put("heading", heading);
				}
			}			

			jsonObject.put("compassHandler", jsonHM);
		}
		
		nextHandle(getParms, jsonObject);
	}
	
}

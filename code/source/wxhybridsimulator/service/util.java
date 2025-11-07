package wxhybridsimulator.service;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
// --- <<IS-END-IMPORTS>> ---

public final class util

{
	// ---( internal utility methods )---

	final static util _instance = new util();

	static util _newInstance() { return new util(); }

	static util _cast(Object o) { return (util)o; }

	// ---( server methods )---




	public static final void validateDocument (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(validateDocument)>> ---
		// @sigtype java 3.5
		// [i] record:0:required document
		// [i] field:1:required reqFailedFields
		// [i] field:1:required reqWarningFields
		// [o] field:0:required status {"SUCCESS","WARNING","FAILURE"}
		// [o] field:0:required message
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		String[]	reqFailedFields = IDataUtil.getStringArray( pipelineCursor, "reqFailedFields" );
		String[]	reqWarningFields = IDataUtil.getStringArray( pipelineCursor, "reqWarningFields" );
		
		String lStatus="Success";
		String lMessage="";		
		
		// document
		IData	document = IDataUtil.getIData( pipelineCursor, "document" );
		if ( document != null)
		{
			IDataCursor docCursor = document.getCursor();
			// Iterate through all fields
		
			for(int lw=0; lw < reqWarningFields.length; lw++){
				Object lValue = IDataUtil.get( docCursor, reqWarningFields[lw] );
				if(lValue == null || lValue.equals("")){
					lMessage = lMessage+"Warning: "+reqWarningFields[lw]+" is empty,";
					lStatus = "Warning";		
				}//if
			}//for
			for(int lf=0; lf < reqFailedFields.length; lf++){
				Object lValue = IDataUtil.get( docCursor, reqFailedFields[lf] );
				if(lValue == null || lValue.equals("")){
					lMessage = lMessage+"Failure: "+reqFailedFields[lf]+" is empty,";
					lStatus = "Failure";		
				}//if
			}//for
			docCursor.destroy();
		}//if
		
		// pipeline
		IDataUtil.put( pipelineCursor, "status", lStatus );
		IDataUtil.put( pipelineCursor, "message", lMessage );
		pipelineCursor.destroy();
		// --- <<IS-END>> ---

                
	}
}


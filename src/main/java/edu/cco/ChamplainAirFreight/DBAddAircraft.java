package edu.cco.ChamplainAirFreight;
/**
 * 
 * @author Matt Ridgway
 * @Date: Nov 11, 2020
 * @Description: DBAddAircraft - class to interact with the database and the GUI page to insert a new Aircraft
 * using the stored procedure Add_Aircraft.
 */

//Imports:
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBAddAircraft extends DBConnection {
	//Variables
		public CallableStatement callable = null;
		public int model;
		
/**
* Default Constructor
* Matt Ridgway 
* 11/11/2020
*/
public DBAddAircraft(int modelNumber) {
	try {
		this.model=modelNumber;		
		
		String storedP = "{call CAFDB.dbo.Add_Aircraft}"; 
		callable = connection.prepareCall(storedP);
		insertSQL(model);			
	}
	catch (SQLException ex) {
		Logger.getLogger(DBAddClient.class.getName()).log(Level.SEVERE, null, ex);
	} catch(Exception e) {
		e.printStackTrace();
		System.out.println("Problem adding New Pilot"); 
	}
	
	
}//end Constructor
/**
* insertSQL Method 
* Matt Ridgway 
* 11/11/2020
* Database structure:
* ACModelID int
*  
*/
public void insertSQL(int modelID) {
	try {
		String sql = "{call CAFDB.dbo.Add_AirCraft(?)}";
		callable=connection.prepareCall(sql);
		callable.setInt(1,  modelID);	
		
		//Execute Stored Procedure
		callable.executeQuery();
		
	} catch (SQLException ex) {
        System.out.println("Insert AirCraft Problem !");
    }
	
	
}
}

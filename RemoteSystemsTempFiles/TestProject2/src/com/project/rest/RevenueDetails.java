package com.project.rest;





import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;





import javax.swing.JOptionPane;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import java.sql.*;
@Path("/r4")

public class RevenueDetails {
	
	
	
	Connection conn;
    ResultSet rslt;
    String data ="successfully inserted11";
    Statement stmt;
    
    @Path("/r5")
    @POST
	//@Produces(MediaType.TEXT_HTML)
	
	public void Revenue(@FormParam("npi") Integer Npi,@FormParam("rd") Integer Rd,@FormParam("rdb") Integer Rdb,
			@FormParam("rev1") Integer Rev1,@FormParam("rev2") Integer Rev2) throws SQLException {
    
    	
    	
    	
    	//String lLoginQuery1 ="Insert into RevenueDetails values ('" + Npi + "','" +Rd + "','" +Rdb+ "','" + Rev1 + "','" + Rev2 + "')";
    	String sql="INSERT INTO RevenueDetails (NpiRevenue,RDBudget,RDBudgetActual,revenue2016,revenue2017) VALUES (?,?,?,?,?)";
		 
			  try{
				  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		             //String url="jdbc:sqlserver://199.63.10.69\\SQLSER2008R2;databaseName=TestDB"; 
		             String url="jdbc:sqlserver://IE1FVWISOP016\\SQLSER2008R2;databaseName=TestDB";
		            conn=DriverManager.getConnection(url,"sa","Password@1");
		        
     
		          //here sonoo is database name, root is username and password  
		    		  
		    		//Statement stmt=(Statement) conn.createStatement();  
		       		//stmt.executeQuery(lLoginQuery1);  
		       		//stmt.executeQuery(lLoginQuery);
		    		//rslt.close();
		    		 
		    		//System.out.println("successfully inserted");
		    		
				            PreparedStatement pst=conn.prepareStatement(sql);
			                
				            pst.setInt(1,Npi);
				            pst.setInt(2, Rd);
				               pst.setInt(3,Rdb);
				           pst.setInt(4, Rev1);
				           pst.setInt(5, Rev2);
				           
				          
			        //put the rest of the code
			        int n1=pst.executeUpdate();
			        if(n1>0)
			        {
			        JOptionPane.showMessageDialog(null,"Inserted Successfully!!");
			        }
			        else
			        {
			        	 JOptionPane.showMessageDialog(null,"Not Inserted!");
			        }
		  
    		}
		catch(Exception e){ System.out.println(e);}  
		//return data;
    } 
}   
  

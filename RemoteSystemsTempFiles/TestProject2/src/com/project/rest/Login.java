package com.project.rest;
import java.sql.*;

import javax.ws.rs.FormParam;
//import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mysql.jdbc.PreparedStatement;

//import java.sql.*;
@Path("/db3")
public class Login {

	
	Connection conn;
    ResultSet rslt;
    String Vuser ="Your valid User";
    String Iuser="Invalid username or password";
    Statement stmt;
    PreparedStatement pst;
    
	
    
    @Path("/login")
    
    @POST
    @Produces(MediaType.TEXT_HTML)
    
    
    public String LoginUserValidataion(@FormParam("user1") String UserName,
    		@FormParam("pass1") String PassWord ) throws SQLException
    {
    
 
       
       try{
    	   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
          // String url="jdbc:sqlserver://199.63.10.69\\SQLSER2008R2;databaseName=TestDB"; 
           String url="jdbc:sqlserver://IE1FVWISOP016\\SQLSER2008R2;databaseName=TestDB";
          conn=DriverManager.getConnection(url,"sa","Password@1");
          String sql ="select * from TestTable where username=? and password=?";
         java.sql.PreparedStatement pst= conn.prepareStatement(sql);
    
          
         pst.setString(1,UserName);
       pst.setString(2,PassWord);
       
   
       
          ResultSet rs1=pst.executeQuery();
          if(rs1.next())
          {
              
        	 return Vuser;
        	
          }
          else
          {
        	 // System.out.println(UserName+"  "+PassWord);
            
        	return Iuser;
          }
 
       }catch(Exception e){
    	   System.out.println(e);
       }
    
      return Iuser;  

       
}
}

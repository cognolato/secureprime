	package it.Dao;
	
	import java.sql.*;
	  
	public class UserDAO {      
	     public static boolean login(String user, String password) {

	    	 //Questa classe va implementata con la chiamata ad un database 
	    	 // i dati di utenze e password (crittografata) vanno memorizzati in una tabella
	    	 if (user.equals("admin") && password.equals("test")) 
	            {
	    		    return true;
	            }
	            else {
	                return false;
	            }

	    }
	}
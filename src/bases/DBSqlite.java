package bases;

import java.sql.*;
import java.util.ArrayList;

public class DBSqlite {

	private Connection co = null;
	private String dbGlobal;
	
	
	
	// Aqui el constructor. Cada que creamos un objeto se inicia la coneccion.
	
	public  DBSqlite(String db){
		this.dbGlobal = db;
		try {
		      Class.forName("org.sqlite.JDBC");
		      co = DriverManager.getConnection("jdbc:sqlite://C:\\Users\\Sebastian\\Desktop\\Maximundo\\" + db);
		      //System.out.println("jdbc:" + dbType + "://" + path + db);
		      co.setAutoCommit(false);
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      throw new RuntimeException("Error al abrir SQlite DB");
		    }
		    System.out.println("Conectado a Sqlite " + db);	
	}
	
	public Connection getConnection(){
		return this.co;
	}
	
	
	
public void Insert(ArrayList<Queryable> querys){     //Funcion insertar query
		
		try {
			Statement stm = co.createStatement();
			
			
			for (Queryable query : querys) {
			
				
				Object temporal = query.toQuery("sqlite", co); // Se lee cada query aqui
				
				if(temporal instanceof String)				// Primera implementacion 
				{
					stm.executeUpdate((String)temporal);	// Se insertan cada query aqui
				}
				
				if(temporal instanceof PreparedStatement)   //Segunda implementacion con PreparedStatement
				{
					((PreparedStatement)(temporal)).executeUpdate(); // Se insertan en este caso tambien
				}	
				
			}
				
			
			stm.close();
		
			//co.close();
			
			
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(querys.get(0).getTabla() + " actualizados " + this.dbGlobal);
	}
	
	

	public void vacuum(String db){
		
		
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite://C:\\Users\\Sebastian\\Desktop\\Maximundo\\"+ db);
	      //System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      
	      stmt.execute("PRAGMA auto_vacuum = FULL");
	      stmt.execute("VACUUM");
	      
	      
	      //stmt.executeUpdate(sql);
	      stmt.close();
	      //c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
		
	}
	
	public void deleteDB(){
		
		String sql;
		Statement stmt =null;
		try {
			stmt = co.createStatement();
			
			sql = "delete from sqlite_sequence WHERE name = 'item'";	
			stmt.executeUpdate(sql);
			sql = "delete from item";
			stmt.executeUpdate(sql);
			sql = "delete from clie";
			stmt.executeUpdate(sql);
			sql = "delete from vendedores";
			stmt.executeUpdate(sql);
			sql = "delete from labo";
			stmt.executeUpdate(sql);	
			sql = "delete from recod";
			stmt.executeUpdate(sql);	
			
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void deleteDB1(){
		
		String sql;
		Statement stmt =null;
		try {
			stmt = co.createStatement();
			
			sql = "DELETE FROM producto_imagen";	
			stmt.executeUpdate(sql);
				
			
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void commitAndClose(){
		try {
			co.commit();
			co.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
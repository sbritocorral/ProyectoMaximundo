package bases;

import java.sql.*;
import java.util.ArrayList;

import com.mysql.jdbc.EscapeTokenizer;

public class DBMysql {

	private Connection co = null;
	private String dbGlobal;
	
	
	
	
	// Aqui el constructor. Cada que creamos un objeto se inicia la coneccion.
	
	public  DBMysql(String db){
		this.dbGlobal = db;
		try {
		      Class.forName("com.mysql.jdbc.Driver");
		      co = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ db,"root", "SebasBrito01");
		      //System.out.println("jdbc:" + dbType + "://" + path + db);
		      co.setAutoCommit(false);
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      throw new RuntimeException("Error al abrir SQlite DB");
		    }
		    System.out.println("Conectado a MySql " + db);    
		
	}
	
	public Connection getConnection(){
		return this.co;
	}
	
	
	
public void Insert(ArrayList<Queryable> querys){     //Funcion insertar query
		
	try {
			Statement stm = co.createStatement();
			
			int x = 0;
			
			for (Queryable query : querys) {
			
				
				if(x == 0)
				{
					PreparedStatement prepare = (PreparedStatement) query.beforeInsert("mysql", co);
					prepare.executeUpdate();
					
					//co.commit();
					x++;
				}
				
				Object temporal = query.toQuery("mysql", co); // Se lee cada query aqui
				
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
	      Class.forName("com.mysql.jdbc.Driver");
	      c = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ db,"root", "SebasBrito01");
	      //System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      
	      stmt.execute("PRAGMA auto_vacuum = FULL");
	      stmt.execute("VACUUM");
	      
	      //stmt.executeUpdate(sql);
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
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
	
	public ArrayList<Queryable> getImagenes(){
		
		ArrayList<Queryable> imagenes = new ArrayList<>();
	
		try {
			Statement stm = co.createStatement();
			ResultSet rs = stm.executeQuery("SELECT FOTO, MODELO FROM imagenes ORDER BY MODELO");
			
			
			while(rs.next())
			{
				Imagen x = new Imagen();
				
				String PROD_MODELO = rs.getString("MODELO");
				x.setPROD_MODELO(PROD_MODELO);
				
				String IMAGEN_GRANDE = rs.getString("FOTO");
				x.setIMAGEN_GRANDE(IMAGEN_GRANDE);
				
				imagenes.add(x);	
				
			}
			
			stm.close();	
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("error en DB imagenes tablet");
		}
		
		return imagenes;
	}
	
public ArrayList<Queryable> getItems(){
		
		ArrayList<Queryable> items = new ArrayList<>();
	
		try {
			Statement stm = co.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM item ORDER BY DSCR;");
			
			
			while(rs.next())
			{
				ItemTablet x = new ItemTablet();
				
				String ITEM = rs.getString("ITEM");
				x.setITEM(ITEM);
				
				String DSCR = escapeSqlData(rs.getString("DSCR"));
				x.setDSCR(DSCR);
				
				String LABO = rs.getString("LABO");
				x.setLABO(LABO);
				
				Double PREC = rs.getDouble("PREC");
				x.setPREC(PREC);
				
				Double STK1 = rs.getDouble("STK1");
				x.setSTK1(STK1);
				
				if(STK1 > 100){
					x.setSTK1(100);
				}
				
				Double PVP = rs.getDouble("PVP");
				x.setPVP(PVP);
				
				String rin = rs.getString("rin");
				x.setRin(rin);
				
				String modelo = rs.getString("modelo");
				x.setModelo(modelo);
				
				String huecos = rs.getString("huecos");
				x.setHuecos(huecos);
				
				String ancho = rs.getString("ancho");
				x.setAncho(ancho);
				
				String camara = rs.getString("camara");
				x.setCamara(camara);
				
				String tipo = rs.getString("tipo");
				x.setTipo(tipo);
				
				String descripcion_completa = rs.getString("descripcion_completa");
				x.setDescripcion_completa(descripcion_completa);
				
				
				items.add(x);	
				
			}
			
			stm.close();	
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("error en DB imagenes tablet");
		}
		
		return items;
	}
	
	public void dateUpdate(java.sql.Date sqlDate){
		
		try {
			Statement stmt = co.createStatement();
			
			String sql = "UPDATE version SET version_datos ='"+sqlDate+"'";
			stmt.executeUpdate(sql);
			
			stmt.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Fecha actualizada en version");
		
		
	}
	
	public String escapeSqlData(String hola){
		
		//StringBuilder myName = null; 
		try {
             hola = hola.replaceAll("'", "''")
                     .replaceAll("&","&&");
                        
            // myName = new StringBuilder(hola);
            // myName.setCharAt(34, ' ');
		} catch (Exception e) {
			e.printStackTrace();

		}
		
		//return myName.toString();
		return hola;
	}
	
	
}

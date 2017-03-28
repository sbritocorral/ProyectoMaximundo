package bases;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;


public class DBSQLserver{
	
	private Connection co = null;
	
	
	public  DBSQLserver(String db){
		
		
		String url ="jdbc:sqlserver://localhost;databaseName=" + db;
		try {
		      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		      co = DriverManager.getConnection(url, "sa", "SebasBrito01");
		      //co.setAutoCommit(false);
		      
		      
		      
		    
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      throw new RuntimeException("Error al abrir SQLserver DB");
		      
		    }
		    System.out.println("Conectado a SQLServer " + db);
		    
		    
	}
	
	public Connection getConnection(){
		return this.co;
	}
	
	
	
	
	public ArrayList<Queryable> getClie(Connection con){
			
			ArrayList<Queryable> clie = new ArrayList<>();
			String codTemp, sql;
	
			try {
				Statement stm = co.createStatement();
				ResultSet rs = stm.executeQuery("SELECT CardCode, CardName, Phone1, E_mail, City, Balance, Address, SlpCode, Territory, LicTradNum, U_HT_NUM_RET FROM OCRD WHERE CardType  IN ('C','L') ORDER BY CardName;");
				
				Statement stm2 = con.createStatement(); // con es dbTablet
				
				//Statement stm3 = con.createStatement();
				
				ResultSet rs1 = null;
				while(rs.next())
				{
					Clie x = new Clie();
					
					codTemp = rs.getString("CardCode");
					
					String REFE = rs.getString("LicTradNum");
					x.setREFE(REFE);
					
					String NOMB = rs.getString("CardName");
					x.setNOMB(NOMB);
					
					
					if(rs.getString("CardName") != null){
						
						NOMB = 	escapeSqlData(rs.getString("CardName").toUpperCase());
						x.setNOMB(NOMB);	
					}
					
					//System.out.println("Esto es NOMB: " + NOMB);
					
					String TELE = rs.getString("Phone1");
					x.setTELE(TELE);
					
					
					String DIR1 = rs.getString("Address");
					x.setDIR1(DIR1);
					
					String VEND = rs.getString("SlpCode");
					x.setVEND(VEND);
					
					
					
					String RECOD = rs.getString("Territory");
					x.setRECOD(RECOD);
					
					String CLIE = rs.getString("CardCode");
					x.setCLIE(CLIE);
					
					
						
					String EMAIL = rs.getString("E_mail");
					x.setEMAIL(EMAIL);
					
					if(rs.getString("E_mail") != null){
						
						EMAIL = escapeSqlData(rs.getString("E_mail").toUpperCase());
						x.setEMAIL(EMAIL);	
					}
					
					//System.out.println("Esto es EMAIL: " + EMAIL);
					
					String CIUDAD = rs.getString("City");
					x.setCIUDAD(CIUDAD);
					
					if(rs.getString("City") != null){
						
						EMAIL = escapeSqlData(rs.getString("City").toUpperCase());
						x.setCIUDAD(CIUDAD);	
					}
					
					
					
					String AUTORIZACIONRETENCION = rs.getString("U_HT_NUM_RET");
					x.setAUTORIZACIONRETENCION(AUTORIZACIONRETENCION);
					
					Double Saldo = rs.getDouble("Balance");
					x.setSALDO(Saldo);
					
					sql = "SELECT CLIE FROM clie WHERE CLIE='" + codTemp + "'";
					
					rs1 = stm2.executeQuery(sql);
					
					
					
					if(rs1.next()){
						
						sql="UPDATE clie SET NOMB='"+ NOMB +"',VEND='"+rs.getString("SlpCode")+"',RECOD='"+rs.getString("Territory")+"',EMAIL='"
						+EMAIL+"',SALDO="+String.valueOf(rs.getDouble("Balance"))+" WHERE CLIE='"+codTemp+"'";
						
						
						stm2.executeUpdate(sql);
					}
					else{
						
						Object insertar = x.toQuery("mysql", con);
						
						((PreparedStatement)(insertar)).executeUpdate();
						
					}
					
					clie.add(x);	
					
				}
				
				System.out.println("listo clientes");
				
				stm.close();
				stm2.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.getMessage());
				System.out.println("error en DB clientes");
			}
			
			return clie;
		}
	
	
	public ArrayList<Queryable> getRecod(){
		
		ArrayList<Queryable> recod = new ArrayList<>();
	
		try {
			Statement stm = co.createStatement();
			ResultSet rs = stm.executeQuery("SELECT T1.[territryID], T1.[descript] FROM [dbo].[OTER]  T1");
			
			
			while(rs.next())
			{
				Recod x = new Recod();
				
				String RECOD = rs.getString("territryID");
				x.setRECOD(RECOD);
				
				String DSCR = rs.getString("descript");
				x.setDSCR(DSCR);
				
				recod.add(x);	
				
			}
			
			stm.close();	
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("error en DB zonas");
		}
		
		return recod;
	}
		
	public ArrayList<Queryable> getVendedores(){
		
		ArrayList<Queryable> vendedores = new ArrayList<>();
	
		try {
			Statement stm = co.createStatement();
			ResultSet rs = stm.executeQuery("SELECT T1.[SlpCode], T1.[SlpName] FROM [dbo].[OSLP]  T1;");
			
			
			while(rs.next())
			{
				Vendedor x = new Vendedor();
				
				String Codigo = rs.getString("SlpCode");
				x.setCodigo(Codigo);
				
				String Nombre = rs.getString("SlpName");
				x.setNombre(Nombre);
				
				String Recorrido = "99";
				x.setRecorrido(Recorrido);
				
				String Password = "12345";
				x.setPassword(Password);
				
				vendedores.add(x);	
				
			}
			
			stm.close();	
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("error en DB vendedores");
		}
		
		return vendedores;
	}
	
	
	public ArrayList<Queryable> getLabo(){
		
		ArrayList<Queryable> labo = new ArrayList<>();
	
		try {
			Statement stm = co.createStatement();
			ResultSet rs = stm.executeQuery("SELECT T1.[FirmCode], T1.[FirmName] FROM [dbo].[OMRC]  T1");
			
			
			while(rs.next())
			{
				Labo x = new Labo();
				
				String LABO = rs.getString("FirmCode");
				x.setLABO(LABO);
				
				String DSCR = rs.getString("FirmName");
				x.setDSCR(DSCR);
				
				labo.add(x);	
				
			}
			
			stm.close();	
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("error en DB marcas");
		}
		
		return labo;
	}
	
	
	public ArrayList<Queryable> getItems(Connection con){
		
		ArrayList<Queryable> items = new ArrayList<>();
		String sql= null;
		
		int count = 0;
	
		try {
			Statement stm = co.createStatement(); // co es dbMain
			
			ResultSet rs = stm.executeQuery( "SELECT T0.[ItemCode] 'codigo', T0.[ItemName] 'nombre', SUM(T4.[OnHand]) 'cantidad', T2.[Price] 'precio', T1.[Price] 'PVP',  T0.[FirmCode] 'marca' FROM OITM T0 "
					+"  LEFT OUTER JOIN ITM1 T1 ON T0.ItemCode = T1.ItemCode AND T1.[PriceList] = 1 "
					+"  LEFT OUTER JOIN ITM1 T2 ON T0.ItemCode = T2.ItemCode AND T2.[PriceList] = 2 "
					+ " LEFT OUTER JOIN OITW T4 ON T0.ItemCode = T4.ItemCode WHERE T0.[ItemName] LIKE '%MAXXIS%' AND T4.[WhsCode] IN ('01','02') AND T2.[Price] > 0 "
					+" GROUP BY T0.[ItemCode], T0.[ItemName],  T2.[Price], T1.[Price],  T0.[FirmCode] ORDER BY T0.[ItemName] " );
			
			Statement stm2 = con.createStatement(); // con es dbTablet
			
			
			ResultSet rs1 = null;
			
			while(rs.next())
			{
				Item x = new Item();
				
				String codTemp = rs.getString("codigo");
				
				String ITEM = rs.getString("codigo");
				x.setITEM(ITEM);
				
				String DSCR = encodeWindosLatin(escapeSqlData(rs.getString("nombre"))).toUpperCase();
				x.setDSCR(DSCR);
				
				String LABO = rs.getString("marca");
				x.setLABO(LABO);
				
				Double PREC = rs.getDouble("precio");
				x.setPREC(PREC);
				
				Double STK1 = rs.getDouble("cantidad");
				x.setSTK1(STK1);
				
				Double PVP = rs.getDouble("PVP");
				x.setPVP(PVP);
				
				
				sql = "SELECT ITEM FROM item WHERE ITEM='" + codTemp + "'";
				
				rs1 = stm2.executeQuery(sql);
				
				
				
				if(rs1.next()){
					
					sql= "UPDATE item SET DSCR='"+ encodeWindosLatin(escapeSqlData(rs.getString("nombre"))).toUpperCase() +"',PREC="+String.valueOf(rs.getDouble("precio"))
					+",STK1="+String.valueOf(rs.getDouble("cantidad"))+",PVP="+String.valueOf(rs.getDouble("PVP"))+" WHERE ITEM='"+codTemp + "'";
					
					stm2.executeUpdate(sql);
				}
				else{
					
					Object insertar = x.toQuery("mysql", con);
					
					
					((PreparedStatement)(insertar)).executeUpdate();
					
				}
				
				count++;
				items.add(x);	
				
			}
			
			System.out.println("listo items maximundo");
			System.out.println(count + " items procesados");
			stm.close();
			stm2.close();
			
			
			
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("Error en db Items");
		}
		
		return items;
	}
	
	
	public ArrayList<Queryable> getItems2(Connection con){
		
		ArrayList<Queryable> items = new ArrayList<>();
		String sql= null;
		
		int count = 0;
	
		try {
			Statement stm = co.createStatement(); // co es dbMain
			
			ResultSet rs = stm.executeQuery("SELECT T0.[ItemCode] 'codigo', T0.[ItemName] 'nombre', SUM(T4.[OnHand]) 'cantidad', T2.[Price] 'precio', T1.[Price] 'PVP',  T0.[FirmCode] 'marca' FROM OITM T0 "
					+" LEFT OUTER JOIN ITM1 T1 ON T0.ItemCode = T1.ItemCode AND T1.[PriceList] = 1 "
					+" LEFT OUTER JOIN ITM1 T2 ON T0.ItemCode = T2.ItemCode AND T2.[PriceList] = 2 "
					+" LEFT OUTER JOIN OITW T4 ON T0.ItemCode = T4.ItemCode WHERE T0.[ItemName] LIKE '%YUELING%' AND T4.[WhsCode] IN ('01','02') AND T2.[Price] > 0 GROUP BY T0.[ItemCode], T0.[ItemName],  T2.[Price], T1.[Price],  T0.[FirmCode] ORDER BY T0.[ItemName] ");
					
				
			
			Statement stm2 = con.createStatement(); // con es dbTablet
			
			Statement stm3 = con.createStatement();
			
			ResultSet rs1 = null;
			
			while(rs.next())
			{
				Item x = new Item();
				
				String codTemp = rs.getString("codigo");
				
				String ITEM = rs.getString("codigo");
				x.setITEM(ITEM);
				
				String DSCR = encodeWindosLatin(escapeSqlData(rs.getString("nombre")));
				x.setDSCR(DSCR);
				
				String LABO = rs.getString("marca");
				x.setLABO(LABO);
				
				Double PREC = rs.getDouble("precio");
				x.setPREC(PREC);
				
				Double STK1 = rs.getDouble("cantidad");
				x.setSTK1(STK1);
				
				Double PVP = rs.getDouble("PVP");
				x.setPVP(PVP);
				
				
				sql = "SELECT ITEM FROM item WHERE ITEM='" + codTemp + "'";
				
				rs1 = stm2.executeQuery(sql);
				
				
				
				if(rs1.next()){
					
					sql= "UPDATE item SET DSCR='"+ rs.getString("nombre") +"',PREC="+String.valueOf(rs.getDouble("precio"))
					+",STK1="+String.valueOf(rs.getDouble("cantidad"))+",PVP="+String.valueOf(rs.getDouble("PVP"))+" WHERE ITEM='"+codTemp + "'";
					
					stm2.executeUpdate(sql);
				}
				else{
					
					Object insertar = x.toQuery("mysql", con);
					
					((PreparedStatement)(insertar)).executeUpdate();
					
				}
				
				count++;
				items.add(x);	
				
			}
			
			System.out.println("listo items autollanta");
			System.out.println(count + " items procesados");
			stm.close();
			stm2.close();
			stm3.close();
			
			
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("error en DB items AUTOLLANTA");
		}
		
		return items;
	}
	
		public void close(){
			try {
				co.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

		public String encodeWindosLatin(String hola){
			
			byte[] sourceBytes = null;
			String data = null; 
			try {
			
				
				sourceBytes = hola.getBytes("UTF-8");
				data = new String(sourceBytes , "Windows-1252");
				
				//System.out.println(data);
					
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
			//System.out.println(sourceBytes.toString());
			
			return data;	
		}
		
}
	
	
	
	
	
	
	

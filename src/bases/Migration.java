package bases;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class Migration {
	
	
	public static void main(String[] args) {
			
		
		long startTime = System.nanoTime();	
		
		DBSQLserver dbMain = new DBSQLserver("MAXXIMUNDO_PROD"); 
		DBSQLserver dbAutollanta = new DBSQLserver("PROD_AUTOLLANTA");
			
		DBMysql dbTablet = new DBMysql("tablet");
		
		DBSqlite dbAndroid = new DBSqlite("maximundo.db");
		DBSqlite dbAndroid1 = new DBSqlite("maxi_imagenes.db");
		
		dbAndroid.deleteDB();
		
		
		dbMain.getItems(dbTablet.getConnection());			//Se pasa items de maximundo a tablet
		dbAutollanta.getItems2(dbTablet.getConnection());	//Se pasa items de autollanta a tablet
		
		
		
		dbAndroid.Insert(dbTablet.getItems());  //Ahora de tabletmysql se pasa a sqlite
		
		
		ArrayList<Queryable> clientes = dbMain.getClie(dbTablet.getConnection());
		
		dbAndroid.Insert(clientes); //clientes a sqlite
		dbTablet.Insert(clientes);  //clientes a mysql
		
		
		ArrayList<Queryable> vendedores = dbMain.getVendedores();
		
		dbAndroid.Insert(vendedores);
		dbTablet.Insert(vendedores);
		
		
		ArrayList<Queryable> marcas = dbMain.getLabo();
		
		dbAndroid.Insert(marcas);
		dbTablet.Insert(marcas);
		
	
		
		//Cosa de la hora
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
		dbTablet.dateUpdate(sqlDate);
		
		
		ArrayList<Queryable> zonas = dbMain.getRecod();
		
		dbAndroid.Insert(zonas);
		dbTablet.Insert(zonas);
		
		
		dbAndroid1.deleteDB1(); 	// Se borra android1
		
		ArrayList<Queryable> imagenes = dbTablet.getImagenes();
		
		dbAndroid1.Insert(imagenes); // Se inserta imagenes
		
		
		
		
		
		
		dbAndroid.commitAndClose();
		dbAndroid1.commitAndClose();
		dbAndroid.vacuum("maximundo.db");
		dbAndroid1.vacuum("maxi_imagenes.db");
		
		
		dbTablet.commitAndClose();
		dbMain.close();
		dbAutollanta.close();
		
		
		
		
		
		

		Comprimir cmp = new Comprimir();	
		String sistema = cmp.getOS();			//Se identifica en que sistema operativo corre el programa para comprimirlo
		
		if(sistema.startsWith("Windows")){
			try {
				cmp.gzipFileWindows("C:\\Users\\Sebastian\\Desktop\\Maximundo\\maximundo.db", "C:\\Users\\Sebastian\\Desktop\\Maximundo\\maximundo.db.gz");
				cmp.gzipFileWindows("C:\\Users\\Sebastian\\Desktop\\Maximundo\\maxi_imagenes.db", "C:\\Users\\Sebastian\\Desktop\\Maximundo\\maxi_imagenes.db.gz");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			cmp.gzipFileLinux("gzip -c /home/apps/maximundo.db > /home/apps/maximundo.db.gz");
			cmp.gzipFileLinux("gzip -c /home/apps/maxi_imagenes.db > /home/apps/maxi_imagenes.db.gz");
		}
		
		
		long endTime = System.nanoTime();
		System.out.println("Tiempo del programa es: " + (endTime - startTime) + " ns ."); 
		
		
	}
	
}

package bases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Item implements Queryable{
	
	private String tabla = "item";
	
	private String ITEM;
	private String DSCR;
	private String LABO;
	private double PREC;
	private double STK1;
	private double PVP;
	
	public String getITEM() {
		return ITEM;
	}
	public void setITEM(String iTEM) {
		ITEM = iTEM;
	}
	public String getDSCR() {
		return DSCR;
	}
	public void setDSCR(String dSCR) {
		DSCR = dSCR;
	}
	public String getLABO() {
		return LABO;
	}
	public void setLABO(String lABO) {
		LABO = lABO;
	}
	
	public double getPREC() {
		return PREC;
	}
	public void setPREC(double pREC) {
		PREC = pREC;
	}
	public double getSTK1() {
		return STK1;
	}
	public void setSTK1(double sTK1) {
		STK1 = sTK1;
	}
	public double getPVP() {
		return PVP;
	}
	public void setPVP(double pVP) {
		PVP = pVP;
	}
	
	@Override
	public String toString() {
		return "Item [ITEM=" + ITEM + ", DSCR=" + DSCR + ", LABO=" + LABO + ", PREC=" + PREC
				+ ", STK1=" + STK1 + ", PVP=" + PVP  + "]";
	}
	@Override
	public Object toQuery(String dbType, Connection con) {
		
		String query = null;
		
		query = "INSERT INTO item (ITEM, DSCR, LABO, PREC, STK1, PVP) values (?,?,?,?,?,?)";
		
		PreparedStatement statement= null;
		try {
			
			statement = con.prepareStatement(query);
			
			statement.setString(1, this.ITEM);
			statement.setString(2, this.DSCR);
			statement.setString(3, this.LABO);
			statement.setDouble(4, this.PREC);
			statement.setDouble(5, this.STK1);
			statement.setDouble(6, this.PVP);
		
			//statement.executeUpdate();
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
					
		return statement;
	}
	@Override
	public Object beforeInsert(String dbType, Connection con) {
		
		PreparedStatement stmt = null;
		try {
			String sql = "DELETE from item ;";
			stmt = con.prepareStatement(sql);			
		  
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
		return stmt;
	}
	@Override
	public String getTabla() {
		return this.tabla;
	}
	
	
	
	
	

}

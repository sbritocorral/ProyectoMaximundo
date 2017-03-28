package bases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Clie implements Queryable{
	
	private String tabla = "clie";
	
	private String REFE;
	private String NOMB;
	private String TELE;
	private String DIR1;
	private String VEND;
	private String RECOD;
	private String CLIE;
	private String EMAIL;
	private String CIUDAD;
	private String AUTORIZACIONRETENCION;
	private Double SALDO;
	public String getREFE() {
		return REFE;
	}
	public void setREFE(String rEFE) {
		REFE = rEFE;
	}
	public String getNOMB() {
		return NOMB;
	}
	public void setNOMB(String nOMB) {
		NOMB = nOMB;
	}
	public String getTELE() {
		return TELE;
	}
	public void setTELE(String tELE) {
		TELE = tELE;
	}
	public String getDIR1() {
		return DIR1;
	}
	public void setDIR1(String dIR1) {
		DIR1 = dIR1;
	}
	public String getVEND() {
		return VEND;
	}
	public void setVEND(String vEND) {
		VEND = vEND;
	}
	public String getRECOD() {
		return RECOD;
	}
	public void setRECOD(String rECOD) {
		RECOD = rECOD;
	}
	public String getCLIE() {
		return CLIE;
	}
	public void setCLIE(String cLIE) {
		CLIE = cLIE;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getCIUDAD() {
		return CIUDAD;
	}
	public void setCIUDAD(String cIUDAD) {
		CIUDAD = cIUDAD;
	}
	public String getAUTORIZACIONRETENCION() {
		return AUTORIZACIONRETENCION;
	}
	public void setAUTORIZACIONRETENCION(String aUTORIZACIONRETENCION) {
		AUTORIZACIONRETENCION = aUTORIZACIONRETENCION;
	}
	public Double getSALDO() {
		return SALDO;
	}
	public void setSALDO(Double sALDO) {
		SALDO = sALDO;
	}
	
	public String getTabla(){
		return tabla;
	}
	@Override
	public String toString() {
		return "Clie [REFE=" + REFE + ", NOMB=" + NOMB + ", TELE=" + TELE + ", DIR1=" + DIR1 + ", VEND=" + VEND
				+ ", RECOD=" + RECOD + ", CLIE=" + CLIE + ", EMAIL=" + EMAIL + ", CIUDAD=" + CIUDAD
				+ ", AUTORIZACIONRETENCION=" + AUTORIZACIONRETENCION + ", SALDO=" + SALDO + "]";
	}
	@Override
	public Object toQuery(String dbType, Connection con) {

		String query = null;
		
		query = "INSERT INTO clie (REFE, NOMB, TELE, DIR1, VEND, RECOD, CLIE, EMAIL, CIUDAD, AUTORIZACIONRETENCION, SALDO) values (?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement statement= null;
		try {
			
			statement = con.prepareStatement(query);
			
			statement.setString(1, this.REFE);
			statement.setString(2, this.NOMB);
			statement.setString(3, this.TELE);
			statement.setString(4, this.DIR1);
			statement.setString(5, this.VEND);
			statement.setString(6, this.RECOD);
			statement.setString(7, this.CLIE);
			statement.setString(8, this.EMAIL);
			statement.setString(9, this.CIUDAD);
			statement.setString(10, this.AUTORIZACIONRETENCION);
			statement.setDouble(11, this.SALDO);
		
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
					
		return statement;
	}
	@Override
	public Object beforeInsert(String dbType, Connection con) {

		PreparedStatement stmt = null;
		try {
			String sql = "DELETE from clie ;";
			stmt = con.prepareStatement(sql);			
		  
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
		return stmt;
	}
	
	

}

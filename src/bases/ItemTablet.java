package bases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemTablet implements Queryable{
	
	private String tabla = "item";
	
	private String ITEM;
	private String DSCR;
	private String LABO;
	private double PREC;
	private double STK1;
	private double PVP;
	private String rin;
	private String modelo;
	private String huecos;
	private String ancho;
	private String camara;
	private String tipo;
	private String descripcion_completa;
	public String getTabla() {
		return tabla;
	}
	public void setTabla(String tabla) {
		this.tabla = tabla;
	}
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
	public String getRin() {
		return rin;
	}
	public void setRin(String rin) {
		this.rin = rin;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getHuecos() {
		return huecos;
	}
	public void setHuecos(String huecos) {
		this.huecos = huecos;
	}
	public String getAncho() {
		return ancho;
	}
	public void setAncho(String ancho) {
		this.ancho = ancho;
	}
	public String getCamara() {
		return camara;
	}
	public void setCamara(String camara) {
		this.camara = camara;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescripcion_completa() {
		return descripcion_completa;
	}
	public void setDescripcion_completa(String descripcion_completa) {
		this.descripcion_completa = descripcion_completa;
	}
	@Override
	public String toString() {
		return "ItemTablet [tabla=" + tabla + ", ITEM=" + ITEM + ", DSCR=" + DSCR + ", LABO=" + LABO + ", PREC=" + PREC
				+ ", STK1=" + STK1 + ", PVP=" + PVP + ", rin=" + rin + ", modelo=" + modelo + ", huecos=" + huecos
				+ ", ancho=" + ancho + ", camara=" + camara + ", tipo=" + tipo + ", descripcion_completa="
				+ descripcion_completa + "]";
	}
	@Override
	public Object toQuery(String dbType, Connection con) {

		String query = null;
		
		query = "INSERT INTO item (ITEM, DSCR, LABO, PREC, STK1, PVP, rin, modelo, huecos, ancho, camara, tipo,descripcion_completa) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement statement= null;
		try {
			
			statement = con.prepareStatement(query);
			
			statement.setString(1, this.ITEM);
			statement.setString(2, this.DSCR);
			statement.setString(3, this.LABO);
			statement.setDouble(4, this.PREC);
			statement.setDouble(5, this.STK1);
			statement.setDouble(6, this.PVP);
			statement.setString(7, this.rin);
			statement.setString(8, this.modelo);
			statement.setString(9, this.huecos);
			statement.setString(10, this.ancho);
			statement.setString(11, this.camara);
			statement.setString(12, this.tipo);
			statement.setString(13, this.descripcion_completa);
		
			//statement.executeUpdate();
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
					
		return statement;
	}
	@Override
	public Object beforeInsert(String dbType, Connection con) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}

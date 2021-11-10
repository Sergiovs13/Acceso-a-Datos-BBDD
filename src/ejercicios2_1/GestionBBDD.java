package ejercicios2_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestionBBDD {

	Connection cnnctn;
	
	public void abrirConexion() {
		try {
			cnnctn=DriverManager.getConnection("jdbc:mysql://localhost/manempsa","root","");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void cerrarConexion() {
		try {
			cnnctn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String verTrabajadores() throws SQLException {
		Statement st=cnnctn.createStatement();
		ResultSet rs=st.executeQuery("Select * From trabajadores");
		
		String info="";
		String fecha;
		String dia;
		String mes;
		String anyo;
		while(rs.next()) {
			fecha=rs.getString("fecha");
			dia=fecha.substring(8, 10);
			mes=fecha.substring(5, 7);
			anyo=fecha.substring(0, 4);
			fecha=dia+"/"+mes+"/"+anyo;
			
			info+=rs.getString("dni")+" -- "+rs.getString("nombre")+" "+rs.getString("apellido")+" -- "+rs.getString("sueldo")+" -- "+fecha+" -- "+rs.getString("matricula")+"\n";
		}
		return info;
	}
}

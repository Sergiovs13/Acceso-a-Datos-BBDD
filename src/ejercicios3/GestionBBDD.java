package ejercicios3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

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
	
	public void mostrarTrabajadores(DefaultTableModel dtm) throws SQLException {
		Statement st=cnnctn.createStatement();
		ResultSet rs=st.executeQuery("Select * From trabajadores");
		String[] datos=new String[dtm.getColumnCount()];
		while(rs.next()) {
			for(int i=0;i<datos.length;i++) {
				datos[i]=rs.getString(i+1);
			}
			dtm.addRow(datos);
		}
		st.close();
		rs.close();
	}
}

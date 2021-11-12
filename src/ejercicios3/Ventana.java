package ejercicios3;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Ventana extends JFrame{
	private JTable table;
	private DefaultTableModel dtm= new DefaultTableModel();
	private GestionBBDD gest=new GestionBBDD();
	
	public Ventana() {
		iniciarComponentes();
		gest.abrirConexion();
	}
	
	private void iniciarComponentes() {
		setTitle("Ver trabajadores");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		dtm.setColumnIdentifiers(new String [] {"DNI","Nombre","Apellidos","Sueldo","Fecha","Matricula"});
		table = new JTable(dtm);
		getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(24, 11, 438, 192);
		getContentPane().add(scrollPane);
		
		JButton btnVerTrabajadores = new JButton("Ver Trabajadores");
		btnVerTrabajadores.setBounds(24, 232, 140, 23);
		getContentPane().add(btnVerTrabajadores);
		
		setVisible(true);
		
		this.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
				gest.cerrarConexion();
			}
		});
	}
}

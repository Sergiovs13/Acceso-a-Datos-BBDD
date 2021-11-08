package ejercicios1_2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class Ventana extends JFrame{

	GestionBBDD gest=new GestionBBDD();
	public Ventana() {
		iniciarComponentes();
		gest.crearConexion();
	}
	
	private void iniciarComponentes() {
		setTitle("Ventana Servicios");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JButton btnVerServicios = new JButton("Ver Servicios");
		btnVerServicios.setBounds(164, 42, 120, 23);
		getContentPane().add(btnVerServicios);
		btnVerServicios.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				gest.verServicios();
			}
		});
		
		this.addWindowFocusListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				gest.cerrarConexion();
			}
		});
		setVisible(true);
	}
}

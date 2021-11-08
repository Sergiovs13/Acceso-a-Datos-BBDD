package ejercicios1_2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Ventana extends JFrame{

	public Ventana() {
		iniciarComponentes();
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
				
			}
			
		});
		
		setVisible(true);
	}
}

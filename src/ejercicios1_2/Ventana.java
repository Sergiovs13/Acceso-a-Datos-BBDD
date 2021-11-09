package ejercicios1_2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

/**
 * Clase que gestiona la ventana
 * @author windows10
 *
 */
public class Ventana extends JFrame{
	

	GestionBBDD gest=new GestionBBDD();
	/**
	 * Constructor de la clase
	 */
	public Ventana() {
		iniciarComponentes();
		gest.crearConexion();
	}
	/**
	 * Metodo que inicia y da utilidad a los componentes
	 */
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
		
		JButton btnVerClientes = new JButton("Ver Clientes");
		btnVerClientes.setBounds(164, 135, 120, 23);
		getContentPane().add(btnVerClientes);
		
		btnVerClientes.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				gest.verClientes();
			}
			
		});
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				gest.cerrarConexion();
			}
		});
		setVisible(true);
	}
}

package ejercicios1_1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
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
		gest.conectarseBBDD();
	}

	/**
	 * Metodo que inicia y da utilidad a los compenentes
	 */
	private void iniciarComponentes() {
		setTitle("Ventana");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JButton btnVerTrabajadores = new JButton("Ver Trabajadores");
		btnVerTrabajadores.setBounds(168, 37, 141, 23);
		getContentPane().add(btnVerTrabajadores);
		btnVerTrabajadores.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				gest.verTrabajadores();
			}
			
		});
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				gest.cerrarConexion();
			}
		});
		setVisible(true);
	}
}

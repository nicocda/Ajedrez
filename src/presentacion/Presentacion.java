package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.util.ArrayList;


import javax.swing.JTextArea;

import negocio.ControladorPartida;
import entidades.Jugador;
import entidades.Partida;
import entidades.Trebejo;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Presentacion extends JFrame {

	private JPanel contentPane;
	private JTextField txtDniBlanco;
	private JTextField txtDniNegro;
	private JTextField txtMovX;
	private JTextField txtMovY;
	private JLabel lblJ1, lblJ2;
	private ControladorPartida cp = new ControladorPartida();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Presentacion frame = new Presentacion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Presentacion() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		//Panel Principal
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Panel selección de partidas
		JPanel pnlSeleccionPartidas = new JPanel();
		pnlSeleccionPartidas.setBounds(5, 5, 424, 251);
		contentPane.add(pnlSeleccionPartidas);
		pnlSeleccionPartidas.setLayout(null);
		
		//Panel movimiento de fichas
		JPanel pnlFichas = new JPanel();
		pnlFichas.setLayout(null);
		pnlFichas.setBounds(0, 0, 424, 251);
		contentPane.add(pnlFichas);
		pnlFichas.setVisible(false);

		//Elementos del panel "Movimiento de fichas"
		JLabel lblFichasBlancas = new JLabel("Blancas: ");
		lblFichasBlancas.setFont(new Font("Verdana", Font.BOLD, 16));
		lblFichasBlancas.setBounds(38, 22, 89, 44);
		pnlFichas.add(lblFichasBlancas);
		
		JLabel lblFichasNegras = new JLabel("Negras: ");
		lblFichasNegras.setFont(new Font("Verdana", Font.BOLD, 16));
		lblFichasNegras.setBounds(238, 22, 89, 44);
		pnlFichas.add(lblFichasNegras);
		
		JList listaBlancas = new JList();
		DefaultListModel model_1 = new DefaultListModel();
		listaBlancas.setBounds(38, 61, 135, 120);
		pnlFichas.add(listaBlancas);
		
		JList listaNegras = new JList();
		DefaultListModel model_2 = new DefaultListModel();
		listaNegras.setBounds(257, 61, 135, 120);
		pnlFichas.add(listaNegras);
		
		txtMovX = new JTextField();
		txtMovX.setColumns(10);
		txtMovX.setBounds(139, 192, 53, 20);
		pnlFichas.add(txtMovX);
		
		txtMovY = new JTextField();
		txtMovY.setColumns(10);
		txtMovY.setBounds(202, 192, 53, 20);
		pnlFichas.add(txtMovY);
		
		JLabel lblPosicionFinal = new JLabel("Posicion Final: ");
		lblPosicionFinal.setBounds(38, 192, 104, 20);
		pnlFichas.add(lblPosicionFinal);
	
		lblJ1 = new JLabel("Jugador 1");
		lblJ1.setFont(new Font("Verdana", Font.BOLD, 16));
		lblJ1.setBounds(130, 22, 98, 44);
		pnlFichas.add(lblJ1);
		
		lblJ2 = new JLabel("Jugador 2");
		lblJ2.setFont(new Font("Verdana", Font.BOLD, 16));
		lblJ2.setBounds(337, 22, 89, 44);
		pnlFichas.add(lblJ2);

		JButton btnMover = new JButton("Mover");
		btnMover.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
<<<<<<< HEAD
				//Todo esto sería ideal que esté en un método aparte, privado (Cuando hicimos lo de 
				//windows form de mecca, en clases, me acuerdo que una de las cosas que me corrigio fue eso.
				pnlSeleccionPartidas.setVisible(false);
				pnlFichas.setVisible(false);
				
				Trebejo t = null;
								
				int j1 =Integer.parseInt( txtDniBlanco.getText());
				int j2 =Integer.parseInt( txtDniNegro.getText());
				int posX =Integer.parseInt( txtMovX.getText());
				int posY =Integer.parseInt( txtMovY.getText());
				Trebejo us = (Trebejo) listaBlancas.getSelectedValue();

				t = cp.validarMovimiento(j1, j2, posX, posY);
				if(t == null)
				{
					JOptionPane.showMessageDialog(pnlFichas, "No existe ese trebejo");
					//y esto?
					//System.out.println("No existe ese trebejo");
				}
				else
				{
					//LOS DATOS DE LOS PARAMETROS SON LOS DE LA FICHA SELECCIONADA(INICIAL)
					if(t.movimientoPermitido(us.getPosX(), us.getPosY(), us.getColor()))
					{
						JOptionPane.showMessageDialog(pnlFichas, "Movimiento realizado!");
						//y esto?
						//System.out.println("Movimiento realizado!");
					};
				}
			}
=======
				panel_inicial.setVisible(false);
				panel_2.setVisible(false);
				Trebejo t = null;
				
				
				int j1 =Integer.parseInt( txtDni.getText());
				int j2 =Integer.parseInt( txtDni2.getText());
				int posX =Integer.parseInt( movX.getText());
				int posY =Integer.parseInt( movY.getText());
				Trebejo us = (Trebejo) list_1.getSelectedValue();
				
				
				if(us!=null)
				{
					if(cp.movimientoPermitido(posX, posY, us));
				} else{
					JOptionPane.showMessageDialog(panel_2,"trebejo no existe");
						}
			}
				
>>>>>>> d6b6eefdbbcaa6a8b6a4acf1ca0edeac002ba490
		});
		
		btnMover.setBounds(292, 192, 89, 23);
		pnlFichas.add(btnMover);
		
		
		//Elementos del panel "Selección de partidas"
		txtDniBlanco = new JTextField();
		txtDniBlanco.setBounds(124, 32, 98, 20);
		pnlSeleccionPartidas.add(txtDniBlanco);
		txtDniBlanco.setColumns(10);
		
		txtDniNegro = new JTextField();
		txtDniNegro.setBounds(36, 152, 262, 20);
		pnlSeleccionPartidas.add(txtDniNegro);
		txtDniNegro.setColumns(10);
		
		JLabel lblDniBlanco = new JLabel("Ingrese DNI: ");
		lblDniBlanco.setBounds(36, 35, 79, 14);
		pnlSeleccionPartidas.add(lblDniBlanco);
		
		JLabel lblDniNegro = new JLabel(": DNI Oponente");
		lblDniNegro.setBounds(308, 153, 87, 14);
		pnlSeleccionPartidas.add(lblDniNegro);
		
		JTextArea txtAreaPartidas = new JTextArea();
		txtAreaPartidas.setBounds(36, 60, 340, 87);
		pnlSeleccionPartidas.add(txtAreaPartidas);
		
		JButton btnJugar = new JButton("Jugar");
		btnJugar.addMouseListener(new MouseAdapter() {
			@Override

			public void mouseClicked(MouseEvent arg0) {
				//Lo mismo de los métodos privados que en el evento anterior.
				int j1 =Integer.parseInt(txtDniBlanco.getText());
				int j2 =Integer.parseInt(txtDniNegro.getText());
				//SI UNO DE LOS textFields ESTA VACIO TIRA ERROR(CORREGIR)...	
				Partida p=null;	
				p = cp.cargarPartida(j1, j2);
				if((p.getBlanco()== null) || (p.getNegro()== null)){
					JOptionPane.showMessageDialog(pnlFichas,"Jugador no existe, ingrese otro");
				}
				else {
					lblJ1.setText(p.getBlanco().getNombre());
					lblJ2.setText(p.getNegro().getNombre());
					ArrayList<Trebejo> trebs = p.getFichas();
					for (Trebejo t  :trebs){
						if(t.getColor()){
							model_1.addElement(t);
							}
							else{
							model_2.addElement(t);
							}
					}
				listaBlancas.setModel(model_1);
				listaNegras.setModel(model_2);
				pnlSeleccionPartidas.setVisible(false);
				pnlFichas.setVisible(true);
				}
						/*if (p!=null){
						lblJ1.setText(p.getBlanco().getNombre());
						lblJ2.setText(p.getNegro().getNombre());}
						else{
							lblJ1.setText("");
							lblJ2.setText("");
						}*/
			}
		});
		
		btnJugar.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnJugar.setBounds(36, 178, 359, 34);
		pnlSeleccionPartidas.add(btnJugar);
		
		//Y eseto???
		JButton btnBuscarOponentes = new JButton("Buscar Oponentes");
		btnBuscarOponentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnBuscarOponentes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Método privado!!
				int dni= Integer.parseInt(txtDniBlanco.getText());
				txtAreaPartidas.setText("");
				ArrayList<Partida> pts= cp.buscarPartidas(dni);
				for( Partida p: pts){
				//Me parece que sería mucho mejor si usaramos un JList, en lugar de un TextArea.
				txtAreaPartidas.append("Dni Blanco  : "+
				p.getBlanco().getDni()+"       Dni Negro: "+p.getNegro().getDni()+"\n");
				}
			}
		});
		btnBuscarOponentes.setBounds(243, 31, 133, 23);
		pnlSeleccionPartidas.add(btnBuscarOponentes);
		
	}
}

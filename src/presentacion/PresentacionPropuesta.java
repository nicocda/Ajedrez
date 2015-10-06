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
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.Component;

public class PresentacionPropuesta extends JFrame {

	private JPanel contentPane, pnlFichas, pnlSeleccionPartidas;
	private JList listaBlancas, listaNegras; 
	private JTextField txtDniBlanco;
	private JTextField txtMovX;
	private JTextField txtMovY;
	private JLabel lblJ1, lblJ2;
	private ControladorPartida cp = new ControladorPartida();
	private Partida p=null;
	private JTextField txtNuevaPartida;
	private JScrollPane scrPanelSeleccionOponentes;
	private JPanel pnlSeleccionOponentes;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PresentacionPropuesta frame = new PresentacionPropuesta();
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
	public PresentacionPropuesta() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 494, 471);
		
		//Panel Principal
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Panel selecci�n de partidas
		pnlSeleccionPartidas = new JPanel();
		pnlSeleccionPartidas.setBounds(5, 5, 473, 427);
		contentPane.add(pnlSeleccionPartidas);
		pnlSeleccionPartidas.setLayout(null);
		
		//Panel movimiento de fichas
		pnlFichas = new JPanel();
		pnlFichas.setLayout(null);
		pnlFichas.setBounds(0, 0, 478, 432);
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
		
		listaBlancas = new JList();
		DefaultListModel model_1 = new DefaultListModel();
		listaBlancas.setBounds(38, 61, 135, 326);
		pnlFichas.add(listaBlancas);
		
		listaNegras = new JList();
		DefaultListModel model_2 = new DefaultListModel();
		listaNegras.setBounds(257, 61, 135, 326);
		pnlFichas.add(listaNegras);
		
		txtMovX = new JTextField();
		txtMovX.setColumns(10);
		txtMovX.setBounds(139, 398, 53, 20);
		pnlFichas.add(txtMovX);
		
		txtMovY = new JTextField();
		txtMovY.setColumns(10);
		txtMovY.setBounds(202, 398, 53, 20);
		pnlFichas.add(txtMovY);
		
		JLabel lblPosicionFinal = new JLabel("Posicion Final: ");
		lblPosicionFinal.setBounds(38, 398, 104, 20);
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
		btnMover.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				//Todo esto ser�a ideal que est� en un m�todo aparte, privado (Cuando hicimos lo de 
				//windows form de mecca, en clases, me acuerdo que una de las cosas que me corrigio fue eso.
				pnlSeleccionPartidas.setVisible(true);
				pnlFichas.setVisible(false);
				
				int posX =Integer.parseInt( txtMovX.getText());
				int posY =Integer.parseInt( txtMovY.getText());
				Trebejo trebSelecc = (Trebejo) listaBlancas.getSelectedValue();
				if(trebSelecc!=null)
				{
					int estado = cp.mover(posX, posY, trebSelecc, p);
					
				} 
				else
				{
					JOptionPane.showMessageDialog(pnlFichas,"trebejo no existe");
				}
				model_1.clear();
				model_2.clear();
			}
				
		});
		
		btnMover.setBounds(292, 398, 89, 23);
		pnlFichas.add(btnMover);
		
		
		
		//Elementos del panel "Selecci�n de partidas"
		txtDniBlanco = new JTextField();
		txtDniBlanco.setBounds(280, 32, 183, 20);
		pnlSeleccionPartidas.add(txtDniBlanco);
		txtDniBlanco.setColumns(10);
		
		JLabel lblDniBlanco = new JLabel("Ingrese DNI: ");
		lblDniBlanco.setBounds(191, 35, 79, 14);
		pnlSeleccionPartidas.add(lblDniBlanco);
		
		JButton btnNuevaPartida = new JButton("Nueva Partida");
		btnNuevaPartida.setBounds(10, 393, 259, 23);
		pnlSeleccionPartidas.add(btnNuevaPartida);
		
		txtNuevaPartida = new JTextField();
		txtNuevaPartida.setBounds(280, 394, 183, 20);
		pnlSeleccionPartidas.add(txtNuevaPartida);
		txtNuevaPartida.setColumns(10);

		
		//Ahora va a haber un bot�n para crear una nueva partida
		btnNuevaPartida.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				//Selecciono los 2 dni y cargo la nueva partida.
				int j1 =Integer.parseInt(txtDniBlanco.getText());
				int j2 =Integer.parseInt(txtNuevaPartida.getText());
				p = cp.cargarPartida(j1, j2);
				if((p.getBlanco() == null) || (p.getNegro() == null))
				{
					JOptionPane.showMessageDialog(pnlFichas,"Jugador no existe, ingrese otro");
				}
				else 
				{
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
			}
		});
		
		
		JButton btnBuscarOponentes = new JButton("Buscar Oponentes");
		
		//Si los paneles no son propiedades privadas no puedo entrar a modificarlos en el evento (no se por qu�)
		//Panel scrolleable, donde apareceran las partidas del jugador.
		scrPanelSeleccionOponentes = new JScrollPane();
		pnlSeleccionOponentes = new JPanel();
		

		
		btnBuscarOponentes.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				//Una pavada: para que los paneles se muestren despues de que el usuario le da al bot�n...
				scrPanelSeleccionOponentes.setBounds(10, 129, 453, 195);
				pnlSeleccionPartidas.add(scrPanelSeleccionOponentes);

				scrPanelSeleccionOponentes.setViewportView(pnlSeleccionOponentes);
				pnlSeleccionOponentes.setLayout(new GridLayout(0, 1, 0, 0));
				
				//Esto es para que cada vez que vuelvo a apretar el bot�n de buscar un jugador se haga un refresh
				pnlSeleccionOponentes.removeAll();

				//Busco los DNIs de los oponentes, los muestro en el panel.
				int dni= Integer.parseInt(txtDniBlanco.getText());
				ArrayList<Integer> dniOponentes = cp.buscarOponentes(dni);
				
				//Para mostrarlos recorro toda la colecci�n y creo un nuevo bot�n con el texto igual al dni
				for (int i=0; i<dniOponentes.size(); i++)
				{
					pnlSeleccionOponentes.add(new JButton(dniOponentes.get(i).toString()));
					//A ese boton lo guardo en una variable caste�ndo el componenente que tiene el panel
					JButton a =(JButton)pnlSeleccionOponentes.getComponent(i);
					//Y le agrego un listener. Cuando apriete uno de esos botones directamente me lleva a la partida
					pnlSeleccionOponentes.getComponent(i).addMouseListener(new MouseAdapter() 
					{
						@Override
						public void mouseClicked(MouseEvent e) 
						{
							int j1 =Integer.parseInt(txtDniBlanco.getText());
							int j2 = Integer.parseInt(a.getText());
							
							p = cp.cargarPartida(j1, j2);
							if((p.getBlanco() == null) || (p.getNegro() == null))
							{
								JOptionPane.showMessageDialog(pnlFichas,"Jugador no existe, ingrese otro");
							}
							else 
							{
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
						}
					});
				}
			}
		});
		
		btnBuscarOponentes.setBounds(10, 63, 453, 23);
		pnlSeleccionPartidas.add(btnBuscarOponentes);
		
		

		
	}
	
}
package negocio;
import java.util.ArrayList;

import datos.*;
import entidades.Jugador;
import entidades.Partida;
import entidades.Trebejo;

public class ControladorPartida 
	{
		CatalogoPartidaPropuesto cp = new CatalogoPartidaPropuesto();
		CatalogoTrebejosPropuesto ct = new CatalogoTrebejosPropuesto();
		CatalogoJugadoresPropuesto cj = new CatalogoJugadoresPropuesto();
	
		public Partida cargarPartida(int dni1, int dni2) 
		{
			if (!cp.existePartida(dni1, dni2))
			{
				cp.agregarPartida(dni1, dni2);
				ct.addTrebejos(dni1, dni2);
			}
			Partida p = cp.buscarUnaPartida(dni1, dni2);
			return p;
	
		}

	public ArrayList<Integer> buscarOponentes(int dni)
	{
		ArrayList<Integer> listaOponentes = cp.buscarOponente(dni);
		return(listaOponentes);
	}
	
	
	public int mover(int posX, int posY, Trebejo treb, Partida part){
		boolean encontroTrebejo = false;
		for  (Trebejo t : part.getFichas())
		{
			if(t.getPosX()== posX && t.getPosY()==posY)
			{	
				encontroTrebejo=true;
				if (t.getColor() == treb.getColor())
				{
					//No puedo mover
					return 1 ;
				}
				//Como
				else 
				{
					boolean posicionOcupada=true;
					if(treb.movimientoPermitido(posX, posY, posicionOcupada))
					{
						part.getFichas().remove(t);
						ct.removeBD(t);
						int pos= this.buscarPosicion(treb,part);
						treb.setPosX(posX);
						treb.setPosY(posY);
						part.getFichas().set(pos, treb);
						ct.updateBD(posX, posY, treb);
						boolean turno = part.getTurno();
						if(turno)
							part.setTurno(false);
						else part.setTurno(true);
					}
					return 2;
				}
			}
			
		};
			if(!encontroTrebejo)
			{	
				boolean posicionOcupada=false;
				if(treb.movimientoPermitido(posX, posY, posicionOcupada))
						{
					int pos= this.buscarPosicion(treb,part);
					treb.setPosX(posX);
					treb.setPosY(posY);
					part.getFichas().set(pos, treb);
					ct.updateBD(posX, posY, treb);
					boolean turno = part.getTurno();
					if(turno)
						part.setTurno(false);
					else part.setTurno(true);
						}
				return 3 ;
			}
			else return 0;
		}

	private int buscarPosicion(Trebejo treb, Partida part) {
		int i= 0;
		for(Trebejo t : part.getFichas())
		{
			if(t.getPosX()==treb.getPosX() && t.getPosY() == treb.getPosY())
			{
				i=part.getFichas().indexOf(t);
			}
		}
		return i;
	}
		
	}




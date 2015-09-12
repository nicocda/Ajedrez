package datos;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import conexion.ConexionPropuesta;
import entidades.Alfil;
import entidades.Caballo;
import entidades.Peon;
import entidades.Reina;
import entidades.Rey;
import entidades.Torre;
import entidades.Trebejo;

public class CatalogoTrebejosPropuesto
{
	private ArrayList<Trebejo> listaTrebejos;

	
	public ArrayList<Trebejo> getListaTrebejos()
	{
		return listaTrebejos;
	}
	
	//Busco los trebejos correspondiente a 2 jugadores (una partida)
	public void buscarTrebejos(int blanco, int negro)
	{
		listaTrebejos = new ArrayList<Trebejo>();
		String sql="select tipo, posx, posy, color from partida where dni1="+Integer.toString(blanco)+" and dni="+Integer.toString(negro);
		Statement sentencia=null;
		ResultSet rs=null;
		
		try 
		{			
			sentencia= ConexionPropuesta.getInstancia().getConn().createStatement();
			rs= sentencia.executeQuery(sql);
			while (rs.next())
			{			
				Trebejo t;
				char tipo = rs.getString("tipo").charAt(0);
				int posX = rs.getInt("posx");
				int posY = rs.getInt("posy");
				boolean color = rs.getBoolean("color");
				
				//Según el tipo se crean (con sus respectivos constructores) cada pieza
				
				switch(tipo)
				{
				case 'P' : t= new Peon(tipo, posX, posY, color, blanco, negro);
							break;
				case 'T' : t= new Torre(tipo, posX, posY, color, blanco, negro);
							break;
				case 'C' : t= new Caballo(tipo, posX, posY, color, blanco, negro);
							break;
				case 'A' : t= new Alfil(tipo, posX, posY, color, blanco, negro);
							break;
				case 'R' : t= new Rey(tipo, posX, posY, color, blanco, negro);
							break;
				case 'D' : t=new Reina(tipo, posX, posY, color, blanco, negro);
							break;
				default: t = null;
							break;
				}
				if (t!= null)
					listaTrebejos.add(t);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
				if(sentencia!=null && !sentencia.isClosed())
				{
					sentencia.close();
				}
				ConexionPropuesta.getInstancia().CloseConn();
			}
			catch (SQLException sqle)
			{
				sqle.printStackTrace();
			}
		}	
	}
}
	

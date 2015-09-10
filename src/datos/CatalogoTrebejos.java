package datos;
import java.sql.*;
import java.util.ArrayList;
import entidades.*;

import conexion.DataConnection;
import entidades.Trebejo;
public class CatalogoTrebejos {

	public void addTrebejos(int j1, int j2) throws SQLException {
		DataConnection con = new DataConnection();
		
		for(int i=1;i<=8;i++)
		{
			String query="INSERT INTO `ajedrez`.`trebejos` (`tipo`,`posX`,`posY`,`color`,`dni1`,`dni2`) VALUES('P',"+i+",2,true,"+j1+","+j2+"),('P',"+i+",7,false,"+j1+","+j2+");";
			con.setQuery(query);
		}
		
		String query="INSERT INTO `ajedrez`.`trebejos` (`tipo`,`posX`,`posY`,`color`,`dni1`,`dni2`) VALUES('A',3,1,true,"+j1+","+j2+"),('A',6,1,true,"+j1+","+j2+"),('A',3,8,false,"+j1+","+j2+"),('A',6,8,false,"+j1+","+j2+"),"
																										+ "('C',2,1,true,"+j1+","+j2+"),('C',7,1,true,"+j1+","+j2+"),('C',2,8,false,"+j1+","+j2+"),('C',7,8,false,"+j1+","+j2+"),"
																										+ "('T',8,1,true,"+j1+","+j2+"),('T',1,1,true,"+j1+","+j2+"),('T',1,8,false,"+j1+","+j2+"),('T',8,8,false,"+j1+","+j2+"),"
																										+ "('D',4,1,true,"+j1+","+j2+"),('D',4,8,false,"+j1+","+j2+"),"
																										+ "('R',5,1,true,"+j1+","+j2+"),('R',5,8,false,"+j1+","+j2+")";
		con.setQuery(query);
		
	}

	public ArrayList<Trebejo> buscarTrebejos(int j1, int j2) throws SQLException {
		DataConnection con = new DataConnection();
		ArrayList<Trebejo> array = new ArrayList<Trebejo>();
		String query= "select * from trebejos where dni1="+j1+" and dni2="+j2;
		ResultSet resultado = con.getQuery(query);
		while(resultado.next()){
			char tipo = resultado.getString(1).charAt(0);
			int posX = resultado.getInt(2);
			int posY = resultado.getInt(3);
			boolean color = resultado.getBoolean(4);
			Trebejo t = null;
			switch(tipo){
				case 'P' : t= new Peon(tipo,posX,posY,color,j1,j2);
							break;
				case 'T' : t= new Torre(tipo,posX,posY,color,j1,j2);
							break;
				case 'C' : t= new Caballo(tipo,posX,posY,color,j1,j2);
							break;
				case 'A' : t= new Alfil(tipo,posX,posY,color,j1,j2);
							break;
				case 'R' : t= new Rey(tipo,posX,posY,color,j1,j2);
							break;
				case 'D' : t=new Reina(tipo,posX,posY,color,j1,j2);
							break;
	
			}
				
			if (t!=null)array.add(t);
			
		}

		
		return array;
	}

}

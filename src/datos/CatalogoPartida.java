package datos;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.DataConnection;

public class CatalogoPartida {

	public boolean buscarPartida(int j1, int j2) throws SQLException
	{
	DataConnection con = new DataConnection();
	ResultSet resultado = null;
	resultado = con.getQuery("select * from partida where (blanco="+j1+" and negro="+j2+") or (blanco="+j2+" and negro="+j1+") ");
	boolean bool = resultado.next();
	return (bool);
	}
	
	public void addPartida(int j1, int j2){
		
		DataConnection con = new DataConnection();
		con.setQuery("INSERT INTO `ajedrez`.`partida`(`blanco`,`negro`)	VALUES("+j1+","+j2+")");
		
	}
	
}

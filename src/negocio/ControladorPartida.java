package negocio;
import java.sql.SQLException;

import datos.*;

public class ControladorPartida {
	
	public void crearPartida(int j1, int j2)
	{
		CatalogoTrebejos ct = new CatalogoTrebejos();
		CatalogoPartida cp = new CatalogoPartida();
		cp.addPartida(j1,j2);
		try {
			ct.addTrebejos(j1,j2);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

		
	}
		
		public boolean buscarPartida(int j1, int j2) throws SQLException
		{
			CatalogoPartida cp = new CatalogoPartida();
			boolean existo= cp.buscarPartida(j1,j2);
			return (existo);
		}
		
	}



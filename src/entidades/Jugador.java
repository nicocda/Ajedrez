package entidades;

public class Jugador {
	private int dni; 
	private String nombre, apellido;
	
	public Jugador()
	{
	}
	
	public Jugador(int d, String n, String a){
		
		setDni(d);
		setNombre(n);
		setApellido(a);
		
	}
	
	//Get-Set
	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	

}

package es.iesjandula.instituto.models;

public class Alumno
{
	private String dniAlumno;
	private String nombre;
	private String apellidos;
	private String correo;
	private int telefono;

	
	public Alumno() 
	{
		
	}
	
	public String getDniAlumno()
	{
		return dniAlumno;
	}

	
	public void setDniAlumno(String dniAlumno)
	{
		this.dniAlumno = dniAlumno;
	}



	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public String getApellidos()
	{
		return apellidos;
	}

	public void setApellidos(String apellidos)
	{
		this.apellidos = apellidos;
	}

	public String getCorreo()
	{
		return correo;
	}

	public void setCorreo(String correo)
	{
		this.correo = correo;
	}

	public int getTelefono()
	{
		return telefono;
	}

	public void setTelefono(int telefono)
	{
		this.telefono = telefono;
	}
	
	@Override
	public boolean equals(Object objetc)
	{
		if (this == objetc)
			return true;
		if (objetc == null)
			return false;
		if (!(objetc instanceof Alumno))
			return false;
		Alumno alumno = (Alumno) objetc;
		
		return this.dniAlumno.equals(alumno.dniAlumno);
	}
}

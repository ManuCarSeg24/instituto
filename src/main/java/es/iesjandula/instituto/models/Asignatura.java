package es.iesjandula.instituto.models;

public class Asignatura
{
	private String identificar;
	private String nombre;
	private int numeroCreditos;
	private int horas;
	
	public Asignatura()
	{
		
	}

	public String getIdentificar()
	{
		return identificar;
	}

	public void setIdentificar(String identificar)
	{
		this.identificar = identificar;
	}

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public int getNumeroCreditos()
	{
		return numeroCreditos;
	}

	public void setNumeroCreditos(int numeroCreditos)
	{
		this.numeroCreditos = numeroCreditos;
	}

	public int getHoras()
	{
		return horas;
	}

	public void setHoras(int horas)
	{
		this.horas = horas;
	}
	
	
}

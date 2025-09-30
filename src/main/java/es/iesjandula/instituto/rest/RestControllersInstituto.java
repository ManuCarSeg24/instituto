package es.iesjandula.instituto.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.iesjandula.instituto.models.Alumno;
import es.iesjandula.instituto.models.Asignatura;
import es.iesjandula.instituto.models.Matricula;
import es.iesjandula.instituto.utils.MiExcepcion;
import jakarta.annotation.PostConstruct;


@RestController
@RequestMapping("/instituto")
public class RestControllersInstituto
{
	private List<Alumno> alumnos;
	private List<Asignatura> asignaturas;
	private List<Matricula> matriculas;
	{
		
	}
	
	@PostConstruct
	public void init() 
	{
		this.alumnos = new ArrayList<Alumno>();
		this.asignaturas = new ArrayList<Asignatura>();
		this.matriculas = new ArrayList<Matricula>();
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/crearAsignatura", consumes = "application/json")
	public ResponseEntity<?> crearAsignatura(@RequestBody(required=true)Asignatura asignatura)
	{
		try
		{
			if (this.asignaturas.contains(asignatura))
			{
				throw new MiExcepcion("Esta asignatura ya existe");
			}
			
			asignaturas.add(asignatura);
			
			
			
			return ResponseEntity.ok().build();
			
		} catch (MiExcepcion miExcepcion)
		{
			return ResponseEntity.badRequest().body(miExcepcion.getMessage());
		}
		
	}
	@RequestMapping(value = "/modificarAsignatura", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<?> modificarAsignatura(@RequestBody(required = true) Asignatura asignatura)
	{
		try
		{
			int indice = this.asignaturas.indexOf(asignatura);

			if (indice == -1)
			{
				throw new MiExcepcion("No existe la asignatura");
			}

			Asignatura asignaturaLista = this.asignaturas.get(indice);

			asignaturaLista.setNombre(asignatura.getNombre());
			asignaturaLista.setHoras(asignatura.getHoras());
			asignaturaLista.setNumeroCreditos(asignatura.getNumeroCreditos());

			return ResponseEntity.ok().build();
		} 
		catch (MiExcepcion miExcepcion)
		{
			return ResponseEntity.badRequest().body(miExcepcion.getMessage());
		}
	}
	@RequestMapping(value = "/borrarAsignatura/{identificador}", method = RequestMethod.DELETE)
	public ResponseEntity<?> borrarAsignatura(@PathVariable("identificador") String identificador)
	{
		try
		{
			int indiceEncontrado = -1;

			int i = 0;
			while (i < this.asignaturas.size() && indiceEncontrado == -1)
			{
				Asignatura asignatura = this.asignaturas.get(i);

				if (asignatura.getIdAsignatura() == identificador)
				{
					indiceEncontrado = i;
				}
				i++;
			}

			if (indiceEncontrado == -1)
			{
				throw new MiExcepcion("No existe la asignatura");
			}

			this.asignaturas.remove(indiceEncontrado);

			return ResponseEntity.ok().build();
		} 
		catch (MiExcepcion miExcepcion)
		{
			return ResponseEntity.badRequest().body(miExcepcion.getMessage());
		}
	}
	@RequestMapping(method = RequestMethod.POST, value="/crearAlumno", consumes = "application/json")
	public ResponseEntity<?> crearAlumno(@RequestBody(required=true)Alumno alumno)
	{
		try
		{
			if (this.alumnos.contains(alumno))
			{
				throw new MiExcepcion("Esta alumno ya existe.");
			}
		
			alumnos.add(alumno);
			
			
			
			return ResponseEntity.ok().build();
			
		} catch (MiExcepcion miExcepcion)
		{
			return ResponseEntity.badRequest().body(miExcepcion.getMessage());
		}
		
	}
	@RequestMapping(value = "/modificarAlumno", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<?> modificarAlumno(@RequestBody(required = true) Alumno alumno)
	{
		try
		{
			int indice = this.alumnos.indexOf(alumno);

			if (indice == -1)
			{
				throw new MiExcepcion("No existe el alumno");
			}

			Alumno alumnoLista = this.alumnos.get(indice);

			alumnoLista.setNombre(alumno.getNombre());
			alumnoLista.setApellidos(alumno.getApellidos());
			alumnoLista.setCorreo(alumno.getCorreo());
			alumnoLista.setTelefono(alumno.getTelefono());
			return ResponseEntity.ok().build();
		} 
		catch (MiExcepcion miExcepcion)
		{
			return ResponseEntity.badRequest().body(miExcepcion.getMessage());
		}
	}
	@RequestMapping(value = "/borrarAlumno/{dni}", method = RequestMethod.DELETE)
	public ResponseEntity<?> borrarAlumno(@PathVariable("identificador") String DNI)
	{
		try
		{
			int indiceEncontrado = -1;

			int i = 0;
			while (i < this.alumnos.size() && indiceEncontrado == -1)
			{
				Alumno alumno = this.alumnos.get(i);

				if (alumno.getDniAlumno() == DNI)
				{
					indiceEncontrado = i;
				}
				i++;
			}

			if (indiceEncontrado == -1)
			{
				throw new MiExcepcion("No existe el alumno");
			}

			this.alumnos.remove(indiceEncontrado);

			return ResponseEntity.ok().build();
		} 
		catch (MiExcepcion miExcepcion)
		{
			return ResponseEntity.badRequest().body(miExcepcion.getMessage());
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/crearMatricula", consumes = "application/json")
	public ResponseEntity<?> crearMatricula(@RequestBody(required=true)Matricula matricula)
	{
		try
		{
			int indiceAlumno = this.buscarAlumno(matricula.getDniAlumno());
			int indiceAsignatura = this.buscarAsignatura(matricula.getIdAsignatura());
			
			if(this.matriculas.contains(matricula))
			{
				throw new MiExcepcion("Ya exite la matricula.");
			}
			
			this.matriculas.add(new Matricula(this.alumnos.get(indiceAlumno), this.asignaturas.get(indiceAsignatura)));
			return ResponseEntity.ok().build();

		} 
		catch (MiExcepcion miExcepcion)
		{
			return ResponseEntity.badRequest().body(miExcepcion.getMessage());
		}
	}
	
	@RequestMapping(value = "/matricula", method = RequestMethod.GET)
	public ResponseEntity<?> listaMatricula()
	{
		return ResponseEntity.ok(this.matriculas);
	}

	private int buscarAsignatura(String idAsignatura) throws MiExcepcion
	{
		int asignaturaEncontrada = -1;
		int indiceAsignatura = 0;
		
		while (indiceAsignatura < this.asignaturas.size() && asignaturaEncontrada == -1)
		{
			Asignatura asignatura = this.asignaturas.get(indiceAsignatura);
			
			if(asignatura.getIdAsignatura().equals(idAsignatura))
			{
				asignaturaEncontrada = indiceAsignatura;
			}
			indiceAsignatura++;
		}
		if (asignaturaEncontrada == -1)
		{
		    throw new MiExcepcion("La asignatura con ID " + idAsignatura + " no existe.");
		}
		return asignaturaEncontrada;
	}

	private int buscarAlumno(String dniAlumno) throws MiExcepcion
	{
		int alumnoEncontrado = -1;
		int indiceAlumno = 0;
		
		while (indiceAlumno < this.alumnos.size() && alumnoEncontrado == -1)
		{
			Alumno alumno = this.alumnos.get(indiceAlumno);
			
			if(alumno.getDniAlumno().equals(dniAlumno))
			{
				alumnoEncontrado = indiceAlumno;
			}
			indiceAlumno++;
		}
		if (alumnoEncontrado == -1)
		{
		    throw new MiExcepcion("El alumno con DNI " + dniAlumno + " no existe.");
		}
		return alumnoEncontrado;
	}
	
}

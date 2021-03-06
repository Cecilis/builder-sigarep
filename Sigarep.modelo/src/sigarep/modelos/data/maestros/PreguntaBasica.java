package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Clase PreguntasBasicas
 * 
 * @author Equipo Builder 
 * @version 1.0
 * @since 15/12/2013
 * @last 08/05/2014
 */
@Entity
// anotaci�n indica que el JavaBean es una entidad persistente
@Access(AccessType.FIELD)
@Table(name = "pregunta_basica")
public class PreguntaBasica implements Serializable {
	private static final long serialVersionUID = 1L;

	// Atributos de la clase 
	@Id
	// Clave principal de la clase
	@Column(name = "id_pregunta_basica", unique = true, nullable = false)
	private Integer idPreguntaBasica;

	@Column(nullable = false)
	private Boolean estatus;

	@Column(nullable = false, length = 255)
	private String pregunta;

	@Column(nullable = false, length = 255)
	private String respuesta;

	// Constructor por defecto
	public PreguntaBasica() {
	}

	/**
	 * Constructor PreguntaBasica
	 * 
	 * @param idPreguntaBasica, pregunta, respuesta, estatus
	 */
	public PreguntaBasica(Integer idPreguntaBasica, String pregunta,
			String respuesta, Boolean estatus) {
		super();
		this.idPreguntaBasica = idPreguntaBasica;
		this.pregunta = pregunta;
		this.respuesta = respuesta;
		this.estatus = estatus;
	}

	// M�todos Set y Get

	public Integer getIdPreguntaBasica() {
		return this.idPreguntaBasica;
	}

	public void setIdPreguntaBasica(Integer idPreguntaBasica) {
		this.idPreguntaBasica = idPreguntaBasica;
	}

	public Boolean getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public String getPregunta() {
		return this.pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	// Fin M�todos Set y Get
}
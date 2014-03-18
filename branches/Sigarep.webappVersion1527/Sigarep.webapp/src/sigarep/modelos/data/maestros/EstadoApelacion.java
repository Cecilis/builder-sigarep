package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Clase EstadoApelacion Registra y Modifica el Estado de Apelaci�n asociado a
 * Instancia Apelada
 * 
 * @author BUILDER
 * @version 1
 * @since 15/12/2013
 */

@Entity
@Access(AccessType.FIELD)
// anotaci�n indica que el JavaBean es una entidad persistente
@Table(name = "estado_apelacion")
public class EstadoApelacion implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor Estado apelacion
	 * 
	 * @param idEstadoApelacion
	 *            , nombreEstado, descripcion, estatus
	 * @return Constructor lleno
	 */
	public EstadoApelacion(Integer idEstadoApelacion, String nombreEstado,
			String descripcion, Boolean estatus) {
		super();
		this.idEstadoApelacion = idEstadoApelacion;
		this.nombreEstado = nombreEstado;
		this.descripcion = descripcion;
		this.estatus = estatus;
	}

	// Atributos de la clase
	@Id
	// Clave principal de la clase
	@Column(name = "id_estado_apelacion", unique = true, nullable = false)
	private Integer idEstadoApelacion;

	@Column(name = "estatus", nullable = false)
	private Boolean estatus;

	@Column(name = "nombre_estado", length = 100)
	private String nombreEstado;

	@Column(name = "descripcion", length = 255)
	private String descripcion;

	// bi-directional many-to-one association to InstanciaApelada
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_instancia_apelada", nullable = false)
	private InstanciaApelada instanciaApelada;

	public EstadoApelacion() {
	}

	// M�todos GET y SET

	public Boolean getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getIdEstadoApelacion() {
		return idEstadoApelacion;
	}

	public void setIdEstadoApelacion(Integer idEstadoApelacion) {
		this.idEstadoApelacion = idEstadoApelacion;
	}

	public String getNombreEstado() {
		return nombreEstado;
	}

	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

	public InstanciaApelada getInstanciaApelada() {
		return instanciaApelada;
	}

	public void setInstanciaApelada(InstanciaApelada instanciaApelada) {
		this.instanciaApelada = instanciaApelada;
	}

}
package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;
import sigarep.herramientas.Archivo;
import java.util.Date;

/**
 * Clase Banner (Banner de la pagina principal
 * del website)
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 15/12/2013
 * @last 08/05/2014
 */

@Entity
@Access(AccessType.FIELD)
// Anotaci�n indica que el JavaBean es una entidad persistente
@Table(name = "banner")
public class Banner implements Serializable {
	private static final long serialVersionUID = 1L;

	// Atributos de la clase
	@Id
	// Clave principal de la clase
	@Column(name = "id_imagen", unique = true, nullable = false)
	private Integer idImagen;

	@Column(length = 255)
	private String descripcion;

	@Column(length = 255)
	private String enlace;

	@Column(nullable = false)
	private Boolean estatus;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_vencimiento")
	private Date fechaVencimiento;

	@Column(length = 60)
	private String titulo;

	@Embedded()
	private Archivo fotoBanner;

	// Constructor por defecto
	public Banner() {
	}

	/**
	 * Constructor Banner
	 * 
	 * @param idImagen, descripcion, enlace, fechaVencimiento, titulo, fotoBanner,
	 *        estatus
	 */
	public Banner(Integer idImagen, String descripcion, String enlace,
			Date fechaVencimiento, String titulo, Archivo fotoBanner,
			Boolean estatus) {
		super();
		this.idImagen = idImagen;
		this.descripcion = descripcion;
		this.enlace = enlace;
		this.fechaVencimiento = fechaVencimiento;
		this.titulo = titulo;
		this.fotoBanner = fotoBanner;
		this.estatus = estatus;
	}

	// M�todos Set y Get
	public Integer getIdImagen() {
		return this.idImagen;
	}

	public void setIdImagen(Integer idImagen) {
		this.idImagen = idImagen;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEnlace() {
		return this.enlace;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

	public Boolean getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public Date getFechaVencimiento() {
		return this.fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Archivo getFotoBanner() {
		return fotoBanner;
	}

	public void setFotoBanner(Archivo fotoBanner) {
		this.fotoBanner = fotoBanner;
	}
	// Fin M�todos Set y Get
}
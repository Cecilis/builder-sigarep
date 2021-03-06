package sigarep.modelos.data.reportes;
import java.util.Date;

/** Clase ListaInformeEstructuradoResultadosApelacion
 * @author Equipo Builder   
 * @version 1.0
 * @since 20/12/2013
 * @last 09/05/2014
 */
public class ListaInformeEstructuradoResultadosApelacion {
	// Atributos de la clase
	private String cedulaEstudiante;
	private String primerNombre;
	private String primerApellido;
	private String nombrePrograma;
	private String veredicto;
	private String nroSesion;
	private Date fechaSesion;
	private String tipoSesion;
	
	// constructor por defecto
	public ListaInformeEstructuradoResultadosApelacion() {
		
	}
	
	/**
	 * Constructor ListaEspecialEstudiantesSancionadosApelaciones
	 * @param cedulaEstudiante, primerNombre, primerApellido, nombrePrograma, veredicto, nroSesion, fechaSesion, tipoSesion
	 */
	public ListaInformeEstructuradoResultadosApelacion(String cedulaEstudiante,String primerNombre, String primerApellido, String nombrePrograma, String veredicto, String nroSesion, Date fechaSesion, String tipoSesion) {
		this.cedulaEstudiante= cedulaEstudiante;
		this.primerNombre= primerNombre;
		this.primerApellido= primerApellido;
		this.nombrePrograma= nombrePrograma;
		this.veredicto= veredicto;
		this.nroSesion= nroSesion;
		this.fechaSesion= fechaSesion;
		this.tipoSesion= tipoSesion;
	}

	//  M�todos Set y Get
	public String getCedulaEstudiante() {
		return cedulaEstudiante;
	}

	public void setCedulaEstudiante(String cedulaEstudiante) {
		this.cedulaEstudiante = cedulaEstudiante;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getNombrePrograma() {
		return nombrePrograma;
	}

	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}

	public String getVeredicto() {
		return veredicto;
	}

	public void setVeredicto(String veredicto) {
		this.veredicto = veredicto;
	}

	public String getNroSesion() {
		return nroSesion;
	}

	public void setNroSesion(String nroSesion) {
		this.nroSesion = nroSesion;
	}

	public Date getFechaSesion() {
		return fechaSesion;
	}

	public void setFechaSesion(Date fechaSesion) {
		this.fechaSesion = fechaSesion;
	}

	public String getTipoSesion() {
		return tipoSesion;
	}

	public void setTipoSesion(String tipoSesion) {
		this.tipoSesion = tipoSesion;
	}// Fin M�todos Set y Get

}

package sigarep.viewmodels.reportes;

import java.util.LinkedList;
import java.util.List;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.reportes.ReportConfig;
import sigarep.modelos.data.reportes.ReportType;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.servicio.transacciones.ServicioApelacionEstadoApelacion;
import sigarep.modelos.servicio.transacciones.ServicioEstudianteSancionado;

/**
 * VM Detalle Historial Estudiante.
 * 
 * @author Equipo Builder
 * @version 2.5.2
 * @since 23/01/2014
 * @last 08/05/2014
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMDetalleHistorialEstudiante {
	// --------------------------Servicios------------------------------
	@WireVariable
	private ServicioEstudianteSancionado servicioestudiantesancionado;
	@WireVariable
	private ServicioApelacionEstadoApelacion servicioapelacionestadoapelacion;
	// --------------------------Variables de Control-------------------
	@Wire("#modalDialog")
	private Window window;
	private String cedula;
	private String codigoLapso;
	private String nombreEstudiante;
	private String nombreSancion;
	private String apellidoEstudiante;
	private Integer instancia;
	private String motivosEstudiante;
	private String caso;
	ReportType reportType = null;
	private ReportConfig reportConfig = null;
	String ruta = "/WEB-INF/sigarepReportes/informes/RHistorialEstudiante.jasper";
	// --------------------------Variables lista------------------------
	private List<ApelacionEstadoApelacion> apelacionestudiante = new LinkedList<ApelacionEstadoApelacion>();
	private List<ApelacionEstadoApelacion> apelacionestudianteinstancia2 = new LinkedList<ApelacionEstadoApelacion>();
	private List<ApelacionEstadoApelacion> apelacionestudianteinstancia3 = new LinkedList<ApelacionEstadoApelacion>();
	private List<EstudianteSancionado> estudiante = new LinkedList<EstudianteSancionado>();
	private List<SolicitudApelacion> apelacion = new LinkedList<SolicitudApelacion>();
	// --------------------------Variables Objeto-----------------------
	private SolicitudApelacion apelacionseleccionada;
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();

	// M�todos Set y Get
	public ReportConfig getReportConfig() {
		return reportConfig;
	}

	public String getCaso() {
		return caso;
	}

	public void setCaso(String caso) {
		this.caso = caso;
	}

	public String getMotivosEstudiante() {
		return motivosEstudiante;
	}

	public void setMotivosEstudiante(String motivosEstudiante) {
		this.motivosEstudiante = motivosEstudiante;
	}

	public ReportType getReportType() {
		return reportType;
	}

	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}

	public Integer getInstancia() {
		return instancia;
	}

	public List<EstudianteSancionado> getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(List<EstudianteSancionado> estudiante) {
		this.estudiante = estudiante;
	}

	public void setInstancia(Integer instancia) {
		this.instancia = instancia;
	}

	public List<ApelacionEstadoApelacion> getApelacionestudianteinstancia2() {
		return apelacionestudianteinstancia2;
	}

	public void setApelacionestudianteinstancia2(
			List<ApelacionEstadoApelacion> apelacionestudianteinstancia2) {
		this.apelacionestudianteinstancia2 = apelacionestudianteinstancia2;
	}

	public List<ApelacionEstadoApelacion> getApelacionestudianteinstancia3() {
		return apelacionestudianteinstancia3;
	}

	public void setApelacionestudianteinstancia3(
			List<ApelacionEstadoApelacion> apelacionestudianteinstancia3) {
		this.apelacionestudianteinstancia3 = apelacionestudianteinstancia3;
	}

	public List<ApelacionEstadoApelacion> getApelacionestudiante() {
		return apelacionestudiante;
	}

	public void setApelacionestudiante(
			List<ApelacionEstadoApelacion> apelacionestudiante) {
		this.apelacionestudiante = apelacionestudiante;
	}

	public SolicitudApelacion getApelacionseleccionada() {
		return apelacionseleccionada;
	}

	public void setApelacionseleccionada(
			SolicitudApelacion apelacionseleccionada) {
		this.apelacionseleccionada = apelacionseleccionada;
	}

	public List<SolicitudApelacion> getApelacion() {
		return apelacion;
	}

	public void setApelacion(List<SolicitudApelacion> apelacion) {
		this.apelacion = apelacion;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCodigoLapso() {
		return codigoLapso;
	}

	public void setCodigoLapso(String codigoLapso) {
		this.codigoLapso = codigoLapso;
	}

	public String getApellidoEstudiante() {
		return apellidoEstudiante;
	}

	public void setApellidoEstudiante(String apellidoEstudiante) {
		this.apellidoEstudiante = apellidoEstudiante;
	}

	public String getNombreEstudiante() {
		return nombreEstudiante;
	}

	public void setNombreEstudiante(String nombreEstudiante) {
		this.nombreEstudiante = nombreEstudiante;
	}

	public String getNombreSancion() {
		return nombreSancion;
	}

	public void setNombreSancion(String nombreSancion) {
		this.nombreSancion = nombreSancion;
	}

	// Fin M�todos Set y Get

	/**
	 * Inicializaci�n
	 * 
	 * @param init
	 * @return Carga de Variables y m�todos inicializados
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Init
	public void init(
			@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("apelacionestudiante") List<ApelacionEstadoApelacion> v3,
			@ExecutionArgParam("apelacionestudianteinstancia2") List<ApelacionEstadoApelacion> v4,
			@ExecutionArgParam("apelacionestudianteinstancia3") List<ApelacionEstadoApelacion> v5,
			@ExecutionArgParam("apelacion") List<SolicitudApelacion> v1,
			@ExecutionArgParam("estudiante") List<EstudianteSancionado> v2,
			@ExecutionArgParam("cedula") String v6,
			@ExecutionArgParam("codigoLapso") String v7,
			@ExecutionArgParam("motivosEstudiante") String v8,
			@ExecutionArgParam("caso") String v9) {
		Selectors.wireComponents(view, this, false);
		this.apelacionestudiante = v3;
		this.apelacionestudianteinstancia2 = v4;
		this.apelacionestudianteinstancia3 = v5;
		this.apelacion = v1;
		this.estudiante = v2;
		this.cedula = v6;
		this.codigoLapso = v7;
		this.motivosEstudiante = v8;
		this.caso = v9;
	}

	/**
	 * Buscar solicitud.
	 * 
	 * @param String
	 *            cedula, String codigoLapso, Integer instancia
	 * @return solicitud apelaci�n.
	 * @throws No
	 *             dispara ninguna excepci�n.
	 */
	@Command
	@NotifyChange({ "apelacionestudiante" })
	public void buscarSolicitud(String cedula, String codigoLapso,
			Integer instancia) {
		instancia = 1;
		apelacionestudiante = servicioapelacionestadoapelacion
				.buscarApelacionHistorial(cedula, codigoLapso, instancia);
	}

	/**
	 * Buscar solicitud instancia 2.
	 * 
	 * @param String
	 *            cedula, String codigoLapso, Integer instancia
	 * @return solicitud apelaci�n instancia 2.
	 * @throws No
	 *             dispara ninguna excepci�n.
	 */
	@Command
	@NotifyChange({ "apelacionestudianteinstancia2" })
	public void buscarSolicitudInstancia2(String cedula, String codigoLapso,
			Integer instancia) {
		instancia = 2;
		apelacionestudianteinstancia2 = servicioapelacionestadoapelacion
				.buscarApelacionHistorial(cedula, codigoLapso, instancia);
	}

	/**
	 * Buscar solicitud instancia 3.
	 * 
	 * @param String
	 *            cedula, String codigoLapso, Integer instancia
	 * @return solicitud apelaci�n instancia 3.
	 * @throws No
	 *             dispara ninguna excepci�n.
	 */
	@Command
	@NotifyChange({ "apelacionestudianteinstancia3" })
	public void buscarSolicitudInstancia3(String cedula, String codigoLapso,
			Integer instancia) {
		instancia = 3;
		apelacionestudianteinstancia3 = servicioapelacionestadoapelacion
				.buscarApelacionHistorial(cedula, codigoLapso, instancia);
	}

	/**
	 * Buscar estudiante.
	 * 
	 * @param String
	 *            cedula
	 * @return estudiante.
	 * @throws No
	 *             dispara ninguna excepci�n.
	 */
	@Command
	@NotifyChange({ "estudiante" })
	public void buscarEstudiante(String cedula) {
		estudiante = servicioestudiantesancionado.buscarApelacion(cedula);
	}

	/**
	 * Generar reporte historial estudiante.
	 * 
	 * @param Ninguno
	 * @return Reporte del historial del estudiante sancionado generado en PDF u
	 *         otro tipo de archivo.
	 * @throws Si
	 *             la lista esta vacia no genera el reporte.
	 */
	@Command("GenerarReporteHistorial")
	@NotifyChange({ "reportConfig" })
	public void generarReporte() {
		reportConfig = new ReportConfig(ruta);
		reportConfig.getParameters().put("Titulo", "Historial de Estudiante");
		reportConfig.getParameters().put("codigoLapso", codigoLapso);
		reportConfig.getParameters().put("cedula", cedula);
		reportConfig.getParameters()
				.put("motivosEstudiante", motivosEstudiante);
		reportConfig.getParameters().put("caso", caso);
		reportConfig.getParameters().put("nombre",
				estudiante.get(0).getEstudiante().getPrimerNombre());
		reportConfig.getParameters().put("apellido",
				estudiante.get(0).getEstudiante().getPrimerApellido());
		for (int i = 0; i < apelacionestudiante.size(); i++) {
			String sugerencia = apelacionestudiante.get(i).getSugerencia();
			if (sugerencia == null) {
				apelacionestudiante.get(i).setSugerencia("-----");
			}
		}
		for (int i = 0; i < apelacionestudianteinstancia2.size(); i++) {
			String sugerencia = apelacionestudianteinstancia2.get(i)
					.getSugerencia();
			if (sugerencia == null) {
				apelacionestudianteinstancia2.get(i).setSugerencia("-----");
			}
		}
		for (int i = 0; i < apelacionestudianteinstancia3.size(); i++) {
			String sugerencia = apelacionestudianteinstancia3.get(i)
					.getSugerencia();
			if (sugerencia == null) {
				apelacionestudianteinstancia3.get(i).setSugerencia("-----");
			}
		}
		reportConfig.getParameters().put("Lista",
				new JRBeanCollectionDataSource(apelacionestudiante));
		reportConfig.getParameters().put("ListaInstancia2",
				new JRBeanCollectionDataSource(apelacionestudianteinstancia2));
		reportConfig.getParameters().put("ListaInstancia3",
				new JRBeanCollectionDataSource(apelacionestudianteinstancia3));
		reportConfig.setType(reportType);
	}
}

package sigarep.viewmodels.transacciones;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.Binder;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Window;
import sigarep.herramientas.Documento;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacionPK;
import sigarep.modelos.data.transacciones.AsignaturaEstudianteSancionado;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.Motivo;
import sigarep.modelos.data.transacciones.MotivoPK;
import sigarep.modelos.data.transacciones.RecaudoEntregado;
import sigarep.modelos.data.transacciones.RecaudoEntregadoPK;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.data.transacciones.SolicitudApelacionPK;
import sigarep.modelos.servicio.transacciones.ServicioApelacionEstadoApelacion;
import sigarep.modelos.servicio.transacciones.ServicioAsignaturaEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioMotivo;
import sigarep.modelos.servicio.transacciones.ServicioRecaudoEntregado;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;

/**
 * VM Registrar Recurso jerarquico.
 * Maneja los metodos necesarios para el registro de los datos iniciales 
 * requeridos del estudiante para iniciar el proceso de recurso jer�rquico (tercera  apelaci�n).
 * @author Equipo Builder
 * @version 1.2
 * @since 20/12/2013
 * @last 10/05/2014
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMRegistrarRecursoJerarquico {
	// --------------------------Servicios------------------------------
	@WireVariable
	private ServicioAsignaturaEstudianteSancionado servicioasignaturaestudiantesancionado;
	@WireVariable
	private ServicioRecaudoEntregado serviciorecaudoentregado;
	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion;
	@WireVariable
	private ServicioApelacionEstadoApelacion servicioapelacionestadoapelacion;
	@WireVariable
	private ServicioMotivo serviciomotivo;
	// --------------------------Variables de Control-------------------
	private String sancion;
	private String lapso;
	private String nombres;
	private String apellidos;
	private String cedula;
	private String lapsosConsecutivos;
	private String asignaturaLapsosConsecutivos = "";
	private String labelAsignaturaLapsosConsecutivos;
	private String observacion;
	private String caso;
	private Integer idRecaudo;
	@Wire("#winRegistrarRecursoJerarquico")
	private Window ventana;
	private Documento doc = new Documento();
	private Media media;
	// --------------------------Variables Lista------------------------
	private List<RecaudoEntregado> listaRecaudos = new LinkedList<RecaudoEntregado>();
	private List<AsignaturaEstudianteSancionado> asignaturas;
	private List<SolicitudApelacion> listaSolicitud = new LinkedList<SolicitudApelacion>();
	// --------------------------Variables Objeto-----------------------
	private EstudianteSancionado estudianteSeleccionado;
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	SolicitudApelacionPK solicitudApelacionPK = new SolicitudApelacionPK();
	SolicitudApelacion solicitudApelacion = new SolicitudApelacion();
	ApelacionEstadoApelacionPK apelacionEstadoApelacionPK = new ApelacionEstadoApelacionPK();
	ApelacionEstadoApelacion apelacionEstadoApelacion = new ApelacionEstadoApelacion();
	RecaudoEntregado recaudoEntregado = new RecaudoEntregado();
	RecaudoEntregadoPK recaudoEntregadoPK = new RecaudoEntregadoPK();
	Motivo motivos = new Motivo();
	MotivoPK motivoPK = new MotivoPK();

	// M�todos Set y Get
	public String getNombres() {
		return nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getAsignaturaLapsosConsecutivos() {
		return asignaturaLapsosConsecutivos;
	}

	public String getLabelAsignaturaLapsosConsecutivos() {
		return labelAsignaturaLapsosConsecutivos;
	}

	public String getObservacion() {
		return observacion;
	}

	public String getCaso() {
		return caso;
	}

	public List<RecaudoEntregado> getListaRecaudos() {
		return listaRecaudos;
	}

	public EstudianteSancionado getEstudianteSeleccionado() {
		return estudianteSeleccionado;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setAsignaturaLapsosConsecutivos(
			String asignaturaLapsosConsecutivos) {
		this.asignaturaLapsosConsecutivos = asignaturaLapsosConsecutivos;
	}

	public void setLabelAsignaturaLapsosConsecutivos(
			String labelAsignaturaLapsosConsecutivos) {
		this.labelAsignaturaLapsosConsecutivos = labelAsignaturaLapsosConsecutivos;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public void setCaso(String caso) {
		this.caso = caso;
	}

	public void setListaRecaudos(List<RecaudoEntregado> listaRecaudos) {
		this.listaRecaudos = listaRecaudos;
	}

	public void setEstudianteSeleccionado(
			EstudianteSancionado estudianteSeleccionado) {
		this.estudianteSeleccionado = estudianteSeleccionado;
	}

	public String getLapsosConsecutivos() {
		return lapsosConsecutivos;
	}

	public void setLapsosConsecutivos(String lapsosConsecutivos) {
		this.lapsosConsecutivos = lapsosConsecutivos;
	}

	public Integer getIdRecaudo() {
		return idRecaudo;
	}

	public void setIdRecaudo(Integer idRecaudo) {
		this.idRecaudo = idRecaudo;
	}

	public Documento getDoc() {
		return doc;
	}

	public void setDoc(Documento doc) {
		this.doc = doc;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	// Fin M�todos Set y Get

	/**
	 * inicializaci�n
	 * 
	 * @param init
	 * @return C�digo de inicializaci�n
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Init
	public void init(
			@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("estudianteSeleccionado") EstudianteSancionado v1,
			@ContextParam(ContextType.BINDER) final Binder binder) {
		Selectors.wireComponents(view, this, false);
		this.estudianteSeleccionado = v1;
		cedula = estudianteSeleccionado.getEstudiante().getCedulaEstudiante();
		lapso = estudianteSeleccionado.getLapsoAcademico().getCodigoLapso();
		sancion = estudianteSeleccionado.getSancionMaestro().getNombreSancion();
		lapsosConsecutivos = estudianteSeleccionado.getLapsosAcademicosRp();
		concatenacionNombres();
		concatenacionApellidos();
		buscarRecaudosEntregados(cedula);
		mostrarDatosDeSancion();
		buscarSolicitud(cedula);
		if (listaSolicitud.size() > 0)
			caso = listaSolicitud.get(0).getNumeroCaso();
		else {
			registrarApelacionConMotivos();
			binder.postCommand("closeThis", null);
		}
		media = null;
		doc = new Documento();
	}

	/**
	 * afterCompose. Conecta a los componentes de la vista. Es necesario para
	 * evitar null pointer.
	 * 
	 * @param @ContextParam(ContextType.VIEW) Component view
	 * @return Ninguno.
	 * @throws No
	 *             dispara ninguna excepci�n.
	 */
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	/**
	 * Actualizar Lista de Sancionados
	 * 
	 * @param actualizarListaSancionados
	 * @return La lista de estudiantes en veredicto se actualiza cuando se
	 *         termina ese proceso.
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@GlobalCommand
	public void actualizarListaSancionados() {
		BindUtils.postGlobalCommand(null, null, "buscarSancionados", null);
	}

	/**
	 * registrarApelacionConMotivos.
	 * 
	 * @param Ninguno
	 * @return Ninguno
	 * @throws No
	 *             dispara ninguna excepcion.
	 * 
	 */
	public void registrarApelacionConMotivos() {
		Integer instancia = 3;
		Integer idEstado = 9;
		Integer idMotivoGeneral = 3;
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("estudianteSeleccionado", estudianteSeleccionado);
		map.put("instancia", instancia);
		map.put("idEstado", idEstado);
		map.put("idMotivoGeneral", idMotivoGeneral);
		final Window window = (Window) Executions
				.createComponents(
						"/WEB-INF/sigarep/vistas/transacciones/RegistrarDatosInicialesApelacion.zul",
						null, map);
		window.setMaximizable(true);
		window.doModal();
	}

	/**
	 * Concatenacion nombres.
	 * 
	 * @param Ninguno
	 * @return Devuelve primer y segundo nombre concatenados
	 * @throws No
	 *             dispara ninguna excepci�n.
	 */
	public void concatenacionNombres() {
		nombres = estudianteSeleccionado.getEstudiante().getPrimerNombre()
				+ " "
				+ estudianteSeleccionado.getEstudiante().getSegundoNombre();
	}

	/**
	 * Concatenacion apellidos.
	 * 
	 * @param Ninguno
	 * @return Devuelve primer y segundo apellido concatenados
	 * @throws No
	 *             dispara ninguna excepci�n.
	 */
	public void concatenacionApellidos() {
		apellidos = estudianteSeleccionado.getEstudiante().getPrimerApellido()
				+ " "
				+ estudianteSeleccionado.getEstudiante().getSegundoApellido();

	}

	/**
	 * Buscar Recaudos Entregados.
	 * 
	 * @param String
	 *            cedula
	 * @return Ninguno
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "listaRecaudos" })
	public void buscarRecaudosEntregados(String cedula) {
		List<RecaudoEntregado> listaAux;
		listaRecaudos = serviciorecaudoentregado.buscarRecaudosEntregadosConSoporte(cedula);
		listaAux = serviciorecaudoentregado.buscarRecaudosEntregadosSinSoporte(cedula);
		listaRecaudos.addAll(listaAux);
	}

	/**
	 * closeThis.
	 * 
	 * @param Ninguno
	 * @return Ninguno
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Command
	public void closeThis() {
		ventana.detach();
	}

	/**
	 * Registrar Solicitud Apelacion.
	 * 
	 * @param @ContextParam(ContextType.BINDER) final Binder binder
	 * @return Ninguno
	 * @throws las
	 *             Excepciones ocurren cuando se quiera registrar una Recurso
	 *             Jer�rquico y no se ha cargado la carta
	 */
	@NotifyChange({ "lista", "observacion" })
	@Command
	public void registrarSolicitudApelacion(
			@ContextParam(ContextType.BINDER) final Binder binder) {
		Date fecha = new Date();
		if (observacion == " " || observacion == null) {
			mensajeAlUsuario.advertenciaLlenarCampos();
		} else {
			solicitudApelacionPK.setCedulaEstudiante(cedula);
			solicitudApelacionPK.setCodigoLapso(lapso);
			solicitudApelacionPK.setIdInstanciaApelada(3);
			solicitudApelacion.setId(solicitudApelacionPK);
			solicitudApelacion.setFechaSolicitud(fecha);
			solicitudApelacion.setEstatus(true);
			solicitudApelacion.setNumeroCaso(caso);
			solicitudApelacion.setObservacion(observacion);
			apelacionEstadoApelacionPK.setCedulaEstudiante(cedula);
			apelacionEstadoApelacionPK.setCodigoLapso(lapso);
			apelacionEstadoApelacionPK.setIdInstanciaApelada(3);
			apelacionEstadoApelacionPK.setIdEstadoApelacion(9);
			apelacionEstadoApelacion.setId(apelacionEstadoApelacionPK);
			apelacionEstadoApelacion.setFechaEstado(fecha);
			apelacionEstadoApelacion.setObservacion(observacion);
			motivoPK.setCedulaEstudiante(cedula);
			motivoPK.setCodigoLapso(lapso);
			motivoPK.setIdInstanciaApelada(3);
			motivoPK.setIdTipoMotivo(3);
			motivos.setId(motivoPK);
			motivos.setEstatus(true);
		}
		try {
			serviciosolicitudapelacion.guardar(solicitudApelacion);
			servicioapelacionestadoapelacion.guardar(apelacionEstadoApelacion);
			serviciomotivo.guardarMotivo(motivos);
			mensajeAlUsuario.informacionRegistroCorrecto();
			ventana.detach();
			actualizarListaSancionados();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Descargar Documento.
	 * 
	 * @param @ContextParam(ContextType.COMPONENT) Component componente
	 * @return Ninguno
	 * @throws Ninguna.
	 */
	@Command
	public void descargarDocumento(
			@ContextParam(ContextType.COMPONENT) Component componente) {
		idRecaudo = listaRecaudos.get(0).getId().getIdRecaudo();
		int idRecaudo = Integer.parseInt(componente.getAttribute("idRecaudo")
				.toString());
		for (int j = 0; j < listaRecaudos.size(); j++) {
			if (listaRecaudos.get(j).getId().getIdRecaudo() == idRecaudo)
				Filedownload.save(listaRecaudos.get(j).getSoporte()
						.getDocumento().getContenidoDocumento(), listaRecaudos
						.get(j).getSoporte().getDocumento().getTipoDocumento(),
						listaRecaudos.get(j).getSoporte().getDocumento()
								.getNombreDocumento());
		}
	}

	/**
	 * Buscar Solicitud.
	 * 
	 * @param String
	 *            Cedula
	 * @return Ninguno
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	public void buscarSolicitud(String cedula) {
		listaSolicitud = serviciosolicitudapelacion
				.buscarSolicitudEstudiante(cedula);
	}

	/**
	 * Mostrar Datos de Sancion.
	 * 
	 * @param Ninguno
	 * @return Ninguno
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	private void mostrarDatosDeSancion() {
		if (sancion.equalsIgnoreCase("RR")) {
			asignaturas = servicioasignaturaestudiantesancionado
					.buscarAsignaturaDeSancion(cedula, lapso);
			if (asignaturas != null)
				for (int i = 0; i < asignaturas.size(); i++)
					asignaturaLapsosConsecutivos += asignaturas.get(i)
							.getAsignatura().getNombreAsignatura()
							+ ", ";
			labelAsignaturaLapsosConsecutivos = "Asignatura(s):";
		} else {
			labelAsignaturaLapsosConsecutivos = "Lapsos consecutivos:";
			asignaturaLapsosConsecutivos = estudianteSeleccionado
					.getLapsosAcademicosRp();
		}
	}

	/**
	 * Cancelar
	 * 
	 * @param Ninguno
	 * @return Ninguno
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "observacion" })
	public void cancelar() {
		observacion = "";
	}

	/**
	 * Cerrar Ventana.
	 * 
	 * @param @BindingParam("ventana") final Window ventana
	 * @return Ninguno
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "observacion" })
	public void cerrarVentana(@BindingParam("ventana") final Window ventana) {
		boolean condicion = false;
		if (observacion != null)
			condicion = true;
		mensajeAlUsuario.confirmacionCerrarVentanaMaestros(ventana, condicion);
	}
}//fin VMRegistrarRecursoJerarquico

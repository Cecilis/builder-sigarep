package sigarep.viewmodels.transacciones;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import sigarep.herramientas.Archivo;
import sigarep.herramientas.Documento;
import net.sf.jasperreports.engine.JRException;

import org.jfree.text.TextBox;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.databind.BindingListModel;
import org.zkoss.zkplus.databind.BindingListModelArray;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelArray;
import org.zkoss.zul.Window;
import sigarep.modelos.data.maestros.*;
import sigarep.modelos.data.transacciones.*;
import sigarep.herramientas.mensajes;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.EstadoApelacion;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.maestros.SancionMaestro;
import sigarep.modelos.data.maestros.Estudiante;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacionPK;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.EstudianteSancionadoPK;
import sigarep.modelos.data.transacciones.Motivo;
import sigarep.modelos.data.transacciones.MotivoPK;
import sigarep.modelos.data.transacciones.RecaudoEntregado;
import sigarep.modelos.data.transacciones.RecaudoEntregadoPK;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.data.transacciones.SolicitudApelacionPK;
import sigarep.modelos.data.transacciones.Soporte;
import sigarep.modelos.data.transacciones.SoportePK;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.maestros.ServicioEstadoApelacion;
import sigarep.modelos.servicio.transacciones.ListaApelacionEstadoApelacion;
import sigarep.modelos.servicio.transacciones.ListaHistorialEstudiante;
import sigarep.modelos.servicio.transacciones.ListaHistorialEstudianteAsignatura;
import sigarep.modelos.servicio.transacciones.ListaHistorialEstudianteMotivo;
import sigarep.modelos.servicio.transacciones.ListaHistorialEstudianteSancion;
import sigarep.modelos.servicio.transacciones.ListaHistorialEstudianteVeredicto;
import sigarep.modelos.servicio.transacciones.ListaRecaudosMotivoEstudiante;
import sigarep.modelos.servicio.transacciones.ServicioApelacion;
import sigarep.modelos.servicio.transacciones.ServicioApelacionEstadoApelacion;
import sigarep.modelos.servicio.transacciones.ServicioHistorialEstudiante;
import sigarep.modelos.servicio.transacciones.ServicioMotivo;
import sigarep.modelos.servicio.transacciones.ServicioMotivos;
import sigarep.modelos.servicio.transacciones.ServicioRecaudoEntregado;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;
import sigarep.modelos.servicio.transacciones.ServicioSoporte;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMHistorialEstudiante {
	@Wire("#modalDialog")
	private Window window;
	private String nombreSancion;
	private String programa;
	private String telefono;
	private String email;
	private String apellido;
	private String nombre;
	private String codigoLapso;
	private String nombreTipoMotivo;
	private String recaudo;
	private String segundoNombre;
	private String segundoApellido;
	private String nombres;
	private String apellidos;
	private String nombreAsignatura;
	private String sexo;
	private String veredicto1;
	private String veredicto2;
	private String veredicto3;
	private Date fechaNacimiento;
	private Date anioIngreso;
	private Float indiceGrado;
	private Integer caso;
	private Integer unidadesCursadas;
	private Integer unidadesAprobadas;
	private ListaHistorialEstudiante listahistorialestudiante;
	private List<TipoMotivo> listaTipoMotivo;
	private String cedula;

	@WireVariable
	private ServicioHistorialEstudiante serviciohistorial;

	mensajes msjs = new mensajes(); // para llamar a los diferentes mensajes de
									// dialogo

	@WireVariable
	private List<ListaHistorialEstudiante> lista = new LinkedList<ListaHistorialEstudiante>();
	
	@WireVariable
	private List<ListaHistorialEstudianteVeredicto> listaVeredicto = new LinkedList<ListaHistorialEstudianteVeredicto>();
	
	public List<ListaHistorialEstudianteVeredicto> getListaVeredicto() {
		return listaVeredicto;
	}

	public void setListaVeredicto(
			List<ListaHistorialEstudianteVeredicto> listaVeredicto) {
		this.listaVeredicto = listaVeredicto;
	}

	public List<ListaHistorialEstudiante> getLista() {
		return lista;
	}

	public void setLista(List<ListaHistorialEstudiante> lista) {
		this.lista = lista;
	}

	public ListaHistorialEstudiante getListahistorialestudiante() {
		return listahistorialestudiante;
	}

	public void setListahistorialestudiante(
			ListaHistorialEstudiante listahistorialestudiante) {
		this.listahistorialestudiante = listahistorialestudiante;
	}


	public List<TipoMotivo> getListaTipoMotivo() {
		return listaTipoMotivo;
	}

	public void setListaTipoMotivo(List<TipoMotivo> listaTipoMotivo) {
		this.listaTipoMotivo = listaTipoMotivo;
	}

	public Integer getCaso() {
		return caso;
	}

	public void setCaso(Integer caso) {
		this.caso = caso;
	}

	public String getNombreAsignatura() {
		return nombreAsignatura;
	}

	public void setNombreAsignatura(String nombreAsignatura) {
		this.nombreAsignatura = nombreAsignatura;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getRecaudo() {
		return recaudo;
	}

	public void setRecaudo(String recaudo) {
		this.recaudo = recaudo;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getNombreTipoMotivo() {
		return nombreTipoMotivo;
	}

	public void setNombreTipoMotivo(String nombreTipoMotivo) {
		this.nombreTipoMotivo = nombreTipoMotivo;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombreSancion() {
		return nombreSancion;
	}

	public void setNombreSancion(String nombreSancion) {
		this.nombreSancion = nombreSancion;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getVeredicto1() {
		return veredicto1;
	}

	public void setVeredicto1(String veredicto1) {
		this.veredicto1 = veredicto1;
	}

	public String getVeredicto2() {
		return veredicto2;
	}

	public void setVeredicto2(String veredicto2) {
		this.veredicto2 = veredicto2;
	}

	public String getVeredicto3() {
		return veredicto3;
	}

	public void setVeredicto3(String veredicto3) {
		this.veredicto3 = veredicto3;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public Integer getUnidadesCursadas() {
		return unidadesCursadas;
	}

	public void setUnidadesCursadas(Integer unidadesCursadas) {
		this.unidadesCursadas = unidadesCursadas;
	}

	public Integer getUnidadesAprobadas() {
		return unidadesAprobadas;
	}

	public void setUnidadesAprobadas(Integer unidadesAprobadas) {
		this.unidadesAprobadas = unidadesAprobadas;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Date getAnioIngreso() {
		return anioIngreso;
	}

	public void setAnioIngreso(Date anioIngreso) {
		this.anioIngreso = anioIngreso;
	}

	public Float getIndiceGrado() {
		return indiceGrado;
	}

	public void setIndiceGrado(Float indiceGrado) {
		this.indiceGrado = indiceGrado;
	}


	public void concatenacionNombres() {

		String nombre1 = nombre;
		String nombre2 = segundoNombre;
		nombres = nombre1 + " " + nombre2;
		System.out.println(nombres);
	}

	public void concatenacionApellidos() {

		String apellido1 = apellido;
		String apellido2 = segundoApellido;
		apellidos = apellido1 + " " + apellido2;

	}

	
	@Command
	@NotifyChange({ "listaVeredicto" })
	public void buscarVeredicto(String cedula) {
		codigoLapso = "2013-1";
		listaVeredicto = serviciohistorial.buscarVeredictos(cedula);
	}

	@Init
	public void init(

	@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("cedula") String v1,
			@ExecutionArgParam("nombre") String v2,
			@ExecutionArgParam("apellido") String v3,
			@ExecutionArgParam("sexo") String v4,
			@ExecutionArgParam("fechaNacimiento") Date v5,
			@ExecutionArgParam("telefono") String v6,
			@ExecutionArgParam("email") String v7,
			@ExecutionArgParam("segundoNombre") String v8,
			@ExecutionArgParam("segundoApellido") String v9,
			@ExecutionArgParam("unidadesCursadas") Integer v10,
			@ExecutionArgParam("unidadesAprobadas") Integer v11,
			@ExecutionArgParam("anioIngreso") Date v12,
			@ExecutionArgParam("indiceGrado") Float v13,
			@ExecutionArgParam("programa") String v14,
			@ExecutionArgParam("codigoLapso") String v15)

	// initialization code

	{
		Selectors.wireComponents(view, this, false);
		this.cedula = v1;
		this.nombre = v2;
		this.apellido = v3;
		this.sexo = v4;
		this.fechaNacimiento = v5;
		this.telefono = v6;
		this.email = v7;
		this.segundoNombre = v8;
		this.segundoApellido = v9;
		this.unidadesCursadas = v10;
		this.unidadesAprobadas = v11;
		this.anioIngreso = v12;
		this.indiceGrado = v13;
		this.programa = v14;
		this.codigoLapso = v15;
		concatenacionNombres();
		concatenacionApellidos();
		buscarVeredicto(cedula);
	}

	@Command
	public void closeThis() {
		window.detach();
	}

}

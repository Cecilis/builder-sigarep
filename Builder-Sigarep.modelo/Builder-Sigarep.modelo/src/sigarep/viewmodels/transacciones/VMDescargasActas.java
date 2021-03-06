package sigarep.viewmodels.transacciones;

import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Filedownload;

import sigarep.modelos.data.maestros.Reglamento;
import sigarep.modelos.servicio.maestros.ServicioReglamento;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMDescargasActas {
	
	private List<Reglamento> lista;
	
	@WireVariable
	private ServicioReglamento servicioreglamento;
	
	
//COMIENZO DE LOS M�TODOS GET Y SET

	public List<Reglamento> getLista() {
		return lista;
	}

	public void setLista(List<Reglamento> lista) {
		this.lista = lista;
	}
// FIN DE LOS M�TODOS GET Y SET
	
	@Init
	public void init(){
        //initialization code
		buscarActas();
					
    }

	/**
	 * buscarActa.
	 * @param Ninguno
	 * @return Ninguno
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "lista" })
	 public void buscarActas() {
		lista = servicioreglamento.buscarActa();		
	 }

	/**
	 * descargarArchivo.
	 * @param Ninguno
	 * @return Ninguno
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	public void descargarArchivo(@ContextParam(ContextType.COMPONENT) Component componente){
		int idDocumento = Integer.parseInt(componente.getAttribute("idActa").toString());
		for (int i = 0; i < lista.size(); i++) {
			if (idDocumento == lista.get(i).getIdDocumento())
				Filedownload.save(lista.get(i).getDocumento().getContenidoDocumento(),
						lista.get(i).getDocumento().getTipoDocumento(),
						lista.get(i).getDocumento().getNombreDocumento());
		}
	}
	
}

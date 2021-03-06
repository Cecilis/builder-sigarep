package sigarep.modelos.servicio.maestros;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.maestros.PreguntaBasica;
import sigarep.modelos.repositorio.maestros.IPreguntaBasicaDAO;

/**
 * Clase  ServicioPreguntaBasica (Servicio
 * para la clase PreguntaBasica)
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 18/12/2013
 * @last 10/05/2014
 */
@Service("serviciopreguntabasica") 
public class ServicioPreguntaBasica{
	private @Autowired IPreguntaBasicaDAO pre ;

	/** guardar pregunta basica
	 * @param preguntaBasica.
	 * @return No devuelve ning�n valor.
	 */
	public void guardarPregunta(PreguntaBasica preb) {
		if (preb.getIdPreguntaBasica() != null)
			pre.save(preb);
		else{
			preb.setIdPreguntaBasica(pre.buscarUltimoID()+1);
			pre.save(preb);
		}
	}
	
	/** eliminar pregunta basica
	 * @param idPreguntaBasica
	 * @return No devuelve ning�n  valor.
	 */
	public void eliminarPregunta(Integer idPreguntaBasica){
		PreguntaBasica pb = pre.findOne(idPreguntaBasica);
		pb.setEstatus(false);
		pre.save(pb);
	}
	
	/** listado de Preguntas
	 * @param una listadoPreguntas
	 * @return listadoPreguntas.
	 */
	public List<PreguntaBasica> listadoPreguntas() {
	    return pre.findByEstatusTrue() ;
	}
	
	/** buscar pregunta por tira de texto
	 * @param pregunta
	 * @return resultado es un listadoPregunta.
	 * @throws la Excepcion es que la pregunta o respuesta este en blanco.
	 */
	public List<PreguntaBasica> filtrarPreguntaBasica(String pregunta){
		List<PreguntaBasica> result = new LinkedList<PreguntaBasica>();
		if (pregunta==null || "".equals(pregunta)){//si el nombre es null o vacio,el resultado va a ser la lista completa de todos los profesores
			result = listadoPreguntas();
		}else{//caso contrario se recorre toda la lista y busca los profesores con el nombre indicado en la caja de texto y tambien busca todos los que tengan  las letras iniciales de ese nombre. Realiza la busqueda con el apellido e inicial del apellido.
			for (PreguntaBasica pr: listadoPreguntas()){
				if (pr.getPregunta().toLowerCase().contains(pregunta.toLowerCase())||
					pr.getRespuesta().toLowerCase().contains(pregunta.toLowerCase())){
					result.add(pr);
				}
			}
		}
		return result;
	}
}
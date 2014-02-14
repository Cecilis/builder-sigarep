package sigarep.modelos.servicio.transacciones;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.maestros.TipoMotivo;

import sigarep.modelos.data.transacciones.AsignaturaEstudianteSancionado;
import sigarep.modelos.data.transacciones.Motivo;
import sigarep.modelos.data.transacciones.MotivoPK;

import sigarep.modelos.repositorio.transacciones.IMotivoDAO;

@Service("serviciomotivo")
public class ServicioMotivo {
	private @Autowired IMotivoDAO iMotivoDAO;
	
	//metodo que permite Guardar
	public void guardarMotivo(Motivo motivo){
		iMotivoDAO.save(motivo);
	}
	
	//metodo que permite eliminar
	public void eliminarMotivo(MotivoPK motivoPK) {
		Motivo motivo = iMotivoDAO.findOne(motivoPK);
		motivo.setEstatus(false);
		iMotivoDAO.save(motivo);
	}
	/** Lista de Motivos
	 * @param cedulaEstudiante y codigoLapso
	 * @return resultado es un lista de Motivos dado una c�dula y un codigo de lapso
	 */
	public List<Motivo> buscarMotivos(String cedula, String codigoLapso){
		return iMotivoDAO.buscarMotivos(cedula, codigoLapso);
	}
}

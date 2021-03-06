package sigarep.modelos.repositorio.transacciones;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;




import sigarep.modelos.data.transacciones.UsuarioGrupo;
import sigarep.modelos.data.transacciones.UsuarioGrupoPK;

public interface IUsuarioGrupoDAO extends
		JpaRepository<UsuarioGrupo, UsuarioGrupoPK> {

	 //@Query()
	 //public List<SolicitudApelacion> solicitudesApelacionPorSancionado(EstudianteSancionadoPK id);
	
	/*@Query("SELECT usergru FROM UsuarioGrupo AS usergru WHERE UsuarioGrupo.id.nombreUsuario = : nombreUsuario ")
	public List<UsuarioGrupo> buscarPorUsuario(@Param("nombreUsuario") String nombreUsuario);
	
	@Query("SELECT usergru FROM UsuarioGrupo AS usergru WHERE UsuarioGrupo.id.nombreUsuario <>  : nombreUsuario ")
	public List<UsuarioGrupo> buscarPorUsuarioNO(@Param("nombreUsuario") String nombreUsuario); */
	
	
}

package sigarep.modelos.servicio.maestros;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.maestros.Banner;
import sigarep.modelos.repositorio.maestros.IBannerDAO;

/**
 * Clase ServicioBanner (Servicio relacionado a la
 * Clase Banner del Sitio Web) 
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 18/12/2013
 * @last 10/05/2014
 */
@Service("servicioBanner")
public class ServicioBanner {
	private @Autowired
	IBannerDAO banner;

	/**
	 * Guardar Banner
	 * @param Banner ba
	 * @return Guarda el objeto
	 * @throws No dispara ninguna excepcion.
	 */
	public void guardarBanner(Banner ba) {
		if (ba.getIdImagen() != null)
			banner.save(ba);
		else {
			ba.setIdImagen(banner.buscarUltimoID() + 1);
			banner.save(ba);
		}
	}

	/**
	 * Eliminar Banner
	 * 
	 * @param Integer
	 *            idImagen
	 * @return Elimina l�gicamente el objeto
	 * @throws Nodispara
	 *             ninguna excepci�n.
	 */
	public void eliminarBanner(Integer idImagen) {
		Banner b = banner.findOne(idImagen);
		b.setEstatus(false);
		banner.save(b);
	}

	/**
	 * Listado de los Banner
	 * 
	 * @param
	 * @return Busca todos Banner que estan en estatus TRUE
	 * @throws No
	 *             dispara ninguna excepci�n.
	 */
	public List<Banner> listadoBanner() {
		return banner.findByEstatusTrue();
	}

	/**
	 * Buscar Banner filtrando por titulo y enlace
	 * 
	 * @param String titulo, String enlace
	 * @return Busca un Banner por titulo y enlace
	 * @throws No dispara ninguna excepci�n.
	 */
	public List<Banner> buscarFiltroBanner(String titulo, String enlace) {
		List<Banner> result = new ArrayList<Banner>();
		if (titulo == null || enlace == null) {
			result = listadoBanner();
		} else {
			for (Banner b : listadoBanner()) {
				if (b.getTitulo().toLowerCase().contains(titulo.toLowerCase())
						&& b.getEnlace().toLowerCase()
								.contains(enlace.toLowerCase())) {
					result.add(b);
				}
			}
		}
		return result;
	}

	/**
	 * Buscar Todos Banner
	 * 
	 * @param
	 * @return Busca todos Banner que estan en estatus TRUE
	 * @throws No
	 *             dispara ninguna excepci�n.
	 */
	public List<Banner> buscarTodosBanner() {
		return banner.findByEstatusTrue();
	}
}
package sigarep.modelos.data.transacciones;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Instancia Miembro, relaciona la persona con una instancia 
 * @author Equipo : Builder-Sigarep Lapso 2013-2
 * @version 1.1
 * @since 10/02/14
 */
@Embeddable
public class InstanciaMiembroPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_instancia_apelada")
	private Integer idInstanciaApelada;

	@Column(name="cedula_persona")
	private String cedulaPersona;

	public InstanciaMiembroPK() {
	}
	public Integer getIdInstanciaApelada() {
		return this.idInstanciaApelada;
	}
	public void setIdInstanciaApelada(Integer idInstanciaApelada) {
		this.idInstanciaApelada = idInstanciaApelada;
	}
	public String getCedulaPersona() {
		return this.cedulaPersona;
	}
	public void setCedulaPersona(String cedulaPersona) {
		this.cedulaPersona = cedulaPersona;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof InstanciaMiembroPK)) {
			return false;
		}
		InstanciaMiembroPK castOther = (InstanciaMiembroPK)other;
		return 
			this.idInstanciaApelada.equals(castOther.idInstanciaApelada)
			&& this.cedulaPersona.equals(castOther.cedulaPersona);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idInstanciaApelada.hashCode();
		hash = hash * prime + this.cedulaPersona.hashCode();
		
		return hash;
	}
}
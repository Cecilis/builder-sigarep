package sigarep.modelos.data.reportes;

/** Clase ApelacionesComparativos 
 * @author Equipo Builder 
 * @version 1.0
 * @since 20/12/2013
 * @last 09/05/2014
 */
public class ApelacionesComparativos {
	// Atributos de la clase
	private Integer apelaciones;
	private String categoria;
	private Integer procedentes;
	private Integer total;
	private Integer sancionados;
	private Integer noprocedentes;

	/**
	 * Constructor ApelacionesComparativos
	 * 
	 * @param categoria, apelaciones, procedentes, total, sancionados
	 */
	public ApelacionesComparativos(String categoria, Integer apelaciones,
			Integer procedentes, Integer noprocedentes, Integer total, Integer sancionados) {
		super();
		this.apelaciones = apelaciones;
		this.categoria = categoria;
		this.procedentes = procedentes;
		this.noprocedentes = noprocedentes;
		this.total = total;
		this.sancionados = sancionados;
	}

	//  M�todos Set y Get
	public Integer getApelaciones() {
		return apelaciones;
	}
	
	public void setApelaciones(Integer apelaciones) {
		this.apelaciones = apelaciones;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public Integer getProcedentes() {
		return procedentes;
	}
	
	public void setProcedentes(Integer procedentes) {
		this.procedentes = procedentes;
	}
	
	public Integer getNoprocedentes() {
		return noprocedentes;
	}
	
	public void setNoprocedentes(Integer noprocedentes) {
		this.noprocedentes = noprocedentes;
	}
	
	public Integer getTotal() {
		return total;
	}
	
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	public Integer getSancionados() {
		return sancionados;
	}
	
	public void setSancionados(Integer sancionados) {
		this.sancionados = sancionados;
	}// Fin M�todos Set y Get
	
}
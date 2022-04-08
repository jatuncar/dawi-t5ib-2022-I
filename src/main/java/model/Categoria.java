package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_categorias")
public class Categoria {
	@Id
	@Column(name = "idcategoria")
	private int idcategoria;   // el mismo del campo
	
	//@Column(name = "descripcion")
	private String descripcion;
	
	@Override
	public String toString() {
		return "Categoria [idcategoria=" + idcategoria + ", descripcion=" + descripcion + "]";
	}

	public Categoria() {
		super();
	}
	
	public Categoria(int idcategoria, String descripcion) {
		super();
		this.idcategoria = idcategoria;
		this.descripcion = descripcion;
	}
	public int getIdcategoria() {
		return idcategoria;
	}
	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}

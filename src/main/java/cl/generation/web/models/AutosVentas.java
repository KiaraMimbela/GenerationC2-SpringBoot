package cl.generation.web.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "autos_ventas")
public class AutosVentas {

	// Tabla relacional para agregar columnas adicionales

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer cantidad;
	private Float valorUnitario;
	private Float total; // cantidad + valorUnitario

	// Relaciones ManyToMany = 2 ManyToOne

	// 1 ManyToOne
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auto_id")
	private Auto auto;

	// 2 ManyToOne
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "venta_id")
	private Venta venta;

	@Column(updatable = false) //
	@DateTimeFormat(pattern = "yyyy-MM-dd") // agregando formato de insercion
	private Date createdAt;// insercion de registro

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;// modificar un registro

	public AutosVentas() {
		super();
	}

	public AutosVentas(Long id, Integer cantidad, Float valorUnitario, Float total, Auto auto, Venta venta) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.valorUnitario = valorUnitario;
		this.total = total;
		this.auto = auto;
		this.venta = venta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Float getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Float valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	// Atributos de control
	// antes de ingresarla se guarda con la informacion del dia
	@PrePersist // agregar a la columna la fecha antes de insertar
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate // ingresar la fecha del dia que se esta actualizando
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
}

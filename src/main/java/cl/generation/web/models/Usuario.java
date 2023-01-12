package cl.generation.web.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

// entity: va a transformar nuestra clase en entidad
// anotaciones, empiezan con @

// table: se va a relacionar con la base de datos llamada usuarios, la va a crear
//recomendacion usar en plural el nombre del objeto

@Entity
@Table(name = "usuarios")
public class Usuario {

	// se va a transformar en una clave primaria
	// en la creacion de la table estabamos estableciendo una pk y autoincrementable
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull // obligatoria
	private String nombre;
	// las anotaciones solo sirven para ese atributo

	@NotNull
	private String apellido;

	@NotNull
	private String correo;

	@NotNull
	private String password;

	// relacion OneToOne // el mapeo de donde se hara la relación, cascada para que
	// si se elimine algo se elimina todo, lazy para que triga toda la informacion
	// permite eliminar error de cursividad ( vizualizacion )
	@JsonIgnore
	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Auto auto;

	// relacion OneToMany , el usuario va a tener muchas direcciones

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Direccion> direcciones;

	// ManyToMany
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "roles_usuarios", // nombre de la tabla relacional
			joinColumns = @JoinColumn(name = "usuario_id"), // donde se esta posicionado
			inverseJoinColumns = @JoinColumn(name = "rol_id"))
	private List<Rol> roles;

	@Transient // esa columna no sea considerada para la creacion de la tabla
	private String password2;

	private String nick;
	private String estado;

	@Range(min = 40, max = 300, message = "Peso fuera de rango") // con numero
	private Float peso;

	@Column(updatable = false) //
	@DateTimeFormat(pattern = "yyyy-MM-dd") // agregando formato de insercion
	private Date createdAt;// insercion de registro

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;// modificar un registro

	public Usuario() {
		super();
	}

	public Usuario(Long id, @NotNull @Size(min = 3, max = 15, message = "Error en el ingreso del nombre") String nombre,
			@NotNull String correo, @NotNull @Size(min = 5, max = 8) String password, Auto auto,
			List<Direccion> direcciones, String password2, String nick, String estado,
			@Range(min = 40, max = 300, message = "Peso fuera de rango") Float peso) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.password = password;
		this.auto = auto;
		this.direcciones = direcciones;
		this.password2 = password2;
		this.nick = nick;
		this.estado = estado;
		this.peso = peso;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Float getPeso() {
		return peso;
	}

	public void setPeso(Float peso) {
		this.peso = peso;
	}

	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}

	public List<Direccion> getDirecciones() {
		return direcciones;
	}

	public void setDirecciones(List<Direccion> direcciones) {
		this.direcciones = direcciones;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
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

package lester.barahona.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import javax.persistence.ManyToMany;



@Entity
@Table(name = "Usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String nombre;
	private String email;
	private String password;
	private int estatus;
	@Column(name="fechaRegistro")
	private Date fechaIngreso;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Usuarioperfil",
			joinColumns = @JoinColumn(name="idUsuario"),
			inverseJoinColumns = @JoinColumn(name="idPerfil")
			)
	private List<Perfil> perfiles;
	
	public Usuario() {
		this.perfiles=new LinkedList<Perfil>();
	}


	public Usuario(int id, String username, String nombre, String email, String password, int estatus,
			Date fechaIngreso) {
		super();
		this.id = id;
		this.username = username;
		this.nombre = nombre;
		this.email = email;
		this.password = password;
		this.estatus = estatus;
		this.fechaIngreso = fechaIngreso;
		this.perfiles=new LinkedList<Perfil>();
	}


	public Usuario(String username, String nombre, String email, String password, int estatus, Date fechaIngreso) {
		super();
		this.username = username;
		this.nombre = nombre;
		this.email = email;
		this.password = password;
		this.estatus = estatus;
		this.fechaIngreso = fechaIngreso;
		this.perfiles=new LinkedList<Perfil>();
	}
	
	
	public void addPerfil(Perfil perfil) {
		this.perfiles.add(perfil);
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getEstatus() {
		return estatus;
	}


	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}


	public Date getFechaIngreso() {
		return fechaIngreso;
	}


	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}


	public List<Perfil> getPerfiles() {
		return perfiles;
	}


	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", nombre=" + nombre + ", email=" + email
				+ ", password=" + password + ", estatus=" + estatus + ", fechaIngreso=" + fechaIngreso + ", perfiles="
				+ perfiles + "]";
	}


}

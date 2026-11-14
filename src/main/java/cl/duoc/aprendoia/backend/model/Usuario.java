package cl.duoc.aprendoia.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(length = 100)
    private String alias;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(length = 50)
    private String telefono = "+56 9 1234 5678";

    @Column(length = 200)
    private String direccion = "Santiago Centro";

    @Column(length = 50)
    private String avatar = "avatar_1";

    @Column(nullable = false, length = 50)
    private String rol = "Cliente"; // "Admin", "GenCont", "Cliente"

    private Integer puntaje = 0;

    private Integer qrEncontrados = 0;

    public Usuario() {
    }

    public Usuario(String email, String nombre, String alias, String password, String rol) {
        this.email = email;
        this.nombre = nombre;
        this.alias = alias;
        this.password = password;
        this.rol = rol != null ? rol : "Cliente";
        this.puntaje = 0;
        this.qrEncontrados = 0;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

    public Integer getQrEncontrados() {
        return qrEncontrados;
    }

    public void setQrEncontrados(Integer qrEncontrados) {
        this.qrEncontrados = qrEncontrados;
    }
}

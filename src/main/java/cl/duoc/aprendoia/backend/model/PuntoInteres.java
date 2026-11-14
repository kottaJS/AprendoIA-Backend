package cl.duoc.aprendoia.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "puntos_interes")
public class PuntoInteres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    @Column(length = 100)
    private String comuna;

    @Column(nullable = false)
    private Double latitud;

    @Column(nullable = false)
    private Double longitud;

    @Column(length = 100)
    private String categoriaIA;

    private Integer puntosRecompensa = 10;

    private Boolean completada = false;

    @Column(length = 100)
    private String codigoQR;

    private Boolean selfieTomada = false;

    @Column(length = 500)
    private String selfieUri;

    @Column(length = 500)
    private String urlVideo = "https://www.youtube.com/shorts/dQw4w9WgXcQ";

    @Column(length = 200)
    private String videoTitulo;

    @Column(length = 200)
    private String videoTema;

    @Column(length = 50)
    private String distanciaAprox = "250 m";

    private Boolean esHitoTuristico = true;

    private Boolean videoVisto = false;

    public PuntoInteres() {
    }

    public PuntoInteres(String nombre, String descripcion, String comuna, Double latitud, Double longitud,
                        String categoriaIA, Integer puntosRecompensa, String codigoQR, String videoTitulo, String videoTema) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.comuna = comuna;
        this.latitud = latitud;
        this.longitud = longitud;
        this.categoriaIA = categoriaIA;
        this.puntosRecompensa = puntosRecompensa != null ? puntosRecompensa : 10;
        this.codigoQR = codigoQR;
        this.videoTitulo = videoTitulo;
        this.videoTema = videoTema;
        this.completada = false;
        this.selfieTomada = false;
        this.esHitoTuristico = true;
        this.videoVisto = false;
    }

    // Getters y Setters
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public String getCategoriaIA() {
        return categoriaIA;
    }

    public void setCategoriaIA(String categoriaIA) {
        this.categoriaIA = categoriaIA;
    }

    public Integer getPuntosRecompensa() {
        return puntosRecompensa;
    }

    public void setPuntosRecompensa(Integer puntosRecompensa) {
        this.puntosRecompensa = puntosRecompensa;
    }

    public Boolean getCompletada() {
        return completada;
    }

    public void setCompletada(Boolean completada) {
        this.completada = completada;
    }

    public String getCodigoQR() {
        return codigoQR;
    }

    public void setCodigoQR(String codigoQR) {
        this.codigoQR = codigoQR;
    }

    public Boolean getSelfieTomada() {
        return selfieTomada;
    }

    public void setSelfieTomada(Boolean selfieTomada) {
        this.selfieTomada = selfieTomada;
    }

    public String getSelfieUri() {
        return selfieUri;
    }

    public void setSelfieUri(String selfieUri) {
        this.selfieUri = selfieUri;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getVideoTitulo() {
        return videoTitulo;
    }

    public void setVideoTitulo(String videoTitulo) {
        this.videoTitulo = videoTitulo;
    }

    public String getVideoTema() {
        return videoTema;
    }

    public void setVideoTema(String videoTema) {
        this.videoTema = videoTema;
    }

    public String getDistanciaAprox() {
        return distanciaAprox;
    }

    public void setDistanciaAprox(String distanciaAprox) {
        this.distanciaAprox = distanciaAprox;
    }

    public Boolean getEsHitoTuristico() {
        return esHitoTuristico;
    }

    public void setEsHitoTuristico(Boolean esHitoTuristico) {
        this.esHitoTuristico = esHitoTuristico;
    }

    public Boolean getVideoVisto() {
        return videoVisto;
    }

    public void setVideoVisto(Boolean videoVisto) {
        this.videoVisto = videoVisto;
    }
}

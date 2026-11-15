package cl.duoc.aprendoia.backend.dto;

import jakarta.validation.constraints.NotNull;

public class ActualizarPuntajeRequest {

    @NotNull(message = "Los puntos ganados son obligatorios")
    private Integer puntosGanados;

    private Boolean incrementoQr = false;

    public ActualizarPuntajeRequest() {
    }

    public ActualizarPuntajeRequest(Integer puntosGanados, Boolean incrementoQr) {
        this.puntosGanados = puntosGanados;
        this.incrementoQr = incrementoQr != null ? incrementoQr : false;
    }

    public Integer getPuntosGanados() {
        return puntosGanados;
    }

    public void setPuntosGanados(Integer puntosGanados) {
        this.puntosGanados = puntosGanados;
    }

    public Boolean getIncrementoQr() {
        return incrementoQr;
    }

    public void setIncrementoQr(Boolean incrementoQr) {
        this.incrementoQr = incrementoQr;
    }
}

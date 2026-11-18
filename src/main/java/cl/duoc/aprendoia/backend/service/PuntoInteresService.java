package cl.duoc.aprendoia.backend.service;

import cl.duoc.aprendoia.backend.model.PuntoInteres;
import cl.duoc.aprendoia.backend.repository.PuntoInteresRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PuntoInteresService {

    private final PuntoInteresRepository repository;

    public PuntoInteresService(PuntoInteresRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<PuntoInteres> obtenerTodos() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<PuntoInteres> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<PuntoInteres> obtenerPorQr(String codigoQr) {
        return repository.findByCodigoQR(codigoQr);
    }

    @Transactional(readOnly = true)
    public List<PuntoInteres> filtrarPorComuna(String comuna) {
        return repository.findByComunaIgnoreCase(comuna);
    }

    @Transactional(readOnly = true)
    public List<PuntoInteres> filtrarPorCategoria(String categoria) {
        return repository.findByCategoriaIAIgnoreCase(categoria);
    }

    public PuntoInteres crear(PuntoInteres punto) {
        if (punto.getCodigoQR() == null || punto.getCodigoQR().isBlank()) {
            punto.setCodigoQR("QR-AUTO-" + System.currentTimeMillis());
        }
        return repository.save(punto);
    }

    public Optional<PuntoInteres> actualizar(Long id, PuntoInteres datos) {
        return repository.findById(id).map(existente -> {
            existente.setNombre(datos.getNombre());
            existente.setDescripcion(datos.getDescripcion());
            existente.setComuna(datos.getComuna());
            existente.setLatitud(datos.getLatitud());
            existente.setLongitud(datos.getLongitud());
            existente.setCategoriaIA(datos.getCategoriaIA());
            if (datos.getPuntosRecompensa() != null) {
                existente.setPuntosRecompensa(datos.getPuntosRecompensa());
            }
            if (datos.getCodigoQR() != null && !datos.getCodigoQR().isBlank()) {
                existente.setCodigoQR(datos.getCodigoQR());
            }
            if (datos.getUrlVideo() != null) {
                existente.setUrlVideo(datos.getUrlVideo());
            }
            if (datos.getVideoTitulo() != null) {
                existente.setVideoTitulo(datos.getVideoTitulo());
            }
            if (datos.getVideoTema() != null) {
                existente.setVideoTema(datos.getVideoTema());
            }
            if (datos.getCompletada() != null) {
                existente.setCompletada(datos.getCompletada());
            }
            if (datos.getSelfieTomada() != null) {
                existente.setSelfieTomada(datos.getSelfieTomada());
            }
            if (datos.getSelfieUri() != null) {
                existente.setSelfieUri(datos.getSelfieUri());
            }
            return repository.save(existente);
        });
    }

    public boolean eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<PuntoInteres> marcarCompletado(Long id, Boolean selfie) {
        return repository.findById(id).map(p -> {
            p.setCompletada(true);
            if (Boolean.TRUE.equals(selfie)) {
                p.setSelfieTomada(true);
            }
            return repository.save(p);
        });
    }
}

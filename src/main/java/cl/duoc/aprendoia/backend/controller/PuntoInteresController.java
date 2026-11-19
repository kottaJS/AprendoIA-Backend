package cl.duoc.aprendoia.backend.controller;

import cl.duoc.aprendoia.backend.dto.ApiResponse;
import cl.duoc.aprendoia.backend.model.PuntoInteres;
import cl.duoc.aprendoia.backend.service.PuntoInteresService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/puntos")
@CrossOrigin(origins = "*")
public class PuntoInteresController {

    private final PuntoInteresService service;

    public PuntoInteresController(PuntoInteresService service) {
        this.service = service;
    }

    /**
     * GET /api/puntos - Listar todos los puntos de interes o filtrar por comuna/categoria
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<PuntoInteres>>> listar(
            @RequestParam(required = false) String comuna,
            @RequestParam(required = false) String categoria) {

        List<PuntoInteres> lista;
        if (comuna != null && !comuna.isBlank()) {
            lista = service.filtrarPorComuna(comuna);
        } else if (categoria != null && !categoria.isBlank()) {
            lista = service.filtrarPorCategoria(categoria);
        } else {
            lista = service.obtenerTodos();
        }

        return ResponseEntity.ok(ApiResponse.ok("Puntos de interés obtenidos correctamente", lista));
    }

    /**
     * GET /api/puntos/{id} - Obtener punto por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PuntoInteres>> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(p -> ResponseEntity.ok(ApiResponse.ok("Punto encontrado", p)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("No se encontró el punto de interés con ID: " + id)));
    }

    /**
     * GET /api/puntos/qr/{codigoQr} - Buscar por codigo QR
     */
    @GetMapping("/qr/{codigoQr}")
    public ResponseEntity<ApiResponse<PuntoInteres>> obtenerPorQr(@PathVariable String codigoQr) {
        return service.obtenerPorQr(codigoQr)
                .map(p -> ResponseEntity.ok(ApiResponse.ok("Punto validado por QR exitosamente", p)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Código QR no válido o no registrado: " + codigoQr)));
    }

    /**
     * POST /api/puntos - Crear un nuevo punto de interes (desde formulario móvil)
     */
    @PostMapping
    public ResponseEntity<ApiResponse<PuntoInteres>> crear(@RequestBody PuntoInteres nuevoPunto) {
        try {
            PuntoInteres guardado = service.crear(nuevoPunto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.ok("Punto de interés creado exitosamente", guardado));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error al crear punto: " + e.getMessage()));
        }
    }

    /**
     * PUT /api/puntos/{id} - Actualizar punto existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PuntoInteres>> actualizar(@PathVariable Long id, @RequestBody PuntoInteres datos) {
        return service.actualizar(id, datos)
                .map(actualizado -> ResponseEntity.ok(ApiResponse.ok("Punto actualizado correctamente", actualizado)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("No se encontró el punto con ID: " + id)));
    }

    /**
     * DELETE /api/puntos/{id} - Eliminar un punto de interés
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        boolean eliminado = service.eliminar(id);
        if (eliminado) {
            return ResponseEntity.ok(ApiResponse.ok("Punto eliminado exitosamente", null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("No se encontró el punto con ID: " + id));
    }

    /**
     * PATCH /api/puntos/{id}/completar - Marcar un punto como completado tras escanear o validar
     */
    @PatchMapping("/{id}/completar")
    public ResponseEntity<ApiResponse<PuntoInteres>> completar(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "false") Boolean selfie) {

        return service.marcarCompletado(id, selfie)
                .map(p -> ResponseEntity.ok(ApiResponse.ok("Misión completada correctamente", p)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("No se encontró el punto con ID: " + id)));
    }
}

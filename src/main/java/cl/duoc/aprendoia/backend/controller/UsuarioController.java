package cl.duoc.aprendoia.backend.controller;

import cl.duoc.aprendoia.backend.dto.ActualizarPuntajeRequest;
import cl.duoc.aprendoia.backend.dto.ApiResponse;
import cl.duoc.aprendoia.backend.dto.LoginRequest;
import cl.duoc.aprendoia.backend.model.Usuario;
import cl.duoc.aprendoia.backend.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    /**
     * GET /api/usuarios - Listar todos los usuarios
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<Usuario>>> listar() {
        List<Usuario> lista = service.obtenerTodos();
        return ResponseEntity.ok(ApiResponse.ok("Usuarios obtenidos exitosamente", lista));
    }

    /**
     * GET /api/usuarios/{id} - Obtener usuario por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Usuario>> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(u -> ResponseEntity.ok(ApiResponse.ok("Usuario encontrado", u)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("No se encontró el usuario con ID: " + id)));
    }

    /**
     * POST /api/usuarios - Registrar nuevo usuario
     */
    @PostMapping
    public ResponseEntity<ApiResponse<Usuario>> registrar(@Valid @RequestBody Usuario usuario) {
        try {
            Usuario nuevo = service.registrar(usuario);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.ok("Usuario registrado exitosamente", nuevo));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    /**
     * POST /api/usuarios/login - Iniciar sesión / autenticar
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Usuario>> login(@Valid @RequestBody LoginRequest request) {
        return service.autenticar(request.getEmailOrAlias(), request.getPassword())
                .map(u -> ResponseEntity.ok(ApiResponse.ok("Inicio de sesión exitoso", u)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(ApiResponse.error("Credenciales inválidas. Verifique correo/alias y clave")));
    }

    /**
     * PUT /api/usuarios/{id}/puntaje - Sumar puntaje a un jugador
     */
    @PutMapping("/{id}/puntaje")
    public ResponseEntity<ApiResponse<Usuario>> sumarPuntaje(
            @PathVariable Long id,
            @Valid @RequestBody ActualizarPuntajeRequest request) {

        return service.sumarPuntos(id, request.getPuntosGanados(), Boolean.TRUE.equals(request.getIncrementoQr()))
                .map(u -> ResponseEntity.ok(ApiResponse.ok("Puntaje actualizado exitosamente", u)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("No se encontró el usuario con ID: " + id)));
    }

    /**
     * PUT /api/usuarios/{id}/perfil - Actualizar datos del perfil
     */
    @PutMapping("/{id}/perfil")
    public ResponseEntity<ApiResponse<Usuario>> actualizarPerfil(
            @PathVariable Long id,
            @RequestBody Usuario datos) {

        return service.actualizarPerfil(id, datos)
                .map(u -> ResponseEntity.ok(ApiResponse.ok("Perfil actualizado exitosamente", u)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("No se encontró el usuario con ID: " + id)));
    }
}

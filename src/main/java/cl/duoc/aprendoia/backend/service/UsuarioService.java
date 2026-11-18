package cl.duoc.aprendoia.backend.service;

import cl.duoc.aprendoia.backend.model.Usuario;
import cl.duoc.aprendoia.backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Usuario> obtenerTodos() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Usuario registrar(Usuario usuario) {
        if (repository.existsByEmailIgnoreCase(usuario.getEmail())) {
            throw new IllegalArgumentException("Ya existe un usuario registrado con el email: " + usuario.getEmail());
        }
        return repository.save(usuario);
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> autenticar(String emailOrAlias, String password) {
        Optional<Usuario> userOpt = repository.findByEmailIgnoreCase(emailOrAlias.trim());
        if (userOpt.isEmpty()) {
            userOpt = repository.findByAliasIgnoreCase(emailOrAlias.trim());
        }

        if (userOpt.isPresent()) {
            Usuario u = userOpt.get();
            if (u.getPassword().equals(password)) {
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }

    public Optional<Usuario> sumarPuntos(Long id, int puntosGanados, boolean sumaQr) {
        return repository.findById(id).map(u -> {
            u.setPuntaje(u.getPuntaje() + puntosGanados);
            if (sumaQr) {
                u.setQrEncontrados(u.getQrEncontrados() + 1);
            }
            return repository.save(u);
        });
    }

    public Optional<Usuario> actualizarPerfil(Long id, Usuario datos) {
        return repository.findById(id).map(u -> {
            if (datos.getNombre() != null) u.setNombre(datos.getNombre());
            if (datos.getAlias() != null) u.setAlias(datos.getAlias());
            if (datos.getTelefono() != null) u.setTelefono(datos.getTelefono());
            if (datos.getDireccion() != null) u.setDireccion(datos.getDireccion());
            if (datos.getAvatar() != null) u.setAvatar(datos.getAvatar());
            return repository.save(u);
        });
    }
}

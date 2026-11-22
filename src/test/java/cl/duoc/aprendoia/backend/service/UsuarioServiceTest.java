package cl.duoc.aprendoia.backend.service;

import cl.duoc.aprendoia.backend.model.Usuario;
import cl.duoc.aprendoia.backend.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService service;

    private Usuario usuarioPrueba;

    @BeforeEach
    void setUp() {
        usuarioPrueba = new Usuario("estudiante@duocuc.cl", "Maximiliano", "Maxi", "123", "Cliente");
        usuarioPrueba.setId(1L);
        usuarioPrueba.setPuntaje(100);
        usuarioPrueba.setQrEncontrados(2);
    }

    @Test
    @DisplayName("Debe autenticar correctamente con email y clave válidos")
    void testAutenticacionExitosaPorEmail() {
        when(repository.findByEmailIgnoreCase("estudiante@duocuc.cl")).thenReturn(Optional.of(usuarioPrueba));

        Optional<Usuario> res = service.autenticar("estudiante@duocuc.cl", "123");

        assertTrue(res.isPresent());
        assertEquals("Maximiliano", res.get().getNombre());
    }

    @Test
    @DisplayName("Debe fallar autenticación si la clave es incorrecta")
    void testAutenticacionClaveInvalida() {
        when(repository.findByEmailIgnoreCase("estudiante@duocuc.cl")).thenReturn(Optional.of(usuarioPrueba));

        Optional<Usuario> res = service.autenticar("estudiante@duocuc.cl", "clave_mala");

        assertTrue(res.isEmpty());
    }

    @Test
    @DisplayName("Debe sumar puntaje e incrementar contador de QR")
    void testSumarPuntos() {
        when(repository.findById(1L)).thenReturn(Optional.of(usuarioPrueba));
        when(repository.save(any(Usuario.class))).thenReturn(usuarioPrueba);

        Optional<Usuario> res = service.sumarPuntos(1L, 50, true);

        assertTrue(res.isPresent());
        assertEquals(150, res.get().getPuntaje());
        assertEquals(3, res.get().getQrEncontrados());
    }

    @Test
    @DisplayName("Debe lanzar excepción si el email ya está registrado")
    void testRegistrarEmailDuplicado() {
        when(repository.existsByEmailIgnoreCase("estudiante@duocuc.cl")).thenReturn(true);

        Usuario nuevo = new Usuario("estudiante@duocuc.cl", "Otro", "Alias", "123", "Cliente");

        assertThrows(IllegalArgumentException.class, () -> service.registrar(nuevo));
        verify(repository, never()).save(any());
    }
}

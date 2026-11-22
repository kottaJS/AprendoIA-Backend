package cl.duoc.aprendoia.backend.service;

import cl.duoc.aprendoia.backend.model.PuntoInteres;
import cl.duoc.aprendoia.backend.repository.PuntoInteresRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PuntoInteresServiceTest {

    @Mock
    private PuntoInteresRepository repository;

    @InjectMocks
    private PuntoInteresService service;

    private PuntoInteres puntoPrueba;

    @BeforeEach
    void setUp() {
        puntoPrueba = new PuntoInteres(
                "Palacio de La Moneda",
                "Sede de gobierno",
                "Santiago Centro",
                -33.4429,
                -70.6539,
                "Ética",
                15,
                "QR-MONEDA-01",
                "Intro IA",
                "Ética"
        );
        puntoPrueba.setId(1L);
    }

    @Test
    @DisplayName("Debe listar todos los puntos de interés")
    void testObtenerTodos() {
        when(repository.findAll()).thenReturn(List.of(puntoPrueba));

        List<PuntoInteres> resultado = service.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Palacio de La Moneda", resultado.get(0).getNombre());
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe buscar un punto por su ID correctamente")
    void testObtenerPorIdExitoso() {
        when(repository.findById(1L)).thenReturn(Optional.of(puntoPrueba));

        Optional<PuntoInteres> resultado = service.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Debe crear un punto y autogenerar QR si viene nulo")
    void testCrearPuntoConQrAutogenerado() {
        PuntoInteres nuevo = new PuntoInteres();
        nuevo.setNombre("Cerro San Cristóbal");
        nuevo.setCodigoQR(null);

        when(repository.save(any(PuntoInteres.class))).thenAnswer(invocation -> invocation.getArgument(0));

        PuntoInteres guardado = service.crear(nuevo);

        assertNotNull(guardado.getCodigoQR());
        assertTrue(guardado.getCodigoQR().startsWith("QR-AUTO-"));
        verify(repository, times(1)).save(nuevo);
    }

    @Test
    @DisplayName("Debe eliminar un punto si existe")
    void testEliminarExistente() {
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(1L);

        boolean eliminado = service.eliminar(1L);

        assertTrue(eliminado);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Debe marcar completado un punto")
    void testMarcarCompletado() {
        when(repository.findById(1L)).thenReturn(Optional.of(puntoPrueba));
        when(repository.save(any(PuntoInteres.class))).thenReturn(puntoPrueba);

        Optional<PuntoInteres> res = service.marcarCompletado(1L, true);

        assertTrue(res.isPresent());
        assertTrue(res.get().getCompletada());
        assertTrue(res.get().getSelfieTomada());
    }
}

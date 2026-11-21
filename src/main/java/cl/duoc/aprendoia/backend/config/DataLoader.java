package cl.duoc.aprendoia.backend.config;

import cl.duoc.aprendoia.backend.model.PuntoInteres;
import cl.duoc.aprendoia.backend.model.Usuario;
import cl.duoc.aprendoia.backend.repository.PuntoInteresRepository;
import cl.duoc.aprendoia.backend.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner initData(PuntoInteresRepository puntoRepo, UsuarioRepository usuarioRepo) {
        return args -> {
            // 1. Cargar usuarios iniciales si la tabla esta vacia
            if (usuarioRepo.count() == 0) {
                Usuario admin = new Usuario("admin@duocuc.cl", "Administrador CITT", "Admin", "123", "Admin");
                admin.setPuntaje(500);

                Usuario genCont = new Usuario("gencont@duocuc.cl", "Generador de Contenido", "GenCont", "123", "GenCont");
                genCont.setPuntaje(300);

                Usuario estudiante = new Usuario("estudiante@duocuc.cl", "Maxi Dinamarca", "Maxi", "123", "Cliente");
                estudiante.setPuntaje(120);
                estudiante.setQrEncontrados(3);

                usuarioRepo.saveAll(List.of(admin, genCont, estudiante));
                System.out.println(">>> [DataLoader] Usuarios de prueba cargados exitosamente.");
            }

            // 2. Cargar puntos de interes de Santiago si la tabla esta vacia
            if (puntoRepo.count() == 0) {
                PuntoInteres p1 = new PuntoInteres(
                        "Palacio de La Moneda",
                        "Sede del gobierno chileno. En este hito aprenderas sobre la etica en algoritmos publicos y gobernanza de IA.",
                        "Santiago Centro",
                        -33.4429,
                        -70.6539,
                        "Ética y Sociedad",
                        15,
                        "QR-MONEDA-IA-001",
                        "Ética en IA y Políticas Públicas",
                        "Gobernanza de Datos"
                );

                PuntoInteres p2 = new PuntoInteres(
                        "Plaza de Armas",
                        "El corazon historico de Santiago. Descubre como la vision artificial interpreta senales urbanas y flujo peatonal.",
                        "Santiago Centro",
                        -33.4372,
                        -70.6506,
                        "Visión Artificial",
                        20,
                        "QR-PLAZA-IA-002",
                        "Visión por Computadora Urbana",
                        "Reconocimiento de Patrones"
                );

                PuntoInteres p3 = new PuntoInteres(
                        "Parque Forestal",
                        "Pulmon verde urbano. Analiza como los Modelos de Lenguaje (LLMs) ayudan a procesar informacion ambiental.",
                        "Santiago Centro",
                        -33.4358,
                        -70.6415,
                        "Modelos de Lenguaje",
                        25,
                        "QR-FORESTAL-IA-003",
                        "Procesamiento de Lenguaje Natural",
                        "Modelos Generativos"
                );

                PuntoInteres p4 = new PuntoInteres(
                        "Sede Duoc UC CITT Alonso de Ovalle",
                        "Centro de Innovacion y Transferencia Tecnologica. Cuna de proyectos de robotica e IoT con IA aplicada.",
                        "Santiago Centro",
                        -33.4475,
                        -70.6558,
                        "Robótica e IA",
                        30,
                        "QR-DUOC-IA-004",
                        "Sistemas Embebidos y Robótica",
                        "Edge AI"
                );

                PuntoInteres p5 = new PuntoInteres(
                        "Cerro Santa Lucía",
                        "Monumento historico con miradores de la capital. Explora como la navegacion autonoma mapea terrenos en 3D.",
                        "Santiago Centro",
                        -33.4402,
                        -70.6439,
                        "Sistemas Autónomos",
                        20,
                        "QR-SANTALUCIA-IA-005",
                        "Geolocalización y Drones Autónomos",
                        "Mapeo Espacial"
                );

                PuntoInteres p6 = new PuntoInteres(
                        "Museo Nacional de Bellas Artes",
                        "Patrimonio artistico nacional. Experimenta con modelos de difusion y creacion multimedia generada por IA.",
                        "Santiago Centro",
                        -33.4351,
                        -70.6441,
                        "Arte y Generación",
                        25,
                        "QR-BELLASARTES-IA-006",
                        "Creatividad e Inteligencia Artificial",
                        "Modelos de Difusión"
                );

                puntoRepo.saveAll(List.of(p1, p2, p3, p4, p5, p6));
                System.out.println(">>> [DataLoader] Puntos de interes de Santiago cargados exitosamente.");
            }
        };
    }
}

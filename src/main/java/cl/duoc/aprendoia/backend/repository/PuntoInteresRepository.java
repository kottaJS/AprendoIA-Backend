package cl.duoc.aprendoia.backend.repository;

import cl.duoc.aprendoia.backend.model.PuntoInteres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PuntoInteresRepository extends JpaRepository<PuntoInteres, Long> {

    List<PuntoInteres> findByComunaIgnoreCase(String comuna);

    List<PuntoInteres> findByCategoriaIAIgnoreCase(String categoriaIA);

    Optional<PuntoInteres> findByCodigoQR(String codigoQR);
}

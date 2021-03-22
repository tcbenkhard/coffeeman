package nl.benkhard.coffeeman.features.machines;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {

    @Query("select m from Machine m where " +
            "lower(m.reference) like concat('%', lower(:query), '%') or " +
            "lower(m.serialNumber) like  concat('%', lower(:query), '%') or " +
            "lower(m.brand) like  concat('%', lower(:query), '%') or " +
            "lower(m.type) like  concat('%', lower(:query), '%')")
    List<Machine> query(@Param("query") String query);
}

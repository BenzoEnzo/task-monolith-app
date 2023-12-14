package pl.benzo.enzo.server.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.benzo.enzo.server.api.model.entity.LinkEntity;

@Repository
public interface LinkRepository extends JpaRepository<LinkEntity,Long> {
    LinkEntity findLinkEntityByGeneratedVal(String generatedVal);
}

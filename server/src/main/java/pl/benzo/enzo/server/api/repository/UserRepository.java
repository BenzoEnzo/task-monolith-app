package pl.benzo.enzo.server.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.benzo.enzo.server.api.model.entity.UserEntity;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u.friends FROM UserEntity u WHERE u.id = :userId")
    Set<UserEntity> findAllFriendsById(@Param("userId") Long userId);
}

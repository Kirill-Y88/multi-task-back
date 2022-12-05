package y88.kirill.multitaskback.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import y88.kirill.multitaskback.models.UserRole;


import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    Optional<UserRole> findById(Long id);
    Optional<UserRole> findByTitle(String title);


}

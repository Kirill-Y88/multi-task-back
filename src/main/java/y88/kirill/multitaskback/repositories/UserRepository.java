package y88.kirill.multitaskback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import y88.kirill.multitaskback.models.User;
import y88.kirill.multitaskback.models.UserRole;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findById(Long id);
    Optional<User> findByLogin(String login);
    Optional<User> findByEmail(String email);
    boolean existsUserByLogin(String login);
    boolean existsUserByEmail(String email);

    List<User> findAllByUserRole(UserRole userRole);



}

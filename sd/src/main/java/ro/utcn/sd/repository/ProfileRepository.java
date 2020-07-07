package ro.utcn.sd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.utcn.sd.entity.Profile;
import ro.utcn.sd.entity.User;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, String> {

    Optional<Profile> findByUsernameOrEmail(String username, String email);

    Optional<Profile> findByUser(User user);

    Optional<Profile> findByEmail(String email);
}

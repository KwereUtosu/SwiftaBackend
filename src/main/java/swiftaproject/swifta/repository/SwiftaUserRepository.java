package swiftaproject.swifta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swiftaproject.swifta.model.SwiftaUser;

/**
 * @author Kwerenachi Utosu
 * @date 2/3/2020
 */
@Repository
public interface SwiftaUserRepository extends JpaRepository<SwiftaUser, Long> {

    SwiftaUser findFirstByEmail(String email);
}

package efub.ebmt.eeojum.domain.Member.repository;

import efub.ebmt.eeojum.domain.Member.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByValue(String value);
}


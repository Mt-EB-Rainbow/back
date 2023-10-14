package efub.ebmt.eeojum.Member.service;

import efub.ebmt.eeojum.Member.domain.RefreshToken;
import efub.ebmt.eeojum.Member.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken addRefreshToken(RefreshToken refreshToken) {
        return refreshTokenRepository.save(refreshToken);
    }

    @Transactional(readOnly = true)
    public RefreshToken findRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByValue(refreshToken)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 RefreshToken입니다!"));
    }

    public void deleteRefreshToken(String refreshToken) {
        RefreshToken foundRefreshToken = refreshTokenRepository.findByValue(refreshToken)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 RefreshToken입니다!"));
        refreshTokenRepository.delete(foundRefreshToken);
    }
}


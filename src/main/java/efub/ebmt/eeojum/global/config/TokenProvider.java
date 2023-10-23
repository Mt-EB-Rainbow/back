package efub.ebmt.eeojum.global.config;

import efub.ebmt.eeojum.global.util.JwtUtil;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class TokenProvider {

    private Key key;

    @Value("${spring.jwt.secret-key}")
    private String accessKey;

    @Value("${spring.jwt.refresh-key}")
    private String refreshKey;

    private final long expiredTime = 1000 * 60 * 60L; // 1시간
    private final long refreshExpiredTime = 7 * 24 * 60 * 60L; // 일주일
    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    // Access Token 생성
    public String createAccessToken(Long memberId) {
        return JwtUtil.createAccessToken(memberId, accessKey, expiredTime);
    }

    // Refresh Token 생성
    public String createRefreshToken(Long memberId) {
        return JwtUtil.createRefreshToken(memberId, refreshKey, refreshExpiredTime);
    }

    // Refresh Token을 이용해 새 AccessToken 발행
    public String refreshAccessToken(String refreshToken) {
        try {
            // Refresh Token 검증
            if (!validateToken(refreshToken, refreshKey)) {
                throw new SecurityException("유효하지 않은 refresh token");
            }

            // User Identity 확인
            Long memberId = getMemberIdFromRefreshToken(refreshToken);

            // 새로운 Access Token 발행
            return createAccessToken(memberId);
        } catch (Exception e) {
            logger.error("access token 재발급 실패", e);
            throw new RuntimeException("access token 재발급 실패", e);
        }
    }

    // Refresh Token 파싱
    public Long getMemberIdFromRefreshToken(String token) {
        Claims claims = JwtUtil.parseRefreshToken(token, refreshKey);
        return claims.get("memberId", Long.class);
    }

    public boolean validateToken(String token, String keyToUse) {
        try {
            Jwts.parserBuilder().setSigningKey(keyToUse).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            logger.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            logger.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            logger.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            logger.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }
}


package efub.ebmt.eeojum.global.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Id;

@Getter
@AllArgsConstructor
@RedisHash(value = "jwtToken", timeToLive = 60 * 60 * 24 * 14)
public class JwtToken {

    @Id
    private String id;

    @Indexed
    private String accessToken;

    private String refreshToken;

    public void updateAccessToken(String accessToken){
        this.accessToken = accessToken;
    }
}


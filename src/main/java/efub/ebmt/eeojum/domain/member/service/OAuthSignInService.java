package efub.ebmt.eeojum.domain.member.service;

import efub.ebmt.eeojum.domain.member.domain.Member;
import efub.ebmt.eeojum.global.config.OAuthPlatform;

public interface OAuthSignInService {
    OAuthPlatform supports();
    Member toEntityMember(String code, OAuthPlatform platform);
}

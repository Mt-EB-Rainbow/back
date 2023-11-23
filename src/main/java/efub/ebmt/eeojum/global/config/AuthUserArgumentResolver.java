package efub.ebmt.eeojum.global.config;

import efub.ebmt.eeojum.domain.member.domain.Member;
import efub.ebmt.eeojum.global.exception.CustomException;
import efub.ebmt.eeojum.global.exception.ErrorCode;
import efub.ebmt.eeojum.global.jwt.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AuthUserArgumentResolver implements HandlerMethodArgumentResolver {
    private final JwtAuthenticationProvider jwtAuthenticationProvider;


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        AuthUser authUserAnnotation = parameter.getParameterAnnotation(AuthUser.class);
        assert authUserAnnotation != null;
        if(!authUserAnnotation.required()){
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        String token = jwtAuthenticationProvider.resolveToken(Objects.requireNonNull(webRequest.getNativeRequest(HttpServletRequest.class)));

        Authentication authentication = jwtAuthenticationProvider.authenticate(token);
        return (Member) authentication.getPrincipal();
    }
}



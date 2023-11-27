package efub.ebmt.eeojum.domain.redirect;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/a")
public class RedirectController {
    @GetMapping("")
    public void oauthLogin(HttpServletResponse response) throws IOException {
        String redirect_uri="https://eeozum.vercel.app/";
        response.sendRedirect(redirect_uri);
    }
}

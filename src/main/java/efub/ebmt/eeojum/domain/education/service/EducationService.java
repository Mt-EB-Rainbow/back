package efub.ebmt.eeojum.domain.education.service;

import com.google.common.net.HttpHeaders;
import efub.ebmt.eeojum.domain.education.dto.request.EducationRequest;
import efub.ebmt.eeojum.domain.education.dto.response.EducationsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class EducationService {
    @Value("${openapi.dream.auth-key}")
    String authKey;
    //https://www.dream.go.kr/dream/dreamApi/v1.do?authKey=KIAO7F5LGGBIAW16CBXXR76IQMEHIOWU&type=A&startPage=1&display=100
    //KIAO7F5LGGBIAW16CBXXR76IQMEHIOWU&type=A&startPage=1&display=100
    public void xmlToJavaObject(String type, int startPage, int display){
        WebClient webClient = WebClient.builder()
                .baseUrl("https://www.dream.go.kr/dream/dreamApi/v1.do")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML_VALUE)
                .build();

        String xmlResponse = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("authKey", "{authKey}")
                        .queryParam("type", "{type}")
                        .queryParam("startPage", "{startPage}")
                        .queryParam("display", "{display}")
                        .build(authKey, type, startPage, display))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println(xmlResponse);
    }

    public EducationsResponse educationList(EducationRequest educationRequest){
        xmlToJavaObject("A", 1, 50);
        EducationsResponse educationsResponse = new EducationsResponse();
        return educationsResponse;
    }
}

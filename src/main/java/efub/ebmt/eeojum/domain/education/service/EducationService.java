package efub.ebmt.eeojum.domain.education.service;

import com.google.common.net.HttpHeaders;
import efub.ebmt.eeojum.domain.education.dto.request.EducationRequest;
import efub.ebmt.eeojum.domain.education.dto.response.EducationResponse;
import efub.ebmt.eeojum.domain.education.dto.response.EducationsResponse;
import efub.ebmt.eeojum.domain.education.dto.response.XmlResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EducationService {
    @Value("${openapi.dream.auth-key}")
    String authKey;
    //https://www.dream.go.kr/dream/dreamApi/v1.do?authKey=KIAO7F5LGGBIAW16CBXXR76IQMEHIOWU&type=A&startPage=1&display=100
    //KIAO7F5LGGBIAW16CBXXR76IQMEHIOWU&type=A&startPage=1&display=100
    public XmlResponse xmlToJavaObject(String type, int startPage, int display){
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

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlResponse.class); // JAXB Context 생성
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();  // Unmarshaller Object 생성
            assert xmlResponse != null;
            return (XmlResponse) unmarshaller.unmarshal(new StringReader(xmlResponse));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<EducationResponse> searchEducation(String type, int startPage, String query, String category){
        xmlToJavaObject(type, startPage, 50).getResponseBody().getList().getDataList().stream()
                .filter(obj -> obj.getMajorCategoryName());
    }

    public EducationsResponse educationList(EducationRequest educationRequest){
        EducationsResponse educationResponse = new EducationsResponse();
        if(educationRequest.getType() == null){
            for(int i=1; i<23; i++){
                xmlToJavaObject("A", i, 50).getResponseBody();
            }
        }
        xmlToJavaObject("A", 1, 50);
        EducationsResponse educationsResponse = new EducationsResponse();
        return educationsResponse;
    }
}

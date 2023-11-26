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
import org.springframework.http.codec.xml.Jaxb2XmlDecoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EducationService {
    @Value("${openapi.dream.auth-key}")
    String authKey;

    public XmlResponse xmlToJavaObject(String type, int startPage, int display){
        WebClient webClient = WebClient.builder()
                .exchangeStrategies(
                        ExchangeStrategies.builder()
                                .codecs(configurer ->
                                        configurer.defaultCodecs().jaxb2Decoder(new Jaxb2XmlDecoder())
                                )
                                .build()
                )
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

        //System.out.println(xmlResponse);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlResponse.class); // JAXB Context 생성
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();  // Unmarshaller Object 생성
            assert xmlResponse != null;
            XmlResponse xmlResponseObj = (XmlResponse) unmarshaller.unmarshal(new StringReader(xmlResponse));
            //System.out.println(xmlResponseObj.getResponseBody());
            //System.out.println(xmlResponseObj.getResponseBody().getList());
            //System.out.println(xmlResponseObj.getResponseBody().getList().getData());
            //System.out.println(xmlResponseObj.getResponseBody().getList().getData().get(0));
            //System.out.println(xmlResponseObj.getResponseBody().getList().getData().get(0).getContentsName());
            return xmlResponseObj;
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void educationSave(){
        return;
    }

    public List<EducationResponse> searchEducationByQuery(String query, String category, String classDomain){
        List<EducationResponse> educationResponses = new ArrayList<>();
        for(int i=1; i<23; i++) {
            if(i == 4)
                continue; //외부 open API 에러로 4페이지는 읽을 수 없음.
            educationResponses.addAll(xmlToJavaObject("A", i, 50).getResponseBody().getList().getData().stream()
                    .filter(data -> category == null ? 1 == 1 : data.getMajorCategoryName().equals(category))
                    .filter(data -> classDomain == null ? 1 == 1 : data.getMiddleCategoryName().equals(classDomain))
                    .filter(data -> query == null ? 1 == 1 : (data.getCourseName().contains(query) || data.getContentsName().contains(query)))
                    .map(EducationResponse::new)
                    .collect(Collectors.toList()));
        }

        for(int i=1; i<32; i++){
            educationResponses.addAll(xmlToJavaObject("B", i, 50).getResponseBody().getList().getData().stream()
                    .filter(data -> category == null ? 1 == 1 : data.getMajorCategoryName().equals(category))
                    .filter(data -> classDomain == null ? 1 == 1 : data.getMiddleCategoryName().equals(classDomain))
                    .filter(data -> query == null ? 1 == 1 : (data.getCourseName().contains(query) || data.getContentsName().contains(query)))
                    .map(EducationResponse::new)
                    .collect(Collectors.toList()));
        }
        return educationResponses;
    }

    public EducationsResponse educationList(EducationRequest educationRequest){
        return new EducationsResponse(searchEducationByQuery(educationRequest.getQuery(), educationRequest.getCategory(), educationRequest.getClassDomain()));
    }
}

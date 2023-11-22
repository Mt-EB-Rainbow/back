package efub.ebmt.eeojum.domain.nurture.service;

import efub.ebmt.eeojum.domain.nurture.dto.request.NurtureRequest;
import efub.ebmt.eeojum.domain.nurture.dto.response.NurtureResponse;
import efub.ebmt.eeojum.domain.nurture.dto.response.NurturesResponse;
import efub.ebmt.eeojum.global.exception.CustomException;
import efub.ebmt.eeojum.global.exception.ErrorCode;
import efub.ebmt.eeojum.global.util.WebDriverUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.Duration;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class NurtureService {

    public NurturesResponse nurtureList(NurtureRequest nurtureRequest){
        //테스트
        System.out.println(System.getProperty("user.dir"));

        WebDriver driver = WebDriverUtil.getChromeDriver();
        List<NurtureResponse> nurturesResponse = new ArrayList<>();
        long pageCnt = 0L;
        String url = "https://iseoul.seoul.go.kr/portal/info/preSchoolList.do";

        if (!ObjectUtils.isEmpty(driver)) {
            List<WebElement> webElementList = new ArrayList<>();
            driver.get(url);
            //TODO: explicitly wait으로 수정 -> 속도 개선
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            //자치구 선택
            WebElement selectElement = driver.findElement(By.id("ap1"));
            Select select = new Select(selectElement);

            try {
                select.selectByVisibleText(nurtureRequest.getDistrict());
            } catch(NoSuchElementException e){
                throw new CustomException(ErrorCode.WRONG_DISTRICT);
            }

            //행정동 입력
            driver.findElement(By.xpath("//*[@id=\"ap2\"]")).sendKeys(nurtureRequest.getDong());

            //어린이집 검색 클릭
            driver.findElement(By.xpath("//*[@id=\"contents\"]/div[2]/form/div/dl[5]/dd/a")).click();

            //총 어린이집 수
            String schoolCntStr = driver.findElement(By.className("listInfo")).findElement(By.tagName("strong")).getText();
            List<String> splitResult = Arrays.asList(schoolCntStr.split(" "));
            long schoolCnt = Long.parseLong(splitResult.get(2));
            if(schoolCnt % 10 != 0 || schoolCnt == 0){
                pageCnt = schoolCnt / 10 + 1;
            }
            else{
                pageCnt = schoolCnt / 10;
            }

            //첫 번째 페이지 아니라면 해당 페이지 번호로 넘어감
            //TODO: 10페이지 넘어가는 경우 적용
            if(!Objects.equals(nurtureRequest.getPageNum(), "1")){
                driver.findElement(By.linkText(nurtureRequest.getPageNum())).click();
            }

            WebElement el1 = driver.findElement(By.tagName("tbody"));
            webElementList = el1.findElements(By.tagName("tr")); //테이블 모든 행 가져옴

            for(int i=0; i<webElementList.size(); i++){
                List<WebElement> tableDataList = webElementList.get(i).findElements(By.tagName("td"));

                String rowId = tableDataList.get(0).getText();
                String schoolName = tableDataList.get(1).getText();
                String type = tableDataList.get(2).getText();
                String capacity = tableDataList.get(3).getText();
                String current = tableDataList.get(4).getText();
                String contact = tableDataList.get(5).getText();
                String address = tableDataList.get(6).getText();

                NurtureResponse nurtureResponse = NurtureResponse.builder()
                        .rowId(rowId)
                        .schoolName(schoolName)
                        .type(type)
                        .capacity(capacity)
                        .current(current)
                        .contact(contact)
                        .address(address)
                        .build();

                nurturesResponse.add(nurtureResponse);
            }

            driver.close();
        }

        return new NurturesResponse(pageCnt, nurturesResponse);
    }
}

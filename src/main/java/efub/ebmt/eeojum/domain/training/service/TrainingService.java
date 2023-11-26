package efub.ebmt.eeojum.domain.training.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import efub.ebmt.eeojum.domain.training.domain.Training;
import efub.ebmt.eeojum.domain.training.dto.CourseDto;
import efub.ebmt.eeojum.domain.training.repository.TrainingRepository;
import efub.ebmt.eeojum.global.util.XmlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TrainingService {
    private final TrainingRepository trainingRepository;

    @Autowired
    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    // 데이터 처리 및 저장 로직
    @Transactional
    public List<Training> processAndSaveData(String type) throws IOException {

        // 1. 외부 API에서 데이터를 가져옴
        List<CourseDto> courseDtos = callAPIAndReturnData(type);

        // 2. 가져온 데이터를 저장
        List<Training> trainings = saveCourses(courseDtos, type);

        // 3. 타입 별로 트레이닝 데이터 조회
        List<Training> trainingByType = getTrainingsByType(type);

        return trainingByType;
    }

    // 한 페이지당 12개의 데이터를 반환할 때, 전체 페이지수 계산
    public Long getTotalPages(String type) throws IOException {
        String xmlData = callAPI(type, 1, 1); // 첫 페이지의 1개 결과로 총 결과 수 확인
        String jsonData = XmlUtil.xmlToJson(xmlData); // XML을 JSON으로 변환
        System.out.println("Converted JSON Data: " + jsonData);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonData);
        int totalResults = rootNode.path("response_body").path("result").asInt();
        System.out.println((long) Math.ceil((double) totalResults / 12));
        return (long) Math.ceil((double) totalResults / 12);
    }

    // api 호출(xml 데이터 반환)
    public String callAPI(String type, long page, long rows) throws IOException {
        StringBuilder result = new StringBuilder();
        String urlStr = "https://www.dream.go.kr/dream/dreamApi/v1.do?" +
                "authKey=KIAO7F5LGGBIAW16CBXXR76IQMEHIOWU&" +
                "type=" + type + "&" +
                "startPage=" + page + "&" +
                "display=" + rows;
        URL url = new URL(urlStr);

        System.out.println(url);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"))) {
            String returnLine;
            while ((returnLine = br.readLine()) != null) {
                result.append(returnLine).append("\n");
            }
        } finally {
            urlConnection.disconnect();
        }
        return result.toString();
    }

    // 저장된 데이터를 엔티티로 전환
    public Training convertToEntity(CourseDto dto) {
        Training training = new Training();

        training.setApplyUrl(dto.getApplyUrl());
        training.setContentsName(dto.getContentsName());
        training.setCourseSeq(dto.getCourseSeq());
        training.setDetailUrl(dto.getDetailUrl());
        training.setCourseName(dto.getCourseName());
        training.setFileUrl(dto.getFileUrl());
        training.setMajorCategoryName(dto.getMajorCategoryName());
        training.setMiddleCategoryName(dto.getMiddleCategoryName());
        training.setTrainingGoal(dto.getTrainingGoal());
        training.setTrainingSummary(dto.getTrainingSummary());
        training.setOnlineTrainingTime(dto.getOnlineTrainingTime());
        training.setType(dto.getType());

        return training;
    }

    // API 호출 및 데이터 처리 로직
    public List<CourseDto> callAPIAndReturnData(String type) throws IOException {
        long totalPages = getTotalPages(type);
        System.out.println("totalPages: " + totalPages);
        List<CourseDto> coursesList = new ArrayList<>();

        for (int page = 1; page <= totalPages; page++) {
            String xmlData = callAPI(type, page, 12);
            String jsonData = XmlUtil.xmlToJson(xmlData);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(jsonData);
            JsonNode dataNode = rootNode.path("response_body").path("list").path("data");

            System.out.println("data node : " + dataNode);
            // If dataNode is a single object, create an array with that object
            if (dataNode.isObject()) {
                ArrayNode arrayNode = mapper.createArrayNode();
                arrayNode.add(dataNode);
                dataNode = arrayNode;
            }

            // Process each node in the dataNode array
            if (dataNode.isArray()) {
                for (JsonNode node : dataNode) {
                    CourseDto course = mapper.treeToValue(node, CourseDto.class);
                    coursesList.add(course);
                }
            }
        }
        return coursesList;
    }

    // 데이터를 엔티티로 변환 및 저장
    @Transactional
    public List<Training> saveCourses(List<CourseDto> courseDtos, String type) {
        List<Training> savedCourses = new ArrayList<>();
        for (CourseDto dto : courseDtos) {
            // 길이 조정 로직 추가
            adjustFieldLengths(dto);
            // type 설정
            dto.setType(type);
            System.out.println(dto);

            Training savedCourse = saveOrUpdateTraining(dto);
            savedCourses.add(savedCourse);
        }
        System.out.println(savedCourses);
        return savedCourses;
    }

    // 필드 길이 조정
    public void adjustFieldLengths(CourseDto course) {
        // contentsName 필드 길이 조정
        if (course.getContentsName() != null && course.getContentsName().length() > 255) {
            course.setContentsName(course.getContentsName().substring(0, 255));
        }

        // trainingGoal 필드 길이 조정
        if (course.getTrainingGoal() != null && course.getTrainingGoal().length() > 255) {
            course.setTrainingGoal(course.getTrainingGoal().substring(0, 255));
        }

        // trainingSummary 필드 길이 조정
        if (course.getTrainingSummary() != null && course.getTrainingSummary().length() > 255) {
            course.setTrainingSummary(course.getTrainingSummary().substring(0, 255));
        }
    }

    // 타입 별로 트레이닝 데이터 조회
    public List<Training> getTrainingsByType(String type) {
        // 해당 타입에 대한 데이터 조회 로직 구현
        return trainingRepository.findByType(type);
    }

    // 같은 데이터일 경우 update
    @Transactional
    public Training saveOrUpdateTraining(CourseDto courseDto) {
        Training existingTraining = trainingRepository.findByCourseSeq(courseDto.getCourseSeq());

        if (existingTraining != null) {
            updateTrainingFromDto(existingTraining, courseDto);
            return trainingRepository.save(existingTraining);
        } else {
            Training newTraining = convertToEntity(courseDto);
            return trainingRepository.save(newTraining);
        }
    }

    private void updateTrainingFromDto(Training training, CourseDto courseDto) {
        training.setApplyUrl(courseDto.getApplyUrl());
        training.setContentsName(courseDto.getContentsName());
        training.setCourseSeq(courseDto.getCourseSeq());
        training.setDetailUrl(courseDto.getDetailUrl());
        training.setCourseName(courseDto.getCourseName());
        training.setFileUrl(courseDto.getFileUrl());
        training.setMajorCategoryName(courseDto.getMajorCategoryName());
        training.setMiddleCategoryName(courseDto.getMiddleCategoryName());
        training.setTrainingGoal(courseDto.getTrainingGoal());
        training.setTrainingSummary(courseDto.getTrainingSummary());
        training.setOnlineTrainingTime(courseDto.getOnlineTrainingTime());
        training.setType(courseDto.getType());
    }

}

package efub.ebmt.eeojum.domain.resume.controller;

import com.google.gson.Gson;
import efub.ebmt.eeojum.domain.resume.domain.Resume;
import efub.ebmt.eeojum.domain.resume.domain.ResumeStatus;
import efub.ebmt.eeojum.domain.resume.dto.request.ResumeRequest;
import efub.ebmt.eeojum.domain.resume.dto.response.ResumeResponse;
import efub.ebmt.eeojum.domain.resume.service.ResumeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ResumeControllerTest {
    @InjectMocks
    private ResumeController resumeController;

    @Mock
    private ResumeService resumeService;

    private MockMvc mockMvc;

    @BeforeEach
     public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(resumeController).build();
    }

    @Test
    @DisplayName("이력서 생성 성공")
    void createResumeSuccess() throws Exception {

        //given
        ResumeRequest request = resumeRequest();
        ResumeResponse response = resumeResponse();

        doReturn(response).when(resumeService).addResume(any(ResumeRequest.class));

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/resume")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(request))
        );

        //then
        MvcResult mvcResult = resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("resumeId", response.getResumeId()).exists())
                .andExpect(jsonPath("title").isEmpty())
                .andExpect(jsonPath("introduction").isEmpty())
                .andExpect(jsonPath("isPrivate", false).exists())
                .andExpect(jsonPath("resumeStatus", "NOT_ASKED").exists())
                .andExpect(jsonPath("jobName").isEmpty())
                .andExpect(jsonPath("feedbackCnt", 0L).exists())
                .andExpect(jsonPath("modifiedAt").isEmpty())
                .andReturn();
    }

    private ResumeRequest resumeRequest() {
        return new ResumeRequest(1L);
    }

    private ResumeResponse resumeResponse(){
        return new ResumeResponse(new Resume(1L, 1L, null, null, false, ResumeStatus.NOT_ASKED, null), 0L);
    }
}
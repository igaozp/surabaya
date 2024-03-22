package xyz.andornot.log;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LogControllerTest {
    @Resource
    private MockMvc mockMvc;

    @Test
    public void printLogWithValidInfo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/log/print")
                        .param("info", "validInfo"))
                .andExpect(status().isOk())
                .andExpect(content().string("Print some log and info:validInfo"));
    }

    @Test
    public void printLogWithEmptyInfo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/log/print")
                        .param("info", ""))
                .andExpect(status().isOk())
                .andExpect(content().string("Print some log and info:"));
    }

    @Test
    public void printLogWithoutInfo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/log/print"))
                .andExpect(status().isBadRequest());
    }
}
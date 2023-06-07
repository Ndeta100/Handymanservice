package com.handi.handi_02;



import com.handi.handi_02.controller.RestController;
import com.handi.handi_02.entity.HandyMan;
import com.handi.handi_02.service.HandyManService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
class Handi02ApplicationTests {
    @Mock
    private HandyManService handyManService;

    @InjectMocks
    private RestController restController;

    private MockMvc mockMvc;

    @Test
    public void testGetAllHandyManServices() throws Exception {
        // Prepare test data
        HandyMan handyMan1 = new HandyMan(1, "John", "Doe", "123456789", "Address",
                true, "Service", "Service Description");
        HandyMan handyMan2 = new HandyMan(2, "Jane", "Smith", "987654321", "Address",
                false, "Service", "Service Description");
        List<HandyMan> handyManList = Arrays.asList(handyMan1, handyMan2);

        // Mock the service method
        when(handyManService.getAllHandyMan()).thenReturn(handyManList);

        // Perform GET request and validate response
        mockMvc.perform(MockMvcRequestBuilders.get("/handyman"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].contactNo").value("123456789"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].address").value("Address"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].availability").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].serviceName").value("Service"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].serviceDesc").value("Service Description"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName").value("Jane"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].lastName").value("Smith"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].contactNo").value("987654321"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].address").value("Address"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].availability").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].serviceName").value("Service"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].serviceDesc").value("Service Description"));
    }



}

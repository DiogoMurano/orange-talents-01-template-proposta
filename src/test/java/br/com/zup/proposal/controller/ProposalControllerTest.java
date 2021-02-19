package br.com.zup.proposal.controller;

import br.com.zup.proposal.controller.request.AddressRequest;
import br.com.zup.proposal.controller.request.ProposalRequest;
import br.com.zup.proposal.controller.response.ProposalResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@PropertySource("classpath:application-test.properties")
class ProposalControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void mustReturnProposalResponse() throws Exception {
        ProposalRequest request = new ProposalRequest("903.671.604-71",
                "aanthonymatheusoliveira@achievecidadenova.com.br",
                "Anthony Matheus Oliveira",
                new AddressRequest("58732-970",
                        "Rua Valdeci Sales 285",
                        "Centro",
                        "Apt 271",
                        "Areia de Baraúnas",
                        "PB"),
                BigDecimal.valueOf(15000));

        MvcResult proposalResult = mockMvc.perform(post("/api/v1/proposal")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn();

        String location = proposalResult.getResponse().getHeader("Location");
        Assert.notNull(location, "location can't be null.");

        MvcResult result = mockMvc.perform(get(location)
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        ProposalResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), ProposalResponse.class);
        Assert.notNull(response, "response can't be null.");
    }

}
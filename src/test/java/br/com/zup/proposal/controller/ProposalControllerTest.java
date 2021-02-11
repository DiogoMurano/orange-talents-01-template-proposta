package br.com.zup.proposal.controller;

import br.com.zup.proposal.controller.request.AddressRequest;
import br.com.zup.proposal.controller.request.ProposalRequest;
import br.com.zup.proposal.model.Proposal;
import br.com.zup.proposal.repository.ProposalRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProposalControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProposalRepository repository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void mustCreateAProposal() throws Exception {
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

        mockMvc.perform(post("/api/v1/proposal")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        Optional<Proposal> optional = repository.findByDocument("903.671.604-71");

        assertAll(() -> {
            assertTrue(optional.isPresent());
            assertNotNull(optional.get());
        });
    }

    @Test
    void mustNotCreateAProposal() throws Exception {
        ProposalRequest request = new ProposalRequest("903.671.604-7",
                "aanthonymatheusoliveira@achievecidadenova.com.br",
                "Anthony Matheus Oliveira",
                new AddressRequest("58732-970",
                        "Rua Valdeci Sales 285",
                        "Centro",
                        "Apt 271",
                        "Areia de Baraúnas",
                        "PB"),
                BigDecimal.valueOf(15000));

        mockMvc.perform(post("/api/v1/proposal")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        Optional<Proposal> optional = repository.findByDocument("903.671.604-7");

        assertFalse(optional.isPresent());
    }

}
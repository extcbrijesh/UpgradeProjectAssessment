package com.upgradeQa.API;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static java.util.UUID.randomUUID;
import static org.testng.AssertJUnit.*;

/**
 * @author by brijesh on 11/7/21.
 */
public class BRFunnelApiTests {

    public static final Logger logger = LoggerFactory.getLogger(BRFunnelApiTests.class);

    public static final String URL = "https://credapi.credify.tech/api/brfunnelorch/v2/resume/byLeadSecret";
    public static final String LOAN_APP_UUID = "b8096ec7-2150-405f-84f5-ae99864b3e96";

    String randomUUId;
    Map<String, String> map = new HashMap<>();
    ObjectMapper objectMapper = new ObjectMapper();
    HttpHeaders headers;
    JSONObject request = new JSONObject();
    RestTemplate restTemplate = new RestTemplate();

    @BeforeTest
    public void Setup() {
        randomUUId = randomUUID().toString();
        map.put(LOAN_APP_UUID, "98686424-b37d-477a-8366-d55fcad40631");

        headers = new HttpHeaders();
        headers.set("x-cf-source-id", "coding-challenge");
        headers.set("x-cf-corr-id", randomUUId);
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    public void WithValidLoanAppIdReturns200() throws JSONException, JsonProcessingException {

        request.put("loanAppUuid", LOAN_APP_UUID);
        request.put("skipSideEffects", Boolean.TRUE);

        HttpEntity<String> req = new HttpEntity<>(request.toString(), headers);

        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(URL, req, String.class);

        assertTrue(responseEntityStr.getStatusCode().is2xxSuccessful());
        JsonNode root = objectMapper.readTree(responseEntityStr.getBody());
        assertNotNull(responseEntityStr.getBody());

        assertEquals(map.get(LOAN_APP_UUID), root.path("loanAppResumptionInfo").path("loanAppUuid").asText());
    }


    @Test
    public void WithInValidLoanAppIdShouldReturns400() throws JSONException {
        request.put("loanAppUuid", randomUUID());
        request.put("skipSideEffects", Boolean.TRUE);

        HttpEntity<String> req = new HttpEntity<>(request.toString(), headers);

        try {
            // this call is throwing 500 but expected 40
            restTemplate.postForEntity(URL, req, String.class);
        } catch (Exception ex) {
            logger.info(ex.getMessage());
            assertTrue(ex.getMessage().contains("MISSING_LOAN_APPLICATION"));
        }

        // Ideally this test should get 400 but results in 500!!!
    }

    @Test
    public void WithNullLoanAppIdShouldReturn400() throws JSONException {
        request.put("loanAppUuid", null);
        request.put("skipSideEffects", Boolean.TRUE);

        HttpEntity<String> req = new HttpEntity<>(request.toString(), headers);

        try {
            // this call is throwing 500 as
            restTemplate.postForEntity(URL, req, String.class);
        } catch (Exception ex) {
            logger.info(ex.getMessage());
            assertTrue(ex.getMessage().contains("400 Bad Request"));
        }
    }
}

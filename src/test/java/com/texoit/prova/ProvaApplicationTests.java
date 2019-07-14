package com.texoit.prova;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.texoit.models.RetornoJson;
import java.io.IOException;
import java.net.MalformedURLException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ProvaApplicationTests {

    private RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        restTemplate = new RestTemplate();
    }

    @Test
    public void deveRetornarMatthewVaughn() throws MalformedURLException, IOException {
        String expected = "Matthew Vaughn";

        String response = restTemplate.getForObject("http://localhost:8080/intervalo_premios", String.class);

        RetornoJson retorno = objectMapper.readValue(response, RetornoJson.class);
        String producer = retorno.getMax().get(0).getProducer();

        Assert.assertEquals(expected, producer);
    }

    @Test
    public void deveRetornarJoelSilver() throws MalformedURLException, IOException {
        String expected = "Joel Silver";

        String response = restTemplate.getForObject("http://localhost:8080/intervalo_premios", String.class);

        RetornoJson retorno = objectMapper.readValue(response, RetornoJson.class);
        String producer = retorno.getMin().get(0).getProducer();

        Assert.assertEquals(expected, producer);
    }

}

package me.dolia.lab.microservices.discovery;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
    properties = "logging.level.org.springframework.boot.test.web.client=trace",
    webEnvironment = WebEnvironment.RANDOM_PORT
)
public class DiscoveryServiceApplicationTests {

  @LocalServerPort
  private int port;
  @Autowired
  private TestRestTemplate rest;

  @Test
  public void eurekaEndpointIsAvailable() {
    var url = "http://localhost:" + port + "/";

    var response = rest.getForEntity(url, String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }
}
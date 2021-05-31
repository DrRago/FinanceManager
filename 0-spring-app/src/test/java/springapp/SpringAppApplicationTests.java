package springapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import springapp.controller.FinanceController;
import springapp.controller.LoginController;
import springapp.controller.RegisterController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringAppApplicationTests {

    @Autowired
    private FinanceController financeController;
    @Autowired
    private LoginController loginController;
    @Autowired
    private RegisterController registerController;

    @Test
    void contextLoads() {
        assertThat(financeController).isNotNull();
        assertThat(loginController).isNotNull();
        assertThat(registerController).isNotNull();
    }

}

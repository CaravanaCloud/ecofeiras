package ecomarkets.domain.notification.email;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class EmailTemplateTest {
    @Inject
    EmailTemplate emailTemplate;

    @Test
    public void testTemplateEmailBody(){
        String emailBody = emailTemplate.getBody("Fulano", 1234l, 10.5, "Conferido");

        assertTrue(emailBody.contains("Prezado(a) PARCEIRO(A) Fulano"));
        assertTrue(emailBody.contains("Sua Cesta 1234 foi Conferido com SUCESSO."));

    }
}

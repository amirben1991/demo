package fr.eni.demo.bll;

import fr.eni.demo.dal.EmployeDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class TestEmployeService {

    // On injecte EmployeService
    @Autowired
    private EmployeService employeService;

    // Pour couvrir toutes les lignes de notre EmployeServiceImpl
    @Test
    void test_employeNull() {
        Assertions.assertThrows(RuntimeException.class, ()-> employeService.ajouter(null));

    }
}

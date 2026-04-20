package fr.eni.demo.bll;

import fr.eni.demo.bo.Employe;
import fr.eni.demo.dal.EmployeDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class TestEmployeService {

    // On injecte EmployeService
    @Autowired
    private EmployeService employeService;


    @Mock


    private EmployeDAO employeDAO;

    // Pour couvrir toutes les lignes de notre EmployeServiceImpl
    @Test
    void test_employeNull() {
        Assertions.assertThrows(RuntimeException.class, ()-> employeService.ajouter(null));
    }

    @Test
    void test_ajout_employe_imma_existe() {

        String immatriculation = "Test01";
        Employe employe = Employe.builder()
                .email("emailTest")
                .nom("NomTest")
                .prenom("PrenomTest")
                .immatriculation(immatriculation)
                .build();

        Mockito.when(employeDAO.findByImmatriculation(immatriculation)).thenReturn(Optional.of(employe));

        RuntimeException e = Assertions.assertThrows(RuntimeException.class, ()-> employeService.ajouter(employe));

        Assertions.assertEquals("L'immatriculation doit être unique",e.getMessage());
    }

    @Test
    void test_ajout_employe_ok() {

        String immatriculation2 = "Test02";
        Employe employe2 = Employe.builder()
                .email("emailTest")
                .nom("NomTest")
                .prenom("PrenomTest")
                .immatriculation(immatriculation2)
                .build();


        Mockito.when(employeDAO.findAll()).thenReturn(List.of(employe2));
        List<Employe> listeEmploye = employeService.chargerTousLesEmployes();

        org.assertj.core.api.Assertions.assertThat(listeEmploye).hasSize(2);

        log.info(employe2.toString());

//        Mockito.when(employeDAO.findByImmatriculation(immatriculation)).thenReturn(Optional.empty());
//
//        Assertions.assertDoesNotThrow(() -> employeService.ajouter(employe));
    }
}

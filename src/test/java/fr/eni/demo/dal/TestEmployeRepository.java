package fr.eni.demo.dal;

import fr.eni.demo.bo.Employe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@DataJpaTest
@Slf4j
public class TestEmployeRepository {

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private EntityManager entityManager;

    public void test_saveEmploye() {
        Employe employe = Employe.builder()
                .mail("email1@gmail.com")
                .nom("NomTest1")
                .prenom(PrenomTest1)
                .immatriculation("TESTIMMAT1")
                .numDom("0212457898")
                .numPortable("0613457895")
                .build();


    Employe employeDB = employeRepository.save(employe);

    log.info(employeDB.toString());

    }



}

package fr.eni.demo.bll;

import fr.eni.demo.bo.Employe;
import fr.eni.demo.dal.EmployeDAO;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeServiceImpl implements EmployeService {



    private EmployeDAO employeDAO;

    // Communiquer l'employé à la coucher DAO
    @Override
    public void ajouter(Employe e) {
        // Régles de festion
        if(e == null) {
            throw new RuntimeException("L'employé ne doit pas être null");
        }

        if(e.getNom().isBlank()) {
            throw new RuntimeException("Le nom de l'employé est obligatoire");
        }

        if(e.getPrenom().isBlank()) {
            throw new RuntimeException("Le prénom est obligatoire");
        }

        Optional<Employe> optionalEmploye = employeDAO.findByImmatriculation(e.getImmatriculation());
        if(optionalEmploye.isPresent()) {
            throw new RuntimeException("L'immatriculation doit être unique");
        }


        employeDAO.create(e);
    }


    @Override
    public List<Employe> chargerTousLesEmployes() {
        return employeDAO.findAll();
    }
}

package es.dgc.gesco.service.facade;

import es.dgc.gesco.model.modules.email.db.entity.Email;
import es.dgc.gesco.service.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailFacade {

    @Autowired
    private EmailService emailService;

    public void saveEmail(List<Email> emailList){
        emailList.forEach(email -> emailService.saveEmail(email));
    }



}

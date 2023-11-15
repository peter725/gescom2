package es.dgc.gesco.service.service;

import es.dgc.gesco.model.modules.email.db.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.dgc.gesco.service.repository.EmailRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Autowired
    private final EmailRepository emailRepository;

    public void saveEmail(Email email){
        emailRepository.save(email);
    }

    public void deleteEmail(Email email){
        emailRepository.delete(email);
    }

    public Email getEmail(Long  id){
        return emailRepository.findById(id).get();
    }
    
}

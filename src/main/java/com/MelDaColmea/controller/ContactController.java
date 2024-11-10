package com.MelDaColmea.controller;


import com.MelDaColmea.model.ContactModel;
import com.MelDaColmea.service.ContactService;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contacto")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService){
        this.contactService = contactService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendContactEmail(@RequestBody ContactModel contactModel){
            try{
                contactService.sendEmail(contactModel.getName(),contactModel.getEmail(),contactModel.getSubject(),contactModel.getMessage());

                return ResponseEntity.ok("Correo enviado");

            } catch (MessagingException e) {

                return ResponseEntity.status(500).body("Error:"+e.getMessage());
            }

    }
}

package com.MelDaColmea.service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private final JavaMailSender mailSender;


    public ContactService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String name, String to, String subject, String body) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo("contactomeldacolmea@gmail.com");
        helper.setSubject(subject);

        // Usamos HTML para formatear el texto del correo
        String htmlBody = "<html>" +
                "<body>" +
                "<p><strong>Nombre:</strong> " + name + "</p>" +
                "<p><strong>Email:</strong> " + to + "</p>" +
                "<p><strong>Mensaje:</strong></p>" +
                "<p>" + body.replace("\n", "<br>") + "</p>" +
                "</body>" +
                "</html>";

        helper.setText(htmlBody, true); // true indica que estamos enviando HTML
        helper.setFrom(to);

        mailSender.send(mimeMessage);

        // Segundo correo: Respuesta automática al remitente
        MimeMessage mimeMessage1 = mailSender.createMimeMessage();
        MimeMessageHelper helper1 = new MimeMessageHelper(mimeMessage1, true);
        helper1.setTo(to);
        helper1.setSubject("Respuesta");

        // Respuesta también en formato HTML
        String responseBody = "<html>" +
                "<body>" +
                "<p>Hemos recibido tu email. Nos pondremos en contacto contigo lo antes posible.</p>" +
                "<p>Un saludo,</p>" +
                "<p><strong>Mel da Colmea</strong></p>" +
                "</body>" +
                "</html>";

        helper1.setText(responseBody, true); // true indica que estamos enviando HTML

        mailSender.send(mimeMessage1);
    }
}

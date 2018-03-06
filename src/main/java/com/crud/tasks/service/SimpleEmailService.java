package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class SimpleEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);

    public static final String NEW_TRELLO_CARD = "TRELLO_CARD_EMAIL";
    public static final String SCHEDULER_EMAIL = "SCHEDULER_EMAIL";

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailCreatorService mailCreatorService;

    public void send(final String mailType ,final Mail mail) {
        LOGGER.info("Starting email preparation...");

        try {
            javaMailSender.send(createMimeMessage(mailType, mail));
            LOGGER.info("Email has been sent");
        } catch (MailException e) {
            LOGGER.error("Failed to process emil sending: ", e.getMessage(), e);
        }
    }

    private MimeMessagePreparator createMimeMessage(final String mailType, final Mail mail ) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(createMail(mailType,mail.getMessage()),true);
        };
    }

    private String createMail(String mailType, String message) {
        switch (mailType) {
            case NEW_TRELLO_CARD:
                return mailCreatorService.buildTrelloCardEmail(message);

            case SCHEDULER_EMAIL:
                return mailCreatorService.buildSchedulerEmail(message);

            default:
                return message;
        }
    }
}

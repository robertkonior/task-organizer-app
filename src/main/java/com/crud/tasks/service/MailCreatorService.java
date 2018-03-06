package com.crud.tasks.service;

import com.crud.tasks.trello.config.AdminConfig;
import com.crud.tasks.trello.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class MailCreatorService {

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyConfig companyConfig;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://robertkonior.github.io");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("company_details", companyConfig);
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildSchedulerEmail(String message) {

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://robertkonior.github.io");
        context.setVariable("button", "Check on website");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("company_details", companyConfig);
        context.setVariable("is_friend", true);
        context.setVariable("review", "Today is :" + LocalDate.now() + "You should do:");

        return templateEngine.process("mail/created-scheduler-mail", context);
    }
}

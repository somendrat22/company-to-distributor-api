package com.supplynext.company_api.services;

import com.supplynext.company_api.models.CompanyEmployee;
import com.supplynext.company_api.models.User;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Slf4j
@Service
public class MailService {

    @Autowired
    TemplateEngine templateEngine;
    @Autowired
    JavaMailSender javaMailSender;

    public void sendInviteEmployeeMail(
            CompanyEmployee invitee,
            User inviter
    ) {
        log.info("Starting invite employee email process. CompanyEmployeeId={}",
                invitee.getSysId());

        try {
            // Prepare email context
            log.debug("Preparing email template context for inviteeEmail={}",
                    invitee.getEmail());

            Context context = new Context();
            context.setVariable("employeeName", invitee.getFullName());
            context.setVariable("companyName", invitee.getCompany().getCompanyName());
            context.setVariable("invitedBy", inviter.getFullName());
            context.setVariable("inviteLink", "https://www.google.com");

            log.debug("Processing email template invite-employee");
            String htmlMail = templateEngine.process("invite-employee", context);

            // Create email
            log.debug("Creating MimeMessage for inviteeEmail={}",
                    invitee.getEmail());
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper =
                    new MimeMessageHelper(mimeMessage);

            mimeMessageHelper.setTo(invitee.getEmail());
            mimeMessageHelper.setSubject(
                    String.format("Invitation from %s",
                            invitee.getCompany().getCompanyName())
            );
            mimeMessageHelper.setText(htmlMail, true);

            log.info("Sending invite email to {}", invitee.getEmail());
            javaMailSender.send(mimeMessage);

            log.info("Invite employee email sent successfully to {}",
                    invitee.getEmail());

        } catch (Exception e) {
            log.error(
                    "Failed to send invite employee email. CompanyEmployeeId={}, InviteeEmail={}",
                    invitee.getSysId(),
                    invitee.getEmail(),
                    e
            );
        }
    }

}

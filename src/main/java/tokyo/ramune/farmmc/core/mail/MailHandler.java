package tokyo.ramune.farmmc.core.mail;

import tokyo.ramune.farmmc.core.database.SQL;

import java.util.UUID;

public class MailHandler {
    public static void createTable() {
        if (SQL.tableExists("mail"))
            return;

        SQL.createTable("mail",
                "date DATE NOT NULL," +
                        "read BOOLEAN NOT NULL DEFAULT 0," +
                        "sender_uuid TEXT NOT NULL," +
                        "recipient_uuid TEXT NOT NULL," +
                        "subject TEXT NOT NULL," +
                        "message TEXT NOT NULL");
    }

    public static void send(Mail... mails) {
        for (Mail mail : mails) {
            SQL.insertData("date, sender_uuid, recipient_uuid, subject, message",
                    mail.getDate() + "," + mail.getSenderUniqueId() + "," + mail.getRecipientUniqueId() + "," + mail.getSubject() + "," + mail.getMessage(),
                    "mail");
        }
    }

    public static Mail getMails(UUID RecipientUniqueId) {
        return null;
    }

    public static void readMail(Mail mail) {

    }
}

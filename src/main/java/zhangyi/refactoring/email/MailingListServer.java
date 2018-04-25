package zhangyi.refactoring.email;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.io.IOException;

public class MailingListServer {
    public static final String SUBJECT_MARKER = "[list]";
    public static final String LOOP_HEADER = "X-Loop";

    public static void main(String[] args) {
        if (args.length != 8) {
            System.err.println("Usage: java MailingList <pop3Host> " +
                    "<smtpHost> <pop3user> <pop3password> " +
                    "<smtpuser> <smtppassword> <listname> " +
                    "<relayinterval>");
            return;
        }
        HostInformation host = new HostInformation(
                args[0], args[1], args[2], args[3],
                args[4], args[5]);
        String listAddress = args[6];
        int interval = new Integer(args[7]).intValue();
        Roster roster = null;
        try {
            roster = new FileRoster("roster.txt");
        } catch (Exception e) {
            System.err.println("unable to open roster.txt");
            return;
        }
        try {
            do {
                try {
                    Properties properties = System.getProperties();
                    Session session = Session.getDefaultInstance(
                            properties, null);
                    Store store = session.getStore("pop3");
                    store.connect(host.pop3Host, -1,
                            host.pop3User, host.pop3Password);
                    Folder defaultFolder = store.getDefaultFolder();
                    if (defaultFolder == null) {
                        System.err.println("Unable to open default folder");
                        return;
                    }
                    Folder folder = defaultFolder.getFolder("INBOX");
                    if (folder == null) {
                        System.err.println("Unable to get: "
                                + defaultFolder);
                        return;
                    }
                    folder.open(Folder.READ_WRITE);
                    process(host, listAddress, roster, session,
                            store, folder);
                } catch (Exception e) {
                    System.err.println(e);
                    System.err.println("(retrying mail check)");
                }
                System.err.print(".");
                try {
                    Thread.sleep(interval * 1000);
                } catch (InterruptedException e) {
                }
            } while (true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void process(
            HostInformation host, String listAddress, Roster roster,
            Session session, Store store, Folder folder)
            throws MessagingException {
        try {
            if (folder.getMessageCount() != 0) {
                Message[] messages = folder.getMessages();
                doMessage(host, listAddress, roster, session,
                        folder, messages);
            }
        } catch (Exception e) {
            System.err.println("message handling error");
            e.printStackTrace(System.err);
        } finally {
            folder.close(true);
            store.close();
        }
    }

    private static void doMessage(
            HostInformation host,
            String listAddress,
            Roster roster,
            Session session,
            Folder folder,
            Message[] messages) throws
            MessagingException, AddressException, IOException,
            NoSuchProviderException {
        FetchProfile fp = new FetchProfile();
        fp.add(FetchProfile.Item.ENVELOPE);
        fp.add(FetchProfile.Item.FLAGS);
        fp.add("X-Mailer");
        folder.fetch(messages, fp);
        for (int i = 0; i < messages.length; i++) {
            Message message = messages[i];
            if (message.getFlags().contains(Flags.Flag.DELETED))
                continue;
            System.out.println("message received: "
                    + message.getSubject());
            if (!roster.containsOneOf(message.getFrom()))
                continue;
            MimeMessage forward = new MimeMessage(session);
            InternetAddress result = null;
            Address[] fromAddress = message.getFrom();
            if (fromAddress != null && fromAddress.length > 0)
                result =
                        new InternetAddress(fromAddress[0].toString());
            InternetAddress from = result;
            forward.setFrom(from);
            forward.setReplyTo(new Address[]{
                    new InternetAddress(listAddress)});
            forward.addRecipients(Message.RecipientType.TO,
                    listAddress);
            forward.addRecipients(Message.RecipientType.BCC,
                    roster.getAddresses());
            String subject = message.getSubject();
            if (-1 == message.getSubject().indexOf(SUBJECT_MARKER))
                subject = SUBJECT_MARKER + " " + message.getSubject();
            forward.setSubject(subject);
            forward.setSentDate(message.getSentDate());
            forward.addHeader(LOOP_HEADER, listAddress);
            Object content = message.getContent();
            if (content instanceof Multipart)
                forward.setContent((Multipart) content);
            else
                forward.setText((String) content);
            Properties props = new Properties();
            props.put("mail.smtp.host", host.smtpHost);
            Session smtpSession =
                    Session.getDefaultInstance(props, null);
            Transport transport = smtpSession.getTransport("smtp");
            transport.connect(host.smtpHost,
                    host.smtpUser, host.smtpPassword);
            transport.sendMessage(forward, roster.getAddresses());
            message.setFlag(Flags.Flag.DELETED, true);
        }
    }
}

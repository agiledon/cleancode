package zhangyi.refactoring.email;

public class HostInformation {
    public final String pop3Host;
    public final String smtpHost;
    public final String pop3User;
    public final String pop3Password;
    public final String smtpUser;
    public final String smtpPassword;

    public HostInformation(String pop3Host, String smtpHost, String pop3User, String pop3Password, String smtpUser, String smtpPassword) {

        this.pop3Host = pop3Host;
        this.smtpHost = smtpHost;
        this.pop3User = pop3User;
        this.pop3Password = pop3Password;
        this.smtpUser = smtpUser;
        this.smtpPassword = smtpPassword;
    }
}

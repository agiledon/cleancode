package zhangyi.refactoring.email;

import javax.mail.Address;

public class FileRoster implements Roster {
    public FileRoster(String fileName) {
    }

    public boolean containsOneOf(Address[] from) {
        return false;
    }

    public Address[] getAddresses() {
        return new Address[0];
    }
}

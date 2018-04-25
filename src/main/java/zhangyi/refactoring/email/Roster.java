package zhangyi.refactoring.email;

import javax.mail.Address;

public interface Roster {
    boolean containsOneOf(Address[] from);

    Address[] getAddresses();
}

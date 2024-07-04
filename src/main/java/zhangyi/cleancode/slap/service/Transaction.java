package zhangyi.cleancode.slap.service;

import java.sql.SQLException;

public interface Transaction {
    void executeWith(TransactionSupport ts) throws SQLException;
}

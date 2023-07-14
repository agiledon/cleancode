package zhangyi.refactoring.demeter.ddd;

import zhangyi.refactoring.demeter.Cashier;

public interface CashierRepository {
    Cashier cashierOf(String cashierId);
}

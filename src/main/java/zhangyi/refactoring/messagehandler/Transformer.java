package zhangyi.refactoring.messagehandler;/*                                                                      *\
**                                                                      **
**      __  __ _________ _____          ©Mort BI                        **
**     |  \/  / () | () |_   _|         (c) 2015                        **
**     |_|\/|_\____|_|\_\ |_|           http://www.bigeyedata.com       **
**                                                                      **
\*                                                                      */

public class Transformer {
    public Transformer() {
    }

    public Transformer forCustomer(String name) {
        return this;
    }

    public MessageWriter transform() {
        return null;
    }
}

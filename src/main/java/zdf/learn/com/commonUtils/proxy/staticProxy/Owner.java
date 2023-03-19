package zdf.learn.com.commonUtils.proxy.staticProxy;

public class Owner implements Person {

    @Override
    public void rentHouse() {
        try {
            System.out.println("Owner.rentHouse() 房东怒了");
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'rentHouse'");
        }
    }
    @Override
    public void checkHouse() {
        System.out.println("房东开始吹牛逼");
    }
}

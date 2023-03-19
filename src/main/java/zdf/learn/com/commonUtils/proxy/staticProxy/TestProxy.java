package zdf.learn.com.commonUtils.proxy.staticProxy;

public class TestProxy {
    public static void main(String[] args) {
        /**
         * 用实现类，生成一个接口类
         */
        Person renter = new Renter();
        Person owner = new Owner();
        /**
         * 将构造出的接口类，让如代理器的构造器中，生成代理构造器
         */
        RenterProxy proxy = new RenterProxy(owner);
        RenterProxy proxyRenter = new RenterProxy(renter);
        /**
         * 执行代理
         */
        proxyRenter.checkHouse();
        proxy.checkHouse();
        proxyRenter.rentHouse();
        proxy.rentHouse();
        //
    }
}

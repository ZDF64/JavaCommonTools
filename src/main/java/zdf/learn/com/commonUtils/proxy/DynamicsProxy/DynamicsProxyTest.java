package zdf.learn.com.commonUtils.proxy.DynamicsProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class DynamicsProxyTest {
    public static void main(String[] args) {
        // 创建被代理的实例对象
        Person renter = new Renter();
        House theRoom = new DKRoom();
        // 创建InvocationHandler对象
        InvocationHandler renterHandler = new RenterInvocationHandler<Person>(renter);
        InvocationHandler roomHandler = new RenterInvocationHandler<House>(theRoom);
        // 然后使用Java原生代理启动方式，启动代理
        // 创建代理对象,代理对象的每个执行方法都会替换执行Invocation中的invoke方法
        Person renterProxy = (Person) Proxy.newProxyInstance(renter.getClass().getClassLoader() // 类加载器
                , new Class<?>[] { Person.class } // 继承的接口们
                , renterHandler); // 代理接口的实现类
        House room = (House) Proxy.newProxyInstance(theRoom.getClass().getClassLoader()
                , new Class<?>[] { House.class },
                roomHandler);
        room.computePrice();
        room.getRange();
        renterProxy.rentHouse();

    }
}

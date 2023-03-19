package zdf.learn.com.commonUtils.proxy.DynamicsProxy;

public class Renter implements Person{

    @Override
    public void rentHouse() {
        System.out.println("租客租房成功！");
    }
    
}

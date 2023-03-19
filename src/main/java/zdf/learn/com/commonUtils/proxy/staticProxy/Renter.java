package zdf.learn.com.commonUtils.proxy.staticProxy;

public class Renter implements Person {

    @Override
    public void rentHouse() {
        try {
            System.out.println("租客租房成功！");
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'rentHouse'");
        }
        
    }

    @Override
    public void checkHouse() {
        System.out.println("租客: 让我康康！");
    }
    
}

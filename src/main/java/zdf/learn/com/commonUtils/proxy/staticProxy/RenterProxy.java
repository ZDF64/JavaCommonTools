package zdf.learn.com.commonUtils.proxy.staticProxy;

public class RenterProxy implements Person {
    private Person person;

    public RenterProxy(Person person) {
        System.out.println(person.getClass().getSimpleName());
        this.person = person;
    }
    
    @Override
    public void rentHouse() {

        try {
            System.out.println("中介找房东租房，转租给租客！");
            person.rentHouse();
            System.out.println("中介给租客钥匙，租客入住！");
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'rentHouse'");
        }

    }

    @Override
    public void checkHouse() {
        person.checkHouse();
    }

}

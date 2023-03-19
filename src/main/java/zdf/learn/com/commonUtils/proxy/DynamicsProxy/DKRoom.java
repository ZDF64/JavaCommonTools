package zdf.learn.com.commonUtils.proxy.DynamicsProxy;

import java.math.BigDecimal;

public class DKRoom implements House{

    @Override
    public BigDecimal computePrice() {
        System.out.println("Price: $30000");
        return new BigDecimal("3000");
    }

    @Override
    public double getRange() {
        System.out.println("size 90m");
        return 90d;
    }
    
}

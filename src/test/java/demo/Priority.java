package demo;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class Priority {

    // @Ignore // is added from Junit but enabled is what you should use from testng
    @Test (priority = 0, enabled = false)
    public void testA(){
        System.out.println("A");
    }


    @Test (priority = -34)
    public void testB(){
        System.out.println("B");
    }

    @Test (priority = 1000)
    public void testC(){
        System.out.println("C");
    }

    @Test // default priority is 0, if there two prioirity with same values the alphabetical order will be used
    public void testD(){
        System.out.println("D");
    }

    @Test (priority = 2)
    public void testE(){
        System.out.println("E");
    }

}



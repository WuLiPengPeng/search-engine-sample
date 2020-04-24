import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class test01 {

    public static void main(String[] args){

        test01 t = new test01();
        //答案是：BBBCB
        t.t1();
        t.t2();
//        t.t3(); 将抛出异常
        t.t4();//0.1000000000000000055511151231257827021181583404541015625  和 0.1
        t.t5();
    }
    /*
        a:true
        b:false
        c:由硬件指令决定

        答案：b
     */
    public void t1(){
       float a = 1.0f - 0.9f ;
       float b = 0.9f - 0.8f ;
       System.out.println("t1()==================");
       System.out.println(a);
        System.out.println(b);
        if(a==b){
            System.out.println("true");
        }else{
            System.out.println("false");
        }
        System.out.println("t1()==================");
    }
    /*
        a:true
        b:false
        c:编译出错

        答案：b
     */
    public void t2(){
        Float a = Float.valueOf(1.0f - 0.9f);
        Float b = Float.valueOf(0.9f - 0.8f);
        System.out.println("t2()===========");
        System.out.println(a);
        System.out.println(b);
        if(a.equals(b)){
            System.out.println("true");
        }else{
            System.out.println("false");
        }
        System.out.println("t2()===========");
    }
    /*
         a:null
         b:抛出异常
         c:default
      */
    public void t3(){
       String param = null ;
       //将抛出空指针异常
       switch (param){
           case "null":
               System.out.println("null");
               break;
           default:
               System.out.println("default");
       }
    }
    /*
         a:两种复制方式一样
         b:推荐a方式
         c:推荐b方式

         答案：c
      */
    public void t4(){
        BigDecimal a = new BigDecimal(0.1);
        System.out.println(a);//0.1000000000000000055511151231257827021181583404541015625
        BigDecimal b = new BigDecimal("0.1");
        //0.1
        System.out.println(b);
    }
    private final  static Lock lock = new ReentrantLock();
    /*
        a:lock是非公平锁
        b:finally代码块不会抛出异常
        c:tryLock获取锁失败则直接往下执行

        答案：b
     */
    public void t5(){
       try {
           lock.tryLock();
       }catch(Exception e){
           e.printStackTrace();
       }finally {
           lock.unlock();
       }
    }



}

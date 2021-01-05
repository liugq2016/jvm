/**
 * @author lgq
 * @Description 演示对象利用finalize()自救一次,演示失败
 * @create 2021-01-05 23:27
 */
public class Exmpl_3_2 {
    public static Exmpl_3_2 SAVA_HOOK= null;

    public void isAlive(){
        System.out.println("yes,i am still alive");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize mothod executed!");
        Exmpl_3_2.SAVA_HOOK = this;
    }

    public static void main(String[] args) {
        //对象第一次拯救自己
        SAVA_HOOK = null;
        System.gc();
        // 因为Finalizer方法优先级很低需要等待一段时间
        try {
            Thread.sleep(500);
            if (SAVA_HOOK!=null){
                SAVA_HOOK.isAlive();
            }else {
                System.out.println("i am dead");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //第二次拯救自己失败
        SAVA_HOOK = null;
        System.gc();
        // 因为Finalizer方法优先级很低需要等待一段时间
        try {
            Thread.sleep(500);
            if (SAVA_HOOK!=null){
                SAVA_HOOK.isAlive();
            }else {
                System.out.println("i am dead");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Client {

    public static void main(String[] args) throws InterruptedException {
        int n = 20000000;

        int sum = 0;
        for (int i = 0; i < n; i++) {
            if(Prime.isPrime(i)){
                sum++;
            }
        }

        System.out.println(sum);

        int totalCore = 10;

        Prime.totalCore = totalCore;
        Prime.n = n;

        Prime[] primeArray = new Prime[totalCore];

        for (int i = 0; i < totalCore; i++) {
            primeArray[i] = new Prime(i);
            primeArray[i].start();
        }

        boolean flag = true;
        while(flag) {
            flag = false;
            for (int i = 0; i < totalCore; i++) {
                if (primeArray[i].isAlive()){
                    flag = true;
                }
            }
        }

        int sum2 = 0;
        for (int i = 0; i < totalCore; i++) {
            sum2 += primeArray[i].primeAmount;
        }
        System.out.println(sum2);
#
//        MyThread t1 = new MyThread("1");
//        t1.start();
//        MyThread t2 = new MyThread("2");
//        t2.start();
//
//        while (t1.isAlive() || t2.isAlive()){
//        }
//
//        System.out.println(MyThread.number);
//
//        Thread t3 = new Thread(new MyRunnable());
//        t3.start();

    }

}

class Prime extends Thread{
    static int totalCore;
    static int n;

    int name;
    int primeAmount;

    public Prime(int name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = name; i < n; i+=totalCore) {
            if(isPrime(i)){
                primeAmount++;
            }
        }
    }

    public static boolean isPrime(int number){
        boolean flag = true;
        if(number == 1){
            return false;
        }
        if(number == 2){
            return true;
        }

        for (int i = 2; i < Math.sqrt(number) + 1; i++) {
            if(number % i == 0){
                flag = false;
                break;
            }
        }
        return flag;
    }
}


class MyThread extends Thread{
    static int number;
    String name;

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            number++;
            super.run();
        }
    }
}

class MyRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("Hello");
    }
}




package JavaThread;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class WorkerThread implements Runnable{
    private String command;

    public WorkerThread(String s){
        this.command = s;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " start. Command = " + command);
        processCommand();
        System.out.println(Thread.currentThread().getName()+ " End.");
    }

    private void processCommand(){
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString(){
        return this.command;
    }
}

public class Main {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for(int i=0;i<10;i++){
            Runnable worker = new WorkerThread(" " + i);
            executorService.execute(worker);
        }

        executorService.shutdown();

        while(!executorService.isTerminated()){

        }
        System.out.println("Finished all threads");
    }
}

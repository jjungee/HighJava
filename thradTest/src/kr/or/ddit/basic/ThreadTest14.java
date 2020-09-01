package kr.or.ddit.basic;

public class ThreadTest14 {

   public static void main(String[] args) {
      // TODO Auto-generated method stub

      ThreadStopTest1 th1 = new ThreadStopTest1();
      
      th1.start();
      
      try {
         Thread.sleep(1000);
      } catch (InterruptedException e) {
      }

//      th1.stop();
      th1.setStop(true);
      
      ThreadStopTest2 th2 = new ThreadStopTest2();
      
      th2.start();
      try {
         Thread.sleep(1000);
      } catch (InterruptedException e) {
      }
      th2.interrupt();
      
      
   }

}


class ThreadStopTest1 extends Thread{
   
   private boolean stop;
   
   public void setStop(boolean stop) {
      this.stop = stop;
   }
   
   @Override
   public void run() {
      while(!stop){
         System.out.println("thread running ...");
      }
      System.out.println("자원정리..");
      System.out.println("쓰레드 종료");
   }
   
}

class ThreadStopTest2 extends Thread{
   
   @Override
   public void run() {
      /*
      //방법 1.
      try {
         while(true){
            System.out.println("Thread runnnnnnnnnn");
            Thread.sleep(1);
         }
      } catch (InterruptedException e) {
         System.out.println("자원정리");
         System.out.println("쓰레드 종료");
         
      }
   */
      
      //방법2.
      while(true){
         System.out.println("쓰레드 실행 .......~");
         // 방법 1
//         if(this.isInterrupted()){
//            break;
//         }
         //방법2
         if(Thread.interrupted()){
            break;
         }
         
      }
      System.out.println("자원정리");
      System.out.println("쓰레드 종료");
      
   }
}
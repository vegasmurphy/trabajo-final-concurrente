package Comunicacion;
import java.util.concurrent.locks.Condition; /* Se importan bibliotecas de clases Concurrentes */
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
   final Lock lock = new ReentrantLock();
   final Condition notFull  = lock.newCondition(); 
   final Condition notEmpty = lock.newCondition(); 

   final Object[] items = new Object[100];
   int putptr=0, takeptr=0, count=0;
   
   public boolean isEmpty(){
	   if(count==0){
		   return true;
	   }
	   else{return false;}
	   
   }
   public void put(Object x) throws InterruptedException {
     lock.lock();
     try {
       while (count == items.length){
         notFull.await();
        }
       items[putptr] = x;
       if (++putptr == items.length) putptr = 0;
       ++count;
       notEmpty.signal();
     } finally {
       lock.unlock();
     }
   }

   public Object take() throws InterruptedException {
     lock.lock();
     try {
       while (count == 0){
         notEmpty.await();
         }
       Object x = items[takeptr];
       if (++takeptr == items.length) takeptr = 0;
       --count;
       notFull.signal();
       return x;
     } finally {
       lock.unlock();
     }
   }
 }
 
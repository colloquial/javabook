package com.colloquial.basics;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SynchedPair {

    /*x SynchedPair.1 */
    private final ReadWriteLock mRwLock;
    private int mX, mY;

    public SynchedPair(boolean isFair) {
        mRwLock = new ReentrantReadWriteLock(isFair);
    }
    /*x*/

    /*x SynchedPair.2 */
    public void set(int x, int y) {
        try {
            mRwLock.writeLock().lock();
            mX = x;            
            mY = y;
        } finally {
            mRwLock.writeLock().unlock();
        }
    }
    /*x*/

    /*x SynchedPair.3 */
    public int[] xy() { 
        try {
            mRwLock.readLock().lock();
            return new int[] { mX, mY };
        } finally {
            mRwLock.readLock().unlock();
        }
    }
    /*x*/

    /*x SynchedPair.4 */
    public static void main(String[] args) throws 
        InterruptedException {
        final SynchedPair pair = new SynchedPair(true);
        Thread[] threads = new Thread[32];
        for (int i = 0; i < threads.length; ++i)
            threads[i] = new Thread(new Runnable() {
                    public void run() {
                        for (int i = 0; i < 2000; ++i) {
                            int[] xy = pair.xy();
                            if (xy[0] != xy[1])
                                System.out.println("error");
                            pair.set(i,i);
                            Thread.yield();
                        }
                    }
                });
        for (int i = 0; i < threads.length; ++i)
            threads[i].start();
        for (int i = 0; i < threads.length; ++i)
            threads[i].join();
        System.out.println("OK");
    }
    /*x*/

}
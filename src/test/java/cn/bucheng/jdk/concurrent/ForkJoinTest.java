package cn.bucheng.jdk.concurrent;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author ：yinchong
 * @create ：2019/7/2 14:49
 * @description：
 * @modified By：
 * @version:
 */
public class ForkJoinTest {

    @Test
    public void testForkJoin() throws ExecutionException, InterruptedException {
        ForkJoinPool joinPool = new ForkJoinPool(10);
        List<Integer> params = new LinkedList<>();
        for(int i=0;i<10000;i++){
            params.add(new Random().nextInt(1000));
        }
        long startTime = System.currentTimeMillis();
        BatchCalculate calculate = new BatchCalculate(params);
        ForkJoinTask<Integer> submit = joinPool.submit(calculate);
        Integer result = submit.get();
        long endTime = System.currentTimeMillis();
        System.out.println("use time:"+(endTime-startTime));
        System.out.println(result);

    }

    static class BatchCalculate extends RecursiveTask<Integer>{

        List<Integer> records;

        public BatchCalculate(List<Integer> records) {
            this.records = records;
        }

        @Override
        protected Integer compute() {
            int size = records.size();
            if(size<10){
              int sum =0;
                for (Integer record : records) {
                    sum+=record;
                }
                System.out.println("Thread:"+Thread.currentThread());
                return sum;
            }else{
                BatchCalculate firstTask = new BatchCalculate(records.subList(0,size/2));
                BatchCalculate secondTask = new BatchCalculate(records.subList(size/2,size));
                invokeAll(firstTask,secondTask);
                return firstTask.join()+secondTask.join();
            }
        }
    }
}

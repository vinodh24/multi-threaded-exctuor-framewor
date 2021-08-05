package com.vinodh.countdownlatch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
 
public class DataAccessor {
    private static ThreadPoolExecutor executor;
    private int timeout = 100000;
    static {
        executor = new ThreadPoolExecutor(10, 10, 1000, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1000));
    }
 
    public static void main(String[] args) {
        List<String> requests = new ArrayList<String>();
        for(int i=0; i<20; i++){
            requests.add("request:"+i);
        }
        DataAccessor dataAccessor = new DataAccessor();
 
        List<ProcessedResponse> results = dataAccessor.getDataFromService(requests);
        for(ProcessedResponse response:results){
            System.out.println("response"+response.toString()+"\n");
        }
        executor.shutdown();
    }
 
    public List<ProcessedResponse> getDataFromService(List<String> requests) {
        final CountDownLatch latch = new CountDownLatch(requests.size());
        List<SubmittedJob> submittedJobs = new ArrayList<SubmittedJob>(requests.size());
        for (String request : requests) {
            Future<ProcessedResponse> future = executor.submit(new GetAndProcessResponse(request, latch));
            submittedJobs.add(new SubmittedJob(future, request));
        }
        boolean isAllDone = false;
        try {
            isAllDone = latch.await(timeout, TimeUnit.MILLISECONDS);
            if (!isAllDone) {
                // some of the jobs not done
                System.out.println("some jobs not done");
            }
        } catch (InterruptedException e1) {
            // take care, or cleanup
            for (SubmittedJob job : submittedJobs) {
                job.getFuture().cancel(true);
            }
        }
        List<ProcessedResponse> results = new LinkedList<DataAccessor.ProcessedResponse>();
        for (SubmittedJob job : submittedJobs) {
            try {
                // before doing a get you may check if it is done
                if (!isAllDone && !job.getFuture().isDone()) {
                    // cancel job and continue with others
                    job.getFuture().cancel(true);
                    continue;
                }
                ProcessedResponse response = job.getFuture().get();
                results.add(response);
            } catch (ExecutionException cause) {
                // exceptions occurred during execution, in any
            } catch (InterruptedException e) {
                // take care
            }
        }
        return results;
    }
 
    private class SubmittedJob {
        final String request;
        final Future<ProcessedResponse> future;
 
        public Future<ProcessedResponse> getFuture() {
            return future;
        }
 
        public String getRequest() {
            return request;
        }
 
        SubmittedJob(final Future<ProcessedResponse> job, final String request) {
            this.future = job;
            this.request = request;
        }
    }
 
    private class ProcessedResponse {
        private final String request;
        private final String response;
 
        ProcessedResponse(final String request, final String response) {
            this.request = request;
            this.response = response;
        }
 
        public String getRequest() {
            return request;
        }
 
        public String getResponse() {
            return response;
        }
 
        public String toString(){
            return "[request:"+request+","+"response:"+ response+"]";
        }
    }
 
    private class GetAndProcessResponse implements Callable<ProcessedResponse> {
        private final String request;
        private final CountDownLatch countDownLatch;
 
        GetAndProcessResponse(final String request, final CountDownLatch countDownLatch) {
            this.request = request;
            this.countDownLatch = countDownLatch;
        }
 
        public ProcessedResponse call() {
            try {
                return getAndProcessResponse(this.request);
            } finally {
                countDownLatch.countDown();
            }
        }
 
        private ProcessedResponse getAndProcessResponse(final String request) {
            // do the service call
            // ........
            if("request:16".equals(request)){
                throw (new RuntimeException("runtime"));
            }
            return (new ProcessedResponse(request, "response.of." + request));
        }
    }
}

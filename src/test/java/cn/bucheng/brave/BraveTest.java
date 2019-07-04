package cn.bucheng.brave;

import com.github.kristofa.brave.*;
import com.github.kristofa.brave.http.HttpSpanCollector;
import org.junit.Test;
import zipkin.Span;
import zipkin.reporter.AsyncReporter;
import zipkin.reporter.Reporter;
import zipkin.reporter.Sender;
import zipkin.reporter.okhttp3.OkHttpSender;

import java.util.concurrent.TimeUnit;

/**
 * @author ：yinchong
 * @create ：2019/7/4 14:34
 * @description：braveTest
 * @modified By：
 * @version:
 */
public class BraveTest {

    @Test
    public void testBrave() {
        Brave.Builder builder = new Brave.Builder("test-yinchong");
        Brave brave = builder.build();
        ClientTracer tracer = brave.clientTracer();
        tracer.startNewSpan("test1");
        tracer.submitBinaryAnnotation("test", "this is test ");
        tracer.setClientSent();
        tracer.startNewSpan("test2");
        tracer.submitBinaryAnnotation("test2", "hahaha");
        tracer.setClientReceived();
    }

    @Test
    public void testReportData() {
        Brave.Builder builder = new Brave.Builder("bucheng");  //指定state
        builder.reporter(braveReporter());
        builder.traceSampler(Sampler.ALWAYS_SAMPLE);
        Brave brave = builder.build();

        ClientTracer tracer = brave.clientTracer();
        SpanId spandId = tracer.startNewSpan("test");
        System.out.println(spandId);
        tracer.submitBinaryAnnotation("test2", "this is test ");
        tracer.setClientSent();
        tracer.submitBinaryAnnotation("test", "this is test ");
        tracer.setClientReceived();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Reporter<Span> braveReporter() {
        String url = "http://192.168.31.51:9411/api/v1/spans";
        Sender sender = OkHttpSender.create(url);
        return AsyncReporter.builder(sender)
                .messageTimeout(500, TimeUnit.MILLISECONDS)
                .build();
    }

    @Test
    public void testAsyncReportData() {


    }
}

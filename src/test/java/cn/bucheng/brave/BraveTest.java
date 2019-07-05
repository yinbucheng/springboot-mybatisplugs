package cn.bucheng.brave;

import com.github.kristofa.brave.*;
import com.github.kristofa.brave.http.HttpSpanCollector;
import org.junit.After;
import org.junit.Test;
import org.springframework.util.StopWatch;
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
        for (int i = 0; i < 4; i++) {
            //创建Brave构建器
            Brave.Builder builder = new Brave.Builder("yinchong" + i);
            //设置Report用来上报数据
            builder.reporter(braveReporter());
            builder.traceSampler(Sampler.ALWAYS_SAMPLE);
            Brave brave = builder.build();
//             brave.serverSpanThreadBinder().getCurrentServerSpan().getSpan();
            //获取跟踪链路
            ClientTracer tracer = brave.clientTracer();
            //创建Span并获取生成的spandID
            SpanId spandId = tracer.startNewSpan("test" + i);
            //记录当前开始时间
            tracer.setClientSent();
            //设置其他属性的key和value
            tracer.submitBinaryAnnotation("test2", "this is test ");
            tracer.submitBinaryAnnotation("test", "this is test ");
            //记录结束开始时间
            tracer.setClientReceived();
            //将当前的spandId设置到链路中构成树形结构，这里内部是static的ThreadLocal变量
            brave.serverTracer().setStateCurrentTrace(spandId, "test");
        }
    }

    @After
    public void after() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

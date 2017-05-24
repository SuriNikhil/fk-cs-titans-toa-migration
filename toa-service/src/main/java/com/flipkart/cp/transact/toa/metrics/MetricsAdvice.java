//package com.flipkart.cp.transact.toa.metrics;
//
//import com.codahale.metrics.Counter;
//import com.codahale.metrics.JmxReporter;
//import com.codahale.metrics.MetricRegistry;
//import com.codahale.metrics.Timer;
//import com.flipkart.cp.transact.toa.util.common.Constants;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.lang.reflect.Method;
//import java.util.Properties;
//
//import static com.codahale.metrics.MetricRegistry.name;
//
//
///**
// * Created by padmanabh.kulkarni on 03/07/15.
// */
//@Aspect
////TODO when used?
/////@EnableAspectJAutoProxy
//public class MetricsAdvice {
//
//   // private static final Logger log = LoggerFactory.getLogger("Metrics");
//
//    private Timer timer;
//    private final MetricRegistry metricRegistry;
//    private Counter counter;
//    private JmxReporter jmxReporter;
//    private com.codahale.metrics.Meter meter;
//
//    public final static String METRIC_GROUP_NAME = "toa";
//    public static String activeEnvironment = "dev";
//
//    static {
//        // Reads the properties file based on environment and sets the thread properties
//        Properties buildProps = new Properties();
//
//        try {
//            File file = new File(Constants.TOA_CONFIG_FILE);
//            FileInputStream fis = new FileInputStream(file);
//            buildProps.load(fis);
//
//            if ( buildProps.getProperty("build.environment") != null) {
//                activeEnvironment = buildProps.getProperty("build.environment");
//            }
//
//        } catch (IOException e) {
//          //  log.error("Error in reading build.properties file {} {} {}", e, e.getMessage(), e.getStackTrace());
//        }
//
//       // log.info("activeEnvironment {}", activeEnvironment);
//    }
//
//    public MetricsAdvice(MetricRegistry metricRegistry) {
//     //   log.info("Starting jmxReporter");
//        this.metricRegistry = metricRegistry;
//        this.jmxReporter = JmxReporter.forRegistry(metricRegistry).inDomain(METRIC_GROUP_NAME).build();
//        jmxReporter.start();
//    }
//
//    @Around("timerAnnotation()")
//    public Object startTimer(ProceedingJoinPoint joinPoint) throws Throwable {
//        Timer.Context context = null;
//        try {
//            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//            Method method = joinPoint.getTarget().getClass().getMethod(
//                    methodSignature.getName(), methodSignature.getParameterTypes());
//            com.flipkart.cp.transact.toa.metrics.Timer myAnnotation = method.getAnnotation(com.flipkart.cp.transact.toa.metrics.Timer.class);
//            this.timer = this.metricRegistry.timer(name(activeEnvironment, myAnnotation.value(), "Timer"));
//            context = this.timer.time();
//        } catch (Exception e) {
//       //     log.error("Error while setting timer {} {} {}", e, e.getMessage(), e.getStackTrace());
//        }
//        Object object = null;
//        try {
//            object = joinPoint.proceed();
//        } catch (Throwable exception) {
//            this.counter = this.metricRegistry.counter(name(activeEnvironment, "Exception", "Count"));
//            counter.inc();
//            object = joinPoint.proceed();
//        } finally {
//            if (null != context) {
//
//                context.stop();
//            }
//        }
//        return object;
//    }
//
//    @Before("counterAnnotation()")
//    public void incrementCounter(JoinPoint joinPoint) {
//        try {
//            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//            Method method = joinPoint.getTarget().getClass().getMethod(
//                    methodSignature.getName(), methodSignature.getParameterTypes());
//            com.flipkart.cp.transact.toa.metrics.Counter myAnnotation = method.getAnnotation(com.flipkart.cp.transact.toa.metrics.Counter.class);
//            this.counter = this.metricRegistry.counter(name(activeEnvironment, myAnnotation.value(), "Count"));
//            counter.inc();
//        } catch (Exception e) {
//         //   log.error("Error while getting count {} {} {}", e, e.getMessage(), e.getStackTrace());
//        }
//    }
//
//    @Before("meterAnnotation()")
//    public void markMeter(JoinPoint joinPoint){
//        try{
//            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//            Method method = joinPoint.getTarget().getClass().getMethod(
//                    methodSignature.getName(), methodSignature.getParameterTypes());
//            com.flipkart.cp.transact.toa.metrics.Meter myAnnotation = method.getAnnotation(com.flipkart.cp.transact.toa.metrics.Meter.class);
//            this.meter = this.metricRegistry.meter(name(activeEnvironment, myAnnotation.value(), "Meter"));
//            meter.mark();
//        } catch (NoSuchMethodException e) {
//         //   log.error("Exception while marking meter {} {} {}", e, e.getMessage(), e.getStackTrace());
//        }
//
//    }
//
//    @Pointcut("@annotation(com.flipkart.cp.transact.toa.metrics.Counter)")
//    public void counterAnnotation(){}
//
//    @Pointcut("@annotation(com.flipkart.cp.transact.toa.metrics.Timer)")
//    public void timerAnnotation(){}
//
//    @Pointcut("@annotation(com.flipkart.cp.transact.toa.metrics.Meter)")
//    public void meterAnnotation(){}
//}

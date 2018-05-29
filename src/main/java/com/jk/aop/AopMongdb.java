package com.jk.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import com.jk.model.Company;
import com.jk.model.Logs;
import com.jk.model.Tpersonal;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author chang
 * @create 2018/5/24
 * @since 1.0.0
 */
@Aspect
@Component
public class AopMongdb {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    private  static  String url="";
    @Pointcut("execution(* com.jk.service.*.*(..))args(JoinPoint joinPoint)")
    public void webLog(){
    }

    @Before("webLog()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
       // ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
      //  HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
      //  url= request.getRequestURL().toString();
     /*   System.out.println("URL : " + request.getRequestURL().toString());
        System.out.println("HTTP_METHOD : " + request.getMethod());
        System.out.println("IP : " + request.getRemoteAddr());
        System.out.println("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        System.out.println("ARGS : " + Arrays.toString(joinPoint.getArgs()));*/
    /*    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        System.out.println("URL : " + request.getRequestURL().toString());
        System.out.println("HTTP_METHOD : " + request.getMethod());
        System.out.println("IP : " + request.getRemoteAddr());*/


    }

   @AfterReturning( pointcut = "webLog()")
    public void doAfterReturning(JoinPoint joinPoint) throws Throwable {

       ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
       cachedThreadPool.execute(new Runnable() {
           @Override
           public void run() {
               InetAddress addr = null;
               try {
                   addr = InetAddress.getLocalHost();
               } catch (UnknownHostException e) {
                   e.printStackTrace();
               }
               String ip=addr.getHostAddress().toString();
               Object ompany = redisTemplate.opsForValue().get(ip);
               String name="";
               Map<String,String>map=new HashMap<>();
               if(ompany!=null){

                   String str=ompany.toString();
                   String strs=str.substring(str.indexOf("{")+1,str.length()-1);
                   String [] arr=  strs.split(",");
                   System.out.println(strs);
                   for (int i=0; i<arr.length;i++){
                     //  map.put(arr[i].split("=")[0],arr[i].split("=")[1]);
                       String [] arr1= arr[i].split("=");
                       if(arr1.length>1){
                           map.put(arr1[0],arr1[1]);
                       }else{
                           map.put(arr1[0],"");
                       }
                   }
                   name= map.get("companyname");
                   if(name==null||"".equals(name)){
                       name=map.get("phone");
                   }
               }
               Object[] paramValues = joinPoint.getArgs();
               String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
               String params=null;
               for(int i=0;i<paramNames.length;i++){
                   params+=paramNames[i]+":"+paramValues[i]+",";
               }
               Logs logs=new Logs();
               logs.setDoituser(name);
               logs.setClazzName(joinPoint.getSignature().getDeclaringTypeName().toString());
               logs.setMethodName(joinPoint.getSignature().getName());
               logs.setParams(params);
               logs.setIsexception("正常");
               mongoTemplate.insert(logs);
               System.out.println("正常日志已储存");
               cachedThreadPool.shutdown();
           }
       });
       System.out.println("后置通知执行.....");
    }
  @AfterThrowing(pointcut = "webLog()")
    public void throwss(JoinPoint jp) throws UnknownHostException {
      ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
      cachedThreadPool.execute(new Runnable() {
          @Override
          public void run() {
              InetAddress addr = null;
              try {
                  addr = InetAddress.getLocalHost();
              } catch (UnknownHostException e) {
                  e.printStackTrace();
              }
              String ip = addr.getHostAddress().toString();
              Object ompany = redisTemplate.opsForValue().get(ip);
              String name = "";
              Map<String,String>map=new HashMap<>();
              if(ompany!=null){
                  String str=ompany.toString();
                  String strs=str.substring(str.indexOf("{")+1,str.length()-1);
                  String [] arr=  strs.split(",");
                  for (int i=0; i<arr.length;i++){
                      String [] arr1= arr[i].split("=");
                      if(arr1.length>1){
                          map.put(arr1[0],arr1[1]);
                      }else{
                          map.put(arr1[0],"");
                      }
                  }
                  name= map.get("companyname");
                  if(name==null||"".equals(name)){
                      name=map.get("phone");
                  }
              }
              Object[] paramValues = jp.getArgs();
              String[] paramNames = ((CodeSignature) jp.getSignature()).getParameterNames();
              String params = null;
              for (int i = 0; i < paramNames.length; i++) {
                  params += paramNames[i] + ":" + paramValues[i] + ",";
              }
              Logs logs = new Logs();
              logs.setDoituser(name);
              logs.setClazzName(jp.getSignature().getDeclaringTypeName().toString());
              logs.setMethodName(jp.getSignature().getName());
              logs.setParams(params);
              logs.setIsexception("异常");
              mongoTemplate.insert(logs);
              System.out.println("异常日志已储存");
              cachedThreadPool.shutdown();
          }
      });
          System.out.println("方法异常时执行.....");
      }

}
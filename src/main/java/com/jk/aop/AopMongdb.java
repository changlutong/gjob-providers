/**
 * Copyright (C), 2018-2018, jk有限公司
 * FileName: AopMongdb
 * Author:  常路通
 * Date:     2018/5/24 14:24
 * Description:
 * /**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: AopMongdb
 * Author:   chang
 * Date:     2018/5/24 14:24
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jk.aop;

import com.jk.model.Logs;
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
        InetAddress addr = InetAddress.getLocalHost();
        String ip=addr.getHostAddress().toString();

        Object ompany = redisTemplate.opsForValue().get(ip);
        String name="";
  /*      if(ompany!=null){
            Company company= (Company) ompany;
           String comname= company.getCompanyname();
           if(comname!=null&&!"".equals(comname)){
               name=comname;
           }else{

               Tpersonal tpersonal= (Tpersonal) ompany;
               if(tpersonal.getPhone()!=null&&!"".equals(tpersonal.getPhone())){
                   name=tpersonal.getPhone();
               }
           }
        }*/
       Map<String,String>map=new HashMap<>();
       if(ompany!=null){

           String str=ompany.toString();
           String strs=str.substring(str.indexOf("{")+1,str.length()-1);
         String [] arr=  strs.split(",");

         for (int i=0; i<arr.length;i++){
            map.put(arr[i].split("=")[0],arr[i].split("=")[1]);

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
        mongoTemplate.insert(logs);
        System.out.println("后置通知执行.....");
    }
  @AfterThrowing(pointcut = "webLog()")
    public void throwss(JoinPoint jp) throws UnknownHostException {
      InetAddress addr = InetAddress.getLocalHost();
      String ip = addr.getHostAddress().toString();
      Object ompany = redisTemplate.opsForValue().get(ip);
      String name = "";
   /*     if(ompany!=null){

            Company company= (Company) ompany;
            String comname= company.getCompanyname();
            if(comname!=null&&!"".equals(comname)){
                name=comname;
            }else{

                Tpersonal tpersonal= (Tpersonal) ompany;
                if(tpersonal.getPhone()!=null&&!"".equals(tpersonal.getPhone())){
                    name=tpersonal.getPhone();
                }
            }
        }*/

      Map<String,String>map=new HashMap<>();
      if(ompany!=null){

          String str=ompany.toString();
          String strs=str.substring(str.indexOf("{")+1,str.length()-1);
          String [] arr=  strs.split(",");

          for (int i=0; i<arr.length;i++){
              map.put(arr[i].split("=")[0],arr[i].split("=")[1]);

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
          System.out.println("方法异常时执行.....");
      }

}
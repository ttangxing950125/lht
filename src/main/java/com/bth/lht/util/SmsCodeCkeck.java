package com.bth.lht.util;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;


/*
pom.xml
<dependency>
  <groupId>com.aliyun</groupId>
  <artifactId>aliyun-java-sdk-core</artifactId>
  <version>4.0.3</version>
</dependency>
*/
    public class SmsCodeCkeck {
        public static String getPhoneCode (String phoneNm,String code){

            DefaultProfile profile = DefaultProfile.getProfile("default", "LTAIIptDvXrpAzBi", "0uXFBbdli8pNYmIO7za4JFzHavWjaw");
            IAcsClient client = new DefaultAcsClient(profile);

            CommonRequest request = new CommonRequest();
            //request.setProtocol(ProtocolType.HTTPS);
            request.setMethod(MethodType.POST);
            request.setDomain("dysmsapi.aliyuncs.com");
            request.setVersion("2017-05-25");
            request.setAction("SendSms");
            request.putQueryParameter("PhoneNumbers", phoneNm);
            request.putQueryParameter("SignName", "灯塔");
            request.putQueryParameter("TemplateCode", "SMS_150570533");
            request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
            CommonResponse response = null;
            try {
               response = client.getCommonResponse(request);
            } catch (ServerException e) {
                e.printStackTrace();
            } catch (ClientException e) {
                e.printStackTrace();
            }

            return  response.getData();
        }

        /**
         *
         * 生成随机数
         * @return
         */
        public String getRadomNum(){
            //Random random =new Random();
            int x=(int)(Math.random()*10000);
            String code = x +"";
            System.out.println(code);
            return code;
        }


    }


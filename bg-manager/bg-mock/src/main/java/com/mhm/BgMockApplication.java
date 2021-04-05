package com.mhm;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BgMockApplication {

	public static void main(String[] args) {
		SpringApplication.run(BgMockApplication.class, args);

//		//通过端口连接服务
//		WireMock.configureFor(9000);
//		//清空之前的配置
//		WireMock.removeAllMappings();
//
//		//调用 封装方法
//		mock("/user/2","user");
		//通过端口连接服务
		WireMock.configureFor(8080);
		//清空之前的配置
//		WireMock.removeAllMappings();

		//get请求
		WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo("/debug"))
		.willReturn(WireMock.aResponse()
		//body里面写 json
		.withBody("{\"name\":FantJ}")
		//返回状态码
		.withStatus(200)));
	}

//	private static void mock(String url, String filename) throws IOException {
//		ClassPathResource resource = new ClassPathResource("/wiremock/"+filename+".txt");
//		String content = FileUtil.readAsString(resource.getFile());
//
//		//get请求
//		WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo(url))
//		.willReturn(WireMock.aResponse()
//		//body里面写 json
//		.withBody(content)
//		//返回状态码
//		.withStatus(200)));
//	}
}

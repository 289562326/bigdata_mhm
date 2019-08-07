package com.example.demo;

import com.mhm.netty.server.NettyServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetSocketAddress;

@SpringBootApplication
public class BgNetty4Application implements CommandLineRunner {
	@Value("${netty.port}")
	private int port;

	@Value("${netty.url}")
	private String url;

	private NettyServer server = new NettyServer();

	public static void main(String[] args) {
		SpringApplication.run(BgNetty4Application.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		InetSocketAddress address = new InetSocketAddress(url,port);
		System.out.println("run  .... . ... "+url);
		server.start(address);
	}
}

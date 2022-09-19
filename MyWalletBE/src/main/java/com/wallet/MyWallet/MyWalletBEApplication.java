package com.wallet.MyWallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.wallet")
public class MyWalletBEApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyWalletBEApplication.class, args);
	}

}

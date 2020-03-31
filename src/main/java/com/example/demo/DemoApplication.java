package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
// 複数プロパティー読みこみ
// 外部ファイル読みだし：「file: /path/to/aaa.properties」 Javaバッチ実行ならば、jarファイル配置パスをルートパスとして指定
// 内部ファイル読み込み：「classpath:/path/to/bbb.properties」 resourcesディレクトリをルートパスとして指定
// ${spring.profiles.active}と指定することで環境毎のプロファイル指定が可能(production, staging, develop) develop指定の場合、app-key-develop.propertiesが読み込まれる
@PropertySource({"file:./config/app-key.properties","classpath:/config/app-key-${spring.profiles.active}.properties"})
//
//バッチ プロファイル文字列指定例
//java -jar sum_order_batch.jar --spring.profiles.active=develop
//バッチ パラメータとしてプロパティファイルを指定する
//java -jar sum_order_batch.jar --spring.config.location=file:./config/application.properties,classpath:/
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

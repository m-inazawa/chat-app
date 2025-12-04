// このファイルは、WebMvcConfigurerインターフェースです。
// WebMvcConfigurerインターフェース：SpringMVCの設定をカスタマイズするためのインターフェース
// デフォルトのSpring MVCの設定を上書きしたり、拡張したりする際に役立つ

package in.tech_camp.chat_app;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  //addResourceHandlers：画像やCSSなど、静的リソースのハンドリングをカスタマイズするためのメソッド
  public void addResourceHandlers(ResourceHandlerRegistry registry) {//引数のResourceHandlerRegistryは、静的ファイルの扱いをカスタマイズするためのオブジェクト
    registry.addResourceHandler("/uploads/**")
            .addResourceLocations("file:src/main/resources/static/uploads/");
  // /uploads/**というURLパターンで表示するリクエストが発生した場合は、 src/main/resources/static/uploads/ ディレクトリの内容を見る」ように設定
  }
}
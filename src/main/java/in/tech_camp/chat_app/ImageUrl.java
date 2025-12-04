//設定プロパティクラスもしくはコンフィギュレーションプロパティクラス（Configuration Properties Class）と呼ばれる
//設定ファイル（application.properties や application.ymlなど）から、構成プロパティを読み取るためのファイル

package in.tech_camp.chat_app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@Component//@Component:アプリ起動時にスキャンされ、SpringBootアプリケーション内でインスタンスを自動管理するための記述
@NoArgsConstructor//@NoArgsConstructorはその部品のコンストラクタには引数が不要であることを示す
public class ImageUrl {
  @Value("${image.url}")//@Value：設定ファイルの値をフィールドにマッピングするためのアノテーション
  private String url;

  public String getImageUrl(){
    return url;
  }
}



// この設定プロパティクラスで画像ファイルを取得できるように、image.URIをマッピングしたら、以下の手順でcontrollerの処理を実装する。
// １．@ModelAttributeでMessageFormを受け取る（これまでと同様）
// ２．application.propertiesで設定した画像の保存場所を取得
// ３．保存する画像のファイル名を決める
// ４．2,3から画像の保存パスを決定
// ５．MessageEntityのimageフィールドに4で決定した画像パスを設定
// ６．MessageEntityをMessageRepositoryに渡してmessagesテーブルに保存（これまでと同様）
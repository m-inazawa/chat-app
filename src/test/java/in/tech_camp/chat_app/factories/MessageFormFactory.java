// Factory クラス；
// オブジェクト指向プログラミングにおける設計パターン（デザインパターン）の一つである「Factory メソッドパターン」や「Abstract Factory パターン」に基づいて、オブジェクトの生成（インスタンス化）を専門に行うクラス

package in.tech_camp.chat_app.factories;

import org.springframework.mock.web.MockMultipartFile;

import com.github.javafaker.Faker;

import in.tech_camp.chat_app.form.MessageForm;

public class MessageFormFactory {
  private static final Faker faker = new Faker();

  public static MessageForm createMessage() {
    MessageForm messageForm = new MessageForm();

    messageForm.setContent(faker.lorem().sentence());
    messageForm.setImage(new MockMultipartFile("image", "image.jpg", "image/jpeg", faker.avatar().image().getBytes()));
// MockMultipartFile：ファイルのアップロード機能をテストする際に使用されるモックオブジェクト
// 以下のように、引数に値を与えて生成する
// new MockMultipartFile(画像を送るフォームのname属性, 画像ファイル名, 画像フォーマット, 画像データ)
    return messageForm;
  }
}
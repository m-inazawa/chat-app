// Factory クラス；
// オブジェクト指向プログラミングにおける設計パターン（デザインパターン）の一つである「Factory メソッドパターン」や「Abstract Factory パターン」に基づいて、オブジェクトの生成（インスタンス化）を専門に行うクラス

package in.tech_camp.chat_app.factories;

import com.github.javafaker.Faker;

import in.tech_camp.chat_app.form.RoomForm;

public class RoomFormFactory {
    private static final Faker faker = new Faker();

  public static RoomForm createRoom() {
    RoomForm roomForm = new RoomForm();

    roomForm.setName(faker.lorem().sentence());

    return roomForm;
  }
}
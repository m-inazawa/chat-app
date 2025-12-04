// Factory クラス；
// オブジェクト指向プログラミングにおける設計パターン（デザインパターン）の一つである「Factory メソッドパターン」や「Abstract Factory パターン」に基づいて、オブジェクトの生成（インスタンス化）を専門に行うクラス

package in.tech_camp.chat_app.factories;

import com.github.javafaker.Faker;

import in.tech_camp.chat_app.form.UserEditForm;

public class UserEditFormFactory {
  private static final Faker faker = new Faker();

  public static UserEditForm createEditUser() {
    UserEditForm userEditForm = new UserEditForm();

    userEditForm.setName(faker.name().username());
    userEditForm.setEmail(faker.internet().emailAddress());

    return userEditForm;
  }
}
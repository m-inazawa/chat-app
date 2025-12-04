package in.tech_camp.chat_app.system;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import in.tech_camp.chat_app.ChatAppApplication;

@ActiveProfiles("test")
@SpringBootTest(classes = ChatAppApplication.class)
@AutoConfigureMockMvc
public class UserIntegrationTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private UserService userServisce;

  @Test
  public void ログインしていない状態でトップページにアクセスした場合サインインページに移動する() throws Exception {
    mocMvc.perform(get("/"))
      .andExpect(status().isFound())
      .andExpect(redirectedUrl("http://localhost/users/logi"));
    // トップページに移動し、再度ログインページにリダイレクトされることを確認する
  }

  @Test
  public void ログインに成功しトップページに遷移する() throws Exception {
    // 予め、ユーザーをDBに保存する
    UserForm userForm = UserFormfactory.createUser();
    UserEntity userEntity = new UserEntity();
    userEntity.setEmail(userForm.getEmail());
    userEntity.setName(userForm.getName());
    userEntity.setPassword(userForm.getPassword());
    userService.createUserWithEncryptedPassword(userEntity);
    
    // サインインページに遷移する
    mockMvc.perform(get("/users/login"))
            .andExpect(status().isOk())
            .andExpect(view().name("users/login"));

    // 保存したユーザーでログインする
    MvcResult loginResult = mockMvc.perform(post("/login")
                                      .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                      .param("email", userForm.getEmail())
                                      .param("password", userForm.getPassword())
                                      .with(csrf()))
                              .andExpect(status().isFound())
                              .andExpect(redirectedUrl("/"))
                              .andReturn();

    // トップページに再度アクセスし、ログインできていることを確認する
    mockMvc.perform(get("/").session((MockHttpSession)loginResult.getRequest().getSession()))
            .andExpect(status().isOk())
            .andExpect(view().name("rooms/index"));
  }

  @Test
  public void ログインに失敗し再びサインインページに戻ってくる() throws Exception {
    // サインインページに遷移する
    mockMvc.perform(get("/users/login"))
            .andExpect(status().isOk())
            .andExpect(view().name("users/login"));

    // 誤ったユーザーでログインするとエラーパスにリダイレクトされる
    mockMvc.perform(post("/login")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("email", "hoge@hoge.com")
                    .param("password", "hogefuga")
                    .with(csrf()))
            .andExpect(status().isFound())
            .andExpect(redirectedUrl("/login?error"));  

    // エラーパスにリダイレクトされたとき、サインインのビューが表示される
    mockMvc.perform(get("/login?error").param("error", ""))
            .andExpect(status().isOk())
            .andExpect(view().name("users/login"));
  }
}
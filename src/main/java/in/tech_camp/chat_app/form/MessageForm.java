package in.tech_camp.chat_app.form;

import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

// import in.tech_camp.chat_app.validation.ValidationPriority1;
// import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MessageForm {
  // @NotBlank(message = "Name can't be blank",groups = ValidationPriority1.class)
  private String content;

  private MultipartFile image;

  public void ValidateMessage(BindingResult result) {
    if ((content == null || content.isEmpty()) && (image == null || image.isEmpty())) {
      result.rejectValue("content", "error.Message", "please enter either content or image");
    }
  }

}
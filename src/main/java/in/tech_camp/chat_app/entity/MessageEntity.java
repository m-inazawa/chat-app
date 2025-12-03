package in.tech_camp.chat_app.entity;

import java.security.Timestamp;

import lombok.Data;

@Data
public class MessageEntity {
  private Integer id;
  private String content;
  private String image;
  private Timestamp createdAt;
  private UserEntity user;
  private RoomEntity room;
}

// MessageEntityを新規作成し、フィールドを定義したら、
// UserEntity、RoomEntityから、関連するメッセージのリストを取得できるように各Entityのフィールドを定義する
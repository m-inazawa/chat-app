package in.tech_camp.chat_app.entity;

import java.util.List;

import lombok.Data;


@Data
public class RoomEntity {
  private Integer id;
  private String name;
  private List<RoomUserEntity> roomUsers;
  private List<UserEntity> messages;//UserEntity、RoomEntityから、関連するメッセージのリストを取得できるようにフィールドを定義
}

package in.tech_camp.chat_app.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import in.tech_camp.chat_app.entity.MessageEntity;

@Mapper
public interface MessageRepository {
  @Insert("INSERT INTO messages(content, image, user_id, room_id) VALUES(#{content}, #{image}, #{user.id}, #{room.id})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void insert(MessageEntity messageEntity);

  @Select("SELECT * FROM messages WHERE room_id = #{roomId}")
  @Results(value = {
    @Result(property = "createdAt", column = "created_at"),
// データベースはスネークケース、Javaはキャメルケースで命名するというルールがあり、MyBatisは異なるカラム・フィールドの場合マッピングされないため、この記述が必要
    @Result(property = "user", column = "user_id",
            one = @One(select = "in.tech_camp.chat_app.repository.UserRepository.findById"))
// 追加したメソッドにアソシエーションを追加している。
// MessageEntityのuserフィールドで、UserRepositoryのfindByIdを実行することで、投稿したユーザーのUserEntityを取得
  })
  List<MessageEntity> findByRoomId(Integer roomId);
}
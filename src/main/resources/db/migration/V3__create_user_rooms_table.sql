CREATE TABLE IF NOT EXISTS room_users (
  id        SERIAL  NOT NULL,
  user_id   INT     NOT NULL,
  room_id   INT     NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
  FOREIGN KEY (room_id) REFERENCES rooms(id) ON DELETE CASCADE
  -- ON DELETE CASCADEは関連するテーブルの値が削除された時に、このテーブルのレコードも一緒に削除するための設定
);
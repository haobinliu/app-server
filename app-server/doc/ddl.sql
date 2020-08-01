-- 用户发表的文章表
create table view
(
	view_index int(20) AUTO_INCREMENT PRIMARY KEY,
	user_id    int(20) NOT NULL,
	content    varchar(5000) NOT NULL COMMENT '内容',
	view_level int(1) DEFAULT 0 COMMENT '0：所有人 1：好友 2：付费 3：保留',
	created_time DateTime DEFAULT NOW() comment '发布时间',
	updated_time TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
)

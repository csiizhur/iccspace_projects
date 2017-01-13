delimiter //
create procedure query_users_message(IN toUserId VARCHAR(32))
begin
select u.nickName, u.headImg,A.* from (select case when from_userid=toUserId then to_userid else from_userid end as userId, 
		msg_content msgContent,date_format(create_time,'%Y-%m-%d %h:%i %p') msgTime,case when from_userid=toUserId then 1 else is_read end as readFlg
		from USER_MESSAGE m
		where (from_UserId=toUserId or to_UserId=toUserId )
		and create_time=(select MAX(create_time) from USER_MESSAGE where from_userid=m.from_userid and to_userid=m.to_userid or from_userid=m.to_userid and to_userid=m.from_userid) )A left join USER u on u.id=A.userid
;
end
//
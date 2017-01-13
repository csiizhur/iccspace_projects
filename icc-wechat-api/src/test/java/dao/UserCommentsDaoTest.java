package dao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icc.dao.CommentsDao;
import com.icc.dao.UserDao;
import com.icc.entity.Comments;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/spring/applicationContext.xml")
public class UserCommentsDaoTest {

	@Autowired
	private CommentsDao commentsDao;
	@Autowired
	private UserDao userDao;
	@Test
	public void testQueryAllData(){
		userDao.queryUUID();
		Comments com=new Comments();
		com.setCommentsContent("这是一个评论");
		commentsDao.insertUserComments(com);
	}
	
}

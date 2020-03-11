#带有验证码的登录功能实现，技术选型：Mysql、JDBCTemplate
##结构：
com.li.LoginTest
	checkCodeServlet：自制验证码
	loginServlet：登录业务层，调用UserDao的方法
	SuccessServlet：登录成功后转发到这里，显示用户名
com.li.domain
	User：用户实体类
com.li.dao	
	UserDao：数据处理
com.li.util
	JDBCUtils：JDBC工具类
	

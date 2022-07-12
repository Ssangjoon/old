package growup.mylist.web.listener;

import java.io.InputStream;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//역할:
// - 웹 애플리케이션이 시작될 때 Service 객체, DAO 객체, Mybatis 객체를 준비한다. 
public class ContextLoaderListener implements ServletContextListener{
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    // 웹애플리케이션이 시작되면 이 메서드가 호출된다. 

    //1) DB 커넥션풀 준비
    try {
      //1) Mybatis의 sqlSessionFactory  준비 
      String resource = "growup/mylist/conf/mybatis-config.xml";
      InputStream inputStream = Resources.getResourceAsStream(resource);
      SqlSessionFactory sqlSessionFactory =
          new SqlSessionFactoryBuilder().build(inputStream);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

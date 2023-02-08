package growup.mylist.web.listener;

import java.io.InputStream;
import javax.annotation.Resources;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import growup.mylist.controller.board.BoardAddController3;
import growup.mylist.controller.board.BoardDeleteController3;
import growup.mylist.controller.board.BoardDetailController3;
import growup.mylist.controller.board.BoardListController3;
import growup.mylist.controller.board.BoardUpdateController3;
import growup.mylist.service.BoardService;
import growup.mylist.service.impl.DefaultBoardService;

//역할:
// - 웹 애플리케이션이 시작될 때 Service 객체, DAO 객체, Mybatis, 페이지 컨트롤러 객체를 준비한다. 
//@WebListener
public class ContextLoaderListener2 implements ServletContextListener{
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    // 웹애플리케이션이 시작되면 이 메서드가 호출된다. 
    System.out.println("서비스 및 DAO, Mybatis, 페이지 컨트롤러 객체 준비!");

    //1) DB 커넥션풀 준비
    try {
      //1) Mybatis의 sqlSessionFactory  준비 
      String resource = "growup/mylist/conf/mybatis-config.xml";
      InputStream inputStream = Resources.getResourceAsStream(resource);
      SqlSessionFactory sqlSessionFactory =
          new SqlSessionFactoryBuilder().build(inputStream);

      // 2) 서비스 객체 생성
      BoardService boardService = new DefaultBoardService(sqlSessionFactory);
      //MemberService memberService = new DefaultMemberService(sqlSessionFactory);

      // 3) 페이지 컨트롤러 객체 생성 및 서블릿에서 서비스 객체를 사용할 수 있도록 
      //    ServletContext보관소에 저장한다.
      ServletContext 웹애플리케이션보관소 = sce.getServletContext();
      웹애플리케이션보관소.setAttribute("/board/list", new BoardListController3(boardService));
      웹애플리케이션보관소.setAttribute("/board/detail", new BoardDetailController3(boardService));
      웹애플리케이션보관소.setAttribute("/board/update", new BoardUpdateController3(boardService));
      웹애플리케이션보관소.setAttribute("/board/delete", new BoardDeleteController3(boardService));
      웹애플리케이션보관소.setAttribute("/board/add", new BoardAddController3(boardService));

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}

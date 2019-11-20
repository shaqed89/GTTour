package kr.goott.tour.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {
	@Autowired
	SqlSession sqlSession;
	
	//�Խ��� �̵�
	@GetMapping(value="/board/list")
	public ModelAndView getList(@RequestParam("commuPage") String commuPage,
								HttpServletRequest request) {
	
		//���������� 
		String pageNumStr = request.getParameter("pageNum");
		BoardVO vo = new BoardVO();
		if(pageNumStr!=null && !pageNumStr.equals("")) {
			vo.setPageNum(Integer.parseInt(pageNumStr));
		}
		BoardDAOInterface dao = sqlSession.getMapper(BoardDAOInterface.class);
		
		//����������
		vo.setCommuPage(commuPage);
//		System.out.println(vo.getCommuPage());
		//��ġŰ�� ��ġ����
		String searchKey = request.getParameter("searchKey");
		String searchWord = request.getParameter("searchWord");
		
		//�� ���ڵ� ��
		
		vo.setTotalRecord(dao.boardTotalRecord(vo.getCommuPage()));
//		System.out.println("totalRecord="+vo.getTotalRecord());
		//vo.setTotalRecord(1);
		//�� ������ ��
		if(vo.getTotalRecord() % vo.getOnePageRecord()==0) {
			vo.setTotalPage(vo.getTotalRecord()/vo.getOnePageRecord());
		}else {
			vo.setTotalPage(vo.getTotalRecord()/vo.getOnePageRecord()+1);
		}
		
		//������ ��ȣ�� ���������� ���
		vo.setStartPage((vo.getPageNum()-1)/vo.getOnePageMax()*vo.getOnePageMax()+1);
//		System.out.println("111111111");
		//�ش������� ���ڵ� ����
		List<BoardVO> lst = dao.getAllRecord(vo);
//		System.out.println("2222222");
		
		//���������� ������ ������
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		mav.addObject("lst", lst);
		mav.setViewName("/board/"+commuPage);
	
		if(vo.getCommuPage()=="travelQ"||vo.getCommuPage().equals("travelQ")) {commuPage = "InfoCenter.jsp";}
		
		return mav;
	}
}
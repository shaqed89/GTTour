package kr.goott.tour.product;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.goott.tour.register.RegisterVO;
import oracle.net.aso.d;


@Controller
public class ProductController {
	@Autowired
	SqlSession sqlSession;
	
	//여행상품 페이지로 이동
	@RequestMapping("/product/product_list")
	public ModelAndView product_list(ProductVO vo) {
		ProductDAOInterface dao = sqlSession.getMapper(ProductDAOInterface.class);
		
		List<ProductVO> list = dao.getAllRecord(vo);
		
//		ProductVO vo2 = list.get(0);
//		System.out.println(vo2.getGoodCode()+","+vo2.getTravelType());
				
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("product/product_list");
		
		return mav;
	}
	
	//추천상품 페이지로 이동
	@RequestMapping("/product/product_recommendList")
	public ModelAndView product_recommendList(ProductVO vo) {
		ProductDAOInterface dao = sqlSession.getMapper(ProductDAOInterface.class);
		
		List<ProductVO> list1 = dao.travelTypeList("가족여행");
		List<ProductVO> list2 = dao.travelTypeList("수학여행");
		List<ProductVO> list3 = dao.travelTypeList("현장학습");
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("list1", list1);
		mav.addObject("list2", list2);
		mav.addObject("list3", list3);
		
		mav.setViewName("product/product_recommendList");
		
		return mav;
	}
	
	//여행 일정 페이지로 이동
	@RequestMapping("/product/product_view")
	public ModelAndView product_view(@RequestParam("goodCode") String goodCode,
									 HttpServletRequest req) {
//	  System.out.println(goodCode+","+userId);
	  String userId = (String)req.getSession().getAttribute("logid");
      
	  ProductDAOInterface dao = sqlSession.getMapper(ProductDAOInterface.class);
      ProductVO vo = new ProductVO();
      vo = dao.selectRecord(goodCode);
      
      List<ScheduleVO> list = dao.selectAllSchedule(vo.getGoodCode());
      ModelAndView mav = new ModelAndView();
      
      if(userId != null) {  
    	  List<Integer> list2 = new ArrayList<Integer>();
    	for (int j = 0; j < list.size(); j++) {
//    		System.out.println(list.get(j).getSc_num());
    		list2.add(dao.selectUserSchedule(list.get(j).getSc_num(), userId));
//    		System.out.println(dao.selectUserSchedule(list.get(j).getSc_num(), userId));
		}
    	 mav.addObject("list2", list2);
      }
      
      mav.addObject("list", list);
      mav.addObject("vo", vo);
      mav.setViewName("product/product_view");
      
      return mav;
	}
	
	
	//여행상품 상세페이지로 이동
	@RequestMapping("/product/product_detail")
	public ModelAndView product_detail(@RequestParam("goodCode") String goodCode,
								 	   @RequestParam("sc_num") int sc_num,
								 	   HttpServletRequest req) {
		//로그인 아이디
		String userId = (String)req.getSession().getAttribute("logid");
		
		//상품코드로 상품정보
		ProductDAOInterface dao = sqlSession.getMapper(ProductDAOInterface.class);
		ProductVO vo =  new ProductVO();
		vo = dao.selectRecord(goodCode);
		
		ModelAndView mav = new ModelAndView();
		if(userId !=null) {
			//여행바구니 선택여부
			int jang = dao.selectBasket(sc_num, userId);
			
			//관심선택여부
			int heart = dao.selectHeart(sc_num, userId);
			System.out.println(jang+","+heart);
			
			mav.addObject("jang", jang);
			mav.addObject("heart", heart);
		}
		
		System.out.println(vo.getGoodCode()+","+vo.getTravelType()+","+sc_num);
		
		ScheduleVO svo = new ScheduleVO();
		svo = dao.selectShcedule(sc_num);
		
		mav.addObject("sc", svo);
		mav.addObject("vo", vo);
		mav.setViewName("product/product_detail");

		//일련번호로 일정정보

		return mav;
	}
	
	//상품관리 페이지로 이동
	@RequestMapping("/product/management")
	public ModelAndView product_management(ProductVO vo) {
		ProductDAOInterface dao = sqlSession.getMapper(ProductDAOInterface.class);
		
		List<ProductVO> list = dao.getAllRecord(vo);
		
//		ProductVO vo2 = list.get(0);
//		System.out.println(vo2.getGoodCode()+","+vo2.getTravelType());
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("product/management");
		
		return mav;
	}
	
	//상품작성페이지 이동
	@RequestMapping("/product/product_writer")
	public ModelAndView product_write() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("product/product_writer");
		
		return mav;
	}
	
	//여행바구니 추가
	@RequestMapping("/product/basketIn")
	@ResponseBody
	public int basketIn(BasketVO vo) {
//		System.out.println(vo.getUserId());	//로그인 아이디
//		System.out.println(vo.getGoodCode());	//상품코드
//		System.out.println(vo.getSc_num());	//일정번호
//		System.out.println(vo.getJang());		//여행바구니
		
		ProductDAOInterface dao = sqlSession.getMapper(ProductDAOInterface.class);
		
		int cnt = dao.insertBasket(vo);
		return cnt;
	}
	
	//여행바구니 삭제
	@RequestMapping("/product/basketOut")
	@ResponseBody
	public int basketOut(BasketVO vo) {
//		System.out.println(vo.getUserId());	//로그인 아이디
//		System.out.println(vo.getGoodCode());	//상품코드
//		System.out.println(vo.getSc_num());	//일정번호
//		System.out.println(vo.getJang());		//여행바구니
		
		ProductDAOInterface dao = sqlSession.getMapper(ProductDAOInterface.class);
		
		int cnt = dao.deleteBasket(vo);
		return cnt;
	}
	
	//관심 추가
	@RequestMapping("/product/heartIn")
	@ResponseBody
	public int heartIn(HeartVO vo) {
//		System.out.println(vo.getUserId());	//로그인 아이디
//		System.out.println(vo.getGoodCode());	//상품코드
//		System.out.println(vo.getSc_num());	//일정번호
//		System.out.println(vo.getJang());		//여행바구니
		
		ProductDAOInterface dao = sqlSession.getMapper(ProductDAOInterface.class);
		
		int cnt = dao.insertHeart(vo);
		return cnt;
	}
	
	//관심 삭제
	@RequestMapping("/product/heartOut")
	@ResponseBody
	public int heartOut(HeartVO vo) {
//		System.out.println(vo.getUserId());	//로그인 아이디
//		System.out.println(vo.getGoodCode());	//상품코드
//		System.out.println(vo.getSc_num());	//일정번호
//		System.out.println(vo.getJang());		//여행바구니
		
		ProductDAOInterface dao = sqlSession.getMapper(ProductDAOInterface.class);
		
		int cnt = dao.deleteHeart(vo);
		return cnt;
	}
}

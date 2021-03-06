package kr.goott.tour.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ProductDAOInterface {
	// 여행상품 전체목록
	public List<ProductVO> getAllRecord(ProductVO vo);	
    // 여행상품 입력
    public void create(ProductVO vo);
    // 여행상품 수정
    public void update(ProductVO vo);
    // 여행상품  삭제
    public void delete(String goodCode);
    // 여행상품 선택
    public ProductVO selectRecord(String goodcode);
    // 상품 일정 전체 목록 선택
    public List<ScheduleVO> selectAllSchedule(String goodcode);
    // 상품 일정 전체 목록 선택(로그인 시)
    public Integer selectUserSchedule(@Param("sc_num") int sc_num, @Param("userId") String userId);    
    // 상세 상품 선택
    public ScheduleVO selectShcedule(int num);
    // 여행분류별 상품 리스트선택
    public List<ProductVO> travelTypeList(String travelType);
    // 여행바구니 추가
    public int insertBasket(BasketVO vo);
    // 여행바구니 취소
    public int deleteBasket(BasketVO vo);
    // 여행선택(테이블에 있는지 없는 여부 확인)
    public int selectBasket(@Param("sc_num") int sc_num, @Param("userId") String userId);
    // 관심 추가
    public int insertHeart(HeartVO vo);
    // 관심 취소
    public int deleteHeart(HeartVO vo);
    // 관심선택(테이블에 있는지 없는 여부 확인)
    public int selectHeart(@Param("sc_num") int sc_num, @Param("userId") String userId);
    // 이미지 첨부
    public int insertImg(ImgUploadVO vo);
    // 코드 중복 검사
    public int codeCheck(String goodCode);
    // 상품 등록
    public int insertProduct(ProductVO vo);
    // 일정 등록
    public int insertSchedule(@Param("goodCode") String goodCode
    						, @Param("startDate") String startDate
    						, @Param("backDate") String backDate);
    // view 이미지 한장 불러오기
    public String selectOneImage(String goodCode);
    
    // detail 이미지 불러오기
    public List<String> selectAllImage(String goodCode);
}

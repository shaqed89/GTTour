package kr.goott.tour.reply;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import kr.goott.tour.reply.ReplyVO;


@Controller
public class ReplyController{

	@Autowired
	SqlSession sqlSession;
    
    @RequestMapping(value="/product/reply/list", method=RequestMethod.POST) //��� ����Ʈ
    @ResponseBody
    public List<ReplyVO> replyServiceList(@RequestParam("sc_num") int sc_num) throws Exception{
    	ReplyDAOInterface commentDAO = sqlSession.getMapper(ReplyDAOInterface.class);   
    	
        return commentDAO.commentList(sc_num);
    }
    
    @RequestMapping(value="/product/reply/insert", method=RequestMethod.POST) //��� �ۼ� 
    @ResponseBody
    public List<ReplyVO> replyServiceInsert(ReplyVO comment, HttpServletRequest req) throws Exception{
    	ReplyDAOInterface commentDAO = sqlSession.getMapper(ReplyDAOInterface.class);  
        
    	
    	HttpSession sess =req.getSession();
    	System.out.println((String)sess.getAttribute("logid"));
        comment.setUserId((String)sess.getAttribute("logid"));  
        
        
        System.out.println(comment.getNum()+","+ comment.getSc_num()+","+ comment.getGoodCode()+","+ comment.getContent()+","
        		+comment.getUserId());
         commentDAO.commentInsert(comment);
        return commentDAO.commentList(comment.getSc_num());
    }
    
    @RequestMapping(value="/product/reply/update", method = RequestMethod.POST) //��� ����  
    @ResponseBody
    public int replyServiceUpdateProc(ReplyVO comment)throws Exception{
    	System.out.println(comment.getNum()+','+comment.getContent());
    	ReplyDAOInterface commentDAO = sqlSession.getMapper(ReplyDAOInterface.class); 
    	
        
        return commentDAO.commentUpdate(comment);
    }
    
    @RequestMapping(value="/product/reply/delete", method = RequestMethod.GET) //��� ����  
    @ResponseBody
    public int replyServiceDelete(@RequestParam int num) throws Exception{
    	ReplyDAOInterface commentDAO = sqlSession.getMapper(ReplyDAOInterface.class); 
        
    	return commentDAO.commentDelete(num);
    }
    
}

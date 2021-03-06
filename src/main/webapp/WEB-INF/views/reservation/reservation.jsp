<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	pageName = "구트투어";
	pageSideName = "예약하기";
	pageImage = "main_img_banner_8.jpg";
%>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/uikit@3.2.2/dist/css/uikit.min.css" />

<script src="https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script	src="https://cdn.jsdelivr.net/npm/uikit@3.2.2/dist/js/uikit.min.js"></script>
<script	src="https://cdn.jsdelivr.net/npm/uikit@3.2.2/dist/js/uikit-icons.min.js"></script>
<style>
	*{font-family:'Noto Sans KR',sans-serif;}
	#tbid{width:100%; margin-top:10px; }
	#trid th{font-size:25px; font-weight:bold; }
	h1{text-align:left; margin-top:10px;}
	a:link, a:visited{color:red; text-decoration:none;}
	a:hover{color:blue; text-decoration:underline;}
	#join form, #join p{text-align:left; font-weight:bold; font-size: 20px;}
	ol,ul{padding-left:0;}
	#telid{width:20px;}  
	#btn-rev{text-align:center; marign:50px auto; }
	#btn-rev button{margin:50px auto;width:100%; height:50px; background:#00a7f0; color:white}
	#rev-tel, #trv-tel, #rev-email, #rev-tel1, #rev-email1, #rev-txt{height:40px; text-align:center; } 
	#email-bar, #email-bar2, #email-bar3, #email-bar4{width:181px; height:34px;text-align:left;}
	#rev-name1, #rev-name, #trv-name{text-align:left;margin-top:10px;}
	#txt{text-align:left;}
	#btn1{width:250px;height:50px;}
</style>
<script type="text/javascript">
$("#bannerImg1").prop("src","/tour/image/<%=pageImage%>");
$("#imgBannerText>h1").text("<%=pageName%>");
$("#imgBannerText>h2").text("<%=pageSideName%>");
    	
    	$(function(){
				
			$("#samecheck").change(function(){
				if(this.checked){
					$("#reservationUser input").val($("#rev-User input").val());
					$("#reservationTel input:nth-of-type(1)").val($("#rev-phone input:nth-of-type(1)").val());
					$("#reservationTel input:nth-of-type(2)").val($("#rev-phone input:nth-of-type(2)").val());
					$("#reservationTel input:nth-of-type(3)").val($("#rev-phone input:nth-of-type(3)").val());
					$("#reservationEmail input:nth-of-type(1)").val($("#rev-mail input:nth-of-type(1)").val());
					$("#reservationEmail input:nth-of-type(2)").val($("#rev-mail input:nth-of-type(2)").val());
					$("#sameFrm input").prop("readonly",true);
				}else{
					$("#sameFrm input[type='text']").val("");
					$("#sameFrm input").prop("readonly",false);
					
				}
			});
			
    	}); 
  	
</script>

<section style="text-align:left">
	<%@ include file="../inc/sidebar.jspf" %>
	<div id='main'>
	<h1 id='rev-h1'>예약하기</h1>
	<hr class="hrStyle"/>
		<table class="table table-bordered" style="table-layout:fixed">
			<thead>
			  <tr>
				<th style='width:130px;'>상품코드</th>
				<th style='width:200px;'>상품명</th>
				<th style='width:100px;'>금액</th>
				<th style='width:60px;'>인원</th>
			    <th style='width:145px;'>출발일</th>
			    <th style='width:145px;'>도착일</th>
			  </tr>
			</thead>
			<tbody id='rev-body'>
			  <tr>
			    <td><a href='#'>${rev_vo.goodCode}</a></td>
			    <td style="width:240px; white-space:nowrap; overflow:hidden; text-overflow:ellipsis">${pvo.goodName}</td>
			    <td>${rev_vo.price} 원</td>
			    <td>${rev_vo.goodNum}</td>
			    <td>${svo.startDate}</td>
			    <td>${svo.backDate}</td>
			  </tr>
			</tbody>
		</table>
	<div id='join'>
	
	<form id="reserFrm"  method="post" action="reservationOK">
		<input type="hidden" name="goodCode" value="${rev_vo.goodCode}"/>
		<input type="hidden" name="sc_num" value="${rev_vo.sc_num}"/>
		<input type="hidden" name="userId" value="${rev_vo.userId}"/>
		<input type="hidden" name="price" value="${rev_vo.price}"/>
		<input type="hidden" name="goodNum" value="${rev_vo.goodNum}"/>
		예약자
		<div class="uk-margin" id='rev-name'>
	        <div class="uk-inline" id="rev-User" style="width:150px;text-align: left">
	            <span class="uk-form-icon" uk-icon="icon: user"></span>
	            <input class="uk-input" type="text" value="${rvo.userName}" readonly> 
	        </div>
	    </div>
		연락처
		<div class="uk-margin" id='rev-tel' style="max-width:;">
	        <div class="uk-inline" id="rev-phone" style="float:left;">
	            <span class="uk-form-icon" uk-icon="icon: receiver"></span>
	            <input class="uk-input" type="text" style="width:150px;text-align:center;"maxlength="3" id='rev-tel1' value="${rvo.t1}" readonly> -
	            <input class="uk-input" type="text" style="width:130px;text-align:left;"maxlength="4" id='rev-tel2' value="${rvo.t2 }" readonly> -
	            <input class="uk-input" type="text" style="width:130px;text-align:left;"maxlength="4" id='rev-tel3' value="${rvo.t3 }" readonly>
	        </div>
	    </div>
		이메일
		<div class="uk-margin" id='rev-email' style="max-width:;">
	        <div class="uk-inline" id="rev-mail" style="float:left;">
	            <span class="uk-form-icon" uk-icon="icon: mail"></span>
	            <input class="uk-input" type="text" style="width:230px;text-align:center;"maxlength="15" value="${rvo.emailId}" readonly> @
	            <input class="uk-input" type="text" style="width:200px;text-align:left;"maxlength="15" value="${rvo.emailDomain}" readonly> 
	            
	        </div>
	    </div>	 
		<hr style='margin:10px auto;'/>
		<br/> 
		<div id='samecontent'>
		<p style='margin-top: -15px; font-size:15px'><input type='checkbox' id='samecheck' >실제 여행자와 예약자가 동일하면 체크해주세요.</p>
		</div>
		<hr style='margin:10px auto;'/>

		예약자
		<div class="uk-margin" id='rev-name1'>
	        <div class="uk-inline" id="reservationUser" style="width:150px;text-align: left">
	            <span class="uk-form-icon" uk-icon="icon: user"></span>
	            <input class="uk-input" type="text" name="reserName">
	        </div>
	    </div>
		연락처
		<div class="uk-margin" id='rev-tel1'>
	        <div class="uk-inline" id="reservationTel" style="float:left;">
	            <span class="uk-form-icon" uk-icon="icon: receiver"></span>
	            <input class="uk-input" type="text" style="width:150px;text-align:center;"maxlength="3" id='trv-tel' name="t1"> -
	            <input class="uk-input" type="text" style="width:130px;text-align:left;"maxlength="4" id='trv-tel2' name="t2"> -
	            <input class="uk-input" type="text" style="width:130px;text-align:left;"maxlength="4" id='trv-tel3' name="t3">
	        </div>
	    </div>
		이메일
		<div class="uk-margin" id='rev-email1'>
	        <div class="uk-inline" id="reservationEmail" style="float:left;">
	            <span class="uk-form-icon" uk-icon="icon: mail"></span>
	            <input class="uk-input" type="text" style="width:230px;text-align:center;"maxlength="15" name="emailId"> @
	            <input class="uk-input" type="text" style="width:200px;text-align:left;"maxlength="15" name="emailDomain"> 
	        </div>
	    </div>
		문의사항
		<div class="uk-margin" id='rev-txt' style="height:200px;">
			<div class="uk-inline" id="reservationCommenting" style="float:left;">
		        <textarea class="uk-textarea" id="txt" rows="5" style="font-size:20px;width:450px;" name="resermemo"></textarea>
	        </div>
	    </div>
	    <div id='btn-rev'>
			<input type="submit" class='btn btn-danger' id='btn1' value="예약하기"/>
		</div>
	</form>
	</div>
	
	</div>	
</section>
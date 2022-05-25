<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
body, html {
    height: 100%;
    background-repeat: no-repeat;
    background-image: linear-gradient(rgb(104, 145, 162), rgb(12, 97, 33));
}

.card-container.card {
    max-width: 350px;
    padding: 40px 40px;
}

.btn {
    font-weight: 700;
    height: 36px;
    -moz-user-select: none;
    -webkit-user-select: none;
    user-select: none;
    cursor: default;
}

/*
 * Card component
 */
.card {
    background-color: #F7F7F7;
    /* just in case there no content*/
    padding: 20px 25px 30px;
    margin: 0 auto 25px;
    margin-top: 50px;
    /* shadows and rounded borders */
    -moz-border-radius: 2px;
    -webkit-border-radius: 2px;
    border-radius: 2px;
    -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}

.profile-img-card {
    width: 96px;
    height: 96px;
    margin: 0 auto 10px;
    -moz-border-radius: 50%;
    -webkit-border-radius: 50%;
    border-radius: 50%;
}

/*
 * Form styles
 */
/* .profile-name-card {
    font-size: 16px;
    font-weight: bold;
    text-align: center;
    margin: 10px 0 0;
    min-height: 1em;
} */

/* .reauth-email {
    color: #404040;
    line-height: 2;
    margin-bottom: 10px;
    font-size: 14px;
    text-align: center;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    -moz-box-sizing: border-box;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
} */

/* .form-signin input[type=email],
.form-signin input[type=password],
.form-signin input[type=text],
.form-signin button {
    width: 100%;
    margin-bottom: 10px;
    z-index: 1;
    position: relative;
    -moz-box-sizing: border-box;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
} */

.form-signin .form-control:focus {
    border-color: rgb(104, 145, 162);
    outline: 0;
    -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgb(104, 145, 162);
    box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgb(104, 145, 162);
}

.btn.btn-signin {
    /*background-color: #4d90fe; */
    background-color: rgb(104, 145, 162);
    /* background-color: linear-gradient(rgb(104, 145, 162), rgb(12, 97, 33));*/
    padding: 0px;
    font-weight: 700;
    font-size: 14px;
    height: 36px;
    -moz-border-radius: 3px;
    -webkit-border-radius: 3px;
    border-radius: 3px;
    border: none;
    -o-transition: all 0.218s;
    -moz-transition: all 0.218s;
    -webkit-transition: all 0.218s;
    transition: all 0.218s;
}

.btn.btn-signin:hover,
.btn.btn-signin:active,
.btn.btn-signin:focus {
    background-color: rgb(12, 97, 33);
}


</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</head>
<body>
<div class="container">
    <div class="card card-container">
        <form action="../member" method="post">
			<input type="hidden" name="param" value="regiAf">
			
            <input type="text" id="id" name="id" class="form-control" placeholder="아이디">
			<input type="button" id="btn" value="아이디확인">
			<p id="idcheck"></p>
            <input type="password" name="pwd" class="form-control" placeholder="비밀번호" required>
            <input type="text" name="name" class="form-control" placeholder="이름" required>
            <input type="number" name="age" class="form-control" placeholder="나이" required>
            <input type="text"  maxlength="6" minlength="6" name="birth" class="form-control" placeholder="생년월일을 6자리로 입력해주세요" required>
            <input type="text" id="address" name="address" class="form-control" placeholder="주소" required>
            <input type="button" id="btn_address" value="주소찾기">
            <input type="number" name="height" class="form-control" placeholder="키" required>
            <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">회원가입</button>
        </form><!-- /form -->
    </div><!-- /card-container -->
</div><!-- /container -->
<script type="text/javascript">
$(document).ready(function() {
	$("#btn").click(function(){
		
		if($("#id").val().trim()==""){			
			alert("id를 입력해 주십시오");
		}else{
			
			//alert($("#id").val().trim());
			
			$.ajax({
				url:"../member?param=checkid",
				type:"post",
				data:{"id":$("#id").val()},
				success:function(data){
					/* alert('success');
					alert(data);
					alert(JSON.stringify(data)); */
			 		if(data.str=="OK"){
						$("#idcheck").css("color","#0000ff");
						$("#idcheck").text("사용 가능한 아이디입니다");
					}else{
						$("#idcheck").css("color","#ff0000");
						$("#idcheck").text("사용 중인 아이디입니다");
						$("#id").val("");
					}
					
				},
				error:function(){
					alert("error");
				}
				
			});
		}
	});
	$("#btn_address").click(function(){
		let url = "../address.html";
        let name = "Child";
        let option = "width = 500, height = 500, top = 100, left = 200, location = no"
        window.open(url, name, option);
	});
	
});
</script>

</body>
</html>
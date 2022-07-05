const usernameInput = document.querySelector(".username-input");
const usernameCheckMsg = document.querySelector(".username-check-msg");
const inputItems = document.querySelectorAll("table input");
const submitButton = document.querySelector(".submit-button");

let signupFlag = [false, false, false, false, false];	// 밑을 반복을 하면서 true로 바꿔주지만 비어있으면 false로 냅둠

submitButton.onclick = () => {
	for(let i = 0; i < inputItems.length; i++) {	// 빈값인지 아닌지 submit요청이 오면은 해야댐
		if(isEmpty(inputItems[i].value)){	// false일때 true로 바꿔서 넣어줌
			alert((i == 0 ? "이름을" 
				: i == 1 ? "이메일을" 
				: i == 2 ? "사용자 이름을"
				: "비밀번호를")
				+ " 입력해 주세요.");
			signupFlag[i] =	false;
			
			return;
		}	 
		signupFlag[i] = true;
	}
	
	if(signupFlag[4] == false){
		alert("사용자이름 중복확인이 필요합니다.");
		return;
	}
	
	if(!signupFlag.includes(false)){
		submit();
	}	
}

usernameInput.onblur = () => {	// onblur -> 벗어나는거
	let username = usernameInput.value;
	$.ajax({
		type: "get",
		url: `/check/username?username=${username}`,
		dataType: "text",	// 응답받을 때의 데이터 형태
		success: (response) => {
			if(response == "true"){
				signupFlag[4]= false;
				usernameCheckMsg.innerHTML = `<td colspan="2">${username}은(는) 이미 존재하는 사용자이름입니다.</td> `;
			}else{
				signupFlag[4] = true;
				usernameCheckMsg.innerHTML = `<td colspan="2">${username}은(는) 가입 할 수 있는 사용자이름입니다.</td> `;
			}
		},
		error: errorMessage
	});
}

function submit() {
	$.ajax({
		type: "post",	
		url: "/signup",
		data: {	// post요청일때는 파람으로 넘기는게 아닌 바디로 넘김
			name: inputItems[0].value,
			email: inputItems[1].value,
			username: inputItems[2].value,
			password: inputItems[3].value
		},
		dataType: "text",
		success: (response) => {	// ajax내부에서 success를 호출함
			if(response == "true"){
				alert("축하합니다!\n회원가입에 성공하였습니다.");
				location.replace("/signin");
			}else{
				alert("회원가입에 실패하였습니다.\n다시 시도해 주세요.");
				usernameCheckMsg.innerHTML = "";
				document.querySelector("form").reset();
			}
		},
		error: errorMessage
	});
}

function errorMessage(request, status, error) {	// try catch부분의 catch부분같은거, 매개변수를 채워주기위해 status사용
	alert("요청 실패");
	console.log(request.status);
	console.log(request.responseText);
	console.log(error);	
}

function isEmpty(str) {
	return str == "" || str == null || typeof str == undefined; // undefined -> 선언한적없음, 즉 비어있음
}
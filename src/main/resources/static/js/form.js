//post를 입력하고 홈으로 이동하는 Ajax
let postObject = {

	init: function() {
		let _this = this;

        document.getElementById('btnConfirm').addEventListener("click", _this.insertPost);
	},

	insertPost: function(e) {
		    const form = new FormData(document.getElementById('formStock'));
    	    const data = Array.from(form.entries()).reduce((memo, [key, value]) => ({
                ...memo,
                [key]: value,
              }), {});

//	  fetch("/", {
//                method: 'post',
//                headers: {
//                    'content-type': 'application/json'
//                },
//                body : JSON.stringify(data)
//            }).then(function(response) {
//                return response;
//              }).catch(function(error) {
//                // Error
//                console.error(error);
//              });

        //XMLHttpRequest 객체 생성
        var xhr = new XMLHttpRequest();

        //요청을 보낼 방식, url, 비동기여부 설정
        xhr.open('POST', '/', true);
        xhr.setRequestHeader('Content-type', 'application/json');

        //요청 전송
        xhr.send(JSON.stringify(data));

        //Callback
        xhr.onload = () => {
            if (xhr.status == 200) {
            	//success
            	new bootstrap.Modal('#portfolioModal1', {}).show();
                console.log(xhr.response);
            } else {
                //failed
                console.log(JSON.stringify(data));
                console.log(xhr.response);
                alert('공공 데이터 api에서 조회가 되지 않습니다ㅠㅠ');
            }
        }

	}
}

// postObject 객체의 init() 함수 호출.
postObject.init();


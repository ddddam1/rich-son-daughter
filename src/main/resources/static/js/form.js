//post를 입력하고 홈으로 이동하는 Ajax
let postObject = {

	init: function() {
		let _this = this;
		_this.setDate();
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
            	let income = xhr.response;
            	document.getElementById('devIncomeImg').src = '/assets/img/' + postObject.getIncomeImage(income) + '.png';
            	document.getElementById('devIncome').innerText = Number(income).toLocaleString('ko-KR') + ' 원';

            	new bootstrap.Modal('#portfolioModal1', {}).show();
                console.log(xhr.response);
            } else {
                //failed
                console.log(JSON.stringify(data));
                console.log(xhr.response);
                alert('공공 데이터 api에서 조회가 되지 않습니다ㅠㅠ');
            }
        }

	},
	getIncomeImage: function(income) {
	    let rate = income/50000000 * 100;

	    if(rate > 50) {
	        return "gold";
	    } else if (rate > 10) {
	        return "ring";
	    } else if (rate > 5) {
	        return "chicken";
	    } else if (rate > 0) {
            return "coffee";
        } else if (rate > -5) {
	        return "paw";
	    } else if (rate > -10) {
	        return "danger";
	    } else {
	        return "handcuffs";
	    }

	},
	setDate: function(){
        let thisDate = new Date();
        let day = thisDate.getDay();

        let calcDate = thisDate.getDate() - day - 6;

        //저번주 월요일
        let nextDate = new Date(thisDate.setDate(calcDate)).toISOString().substring(0, 10);
        document.getElementById('devBuyDate').value = nextDate;

        //저번주 금요일
        let calcDate2 =  new Date(thisDate.setDate(calcDate + 4)).toISOString().substring(0, 10);
        document.getElementById('devSellDate').value = calcDate2;
	}

}

// postObject 객체의 init() 함수 호출.
postObject.init();


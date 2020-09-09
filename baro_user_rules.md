고객용 API 문서
======

<h1>변경 사항</h1>

* `OrderFindByReceiptId.do` : 주문의 현황을 알려주는 `order_state` 추가.   
  이 값은 `DONE`, `PREPARING`, `CANCEL`의 3가지 값을 가질 수 있다.
* `OrderInsert.do` : `total_price`, `discount_price`, `coupon_id` 추가.
  * `menu_name`과 `extra_name` 추가
* `CouponFindByPhone.do` : `coupon_condition`이 문자열에서 INT 형으로 변경됨.
* `가게 관련 API` : 가게가 영업중인지 아닌지를 알려주는 `is_open`이 `Y` 또는 `N`으로 추가됨.
<hr/>

<h1>신규 API</h1>

* `CouponHistoryFindByPhone.do`
* `StoreCheckIsOpen.do`
<hr/>

<h1>회원 관련 API</h1>

* 회원의 로그인 처리
  * URL : http://15.165.22.64:8080/MemberLogin.do
  * Http Method : POST
  * 제공해야하는 JSON 형식
```json
{ "phone" : "전화번호",
  "pass" : "비밀번호",
  "device_token":"firebase device token"
}
```
  * 응답 형식
```json
// 로그인 성공 시
{
    "result": true,
    "nick": "sangwoo",
    "phone": "01029093199",
    "created_date": "2020년 8월 12일 ",
    "message": "Login success.",
    "email": "robbyra@gmail.com"
}

// 로그인 실패 시
{"result":false, "message":"등록되지 않은 회원이거나 비밀번호가 일치하지 않습니다."}
```

* 회원 전화번호 중복 체크
  * URL : http://15.165.22.64:8080/MemberPhoneCheck.do?phone=전화번호
  * Http Method : GET
  * 제공해야하는 JSON 형식 : __없음, 파라미터로 `phone=중복체크할전화번호` 형식으로 전달__
  * 응답 형식
```json
// 가입 가능한 전화번호일 때
{"result":true, "message":"가입 가능한 전화번호 입니다."}
// 이미 가입된 전화번호일 때
{"result":false, "message":"이미 가입된 전화번호 입니다."}
```

* 회원 등록 처리
  * URL : http://15.165.22.64:8080/MemberRegister.do
  * Http Method : POST
  * 제공해야하는 JSON 형식
```json
{"phone":"전화번호", "email":"이메일주소", "nick":"사용자명", "pass":"비밀번호"}
```
  * 응답 형식
```json
// 회원 가입 성공 시
{"result":true, "messsage":"회원 가입에 성공했습니다."}

// 회원 가입 실패 시 (1) : 이미 사용중인 전화번호일 경우
{
    "result": false,
    "errno": 2,
    "message": "이미 가입된 전화번호 입니다."
}

// 회원 가입 실패 시 (2) : 이미 사용중인 이메일일 경우
{
    "result": false,
    "errno": 3,
    "message": "이미 사용중인 이메일 입니다."
}
```

* 회원 비밀번호 변경 처리
  * URL : http://15.165.22.64:8080/MemberPassUpdate.do
  * Http Method : PUT
  * 제공해야하는 JSON 형식
```json
{"phone":"전화번호", "pass":"신규비밀번호"}
```
  * 응답 형식
```json
// 비밀번호 변경 처리 시
{"result":true, "message":"비밀번호가 정상적으로 변경되었습니다."}
// 비밀번호 변경 실패 시
{"result":false, "message":"등록되지 않은 전화번호 입니다."}
```

* 회원 이메일 변경 처리
  * URL : http://15.165.22.64:8080/MemberEmailUpdate.do
  * Http Method : PUT
  * 제공해야하는 JSON 형식
```json
{"phone":"전화번호", "email":"변경할 신규 이메일"}
```
  * 응답 형식
```json
// 이메일 변경 처리 시
{"result":true, "message":"이메일이 정상적으로 변경되었습니다."}
// 이메일 변경 실패 시
{"result":false, "message":"등록되지 않은 전화번호 입니다."} // 또는
{"result":false, "message":"이미 사용중인 이메일 입니다."}
```
<hr/>

<h1>공지 관련 API</h1>

* 모든 공지 읽어오기
  * URL : http://15.165.22.64:8080/NoticeFindAll.do
  * Http Method : GET
  * 제공해야하는 JSON 형식 : __없음__
  * 응답 형식
```json
// 공지사항이 1개도 존재하지 않을 때
{"result":false, "message":"등록된 알림, 공지사항 또는 이벤트가 존재하지 않습니다."}

// 공지사항이 존재할 때 (4개의 공지사항이 존재하는 예시)
{
    "result": true,
    "message": "notice 가져오기 성공.",
    "notice": [
        {
            "notice_code": "NOTICE",
            "notice_date": "2020년 8월 8일 10시 2분 36초",
            "title": "공지사항 제목",
            "notice_id": 1,
            "content": "공지사항 내용"
        },
        {
            "notice_code": "EVENT",
            "notice_date": "2020년 8월 8일 10시 3분 9초",
            "title": "이벤트 제목",
            "notice_id": 2,
            "content": "이벤트 내용"
        },
        {
            "notice_code": "ALERT",
            "notice_date": "2020년 8월 8일 10시 3분 30초",
            "title": "알림사항 제목",
            "notice_id": 3,
            "content": "알림사항 내용"
        },
        {
            "notice_code": "NOTICE",
            "notice_date": "2020년 8월 8일 11시 16분 41초",
            "title": "test title",
            "notice_id": 4,
            "content": "test content"
        }
    ]
}
```

* 특정 공지 읽어오기
  * URL : http://15.165.22.64:8080/NoticeFindById.do?notice_id=id값
  * Http Method : GET
  * 제공해야하는 JSON 형식 : __없음, 파라미터로 `notice_id=id값` 형태로 전달
  * 응답 형식
```json
// 해당 notice_id = 1로 공지를 가져오는데 성공했을 시 (주소 : http://54.180.56.44:8080/NoticeFindById.do?notice_id=1)
{
    "result": true,
    "notice_code": "NOTICE",
    "notice_date": "2020년 8월 8일 10시 2분 36초",
    "message": "해당 id로 공지 가져오기 성공",
    "title": "공지사항 제목",
    "notice_id": 1,
    "content": "공지사항 내용"
}

// 해당 notice_id가 존재하지 않을 시
{
    "result": false,
    "message": "해당 notice_id로 존재하는 공지사항이 없습니다."
}
```

* 공지사항 등록 처리
  * URL : http://15.165.22.64:8080/NoticeSave.do
  * Http Method : POST
  * 제공해야하는 JSON 형식
```json
{"title":"제목", "content":"내용", "notice_code":"공지 코드"}
```
  * __`notice_code`에 해당하는 부분은 반드시 `NOTICE`, `ALERT` 중 하나여야 함__.

  * 응답 형식
```json
// 등록 성공 시
{"result":true, "message":"notice 등록 성공"}
// 등록 실패 시
{"result":false, "message":"catch되는 예외에 따라 메시지 달라짐(알 수 없음)"}
```

* 공지사항 notice_code 별로 가져오기
  * URL : http://15.165.22.64:8080/NoticeFindByCode.do?notice_code=공지코드
  * HTTP Method : GET
  * 제공해야하는 JSON 형식 : __없음, 파라미터로 보내는 `notice_code`는 반드시 `EVENT`, `ALERT`, `NOTICE` 중 하나__
  * 응답 형식
```json
// 공지 코드별로 가져오기 성공 시 (URL : http://localhost:8080/NoticeFindByCode.do?notice_code=EVENT)
{
    "result": true,
    "message": "notice_code 별로 가져오기 성공",
    "notice": [
        {
            "notice_code": "EVENT",
            "notice_date": "2020년 8월 8일 ",
            "title": "이벤트 제목",
            "notice_id": 2,
            "content": "이벤트 내용"
        },
        {
            "notice_code": "EVENT",
            "notice_date": "2020년 8월 10일 ",
            "title": "EVENT TITLE2",
            "notice_id": 5,
            "content": "EVENT CONTENT2"
        }
    ]
}

// 공지 코드가 잘못되었을 때
{
    "result": false,
    "message": "notice_code가 잘못되었습니다."
}
```
<hr/>

<h1>가게 종류 관련 API</h1>

* 가게 종류 목록 가져오기
  * URL : http://15.165.22.64:8080/TypeFindAll.do
  * Http Method : GET
  * 제공해야 하는 JSON 형식 : __없음__
  * 응답 형식
```json
// 가게 정보가 없을 때
{"result":false, "message":"등록된 가게 종류 정보가 없습니다."}
// 가게 정보가 있을 때
{
    "result": true,
    "message": "types 가져오기 성공",
    "type": [
        {
            "type_image": "default.png",
            "type_name": "카페",
            "type_code": "CAFE"
        },
        {
            "type_image": "default.png",
            "type_name": "중식",
            "type_code": "CHINESE"
        },
        {
            "type_image": "default.png",
            "type_name": "디저트",
            "type_code": "DESSERT"
        },
        {
            "type_image": "default.png",
            "type_name": "일식",
            "type_code": "JAPANESE"
        },
        {
            "type_image": "default.png",
            "type_name": "한식",
            "type_code": "KOREAN"
        }
    ]
}
```

<h1>가게 관련 API</h1>

* 특정 가게의 정보 가져오기
  * URL : http://15.165.22.64:8080/StoreFindById.do?store_id=가게id값
  * Http Method : GET
  * 제공해야 하는 JSON 형식 : __없음, 파라미터로 `store_id=?` 전송__
  * 응답 형식
```json
// 해당 store_id를 가진 가게가 존재할 때
{
    "store_id": 1,
    "store_opentime": "10:30",
    "store_info": "안녕하세요 이 카페는 테스트용 카페입니다.",
    "store_latitude": 37.4952,
    "store_closetime": "22:00",
    "store_daysoff": "매주 월, 화 휴무",
    "message": "가게 정보 가져오기 성공.",
    "result": true,
    "store_phone": "0212345678",
    "store_longitude": 126.9565,
    "store_name": "test cafe",
    "store_location": "서울특별시 테스트구 테스트동 테스트로 111 테스트빌딩 2층",
    "type_code": "CAFE",
    "store_image": "test_cafe1.png",
    "is_open":"Y"
}
// 해당 store_id를 가진 가게가 존재하지 않을 때
{
    "result": false,
    "message": "해당 store_id를 가진 가게 정보가 존재하지 않습니다."
}
```

* 특정 이름의 가게 id만 가져오기
  * URL : http://15.165.22.64:8080/StoreFindIdByStoreName.do?store_name=가게명
  * Http Method : GET
  * 제공해야 하는 형식 : __없음, 파라미터로 `store_name=가게명` 전송, 만약 가게명에 공백이 있을 경우 `%20`을 공백 자리에 넣어준다.__   
    예시 : 가게명이 `TEST CAFE2` 인 경우의 파라미터 : `store_name=TEST%20CAFE2`
  * 응답 형식
```json
// 해당 store_name으로 store_id를 가져오기 성공 시
{
    "result": true,
    "store_id": 3,
    "message": "가게 id 가져오기 성공"
}

// 해당 store_name이 존재하지 않는 경우
{
    "result": false,
    "message": "해당 가게 이름으로 조회된 가게 정보가 없습니다."
}
```

* 가게명 검색 API
  * URL : http://15.165.22.64:8080/StoreSearch.do?keyword=검색어
  * HTTP Method : GET
  * 제공해야하는 형식 : __없음, 파라미터로 `keyword=검색어` 지정__
  * 응답 형식
```json
// 검색 결과가 존재하는 경우 (예시 : http://54.180.56.44:8080/StoreSearch.do?keyword=중국)
{
    "result": true,
    "store": [
        {
            "store_id": 1,
            "store_opentime": "10:30",
            "store_info": "안녕하세요 이 카페는 테스트용 카페입니다.",
            "store_latitude": 37.4952,
            "store_closetime": "22:00",
            "is_open": "Y",
            "store_daysoff": "매주 월, 화 휴무",
            "store_phone": "0212345678",
            "store_longitude": 126.9565,
            "store_name": "test cafe",
            "store_location": "서울특별시 테스트구 테스트동 테스트로 111 테스트빌딩 2층",
            "type_code": "CAFE"
        },
        {
            "store_id": 3,
            "store_opentime": "10:00",
            "store_info": "CAFE2 의 정보 입니다.",
            "store_latitude": 0.1,
            "store_closetime": "23:00",
            "is_open": "N",
            "store_daysoff": "매주 월 휴무",
            "store_phone": "0211112222",
            "store_longitude": 0.1,
            "store_name": "TEST CAFE2",
            "store_location": "테스트시 테스트동",
            "type_code": "CAFE"
        },
        {
            "store_id": 4,
            "store_opentime": "9:00",
            "store_info": "CAFE3 의 정보 입니다.",
            "store_latitude": 0.2,
            "store_closetime": "21:00",
            "is_open": "N",
            "store_daysoff": "매주 일 휴무",
            "store_phone": "0211113333",
            "store_longitude": 0.2,
            "store_name": "TEST CAFE3",
            "store_location": "테스트시 테스트2동",
            "type_code": "CAFE"
        },
        {
            "store_id": 5,
            "store_opentime": "8:00",
            "store_info": "CAFE4 의 정보 입니다.",
            "store_latitude": 0.3,
            "store_closetime": "22:00",
            "is_open": "N",
            "store_daysoff": "연중 휴무",
            "store_phone": "0211114444",
            "store_longitude": 0.3,
            "store_name": "TEST CAFE4",
            "store_location": "테스트시 테스트3동",
            "type_code": "CAFE"
        }
    ],
    "message": "검색 성공"
}

// 검색 결과가 존재하지 않는 경우
{
    "result": false,
    "message": "검색 결과가 존재하지 않습니다."
}
```

* type_code로 가게 정보 가져오기
  * URL : http://15.165.22.64:8080/StoreInfoFindByType.do?type_code=타입코드값
  * Http Method : GET
  * 제공해야 하는 JSON 형식 : __없음, 파라미터로 `type_code=?` 형식으로 전송__
  * 응답 형식
```json
// 성공 시
{
    "result": true,
    "store": [
        {
            "store_id": 6,
            "store_info": "중국집1 의 정보 입니다.",
            "store_latitude": 1.1,
            "is_open": "N",
            "store_longitude": 1.1,
            "store_name": "중국집1",
            "store_location": "테스트시 테스트4동",
            "store_image": "default.png"
        },
        {
            "store_id": 7,
            "store_info": "중국집2 의 정보 입니다.",
            "store_latitude": 1.2,
            "is_open": "N",
            "store_longitude": 1.2,
            "store_name": "중국집2",
            "store_location": "테스트시 테스트5동",
            "store_image": "default.png"
        },
        {
            "store_id": 8,
            "store_info": "중국집3 의 정보 입니다.",
            "store_latitude": 1.3,
            "is_open": "N",
            "store_longitude": 1.3,
            "store_name": "중국집3",
            "store_location": "테스트시 테스트동",
            "store_image": "default.png"
        },
        {
            "store_id": 9,
            "store_info": "중국집4 의 정보 입니다.",
            "store_latitude": 37.495,
            "is_open": "N",
            "store_longitude": 126.9563,
            "store_name": "중국집4",
            "store_location": "테스트시 테스트동",
            "store_image": "default.png"
        }
    ],
    "message": "type_code별로 가게 정보 가져오기 성공"
}

// 실패 시
{
    "result": false,
    "message": "해당 type_code를 가진 가게 정보가 존재하지 않습니다."
}
```

* 모든 가게의 위치 정보와 이름 가져오기
  * URL : http://15.165.22.64:8080/StoreAllLocation.do
  * Http Method : GET
  * 제공해야하는 JSON 형식 : __없음__
  * 응답 결과
```json
{
    "result": true,
    "store": [
        {
            "store_name": "마루 스시",
            "store_latitude": 2.1,
            "store_longitude": 2.1
        },
        {
            "store_name": "마루 스시",
            "store_latitude": 2.1,
            "store_longitude": 2.1
        },
        {
            "store_name": "돈카츠",
            "store_latitude": 2.2,
            "store_longitude": 2.2
        },
        {
            "store_name": "라멘집",
            "store_latitude": 2.3,
            "store_longitude": 2.3
        },
        {
            "store_name": "해물탕집",
            "store_latitude": 2.4,
            "store_longitude": 2.4
        }
    ],
    "message": "모든 가게의 위치 정보 가져오기 성공"
}
```
<hr/>

<h1>카테고리 관련 API</h1>

* store_id별로 카테고리 가져오기
  * URL : http://15.165.22.64:8080/CategoryFindByStoreId.do?store_id=가게id값
  * Http Method : GET
  * 제공해야 하는 JSON 형식 : __없음, 파라미터로 `store_id=?` 형식으로 전송__
  * 응답 형식
```json
// store_id 에 알맞은 카테고리를 가져오는데 성공했을 경우
{
    "result": true,
    "message": "해당 store_id로 카테고리 목록 불러오기 성공",
    "category": [
        {
            "store_id": 1,
            "category_name": "커피",
            "category_id": 1
        },
        {
            "store_id": 1,
            "category_name": "에이드",
            "category_id": 2
        },
        {
            "store_id": 1,
            "category_name": "라떼&티",
            "category_id": 3
        },
        {
            "store_id": 1,
            "category_name": "디저트",
            "category_id": 4
        }
    ]
}

// store_id가 존재하지 않는 경우
{
    "result": false,
    "message": "해당 store_id를 가진 카테고리 정보가 존재하지 않습니다."
}
```

<hr/>

* 결제하기 클릭 직전, 마지막으로 해당 매장의 영업 유무 확인하기
  * URL : http://15.165.22.64:8080/StoreCheckIsOpen.do?store_id=가게id값
  * Http Method : GET
  * 제공해야하는 JSON 형식 : __없음, 파라미터로 `store_id=가게id값` 전달__
  * 응답 형식
```json
// 해당 매장이 영업 중이라면
{
    "result": true,
    "message": "해당 매장이 영업 중 입니다."
}

// 해당 매장이 영업 중이 아니라면
{
    "result": false,
    "message": "해당 매장이 영업 중이 아닙니다."
}
```
<hr/>

<h1>메뉴 관련 API</h1>

* 가게의 모든 메뉴 목록 가져오기
  * URL : http://15.165.22.64:8080/MenuFindByStoreId.do?store_id=가게id값
  * Http Method : GET
  * 제공해야하는 JSON 형식 : __없음, 파라미터로 `store_id=가게id값` 전달__
  * 응답 형식
```json
// 해당 store_id를 가진 menu 정보가 존재하는 경우
{
    "result": true,
    "message": "해당 store_id로 메뉴 정보 가져오기 성공",
    "menu": [
        {
            "store_id": 1,
            "category_id": 1,
            "menu_info": "좋은 원두를 사용한 아메키라노",
            "menu_name": "아메리카노",
            "menu_defaultprice": 1500,
            "menu_id": 4
        },
        {
            "store_id": 1,
            "category_id": 1,
            "menu_info": "좋은 원두를 사용한 민트카페모카",
            "menu_name": "민트카페모카",
            "menu_defaultprice": 2700,
            "menu_id": 11
        },
        {
            "store_id": 1,
            "category_id": 1,
            "menu_info": "좋은 원두를 사용한 카라멜마끼아또",
            "menu_name": "카라멜마끼아또",
            "menu_defaultprice": 3000,
            "menu_id": 12
        },
        {
            "store_id": 1,
            "category_id": 3,
            "menu_info": "이건 우유",
            "menu_name": "우유",
            "menu_defaultprice": 1500,
            "menu_id": 24
        },
        {
            "store_id": 1,
            "category_id": 3,
            "menu_info": "이건 녹차",
            "menu_name": "녹차",
            "menu_defaultprice": 2300,
            "menu_id": 25
        },
        {
            "store_id": 1,
            "category_id": 3,
            "menu_info": "이건 로즈마리",
            "menu_name": "로즈마리",
            "menu_defaultprice": 2300,
            "menu_id": 26
        },
        {
            "store_id": 1,
            "category_id": 3,
            "menu_info": "이건 페퍼민트",
            "menu_name": "페퍼민트",
            "menu_defaultprice": 2300,
            "menu_id": 27
        }
    ]
}

// 해당 store_id를 가진 메뉴가 존재하지 않는 경우
{
    "result": false,
    "message": "해당 store_id를 가진 메뉴 정보가 없습니다."
}
```
<hr/>

<h1>Extra 관련 API</h1>

* menu_id 별로 extra 목록 가져오기
  * URL : http://15.165.22.64:8080/ExtraFindByMenuId.do?menu_id=메뉴id값
  * Http Method : GET
  * 제공해야하는 JSON 형식 : __없음, 파라미터로 `menu_id=메뉴id값` 전송__
  * 응답 형식
```json
// menu_id가 존재하여 extra 정보 가져오기 성공 시
{
    "result": true,
    "extra": [
        {
            "extra_group": "TEMPERATURE",
            "extra_id": 2,
            "extra_price": 0,
            "extra_name": "ICE",
            "extra_maxcount": 3
        },
        {
            "extra_group": "TEMPERATURE",
            "extra_id": 1,
            "extra_price": 0,
            "extra_name": "HOT",
            "extra_maxcount": 3
        },
        {
            "extra_group": "SIZE",
            "extra_id": 19,
            "extra_price": 500,
            "extra_name": "사이즈업",
            "extra_maxcount": 3
        },
        {
            "extra_group": "SIZE",
            "extra_id": 66,
            "extra_price": 0,
            "extra_name": "기본 크기",
            "extra_maxcount": 3
        },
        {
            "extra_group": "null",
            "extra_id": 38,
            "extra_price": 300,
            "extra_name": "샷 추가",
            "extra_maxcount": 3
        },
        {
            "extra_group": "null",
            "extra_id": 37,
            "extra_price": 300,
            "extra_name": "시럽 추가",
            "extra_maxcount": 3
        }
    ],
    "message": "menu_id로 extra 정보 가져오기 성공"
}
// extra가 존재하지 않을 때
{
    "result": false,
    "message": "해당 menu_id를 가진 extra 정보가 존재하지 않습니다."
}
```
<hr/>

<h1>사진 관련 API</h1>

* 이벤트 사진 가져오기
  * URL(1, 이벤트 사진 가져오기) : http://15.165.22.64:8080/ImageEvent.do?image_name=이미지명
  * URL(2, 가게 사진 가져오기) : http://15.165.22.64:8080/ImageStore.do?image_name=이미지명
  * URL(3, 가게 타입 사진 가져오기) : http://15.165.22.64:8080/ImageType.do?image_name=이미지명
  * Http Method : GET
  * 제공해야하는 JSON형식 : __없음, 파라미터에 `image_name=이미지명`으로 전달
  * 응답 형식 : JSON이 아닌 byte[] 배열, slack에 올린 `MainActivity.java` 참고

<h1>주문 관련 API</h1>

* 주문 내역 저장 처리
  * URL : http://15.165.22.64:8080/OrderInsert.do
  * Http Method : POST
  * 제공해야 하는 JSON형식
    * __`discount_price`와 `coupon_id`는 사용자가 쿠폰을 사용하지 않으면 둘 다 `-1`로 넣어주시면 됩니다__. 
    * __`total_price`는 실결제금액이 아닌, 쿠폰을 적용하든 적용하지 않든 계산되는 구매 상품의 총 금액 입니다.__
```json
{
  "phone":"전화번호",
  "store_id":"가게id값",
  "receipt_id":"부트페이에서 제공하는 고유 영수증id값",
  "total_price":"할인 적용 안된 순수 전체 금액",
  "discount_price":"쿠폰으로 할인되는 금액",
  "coupon_id":"쿠폰id값",
  "order_date":"주문한 시각",
  "orders":[
    {
      "menu_id":"메뉴id값",
      "menu_name":"메뉴 이름",
      "menu_defaultprice":"메뉴기본가격",
      "order_count":"해당 메뉴를 주문한 개수",
      "extras":[
        {
          "extra_id":"extra_id값",
          "extra_price":"extra_price값",
          "extra_name":"extra이름",
          "extra_count":"extra횟수"
        }
      ]
    }
  ]
}
```
  * 기본 틀은 위 형식이고 아래는 예시입니다.
```json
{
    "orders": [
        {
            "menu_defaultprice":"1000",
            "menu_id":34,
            "menu_name":"쿠키",
            "order_count":1,
            "extras":[]
        },
        {
            "extras": [
                {
                    "extra_count": 0,
                    "extra_id": 1,
                    "extra_price": 0,
                    "extra_name":"HOT"
                },
                {
                    "extra_count": 1,
                    "extra_id": 19,
                    "extra_price": 500,
                    "extra_name":"사이즈업"
                }
            ],
            "menu_defaultprice": 1500,
            "menu_id": 4,
            "menu_name":"아메리카노",
            "order_count": 2
        },
        {
            "extras": [
                {
                    "extra_count": 0,
                    "extra_id": 4,
                    "extra_price": 0,
                    "extra_name":"ICE"
                },
                {
                    "extra_count": 2,
                    "extra_id": 20,
                    "extra_price": 500,
                    "extra_name":"사이즈업"
                }
            ],
            "menu_defaultprice": 2000,
            "menu_id": 5,
            "menu_name":"카푸치노",
            "order_count": 1
        },
        {
            "extras": [
                {
                    "extra_count": 0,
                    "extra_id": 2,
                    "extra_price": 0,
                    "extra_name":"ICE"
                },
                {
                    "extra_count": 0,
                    "extra_id": 66,
                    "extra_price": 0,
                    "extra_name":"기본 크기"
                }
            ],
            "menu_defaultprice": 1500,
            "menu_id": 4,
            "menu_name":"아메리카노",
            "order_count": 1
        }
    ],
    "phone": "01093756927",
    "store_id": 1,
    "receipt_id":"coupontest34",
    "total_price":"9500",
    "discount_price":1000,
    "coupon_id":1
}
```

  * 응답 형식
```json
{"result":"true", "message":"주문 내역이 정상적으로 저장되었습니다."}
```

* 주문 내역 리스트 가져오기
  * URL : http://15.165.22.64:8080/OrderListFindByPhone.do?phone=전화번호
  * Http Method : GET
  * 제공해야하는 JSON 형식 : __없음, 파라미터로 `phone=전화번호` 형식으로 전달__
  * 응답 형식
```json
// 주문 내역 리스트 가져오기 성공 시
{
    "result": true,
    "message": "전화번호로 주문 정보 가져오기 성공",
    "order": [
        {
            "order_date": "2020년 8월 24일 13시 23분 26초",
            "receipt_id": "coupontest34",
            "store_name": "test cafe",
            "total_price": 9500,
            "total_count": 5
        }
    ]
}
// 주문 내역 정보가 없을 때
{
    "result": false,
    "message": "주문 내역이 존재하지 않습니다."
}
```

* 주문 상세 정보 가져오기
  * URL : http://15.165.22.64:8080/OrderFindByReceiptId.do?receipt_id=고유영수증값
  * Http Method : GET
  * 제공해야하는 JSON 형식 : __없음, 파라미터로 `receipt_id=?`형식으로 전달__
  * 응답 형식
```json
{
    "result": true,
    "orders": [
        {
            "order_count": 1,
            "menu_name": "카푸치노",
            "menu_defaultprice": 2000,
            "extras": [
                {
                    "extra_price": 0,
                    "extra_name": "HOT",
                    "extra_count": 1
                },
                {
                    "extra_price": 0,
                    "extra_name": "기본 크기",
                    "extra_count": 1
                }
            ],
            "order_id": 97,
            "order_state": "DONE"
        },
        {
            "order_count": 1,
            "menu_name": "헤이즐넛라떼",
            "menu_defaultprice": 2300,
            "extras": [
                {
                    "extra_price": 0,
                    "extra_name": "ICE",
                    "extra_count": 1
                },
                {
                    "extra_price": 500,
                    "extra_name": "사이즈업",
                    "extra_count": 1
                }
            ],
            "order_id": 98,
            "order_state": "DONE"
        }
    ],
    "message": "상세 주문 내역 가져오기 성공"
}
```

* 주문 횟수 가져오기(아직은 사용안하지만 나중에 이벤트용으로 쓸듯)
  * URL : http://15.165.22.64:8080/OrderTotalCountByPhone.do?phone=?
  * Http Method : GET
  * 제공해야하는 JSON 형식 : __없음, 파라미터로 `phone=전화번호` 형식으로 전달__
  * 응답 형식
```json
{
    "result": true,
    "total_orders": 2
}
```

* 주문한 물품들의 진행 현황 보기(ACCEPT or PREPARING)
```
CF.
ACCEPT -> 회원이 주문을 한 접수중의 상태
PREPARING -> 업주가 회원을 주문을 받아 시간설정을 하고 제조중인 상태
DONE -> 완료의 상태
CANCEL -> 결제취소의 상태
```
* URL :  http://15.165.22.64:8080/OrderProgressing.do?phone=전화번호
* Http Method : GET
* 제공해야하는 JSON 형식 : __없음, 파라미터로 `phone=전화번호` 형식으로 전달__
* 응답형식
```json
//성공시
{
 "result": true,
    "message": "전화번호로 주문 정보 가져오기 성공",
    "order": [
        {
            "order_date": "2020년 8월 26일 14시 29분 8초",
            "receipt_id": "5f45f31b2fa5c200249c41a2",
            "store_name": "test cafe",
            "total_price": 1500,
            "order_state": "PREPARING",
            "total_count": 1
        },
        {
            "order_date": "2020년 9월 7일 8시 2분 8초",
            "receipt_id": "5f55e8f7878a56003972e87b",
            "store_name": "test cafe",
            "total_price": 1500,
            "order_state": "ACCEPT",
            "total_count": 1
        }
    ]
}
//실패시

```



<hr/>

<h1>즐겨찾기 관련 API</h1>

* 즐겨찾기 정보 가져오기
  * URL : http://15.165.22.64:8080/FavoriteList.do?phone=전화번호
  * Http Method : GET
  * 제공해야하는 JSON 형식 : __없음, 파라미터로 `phone=전화번호`로 전달__
  * 응답 형식
```json
// 성공 시
{
    "result": true,
    "message": "01093756927의즐겨찾기 정보 가져오기 성공",
    "favorite": [
        {
            "store_id": 1,
            "store_info": "안녕하세요 이 카페는 테스트용 카페입니다.",
            "store_latitude": 37.4952,
            "is_open": "N",
            "store_longitude": 126.9565,
            "store_name": "test cafe",
            "store_location": "서울특별시 테스트구 테스트동 테스트로 111 테스트빌딩 2층",
            "store_image": "test_cafe1.png"
        },
        {
            "store_id": 25,
            "store_info": "유명한 레트로 분위기의 호프집",
            "store_latitude": 37.506721,
            "is_open": "Y",
            "store_longitude": 127.005268,
            "store_name": "하트타임",
            "store_location": "서초구 반포 쇼핑타운상가 5동 지하 1층",
            "store_image": "default.png"
        }
    ]
}

// 실패 시 (등록된 전화번호가 아니거나 즐겨찾기 정보가 없을 때)
{
    "result": false,
    "message": "등록된 즐겨찾기 정보가 없습니다."
}
```

* 즐겨찾기 등록 처리
  * URL : http://15.165.22.64:8080/FavoriteSave.do
  * Http Method : POST
  * 제공해야하는 JSON 형식
```json
{
  "phone":"전화번호",
  "store_id":"가게id값"
}
```
  * 응답형식
```json
// 즐겨찾기 등록 성공 시
{
    "result": true,
    "message": "즐겨찾기가 정상적으로 등록되었습니다."
}
// 이미 등록된 즐겨찾기여서 등록 실패 시
{
    "result": false,
    "message": "이미 즐겨찾기로 등록된 가게 입니다."
}
```

* 즐겨찾기 삭제 처리
  * URL : http://15.165.22.64:8080/FavoriteDelete.do
  * Http Method : PUT
  * 제공해야하는 JSON 형식
```json
{
  "phone":"전화번호",
  "store_id":"즐겨찾기에서 삭제할 가게의 id값"
}
```
  * 응답 형식
```json
// 즐겨찾기 삭제 처리 성공 시
{
    "result": true,
    "message": "즐겨찾기 목록에서 삭제되었습니다."
}

// 즐겨찾기 삭제 처리 실패 시
{
    "result": false,
    "message": "즐겨찾기 삭제에 실패했습니다."
}
```

<hr/>

<h1>쿠폰 관련 API</h1>

* 쿠폰 리스트 가져오기
  * URL : http://15.165.22.64:8080/CouponFindByPhone.do?phone=전화번호
  * Http Method : GET
  * 제공해야하는 JSON 형식 : __없음, 파라미터로 `phone=전화번호` 전달__
  * 응답 형식
    * __`coupon_condition`은 쿠폰을 사용할 수 있는 최소 금액을 의미합니다.__
    * __`coupon_condition`은 해당 쿠폰이 사용 가능한 조건을 의미한다. 만약 이 값이 0 이라면 조건 없이 사용 가능함을 의미한다__.
    * __`coupon_type`은 `DISCOUNT`와 `SALE` 두 가지를 가지는데, `DISCOUNT`일 경우 `coupon_discount`만큼 할인함을 의미하며,__ 
      __`SALE` 일 경우 (100 - `coupon_discount`)% 를 할인함을 의미한다.__   
      __예를 들어 아래에서 `coupon_id=1`인 쿠폰은 구매 금액과 상관없이 적용 가능하며, 구매 금액에서 1000원을 할인해주는 것이다.__   
      __또한 `coupon_id=2`인 10000원 이상 구매 시 구매액의 10%를 할인해주게 된다.__
```json
// 해당 전화번호의 회원의 쿠폰 정보들이 존재할 때
{
    "result": true,
    "coupon": [
        {
            "coupon_title": "1000원 할인권",
            "coupon_condition": 1000,
            "coupon_id": 1,
            "coupon_content": "모든 가게에서 1000원 할인 가능!",
            "coupon_enddate": "2020년 9월 21일 17시 25분 30초",
            "coupon_discount": 1000,
            "coupon_type": "DISCOUNT"
        },
        {
            "coupon_title": "10% 할인권",
            "coupon_condition": 10000,
            "coupon_id": 2,
            "coupon_content": "모든 가게에서 10% 할인 가능!",
            "coupon_enddate": "2020년 9월 21일 17시 25분 50초",
            "coupon_discount": 10,
            "coupon_type": "SALE"
        },
        {
            "coupon_title": "5000원 할인권",
            "coupon_condition": 9000,
            "coupon_id": 3,
            "coupon_content": "모든 가게에서 5000원 할인 가능!",
            "coupon_enddate": "2020년 9월 21일 17시 26분 11초",
            "coupon_discount": 5000,
            "coupon_type": "DISCOUNT"
        },
        {
            "coupon_title": "5000원 할인권",
            "coupon_condition": 9000,
            "coupon_id": 3,
            "coupon_content": "모든 가게에서 5000원 할인 가능!",
            "coupon_enddate": "2020년 9월 21일 17시 26분 11초",
            "coupon_discount": 5000,
            "coupon_type": "DISCOUNT"
        }
    ],
    "message": "쿠폰 정보를 가져오기 성공"
}

// 해당 회원의 쿠폰이 존재하지 않을 경우
{
    "result": false,
    "message": "사용 가능한 쿠폰이 없습니다."
}
```

* 장바구니에서 결제 전에 사용 가능한 쿠폰 목록 가져오기
  * URL : http://15.165.22.64:8080/CouponFindUsable.do?phone=전화번호&price=결제전총금액
  * Http Method : GET
  * 제공해야하는 JSON 형식 : __없음, 파라미터로 `phone=전화번호`와 `price=전체금액` 의 총 2개 전달__
  * 응답 형식
```json
// 적용 가능한 쿠폰이 있을 경우
{
    "result": true,
    "coupon": [
        {
            "coupon_title": "10% 할인권",
            "coupon_condition": 10000,
            "coupon_id": 2,
            "coupon_content": "모든 가게에서 10% 할인 가능!",
            "coupon_enddate": "2020년 9월 21일 17시 25분 50초",
            "coupon_discount": 10,
            "coupon_type": "SALE"
        },
        {
            "coupon_title": "5000원 할인권",
            "coupon_condition": 9000,
            "coupon_id": 3,
            "coupon_content": "모든 가게에서 5000원 할인 가능!",
            "coupon_enddate": "2020년 9월 21일 17시 26분 11초",
            "coupon_discount": 5000,
            "coupon_type": "DISCOUNT"
        }
    ],
    "message": "적용 가능한 쿠폰 목록 가져오기 성공"
}

// 적용 가능한 쿠폰이 없을 경우
{
    "result": false,
    "message": "적용 가능한 쿠폰이 없습니다."
```

* 쿠폰 개수 가져오기
  * URL : http://15.165.22.64:8080/CouponCountByPhone.do?phone=전화번호
  * Http Method : GET
  * 제공해야하는 JSON 형식 : __없음, 파라미터로 `phone=전화번호` 전달__
  * 주의 사항 : 응답 형식에서 `result`는 무조건 `true`가 온다. 쿠폰이 없는 경우에는 `coupon_count`값이 `0`이 온다.
  * 응답 형식
```json
// 쿠폰이 존재하는 경우
{
    "result": true,
    "coupon_count": 4,
    "message": "쿠폰 개수 가져오기 성공"
}

// 쿠폰이 존재하지 않는 경우
{
    "result": true,
    "coupon_count": 0,
    "message": "쿠폰 개수 가져오기 성공"
}
```

* 쿠폰 사용 내역 가져오기
  * URL : http://15.165.22.64:8080/CouponHistoryByPhone.do?phone=전화번호
  * Http Method : GET
  * 제공해야하는 JSON 형식 : __없음, 파라미터로 `phone=전화번호` 전달
  * 응답 형식
```json
// 쿠폰 사용 내역이 존재할 경우
{
    "result": true,
    "history": [
        {
            "store_name": "test cafe",
            "total_price": 9500,
            "discount_price": 1000
        }
    ],
    "message": "쿠폰 사용 내역 가져오기 성공"
}

// 쿠폰 사용 내역이 존재하지 않을 경우
{
    "result": false,
    "message": "쿠폰 사용 내역이 존재하지 않습니다."
}
```
<hr/>

<h1>결제 관련 API</h1>

* User Token 발급받는 API
  * URL : http://15.165.22.64:8080/BillingGetUserToken.do
  * Http Method : POST
  * 제공해야하는 JSON 형식   
    __`user_id`는 사용자 고유값이나, 우리의 경우 전화번호로 사용자 고유값을 사용하므로 `user_id`와 `phone`이 동일하다__
```json
{"phone":"전화번호",
 "user_id":"전화번호"}
```
  * 응답 형식
```json
{
    "expired_unixtime": 1597839941,
    "expired_localtime": "2020-08-19 21:25:41 +0900",
    "user_token": "5f3d0c3518e1ae003ae2d9a0"
}
```
<hr/>

<h1>1:1 문의 내역 관련 API</h1>

* 문의 내역 리스트 가져오기
  * URL : http://15.165.22.64:8080/InquiryListFindByEmail.do?email=이메일
  * Http Method : GET
  * 제공해야하는 JSON 형식 : __없음, 파라미터로 `email=사용자이메일` 전달__
  * 응답 형식
```json
// 문의 내역이 존재할 경우
{
    "result": true,
    "inquiry": [
        {
            "inquiry_id": 1,
            "title": "문의 제목 1번 제목",
            "is_replied": "N",
            "inquiry_date": "2020년 8월 20일 11시 36분 41초"
        },
        {
            "inquiry_id": 2,
            "title": "문의 제목 2번 제목",
            "is_replied": "Y",
            "inquiry_date": "2020년 8월 20일 11시 36분 42초"
        },
        {
            "inquiry_id": 3,
            "title": "문의 제목 3번 제목",
            "is_replied": "N",
            "inquiry_date": "2020년 8월 20일 11시 36분 43초"
        },
        {
            "inquiry_id": 4,
            "title": "테스트 문의 제목",
            "is_replied": "N",
            "inquiry_date": "2020년 8월 20일 12시 20분 30초"
        },
        {
            "inquiry_id": 5,
            "title": "테스트 문의 제목2",
            "is_replied": "N",
            "inquiry_date": "2020년 8월 20일 12시 22분 25초"
        }
    ],
    "message": "email로 문의 내역 가져오기 성공"
}

// 문의 내역이 존재하지 않을 경우
{
    "result": false,
    "message": "1:1 문의 내역이 존재하지 않습니다."
}
```

* 문의 내역 상세 정보 가져오기
  * URL : http://15.165.22.64:8080/InquiryFindById.do?inquiry_id=문의내역id값
  * Http Method : GET
  * 제공해야하는 JSON 형식 : __없음, 파라미터로 `inquiry_id=문의내역id값` 전달__
  * 응답 형식
```json
// 성공했을 시
{
    "result": true,
    "inquiry_id": 1,
    "message": "inquiry_id로 문의 내역 정보 가져오기 성공",
    "title": "문의 제목 1번 제목",
    "is_replied": "N",
    "email": "sky_battle@naver.com",
    "content": "문의 내용 1번 내용",
    "inquiry_date": "2020년 8월 20일 "
}

// 해당 문의내역 id가 존재하지 않을 경우
{
    "result": false,
    "message": "해당 id의 문의 내역이 존재하지 않습니다."
}
```

* 1:1 문의 등록 하기
  * URL : http://15.165.22.64:8080/InquirySave.do
  * Http Method : POST
  * 제공해야하는 JSON 형식   
    __주의 : `title`, `content`는 반드시 공백이 아니여야 한다.__
```json
{
    "email":"문의자의 이메일",
    "title":"문의 제목",
    "content":"문의 내용"
}
```
  * 응답 형식
```json
// 문의 등록 성공 시
{
    "result": true,
    "message": "1:1 문의가 정상적으로 등록되었습니다."
}

// 등록 실패 시 (ex. 잘못된 이메일)
{
    "result": false,
    "message": "알 수 없는 원인으로 문의 등록에 실패했습니다."
}
```

* 결제 검증 처리
  * URL : http://15.165.22.64:8080/BillingVerify.do
  * Http Method : POST
  * 제공해야하는 JSON 형식
```json
{
    "phone":"전화번호",
    "receipt_id":"receipt_id"
}
```
  * 응답 형식
```json
// 검증 결과 올바른 결제이면
{
    "result": true,
    "code": 0,
    "message": ""
}

// 검증 결과 올바르지 않은 결제이면
{
    "result":false,
    "code":"0이 아닌 다른 값, bootpay에서 지정",
    "message":"비어있지 않은 값, bootpay에서 지정"
}
```
<hr/>

<h1>Socket Protocol</h1>

* URL : ws://15.165.22.64:8080/websocket

* Web Socket 메시지 방식은 아래의 규칙을 따른다.
  * (1) 서버에 고유id값 등록 (ex. 점주라면 가게id값) : `connect:::가게id값`
  * (2) 특정 가게id에 메시지 보내기 : `message:::가게id값:::보낼메시지`
  * __connect, message는 고정으로 보내야 하는 것임__
  * message에 아래와 같은 json형식으로 데이터를 담아 보낸다.
```json
{
    "coupon_id":-1, //쿠폰 적용 안된것
    "discount_price":-1,
    "each_count":1,
    "order_date":"2020/09/09 02:17:29",
    "orders":
    [
        {
        "extras":
            [
                {"extra_count":1,
                "extra_id":1,
                "extra_name":"HOT",
                "extra_price":0
                },
                {"extra_count":1,
                "extra_id":66,
                "extra_name":"기본 크기",
                "extra_price":0
                }
            ],
        "menu_defaultprice":1500,
        "menu_id":4,
        "menu_name":"아메리카노",
        "order_count":1
        }
    ],
    "phone":"01093756927",
    "receipt_id":"5f583b2e8f07510020236186",
    "store_id":1,
    "total_price":1500
}
```
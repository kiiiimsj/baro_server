점주용 API 문서
======

* 점주 로그인 처리
  * URL : http://54.180.56.44:8080/OwnerLogin.do
  * Http Method : POST
  * 제공해야 하는 JSON 형식
```json
{"phone":"점주전화번호", "pass":"점주비밀번호"}
```
  * 응답 형식
```json
// 로그인 성공 시
{
    "result": true,
    "store_id": 1,
    "nick": "sangwoo",
    "store_name": "test cafe",
    "message": "정상적으로 점주 로그인이 되었습니다.",
    "email": "robbyra@gmail.com"
}

// 로그인 실패 시
{
    "result": false,
    "message": "등록되지 않은 전화번호이거나 비밀번호가 일치하지 않습니다."
}
```

* 점주 비밀번호 변경 처리
  * URL : http://54.180.56.44:8080/OwnerPassUpdate.do
  * Http Method : PUT
  * 제공해야 하는 JSON 형식
```json
{"phone":"점주전화번호", "pass":"새로운비밀번호"}
```
  * 응답 형식
```json
// 변경 성공 시
{
    "result": true,
    "message": "비밀번호가 정상적으로 변경되었습니다."
}

// 변경 실패 시
{
    "result": false,
    "message": "가입된 전화번호가 아닙니다."
}
```

* 점주 이메일 변경 처리
  * URL : http://54.180.56.44:8080/OwnerEmailUpdate.do
  * Http Method : PUT
  * 제공해야 하는 JSON 형식
```json
{"phone":"점주전화번호", "email":"새로운이메일"}
```
  * 응답 형식
```json
// 변경 성공 시
{
    "result":true,
    "message":"이메일이 정상적으로 변경되었습니다."
}

// 변경 실패 시
{
    "result":false,
    "message":"이미 사용중인 이메일 입니다."
}

```

* 점주 회원가입 처리
  * URL : http://54.180.56.44:8080/OwnerRegister.do
  * Http Method : POST
  * 제공해야하는 JSON 형식
```json
{"phone":"전화번호", "email":"이메일", "nick":"점주명","pass":"비밀번호"}
```
  * 응답 형식
```json
// 점주 가입 성공 시
{
    "result": true,
    "message": "점주 가입에 성공했습니다. 가게를 등록해주세요."
}

// 점주 가입 실패 시 (1) : 이미 가입된 전화번호일 경우
{
    "result": false,
    "errno": 4,
    "message": "이미 가입된 전화번호 입니다."
}

// 점주 가입 실패 시 (2) : 이미 사용중인 이메일일 경우
{
    "result": false,
    "errno": 3,
    "message": "이미 사용중인 이메일 입니다."
}
```

* 점주 가게 등록 처리(이미 등록하는 가게일 경우)
  * URL : http://54.180.56.44:8080/OwnerSetStore.do
  * Http Method : PUT
  * 제공해야하는 JSON 형식
```json
{"phone":"점주전화번호", "store_id":"등록하려는 store_id값"}
```
  * 응답 형식
```json
// 성공적으로 점주에 가게 id를 등록했을 경우
{
    "result": true,
    "message": "정상적으로 가게 등록이 완료되었습니다."
}
// 가게 id가 존재하지 않을 경우
{
    "result": false,
    "message": "해당 store_id를 가진 가게 정보가 존재하지 않습니다."
}
```

* 점주 가게, 메뉴, 엑스트라 변경 처리
  * URL : http://54.180.56.44:8080/OwnerUpdates.do
  * Http Method : PUT
  * 제공해야하는 JSON 형식 __(update_type은 반드시 아래 지정한 것들 중 하나여야함)__
```json
{
    "store_id":"해당 업주가 운영하는 가게 id",
    "updates":[
        {
            "update_type":"UPDATE_MENU_PRICE",
            "menu_id":"menu_id",
            "menu_defaultprice":"100000"
        },
        {
            "update_type":"UPDATE_MENU_NAME",
            "menu_id":"menu_id",
            "menu_name":"NEW MENU NAME"
        },
        {
            "update_type":"DELETE_MENU",
            "menu_id":"menu_id"
        },
        {
          "update_type":"UPDATE_MENU_INFO",
          "menu_id":"menu_id",
          "menu_info":"menu_info"
        }
        {
            "update_type":"NEW_MENU",
            "menu_name":"NEW MENU NAME",
            "menu_defaultprice":"100000",
            "category_id":"category_id",
            "menu_info":"menu_info"
        },
        {
            "update_type":"UPDATE_EXTRA_PRICE",
            "extra_id":"extra_id",
            "extra_price":"100000"
        },
        {
            "update_type":"UPDATE_EXTRA_NAME",
            "extra_id":"extra_id",
            "extra_name":"NEW EXTRA NAME"
        },
        {
            "update_type":"DELETE_EXTRA",
            "extra_id":"extra_id"
        },
        {
            "update_type":"NEW_EXTRA",
            "extra_name":"extra_name",
            "extra_price":"extra_price",
            "menu_id":"menu_id",
            "is_required":"is_required",
            "extra_group":"extra_group"
        },
        {
            "update_type":"UPDATE_STORE_NAME",
            "store_name":"store_name"
        },
        {
            "update_type":"NEW_CATEGORY",
            "category_name":"CATEGORY_NAME"
        },
        {
            "update_type":"UPDATE_STORE_LATLNG",
            "store_latitude":"store_latitude",
            "store_longitude":"store_longitude"
        },
        {
            "update_type":"UPDATE_STORE_TIME",
            "store_opentime":"store_opentime",
            "store_closetime":"store_closetime"
        },
        {
            "update_type":"UPDATE_STORE_PHONE",
            "store_phone":"store_phone"
        },
        {
            "update_type":"UPDATE_STORE_DAYSOFF",
            "store_daysoff":"store_daysoff"
        },
        {
            "update_type":"UPDATE_STORE_LOCATION",
            "store_location":"store_location"
        },
        {
            "update_type":"UPDATE_STORE_INFO",
            "store_info":"store_info"
        }
    ]
}
```

* 특정 기간의 매출 가져오기
  * URL : http://54.180.56.44:8080/OwnerFindPriceBetweenDate.do
  * Http Method : POST
  * 제공해야하는 JSON 형식
```json
{
    "store_id":"가게id값",
    "startDate":"yyyy:MM:dd",
    "endDate":"yyyy:MM:dd"
}
```
  * 응답 형식
```json
{
    "total_price": 총매출(int),
    "coupon_total_price": 쿠폰으로 할인된 총 가격(int)
}
```

* 점주 가게 영업중, 준비중 변경 처리
  * URL : http://54.180.56.44:8080/OwnerSetStoreStatus.do
  * Http Method : PUT
  * 제공해야하는 JSON 형식
```json
{
    "store_id":"가게id값",
    "is_open":"Y 또는 N"
}
```
  * 응답 형식
```json
{
    "result": true,
    "message": "정상 처리 되었습니다."
}
```

* 처리해야할 주문들의 목록 가져오기
  * URL : http://54.180.56.44:8080/OrderFindByStoreId.do?store_id=가게id값
  * Http Method : GET
  * 제공해야하는 JSON형식 : __없음, 파라미터로 `store_id=가게id값` 전달__
  * 응답 형식
```json
// 처리해야할 주문 사항들이 존재할 때
{
    "result": true,
    "orders": [
        {
            "order_date": "2020년 8월 26일 15시 49분 39초",
            "order_count": 1,
            "total_price": 1500,
            "phone": "01093756927",
            "discount_price": 0,
            "receipt_id": "5f4605f8878a560039fa63a8"
        },
        {
            "order_date": "2020년 8월 26일 15시 54분 17초",
            "order_count": 1,
            "total_price": 1500,
            "phone": "01093756927",
            "discount_price": 0,
            "receipt_id": "5f460712878a560047fa6bdd"
        }
    ],
    "message": "처리 해야할 주문 요청 존재"
}

// 처리해야할 주문 사항들이 없을 때
{
    "result": false,
    "message": "새로 들어온 주문 사항이 없습니다."
}
```

* 처리해야할 주문 내역의 상세 정보 보기
  * URL : http://54.180.56.44:8080/OrderFindByReceiptId.do?receipt_id=영수증번호
  * Http Method : GET
  * 제공해야하는 JSON 형식 : __없음, 파라미터로 `receipt_id=영수증id값` 전달__
  * 응답 형식
```json
{
    "result": true,
    "orders": [
        {
            "order_count": 1,
            "menu_name": "쿠키",
            "menu_defaultprice": 1000,
            "extras": [],
            "order_id": 136,
            "order_state": "PREPARING"
        },
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
                    "extra_price": 500,
                    "extra_name": "사이즈업",
                    "extra_count": 1
                }
            ],
            "order_id": 137,
            "order_state": "PREPARING"
        }
    ],
    "message": "상세 주문 내역 가져오기 성공"
}
```

* 주문 결제 취소 처리
  * URL : http://54.180.56.44:8080/BillingCancel.do
  * Http Method : PUT
  * 제공해야하는 JSON 형식
```json
{
    "receipt_id":"부트페이에서 제공하는 고유 영수증id값",
    "cancel_reason":"환불 취소 사유",
    "store_name":"가게 이름",
    "nick":"점주명"
}
```
  * 응답 형식
```json
// 결제 취소 성공 시
{
    "result": true,
    "cancelled_price": 4000,
    "code": 0,
    "revoked_at": "2020-08-20 16:56:25",
    "request_cancel_price": 4000,
    "message": ""
}

// 결제 취소 실패 시
{
    "result":false,
    "code":"부트페이에서 제공하는 code값",
    "message":"부트페이에서 제공하는 메시지"
}
```

* 주문 제조 완료로 상태 변경 처리
  * URL : http://54.180.56.44:8080/OrderStatusDone.do
  * Http Method : PUT
  * 제공해야하는 JSON 형식
```json
{
    "receipt_id":"해당 주문의 고유 영수증id값"
}
```
  * 응답 형식
```json
{
    "result":true,
    "message":"제조 완료 상태로 변경 완료"
}
```

* 주문한 고객에게 메시지 보내기
  * URL : http://54.180.56.44:8080/OwnerSendMessage.do
  * Http Method : POST
  * 제공해야하는 JSON 형식
```json
{
    "phone":"주문한 고객의 전화번호",
    "title":"알림 제목",
    "content":"알림 내용"
}

// 예시
{
    "phone":"01029093199",
    "title":"test cafe에서 알려드립니다.",
    "content":"고객님이 주문하신 음식이 20분 내에 제조 완료될 예정입니다."
}
```

  * 응답 형식
```json
// 성공 시
// response는 단순 확인용 입니다.
{
    "result": true,
    "response": "projects/wantchu-e2a20/messages/0:1598672051903691%3dce49aff9fd7ecd",
    "message": "메시지 전송 성공"
}

// 실패 시 (고객 전화번호가 잘못되었을 때)
{
    "result": false,
    "message": "존재하지 않는 고객 전화번호 입니다."
}

// 실패 시 (서버 오류일 때)
{
    "result": false,
    "message": "The registration token is not a valid FCM registration token"
}
```


package com.wantchu.wantchu_server2.business;

public class SQL {

    public static class Type {
        public static final String FIND_ALL = "SELECT * FROM types";
    }

    public static class Store {
        public static final String FIND_BY_STORE_ID = "SELECT * FROM stores WHERE store_id=?";
        public static final String STORE_SEARCH = "SELECT * FROM stores WHERE store_name LIKE ?";
        public static final String FIND_INFO_BY_TYPE_CODE = "SELECT store_id, store_name, store_latitude, store_longitude, store_location, store_info, store_image, is_open FROM stores WHERE type_code=?";
        public static final String FIND_ALL_STORE_LOCATION = "SELECT store_id, store_name, store_latitude, store_longitude FROM stores";
        public static final String FIND_STORE_ID_BY_NAME = "SELECT store_id FROM stores WHERE store_name=?";
        public static final String IS_STORE_OPEN = "SELECT is_open FROM stores WHERE store_id = ?";
        public static final String UPDATE_STORE_NAME = "UPDATE stores SET store_name=? WHERE store_id=?";
        public static final String UPDATE_STORE_LAT_LNG = "UPDATE stores SET store_latitude=?, store_longitude=? WHERE store_id=?";
        public static final String UPDATE_STORE_TIME = "UPDATE stores SET store_opentime=?, store_closetime=? WHERE store_id=?";
        public static final String UPDATE_STORE_PHONE = "UPDATE stores SET store_phone=? WHERE store_id=?";
        public static final String UPDATE_STORE_DAYS_OFF = "UPDATE stores SET store_daysoff=? WHERE store_id=?";
        public static final String UPDATE_STORE_LOCATION = "UPDATE stores SET store_location=? WHERE store_id=?";
        public static final String UPDATE_STORE_INFO = "UPDATE stores SET store_info=? WHERE store_id=?";
        public static final String UPDATE_STORE_OPEN_STATUS = "UPDATE stores SET is_open=? WHERE store_id=?";
    }

    public static class Member {
        public static final String FIND_DEVICE_TOKEN = "SELECT device_token FROM members WHERE phone = ?";
        public static final String IS_VALID_ACCOUNT = "SELECT * FROM members WHERE phone=? AND pass=sha2(?, 256)";
        public static final String CHECK_PHONE = "SELECT * FROM members WHERE phone = ?";
        public static final String CHECK_EMAIL = "SELECT * FROM members WHERE email = ?";
        public static final String REGISTER = "INSERT INTO members VALUES(?,?,?,sha2(?, 256),DEFAULT, DEFAULT)";
        public static final String UPDATE_PASS = "UPDATE members SET pass=sha2(?, 256) WHERE phone=?";
        public static final String UPDATE_EMAIL = "UPDATE members SET email=? WHERE phone=?";
        public static final String UPDATE_DEVICE_TOKEN = "UPDATE members SET device_token = ? WHERE phone = ?";
    }

    public static class Owner {
        public static final String IS_VALID_ACCOUNT = "SELECT store_id, store_name, email, nick FROM owners NATURAL JOIN stores WHERE phone=? AND pass=sha2(?, 256)";
        public static final String UPDATE_PASS = "UPDATE owners SET pass=PASSWORD(?) WHERE phone=?";
        public static final String CHECK_PHONE = "SELECT * FROM owners WHERE phone = ?";
        public static final String CHECK_EMAIL = "SELECT * FROM owners WHERE email=?";
        public static final String UPDATE_EMAIL = "UPDATE owners SET email=? WHERE phone=?";
        public static final String REGISTER = "INSERT INTO owners VALUES(?,?,?,?,null)";
        public static final String CHECK_STORE_ID = "SELECT store_name FROM stores WHERE store_id=?";
        public static final String UPDATE_STORE_ID = "UPDATE owners SET store_id=? WHERE phone=?";
        public static final String UPDATE_STATUS_FIRST = "UPDATE orders SET order_state='ACCEPT' WHERE receipt_id=?";
        public static final String UPDATE_STATUS_COMPLETE="UPDATE orders SET order_state='DONE' WHERE receipt_id=?";
        public static final String FIND_STORE_STATISTICS_DEFAULT="SELECT CONCAT(YEAR(order_date),MONTH(order_date),DAY(order_date)) AS dategroup ,IFNULL(sum(menu_defaultprice*order_count),0) AS default_total_price FROM orders WHERE store_id=? GROUP BY dategroup AND order_date BETWEEN ? AND ?";
        public static final String FIND_STORE_STATISTICS_EXTRA="select CONCAT(YEAR(order_date),MONTH(order_date),DAY(order_date)) AS dategroup ,IFNULL(sum(extra_price*extra_count*order_count),0) AS extra_total_price FROM extraorders natural join orders WHERE store_id=? GROUP BY dategroup AND order_date BETWEEN ? AND ?";
    }

    public static class Extra {
        public static final String FIND_ID_BY_MENU_ID = "SELECT extra_id FROM extras WHERE menu_id=?";
        public static final String DELETE_BY_EXTRA_ID = "DELETE FROM extras WHERE extra_id=?";
        public static final String UPDATE_EXTRA_PRICE = "UPDATE extras SET extra_price=? WHERE extra_id=?";
        public static final String UPDATE_EXTRA_NAME = "UPDATE extras SET extra_name=? WHERE extra_id=?";
        public static final String INSERT_EXTRA_REQUIRED = "INSERT INTO extras VALUES(DEFAULT, ?, ?, ?, ?, 'Y')";
        public static final String INSERT_EXTRA_NOT_REQUIRED = "INSERT INTO extras VALUES(DEFAULT, ?, ?, ?, ?, 'N')";
        public static final String FIND_LIST_BY_MENU_ID = "select e.extra_id, e.extra_price, e.extra_name, r.extra_group, e.extra_maxcount from extras e left outer join requiredextras r using(extra_id) WHERE menu_id=? order by extra_group DESC, extra_price ASC";
    }

    public static class RequiredExtra {
        public static final String DELETE_BY_EXTRA_ID = "DELETE FROM requiredextras WHERE extra_id=?";
        public static final String INSERT_REQUIRED_EXTRA = "INSERT INTO requiredextras VALUES(?,?)";
    }

    public static class Menu {
        public static final String FIND_BY_STORE_ID = "SELECT * FROM menus WHERE store_id=?";
        public static final String UPDATE_MENU_PRICE = "UPDATE menus SET menu_defaultprice=? WHERE store_id=? AND menu_id=?";
        public static final String UPDATE_MENU_NAME = "UPDATE menus SET menu_name=? WHERE store_id=? AND menu_id=?";
        public static final String UPDATE_MENU_INFO = "UPDATE menus SET menu_info=? WHERE store_id=? AND menu_id=?";
        public static final String DELETE_MENU = "DELETE FROM menus WHERE menu_id=?";
        public static final String INSERT_MENU = "INSERT INTO menus VALUES(DEFAULT, ?, ?, ?, ?, ?)";
    }

    public static class Category {
        public static final String FIND_BY_STORE_ID = "SELECT * FROM categories WHERE store_id=?";
        public static final String INSERT_CATEGORY = "INSERT INTO categories VALUES(DEFAULT, ?, ?)";
    }

    public static class Order {
        public static final String INSERT_ORDER = "INSERT INTO orders VALUES(default, default, ?, ?, ?, ?, ?, ?, ?, ?)";
        public static final String FIND_ORDER_LIST_BY_PHONE = "SELECT receipt_id, store_name, order_date, sum(order_count) as CNT FROM orders NATURAL JOIN stores WHERE phone =? GROUP BY receipt_id;";
        public static final String FIND_ORDER_LIST_BY_PHONE_PREPARING_OR_ACCEPT = "SELECT receipt_id, store_name, order_date, order_state,sum(order_count) as CNT FROM orders NATURAL JOIN stores WHERE (phone =? AND (order_state ='PREPARING' OR order_state ='ACCEPT')) GROUP BY receipt_id;";
        public static final String TOTAL_PRICE_OF_ORDER_BY_RECEIPT_ID = "SELECT IFNULL(sum(menu_defaultprice*order_count),0) from orders where receipt_id=?";
        public static final String TOTAL_PRICE_OF_ORDERS_BETWEEN_DATE = "select ifnull(sum(menu_defaultprice*order_count),0) from orders where store_id=? AND order_date between ? AND ?";
        public static final String FIND_ORDER_IDS_BY_RECEIPT_ID = "SELECT order_id FROM orders WHERE receipt_id=?";
        public static final String FIND_ORDER_DETAIL_BY_ORDER_ID = "SELECT order_count, menu_name, menu_defaultprice, order_state FROM orders WHERE order_id=?";
        public static final String FIND_ORDER_COUNT_BY_PHONE = "select count(distinct receipt_id) from orders where phone=?";
        public static final String FIND_RECEIPT_IDS_OF_PREPARING_ORDERS = "SELECT DISTINCT receipt_id,order_state FROM orders WHERE store_id=? AND (order_state='ACCEPT' OR order_state='PREPARING')";
        public static final String FIND_RECEIPT_IDS_OF_ALL_ORDERS = "SELECT DISTINCT receipt_id FROM orders WHERE store_id=? limit ?, ?";
        public static final String FIND_PREPARING_ORDER_INFO_BY_RECEIPT_ID = "select phone, sum(order_count) as CNT, order_date from orders where receipt_id=?;";
        public static final String FIND_ALL_ORDER_INFO_BY_RECEIPT_ID = "SELECT phone, sum(order_count) as CNT, order_date, order_state from orders where receipt_id=?";
        public static final String UPDATE_ORDER_AS_CANCEL = "UPDATE orders SET order_state='CANCEL' WHERE receipt_id=?";
        public static final String UPDATE_ORDER_AS_DONE = "UPDATE orders SET order_state='DONE' WHERE receipt_id=?";
        public static final String FIND_RECEIPT_IDS_OF_DONE_ORDERS = "SELECT DISTINCT receipt_id FROM orders WHERE store_id=? AND order_date between ? AND ? AND (order_state='DONE' OR order_state='CANCEL') ORDER BY order_date DESC limit ?,20";
    }

    public static class ExtraOrder {
        public static final String INSERT_EXTRA_ORDER = "INSERT INTO extraorders VALUES(default, ?, ?, ?, ?, ?)";
        public static final String TOTAL_PRICE_OF_ORDER_BY_RECEIPT_ID = "SELECT IFNULL(sum(extra_price*extra_count*order_count), 0) from extraorders natural join orders where receipt_id=?";
        public static final String TOTAL_PRICE_OF_ORDERS_BETWEEN_DATE = "select ifnull(sum(extra_price*extra_count*order_count), 0) from extraorders natural join orders where store_id=? AND order_date between ? AND ?";
        public static final String FIND_DETAILS_BY_ORDER_ID = "SELECT extra_price, extra_name, extra_count FROM extraorders NATURAL JOIN extras WHERE order_id=?";
    }

    public static class Coupon {
        public static final String FIND_COUPONS_BY_PHONE = "select coupon_id, coupon_title, coupon_content, coupon_condition, coupon_enddate, coupon_discount, coupon_type from coupons natural join members natural join couponsbymembers where phone=? AND (now() < coupon_enddate) AND is_used='N'";
        public static final String FIND_USABLE_COUPONS_AT_PURCHASE = "select DISTINCT coupon_id, coupon_enddate, coupon_discount, coupon_type, coupon_title, coupon_content, coupon_condition  from coupons natural join couponsbymembers where phone=? AND (now() < coupon_enddate) AND (coupon_condition <= ?) AND is_used='N'";
    }

    public static class CouponHistory {
        public static final String FIND_LIST_BY_PHONE = "select discount_price, total_price, store_name from couponhistories natural join stores where phone=?";
        public static final String INSERT_COUPON_HISTORY = "INSERT INTO couponhistories VALUES(default, ?, ?, default, ?, ?, ?, ?)";
        public static final String TOTAL_DISCOUNT_PRICE_OF_ORDERS_BETWEEN_DATE = "select ifnull(sum(discount_price),0) from couponhistories where store_id=? and use_date between ? and ?";
        public static final String FIND_PRICE_INFO_BY_RECEIPT_ID = "select discount_price, total_price from couponhistories where receipt_id=?";
    }

    public static class CouponsByMembers {
        public static final String FIND_COUPON_COUNT_BY_PHONE = "select ifnull(count(*),0) from couponsbymembers natural join coupons natural join members where phone=? AND (now() < coupon_enddate) AND is_used='N'";
        public static final String UPDATE_COUPON_AS_USED = "UPDATE couponsbymembers SET is_used='Y' WHERE coupon_id=? AND phone=?";
    }

    public static class Notice {
        public static final String FIND_ALL = "SELECT * FROM notices";
        public static final String FIND_BY_CODE = "SELECT * FROM notices WHERE notice_code=?";
        public static final String FIND_BY_ID = "SELECT * FROM notices WHERE notice_id=?";
        public static final String INSERT_NOTICE = "INSERT INTO notices VALUES(DEFAULT, ?, ?, ?, DEFAULT)";
    }

    public static class Inquiry {
        public static final String FIND_BY_EMAIL = "SELECT inquiry_id, title, inquiry_date, is_replied FROM inquiries WHERE email=?";
        public static final String FIND_BY_ID = "SELECT * FROM inquiries WHERE inquiry_id=?";
        public static final String INSERT_INQUIRY = "INSERT INTO inquiries VALUES(default, ?, ?, ?, default, default)";
    }

    public static class Favorite {
        public static final String FIND_FAVORITES_BY_PHONE = "SELECT store_id, store_latitude, store_longitude, store_name, store_info, store_location, store_image, is_open FROM stores NATURAL JOIN favorites WHERE phone=?";
        public static final String INSERT_FAVORITE = "INSERT INTO favorites VALUES(?,?)";
        public static final String DELETE_FAVORITE = "DELETE FROM favorites WHERE phone=? AND store_id=?";
    }
}

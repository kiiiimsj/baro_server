package com.wantchu.wantchu_server2.business;

public class SQL {

    public static class Alert{
        public static final String FIND_ALERT_ALL = "SELECT id,alert_id,is_read,alert_title,alert_startdate FROM alertsbymembers NATURAL JOIN alert WHERE phone = ?";
        public static final String GET_RECENTLY_ALERT_DATE = "SELECT alert_startdate FROM alert ORDER BY alert_startdate DESC LIMIT 0,1";
        public static final String GET_RECENTLY_ALERT_ID = "SELECT alert_id FROM alert Order by alert_id DESC LIMIT 0,1";
        public static final String FIND_NEW_ALERT_COUNT = "SELECT COUNT(*) FROM alertsbymembers WHERE phone = ? AND is_read = 'N'";
        public static final String GET_ALERT_DETAIL_CONTENT = "SELECT alert_content FROM alert WHERE alert_id=?";
        public static final String ALERT_READ_CHECK = "UPDATE alertsbymembers SET is_read = 'Y' where alert_id=? AND phone=?";
    }

    public static class Type {
        public static final String FIND_ALL = "SELECT * FROM types";
    }
    public static class Event {
        public static final String FIND_ALL = "SELECT event_id, event_image FROM events";
        public static final String FIND_EVENT_DETAIL = "SELECT * FROM events WHERE event_id=?";
        public static final String FIND_EVENT_ADVERTISING = "SELECT event_id, event_image FROM events WHERE is_advertising='Y'";
    }

    public static class Store {
        public static final String FIND_BY_STORE_ID = "SELECT * FROM stores WHERE store_id=?";
        //public static final String STORE_SEARCH = "SELECT * FROM stores WHERE store_name LIKE ? Limit ?,? ";
        public static final String STORE_SEARCH_BY_ULTRA = "SELECT store_id, store_name,\n" +
                "(6371*acos(cos(RADIANS( ? ))*cos(radians(store_latitude))*cos(radians(store_longitude)\n" +
                "-RADIANS( ? ))+sin(RADIANS( ? ))*sin(radians(store_latitude))))*1000 AS distance , store_location, store_info, store_image, is_open FROM stores WHERE is_ultra='Y'";
        public static final String FIND_INFO_BY_TYPE_CODE = "SELECT store_id, store_name, " +
                "(6371*acos(cos(radians( ? ))*cos(radians(store_latitude))*cos(radians(store_longitude) " +
                "-radians( ? ))+sin(radians( ? ))*sin(radians(store_latitude))))*1000 AS DISTANCE , store_location, store_info, store_image, is_open FROM stores WHERE type_code=?"
                +" ORDER BY is_open='N' AND DISTANCE Limit ?,?";
        public static final String FIND_INFO_BY_KEYWORD = "SELECT store_id, store_name, " +
                "(6371*acos(cos(radians( ? ))*cos(radians(store_latitude))*cos(radians(store_longitude) " +
                "-radians( ? ))+sin(radians( ? ))*sin(radians(store_latitude))))*1000 AS DISTANCE , store_location, store_info, store_image, is_open FROM stores WHERE store_name LIKE ";
                /*+" ORDER BY DISTANCE";*/
        public static final String FIND_ALL_STORE_LOCATION = "SELECT store_id, store_name, store_latitude, store_longitude , " +
                "(6371*acos(cos(radians( ? ))*cos(radians(store_latitude))*cos(radians(store_longitude) " +
                "-radians( ? ))+sin(radians( ? ))*sin(radians(store_latitude))))*1000 " +
                "AS DISTANCE FROM stores " +
                "HAVING DISTANCE <= 1500 " +
                "ORDER BY distance ";
        public static final String STORE_SEARCH_BY_NEW = "SELECT store_id, store_name,\n" +
                "(6371*acos(cos(RADIANS( ? ))*cos(RADIANS(store_latitude))*cos(RADIANS(store_longitude)\n" +
                "-RADIANS( ? ))+sin(RADIANS( ? ))*sin(radians(store_latitude))))*1000 AS distance , store_location, store_info, store_image, is_open FROM new_stores";
        public static final String FIND_ALL_STORE_LOCATION2 = "SELECT store_id, store_name, store_latitude, store_longitude,(6371*acos(cos(radians( ? ))*cos(radians(store_latitude))*cos(radians(store_longitude) -radians( ? ))+sin(radians( ? ))*sin(radians(store_latitude))))*1000 AS DISTANCE FROM stores ORDER BY distance";
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
        public static final String IS_VALID_ACCOUNT = "SELECT store_id, store_name, email, nick, is_open FROM owners NATURAL JOIN stores WHERE phone=? AND pass=sha2(?, 256)";
        public static final String UPDATE_PASS = "UPDATE owners SET pass=PASSWORD(?) WHERE phone=?";
        public static final String CHECK_PHONE = "SELECT * FROM owners WHERE phone = ?";
        public static final String CHECK_EMAIL = "SELECT * FROM owners WHERE email=?";
        public static final String UPDATE_EMAIL = "UPDATE owners SET email=? WHERE phone=?";
        public static final String REGISTER = "INSERT INTO owners VALUES(?,?,?,?,null)";
        public static final String CHECK_STORE_ID = "SELECT store_name FROM stores WHERE store_id=?";
        public static final String UPDATE_STORE_ID = "UPDATE owners SET store_id=? WHERE phone=?";
        public static final String UPDATE_STATUS_FIRST = "UPDATE orders SET order_state='ACCEPT' WHERE receipt_id=?";
        public static final String UPDATE_STATUS_COMPLETE="UPDATE orders SET order_state='DONE' WHERE receipt_id=?";
        public static final String FIND_STORE_STATISTICS_DEFAULT="SELECT * FROM (SELECT DATE(order_date) AS dater1 ,IFNULL(sum(menu_defaultprice*order_count),0) AS default_total_price FROM orders\n" +
                "where store_id=? AND order_state='DONE' AND order_date BETWEEN ? AND DATE_ADD(?,INTERVAL 1 DAY)\n" +
                "GROUP BY DATE(order_date)) AS A\n" +
                "LEFT OUTER JOIN (SELECT DATE(order_date) AS dater2 ,ifnull(SUM(extra_price*extra_count),0) AS extra_total_price\n" +
                "FROM orders INNER JOIN extraorders ON orders.order_id = extraorders.order_id\n" +
                "where store_id=? AND order_state='DONE' and order_date BETWEEN ? AND DATE_ADD(?,INTERVAL 1 DAY)\n" +
                "GROUP BY DATE(order_date)) AS B\n" +
                "ON A.dater1=B.dater2";


        //public static final String FIND_STORE_STATISTICS_EXTRA="SELECT DATE(order_date),IFNULL(SUM(extra_price),0) as extra_total_price FROM orders INNER JOIN extraorders ON orders.order_id = extraorders.order_id where store_id=? and order_date BETWEEN ? AND ? GROUP BY DATE(order_date)";

        //public static final String UPDATE_OWNER_DEVICE_TOKEN = "UPDATE owners SET owner_device_token = ? WHERE phone = ?";
        public static final String FIND_DUPLICATE_TOKEN = "SELECT phone FROM owners WHERE store_id=? AND owner_device_token=?";
        public static final String FIND_MENU_LIST_STATISTICS = "SELECT * FROM (SELECT menu_name, count(*) as menu_count, IFNULL(sum(menu_defaultprice*order_count),0) AS default_total_price FROM orders\n" +
                "where store_id=? AND order_state='DONE' AND order_date BETWEEN ? AND DATE_ADD(? ,INTERVAL 1 DAY)\n" +
                "GROUP BY menu_name) AS A\n" +
                "LEFT OUTER JOIN (SELECT menu_name ,ifnull(SUM(extra_price*extra_count),0) AS extra_total_price\n" +
                "FROM orders INNER JOIN extraorders ON orders.order_id = extraorders.order_id\n" +
                "where store_id=? AND order_state='DONE' and order_date BETWEEN ? AND DATE_ADD(? ,INTERVAL 1 DAY)\n" +
                "GROUP BY menu_name) AS B\n" +
                "ON A.menu_name=B.menu_name" +
                "order by (default_total_price+extra_total_price) DESC ";
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
        public static final String SAVE_SOLDOUT = "UPDATE menus SET is_soldout ='Y' WHERE menu_id=?";
        public static final String DELETE_SOLDOUT = "UPDATE menus SET is_soldout ='N' WHERE menu_id=?";
    }

    public static class Category {
        public static final String FIND_BY_STORE_ID = "SELECT * FROM categories WHERE store_id=?";
        public static final String INSERT_CATEGORY = "INSERT INTO categories VALUES(DEFAULT, ?, ?)";
    }

    public static class Order {
        public static final String INSERT_ORDER = "INSERT INTO orders VALUES(default, default, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        public static final String FIND_ORDER_LIST_BY_PHONE = "SELECT receipt_id, store_name, order_date, sum(order_count) as CNT,order_state, store_image, store_id FROM orders NATURAL JOIN stores WHERE phone =? AND (order_state='CANCEL' OR order_state='DONE') GROUP BY receipt_id Limit ?,?;";
        public static final String FIND_ORDER_LIST_BY_PHONE_PREPARING_OR_ACCEPT = "SELECT receipt_id, store_name, order_date, order_state,sum(order_count) as CNT, store_image, store_latitude, store_longitude, store_phone FROM orders NATURAL JOIN stores WHERE (phone =? AND order_state ='PREPARING' OR order_state ='ACCEPT') GROUP BY receipt_id;";
        public static final String TOTAL_PRICE_OF_ORDER_BY_RECEIPT_ID = "SELECT IFNULL(sum(menu_defaultprice*order_count),0) from orders where receipt_id=?";
        public static final String TOTAL_PRICE_OF_ORDERS_BETWEEN_DATE = "select ifnull(sum(menu_defaultprice*order_count),0) from orders where store_id=? AND order_date between ? AND DATE_ADD(?,INTERVAL 1 DAY)";
        public static final String FIND_ORDER_IDS_BY_RECEIPT_ID = "SELECT order_id FROM orders WHERE receipt_id=?";
        public static final String FIND_ORDER_DETAIL_BY_ORDER_ID = "SELECT order_count, menu_name, menu_defaultprice, order_state FROM orders WHERE order_id=?";
        public static final String FIND_ORDER_COUNT_BY_PHONE = "select count(distinct receipt_id) from orders where phone=?";
        public static final String FIND_RECEIPT_IDS_OF_PREPARING_ORDERS = "SELECT DISTINCT receipt_id,order_state FROM orders WHERE store_id=? AND (order_state='ACCEPT' OR order_state='PREPARING')";
        //public static final String FIND_RECEIPT_IDS_OF_ALL_ORDERS = "SELECT DISTINCT receipt_id FROM orders WHERE store_id=? limit ?, ?";
        public static final String FIND_PREPARING_ORDER_INFO_BY_RECEIPT_ID = "select phone, sum(order_count) as CNT, order_date from orders where receipt_id=?;";
        public static final String FIND_ALL_ORDER_INFO_BY_RECEIPT_ID = "SELECT phone, sum(order_count) as CNT, order_date, order_state from orders where receipt_id=?";
        public static final String UPDATE_ORDER_AS_CANCEL = "UPDATE orders SET order_state='CANCEL' WHERE receipt_id=?";
        //public static final String UPDATE_ORDER_AS_DONE = "UPDATE orders SET order_state='DONE' WHERE receipt_id=?";
        public static final String FIND_RECEIPT_IDS_OF_DONE_ORDERS = "SELECT DISTINCT receipt_id FROM orders WHERE store_id=? AND order_date between ? AND DATE_ADD(?,INTERVAL 1 DAY) AND (order_state='DONE' OR order_state='CANCEL') ORDER BY order_date DESC limit ?,20";
        public static final String FIND_RECEIPT_IDS_OF_DONE_OR_CANCEL_BY_PHONE_ORDERS = "SELECT DISTINCT receipt_id FROM orders WHERE store_id=? AND phone=? AND (order_state='DONE' OR order_state='CANCEL') ORDER BY order_date DESC LIMIT ?,20";
        public static final String FIND_OWNER_DEVICE_TOKEN = "SELECT owner_device_token FROM owners WHERE store_id=?";
        public static final String FIND_DUPLICATE_TOKEN = "SELECT phone FROM owners WHERE store_id=? AND owner_device_token=?";
        public static final String FIND_ORDER_REQUESTS = "SELECT requests FROM orders WHERE receipt_id= ? LIMIT 1";
        public static final String FIND_ORDER_STATE = "SELECT order_state FROM orders WHERE receipt_id=?";
        public static final String CALCULATE_DEFAULT_PRICE = "select ifnull(sum(menu_defaultprice*order_count),0) \n" +
                "from orders\n" +
                "where store_id=?\n" +
                "AND order_date between (select DATE_FORMAT(DATE_SUB(NOW(), INTERVAL WEEKDAY(NOW()) DAY), '%Y-%m-%d')) AND DATE_ADD(NOW(),INTERVAL 1 DAY)";
    }

    public static class ExtraOrder {
        public static final String INSERT_EXTRA_ORDER = "INSERT INTO extraorders VALUES(default, ?, ?, ?, ?, ?)";
        public static final String TOTAL_PRICE_OF_ORDER_BY_RECEIPT_ID = "SELECT IFNULL(sum(extra_price*extra_count*order_count), 0) from extraorders natural join orders where receipt_id=?";
        public static final String TOTAL_PRICE_OF_ORDERS_BETWEEN_DATE = "select ifnull(sum(extra_price*extra_count*order_count), 0) as extra_price from extraorders natural join orders where store_id=? AND order_date between ? AND DATE_ADD(?,INTERVAL 1 DAY)";
        public static final String FIND_DETAILS_BY_ORDER_ID = "SELECT extra_price, extra_name, extra_count FROM extraorders NATURAL JOIN extras WHERE order_id=?";
        public static final String CALCULATE_EXTRA_PRICE = "select ifnull(sum(extra_price*extra_count*order_count), 0) as extra_price from extraorders natural join orders \n" +
                "where store_id=?\n" +
                "AND order_date between (select DATE_FORMAT(DATE_SUB(NOW(), INTERVAL WEEKDAY(NOW()) DAY), '%Y-%m-%d')) AND DATE_ADD(NOW(),INTERVAL 1 DAY)";
    }

    public static class Coupon {
        public static final String FIND_COUPONS_BY_PHONE = "select coupon_id, coupon_title, coupon_content, coupon_condition, coupon_enddate, coupon_discount, coupon_type from coupons natural join members natural join couponsbymembers where phone=? AND (now() < coupon_enddate) AND is_used='N'";
        public static final String FIND_USABLE_COUPONS_AT_PURCHASE = "select coupon_id, coupon_enddate, coupon_discount, coupon_type, coupon_title, coupon_content, coupon_condition  from coupons natural join couponsbymembers where phone=? AND (now() < coupon_enddate) AND (? - coupon_condition >= 500) AND is_used='N'";
        public static final String INSERT_BY_COUPON_NUMBER = "INSERT INTO couponsbymembers(phone,coupon_id) SELECT phone,coupon_id FROM members NATURAL JOIN coupons WHERE phone = ? AND coupon_id = ? AND NOT EXISTS (SELECT * FROM couponsbymembers WHERE phone = ? AND coupon_id = ?)";
    }

    public static class CouponHistory {
        public static final String FIND_LIST_BY_PHONE = "select discount_price, total_price, store_name from couponhistories natural join stores where phone=?";
        public static final String INSERT_COUPON_HISTORY = "INSERT INTO couponhistories VALUES(default, ?, ?, default, ?, ?, ?, ?)";
        public static final String TOTAL_DISCOUNT_PRICE_OF_ORDERS_BETWEEN_DATE = "select ifnull(sum(discount_price),0) from couponhistories where store_id=? and use_date between ? and DATE_ADD(?,INTERVAL 1 DAY)";
        public static final String FIND_PRICE_INFO_BY_RECEIPT_ID = "select discount_price, total_price from couponhistories where receipt_id=?";
        public static final String CALCULATE_COUPON_PRICE = "select ifnull(sum(discount_price),0) \n" +
                "from couponhistories \n" +
                "where store_id=?\n" +
                "and use_date between (select DATE_FORMAT(DATE_SUB(NOW(), INTERVAL WEEKDAY(NOW()) DAY), '%Y-%m-%d')) and DATE_ADD(NOW(), INTERVAL 1 DAY)";
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
        public static final String FIND_FAVORITES_BY_PHONE
                = "SELECT store_id, (6371*acos(cos(radians(?))*cos(radians(store_latitude))*cos(radians(store_longitude)\n" +
                "\n" +
                "\t-radians(?))+sin(radians(?))*sin(radians(store_latitude))))*1000\n" +
                "\n" +
                "\tAS distance, store_name, store_info, store_location, store_image, is_open FROM stores\n" +
                " NATURAL JOIN favorites WHERE phone=? ORDER BY is_open='N' AND distance";
        public static final String INSERT_FAVORITE = "INSERT INTO favorites VALUES(?,?)";
        public static final String DELETE_FAVORITE = "DELETE FROM favorites WHERE phone=? AND store_id=?";
        public static final String CHECK_FAVORITE = "SELECT * FROM favorites WHERE phone=? AND store_id=?";
    }
}

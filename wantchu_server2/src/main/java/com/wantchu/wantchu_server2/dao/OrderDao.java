package com.wantchu.wantchu_server2.dao;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.wantchu.wantchu_server2.business.SQL;
import com.wantchu.wantchu_server2.coupon.exception.CouponHistoryNotFoundException;
import com.wantchu.wantchu_server2.order.dto.OrderCompleteBetweenDateReqeustDto;
import com.wantchu.wantchu_server2.order.exception.OrderNoPreparingException;
import com.wantchu.wantchu_server2.order.exception.OrderNotFoundByPhoneException;
import com.wantchu.wantchu_server2.order.exception.OrderNotFoundException;
import com.wantchu.wantchu_server2.vo.*;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;

@Component
public class OrderDao {

    private JdbcTemplate jdbcTemplate;

    public OrderDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int orderInsert(final OrderVo orderVo) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.Order.INSERT_ORDER, new String[] {"order_id"});
            preparedStatement.setString(1, orderVo.getPhone());
            preparedStatement.setInt(2, orderVo.getStore_id());
            preparedStatement.setInt(3, orderVo.getMenu_id());
            preparedStatement.setString(4, orderVo.getMenu_name());
            preparedStatement.setInt(5, orderVo.getMenu_defaultprice());
            preparedStatement.setInt(6, orderVo.getOrder_count());
            preparedStatement.setString(7, orderVo.getReceipt_id());
            preparedStatement.setString(8, orderVo.getOrder_state());
            return preparedStatement;
        }, keyHolder);
        Number keyValue = keyHolder.getKey();
        return keyValue.intValue();
    }

    public void extraOrderInsert(final ExtraOrderVo extraOrderVo) {
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.ExtraOrder.INSERT_EXTRA_ORDER);
            preparedStatement.setInt(1, extraOrderVo.getOrder_id());
            preparedStatement.setInt(2, extraOrderVo.getExtra_id());
            preparedStatement.setString(3, extraOrderVo.getExtra_name());
            preparedStatement.setInt(4, extraOrderVo.getExtra_price());
            preparedStatement.setInt(5, extraOrderVo.getExtra_count());
            return preparedStatement;
        });
    }


    public List<OrderListVo> findAbstractOrderInfo(String phone) throws OrderNotFoundByPhoneException {
        List<OrderListVo> list = jdbcTemplate.query(
                SQL.Order.FIND_ORDER_LIST_BY_PHONE,
                (resultSet, i) -> {
                    OrderListVo orderListVo = new OrderListVo();
                    orderListVo.setReceipt_id(resultSet.getString("receipt_id"));
                    orderListVo.setStore_name(resultSet.getString("store_name"));
                    orderListVo.setOrder_date(resultSet.getTimestamp("order_date").toLocalDateTime());
                    orderListVo.setTotal_count(resultSet.getInt("CNT"));
                    return orderListVo;
                }
                , phone);
        if(list.size() == 0) {
            throw new OrderNotFoundByPhoneException();
        } else {
            return list;
        }
    }

    public int findExtraOrderTotalPrice(String receipt_id) {
        Integer extraOrderSum = jdbcTemplate.queryForObject(
                SQL.ExtraOrder.TOTAL_PRICE_OF_ORDER_BY_RECEIPT_ID, Integer.class, receipt_id);
        return extraOrderSum;
    }

    public int findOrderTotalPrice(String receipt_id) {
        Integer orderSum = jdbcTemplate.queryForObject(
                SQL.Order.TOTAL_PRICE_OF_ORDER_BY_RECEIPT_ID, Integer.class, receipt_id);
        return orderSum;
    }

    public List<Integer> findOrderIdsByReceiptId(String receipt_id) {
        List<Integer> list = jdbcTemplate.query(
                SQL.Order.FIND_ORDER_IDS_BY_RECEIPT_ID,
                (resultSet, i) -> resultSet.getInt("order_id")
                , receipt_id);
        return list;
    }

    public void insertCouponHistory(CouponHistoryVo couponHistoryVo) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(SQL.CouponHistory.INSERT_COUPON_HISTORY);
                    preparedStatement.setString(1, couponHistoryVo.getPhone());
                    preparedStatement.setInt(2, couponHistoryVo.getCoupon_id());
                    preparedStatement.setInt(3, couponHistoryVo.getDiscount_price());
                    preparedStatement.setInt(4, couponHistoryVo.getStore_id());
                    preparedStatement.setInt(5, couponHistoryVo.getTotal_price());
                    preparedStatement.setString(6, couponHistoryVo.getReceipt_id());
                    return preparedStatement;
                }
        );
    }

    public void setCouponAsUsed(CouponHistoryVo couponHistoryVo) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(SQL.CouponsByMembers.UPDATE_COUPON_AS_USED);
                    preparedStatement.setInt(1, couponHistoryVo.getCoupon_id());
                    preparedStatement.setString(2, couponHistoryVo.getPhone());
                    return preparedStatement;
                }
        );
    }

    public List<ExtraOrderVo> findExtraOrdersByOrderId(int order_id) {
        List<ExtraOrderVo> list = jdbcTemplate.query(
                SQL.ExtraOrder.FIND_DETAILS_BY_ORDER_ID,
                (resultSet, i) -> {
                    ExtraOrderVo extraOrderVo = new ExtraOrderVo();
                    extraOrderVo.setExtra_price(resultSet.getInt("extra_price"));
                    extraOrderVo.setExtra_name(resultSet.getString("extra_name"));
                    extraOrderVo.setExtra_count(resultSet.getInt("extra_count"));
                    return extraOrderVo;
                }
                , order_id);
        return list;
    }

    public OrderDetailVo findOrderDetailByOrderId(int order_id) {
        List<OrderDetailVo> list = jdbcTemplate.query(
                SQL.Order.FIND_ORDER_DETAIL_BY_ORDER_ID,
                (resultSet, i) -> {
                    OrderDetailVo detailVo = new OrderDetailVo();
                    detailVo.setMenu_defaultprice(resultSet.getInt("menu_defaultprice"));
                    detailVo.setMenu_name(resultSet.getString("menu_name"));
                    detailVo.setOrder_count(resultSet.getInt("order_count"));
                    detailVo.setOrder_state(resultSet.getString("order_state"));
                    return detailVo;
                }
        , order_id);
        return list.get(0);
    }

    public int findOrderCountByPhone(String phone) {
        Integer result = jdbcTemplate.queryForObject(SQL.Order.FIND_ORDER_COUNT_BY_PHONE, Integer.class, phone);
        return result;
    }

    public List<OrderVo> findReceiptIdsOfPreparingOrders(int store_id) throws OrderNoPreparingException {
        List<OrderVo> list = jdbcTemplate.query(
                SQL.Order.FIND_RECEIPT_IDS_OF_PREPARING_ORDERS,
                (resultSet, i) -> {
                    OrderVo orderVo = new OrderVo();
                    String receipt_id = resultSet.getString("receipt_id");
                    String order_state = resultSet.getString("order_state");
                    orderVo.setOrder_state(order_state);
                    orderVo.setReceipt_id(receipt_id);
                    return orderVo;
                }
                , store_id);
        if(list.size() == 0) {
            throw new OrderNoPreparingException();
        } else {
            return list;
        }
    }

    public List<String> findReceiptIdsOfAllOrders(int store_id, int start, int end) throws OrderNotFoundException {
        List<String> list = jdbcTemplate.query(
                SQL.Order.FIND_RECEIPT_IDS_OF_ALL_ORDERS,
                (resultSet, i) -> {
                    String receipt_id = resultSet.getString("receipt_id");
                    return receipt_id;
                }
        , store_id, start, end);
        if(list.size() == 0) {
            throw new OrderNotFoundException();
        } else {
            return list;
        }
    }

    public OrderVo findPreparingOrderInfoByReceiptId(String receipt_id) {
        List<OrderVo> list = jdbcTemplate.query(
                SQL.Order.FIND_PREPARING_ORDER_INFO_BY_RECEIPT_ID,
                (resultSet, i) -> {
                    OrderVo orderVo = new OrderVo();
                    orderVo.setPhone(resultSet.getString("phone"));
                    orderVo.setOrder_count(resultSet.getInt("CNT"));
                    orderVo.setOrder_date(resultSet.getTimestamp("order_date").toLocalDateTime());
                    return orderVo;
                }
                , receipt_id);
        return list.get(0);
    }

    public OrderVo findAllOrderInfoByReceiptId(String receipt_id){
        List<OrderVo> list = jdbcTemplate.query(
                SQL.Order.FIND_ALL_ORDER_INFO_BY_RECEIPT_ID,
                (resultSet, i) -> {
                    OrderVo orderVo = new OrderVo();
                    orderVo.setPhone(resultSet.getString("phone"));
                    orderVo.setOrder_count(resultSet.getInt("CNT"));
                    orderVo.setOrder_date(resultSet.getTimestamp("order_date").toLocalDateTime());
                    orderVo.setOrder_state(resultSet.getString("order_state"));
                    return orderVo;
                }
        , receipt_id);
        return list.get(0);
    }

    public CouponHistoryVo findOrderPriceInfoByReceiptId(String receipt_id) throws CouponHistoryNotFoundException {
        List<CouponHistoryVo> list = jdbcTemplate.query(
                SQL.CouponHistory.FIND_PRICE_INFO_BY_RECEIPT_ID,
                (resultSet, i) -> {
                    CouponHistoryVo historyVo = new CouponHistoryVo();
                    historyVo.setTotal_price(resultSet.getInt("total_price"));
                    historyVo.setDiscount_price(resultSet.getInt("discount_price"));
                    return historyVo;
                }
                , receipt_id);
        if(list.size() == 0) {
            throw new CouponHistoryNotFoundException();
        } else {
            return list.get(0);
        }
    }

    public void setOrderStateAsCancel(String receipt_id) {
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.Order.UPDATE_ORDER_AS_CANCEL);
            preparedStatement.setString(1, receipt_id);
            return preparedStatement;
        });
    }

    public void setOrderStateAsDone(String receipt_id) {
        jdbcTemplate.update(connection -> {
           PreparedStatement preparedStatement = connection.prepareStatement(SQL.Order.UPDATE_ORDER_AS_DONE);
           preparedStatement.setString(1, receipt_id);
           return preparedStatement;
        });
    }


    public void findOrderPrepareOrDoneByPhone(String phone) {
        List<CouponHistoryVo> list = jdbcTemplate.query(
                SQL.CouponHistory.FIND_PRICE_INFO_BY_RECEIPT_ID,
                (resultSet, i) -> {
                    CouponHistoryVo historyVo = new CouponHistoryVo();
                    historyVo.setTotal_price(resultSet.getInt("total_price"));
                    historyVo.setDiscount_price(resultSet.getInt("discount_price"));
                    return historyVo;
                }
                , phone);


    }
}

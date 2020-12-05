package com.wantchu.wantchu_server2.dao;

import com.wantchu.wantchu_server2.business.SQL;
import com.wantchu.wantchu_server2.coupon.exception.CouponHistoryNotFoundException;
import com.wantchu.wantchu_server2.coupon.exception.CouponNoUsableFoundException;
import com.wantchu.wantchu_server2.coupon.exception.CouponNotFoundByPhoneException;
import com.wantchu.wantchu_server2.coupon.exception.CouponNotInsertException;
import com.wantchu.wantchu_server2.vo.CouponHistoryVo;
import com.wantchu.wantchu_server2.vo.CouponVo;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.List;

@Component
public class CouponDao {

    private JdbcTemplate jdbcTemplate;

    public CouponDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<CouponHistoryVo> findCouponHistoryByPhone(String phone) throws CouponHistoryNotFoundException {
        List<CouponHistoryVo> list = jdbcTemplate.query(
                SQL.CouponHistory.FIND_LIST_BY_PHONE,
                (resultSet, i) -> {
                    CouponHistoryVo historyVo = new CouponHistoryVo();
                    historyVo.setTotal_price(resultSet.getInt("total_price"));
                    historyVo.setDiscount_price(resultSet.getInt("discount_price"));
                    historyVo.setStore_name(resultSet.getString("store_name"));
                    return historyVo;
                }
                , phone);
        Iterator<CouponHistoryVo> iterator = list.iterator();
        if(iterator.hasNext()) {
            return list;
        } else {
            throw new CouponHistoryNotFoundException();
        }
    }


    public List<CouponVo> findCouponsByPhone(String phone) throws CouponNotFoundByPhoneException {
        List<CouponVo> list = jdbcTemplate.query(
                SQL.Coupon.FIND_COUPONS_BY_PHONE,
                (resultSet, i) -> {
                     CouponVo couponVo = new CouponVo();
                     couponVo.setCoupon_id(resultSet.getInt("coupon_id"));
                     couponVo.setCoupon_title(resultSet.getString("coupon_title"));
                     couponVo.setCoupon_content(resultSet.getString("coupon_content"));
                     couponVo.setCoupon_condition(resultSet.getInt("coupon_condition"));
                     couponVo.setCoupon_discount(resultSet.getInt("coupon_discount"));
                     couponVo.setCoupon_enddate(resultSet.getTimestamp("coupon_enddate").toLocalDateTime());
                     couponVo.setCoupon_type(resultSet.getString("coupon_type"));
                     return couponVo;
                }
                , phone);
        Iterator<CouponVo> iterator = list.iterator();
        if(iterator.hasNext()) {
            return list;
        } else {
            throw new CouponNotFoundByPhoneException();
        }
    }

    public int findCouponCountByPhone(String phone) {
        Integer result = jdbcTemplate.queryForObject(SQL.CouponsByMembers.FIND_COUPON_COUNT_BY_PHONE, Integer.class, phone);
        return result;
    }

    public List<CouponVo> findUsableCouponsByPhoneAndPrice(String phone, int price) throws CouponNoUsableFoundException {
        List<CouponVo> list = jdbcTemplate.query(
                SQL.Coupon.FIND_USABLE_COUPONS_AT_PURCHASE,
                (resultSet, i) -> {
                     CouponVo couponVo = new CouponVo();
                     couponVo.setCoupon_id(resultSet.getInt("coupon_id"));
                     couponVo.setCoupon_enddate(resultSet.getTimestamp("coupon_enddate").toLocalDateTime());
                     couponVo.setCoupon_discount(resultSet.getInt("coupon_discount"));
                     couponVo.setCoupon_type(resultSet.getString("coupon_type"));
                     couponVo.setCoupon_title(resultSet.getString("coupon_title"));
                     couponVo.setCoupon_content(resultSet.getString("coupon_content"));
                     couponVo.setCoupon_condition(resultSet.getInt("coupon_condition"));
                     return couponVo;
                }
                , phone, price);
        Iterator<CouponVo> iterator = list.iterator();
        if(iterator.hasNext()) {
            return list;
        } else {
            throw new CouponNoUsableFoundException();
        }
    }

    public void insertCouponNumber(String phone, String coupon_id) throws CouponNotInsertException {
        int result = jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.Coupon.INSERT_BY_COUPON_NUMBER);
            preparedStatement.setString(1, phone);
            preparedStatement.setString(2, coupon_id);
            preparedStatement.setString(1, phone);
            preparedStatement.setString(2, coupon_id);
            return preparedStatement;
        });
        if(result == 0) {
            throw new CouponNotInsertException();
        }
    }

}

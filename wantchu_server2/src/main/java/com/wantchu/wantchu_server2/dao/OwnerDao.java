package com.wantchu.wantchu_server2.dao;

import com.wantchu.wantchu_server2.business.SQL;
import com.wantchu.wantchu_server2.member.exception.MemberPhoneNotFoundException;
import com.wantchu.wantchu_server2.owner.dto.*;
import com.wantchu.wantchu_server2.owner.exception.OwnerLoginException;
import com.wantchu.wantchu_server2.owner.exception.OwnerPassUpdateException;
import com.wantchu.wantchu_server2.owner.exception.StatisticsNotFoundException;
import com.wantchu.wantchu_server2.vo.*;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.jetbrains.annotations.NotNull;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Component
public class OwnerDao {

    private JdbcTemplate jdbcTemplate;

    public OwnerDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public OwnerVo isValidAccount(@NotNull OwnerLoginRequestDto requestDto) throws OwnerLoginException {
        List<OwnerVo> list = jdbcTemplate.query(
                SQL.Owner.IS_VALID_ACCOUNT,
                (resultSet, i) -> {
                    OwnerVo ownerVo = OwnerVo.builder()
                        .store_id(resultSet.getInt("store_id"))
                        .store_name(resultSet.getString("store_name"))
                        .email(resultSet.getString("email"))
                        .phone(resultSet.getString("phone"))
                        .is_open(resultSet.getString("is_open"))
                        .build();
                    return ownerVo;
                }
                , requestDto.getId(), requestDto.getPass());
        if(list.size() == 0) {
            throw new OwnerLoginException();
        } else {
            return list.get(0);
        }
    }

//    public void updateDeviceToken(String phone, String owner_device_token){
//        jdbcTemplate.update(connection -> {
//           PreparedStatement preparedStatement = connection.prepareStatement(SQL.Owner.UPDATE_OWNER_DEVICE_TOKEN);
//           preparedStatement.setString(1, owner_device_token);
//           preparedStatement.setString(2, phone);
//           return preparedStatement;
//        });
//    }

    public void updatePassword(OwnerPassUpdateRequestDto requestDto) throws OwnerPassUpdateException {
        int result = jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.Owner.UPDATE_PASS);
            preparedStatement.setString(1, requestDto.getPass());
            preparedStatement.setString(2, requestDto.getPhone());
            return preparedStatement;
        });
        if(result == 0) {
            throw new OwnerPassUpdateException();
        }
    }

    public boolean isPhoneInUse(String phone) {
        List<OwnerVo> list = jdbcTemplate.query(
                SQL.Owner.CHECK_PHONE,
                (resultSet, i) -> new OwnerVo()
                , phone);
        if(list.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isEmailInUse(String email) {
        List<OwnerVo> list = jdbcTemplate.query(
                SQL.Owner.CHECK_EMAIL,
                (resultSet, i) -> new OwnerVo()
                , email);
        if(list.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void updateEmail(OwnerEmailUpdateRequestDto requestDto) {
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.Owner.UPDATE_EMAIL);
            preparedStatement.setString(1, requestDto.getEmail());
            preparedStatement.setString(2, requestDto.getPhone());
            return preparedStatement;
        });
    }

    public void register(OwnerRegisterRequestDto requestDto) {
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.Owner.REGISTER);
            preparedStatement.setString(1, requestDto.getPhone());
            preparedStatement.setString(2, requestDto.getEmail());
            preparedStatement.setString(3, requestDto.getNick());
            preparedStatement.setString(4, requestDto.getPass());
            return preparedStatement;
        });
    }

    public boolean isValidStoreId(int store_id) {
        List<StoreVo> list = jdbcTemplate.query(
                SQL.Owner.CHECK_STORE_ID,
                (resultSet, i) -> new StoreVo()
                , store_id);
        if(list.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void setOwnerStore(OwnerStoreSetRequestDto requestDto) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(SQL.Owner.UPDATE_STORE_ID);
                    preparedStatement.setInt(1, requestDto.getStore_id());
                    preparedStatement.setString(2, requestDto.getPhone());
                    return preparedStatement;
                });
    }

    public void updateMenuPrice(MenuVo menuVo) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(SQL.Menu.UPDATE_MENU_PRICE);
                    preparedStatement.setInt(1, menuVo.getMenu_defaultprice());
                    preparedStatement.setInt(2, menuVo.getStore_id());
                    preparedStatement.setInt(3, menuVo.getMenu_id());
                    return preparedStatement;
                }
        );
    }

    public void updateMenuName(MenuVo menuVo) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(SQL.Menu.UPDATE_MENU_NAME);
                    preparedStatement.setString(1, menuVo.getMenu_name());
                    preparedStatement.setInt(2, menuVo.getStore_id());
                    preparedStatement.setInt(3, menuVo.getMenu_id());
                    return preparedStatement;
                }
        );
    }

    public void updateMenuInfo(MenuVo menuVo) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(SQL.Menu.UPDATE_MENU_INFO);
                    preparedStatement.setString(1, menuVo.getMenu_info());
                    preparedStatement.setInt(2, menuVo.getStore_id());
                    preparedStatement.setInt(3, menuVo.getMenu_id());
                    return preparedStatement;
                }
        );
    }

    public void deleteMenu(int menu_id) {
        List<Integer> list = jdbcTemplate.query(
                SQL.Extra.FIND_ID_BY_MENU_ID,
                (resultSet, i) -> resultSet.getInt("extra_id")
                , menu_id);

        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()) {
            int extra_id = iterator.next();
            jdbcTemplate.update(
                    connection -> {
                        PreparedStatement preparedStatement = connection.prepareStatement(SQL.RequiredExtra.DELETE_BY_EXTRA_ID);
                        preparedStatement.setInt(1, extra_id);
                        return preparedStatement;
                    }
            );

            jdbcTemplate.update(
                    connection -> {
                        PreparedStatement preparedStatement = connection.prepareStatement(SQL.Extra.DELETE_BY_EXTRA_ID);
                        preparedStatement.setInt(1, extra_id);
                        return preparedStatement;
                    }
            );

            jdbcTemplate.update(
                    connection -> {
                        PreparedStatement preparedStatement = connection.prepareStatement(SQL.Menu.DELETE_MENU);
                        preparedStatement.setInt(1, menu_id);
                        return preparedStatement;
                    }
            );
        }
    }

    public void insertMenu(MenuVo menuVo) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(SQL.Menu.INSERT_MENU);
                    preparedStatement.setInt(1, menuVo.getMenu_defaultprice());
                    preparedStatement.setInt(2, menuVo.getCategory_id());
                    preparedStatement.setString(3, menuVo.getMenu_name());
                    preparedStatement.setString(4, menuVo.getMenu_info());
                    preparedStatement.setInt(5, menuVo.getStore_id());
                    return preparedStatement;
                }
        );
    }

    public void updateExtraPrice(ExtraVo extraVo) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(SQL.Extra.UPDATE_EXTRA_PRICE);
                    preparedStatement.setInt(1, extraVo.getExtra_price());
                    preparedStatement.setInt(2, extraVo.getExtra_id());
                    return preparedStatement;
                }
        );
    }

    public void updateExtraName(ExtraVo extraVo) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(SQL.Extra.UPDATE_EXTRA_NAME);
                    preparedStatement.setString(1, extraVo.getExtra_name());
                    preparedStatement.setInt(2, extraVo.getExtra_id());
                    return preparedStatement;
                }
        );
    }

    public void deleteExtra(int extra_id) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(SQL.RequiredExtra.DELETE_BY_EXTRA_ID);
                    preparedStatement.setInt(1, extra_id);
                    return preparedStatement;
                }
        );

        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(SQL.Extra.DELETE_BY_EXTRA_ID);
                    preparedStatement.setInt(1, extra_id);
                    return preparedStatement;
                }
        );
    }

    public void insertExtra(ExtraVo extraVo) {
        if(!((extraVo.getExtra_group()).equals("null"))) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(
                    connection -> {
                        PreparedStatement preparedStatement = connection.prepareStatement(SQL.Extra.INSERT_EXTRA_REQUIRED, new String[] {"extra_id"});
                        preparedStatement.setInt(1, extraVo.getExtra_price());
                        preparedStatement.setString(2, extraVo.getExtra_name());
                        preparedStatement.setInt(3, extraVo.getStore_id());
                        preparedStatement.setInt(4, extraVo.getMenu_id());
                        return preparedStatement;
                    }, keyHolder);
            int newExtra_id = keyHolder.getKey().intValue();
            jdbcTemplate.update(
                    connection -> {
                        PreparedStatement preparedStatement = connection.prepareStatement(SQL.RequiredExtra.INSERT_REQUIRED_EXTRA);
                        preparedStatement.setInt(1, newExtra_id);
                        preparedStatement.setString(2, extraVo.getExtra_group());
                        return preparedStatement;
                    }
            );
        }
        else {
            jdbcTemplate.update(
                    connection -> {
                        PreparedStatement preparedStatement = connection.prepareStatement(SQL.Extra.INSERT_EXTRA_NOT_REQUIRED);
                        preparedStatement.setInt(1, extraVo.getExtra_price());
                        preparedStatement.setString(2, extraVo.getExtra_name());
                        preparedStatement.setInt(3, extraVo.getStore_id());
                        preparedStatement.setInt(4, extraVo.getMenu_id());
                        return preparedStatement;
                    }
            );
        }
    }

    public void updateStoreName(StoreVo storeVo) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(SQL.Store.UPDATE_STORE_NAME);
                    preparedStatement.setString(1, storeVo.getStore_name());
                    preparedStatement.setInt(2, storeVo.getStore_id());
                    return preparedStatement;
                }
        );
    }

    public void updateStoreLatLng(StoreVo storeVo) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(SQL.Store.UPDATE_STORE_LAT_LNG);
                    preparedStatement.setDouble(1, storeVo.getStore_latitude());
                    preparedStatement.setDouble(2, storeVo.getStore_longitude());
                    preparedStatement.setInt(3, storeVo.getStore_id());
                    return preparedStatement;
                }
        );
    }

    public void updateStoreTime(StoreVo storeVo) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(SQL.Store.UPDATE_STORE_TIME);
                    preparedStatement.setString(1, storeVo.getStore_opentime());
                    preparedStatement.setString(2, storeVo.getStore_closetime());
                    preparedStatement.setInt(3, storeVo.getStore_id());
                    return preparedStatement;
                }
        );
    }

    public void updateStorePhone(StoreVo storeVo) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(SQL.Store.UPDATE_STORE_PHONE);
                    preparedStatement.setString(1, storeVo.getStore_phone());
                    preparedStatement.setInt(2, storeVo.getStore_id());
                    return preparedStatement;
                }
        );
    }

    public void updateStoreDaysOff(StoreVo storeVo) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(SQL.Store.UPDATE_STORE_DAYS_OFF);
                    preparedStatement.setString(1, storeVo.getStore_daysoff());
                    preparedStatement.setInt(2, storeVo.getStore_id());
                    return preparedStatement;
                }
        );
    }

    public void updateStoreLocation(StoreVo storeVo) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(SQL.Store.UPDATE_STORE_LOCATION);
                    preparedStatement.setString(1, storeVo.getStore_location());
                    preparedStatement.setInt(2, storeVo.getStore_id());
                    return preparedStatement;
                }
        );
    }

    public void updateStoreInfo(StoreVo storeVo) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(SQL.Store.UPDATE_STORE_INFO);
                    preparedStatement.setString(1, storeVo.getStore_info());
                    preparedStatement.setInt(2, storeVo.getStore_id());
                    return preparedStatement;
                }
        );
    }

    public void insertCategory(CategoryVo categoryVo) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(SQL.Category.INSERT_CATEGORY);
                    preparedStatement.setInt(1, categoryVo.getStore_id());
                    preparedStatement.setString(2, categoryVo.getCategory_name());
                    return preparedStatement;
                }
        );
    }

    public int findExtraOrderTotalPriceOfStore(OwnerPriceBetweenDateRequestDto requestDto) {
        Integer extraOrderTotalPrice = jdbcTemplate.queryForObject(
                SQL.ExtraOrder.TOTAL_PRICE_OF_ORDERS_BETWEEN_DATE, Integer.class, requestDto.getStore_id(), requestDto.getStart_date(), requestDto.getEnd_date());
        return extraOrderTotalPrice;
    }

    public int findMenuDefaultTotalPriceOfStore(OwnerPriceBetweenDateRequestDto requestDto) {
        Integer menuDefaultTotalPrice = jdbcTemplate.queryForObject(
                SQL.Order.TOTAL_PRICE_OF_ORDERS_BETWEEN_DATE, Integer.class, requestDto.getStore_id(), requestDto.getStart_date(), requestDto.getEnd_date());
        return menuDefaultTotalPrice;
    }

    public int findCouponDiscountPriceOfStore(OwnerPriceBetweenDateRequestDto requestDto) {
        Integer couponDiscountTotalPrice = jdbcTemplate.queryForObject(
                SQL.CouponHistory.TOTAL_DISCOUNT_PRICE_OF_ORDERS_BETWEEN_DATE, Integer.class, requestDto.getStore_id(), requestDto.getStart_date(), requestDto.getEnd_date());
        return couponDiscountTotalPrice;
    }

    //정산쪽 계산( 위 3개와 비슷)
    public int findCalculateDefault(int store_id){
        Integer sumOfDefault = jdbcTemplate.queryForObject(
                SQL.Order.CALCULATE_DEFAULT_PRICE, Integer.class, store_id);
        return sumOfDefault;
    }

    public int findCalculateExtra(int store_id){
        Integer sumOfExtra = jdbcTemplate.queryForObject(
                SQL.ExtraOrder.CALCULATE_EXTRA_PRICE, Integer.class, store_id);
        return sumOfExtra;
    }

    public int findCalculateCoupon(int store_id){
        Integer sumOfCoupon = jdbcTemplate.queryForObject(
                SQL.CouponHistory.CALCULATE_COUPON_PRICE, Integer.class, store_id);
        return sumOfCoupon;
    }
    public int findCalculateDiscount(int store_id) {
        try {
            Integer sumOfDiscount = jdbcTemplate.queryForObject(
                    SQL.Order.CALCULATE_DISCOUNT_PRICE, Integer.class, store_id, store_id);
            return sumOfDiscount;
        }catch (EmptyResultDataAccessException e){
            return 0;
        } catch (NullPointerException e){
            return 0;
        }

    }
    public int findCalculateDefaultDiscount(int store_id) {
        try {
            Integer defaultDiscount = jdbcTemplate.queryForObject(
                    SQL.Order.CALCULATE_DISCOUNT_DEFAULT_PRICE, Integer.class, store_id);
            return defaultDiscount;
        }catch (EmptyResultDataAccessException e){
            return 0;
        } catch (NullPointerException e){
            return 0;
        }

    }
    public int findCalculateExtraDiscount(int store_id) {
        try {
            Integer sumOfExtraDiscounts= jdbcTemplate.queryForObject(
                    SQL.Order.CALCULATE_DISCOUNT_EXTRA_PRICE, Integer.class, store_id);
            return sumOfExtraDiscounts;
        }catch (EmptyResultDataAccessException e){
            return 0;
        } catch (NullPointerException e){
            return 0;
        }

    }

    public void setStoreOpenOrClosed(String is_open, int store_id) {
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.Store.UPDATE_STORE_OPEN_STATUS);
            preparedStatement.setString(1, is_open);
            preparedStatement.setInt(2, store_id);
            return preparedStatement;
        });
    }

    public String getDeviceTokenByPhone(String phone) throws MemberPhoneNotFoundException{
        List<String> list = jdbcTemplate.query(
                SQL.Member.FIND_DEVICE_TOKEN,
                (resultSet, i) -> {
                    String device_token = resultSet.getString("device_token");
                    return device_token;
                }
                , phone);
        if(list.size() == 0) {
            throw new MemberPhoneNotFoundException();
        } else {
            return list.get(0);
        }
    }

    public void setStatusFirst(String receipt_id){
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.Owner.UPDATE_STATUS_FIRST);
            preparedStatement.setString(1, receipt_id);
            return preparedStatement;
        });
    }
    public void setStatusComplete(String receipt_id){
        jdbcTemplate.update(connection -> {
           PreparedStatement preparedStatement = connection.prepareStatement(SQL.Owner.UPDATE_STATUS_COMPLETE);
           preparedStatement.setString(1, receipt_id);
           return preparedStatement;
        });
    }
    public List<PriceByDayVo> setStatistics(OwnerSetStatisticsRequestDto requestDto) throws StatisticsNotFoundException {
        List<PriceByDayVo> list = jdbcTemplate.query(
                SQL.Owner.FIND_STORE_STATISTICS_DEFAULT,
                (resultSet, i) -> {
                    PriceByDayVo priceByDayVo = new PriceByDayVo();
                    priceByDayVo.setDate(resultSet.getString("dater1"));
                    priceByDayVo.setDefaultPrice(resultSet.getInt("default_total_price"));
                    priceByDayVo.setExtraPrice(resultSet.getInt("extra_total_price"));
                    return priceByDayVo;
                }
                ,requestDto.getStore_id(),requestDto.getStart_date(), requestDto.getEnd_date(),requestDto.getStore_id(),requestDto.getStart_date(), requestDto.getEnd_date());
        if(list.size() == 0){
            throw new StatisticsNotFoundException();
        }
        else{
            return list;
        }
    }

//    public List<PriceByDayVo> setStatisticsExtra(OwnerSetStatisticsRequestDto requestDto) throws StatisticsNotFoundException{
//        List<PriceByDayVo> list = jdbcTemplate.query(
//                SQL.Owner.FIND_STORE_STATISTICS_EXTRA,
//                (resultSet, i) -> {
//                    PriceByDayVo priceByDayVo = new PriceByDayVo();
//                    priceByDayVo.setDate(resultSet.getString("DATE(order_date)"));
//                    priceByDayVo.setPrice(resultSet.getInt("extra_total_price"));
//                    return priceByDayVo;
//                }
//                ,requestDto.getStore_id(),requestDto.getStart_date(), requestDto.getEnd_date());
//        if(list.size() == 0){
//            throw new StatisticsNotFoundException();
//        }
//        else{
//            return list;
//        }
//    }


//    public boolean duplicateToken(int store_id, String owner_device_token){
//        List<String> list = jdbcTemplate.query(
//                SQL.Owner.FIND_DUPLICATE_TOKEN,
//                (resultSet, i) -> {
//                    String phone = resultSet.getString("phone");
//                    return phone;
//                }
//                ,store_id, owner_device_token);
//        if(list.size() == 0){
//            return false;
//        }
//        else{
//            return true;
//        }
//    }

    public List<MenuStatisticsVo> setMenuStatistics(OwnerSetStatisticsRequestDto requestDto) throws StatisticsNotFoundException{
        List<MenuStatisticsVo> list = jdbcTemplate.query(
                SQL.Owner.FIND_MENU_LIST_STATISTICS,
                (resultSet, i) -> {
                    MenuStatisticsVo menuStatisticsVo = new MenuStatisticsVo();
                    menuStatisticsVo.setMenu_name(resultSet.getString("menu_name"));
                    menuStatisticsVo.setMenu_count(resultSet.getInt("menu_count"));
                    menuStatisticsVo.setDefault_price(resultSet.getInt("default_total_price"));
                    menuStatisticsVo.setExtra_price(resultSet.getInt("extra_total_price"));
                    return menuStatisticsVo;
                }
                ,requestDto.getStore_id(), requestDto.getStart_date(), requestDto.getEnd_date(),requestDto.getStore_id(), requestDto.getStart_date(), requestDto.getEnd_date());
        if(list.size() == 0) {
            throw new StatisticsNotFoundException();
        }
        else{
            return list;
        }
    }


    public void setStoreDiscount(OwnerSetStoreDiscountDto requestDto) {
        jdbcTemplate.update(connection -> {
           PreparedStatement preparedStatement = connection.prepareStatement(SQL.Owner.UPDATE_STORE_DISCOUNT_RATE);
           preparedStatement.setInt(2, requestDto.getStore_id());
           preparedStatement.setInt(1, requestDto.getDiscount_rate());
           return preparedStatement;
        });
    }
}

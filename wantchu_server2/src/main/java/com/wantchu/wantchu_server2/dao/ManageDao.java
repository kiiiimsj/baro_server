package com.wantchu.wantchu_server2.dao;

import com.google.firebase.database.annotations.NotNull;
import com.wantchu.wantchu_server2.business.SQL;
import com.wantchu.wantchu_server2.manage.dto.*;
import com.wantchu.wantchu_server2.manage.exception.*;
import com.wantchu.wantchu_server2.owner.dto.OwnerRegisterRequestDto;
import com.wantchu.wantchu_server2.vo.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

@Component
public class ManageDao {

    private JdbcTemplate jdbcTemplate;

    public ManageDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public void insertType(@NotNull TypeInsertDto requestDto) {
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.Manage.INSERT_TYPE);
            preparedStatement.setString(1, requestDto.getType_code());
            preparedStatement.setString(2, requestDto.getType_name());
            preparedStatement.setString(3, requestDto.getType_image());
            return preparedStatement;
        });
    }

    public void deleteType(String type_code) throws DeleteTypeException {
        try{
            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL.Manage.DELETE_TYPE);
                preparedStatement.setString(1, type_code);
                return preparedStatement;
            });
        }
        catch(Exception e) {
            throw new DeleteTypeException();
        }
    }

    public List<PrintTypeVo> printType() throws NotFoundTypeException {
        List<PrintTypeVo> list = jdbcTemplate.query(
                SQL.Manage.PRINT_TYPE,
                (resultSet, i) -> {
                    PrintTypeVo vo = new PrintTypeVo(resultSet.getString("type_code"), resultSet.getString("type_name"));
                    return vo;
                });
        if(list.size() == 0) {
            throw new NotFoundTypeException();
        }
        else{
            return list;
        }
    }

    public void insertUltra(@NotNull UltraInsertDto requestDto) {
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.Manage.INSERT_ULTRA);
            preparedStatement.setInt(1, requestDto.getStore_id());
            preparedStatement.setString(2, requestDto.getStore_large_image());
            return preparedStatement;
        });
    }

    public void deleteUltra(int store_id) throws DeleteUltraException {
        try{
            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL.Manage.DELETE_ULTRA);
                preparedStatement.setInt(1, store_id);
                return preparedStatement;
            });
        }
        catch (Exception e) {
            throw new DeleteUltraException();
        }
    }

    public List<PrintUltraVo> printUltra() throws NotFoundUltraException {
        List<PrintUltraVo> list = jdbcTemplate.query(
                SQL.Manage.PRINT_ULTRA,
                (resultSet, i) -> {
                    PrintUltraVo vo = new PrintUltraVo(resultSet.getInt("store_id"), resultSet.getInt("ultra_store_id"), resultSet.getString("store_name"));
                    return vo;
                });
        if(list.size() == 0) {
            throw new NotFoundUltraException();
        }
        else{
            return list;
        }
    }


    public void insertNewStore(NewStoreInsertDto requestDto) {
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.Manage.INSERT_NEW_STORE);
            preparedStatement.setInt(1, requestDto.getStore_id());
            preparedStatement.setString(2, requestDto.getStore_large_image());
            return preparedStatement;
        });
    }

    public void deleteNewStore(int store_id) throws DeleteNewStoreException {
        try{
            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL.Manage.DELETE_NEW_STORE);
                preparedStatement.setString(1, String.valueOf(store_id));
                return preparedStatement;
            });
        }
        catch(Exception e) {
            throw new DeleteNewStoreException();
        }
    }

    public List<PrintNewStoreVo> printNewStore() throws NotFoundNewStoreException {
        List<PrintNewStoreVo> list = jdbcTemplate.query(
                SQL.Manage.PRINT_NEW_STORE,
                (resultSet, i) -> {
                    PrintNewStoreVo vo = new PrintNewStoreVo(resultSet.getInt("store_id"), resultSet.getInt("new_store_id"), resultSet.getString("store_name"));
                    return vo;
                });
        if(list.size() == 0) {
            throw new NotFoundNewStoreException();
        }
        else{
            return list;
        }
    }

    public void insertAlert(AlertInsertDto requestDto) {
        jdbcTemplate.update(con -> {
          PreparedStatement preparedStatement = con.prepareStatement(SQL.Manage.INSERT_ALERT);
          preparedStatement.setString(1,requestDto.getAlert_title());
          preparedStatement.setString(2,requestDto.getAlert_content());
          return  preparedStatement;
        });
    }

    public void deleteAlert(int alert_id) throws DeleteAlertException {
        try {
            jdbcTemplate.update(con -> {
                PreparedStatement preparedStatement = con.prepareStatement(SQL.Manage.DELETE_ALERT);
                preparedStatement.setInt(1, alert_id);
                return preparedStatement;
            });
        }catch (Exception e) {
            throw new DeleteAlertException();
        }
    }

    public List<PrintAlertVo> printAlert() throws NotFoundAlertException {
        List<PrintAlertVo> list = jdbcTemplate.query(
                SQL.Manage.PRINT_ALERT,
                (resultSet, i) -> {
                    PrintAlertVo vo = new PrintAlertVo(resultSet.getInt("alert_id"), resultSet.getString("alert_title"));
                    return vo;
                });
        if(list.size() == 0) {
            throw new NotFoundAlertException();
        }
        else{
            return list;
        }
    }

    public void insertStore(StoreInsertDto requestDto) {
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(SQL.Manage.INSERT_STORES);
            preparedStatement.setString(1,requestDto.getType_code());
            preparedStatement.setString(2,requestDto.getStore_name());
            preparedStatement.setDouble(3,requestDto.getStore_latitude());
            preparedStatement.setDouble(4,requestDto.getStore_longitude());
            preparedStatement.setString(5,requestDto.getStore_opentime());
            preparedStatement.setString(6,requestDto.getStore_closetime());
            preparedStatement.setString(7,requestDto.getStore_phone());
            preparedStatement.setString(8,requestDto.getStore_dayoff());
            preparedStatement.setString(9,requestDto.getStore_location());
            preparedStatement.setString(10,requestDto.getStore_info());
//            preparedStatement.setString(11,requestDto.getStore_image());
            preparedStatement.setString(11,requestDto.getOwner_id());
            preparedStatement.setString(12,requestDto.getRepresentative_name());
            preparedStatement.setString(13,requestDto.getBusiness_number());
            return  preparedStatement;
        });
    }

    public void deleteStore(int store_id) throws DeleteStoreException {
        try {
            jdbcTemplate.update(con -> {
                PreparedStatement preparedStatement = con.prepareStatement(SQL.Manage.DELETE_STORES);
                preparedStatement.setInt(1, store_id);
                return preparedStatement;
            });
        }catch (Exception e) {
            throw new DeleteStoreException();
        }
    }

    public List<PrintStoreVo> printStore(String store_name) throws NotFoundStoreException {
        List<PrintStoreVo> list = jdbcTemplate.query(
                SQL.Manage.PRINT_STORES+"'%"+store_name+"%'",
                (resultSet, i) -> {
                    PrintStoreVo vo = new PrintStoreVo(resultSet.getInt("store_id"), resultSet.getString("type_code"), resultSet.getString("store_name"));
                    return vo;
                });
        if(list.size() == 0) {
            throw new NotFoundStoreException();
        }
        else{
            return list;
        }
    }

    public void insertCategory(CategoryInsertDto requestDto) {
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(SQL.Manage.INSERT_CATEGORIES);
            preparedStatement.setInt(1,requestDto.getStore_id());
            preparedStatement.setString(2,requestDto.getCategory_name());
            return  preparedStatement;
        });
    }

    public void deleteCategory(int category_id) throws DeleteCategoryException {
        try {
            jdbcTemplate.update(con -> {
                PreparedStatement preparedStatement = con.prepareStatement(SQL.Manage.DELETE_CATEGORY);
                preparedStatement.setInt(1, category_id);
                return preparedStatement;
            });
        }catch (Exception e) {
            throw new DeleteCategoryException();
        }
    }

    public List<PrintCategoryVo> printCategory(int store_id) throws NotFoundCategoryException {
        List<PrintCategoryVo> list = jdbcTemplate.query(
                SQL.Manage.PRINT_CATEGORIES+store_id,
                (resultSet, i) -> {
                    PrintCategoryVo vo = new PrintCategoryVo(resultSet.getInt("category_id"), resultSet.getString("category_name"));
                    return vo;
                });
        if(list.size() == 0) {
            throw new NotFoundCategoryException();
        }
        else{
            return list;
        }
    }

    public void insertMenu(MenuInsertDto requestDto) {
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(SQL.Manage.INSERT_MENUS);
            preparedStatement.setInt(1,requestDto.getMenu_defaultprice());
            preparedStatement.setInt(2,requestDto.getCategory_id());
            preparedStatement.setString(3,requestDto.getMenu_name());
            preparedStatement.setString(4,requestDto.getMenu_info());
            preparedStatement.setInt(5,requestDto.getStore_id());
            preparedStatement.setString(6,requestDto.getMenu_image());
            return  preparedStatement;
        });
    }

    public void deleteMenu(int menu_id) throws DeleteMenuException {
        try {
            jdbcTemplate.update(con -> {
                PreparedStatement preparedStatement = con.prepareStatement(SQL.Manage.DELETE_MENUS);
                preparedStatement.setInt(1, menu_id);
                return preparedStatement;
            });
        }catch (Exception e) {
            throw new DeleteMenuException();
        }
    }

    public List<PrintMenuVo> printMenu(int category_id) throws NotFoundMenuException {
        List<PrintMenuVo> list = jdbcTemplate.query(
                SQL.Manage.PRINT_MENUS+category_id,
                (resultSet, i) -> {
                    PrintMenuVo vo = new PrintMenuVo(resultSet.getInt("menu_id"),resultSet.getString("menu_name"),resultSet.getInt("menu_defaultprice"));
                    return vo;
                });
        if(list.size() == 0) {
            throw new NotFoundMenuException();
        }
        else{
            return list;
        }
    }

    public void insertEvent(EventInsertDto requestDto) {
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(SQL.Manage.INSERT_EVENTS);
            preparedStatement.setString(1,requestDto.getEvent_image());
            preparedStatement.setString(2,requestDto.getEvent_title());
            preparedStatement.setString(3,requestDto.getEvent_content());
            return  preparedStatement;
        });
    }

    public void deleteEvent(int event_id) throws DeleteEventException {
        try {
            jdbcTemplate.update(con -> {
                PreparedStatement preparedStatement = con.prepareStatement(SQL.Manage.DELETE_EVENTS);
                preparedStatement.setInt(1, event_id);
                return preparedStatement;
            });
        }catch (Exception e) {
            throw new DeleteEventException();
        }
    }

    public List<PrintEventVo> printEvent() throws NotFoundEventException {
        List<PrintEventVo> list = jdbcTemplate.query(
                SQL.Manage.PRINT_EVENTS,
                (resultSet, i) -> {
                    PrintEventVo vo = new PrintEventVo(resultSet.getInt("event_id"),resultSet.getString("event_title"));
                    return vo;
                });
        if(list.size() == 0) {
            throw new NotFoundEventException();
        }
        else{
            return list;
        }
    }

    public void insertCoupon(CouponInsertDto requestDto) {
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(SQL.Manage.INSERT_COUPONS);
            preparedStatement.setString(1,requestDto.getCoupon_title());
            preparedStatement.setString(2,requestDto.getCoupon_content());
            preparedStatement.setInt(3,requestDto.getCoupon_condition());
            preparedStatement.setInt(4,requestDto.getCounpon_discount());
            preparedStatement.setString(5,requestDto.getCoupon_type());
            preparedStatement.setInt(6,requestDto.getCoupon_number());
            return  preparedStatement;
        });
    }

    public void deleteCoupon(int coupon_id) throws DeleteCouponException {
        try {
            jdbcTemplate.update(con -> {
                PreparedStatement preparedStatement = con.prepareStatement(SQL.Manage.DELETE_COUPONS);
                preparedStatement.setInt(1, coupon_id);
                return preparedStatement;
            });
        }catch (Exception e) {
            throw new DeleteCouponException();
        }
    }

    public List<PrintCouponVo> printCoupon() throws NotFoundCouponException {
        List<PrintCouponVo> list = jdbcTemplate.query(
                SQL.Manage.PRINT_COUPONS,
                (resultSet, i) -> {
                    PrintCouponVo vo = new PrintCouponVo(resultSet.getInt("coupon_id"),resultSet.getString("coupon_title"));
                    return vo;
                });
        if(list.size() == 0) {
            throw new NotFoundCouponException();
        }
        else{
            return list;
        }
    }

    public String findOwner(String ownerPhone)  {
        String result = jdbcTemplate.queryForObject(SQL.Manage.FIND_OWNER_BY_PHONE, String.class, ownerPhone);
        return result;
    }

    public void insertExtra(ExtraInsertDto requestDto) {
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(SQL.Manage.INSERT_EXTRAS);
            preparedStatement.setInt(1,requestDto.getExtra_price());
            preparedStatement.setString(2,requestDto.getExtra_name());
            preparedStatement.setInt(3,requestDto.getStore_id());
            if (new Boolean(requestDto.getIs_required())){
                preparedStatement.setString(4,requestDto.getRequired_group_name());
            }else{
                preparedStatement.setString(4,null);
            }

            return  preparedStatement;
        });
    }

    public void deleteExtra(int extra_id) throws DeleteExtraException {
        try {
            jdbcTemplate.update(con -> {
                PreparedStatement preparedStatement = con.prepareStatement(SQL.Manage.DELETE_EXTRAS);
                preparedStatement.setInt(1, extra_id);
                return preparedStatement;
            });
        }catch (Exception e) {
            throw new DeleteExtraException();
        }
    }

    public List<PrintExtraVo> printExtra(int store_id) throws NotFoundExtraException {
        List<PrintExtraVo> list = jdbcTemplate.query(
                SQL.Manage.PRINT_EXTRAS,
                (resultSet, i) -> {
                    PrintExtraVo vo = new PrintExtraVo(resultSet.getInt("extra_id"),resultSet.getInt("extra_price"),
                            resultSet.getString("extra_name"));
                    return vo;
                },store_id);
        if(list.size() == 0) {
            throw new NotFoundExtraException();
        }
        else{
            return list;
        }
    }

    public void insertExtraByMenu(ExtraByMenuInsertDto request) {
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(SQL.Manage.INSERT_EXTRA_BY_MENU);
            preparedStatement.setInt(1,request.getExtra_id());
            preparedStatement.setInt(2,request.getMenu_id());
            preparedStatement.setInt(3,request.getExtra_maxcount());
            return  preparedStatement;
        });
    }

    public void deleteExtraByMenu(int id) throws DeleteExtraByMenuException {
        try {
            jdbcTemplate.update(con -> {
                PreparedStatement preparedStatement = con.prepareStatement(SQL.Manage.DELETE_EXTRA_BY_MENU);
                preparedStatement.setInt(1, id);
                return preparedStatement;
            });
        }catch (Exception e) {
            throw new DeleteExtraByMenuException();
        }
    }

    public List<PrintExtraByMenuVo> extraByMenuPrint(int menu_id) throws NotFoundExtraException {
        List<PrintExtraByMenuVo> list = jdbcTemplate.query(
                SQL.Manage.PRINT_EXTRA_BY_MENU,
                (resultSet, i) -> {
                    PrintExtraByMenuVo vo = new PrintExtraByMenuVo(resultSet.getInt("id"),resultSet.getInt("extra_maxcount"),
                            resultSet.getString("extra_name"),resultSet.getInt("extra_price"));
                    return vo;
                },menu_id);
        if(list.size() == 0) {
            throw new NotFoundExtraException();
        }
        else{
            return list;
        }
    }

    public List<FindOrderListByPhoneForManageVo> findOrderList(String phone) throws NotFoundManageOrderListException {
        List<FindOrderListByPhoneForManageVo> list = jdbcTemplate.query(
                SQL.Manage.FIND_ORDER_LIST_BY_PHONE_FOR_MANAGE,
                (resultSet, i) -> {
                    FindOrderListByPhoneForManageVo vo = new FindOrderListByPhoneForManageVo(
                            resultSet.getString("order_date"),resultSet.getString("receipt_id"),
                            resultSet.getString("order_state"),resultSet.getString("store_name"),
                            resultSet.getInt("store_id"),resultSet.getString("representative_name"));
                    return vo;
                },phone);
        if(list.size() == 0) {
            throw new NotFoundManageOrderListException();
        }
        else{
            return list;
        }
    }

    public List<PrintMarketingInfoVo> printMarketingInfo() throws NotFoundMarketingInfoListException {
        List<PrintMarketingInfoVo> list = jdbcTemplate.query(
                SQL.Manage.PRINT_MARKETING_LIST,
                (resultSet, i) -> {
                    PrintMarketingInfoVo vo = new PrintMarketingInfoVo(resultSet.getString("device_token"));
                    return vo;
                });
        if(list.size() == 0) {
            throw new NotFoundMarketingInfoListException();
        }
        else{
            return list;
        }
    }

    public List<PayBackListVo> payBackMoney(String date,int discount_rate) throws PayBackMoneyListNotFoundException {
        List<PayBackListVo> list = jdbcTemplate.query(
                SQL.Manage.GET_PAYBACK_AMOUNT,
                (resultset, i) -> {
                    PayBackListVo vo = new PayBackListVo(resultset.getInt("store_id"),resultset.getString("store_name"),resultset.getInt("payBackSum"));
                    return vo;
                },discount_rate,discount_rate,date,date,discount_rate,discount_rate,date,date);
        if (list.size() == 0) {
            throw new PayBackMoneyListNotFoundException();
        }else{
            return list;
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
    public boolean isIdInUse(String id) {
        List<OwnerVo> list = jdbcTemplate.query(
                SQL.Owner.CHECK_ID,
                (resultSet, i) -> new OwnerVo()
                , id);
        if(list.size() == 0) {
            return false;
        } else {
            return true;
        }
    }
    public void onwerRegister(FxOwnerRegisterRequestDto requestDto) {
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.Manage.FX_OWNER_REGISTER);
            preparedStatement.setString(1, requestDto.getId());
            preparedStatement.setString(2, requestDto.getPhone());
            preparedStatement.setString(3, requestDto.getEmail());
            preparedStatement.setString(4, requestDto.getPass());
            return preparedStatement;
        });
    }


    public int getLastInsertId() {
        int store_id = jdbcTemplate.queryForObject(SQL.Manage.SELECT_LAST_INSERT_STORE_ID,Integer.class);
        return store_id;
    }

    public void updateStoreImage(int store_id) {
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.Manage.UPDATE_STORE_IMAGE);
            preparedStatement.setString(1,store_id+"png");
            preparedStatement.setInt(2, store_id);
            return preparedStatement;
        });
    }
}

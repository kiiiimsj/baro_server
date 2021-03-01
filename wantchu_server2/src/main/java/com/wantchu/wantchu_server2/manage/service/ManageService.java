package com.wantchu.wantchu_server2.manage.service;

import com.google.firebase.database.annotations.NotNull;
import com.wantchu.wantchu_server2.business.ImageReceivers.FileTransferServer;
import com.wantchu.wantchu_server2.business.ObjectMaker;
import com.wantchu.wantchu_server2.dao.ManageDao;
import com.wantchu.wantchu_server2.fcmtest.FcmUtil;
import com.wantchu.wantchu_server2.manage.dto.*;
import com.wantchu.wantchu_server2.manage.exception.NotFoundRequiredExtrasException;
import com.wantchu.wantchu_server2.vo.*;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.BindException;
import java.util.List;

@RequiredArgsConstructor
@Component
@Service
public class ManageService {
    private final ManageDao manageDao;
    private static final int DISCOUNT_RATE = 10;
    //시작

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject typeInsert(@NotNull TypeInsertDto requestDto) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            manageDao.insertType(requestDto);
            jsonObject.put("result", true);
            jsonObject.put("message", "정상적으로 type등록이 완료되었습니다.");
        }
        catch(Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject typeDelete(String type_code) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            manageDao.deleteType(type_code);
            jsonObject.put("result", true);
            jsonObject.put("message", "해당 type을 정상적으로 제거하였습니다.");
        }
        catch(Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject typePrint() {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            List<PrintTypeVo> list = manageDao.printType();
            jsonObject.put("result", true);
            jsonObject.put("message", "성공적으로 type list 출력");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(PrintTypeVo type : list) {
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(type.convertMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("type", jsonArray);
        }
        catch(Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject insertUltra(@NotNull UltraInsertDto requestDto) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            manageDao.insertUltra(requestDto);
            jsonObject.put("result", true);
            jsonObject.put("message", "정상적으로 ultra 등록이 완료되었습니다.");
        }
        catch (Exception e) {
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject ultraDelete(int store_id) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            manageDao.deleteUltra(store_id);
            jsonObject.put("result", true);
            jsonObject.put("message", "해당 ultra를 정상적으로 제거하였습니다.");
        }
        catch (Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject ultraPrint() {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            List<PrintUltraVo> list = manageDao.printUltra();
            jsonObject.put("result", true);
            jsonObject.put("message", "성공적으로 ultra list 출력");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(PrintUltraVo ultra : list) {
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(ultra.convertMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("ultra", jsonArray);
        }
        catch(Exception e) {
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;

    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject insertNewStore(@NotNull NewStoreInsertDto requestDto) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            manageDao.insertNewStore(requestDto);
            jsonObject.put("result", true);
            jsonObject.put("message", "정상적으로 newstore 등록이 완료되었습니다.");
        }
        catch(Exception e) {
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject newStoreDelete(int store_id){
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            manageDao.deleteNewStore(store_id);
            jsonObject.put("result", true);
            jsonObject.put("message", "해당 newstore를 정상적으로 제거하였습니다.");
        }
        catch (Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject newStorePrint() {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            List<PrintNewStoreVo> list = manageDao.printNewStore();
            jsonObject.put("result", true);
            jsonObject.put("message", "성공적으로 newstore 출력 성공");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(PrintNewStoreVo newStore : list) {
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(newStore.convertMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("newStore", jsonArray);
        }
        catch (Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject insertAlert(@NotNull AlertInsertDto requestDto) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            manageDao.insertAlert(requestDto);
            jsonObject.put("result", true);
            jsonObject.put("message", "정상적으로 alert 등록이 완료되었습니다.");
        }
        catch(Exception e) {
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject alertDelete(int alert_id) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            manageDao.deleteAlert(alert_id);
            jsonObject.put("result", true);
            jsonObject.put("message", "해당 alert를 정상적으로 제거하였습니다.");
        }
        catch (Exception e) {
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject alertPrint(){
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            List<PrintAlertVo> list = manageDao.printAlert();
            jsonObject.put("result", true);
            jsonObject.put("message", "성공적으로 alert list 출력");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(PrintAlertVo alert : list) {
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(alert.convertMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("alert", jsonArray);
        }
        catch(Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject storeInsert(@NotNull StoreInsertDto requestDto) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            manageDao.insertStore(requestDto);
            jsonObject.put("result", true);
            jsonObject.put("message", "정상적으로 store등록이 완료되었습니다.");
        }
        catch (Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject storeDelete(int store_id) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            manageDao.deleteStore(store_id);
            jsonObject.put("result", true);
            jsonObject.put("message", "해당 store을 정상적으로 제거하였습니다.");
        }
        catch (Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject storePrintByStoreName(String store_name) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            List<PrintStoreVo> list = manageDao.printStore(store_name);
            jsonObject.put("result", true);
            jsonObject.put("message", "성공적으로 store list 출력");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(PrintStoreVo store : list) {
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(store.convertMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("store", jsonArray);
        }
        catch (Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject categoryInsert(@NotNull CategoryInsertDto requestDto) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            manageDao.insertCategory(requestDto);
            jsonObject.put("result", true);
            jsonObject.put("message", "정상적으로 category등록이 완료되었습니다.");
        }
        catch (Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject categoryDelete(int category_id) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            manageDao.deleteCategory(category_id);
            jsonObject.put("result", true);
            jsonObject.put("message", "정상적으로 해당 category를 삭제하였습니다.");
        }
        catch (Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject categoryPrintByStoreId(int store_id) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            List<PrintCategoryVo> list = manageDao.printCategory(store_id);
            jsonObject.put("result", true);
            jsonObject.put("message", "성공적으로 category list 출력");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(PrintCategoryVo category : list) {
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(category.convertMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("category", jsonArray);
        }
        catch (Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject menuInsert(@NotNull MenuInsertDto requestDto) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            manageDao.insertMenu(requestDto);
            jsonObject.put("result", true);
            jsonObject.put("message", "정상적으로 menu등록이 완료되었습니다.");
        }
        catch (Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject menuDelete(int menu_id) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            manageDao.deleteMenu(menu_id);
            jsonObject.put("result", true);
            jsonObject.put("message", "해당 menu를 정상적으로 제거하였습니다.");
        }
        catch (Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject menuPrintByCategoryId(int category_id) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            List<PrintMenuVo> list = manageDao.printMenu(category_id);
            jsonObject.put("result", true);
            jsonObject.put("message", "성공적으로 menu list 출력");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(PrintMenuVo menu : list) {
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(menu.convertMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("menu", jsonArray);
        }
        catch (Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject eventInsert(@NotNull EventInsertDto requestDto) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            manageDao.insertEvent(requestDto);
            jsonObject.put("result", true);
            jsonObject.put("message", "이벤트 추가에 성공했습니다");
        }
        catch (Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject eventDelete(int event_id) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            manageDao.deleteEvent(event_id);
            jsonObject.put("result", true);
            jsonObject.put("message", "해당 event를 정상적으로 제거하였습니다.");
        }
        catch (Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject eventPrint() {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            List<PrintEventVo> list = manageDao.printEvent();
            jsonObject.put("result", true);
            jsonObject.put("message", "성공적으로 event list 출력");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(PrintEventVo event : list) {
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(event.convertMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("event", jsonArray);
        }
        catch (Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject couponInsert(@NotNull CouponInsertDto requestDto) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            manageDao.insertCoupon(requestDto);
            jsonObject.put("result", true);
            jsonObject.put("message", "정상적으로 coupon 등록이 완료되었습니다.");
        }
        catch (Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject couponDelete(int coupon_id) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            manageDao.deleteCoupon(coupon_id);
            jsonObject.put("result", true);
            jsonObject.put("message", "해당 coupon을 정상적으로 제거하였습니다.");
        }
        catch (Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public org.json.simple.JSONObject couponPrint() {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            List<PrintCouponVo> list = manageDao.printCoupon();
            jsonObject.put("result", true);
            jsonObject.put("message", "성공적으로 coupon list 출력");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(PrintCouponVo coupon : list) {
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(coupon.convertMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("coupon", jsonArray);
        }
        catch (Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    public JSONObject findOwnerByPhone(String ownerPhone) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            String phone = manageDao.findOwner(ownerPhone);
            jsonObject.put("owner_id",phone);
            jsonObject.put("result", true);
            jsonObject.put("message", "해당 번호를 가진 점주가 존재합니다.");
        }
        catch (Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    public JSONObject extraInsert(@NotNull ExtraInsertDto requestDto) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            manageDao.insertExtra(requestDto);
            if (new Boolean(requestDto.getIs_required())){
                int id = manageDao.getLastInsertRequiredExtra();
                manageDao.insertRequiredExtra(requestDto,id);
            }
            jsonObject.put("result", true);
            jsonObject.put("message", "정상적으로 extra 등록이 완료되었습니다.");
        }
        catch (Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    public JSONObject extraDelete(int extra_id) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            manageDao.deleteExtra(extra_id);
            jsonObject.put("result", true);
            jsonObject.put("message", "해당 extra를 정상적으로 제거하였습니다.");
        }
        catch (Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    public JSONObject extraPrint(int store_id) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            List<PrintExtraVo> list = manageDao.printExtra(store_id);
            jsonObject.put("result", true);
            jsonObject.put("message", "성공적으로 extra list 출력");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(PrintExtraVo extra : list) {
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(extra.convertMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("extras", jsonArray);
        }
        catch (Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    public JSONObject insertExtraByMenu(ExtraByMenuInsertDto request) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            manageDao.insertExtraByMenu(request);
            jsonObject.put("result", true);
            jsonObject.put("message", "정상적으로 extraByMenus 등록이 완료되었습니다.");
        }
        catch (Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    public JSONObject deleteExtraByMenu(int id) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            manageDao.deleteExtraByMenu(id);
            jsonObject.put("result", true);
            jsonObject.put("message", "해당 extraByMenus 를 정상적으로 제거하였습니다.");
        }
        catch (Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    public JSONObject extraByMenuPrint(int menu_id) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            List<PrintExtraByMenuVo> list = manageDao.extraByMenuPrint(menu_id);
            jsonObject.put("result", true);
            jsonObject.put("message", "성공적으로 ExtraByMenu list 출력");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(PrintExtraByMenuVo data : list) {
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(data.convertMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("extraByMenus", jsonArray);
        }
        catch (Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    public JSONObject requiredExtrasPrint(int store_id) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            List<PrintRequiredExtrasVo> list = manageDao.requiredExtrasPrint(store_id);
            jsonObject.put("result", true);
            jsonObject.put("message", "성공적으로 RequiredExtra list 출력");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(PrintRequiredExtrasVo data : list) {
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(data.convertMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("requiredExtras", jsonArray);
        } catch (Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    public JSONObject requiredExtrasDelete(int extra_id) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try{
            manageDao.requiredExtrasDelete(extra_id);
            jsonObject.put("result", true);
            jsonObject.put("message", "해당 extraByMenus 를 정상적으로 제거하였습니다.");
        }
        catch (Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    public JSONObject findOrderListByPhoneForManage(String phone) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            List<FindOrderListByPhoneForManageVo> list = manageDao.findOrderList(phone);
            jsonObject.put("result", true);
            jsonObject.put("message", "성공적으로 order list 출력");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(FindOrderListByPhoneForManageVo data : list) {
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(data.convertMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("requiredExtras", jsonArray);
        } catch (Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    public JSONObject printMarketingInfo(MarketingDto request) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            List<PrintMarketingInfoVo> list = manageDao.printMarketingInfo();
            jsonObject.put("result", true);
            jsonObject.put("message", "성공적으로 marketingInfoList 출력");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(PrintMarketingInfoVo data : list) {
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(data.convertMap());
                jsonArray.add(jTemp);
            }
            FcmUtil fcmUtil = new FcmUtil();
            fcmUtil.send_FCM_Marketing(list,request.getTitle(),request.getContent(),jsonObject);

            jsonObject.put("marketingInfos", jsonArray);
        } catch (Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    public JSONObject payBackMoney(String date) {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            List<PayBackListVo> payBackSumList = manageDao.payBackMoney(date,DISCOUNT_RATE);
            jsonObject.put("result", true);
            jsonObject.put("message", "성공적으로 정산액 출력");
            org.json.simple.JSONArray jsonArray = ObjectMaker.getSimpleJSONArray();
            for(PayBackListVo data : payBackSumList) {
                org.json.simple.JSONObject jTemp = ObjectMaker.getSimpleJSONObject();
                jTemp.putAll(data.convertMap());
                jsonArray.add(jTemp);
            }
            jsonObject.put("payBackMoneyList", jsonArray);
        } catch (Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }

    public JSONObject pictureTest() {
        org.json.simple.JSONObject jsonObject = ObjectMaker.getSimpleJSONObject();
        try {
            jsonObject.put("result", true);
            jsonObject.put("message", "성공적으로 marketingInfoList 출력");
            FileTransferServer server = null;
            try{
                server = new FileTransferServer(9999,new File("/types"));
            }catch (BindException e){
                server = new FileTransferServer(9999,new File("/types"));
            }
            catch(Exception e){
                e.printStackTrace();
            }
        } catch (Exception e){
            jsonObject = ObjectMaker.getJSONObjectWithException(e);
        }
        return jsonObject;
    }
}

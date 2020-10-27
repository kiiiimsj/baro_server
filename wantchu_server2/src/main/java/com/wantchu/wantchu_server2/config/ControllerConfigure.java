package com.wantchu.wantchu_server2.config;


import com.wantchu.wantchu_server2.alert.service.AlertService;
import com.wantchu.wantchu_server2.category.service.CategoryService;
import com.wantchu.wantchu_server2.coupon.service.CouponService;
import com.wantchu.wantchu_server2.dao.*;
import com.wantchu.wantchu_server2.event.service.EventService;
import com.wantchu.wantchu_server2.extra.service.ExtraService;
import com.wantchu.wantchu_server2.favorite.service.FavoriteService;
import com.wantchu.wantchu_server2.member.service.MemberService;
import com.wantchu.wantchu_server2.menu.service.MenuService;
import com.wantchu.wantchu_server2.notice.service.NoticeService;
import com.wantchu.wantchu_server2.order.service.OrderService;
import com.wantchu.wantchu_server2.owner.service.OwnerService;
import com.wantchu.wantchu_server2.store.service.StoreService;
import com.wantchu.wantchu_server2.type.service.TypeService;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfigure {

    @Autowired
    private MemberService memberService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private ExtraService extraService;
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OwnerService ownerService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private AlertService alertService;
    @Autowired
    private EventService eventService;


    @Autowired
    private MemberDao memberDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private CouponDao couponDao;
    @Autowired
    private ExtraDao extraDao;
    @Autowired
    private FavoriteDao favoriteDao;
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private NoticeDao noticeDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OwnerDao ownerDao;
    @Autowired
    private StoreDao storeDao;
    @Autowired
    private TypeDao typeDao;
    @Autowired
    private AlertDao alertDao;
    @Autowired
    private EventDao eventDao;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:log4jdbc:mysql://baro.c3ptb78nhjkk.ap-northeast-2.rds.amazonaws.com:3306/baro?autoReconnection=true");
        dataSource.setUsername("root");

        dataSource.setPassword("qwerasdf12");
        dataSource.setInitialSize(2);
        dataSource.setMaxIdle(10);
        dataSource.setMaxActive(10);
        return dataSource;
    }

    @Bean
    public MemberDao memberDao() {
        return new MemberDao(dataSource());
    }
    @Bean
    public CategoryDao categoryDao(){ return new CategoryDao(dataSource());}
    @Bean
    public CouponDao couponDao(){ return new CouponDao(dataSource());}
    @Bean
    public ExtraDao extraDao(){ return new ExtraDao(dataSource());}
    @Bean
    public FavoriteDao favoriteDao(){ return new FavoriteDao(dataSource());}
    @Bean
    public MenuDao menuDao(){ return new MenuDao(dataSource());}
    @Bean
    public NoticeDao noticeDao(){ return new NoticeDao(dataSource());}
    @Bean
    public OrderDao orderDao(){ return new OrderDao(dataSource());}
    @Bean
    public OwnerDao ownerDao(){ return new OwnerDao(dataSource());}
    @Bean
    public StoreDao storeDao(){ return new StoreDao(dataSource());}
    @Bean
    public TypeDao typeDao(){ return new TypeDao(dataSource());}
    @Bean
    public AlertDao alertDao(){ return new AlertDao(dataSource());}
    @Bean
    public EventDao eventDao(){ return new EventDao(dataSource());}


    @Bean
    public MemberService memberService() {
        MemberService service = new MemberService(memberDao());
        return service;
    }
    @Bean
    public CategoryService categoryService(){
        CategoryService service = new CategoryService(categoryDao());
        return service;
    }
    @Bean
    public CouponService couponService(){
        CouponService service = new CouponService(couponDao());
        return service;
    }
    @Bean
    public ExtraService extraService(){
        ExtraService service = new ExtraService(extraDao());
        return service;
    }
    @Bean
    public FavoriteService favoriteService(){
        FavoriteService service = new FavoriteService(favoriteDao());
        return service;
    }
    @Bean
    public MenuService menuService(){
        MenuService service = new MenuService(menuDao());
        return service;
    }
    @Bean
    public NoticeService noticeService(){
        NoticeService service = new NoticeService(noticeDao());
        return service;
    }
    @Bean
    public OrderService orderService(){
        OrderService service = new OrderService(orderDao());
        return service;
    }
    @Bean
    public OwnerService ownerService(){
        OwnerService service = new OwnerService(ownerDao());
        return service;
    }
    @Bean
    public StoreService storeService(){
        StoreService service = new StoreService(storeDao());
        return service;
    }
    @Bean
    public TypeService typeService(){
        TypeService service = new TypeService(typeDao());
        return service;
    }
    @Bean
    public AlertService alertService(){
        AlertService service = new AlertService(alertDao());
        return service;
    }
    @Bean
    public EventService eventService(){
        EventService service = new EventService(eventDao());
        return service;
    }

//    @Bean
//    public MemberApiController memberApiController() {
//        MemberApiController controller = new MemberApiController();
//        controller.setMemberService(memberService);
//        return controller;
//    }
}
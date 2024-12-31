create table `warmwind-master`.daily_finance
(
    id              int auto_increment comment '编号'
        primary key,
    daily_record_id int                                 not null comment '日常记录表编号',
    name            varchar(64)                         null comment '收支名称',
    account         decimal                             null comment '收支金额',
    type            int                                 null comment '收支类型',
    is_necessary    int       default 1                 not null comment '是否必要（0，否，1，是）',
    remarks         varchar(255)                        null comment '备注',
    create_time     timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time     timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted         int       default 0                 not null comment '删除标记'
)
    comment '日常收支表';

create table `warmwind-master`.daily_plan
(
    id            int auto_increment comment '编号'
        primary key,
    name          varchar(64)                         null comment '计划名',
    type          int                                 null comment '计划类型',
    is_mark       int       default 1                 not null comment '是否标记(0， 否；1，是)',
    is_add_push   int                                 null comment '是否需要推送计划',
    is_complete   int                                 null comment '是否完成',
    complete_time timestamp                           null comment '完成时间',
    remarks       varchar(255)                        null comment '备注',
    create_time   timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time   timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted       int       default 0                 null comment '删除标记'
)
    comment '日常计划表';

create table `warmwind-master`.daily_record
(
    id          int auto_increment comment '编号'
        primary key,
    mood        int                                 null comment '心情',
    weather     int                                 null comment '天气',
    evaluate    varchar(255)                        null comment '对今天的评价',
    diary       varchar(255)                        null comment '日记',
    is_running  int                                 null comment '是否运动',
    is_reading  int                                 null comment '是否阅读',
    is_learing  int                                 null comment '是否学习',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     int       default 0                 not null comment '删除标记'
)
    comment '日常记录表';

create table `warmwind-master`.restaurant_food
(
    id             int auto_increment comment '主键'
        primary key,
    name           varchar(50)                          not null comment '食品名称',
    img            varchar(255)                         null comment '图片地址',
    price          decimal(8, 2)                        not null comment '价格',
    unit           varchar(10)                          not null comment '单位',
    is_recommended tinyint(1) default 0                 null comment '是否推荐',
    is_new         tinyint(1)                           null comment '是否新品',
    tag            varchar(255)                         null comment '标签',
    introduce      varchar(255)                         null comment '介绍',
    create_time    timestamp  default CURRENT_TIMESTAMP not null comment '创建时间',
    create_by      int                                  null comment '创建人',
    update_time    timestamp  default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新人',
    update_by      int                                  null comment '更新人',
    deleted        tinyint(1) default 0                 not null comment '删除标记',
    tenant_id      int                                  not null comment '租户id'
)
    comment '点餐-食物';

create table `warmwind-master`.restaurant_food_type
(
    id          int auto_increment comment '主键'
        primary key,
    name        varchar(50)                          null comment '类型名称',
    sort        int                                  null comment '排序列',
    icon        varchar(255)                         null comment '图标地址',
    create_time timestamp  default CURRENT_TIMESTAMP not null comment '创建时间',
    create_by   int                                  null comment '创建人',
    update_time timestamp  default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    update_by   int                                  null comment '更新人',
    deleted     tinyint(1) default 0                 not null comment '删除标记',
    tenant_id   int                                  not null comment '租户id'
)
    comment '点餐-食品类型表';

create table `warmwind-master`.restaurant_order
(
    id           int auto_increment comment '主键'
        primary key,
    status       int        default 0                 not null comment '订单状态',
    total_amount decimal(8, 2)                        not null comment '订单总金额',
    create_time  timestamp  default CURRENT_TIMESTAMP not null comment '创建时间',
    create_by    int                                  null comment '创建人',
    update_time  timestamp  default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新人',
    update_by    int                                  null comment '更新人',
    deleted      tinyint(1) default 0                 not null comment '删除标记',
    tenant_id    int                                  not null comment '租户id'
)
    comment '点餐-订单表';

create table `warmwind-master`.restaurant_order_evaluate
(
    id                 int auto_increment comment '主键'
        primary key,
    restauran_order_id int                                  null comment '点餐订单编号',
    content            varchar(255)                         null comment '评价内容',
    dining_rate        int                                  null comment '用餐评价',
    create_time        timestamp  default CURRENT_TIMESTAMP not null comment '创建时间',
    create_by          int                                  null comment '创建人',
    update_time        timestamp  default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新人',
    update_by          int                                  null comment '更新人',
    deleted            tinyint(1) default 0                 not null comment '删除标记',
    tenant_id          int                                  not null comment '租户id'
)
    comment '点餐-订单评价表';

create table `warmwind-master`.restaurant_order_food
(
    id                  int auto_increment comment '主键'
        primary key,
    restaurant_food_id  int null comment '点餐食品编号',
    restaurant_order_id int not null comment '点餐订单编号'
)
    comment '点餐-订单商品关联表';

create table `warmwind-master`.sys_login_record
(
    id          int auto_increment comment '主键编号'
        primary key,
    username    varchar(64)                         not null comment '用户名',
    os          varchar(200)                        null comment '操作系统',
    device      varchar(200)                        null comment '设备名称',
    browser     varchar(200)                        null comment '浏览器类型',
    ip          varchar(200)                        null comment 'ip地址',
    login_type  tinyint                             null comment '操作类型, 0登录成功, 1登录失败, 2退出登录, 3续签token',
    comments    varchar(200)                        null comment '备注',
    create_by   int                                 null comment '创建人',
    create_time timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    update_by   int                                 null comment '更新人',
    update_time timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_deleted  tinyint   default 0                 not null comment '删除标记 0否 1是'
)
    comment '系统登录日志表';

create table `warmwind-master`.sys_menu
(
    id          int auto_increment comment '主键编号 '
        primary key,
    parent_id   int                                 not null comment '父级id，0是顶级',
    title       varchar(64)                         null comment '菜单名称',
    path        varchar(200)                        null comment '菜单路由地址',
    component   varchar(200)                        null comment '菜单组件地址, 目录可为空',
    menu_type   int                                 null comment '类型, 0菜单, 1按钮',
    sort_number int                                 null comment '排序列',
    authority   varchar(200)                        null comment '权限标识',
    target      varchar(64)                         null comment '打开位置',
    icon        varchar(200)                        null comment '菜单图标',
    color       varchar(64)                         null comment '图标颜色',
    hide        int                                 null comment '是否隐藏, 0否, 1是(仅注册路由不显示在左侧菜单)',
    active      varchar(200)                        null comment '菜单侧栏选中的path',
    meta        varchar(200)                        null comment '路由元信息',
    create_by   int                                 not null comment '创建人',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by   int                                 null comment '更新人',
    update_time timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_deleted  int       default 0                 not null comment '是否删除'
)
    comment '系统菜单表';

create table `warmwind-master`.sys_operation_record
(
    id                  int auto_increment comment '主键编号'
        primary key,
    user_id             int                       null comment '用户id',
    module              varchar(64)               null comment '操作模块',
    description         varchar(200)              null comment '操作功能',
    request_url         varchar(200)              null comment '请求url',
    request_method_type varchar(200)              null comment '请求方法类型',
    calling_method      varchar(200)              null comment '调用方法',
    request_params      varchar(1000)             null comment '请求参数',
    result              varchar(1000)             null comment '响应结果',
    error               varchar(1000)             null comment '异常信息',
    comments            varchar(255)              null comment '备注',
    spend_time          mediumtext                null comment '消耗时间, 单位毫秒',
    os                  varchar(255)              null comment '操作系统',
    device              varchar(255)              null comment '设备名称',
    browser             varchar(255)              null comment '浏览器类型',
    ip                  varchar(255)              null comment 'ip地址',
    status              int       default 0       null comment '状态, 0成功, 1异常',
    create_time         timestamp default (now()) null comment '创建时间'
)
    comment '系统操作日志';

create table `warmwind-master`.sys_role
(
    id          int auto_increment comment '角色编号'
        primary key,
    role_code   varchar(32)                         not null comment '角色代码',
    role_name   varchar(32)                         not null comment '角色名称',
    comments    varchar(200)                        null comment '备注',
    create_by   int                                 null comment '创建人',
    create_time timestamp default CURRENT_TIMESTAMP not null,
    update_by   int                                 null comment '更新人',
    update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     tinyint   default 0                 not null comment '删除标记'
)
    comment '系统角色';

create table `warmwind-master`.sys_role_menu
(
    id      int auto_increment comment '主键编号'
        primary key,
    role_id int not null comment '系统角色编号',
    menu_id int not null comment '系统菜单编号',
    constraint sys_role_menu_sys_menu_id_fk
        foreign key (menu_id) references `warmwind-master`.sys_menu (id)
            on delete cascade,
    constraint sys_role_menu_sys_role_id_fk
        foreign key (role_id) references `warmwind-master`.sys_role (id)
            on delete cascade
)
    comment '系统角色菜单表';

create table `warmwind-master`.sys_user
(
    id                  int auto_increment comment '用户编号'
        primary key,
    username            varchar(64)                         not null comment '用户名',
    nickename           varchar(64)                         null comment '昵称',
    password            varchar(255)                        not null comment '密码',
    avatar              varchar(255)                        null comment '头像',
    sex                 tinyint                             null comment '性别',
    phone               varchar(32)                         null comment '手机号',
    email               varchar(64)                         null comment '邮箱',
    email_verify_status tinyint                             null comment '邮箱验证状态',
    account_status      tinyint                             null comment '用户账号状态',
    create_by           int                                 null comment '创建人',
    create_time         timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by           int                                 null comment '更新人',
    update_time         timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted             tinyint   default 0                 not null comment '删除标记'
)
    comment '系统用户';

create table `warmwind-master`.sys_user_role
(
    id      int auto_increment comment '主键编号'
        primary key,
    user_id int not null comment '用户id',
    role_id int not null comment '角色主键编号',
    constraint sys_user_role_sys_role_id_fk
        foreign key (role_id) references `warmwind-master`.sys_role (id),
    constraint sys_user_role_sys_user_id_fk
        foreign key (user_id) references `warmwind-master`.sys_user (id)
)
    comment '用户角色表';


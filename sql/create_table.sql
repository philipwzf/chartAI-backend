# 数据库初始化
# @author <a href="https://github.com/liyupi">程序员鱼皮</a>
# @from <a href="https://yupi.icu">编程导航知识星球</a>

-- Create DB
create database if not exists chartAI;

-- Switch DB
use chartAI;

-- User Table
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    userAccount  varchar(256)                           not null comment 'Account',
    userPassword varchar(512)                           not null comment 'Password',
    userName     varchar(256)                           null comment 'Username',
    userAvatar   varchar(1024)                          null comment 'User Avatar',
    userRole     varchar(256) default 'user'            not null comment 'User Role：user/admin/ban',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment 'Create Time',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment 'Update Time',
    isDelete     tinyint      default 0                 not null comment 'Is deleted',
    index idx_userAccount (userAccount)
) comment 'User' collate = utf8mb4_unicode_ci;

-- chart table
create table if not exists chart
(
    id         bigint auto_increment comment 'id' primary key,
    goal       text                               null comment 'Goal for analysis',
    `name`       varchar(128)                       null comment 'Chart Name',
    chartData  text                               null comment 'Data for chart',
    chartType  varchar(128)                       null comment 'Chart Type',
    genChart   text                               null comment 'Goal for analysis',
    genResult  text                               null comment 'Data for chart',
    userId     bigint                             not null comment 'User id',
    createTime datetime default CURRENT_TIMESTAMP not null comment 'Create Time',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment 'Update Time',
    isDelete   tinyint  default 0                 not null comment 'Is Delete'
) comment 'Chart Table' collate = utf8mb4_unicode_ci;


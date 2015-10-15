CREATE TABLE `t_visit` (  `id` varchar(37) NOT NULL COMMENT 'ID主键',  `idsite` int UNSIGNED NOT NULL COMMENT '网站ID',  `idsubsite` varchar(37) NOT NULL DEFAULT '' COMMENT '子站ID',  `create_time` datetime NOT NULL COMMENT '创建时间',  `update_time` datetime NOT NULL COMMENT '更新时间',  `new_user` tinyint(1) NOT NULL COMMENT '是否老用户',  `new_sub_user` tinyint(1) NOT NULL COMMENT '是否子站新用户',  `idvisitor` varchar(37) NOT NULL COMMENT '访问者ID',  `visit_servertime` datetime NOT NULL COMMENT '访问服务器时间',  `visit_localtime` datetime NOT NULL COMMENT '访问者本地时间',  `visit_totaltime` integer NOT NULL COMMENT '访问者停留时间',  `idurl` varchar(37) NULL COMMENT '当前urlID',  `idtitle` varchar(37) NULL COMMENT '页面标题ID',  `idurl_entry` varchar(37) NOT NULL COMMENT '进入页url ID',  `idtitle_entry` varchar(37) NOT NULL COMMENT '进入页标题ID',  `idurl_exit` varchar(37) NOT NULL COMMENT '退出页链接ID',  `idtitle_exit` varchar(37) NOT NULL COMMENT '退出页标题ID',  `url_referer` varchar(256) NULL COMMENT '上一页面url',  `useragent` varchar(256) NULL COMMENT '用户代理',  `operate_system` varchar(10) NULL COMMENT '操作系统',  `operate_version` varchar(20) NULL COMMENT '操作系统版本',  `browser_name` varchar(10) NULL COMMENT '浏览器名称',  `browser_version` varchar(20) NULL COMMENT '浏览器版本',  `app_name` varchar(20) NULL COMMENT '打开页面的App',  `screen_resolution` varchar(15) NULL COMMENT '屏幕分辨率',  `screen_depth` varchar(3) NULL COMMENT '颜色深度',  `location_ip` varchar(16) NULL COMMENT '外网IP地址',  `location_lang` varchar(10) NULL COMMENT '浏览器语言',  `location_country` varchar(10) NULL COMMENT '国家',  `location_region` varchar(10) NULL COMMENT '区域',  `location_city` varchar(10) NULL COMMENT '城市',  `end_model` varchar(32) NULL COMMENT '终端设备型号',  `end_brand` varchar(10) NULL COMMENT '终端品牌',  `end_type` varchar(10) NULL COMMENT '终端类型（PC or mobile）',  `net_type` varchar(10) NULL COMMENT '网络类型（移动数据，WIFI）',  `count_visits` int NOT NULL COMMENT '这次访问的PV总量',  `count_events` int NULL COMMENT '这次访问的事件总数',  PRIMARY KEY (`id`));CREATE TABLE `t_site` (  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID主键',  `create_time` datetime NOT NULL COMMENT '创建时间',  `update_time` datetime NOT NULL COMMENT '更新时间',  `name` varchar(32) NULL COMMENT '网站备注名称',  `domain` varchar(64) NULL COMMENT '网站域名',  `regex` varchar(128) NULL COMMENT '子站正则表达式',  PRIMARY KEY (`id`));CREATE TABLE `t_url` (  `id` varchar(37) NOT NULL COMMENT 'ID主键',  `idsite` int UNSIGNED NOT NULL COMMENT '网站ID',  `idsubsite` varchar(37) NOT NULL DEFAULT '' COMMENT '子站ID',  `create_time` datetime NOT NULL COMMENT '创建时间',  `update_time` datetime NOT NULL COMMENT '更新时间',  `hash` int(10) NOT NULL COMMENT '哈希值',  `url` varchar(256) NOT NULL COMMENT 'url 值',  PRIMARY KEY (`id`));CREATE TABLE `t_title` (  `id` varchar(37) NOT NULL COMMENT 'ID主键',  `idsite` int UNSIGNED NOT NULL COMMENT '网站ID',  `idsubsite` varchar(37) NOT NULL DEFAULT '' COMMENT '子站ID',  `create_time` datetime NOT NULL COMMENT '创建时间',  `update_time` datetime NOT NULL COMMENT '更新时间',  `hash` int(10) NOT NULL COMMENT '哈希值',  `title` varchar(256) NOT NULL COMMENT '标题',  PRIMARY KEY (`id`));CREATE TABLE `t_event` (  `id` varchar(37) NOT NULL COMMENT 'ID主键',  `idsite` int UNSIGNED NOT NULL COMMENT '网站ID',  `idsubsite` varchar(37) NOT NULL DEFAULT '' COMMENT '子站ID',  `idvisitor` varchar(37) NOT NULL COMMENT '访问者ID',  `local_time` datetime NOT NULL COMMENT '本地时间',  `create_time` datetime NOT NULL COMMENT '创建时间',  `update_time` datetime NOT NULL COMMENT '更新时间',  `category` varchar(64) NOT NULL COMMENT '事件分类',  `action` varchar(64) NOT NULL COMMENT '动作',  `name` varchar(64) NULL COMMENT '事件名称',  `value` float NULL COMMENT '值',  PRIMARY KEY (`id`));CREATE TABLE `t_action` (  `id` varchar(37) NOT NULL COMMENT 'ID主键',  `idsite` int UNSIGNED NOT NULL COMMENT '网站ID',  `idsubsite` varchar(37) NOT NULL DEFAULT '' COMMENT '子站ID',  `create_time` datetime NOT NULL COMMENT '创建时间',  `update_time` datetime NOT NULL COMMENT '更新时间',  `idvisitor` varchar(37) NOT NULL COMMENT '访客用户ID',  `idvisit` varchar(37) NOT NULL COMMENT '关联Visit ID',  `idtitle` varchar(37) NOT NULL COMMENT '标题ID',  `idurl` varchar(37) NOT NULL COMMENT 'urlID',  `server_time` datetime NOT NULL COMMENT '服务器时间',  `time_spent` integer UNSIGNED NOT NULL COMMENT '页面加载时间',  PRIMARY KEY (`id`));CREATE TABLE `t_subsite` (  `id` varchar(37) NOT NULL COMMENT 'ID主键',  `idsite` int UNSIGNED NOT NULL COMMENT '网站ID',  `name` varchar(64) NOT NULL COMMENT '子站名称标识',  `remark` varchar(64) NOT NULL DEFAULT '' COMMENT '备注',  `create_time` datetime NOT NULL COMMENT '创建时间',  `update_time` datetime NOT NULL COMMENT '更新时间',  PRIMARY KEY (`id`));CREATE TABLE `t_share_user` (  `id` varchar(37) NOT NULL COMMENT 'ID主键',  `idsite` int UNSIGNED NOT NULL COMMENT '网站ID',  `idsubsite` varchar(37) NOT NULL DEFAULT '' COMMENT '子站ID',  `idvisitor` varchar(37) NOT NULL COMMENT '访问者ID（cookie）',  `openid` varchar(37) NULL COMMENT '微信用户openid',  `unionid` varchar(37) NULL COMMENT '微信用户唯一ID',  `nickname` varchar(64) NULL COMMENT '微信用户昵称',  `headimgurl` varchar(256) NULL COMMENT '微信用户头像链接',  `sex` varchar(10) NULL COMMENT '微信用户性别',  `province` varchar(32) NULL COMMENT '微信用户省份',  `city` varchar(32) NULL COMMENT '微信用户城市',  `country` varchar(32) NULL COMMENT '微信用户国家',  `privilege` varchar(32) NULL COMMENT '微信用户特权',  `create_time` datetime NOT NULL COMMENT '创建时间',  `update_time` datetime NOT NULL COMMENT '更新时间',  PRIMARY KEY (`id`) ,  UNIQUE INDEX `idvisitor` (`idvisitor`));CREATE TABLE `t_share_point` (  `id` varchar(37) NOT NULL COMMENT 'ID主键',  `idsite` int UNSIGNED NOT NULL COMMENT '网站ID',  `idsubsite` varchar(37) NOT NULL DEFAULT '' COMMENT '子站ID',  `idvisitor` varchar(37) NOT NULL COMMENT '访问者ID',  `idurl` varchar(37) NOT NULL COMMENT '页面ID',  `idrefer` varchar(37) NOT NULL COMMENT '上个一点ID',  `create_time` datetime NOT NULL COMMENT '创建时间',  `update_time` datetime NOT NULL COMMENT '更新时间',  PRIMARY KEY (`id`));ALTER TABLE `t_visit` ADD CONSTRAINT `fk_t_visit_t_site_1` FOREIGN KEY (`idsite`) REFERENCES `t_site` (`id`);ALTER TABLE `t_event` ADD CONSTRAINT `fk_t_event_t_site_1` FOREIGN KEY (`idsite`) REFERENCES `t_site` (`id`);ALTER TABLE `t_title` ADD CONSTRAINT `fk_t_title_t_site_1` FOREIGN KEY (`idsite`) REFERENCES `t_site` (`id`);ALTER TABLE `t_url` ADD CONSTRAINT `fk_t_url_t_site_1` FOREIGN KEY (`idsite`) REFERENCES `t_site` (`id`);ALTER TABLE `t_action` ADD CONSTRAINT `fk_t_action_t_visit_1` FOREIGN KEY (`idvisit`) REFERENCES `t_visit` (`id`);ALTER TABLE `t_action` ADD CONSTRAINT `fk_t_action_t_site_1` FOREIGN KEY (`idsite`) REFERENCES `t_site` (`id`);ALTER TABLE `t_subsite` ADD CONSTRAINT `fk_t_subsite_t_site_1` FOREIGN KEY (`idsite`) REFERENCES `t_site` (`id`);ALTER TABLE `t_share_user` ADD CONSTRAINT `fk_t_wxuser_t_site_1` FOREIGN KEY (`idsite`) REFERENCES `t_site` (`id`);ALTER TABLE `t_share_point` ADD CONSTRAINT `fk_t_share_point_t_site_1` FOREIGN KEY (`idsite`) REFERENCES `t_site` (`id`);ALTER TABLE `t_share_point` ADD CONSTRAINT `fk_t_share_point_t_url_1` FOREIGN KEY (`idurl`) REFERENCES `t_url` (`id`);ALTER TABLE `t_share_point` ADD CONSTRAINT `fk_t_share_point_t_share_user_1` FOREIGN KEY (`idvisitor`) REFERENCES `t_share_user` (`idvisitor`);
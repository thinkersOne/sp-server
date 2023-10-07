INSERT INTO aps.category (user_id,name,create_by,create_time,update_by,update_time) VALUES
	 (1,'1','admin','2023-09-23 09:30:34','admin','2023-09-23 09:30:34'),
	 (10007,'测试1','admin','2023-09-27 19:23:16','admin','2023-09-27 19:23:16'),
	 (10015,'测试1','admin','2023-10-06 23:20:05','admin','2023-10-06 23:20:05');
INSERT INTO aps.chart_message (user_id,content,sender,create_by,create_time,update_by,update_time,`role`,plantform) VALUES
	 (10008,'下午好','admin','admin','2023-10-06 12:54:49','admin','2023-10-06 12:54:49','user',1),
	 (10008,'您好，下午好！如果您有任何问题或需要帮助，请随时告诉我。','assistant','admin','2023-10-06 12:54:49','admin','2023-10-06 12:54:49','assistant',1),
	 (10008,'你好呀','admin','admin','2023-10-06 14:26:18','admin','2023-10-06 14:26:18','user',1),
	 (10008,'您好，请问您有什么需要我帮助的吗？','assistant','admin','2023-10-06 14:26:18','admin','2023-10-06 14:26:18','assistant',1),
	 (10008,'下午好','admin','admin','2023-10-06 14:45:04','admin','2023-10-06 14:45:04','user',2),
	 (10008,'下午好！很高兴能为你提供帮助。有什么我可以为你做的吗？','system','admin','2023-10-06 14:45:04','admin','2023-10-06 14:45:04','system',2),
	 (10008,'你好呀','admin','admin','2023-10-06 15:31:01','admin','2023-10-06 15:31:01','user',2),
	 (10008,'你好！很高兴能为你服务。有什么我可以为你做的吗？','system','admin','2023-10-06 15:31:01','admin','2023-10-06 15:31:01','system',2),
	 (10008,'我想吃冰淇淋','admin','admin','2023-10-06 15:31:20','admin','2023-10-06 15:31:20','user',2),
	 (10008,'冰淇淋是一种非常受欢迎的冷饮，有各种口味和形状可以选择。你可以在超市、冰淇淋店或者网上购买冰淇淋。在吃冰淇淋之前，最好先确认它的保质期和生产日期，以确保它是新鲜的。如果你在户外活动，也可以将冰淇淋放在冰箱里，以保持它的低温。','system','admin','2023-10-06 15:31:20','admin','2023-10-06 15:31:20','system',2);
INSERT INTO aps.chart_message (user_id,content,sender,create_by,create_time,update_by,update_time,`role`,plantform) VALUES
	 (10008,'请推荐一些上海旅游景点','admin','admin','2023-10-06 15:36:37','admin','2023-10-06 15:36:37','user',1),
	 (10008,'当然可以！上海是一个充满历史和文化底蕴的城市，拥有许多著名的旅游景点。以下是一些我推荐的上海旅游景点：

1. 外滩：上海的标志性景点之一，可以欣赏到黄浦江两岸的美景，以及外滩一侧的殖民地时代建筑。
2. 豫园：上海最著名的园林之一，建于18世纪，是一个非常有特色的古典园林。
3. 上海博物馆：中国最大的博物馆之一，收藏了大量的艺术品和历史文物。
4. 东方明珠电视塔：上海的标志性建筑之一，是一个非常有特色的现代建筑，可以俯瞰上海市区。
5. 上海迪士尼乐园：中国第一个迪士尼乐园，适合家庭娱乐和游玩。
6. 静安寺：上海最古老的寺庙之一，拥有许多历史文物和佛教艺术品。
7. 南京路步行街：中国最繁华的商业街之一，拥有许多购物中心和商店。
8. 上海野生动物园：中国最大的野生动物园之一，可以近距离观察各种野生动物。

希望这些景点能够为您的上海之旅提供一些参考！','assistant','admin','2023-10-06 15:36:37','admin','2023-10-06 15:36:37','assistant',1),
	 (10008,'请推荐一些上海有名的小吃景点','admin','admin','2023-10-06 15:37:02','admin','2023-10-06 15:37:02','user',2),
	 (10008,'上海是一个美食天堂，有很多著名的小吃景点。比如南京路步行街、豫园、田子坊、静安寺、上海外滩、新天地等。每个地方都有其特色小吃，比如小笼包、汤圆、麻婆豆腐、上海烤鸭等。你可以在这些地方品尝到上海最有名的小吃，同时也可以欣赏到上海独特的风景。','system','admin','2023-10-06 15:37:02','admin','2023-10-06 15:37:02','system',2),
	 (10008,'SpringBoot get请求路径传参应该怎么来做','admin','admin','2023-10-06 15:38:40','admin','2023-10-06 15:38:40','user',2),
	 (10008,'在Spring Boot中，可以使用@RequestParam注解来获取GET请求的路径参数。例如，如果你想获取名为id的参数，可以在方法上添加@RequestParam("id") String id的注解。然后，你就可以在方法中通过id来获取参数的值了。你也可以使用@RequestParamMap注解来获取所有路径参数。例如，如果你想获取名为id和name的参数，可以在方法上添加@RequestParamMap Map<String, String> params的注解。然后，你就可以在方法中通过params来获取所有参数的值了。','system','admin','2023-10-06 15:38:40','admin','2023-10-06 15:38:40','system',2),
	 (10015,'晚上好呀','测试1','admin','2023-10-06 23:22:41','admin','2023-10-06 23:22:41','user',1),
	 (10015,'晚上好！如果您有任何问题或需要帮助，请随时告诉我。','assistant','admin','2023-10-06 23:22:41','admin','2023-10-06 23:22:41','assistant',1),
	 (10015,'晚上好呀','测试1','admin','2023-10-06 23:22:52','admin','2023-10-06 23:22:52','user',2),
	 (10015,'晚上好！很高兴能为你服务。有什么我可以为你做的吗？','system','admin','2023-10-06 23:22:52','admin','2023-10-06 23:22:52','system',2);
INSERT INTO aps.chart_message (user_id,content,sender,create_by,create_time,update_by,update_time,`role`,plantform) VALUES
	 (10015,'sudo yum install yum-utils device-mapper-persistent-data lvm2 Last metadata expiration check: 0:05:16 ago on Sat 07 Oct 2023 12:06:51 AM CST. No match for argument: yum-utils Package thin-provisioning-tools-0.9.0-3.r2.hce2.x86_64 is already installed. Package lvm2-8:2.03.14-4.r16.hce2.x86_64 is already installed. Error: Unable to find a match: yum-utils','测试1','admin','2023-10-07 00:13:56','admin','2023-10-07 00:13:56','user',2),
	 (10015,'It looks like the ``yum-utils`` package is already installed on your system. The error message is indicating that there is no match for the ``yum-utils`` argument in the list of available packages.
This could be because you have already installed ``yum-utils`` and there is no need to install it again. If you are trying to install another package that requires ``yum-utils``, you can try running the command with the package name instead of ``yum-utils``.
For example, if you are trying to install ``thin-provisioning-tools``, you can run the following command:
``````
sudo yum install thin-provisioning-tools
``````

If you are trying to install ``lvm2``, you can run the following command:
``````
sudo yum install lvm2
``````

I hope this helps! Let me know if you have any questions.','system','admin','2023-10-07 00:13:56','admin','2023-10-07 00:13:56','system',2);
INSERT INTO aps.password (user_id,category_id,title,account,password,url,notes,create_by,create_time,update_by,update_time) VALUES
	 (10015,4,'测试1','测试1','123456','XXXXX','1','admin','2023-10-06 23:22:28','admin','2023-10-06 23:22:28');
INSERT INTO aps.`user` (username,password,create_by,create_time,update_by,update_time) VALUES
	 ('1111','1111','admin','2023-08-23 21:33:36','admin','2023-08-23 21:33:36'),
	 ('123456','112233','admin','2023-08-23 22:22:40','admin','2023-08-23 22:22:40'),
	 ('3333','123456','admin','2023-09-24 16:22:53','admin','2023-09-24 16:22:53'),
	 ('test','123456','admin','2023-09-24 16:32:51','admin','2023-09-24 16:32:51'),
	 ('sa','123456','-1','2023-05-28 09:42:20','-1','2023-05-28 09:42:20'),
	 ('admin','admin','-1','2023-05-28 09:42:20','-1','2023-05-28 09:42:20'),
	 ('admin2','admin2','-1','2023-05-28 09:42:20','-1','2023-05-28 09:42:20'),
	 ('出生地我','123456','10001','2023-06-02 06:23:26','10001','2023-06-02 06:23:26'),
	 ('测试1','123456','0','2023-06-02 06:41:32','0','2023-06-02 06:41:32'),
	 ('测试2','123456','0','2023-06-03 02:19:01','0','2023-06-03 02:19:01');
INSERT INTO aps.`user` (username,password,create_by,create_time,update_by,update_time) VALUES
	 ('测试1','123456','0','2023-10-06 23:17:06','0','2023-10-06 23:17:06');
INSERT INTO aps.version_info (version,url,description,create_by,create_time,update_by,update_time) VALUES
	 ('2.0.0.0','/upload/file/2023/08-24/16928862528461890507042.exe','','admin','2023-08-24 22:11:16','admin','2023-08-24 22:11:16');

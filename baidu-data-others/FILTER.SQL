-- 使用连接查询，条件显示所有物业的信息和对应的价格
select * from price p join street_shop_transaction s on p.shops_id=s.id;

-- 过滤字段 导出sql 数据到清洗表
select replace(replace(replace(replace(p.price,char(10),''),char(13),''),char(9),''),char(160),'') as price,
p.shops_id as property_id,
title,address,street_shop_type as category,
built_up_area as area,
street_shop_profile as description,
suitable_operation,agencyname,username as agency,
phone as tel,url as link_url,website,
concat(c_id,r_id,s_id),replace(concat_ws('-',r_id,s_id),'轻松买铺，贷来财富','') as region
from price p join street_shop_transaction s on p.shops_id=s.id;

select count(*) from price;
select count(*) from street_shop_transaction;

-- 替换空格 换行 制表符
select replace(replace(replace(replace(price,char(10),''),char(13),''),char(160),''),char(9),'') from price where id='000bb3f191f911e68eee448a5b06eee2'

select replace('25000元/月  ',char(160),'')

-- 去掉uuid的-
update property_images set img_id=replace(img_id,'-','')

-- 合并picture表(字段别名)
select id as img_id, pic_url as img_url,belong_id as property_id from picture 

-- 合并property表(字段设置别名)赶集
select SUBSTRING_INDEX(price, '元', 1) as price,
substring_index(price,'元',-2) as price_note,
p.shops_id as property_id,
title,address,street_shop_type as category,
cast(built_up_area as char(10)) as area,
street_shop_profile as description,
username as agency,
phone as tel,url as link_url,
concat_ws('-',r_id,s_id) as region
from price p join street_shop_transaction s on p.shops_id=s.id;

-- 合并property表(字段设置别名)58
select SUBSTRING_INDEX(replace(replace(replace(replace(p.price,char(10),''),char(13),''),char(9),''),char(160),''),'元',1) as price,
SUBSTRING_INDEX(replace(replace(replace(replace(p.price,char(10),''),char(13),''),char(9),''),char(160),''),'元',-1)as price_note,
p.shops_id as property_id,
title,address,street_shop_type as category,
cast(built_up_area as char(10)) as area,
street_shop_profile as description,
username as agency,
phone as tel,url as link_url,
replace(concat_ws('-',r_id,s_id),'轻松买铺，贷来财富','') as region
from price p join street_shop_transaction s on p.shops_id=s.id;

-- 合并到生产环境
select img_id as ID,img_url as OSS_URL,property_id as SHOPSID  from property_images

SELECT property_id as ID,
property_id as HISTORYID,
SUBSTRING_INDEX(REGION,'-',1) as AREA,
SUBSTRING_INDEX(REGION,'-',-1) as STREET,
address as ADDRESS,
CAST(area AS DECIMAL) as SHOP_SIZE,
lat as LAT,
lng as LNG
FROM property

SELECT property_id as ID,
property_id as BASE_SHOPSID,
title as SHOPS_NAME,
CAST(price as DECIMAL)as TOTAL_RENT_FEE,
CAST(fee as DECIMAL)as RENT_FEE,
category as ESTATES_TYPE,
1 as RELEASE_TYPE,
publishdate as CREATE_TIME,
replace(REPLACE(tel,CHAR(10),''),' ','') as MOBILEPHONE,
agency as USERID,
floor as FLOOR
FROM property
where tel not like '%http%'


-- 使用GROUP_CONCAT(expr)分组取最新一条数据
select count(*) from property_images
select * from property_images where img_id in(select SUBSTRING_INDEX(GROUP_CONCAT(img_id ORDER BY property_id),',',1) from property_images GROUP BY property_id);
select * from property_images where img_id in(select SUBSTRING_INDEX(GROUP_CONCAT(img_id ORDER BY create_time desc),',',1) from property_images GROUP BY property_id);

select img_url from property_images p1 where  p1.img_id in(select SUBSTRING_INDEX(GROUP_CONCAT(p2.img_id order by p2.create_time desc),',',1) from property_images p2 group by p2.property_id );

select DISTINCT(property_id) from property_images where img_id in(select SUBSTRING_INDEX(GROUP_CONCAT(img_id ORDER BY property_id),',',1) from property_images GROUP BY property_id);

-- 使用join
select * from property_images p1,(select property_id from property_images GROUP BY property_id) p2 where p1.property_id=p2.property_id;

select s1.* from stu s1 join (select class,max(id) as id from stu group by class) s2 where s1.class=s2.class and s1.id=s2.id;

select s1.*,s2.* from stu s1 left join stu s2 on s1.class=s2.class and s1.id<s2.id where s2.id is null;

select * from stu s1 left join stu s2 on s1.class=s2.class

select * from property p
left join (select img_url,property_id from property_images p1 where p1.img_id in(select SUBSTRING_INDEX(GROUP_CONCAT(p2.img_id order by p2.create_time desc),',',1) from property_images p2 group by p2.property_id ) )pi 
on pi.property_id=p.property_id where p.link_url like '%zu%';
Create table T_RESEIVE_MSG
(
	ToUserName VARCHAR(100),
	FromUserName  VARCHAR(100),
	CreateTime BIGINT,
	MsgType VARCHAR(20),--text,image,location,link,event
	Content VARCHAR(500),
	MsgId BIGINT,
	PicUrl VARCHAR(100),
	Location_X DOUBLE,
	Location_Y  DOUBLE,
	Scale INT,
	Label VARCHAR(100),
	Title VARCHAR(100),
	Description VARCHAR(500),
	Url VARCHAR(100),
	Event VARCHAR(100), --ENTER,LOCATION
	Latitude DOUBLE,
	Longitude DOUBLE,
	A_Precision DOUBLE
)

Create table T_SEND_MSG
(
	ToUserName VARCHAR(100),
	FromUserName  VARCHAR(100),
	CreateTime BIGINT,
	MsgType  VARCHAR(20),--text,music,news
	Content VARCHAR(500),
	MsgId INT NOT NULL   PRIMARY KEY auto_increment,
	FuncFlag VARCHAR(50),
	ArticleCount INT,
	MusicUrl  VARCHAR(100),
	HQMusicUrl  VARCHAR(100),
	Articles   VARCHAR(1000)
)

Create table T_USER_INFO 
(
	Id INT NOT NULL   PRIMARY KEY auto_increment,
	UserName VARCHAR(100),
	Sex VARCHAR(10),
)

alter table T_USER_INFO add column Mobile varchar(20) DEFAULT NULL;
alter table T_RESEIVE_MSG add column EventKey varchar(20) DEFAULT NULL;
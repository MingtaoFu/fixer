CREATE USER 'taotao'@'localhost' IDENTIFIED BY 'taotao666';
CREATE DATABASE fixer character SET utf8;
USE fixer;

CREATE TABLE IF NOT EXISTS Customer(									/* 客户*/
	cid INT(5) NOT NULL AUTO_INCREMENT,
	id VARCHAR(20) NOT NULL UNIQUE,									/* 身份证号*/
	property  enum(0,1,2,3) NOT NULL,						/* 客户属性*/
	companyName VARCHAR(100),										/* 单位名称*/
	tel VARCHAR(15),														/* 座机*/
	mobilePhone VARCHAR(15) NOT NULL,								/* 移动电话*/
	address VARCHAR(100) NOT NULL,									/* 地址*/
	zipCode VARCHAR(10),													/* 邮编*/
	contactPersonName VARCHAR(20) NOT NULL ,						/* 联系人*/
	email VARCHAR(50),													/* 电子邮件*/
	CONSTRAINT PK_CID PRIMARY KEY(cid))
	DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS Device(										/* 送修设备*/
	did INT(5) AUTO_INCREMENT NOT NULL,
	cid INT(5)  NOT NULL,													/* 客户id*/
	ctime TIMESTAMP default CURRENT_TIMESTAMP,						/* 报修时间*/
	expectedPrice DECIMAL(8,2),											/* 预估价格*/
	expectedCompletedTime TIMESTAMP,								/* 预估完成时间*/
	status enum(0,1,2) NOT NULL,								/* 报修状态*/
	deviceType enum(0,1,2,3,4) NOT NULL,				/* 机器类型*/
	deviceBrand VARCHAR(50),											/* 机器品牌*/
	deviceModel VARCHAR(50),											/* 机器型号*/
	deviceSerialNum VARCHAR(50),										/* 系列号*/
	lackPart VARCHAR(50),					 								/* 缺少零件*/
	breakdownAppearance VARCHAR(100) NOT NULL,					/* 机器故障现象*/
	breakdownType enum(0,1) NOT NULL,							/* 故障类型*/
	appearanceCheck VARCHAR(100),										/* 机器外观检查*/
	startingUpCommand VARCHAR(100),									/* 开机口令*/
	significantMaterial VARCHAR(100),									/* 重要资料*/
	HHD VARCHAR(100),													/* HHD*/
	RAM VARCHAR(100),													/* RAM*/
	PCCard VARCHAR(100), 												/* 外置 PC 卡*/
	ACAdapter VARCHAR(100),												/* AC 适配器*/
	battery VARCHAR(100),												/* 电池*/
	CD_ROM VARCHAR(100),												/* 外置光驱*/
	floppy VARCHAR(100),													/* 外置软驱*/
	other VARCHAR(100),													/* 其他*/
	CONSTRAINT PK_DID PRIMARY KEY(did),
	CONSTRAINT FK_CID FOREIGN KEY(cid) REFERENCES Customer(cid))
	DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

CREATE TABLE  IF NOT EXISTS RepairRecord(								/* 维修记录*/
	rrid INT(5) AUTO_INCREMENT NOT NULL,
	did INT(5)  NOT NULL,													/* 报修记录id*/
	distributeTime TIMESTAMP,											/* 分配时间*/
	maintenance VARCHAR(20),											/* 维修人员*/
	detectionRecord VARCHAR(100),										/* 检测记录(信息)*/
	repairRecord VARCHAR(100),											/* 维修记录(信息)*/
	repairTime TIMESTAMP,												/* 维修检测时间*/
	workload VARCHAR(20),												/* 工作量*/
	requiredPart VARCHAR(50),											/* 维修所使用的器件*/
	status enum(0,1,2,3) NOT NULL,						/* 维修状态*/
	delayDegree enum(0,1,2),									/* 延迟程度*/
	CONSTRAINT PK_RRID PRIMARY KEY(rrid),
	CONSTRAINT FK_DID FOREIGN KEY(did) REFERENCES Device(did))
	DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS Parts(										/* 备件*/
	pid INT(5) AUTO_INCREMENT NOT NULL,
	partName VARCHAR(50) NOT NULL,									/* 备件名称?*/
	price DECIMAL(8,2),													/* 单价//*/
	modelNumber VARCHAR(50),											/* 型号?*/
	quantity INT(8) NOT NULL,											/* 数量?*/
	inTime TIMESTAMP,													/* 入库时间*/
	waringQuantity INT(8) NOT NULL,										/* 警戒数量*/
	status enum(0,1,2,3),									/* 库存状态:
																			/*正常:库存量>警戒数量
																			     临界:库存量=警戒数量
																			     警示:0<库存量<警戒数量
																			     缺货:库存量=0 */
	CONSTRAINT PK_PID PRIMARY KEY(pid))
	DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS DetailedPartsList(							/* 备件流水明细*/
	plid INT(5) AUTO_INCREMENT NOT NULL,
	rrid INT(5)  NOT NULL,												/* 维修记录*/
	pid INT(5)  NOT NULL,													/* 备件号*/
	partName VARCHAR(50) NOT NULL,									/* 备件名称?*/
	price DECIMAL(8,2),													/* 单价//*/
	modelNumber VARCHAR(50),											/* 型号?*/
	quantity INT(8) NOT NULL,											/* 数量?*/
	outTime TIMESTAMP,													/* 出库时间*/
	CONSTRAINT PK_PLID PRIMARY KEY(plid),
	CONSTRAINT FK_PID FOREIGN KEY(pid) REFERENCES Parts(pid),
	CONSTRAINT FK_P_RRID FOREIGN KEY(rrid) REFERENCES RepairRecord(rrid))
	DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS Settlement(									/* 结算*/
	sid INT(5) AUTO_INCREMENT NOT NULL,
	rrid INT(5)  NOT NULL,												/* 维修记录*/
	laborCosts DECIMAL(8,2) NOT NULL,	 								/* 人工费*/
	materialsCosts DECIMAL(8,2) NOT NULL,								/* 材料费*/
	warrantyPromise VARCHAR(100),										/* 报修承诺*/
	notice VARCHAR(100),													/* 注意事项*/
	settlementTime TIMESTAMP,											/* 结算日期*/
	status enum(\"0\",\"1\", \"2\") NOT NULL DEFAULT \"0\",			/* 审核状态*/
	CONSTRAINT PK_SID PRIMARY KEY(sid),
	CONSTRAINT FK_S_RRID FOREIGN KEY(rrid) REFERENCES RepairRecord(rrid))
	DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS User(
	uid INT(5) AUTO_INCREMENT NOT NULL,
	userName VARCHAR(50) NOT NULL UNIQUE,
	passWord VARCHAR(100) NOT NULL,
	characters enum(0,1,2,3,4,5,6)  NOT NULL,
	CONSTRAINT PK_UID PRIMARY KEY(uid))
	DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;


insert into Device(cid,status,deviceType,breakdownAppearance,breakdownType) Values(1,0,1,'gaygaygay',1);
insert into RepairRecord(did,distributeTime,repairTime) Values(1,'2016-01-01 00:00:00','2016-01-02 00:00:00');

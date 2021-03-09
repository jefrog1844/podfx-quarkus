INSERT INTO Auth(id, email, firstName, lastName, username, password, tenant) VALUES(1, 'user@podfx.com','pod','fx','read.only','readonly','podfx1');
INSERT INTO Auth(id, email, firstName, lastName, username, password, tenant) VALUES(2, 'user@podfx.com','pod','fx','read.write','readwrite','podfx2');
INSERT INTO Auth(id, email, firstName, lastName, username, password, tenant) VALUES(3, 'user@podfx.com','pod','fx','full.access','fullaccess','podfx3');
INSERT INTO Role(id, name) VALUES (1, 'create');
INSERT INTO Role(id, name) VALUES (2, 'read');
INSERT INTO Role(id, name) VALUES (3, 'update');
INSERT INTO Role(id, name) VALUES (4, 'delete');
INSERT INTO USER_ROLE(user_id, role_id) VALUES (1,2);
INSERT INTO USER_ROLE(user_id, role_id) VALUES (2,1);
INSERT INTO USER_ROLE(user_id, role_id) VALUES (2,2);
INSERT INTO USER_ROLE(user_id, role_id) VALUES (2,3);
INSERT INTO USER_ROLE(user_id, role_id) VALUES (3,1);
INSERT INTO USER_ROLE(user_id, role_id) VALUES (3,2);
INSERT INTO USER_ROLE(user_id, role_id) VALUES (3,3);
INSERT INTO USER_ROLE(user_id, role_id) VALUES (3,4);
INSERT INTO DFMEA(id,number,originated,originator,partnumber,revised,teammembers,title,type) VALUES(1,'FM-0001','01/01/2020','jrogers','TA-1000','01/02/2020','jrogers,crogers,crogers,mrogers','Linkage Weldment','System');
INSERT INTO DFMEA(id,number,originated,originator,partnumber,revised,teammembers,title,type) VALUES(2,'FM-0002','01/02/2020','crogers','FL-2000','01/03/2020','jrogers,crogers,crogers,mrogers','Flashlight Turbo','System');
INSERT INTO DFMEA(id,number,originated,originator,partnumber,revised,teammembers,title,type) VALUES(3,'FM-0003','01/03/2020','crogers','MT-3000','01/04/2020','jrogers,crogers,crogers,mrogers','Mouse Trap','System');
INSERT INTO DFMEA(id,number,originated,originator,partnumber,revised,teammembers,title,type) VALUES(4,'FM-0004','01/04/2020','mrogers','MT-4000','01/05/2020','jrogers,crogers,crogers,mrogers','Super Mouse Trapinator','System');
INSERT INTO DFMEA(id,number,originated,originator,partnumber,revised,teammembers,title,type) VALUES(5,'FM-0005','01/05/2020','jrogers','GS-5000',null,'jrogers,crogers,crogers,mrogers','Garden Sprinkler','System');
INSERT INTO BLOCK(id,name,type,parent_block_id,dfmea_id) VALUES (1,'Rear Suspension','System',null,1);
INSERT INTO BLOCK(id,name,type,parent_block_id,dfmea_id) VALUES (2,'Kinematic elements','High-level subsystem',1,1);
INSERT INTO BLOCK(id,name,type,parent_block_id,dfmea_id) VALUES (3,'Chassis elements','High-level subsystem',1,1);
INSERT INTO BLOCK(id,name,type,parent_block_id,dfmea_id) VALUES (4,'Axle assembly','Subsystem',2,1);
INSERT INTO BLOCK(id,name,type,parent_block_id,dfmea_id) VALUES (5,'Macperson struts','Subsystem',2,1);
INSERT INTO BLOCK(id,name,type,parent_block_id,dfmea_id) VALUES (6,'Trailing arm','Subsystem',2,1);
INSERT INTO BLOCK(id,name,type,parent_block_id,dfmea_id) VALUES (7,'Wheel system','Subsystem',3,1);
INSERT INTO BLOCK(id,name,type,parent_block_id,dfmea_id) VALUES (8,'Other elements','Subsystem',3,1);
INSERT INTO BLOCK(id,name,type,parent_block_id,dfmea_id) VALUES (9,'Molded bushing','Component',6,1);
INSERT INTO BLOCK(id,name,type,parent_block_id,dfmea_id) VALUES (10,'E-coat','Component',6,1);
INSERT INTO BLOCK(id,name,type,parent_block_id,dfmea_id) VALUES (11,'Label','Component',6,1);
INSERT INTO BLOCK(id,name,type,parent_block_id,dfmea_id) VALUES (12,'Linkage Weldment','Component',6,1);
INSERT INTO BLOCK(id,name,type,parent_block_id,dfmea_id) VALUES (13,'Bushing','Part',12,1);
INSERT INTO BLOCK(id,name,type,parent_block_id,dfmea_id) VALUES (14,'Rod','Part',12,1);
INSERT INTO BLOCK(id,name,type,parent_block_id,dfmea_id) VALUES (15,'Clevis','Part',12,1);
INSERT INTO FACTOR(id,name,category,dfmea_id) VALUES (1,'Force','Signal',1);
INSERT INTO FACTOR(id,name,category,dfmea_id) VALUES (2,'Motion','Signal',1);
INSERT INTO FACTOR(id,name,category,dfmea_id) VALUES (3,'Rod','Internal',1);
INSERT INTO FACTOR(id,name,category,dfmea_id) VALUES (4,'Bushing ring','Internal',1);
INSERT INTO FACTOR(id,name,category,dfmea_id) VALUES (5,'Clevis','Internal',1);
INSERT INTO FACTOR(id,name,category,dfmea_id) VALUES (6,'Salt Water','Environmental',1);
INSERT INTO FACTOR(id,name,category,dfmea_id) VALUES (7,'Stone','Environmental',1);
INSERT INTO FACTOR(id,name,category,dfmea_id) VALUES (8,'Molded busing,e-coat,label','System',1);
INSERT INTO FACTOR(id,name,category,dfmea_id) VALUES (9,'Wheel system','System',1);
INSERT INTO FACTOR(id,name,category,dfmea_id) VALUES (10,'Axle','System',1);
INSERT INTO FACTOR(id,name,category,dfmea_id) VALUES (11,'Chassis','System',1);
INSERT INTO FACTOR(id,name,category,dfmea_id) VALUES (12,'Rebound limit','Customer',1);
INSERT INTO FACTOR(id,name,category,dfmea_id) VALUES (13,'Jounce limit','Customer',1);
INSERT INTO interface (id, dataexchange, enabled, energytransfer, materialexchange, physicalconnection, input_factor_id, output_factor_id) 
	VALUES (1, NULL, true, 'Transfer force', 'Retain ring to rod', 'Locate rod to ring', 3, 4);
INSERT INTO interface (id, dataexchange, enabled, energytransfer, materialexchange, physicalconnection, input_factor_id, output_factor_id) 
	VALUES (2, NULL, true, 'Transfer force', 'Retain clevis to rod', 'Locate rod to clevis', 3, 5);
INSERT INTO interface (id, dataexchange, enabled, energytransfer, materialexchange, physicalconnection, input_factor_id, output_factor_id) 
	VALUES (3, NULL, false, NULL, NULL, NULL, 3, 6);
INSERT INTO interface (id, dataexchange, enabled, energytransfer, materialexchange, physicalconnection, input_factor_id, output_factor_id) 
	VALUES (4, NULL, false, NULL, NULL, NULL, 3, 7);
INSERT INTO interface (id, dataexchange, enabled, energytransfer, materialexchange, physicalconnection, input_factor_id, output_factor_id) 
	VALUES (5, NULL, false, NULL, NULL, NULL, 3, 8);
INSERT INTO interface (id, dataexchange, enabled, energytransfer, materialexchange, physicalconnection, input_factor_id, output_factor_id) 
	VALUES (6, NULL, false, NULL, NULL, NULL, 3, 9);
INSERT INTO interface (id, dataexchange, enabled, energytransfer, materialexchange, physicalconnection, input_factor_id, output_factor_id) 
	VALUES (7, NULL, false, NULL, NULL, NULL, 3, 10);
INSERT INTO interface (id, dataexchange, enabled, energytransfer, materialexchange, physicalconnection, input_factor_id, output_factor_id) 
	VALUES (8, NULL, false, NULL, NULL, NULL, 3, 11);
INSERT INTO interface (id, dataexchange, enabled, energytransfer, materialexchange, physicalconnection, input_factor_id, output_factor_id) 
	VALUES (9, NULL, false, NULL, NULL, NULL, 3, 12);
INSERT INTO interface (id, dataexchange, enabled, energytransfer, materialexchange, physicalconnection, input_factor_id, output_factor_id) 
	VALUES (10, NULL, false, NULL, NULL, NULL, 3, 13);
INSERT INTO interface (id, dataexchange, enabled, energytransfer, materialexchange, physicalconnection, input_factor_id, output_factor_id) 
	VALUES (11, NULL, true, NULL, NULL, 'Control angular relationship', 4, 5);
INSERT INTO interface (id, dataexchange, enabled, energytransfer, materialexchange, physicalconnection, input_factor_id, output_factor_id) 
	VALUES (12, NULL, false, NULL, NULL, NULL, 4, 6);
INSERT INTO interface (id, dataexchange, enabled, energytransfer, materialexchange, physicalconnection, input_factor_id, output_factor_id) 
	VALUES (13, NULL, false, NULL, NULL, NULL, 4, 7);
INSERT INTO interface (id, dataexchange, enabled, energytransfer, materialexchange, physicalconnection, input_factor_id, output_factor_id) 
	VALUES (14, NULL, false, NULL, NULL, NULL, 4, 8);
INSERT INTO interface (id, dataexchange, enabled, energytransfer, materialexchange, physicalconnection, input_factor_id, output_factor_id) 
	VALUES (15, NULL, false, NULL, NULL, NULL, 4, 9);
INSERT INTO interface (id, dataexchange, enabled, energytransfer, materialexchange, physicalconnection, input_factor_id, output_factor_id) 
	VALUES (16, NULL, false, NULL, NULL, NULL, 4, 10);
INSERT INTO interface (id, dataexchange, enabled, energytransfer, materialexchange, physicalconnection, input_factor_id, output_factor_id) 
	VALUES (17, NULL, false, NULL, NULL, NULL, 4, 11);
INSERT INTO interface (id, dataexchange, enabled, energytransfer, materialexchange, physicalconnection, input_factor_id, output_factor_id) 
	VALUES (18, NULL, false, NULL, NULL, NULL, 4, 12);
INSERT INTO interface (id, dataexchange, enabled, energytransfer, materialexchange, physicalconnection, input_factor_id, output_factor_id) 
	VALUES (19, NULL, false, NULL, NULL, NULL, 4, 13);
INSERT INTO interface (id, dataexchange, enabled, energytransfer, materialexchange, physicalconnection, input_factor_id, output_factor_id) 
	VALUES (20, NULL, false, NULL, NULL, NULL, 5, 6);
INSERT INTO interface (id, dataexchange, enabled, energytransfer, materialexchange, physicalconnection, input_factor_id, output_factor_id) 
	VALUES (21, NULL, false, NULL, NULL, NULL, 5, 7);
INSERT INTO interface (id, dataexchange, enabled, energytransfer, materialexchange, physicalconnection, input_factor_id, output_factor_id) 
	VALUES (22, NULL, false, NULL, NULL, NULL, 5, 8);
INSERT INTO interface (id, dataexchange, enabled, energytransfer, materialexchange, physicalconnection, input_factor_id, output_factor_id) 
	VALUES (23, NULL, false, NULL, NULL, NULL, 5, 9);
INSERT INTO interface (id, dataexchange, enabled, energytransfer, materialexchange, physicalconnection, input_factor_id, output_factor_id) 
	VALUES (24, NULL, false, NULL, NULL, NULL, 5, 10);
INSERT INTO interface (id, dataexchange, enabled, energytransfer, materialexchange, physicalconnection, input_factor_id, output_factor_id) 
	VALUES (25, NULL, false, NULL, NULL, NULL, 5, 11);
INSERT INTO interface (id, dataexchange, enabled, energytransfer, materialexchange, physicalconnection, input_factor_id, output_factor_id) 
	VALUES (26, NULL, false, NULL, NULL, NULL, 5, 12);
INSERT INTO interface (id, dataexchange, enabled, energytransfer, materialexchange, physicalconnection, input_factor_id, output_factor_id) 
	VALUES (27, NULL, false, NULL, NULL, NULL, 5, 13);
ALTER SEQUENCE auth_id_seq restart with 100;
ALTER SEQUENCE block_id_seq restart with 100;
ALTER SEQUENCE dfmea_id_seq restart with 100;
ALTER SEQUENCE factor_id_seq restart with 100;
ALTER SEQUENCE funktion_id_seq restart with 100;
ALTER SEQUENCE interface_id_seq restart with 100;
ALTER SEQUENCE failuremode_id_seq restart with 100;
ALTER SEQUENCE hibernate_sequence restart with 100;

--                                                                         (id, date_time, no_run, power, status)

-- Nessa primeira parte a máquina está parada e sem energia elétrica
INSERT INTO tb_status_maquina (id, date_time, no_run, power, status) VALUES(1,'2019-05-01 01:01',1,0,0);
INSERT INTO tb_status_maquina (id, date_time, no_run, power, status) VALUES(2,'2019-05-01 02:01',1,0,0);
INSERT INTO tb_status_maquina (id, date_time, no_run, power, status) VALUES(3,'2019-05-01 03:01',1,0,0);
INSERT INTO tb_status_maquina (id, date_time, no_run, power, status) VALUES(4,'2019-05-01 04:01',1,0,0);
INSERT INTO tb_status_maquina (id, date_time, no_run, power, status) VALUES(5,'2019-05-01 05:01',1,0,0);

-- Aqui ela começou a trabalhar
INSERT INTO tb_status_maquina (id, date_time, no_run, power, status) VALUES(6,'2019-05-01 06:01',0,1,1);
INSERT INTO tb_status_maquina (id, date_time, no_run, power, status) VALUES(7,'2019-05-01 07:01',0,1,1);
INSERT INTO tb_status_maquina (id, date_time, no_run, power, status) VALUES(8,'2019-05-01 08:01',0,1,1);
INSERT INTO tb_status_maquina (id, date_time, no_run, power, status) VALUES(9,'2019-05-01 09:01',0,1,1);

-- Porém ocorreu um erro ( Faltou material)
INSERT INTO tb_status_maquina (id, date_time, no_run, power, status) VALUES(10,'2019-05-01 10:01',1,1,0);

-- O pessoal autorizado conseguiu iserir o mateiral faltante
INSERT INTO tb_status_maquina (id, date_time, no_run, power, status) VALUES(11,'2019-05-01 11:01',0,1,1);
INSERT INTO tb_status_maquina (id, date_time, no_run, power, status) VALUES(12,'2019-05-01 12:01',0,1,1);
INSERT INTO tb_status_maquina (id, date_time, no_run, power, status) VALUES(13,'2019-05-01 13:01',0,1,1);
INSERT INTO tb_status_maquina (id, date_time, no_run, power, status) VALUES(14,'2019-05-01 14:01',0,1,1);
INSERT INTO tb_status_maquina (id, date_time, no_run, power, status) VALUES(15,'2019-05-01 15:01',0,1,1);
INSERT INTO tb_status_maquina (id, date_time, no_run, power, status) VALUES(16,'2019-05-01 16:01',0,1,1);

-- Aconteceu outro erro
INSERT INTO tb_status_maquina (id, date_time, no_run, power, status) VALUES(17,'2019-05-01 17:01',1,1,0);

-- já foi resolvido
INSERT INTO tb_status_maquina (id, date_time, no_run, power, status) VALUES(18,'2019-05-01 18:01',0,1,1);

-- fim do expediente
INSERT INTO tb_status_maquina (id, date_time, no_run, power, status) VALUES(19,'2019-05-01 19:01',1,0,0);
INSERT INTO tb_status_maquina (id, date_time, no_run, power, status) VALUES(20,'2019-05-01 20:01',1,0,0);
INSERT INTO tb_status_maquina (id, date_time, no_run, power, status) VALUES(21,'2019-05-01 21:01',1,0,0);
INSERT INTO tb_status_maquina (id, date_time, no_run, power, status) VALUES(22,'2019-05-01 22:01',1,0,0);
INSERT INTO tb_status_maquina (id, date_time, no_run, power, status) VALUES(23,'2019-05-01 23:01',1,0,0);
INSERT INTO tb_status_maquina (id, date_time, no_run, power, status) VALUES(00,'2019-05-01 00:01',1,0,0);

SELECT
    date_time AS momento_gravacao, 
    COALESCE(SUM(status),0) AS total
FROM tb_dados_maquina AS dm
WHERE 
    dm.status = 1 
GROUP BY date_time;
 
--  tentando separar por dias
 
SELECT
    date(DATE_TRUNC('day',dm.date_time) + INTERVAL'1 day' - INTERVAL'1 day') AS momento_gravacao, 
    count(DATE_TRUNC('day',dm.date_time) + INTERVAL'1 day' - INTERVAL'1 day') as total
FROM tb_dados_maquina AS dm
WHERE 
    dm.status = 1 
GROUP BY dm.date_time
ORDER BY date(dm.date_time);
 

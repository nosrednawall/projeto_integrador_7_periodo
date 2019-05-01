-- select de dados da tabela dimensao quantidade de vezes maquina parou

-- Primeiro funciona que uma beleza
SELECT
    *
FROM 
    tb_qtda_maquina_parou
WHERE
    date_time::date >= CURRENT_DATE;

    
    
    
--Segundo funciona bem também 
SELECT
    *
FROM 
    tb_qtda_maquina_parou
WHERE
    date_time::date >= '2019-05-01';
    
    
    
    
-- Terceiro  funciona bem também
SELECT
    *
FROM 
    tb_qtda_maquina_parou
WHERE
    date_time::date >= '2019-04-01'
AND
    date_time::date < '2019-05-03';





-- Selects de exemplo, não utilizar
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
 

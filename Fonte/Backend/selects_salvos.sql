SELECT
    CAST (data AS date)
    , COALESCE(COUNT (power) FILTER(WHERE power=100),0) AS Cem_Porcento
    , COALESCE(COUNT (power) FILTER(WHERE power=75),0) AS Setenta_e_cinco_Porcento
    , COALESCE(COUNT (power) FILTER(WHERE power=50),0) AS Cinquenta_Porcento
    , COALESCE(COUNT (power) FILTER(WHERE power=25),0) AS Vinte_E_cinco_Porcento
    , COALESCE(COUNT (power) FILTER(WHERE power=0),0) AS Zero_Porcento
    
FROM
    tb_power_maquina 
WHERE
    CAST (data AS date) BETWEEN '2019-01-01'
AND
    '2019-06-03'
GROUP BY CAST (data AS date);




SELECT
    CAST (data AS date)
    , COALESCE(COUNT (status) FILTER(WHERE status=1),0) AS status_ligado
    , COALESCE(COUNT (status) FILTER(WHERE status=0),0) AS status_desligado
FROM
    tb_status_maquina 
WHERE
    CAST (data AS date) BETWEEN '2019-04-01'
AND
    '2019-06-03'
GROUP BY CAST (data AS date);
   
    
SELECT f, SUM(f.autoMan) FROM tb_funcionamento_maquina f WHERE f.data BETWEEN :pDataInicial AND :pDataLimite


select p from Persons p where (cast(:createdAt as timestamp) is null or p.createdAt > :createdAt)


SELECT
    CAST (data AS date)
    , COALESCE(SUM(auto_man),0) as auto_man
    , COALESCE(SUM(run_cmd),0) as run_cmd
FROM
    tb_funcionamento_maquina 
WHERE
    CAST (data AS date) BETWEEN '2019-04-01'
AND
    '2019-06-03'
GROUP BY CAST (data AS date);



SELECT
    data::date, SUM(auto_man) as soma
FROM
    tb_funcionamento_maquina 
WHERE
    data::date BETWEEN '2019-04-01'
AND
    '2019-06-03'
GROUP BY data::date;


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
 

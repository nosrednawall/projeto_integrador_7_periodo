code_simatic=> \d tb_dados_maquina
                     Tabela "public.tb_dados_maquina"
  Coluna   |            Tipo             | Collation | Nullable | Default 
-----------+-----------------------------+-----------+----------+---------
 id        | bigint                      |           | not null | 
 auto_man  | integer                     |           | not null | 
 date_time | timestamp without time zone |           |          | 
 no_run    | integer                     |           | not null | 
 power     | integer                     |           | not null | 
 run_cmd   | integer                     |           | not null | 
 speed_pv  | character varying(255)      |           | not null | 
 status    | integer                     |           | not null | 
 version   | integer                     |           |          | 
Índices:
    "tb_dados_maquina_pkey" PRIMARY KEY, btree (id)
Restrições de verificação:
    "tb_dados_maquina_auto_man_check" CHECK (auto_man >= 0 AND auto_man <= 1)
    "tb_dados_maquina_no_run_check" CHECK (no_run >= 0 AND no_run <= 1)
    "tb_dados_maquina_power_check" CHECK (power >= 0 AND power <= 1)
    "tb_dados_maquina_run_cmd_check" CHECK (run_cmd >= 0 AND run_cmd <= 1)
    "tb_dados_maquina_status_check" CHECK (status >= 0 AND status <= 1)

code_simatic=> 

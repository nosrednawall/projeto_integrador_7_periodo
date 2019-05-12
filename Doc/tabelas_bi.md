# Tabelas dimensão Code Simatic

## Tabelas
### Campos:
```sh
                     Lista de relações
 Esquema |           Nome           |   Tipo    |   Dono   
---------+--------------------------+-----------+----------
 public  | hibernate_sequence       | sequência | anderson
 public  | tb_dados_maquina         | tabela    | anderson
 public  | tb_funcionamento_maquina | tabela    | anderson
 public  | tb_perfil                | tabela    | anderson
 public  | tb_perfil_tb_permissao   | tabela    | anderson
 public  | tb_permissao             | tabela    | anderson
 public  | tb_power_maquina         | tabela    | anderson
 public  | tb_status_maquina        | tabela    | anderson
 public  | tb_usuario               | tabela    | anderson
(9 registros)
```

### Json com valores enviados pela máquina
```sh
    {
       "speedPV":1500,
       "power":100,
       "noRun":0,
       "autoMan":0,
        "runCmd":1,
        "status":1
     }
```
---------------------------------------------

## Tabela fato:
```sh
                    Tabela "public.tb_dados_maquina"
  Coluna  |            Tipo             | Collation | Nullable | Default 
----------+-----------------------------+-----------+----------+---------
 id       | bigint                      |           | not null | 
 auto_man | integer                     |           | not null | 
 data     | timestamp without time zone |           |          | 
 no_run   | integer                     |           | not null | 
 power    | integer                     |           | not null | 
 run_cmd  | integer                     |           | not null | 
 speed_pv | integer                     |           | not null | 
 status   | integer                     |           | not null | 
Índices:
    "tb_dados_maquina_pkey" PRIMARY KEY, btree (id)
Restrições de verificação:
    "tb_dados_maquina_auto_man_check" CHECK (auto_man >= 0 AND auto_man <= 1)
    "tb_dados_maquina_no_run_check" CHECK (no_run >= 0 AND no_run <= 1)
    "tb_dados_maquina_power_check" CHECK (power <= 100 AND power >= 0)
    "tb_dados_maquina_run_cmd_check" CHECK (run_cmd >= 0 AND run_cmd <= 1)
    "tb_dados_maquina_speed_pv_check" CHECK (speed_pv >= 0 AND speed_pv <= 1500)
    "tb_dados_maquina_status_check" CHECK (status >= 0 AND status <= 1)
```

## Tabela dimensão: Status maquina
```sh
                   Tabela "public.tb_status_maquina"
 Coluna |            Tipo             | Collation | Nullable | Default 
--------+-----------------------------+-----------+----------+---------
 id     | bigint                      |           | not null | 
 data   | timestamp without time zone |           |          | 
 no_run | integer                     |           | not null | 
 status | integer                     |           | not null | 
Índices:
    "tb_status_maquina_pkey" PRIMARY KEY, btree (id)
Restrições de verificação:
    "tb_status_maquina_no_run_check" CHECK (no_run >= 0 AND no_run <= 1)
    "tb_status_maquina_status_check" CHECK (status >= 0 AND status <= 1)


```

## Tabela dimensão: Power maquina
```sh
                    Tabela "public.tb_power_maquina"
  Coluna  |            Tipo             | Collation | Nullable | Default 
----------+-----------------------------+-----------+----------+---------
 id       | bigint                      |           | not null | 
 data     | timestamp without time zone |           |          | 
 power    | integer                     |           | not null | 
 speed_pv | integer                     |           | not null | 
Índices:
    "tb_power_maquina_pkey" PRIMARY KEY, btree (id)
Restrições de verificação:
    "tb_power_maquina_power_check" CHECK (power <= 100 AND power >= 0)
    "tb_power_maquina_speed_pv_check" CHECK (speed_pv >= 0 AND speed_pv <= 1500)
```

## Tabela dimensão: Funcionamento maquina
```sh
                Tabela "public.tb_funcionamento_maquina"
  Coluna  |            Tipo             | Collation | Nullable | Default 
----------+-----------------------------+-----------+----------+---------
 id       | bigint                      |           | not null | 
 auto_man | integer                     |           | not null | 
 data     | timestamp without time zone |           |          | 
 power    | integer                     |           | not null | 
 run_cmd  | integer                     |           | not null | 
 speed_pv | integer                     |           | not null | 
Índices:
    "tb_funcionamento_maquina_pkey" PRIMARY KEY, btree (id)
Restrições de verificação:
    "tb_funcionamento_maquina_auto_man_check" CHECK (auto_man >= 0 AND auto_man <= 1)
    "tb_funcionamento_maquina_power_check" CHECK (power <= 100 AND power >= 0)
    "tb_funcionamento_maquina_run_cmd_check" CHECK (run_cmd >= 0 AND run_cmd <= 1)
    "tb_funcionamento_maquina_speed_pv_check" CHECK (speed_pv >= 0 AND speed_pv <= 1500)

```

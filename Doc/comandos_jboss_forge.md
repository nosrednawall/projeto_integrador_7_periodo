## Passo 1 - Instalar o Jboss Forge

* Siga os comandos no [site](https://forge.jboss.org/download) ou busque no marketplace do Eclipse por JbossTools

## Passo 2 - Criando o projeto

* Inicie o terminal do Jboos Forge Apertando o Atalho Control+3 e escrevendo Forge, após isso aperte Enter. 

* execute no terminal:

```sh
$ cd <your folder for develop>

$ project-new --named projeto-integrador-7-periodo --top-level-package org.iel.projetointegrador.setimoperiodo --final-name  --type war

$ jpa-setup --provider Hibernate --db-type POSTGRES --data-source-name java:projetointegrador_ds

jpa-new-entity --named Usuario
jpa-new-field --named nomeCompleto --type String --not-nullable
jpa-new-field --named cpf --type String --not-nullable --length 15
jpa-new-field --named email --type String --not-nullable
jpa-new-field --named senha --type String --not-nullable
jpa-new-field --named sexo --type boolean --not-nullable
jpa-new-field --named setor --type String --not-nullable
jpa-new-field --named ramal --type String --not-nullable --length 5
jpa-new-field --named dataCriacao --type java.util.Date  --temporal-type
jpa-new-field --named status --type boolean --not-nullable

jpa-new-field --named perfil --type com.querocomprar.cupons.model.Perfil --relationship-type Many-to-One


constraint-add --on-property nomeCompleto --constraint NotNull
constraint-add --on-property cpf --constraint NotNull
constraint-add --on-property email --constraint NotNull
constraint-add --on-property senha --constraint NotNull
constraint-add --on-property sexo --constraint NotNull
constraint-add --on-property setor --constraint NotNull
constraint-add --on-property ramal --constraint NotNull
constraint-add --on-property dataCriacao --constraint NotNull
constraint-add --on-property status --constraint NotNull

jpa-new-entity --named Permissao
jpa-new-field --named nome --type String --not-nullable
jpa-new-field --named descricao --type String --not-nullable

constraint-add --on-property nome --constraint NotNull
constraint-add --on-property descricao --constraint NotNull

jpa-new-entity --named Perfil
jpa-new-field --named nome --type String --not-nullable
jpa-new-field --named descricao --type String --not-nullable
jpa-new-field --named status --type boolean --not-nullable

constraint-add --on-property nome --constraint NotNull
constraint-add --on-property descricao --constraint NotNull
constraint-add --on-property status --constraint NotNull


jpa-new-field --named saldo --type com.querocomprar.cupons.model.Transacao --relationship-type One-to-Many

jpa-new-entity --named ChamadoSuporte
jpa-new-field --named titulo --type String --not-nullable
jpa-new-field --named descricaoFalha --type String --not-nullable --length 2000
jpa-new-field --named anexos --type com.querocomprar.cupons.model.ArquivosAnexos --relationship-type One-to-Many
jpa-new-field --named status --type boolean --not-nullable
jpa-new-field --named resposta --type String --length 2000
jpa-new-field --named dataCriacao --type java.util.Date  --temporal-type
jpa-new-field --named dataFechamento --type java.util.Date  --temporal-type
jpa-new-field --named usuario --type com.querocomprar.cupons.model.Usuario --relationship-type Many-to-One

<!-- Criar enum na mão -->


jpa-generate-daos-from-entities --targets com.querocomprar.cupons.model.*
rest-generate-endpoints-from-entities --targets com.querocomprar.cupons.model.*

```
 

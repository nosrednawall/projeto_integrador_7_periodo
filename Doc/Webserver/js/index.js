/**Variáveis globais */
let enderecoServidor = 'http://0.0.0.0';
let isLigado = false;
let isDadosFalsos = true ;
let contadorNoRun = 0;
let isInicializado = false;

/**Variáveis dos campos do html */
let contadorHtml = document.getElementById('idNoRun');

/**json com os dados da maquina */
let jsonDados = {
    "speedPV":'0',
    "power":'0',
    "noRun":'0',
    "autoMan":'0',
    "runCmd":'0',
    "status":'0'
};

var gg1 = new JustGage({
    id: "gg1",
    formatNumber: true,
    counter: true,
    title: "Velocidade",	
    label: "RPM",	
    levelColors: ["#00fff6","#ff00fc","#1200ff"],				
    // textRenderer: textoValor,
    titleFontColor: "red",				
    valueFontColor: "blue",
    labelFontColor: "black"	
    });

function atualizaGrafico(valor){
    gg1.refresh(valor);
}
/**Referente ao gráfico da tela */
document.addEventListener("DOMContentLoaded", function(event) {

    gg1 = new JustGage({
        id: "gg1",
        formatNumber: true,
        counter: true,
        title: "Velocidade",	
        label: "RPM",	
        levelColors: ["#00fff6","#ff00fc","#1200ff"],				
        // textRenderer: textoValor,
        titleFontColor: "red",				
        valueFontColor: "blue",
        labelFontColor: "black"	
        });
    
    document.getElementById('gg1_refresh').addEventListener('click', function() {
        let valor = parseInt(document.getElementById('idPower').value);
        if( valor > -1 && valor < 101){
            document.getElementById('alerta').hidden = true;
            jsonDados.power = valor;
            jsonDados.speedPV = (valor * 1500.0 ) / 100.0;
            document.getElementById('Speed_PV').innerHTML = jsonDados.speedPV+"RPM";
            gg1.refresh(parseInt(jsonDados.speedPV));
            document.getElementById('idPower').value = jsonDados.power;
        }else{
            $().ready(function() {
                setTimeout(function () {
                    document.getElementById('alerta').innerHTML = "O valor "+ valor +" para a potência é inválido";
                    document.getElementById('alerta').hidden = false;
                }, 1000); // O valor é representado em milisegundos.
            });
            document.getElementById('idPower').value = jsonDados.power;
        } 
        return false;
    });
});

/**funcao master */

document.addEventListener("DOMContentLoaded", function(event){

    $.ajaxSetup({ cache: false });
        setInterval(function() {

            var d = new Date();
            var n = d.toLocaleDateString();
            var m = d.toLocaleTimeString();
            $('#DateNow').text(m);

            enviaJson(jsonDados);
            
        },1000);			
}, false);


/**funcao que altera o json apartir de ligar ou desligar a máquina */
function alteraLigaDesliga(){
    isLigado = document.getElementById('ligarDesligar').value == 0 ? false : true;
    console.log(isLigado);
    if(isLigado){

        document.getElementById('autoMan').disabled = false;
        document.getElementById('runCmd').disabled = false;
        document.getElementById('idPower').disabled = false;

        if(!isInicializado){
            jsonDados.speedPV = 1500;
            jsonDados.power = 100;
            jsonDados.noRun = 0;
            jsonDados.autoMan = 1;
            jsonDados.runCmd = 0;
            jsonDados.status = 1;
    
            document.getElementById('idPower').value = jsonDados.power;
            document.getElementById("idPower").disabled = true;

            atualizaGrafico(parseInt(jsonDados.speedPV));

            isInicializado = true;
        }else{
            //atualizo o json com os dados da tela
            jsonDados.speedPV = ((document.getElementById('idPower').value) * 1500.0 ) / 100.0;
            jsonDados.power = document.getElementById('idPower').value;
            jsonDados.noRun = contadorNoRun;
            jsonDados.autoMan = autoMan == 1 ? 1 : 0;
            jsonDados.runCmd = runCmd == 1 ? 1 : 0;
            jsonDados.status = 1;
        }
        document.getElementById('Status_Run').innerHTML = "Running";
    } else {
        document.getElementById('autoMan').disabled = true;
        document.getElementById('runCmd').disabled = true;
        document.getElementById('idPower').disabled = true;
        contadorNoRun++;

        //atualizo o json zerando os dados
        jsonDados.speedPV = 0;
        jsonDados.power = 0;
        jsonDados.noRun = contadorNoRun;
        jsonDados.autoMan = 0;
        jsonDados.runCmd =  0;
        jsonDados.status = 0;

        atualizaGrafico(parseInt(jsonDados.speedPV));
        document.getElementById('idNoRun').value = contadorNoRun;
        document.getElementById('Status_Run').innerHTML = "Stop";
        console.log("a maquina foi parada " + contadorNoRun + ' vezes');
    }
    console.log('alterando via alteraLigaDesliga '+ JSON.stringify(jsonDados));
}

function salvaEnderecoServidor(){
    enderecoServidor = document.getElementById('endereco_servidor').value;

    if(enderecoServidor == ''){
        document.getElementById('alerta').hidden = false;
        
    }else{
        document.getElementById('alerta').hidden = true;
    }
}

function alteraEscolhaDados(){
    isDadosFalsos = document.getElementById('escolhaDados').value == 0 ? false : true;
}

/**Função altera o funcionamento de automático para manual e vice versa */
function alteraFuncionamento(id){
    var runCmd = document.getElementById('runCmd').value;
    var autoMan = document.getElementById('autoMan').value;

    if(id == 'autoMan'){
        /** se o auto man for igual a 1 o sum cmd recebe 0 se não ococrre o contrário */
        document.getElementById("runCmd").selectedIndex     = autoMan == 1 ? 0 : 1;
        /**se o auto man for igual a 1 o power recebera 0 */
        document.getElementById("idPower").disabled         = autoMan == 1 ? true : false;
    }else{
        document.getElementById("autoMan").selectedIndex    = runCmd == 1 ? 0 : 1;
        document.getElementById("idPower").disabled         = autoMan == 1 ? false : true;
    }
    //atualiza os valores do json
    jsonDados.autoMan = autoMan == 1 ? 1 : 0;
    jsonDados.runCmd = runCmd == 1 ? 1 : 0;

    console.log('alterando via alteraFuncionamento '+JSON.stringify(jsonDados));
}


// Função envia o json para o servidor tal
function enviaJson(json){
    // Exemplo de requisição POST
    var ajax = new XMLHttpRequest();

    //esse é o cara
    var json_object = JSON.stringify(json);

    var servidor = "http://45.225.163.18:8080/";
    var localhost = "http://0.0.0.0:8080/";
    var rede_local = "http://192.168.0.11:8080/"
    
    // Seta tipo de requisição: Post e a URL da API
    ajax.open("POST", localhost+"code-simatic/rest/dados-maquina", true);
    ajax.setRequestHeader("Content-type", "application/json;charset=UTF-8");

    // Seta paramêtros da requisição e envia a requisição
    ajax.send(json_object);
    console.log(json_object);
    // Cria um evento para receber o retorno.
    ajax.onreadystatechange = function() {
        // Caso o state seja 4 e o http.status for 200, é porque a requisiçõe deu certo.
        if (ajax.readyState == 4 && ajax.status == 200) {
            var data = ajax.responseText;
        // Retorno do Ajax
            console.log(data);
        }else{
            console.log(ajax.response);
        }
    };

}

//Função captura os dados de dentro do arquivo dentro do servidor, não funciona sem estar dentro do servidor
function request_json_arquivo(){
    url="IOServer.htm";

    var json_arquivo = $.getJSON(url, function(result){
                	$('#Speed_PV').text(result["Speed_PV"]+ " RPM");
        $('#NoRun').text(result["No_Run"]);
        
        document.getElementById('Power').value = result["Power"];	
        
        // Enable or disable input Power					
        if(result["Auto_Man"]==1){
            val = 69.0;
            document.getElementById('Power').disabled = true;
            document.getElementById('Power').value = val;
            sdata=escape('"IOMotor".Power')+'='+val;
            //alert(sdata);
            $.post(url,sdata,function(result3){});
            $('#Power').attr("disabled", "disabled");
        }
        else{
            document.getElementById('Power').disabled = false;
            $('#Power').removeAttr('disabled');
        }
        
            							
        if(result["Status"]==0){
            $('#Status_Run').text("Stop");
            }
        else{
            $('#Status_Run').text("Running");
            }
        pos3.refresh(result["Speed_PV"].trim());
    });

    var d = new Date();
    var n = d.toLocaleDateString();
    var m = d.toLocaleTimeString();
    $('#DateNow').text(m);
    return json_arquivo;
}

function atualizaTelaComDadosObtidos(result){

    document.getElementById('Power').value = result["Power"];	
        
    // Enable or disable input Power					
    if(result["Auto_Man"]==1){
        val = 69.0;
        document.getElementById('Power').disabled = true;
        document.getElementById('Power').value = val;
        sdata=escape('"IOMotor".Power')+'='+val;
        //alert(sdata);
        $.post(url,sdata,function(result3){});
        $('#Power').attr("disabled", "disabled");
    }
    else{
        document.getElementById('Power').disabled = false;
        $('#Power').removeAttr('disabled');
    }
    
            							
    if(result["Status"]==0){
        $('#Status_Run').text("Stop");
        }
    else{
        $('#Status_Run').text("Running");
        }
    pos3.refresh(result["Speed_PV"].trim());

    var d = new Date();
    var n = d.toLocaleDateString();
    var m = d.toLocaleTimeString();
    $('#DateNow').text(m);
    return json_arquivo;
}

//cemitério de funções

//função captura os dados da tela DOM, mas não é muito interessante pois já consigo capturar os dados apenas alterando o javascript
// function gerarJson(){

    // var dado_status_run = document.getElementById('Status_Run').innerHTML;
    // console.log(dado_status_run);
    // var dado_no_run = document.getElementById('NoRun').innerHTML;
    // var dado_speed_pv = document.getElementById('Speed_PV').innerHTML;

    // // var jsonDados = '{"dados" : [{ "status_run": ' + dado_status_run + ' },{ "no_run":'+ dado_no_run +' },{ "speed_pv":'+ dado_speed_pv +' }]}';
    // var dados = { "status_run": dado_status_run,"no_run":dado_no_run,"speed_pv":dado_speed_pv};
    
    // console.log(dados);
    // var objeto_json = JSON.parse(dados);

    // console.log(objeto_json);
// }

// Função que gera um número aleatório entre dois números informados
// function getRandomArbitrary(min, max) {
//     return Math.random() * (max - min) + min;
// }

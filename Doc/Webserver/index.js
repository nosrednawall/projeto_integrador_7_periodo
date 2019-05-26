// super função
(function() {
    var $$ = function(selector) {
        return Array.prototype.slice.call(document.querySelectorAll(selector));
    }

    document.addEventListener("DOMContentLoaded", function() {
        var checkbox;
        $$(".switch").forEach(function(switchControl) {
            if (switchControl.className === ("switch on")) {
                switchControl.lastElementChild.checked = true;
            }
            switchControl.addEventListener("click", function toggleSwitch() {
                if (switchControl.className === "switch on") {
                    switchControl.className = 'switch off';
                } else {
                    switchControl.className = ("switch on");
                }
                checkbox = switchControl.lastElementChild;

                checkbox.checked = !checkbox.checked;
                var url = "/" + window.location.pathname.substr(1);
                url="IOServer.htm";
                var ival = +checkbox.checked;
                var sdata = checkbox.id;
                //alert(ival);						
                sdata=escape(sdata)+'='+ival;
                //alert(sdata);

                $.post(url,sdata,function(result,status){});
                //alert(status);
            }, false);
    });
        
    var pos3 = new JustGage({
        id: "pos3",
        value: 0,
        min: 0,
        max: 1500,
        title: "Speed",	
        label: "RPM",	
        levelColors: ["#00fff6","#ff00fc","#1200ff"],				
        textRenderer: customValue,
        titleFontColor: "red",				
        valueFontColor: "blue",
        labelFontColor: "black"				
    });
                        
    document.getElementById('Power').addEventListener('keypress', function(event1) {				
        if(event1.key=="Enter"){
            var val = document.getElementById('Power').value;
  
            $('#NoRun').text(x);					
            var tmp = $('#Power').val();
            var max_val = document.getElementById('Power').max;
            var min_val = document.getElementById('Power').min;						
            if(val>100.0){
                val = 100.0;
                alert("Value must be between 0.0 and 100.0");
                document.getElementById('Power').value = val;
                }
            else if(val<0.0){
                val = 0.0;
                alert("Value must be between 0.0 and 100.0");
                document.getElementById('Power').value = val;							
            }
                       			
            url="IOServer.htm";
            name='"IOMotor".Power';
            //val=$('input[id=Power]').val();
            sdata=escape(name)+'='+val;
            //alert(sdata);
            $.post(url,sdata,function(result2){});
         			                			
            url2="IOServer.htm";
            name='"IOMotor".Power';
            //val=$('input[id=Power]').val();
            sdata=escape(name)+'='+val;
            //alert(sdata);
            $.post(url,sdata,function(result2){});
        }
        return false;
    },false);	

    //atualiza os dados na tela e envia o json
    $.ajaxSetup({ cache: false });
        setInterval(function() {
            //caso esteja no servidor utilize essa função
            // request_json_arquivo();

            //caso não esteja utilize essa
            var json = gerador_dados_na_tela();

            enviaJson(json);
        },1000);
            	
        function customValue(val) {				
            if (val < 500) {
                return 'low';
            } else if (val <= 1200) {
                return 'normal';
            } else {					
                return 'high';
            }
        }				
    }, false);
})();


// Função que gera um número aleatório inteiro entre dois números informados
function getRandomInt(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min)) + min;
    }

// Função cria um json com os valores aleatórios
function gerar_dados_json(){
    
    var Speed_PV = 0;
    var Power = 0;
    var No_Run = 1;
    var Auto_Man = 0;
    var Run_CMD = 0; 
    var Status = 0;

    var dados ={
        "speedPV":Speed_PV,
        "power":Power,
        "noRun":No_Run,
        "autoMan":Auto_Man,
        "runCmd":Run_CMD,
        "status":Status
    };

    return dados;
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

// Função atualiza os componentes da tela com os valores gerados aleatórios
function gerador_dados_na_tela(){
    var json_gerado = gerar_dados_json();

    if(json_gerado.Auto_Man==1){
        val = 69.0;
        document.getElementById('Power').disabled = true;
        document.getElementById('Power').value = val;
    }else{
        document.getElementById('Power').disabled = false;
    }						
    
    if(json_gerado.Status ==0){
        document.getElementById('Status_Run').innerHTML = "Stop";
    }else{
        document.getElementById('Status_Run').innerHTML = "Running";
    }

    var d = new Date();
    var n = d.toLocaleDateString();
    var m = d.toLocaleTimeString();
    $('#DateNow').text(m);
return json_gerado;
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

//cemitério de funções

//função captura os dados da tela DOM, mas não é muito interessante pois já consigo capturar os dados apenas alterando o javascript
function gerarJson(){

    var dado_status_run = document.getElementById('Status_Run').innerHTML;
    console.log(dado_status_run);
    var dado_no_run = document.getElementById('NoRun').innerHTML;
    var dado_speed_pv = document.getElementById('Speed_PV').innerHTML;

    // var jsonDados = '{"dados" : [{ "status_run": ' + dado_status_run + ' },{ "no_run":'+ dado_no_run +' },{ "speed_pv":'+ dado_speed_pv +' }]}';
    var dados = { "status_run": dado_status_run,"no_run":dado_no_run,"speed_pv":dado_speed_pv};
    
    console.log(dados);
    // var objeto_json = JSON.parse(dados);

    // console.log(objeto_json);
}

// Função que gera um número aleatório entre dois números informados
function getRandomArbitrary(min, max) {
    return Math.random() * (max - min) + min;
}

	function mascara(o,f){
	    v_obj=o;
	    v_fun=f;
	    setTimeout("execmascara()",1);
	}

	function execmascara(){
	    v_obj.value=v_fun(v_obj.value);
	}
	
	function soNumeros(v){
	    return v.replace(/\D/g,"");
	}
	
	function alfanumericos(v){
	    return v.replace(/[\W_]/,"");
	}
	
	function valorMonetario(v){
        v=v.replace(/\D/g,""); //Remove tudo o que não é dígito
        v=v.replace(/^([0-9]{3}\.?){3}-[0-9]{2}$/,"$1.$2");
        v=v.replace(/(\d{1})(\d{14})$/,"$1.$2") ; //coloca ponto antes dos últimos 14 digitos
        v=v.replace(/(\d{1})(\d{11})$/,"$1.$2") ; //coloca ponto antes dos últimos 11 digitos
        v=v.replace(/(\d{1})(\d{8})$/,"$1.$2") ; //coloca ponto antes dos últimos 8 digitos
        v=v.replace(/(\d{1})(\d{5})$/,"$1.$2");  //coloca ponto antes dos últimos 5 digitos
        v=v.replace(/(\d)(\d{2})$/,"$1,$2"); //Coloca uma vírgula antes dos 2 últimos digitos
        
        return v;
    }
	
	function data(dat)
	{
		novo = "";
		novo2 = "";

		for (i=0; i<dat.value.length; i++)
		{
			c = dat.value.charAt(i);
			if ( c >= '0' && c <= '9' ) novo += c;
		}

	//FORMATA
		for (i = 0; i < novo.length; i++)
		{
			c = novo.charAt(i);
			novo2 = novo2 + c;
			if (i == 2) novo2 = novo2.substr(0,2) + '/' + novo2.substr(2);
			if (i == 4) novo2 = novo2.substr(0,5) + '/' + novo2.substr(5);
			if (i >= 7) break;
		}
		dat.value = novo2;
	}

	function dataValida(dat)
	{
		d = dat.value;
		if (d.length != 10) return false;

		if(d.indexOf('/') != 2) return false;
		if(d.indexOf('/',3) != 5) return false;

		dia = d.substring(0,2);
		mes = d.substring(3,5);
		ano = d.substring(6,10);

		if (ano < 1900) return false;

		if (isNaN(++dia)) return false; --dia;
		if (isNaN(++mes)) return false; --mes;
		if (isNaN(++ano)) return false; --ano;

		m = Math.floor(ano/1000);
		c = Math.floor((ano%1000)/100);

		fev = (ano%4 != 0)?28:(ano%100 == 0 && c != 0)?28:(ano%1000 == 0 && m%2 != 0)?28:29;
		meses = new Array(31,fev,31,30,31,30,31,31,30,31,30,31);

		if (mes < 1 || mes > 12) return false;
		if (dia < 1 || dia > meses[mes-1]) return false;

		return true;
	}
	
	var curDt = new Date();
    function disablementFunction(day){
        if (day.isWeekend) return false;
        /*if (curDt==undefined){
            curDt = day.date.getDate();
        }*/
        if(curDt.getFullYear() < day.date.getFullYear())
        	return true;
        else if(curDt.getFullYear() == day.date.getFullYear()){
        	if(curDt.getMonth() < day.date.getMonth())
        		return true;
        	else if(curDt.getMonth() == day.date.getMonth()){
        		if(curDt.getDate() <= day.date.getDate())
        			return true;
        		else
        			return false;
        	}
        	else
        		return false;
        }
        else
        	return false;
    }
    
//#############################################################
function modalWin(file,width,height){
	if(file !=null) {
		if(window.showModalDialog){
			window.showModalDialog(file,"modal","dialogWidth:"+width+"px;dialogHeight:"+height+"px");
			return true;
		}else{
			window.open(file,'modal','width='+width+',height='+height+',toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=no,resizable=no,modal=yes,location=no');
			return true;
		}
	}
}

function modalWinParameter(file,width,height, modo, chave){
	if(file == null || file == "") return true;
	var params = "";
	if(modo != null || modo != '') {
		params = params + "modo=" + modo;
	}
	if(chave != null || chave != "") {
		if (params != "") {
			params = params + "&";
		}
		params = params + "chave=" + chave;
	}
	if (params != "") {
		file = file + "?" + params;
	}
	modalWin(file, width, height);
	return true;
}

// #############################################################
/*
 * Window.open nova aba
 * 
 */

function openTab(file) {
	if (file != null){
		myWindow = window.open(file, "_blank", 'toolbar=no,menubar=yes,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no,modal=no,location=no');
		myWindow.focus();
		return true;
	}
}

// #############################################################
//

	/*
	 * p em ordem alfab√©tica por nome de elementos de form
	 */
	
	/*
	 * SELECT MULTIPLE
	 */
	
	// pelo menos 1 opcao selecionada no campo com nome = nome_campo
	function algum_selecionado(f, nome_campo)
	{
		var sel = eval('f.elements.' + nome_campo);
	
		for (var i=0; i<sel.options.length; i++)
		{
			if (sel.options[i].selected) return true;
		}
	
		return false;
	}
	
	function unico_checkbox_marcado(f, nome_campo) {
		var cb = eval('f.elements.' + nome_campo);
		var qtde = 0;
		for(var i=0; i < cb.length; i++) {
			if(cb[i].type == 'checkbox' && cb[i].checked) {
				qtde = qtde + 1;
			}
		}
		if(qtde == 1) {
			return true;
		}
		return false;
	}
	
	function unico_checkbox_marcado(f) {
		var elementos = f.elements;
		var qtde = 0;
		for(var i=0; i < elementos.length; i++) {
			if(elementos[i].type == 'checkbox' && elementos[i].checked) {
				qtde = qtde + 1;
			}
		}
		if(qtde == 1) {
			return true;
		}
		return false;
	}
	
	/*
	 * CHECKBOX
	 */
	
	// pelo menos 1 marcado com o nome = nome_campo
	function algum_marcado(f, nome_campo)
	{
		var cb = eval('f.elements.' + nome_campo);
	
		if (cb.type == 'checkbox') {
			if (cb.type == 'checkbox' && cb.checked) return true;
		}
		else { // array
			for (var i=0; i<cb.length; i++) {
				if (cb[i].type == 'checkbox' && cb[i].checked) return true;
			}
		}
	
		return false;
	}
	
	// pelo menos 1 marcado com o nome iniciado por prefixo
	function algum_marcado2(f, prefixo)
	{
		var elementos = f.elements;
	
		for (var i=0; i<elementos.length; i++) {
			if (elementos[i].type == 'checkbox' && elementos[i].name.indexOf(prefixo) == 0 && elementos[i].checked) {
				return true;
			}
		}
	
		return false;
	}
	
	// (des)marcar todos
	function des_marcar_todos(f, marcar, nome_campo)
	{
		var cb  = eval('f.elements.' + nome_campo);
	
		if (cb.type == 'checkbox') {
			cb.checked = marcar;
		}
		else { // array
			for (var i=0; i<cb.length; i++) {
				if (cb[i].type == 'checkbox') {
					cb[i].checked = marcar;
				}
				else
					break;
			}
		}
	}
	
	function des_marcar_todos(f, marcar)
	{
		var elementos = f.elements;
	
		for (var i=0; i<elementos.length; i++) {
			if (elementos[i].type == 'checkbox') {
				elementos[i].checked = marcar;
			}
		}
	
		return false;
	}
	
	/*
	 * TEXTAREA
	 */
	function limite(text_area, limite_maximo) 
	{
		if (text_area.value.length > limite_maximo) {
			text_area.value = text_area.value.substring(0, limite_maximo);
			alert('Este campo deve ter at√© ' + limite_maximo + ' caracteres.');
			return;
		}
	}
	/**
	 	Tratamento de Abas
	 */
	 var tabLinks = new Array();
	 var contentDivs = new Array();
	 
	 function abas() {

	      // Grab the tab links and content divs from the page
		  var tabList =  document.getElementById('tabs');
		  if(tabList == null) {
			  return;
		  }
	      var tabListItems = tabList.childNodes;
	      for ( var i = 0; i < tabListItems.length; i++ ) {
	        if ( tabListItems[i].nodeName == "LI" ) {
	          var tabLink = getFirstChildWithTagName( tabListItems[i], 'A' );
	          var id = getHash( tabLink.getAttribute('href') );
	          tabLinks[id] = tabLink;
	          contentDivs[id] = document.getElementById( id );
	        }
	      }

	      // Assign onclick events to the tab links, and
	      // highlight the first tab
	      var i = 0;

	      for ( var id in tabLinks ) {
	        tabLinks[id].onclick = showTab;
	        tabLinks[id].onfocus = function() {this.blur();};
	        if ( i == 0 ) 
	        	tabLinks[id].className = 'active';
	        i++;
	      }

	      // Hide all content divs except the first
	      var i = 0;

	      for ( var id in contentDivs ) {
	        if ( i != 0 ) 
	        	contentDivs[id].className = 'tabContent hide';
	        i++;
	      }
	      
	    }
	 
	 function showTab() {
	      var selectedId = getHash( this.getAttribute('href') );

	      // Highlight the selected tab, and dim all others.
	      // Also show the selected content div, and hide all others.
	      for ( var id in contentDivs ) {
	        if ( id == selectedId ) {
	          tabLinks[id].className = 'active';
	          contentDivs[id].className = 'tabContent';
	        } else {
	          tabLinks[id].className = '';
	          contentDivs[id].className = 'tabContent hide';
	        }
	      }

	      // Stop the browser following the link
	      return false;
	    }
	 
	  function getFirstChildWithTagName( element, tagName ) {
	      for ( var i = 0; i < element.childNodes.length; i++ ) {
	        if ( element.childNodes[i].nodeName == tagName ) 
	        	return element.childNodes[i];
	      }
	    }
	 
	  function getHash( url ) {
	      var hashPos = url.lastIndexOf ( '#' );
	      return url.substring( hashPos + 1 );
	    }
	  
/* Menu e Sub-menus------------------------------------------ */
	  
	  function menuHorizontal() { 

		   var navItems = document.getElementById("barra").getElementsByTagName("li"); 
		        
		   for (var i=0; i< navItems.length; i++) { 
		          if((navItems[i].className == "menuvertical") || (navItems[i].className == "submenu")) 
		          { 
		                 if(navItems[i].getElementsByTagName('ul')[0] != null) 
		                 { 
		                        navItems[i].onmouseover=function() {this.getElementsByTagName('ul')[0].style.display="block";this.style.
		backgroundColor = "#f9f9f9";};
		                        navItems[i].onmouseout=function() {this.getElementsByTagName('ul')[0].style.display="none";this.style.
		backgroundColor = "#FFFFFF";};
		                 } 
		          } 
		   } 

		} 
	  
	  function altera_tipo_pessoa(campord)
	  {
	  	if (campord.value == '1') //PF
	  	{
	  		document.getElementById('tb_pf').style.display = 'table-row-group';
	  		document.getElementById('tb_pj').style.display = 'none';
	  	}
	  	else if (campord.value == '2') //PJ
	  	{
	  		document.getElementById('tb_pf').style.display = 'none';
	  		document.getElementById('tb_pj').style.display = 'table-row-group';
	  	}
	  }
	  
	  function isNumeric(valor)
	  {
	      validChar = '0123456789.,-';
	      for(var i=0;i<valor.length;i++)
	          if(validChar.indexOf(valor.substr(i,1))<0)
	              return false;
	      return true;
	  }

	  function addLoadEvent(func) {
			var oldonload = window.onload;
			if (typeof window.onload != 'function') {
				window.onload = func;
			} else {
				window.onload = function() {
					if (oldonload) {
						oldonload();
					}
					func();
				};
			}
		}
	  
	  navigator.sayswho= (function(){
		    var N= navigator.appName, ua= navigator.userAgent, tem;
		    var M= ua.match(/(opera|chrome|safari|firefox|msie)\/?\s*(\.?\d+(\.\d+)*)/i);
		    if(M && (tem= ua.match(/version\/([\.\d]+)/i))!= null) M[2]= tem[1];
		    M= M? [M[1], M[2]]: [N, navigator.appVersion, '-?'];

		    return M;
		})();
	  
	  function avisoMSIE() {		  
		  if(navigator.sayswho[0] == 'Chrome') {
			  return true;
		  } else return false;
	  }
	  
	  function setFocus() {
			document.getElementById('messages').focus();
	  }
	  
	  // FunÁ„o para converter para portuguÍs o calendar
	  
	  PrimeFaces.locales['pt_BR'] = {
			    closeText: 'Fechar',
			    prevText: 'Anterior',
			    nextText: 'Seguinte',
			    currentText: 'ComeÁo',
			    monthNames: ['Janeiro','Fevereiro','MarÁo','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
			    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun', 'Jul','Ago','Set','Out','Nov','Dez'],
			    dayNames: ['Domingo','Segunda','TerÁa','Quarta','Quinta','Sexta','S·bado'],
			    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','S·b'],
			    dayNamesMin: ['D','S','T','Q','Q','S','S'],
			    weekHeader: 'Semana',
			    firstDay: 0,
			    isRTL: false,
			    showMonthAfterYear: false,
			    yearSuffix: '',
			    timeOnlyTitle: "SÛ hoje",
			    timeText: 'Tempo',
			    hourText: 'Hora',
			    minuteText: 'Minuto',
			    secondText: 'Segundo',
			    ampm: false,
			    month: 'MÍs',
			    week: 'Semana',
			    day: 'Dia',
			    allDayText : 'Todo o Dia'
			};
(function(){
	window.undefined = window.undefined;
	window.isIE=!!(window.attachEvent && !window.opera);
	if (window.isIE) {
	  window.attachEvent('onunload', $_IEGC);
	  /* window.attachEvent('onload', $_IEGC); */
	}
	if ($_E){
		$_E_original=$_E;
	}

	if (!window.isIE && window.Node){
		Node.prototype.swapNode=function(node){
			var nextSibling=this.nextSibling;
			var parentNode=this.parentNode;
			node.parentNode.replaceChild(this,node);
			parentNode.insertBefore(node,nextSibling);
		};
	}
	if (!window.isIE && window.HTMLElement){
		HTMLElement.prototype.__defineGetter__("innerText", function(){	return this.textContent;});
	}

})();

function $_IEGC(){
	CollectGarbage();
}


var ECTableConstants={
	EMPTY_FUNCTION : function(){},
	EC_ID : "ec",
	ETI_ID : "eti",
	ETI_PAGE_FLAG : "eti_p",
	SORT_PREFIX : "_s_",
	FILTER_PREFIX : "_f_",
	ACTION : "a",
	FILTER_ACTION : "fa",
    CLEAR_ACTION  : "ca",

	PAGEFIELD_SUFFIX : "_p",
	EXPORT_IFRAME_SUFFIX : "_ecs_export_iframe",
	SHADOW_ROW : "_shadowRow",
	HIDE_HEADER_ROW : "_hideListRow",


	DEFALUT_ADD_TEMPLATE : "add_template",


	AJAX_ZONE_BEGIN : "_begin_ ",
	AJAX_ZONE_END : " _end_",
	AJAX_ZONE_PREFIX : "<!-- ECS_AJAX_ZONE_PREFIX_",
	AJAX_ZONE_SUFFIX : "_ECS_AJAX_ZONE_SUFFIX -->",
	MIN_COL_WIDTH : 10,
	SCROLLBAR_WIDTH :18,
	SCROLL_SPEED : 50,
	MIN_COLWIDTH : "30",
	AJAX_HEADER :['useAjaxPrep','true'],
	ROW_HIGHLIGHT_CLASS : "highlight",
	ROW_SELECTLIGHT_CLASS : "selectlight",
	DRAG_BUTTON_COLOR : "#3366ff",

	HEAD_ZONE_SUFFIX   : "_headZone",
	BODY_ZONE_SUFFIX   : "_bodyZone",
	FOOT_ZONE_SUFFIX	: "_footZone",

	LIST_HEIGHT_FIXED : window.isIE?0:0 ,
	LIST_WIDTH_FIXED : window.isIE?0:1 ,
	IE_WIDTH_FIX_A : 1,
	IE_WIDTH_FIX_B : 2,
	FF_WIDTH_FIX_A : -3,
	FF_WIDTH_FIX_B : -6,
	OFFSET_A		: 2
};



var $_E=function(){

  var elements = [];

  for (var i = 0; i < arguments.length; i++) {
    var element = arguments[i];
    if (typeof(element) == 'string') {
		var elemId=element;
		element = document.getElementById(elemId);
		if (element==null){
			element = document.getElementsByName(elemId);
			if (element.length>0){
				element=element[0];
			}else{
				element=null;
			}
		}
	}

    if (arguments.length == 1) {return element;}
    elements.push(element);
  }

  return elements;
};


var ECTableList={};

var ECTable=function(formid){


	var Me=this;

	Me.findAjaxZoneAtClient=false;


	Me.ETI_ID=ECTableConstants.ETI_ID;
	Me.ETI_PAGE_FLAG=ECTableConstants.ETI_PAGE_FLAG;

	Me.MIN_COL_WIDTH=80;

	Me.onLoad=null;

	if (!formid||formid==''){
		formid=ECTableConstants.EC_ID;
	}
	Me.id=formid;
	Me.EXPORT_IFRAME_ID=formid+ ECTableConstants.EXPORT_IFRAME_SUFFIX;
	Me.SHADOWROW_ID=formid+ECTableConstants.SHADOW_ROW;

	Me.ECForm=null;
	Me.ECMainContent=null;
	Me.selectedRow=null;

	Me.sortedColumn=null;
	Me.sortType="default";
	Me.sortedColumnHearderId=null;

	Me.afterFillForm=null;

	//Me.resizeWay="resizeSibling";
	Me.resizeWay="resizeTable";

	Me.listWidth=0;
	Me.isClassic=false;
	Me.canResizeColWidth=false;


	Me.useAjax=true;
	Me.doPreload=true;
	Me.doPreloadPrev=false;

	Me.doPrep="temp";
	Me.doPrepPrev="temp";


	Me.isDebug=false;



	Me.prepState={next : 0 ,prev : 0};
	Me.prepPage={next : 0 ,prev : 0};
	Me.prepareaName={};
	Me.pageFieldName=Me.id + ECTableConstants.PAGEFIELD_SUFFIX;

	Me.totalPagesFieldName=Me.id+"_totalpages";

	Me.prepareaName['next']=Me.id+"_ec_preparea_n";
	Me.prepareaName['prev']=Me.id+"_ec_preparea_p";

	Me.scrollList=false;
	Me.orgListHeight=0;
	Me.listHeight=0;
	Me.minHeight=0;
	Me.columnNum=0;

	ECTableList[formid]=Me;



	Me.buildPrepArea=function(){
		if (!Me.doPreload){
			return;
		}

		var hasPrepareaNext=document.getElementById(this.prepareaName['next']);
		if (!hasPrepareaNext){
			var ta=document.createElement("textarea");
			ta.id=this.prepareaName['next'];
			ta.disabled=true;
			ta.style.display="none";
			document.body.appendChild(ta);
		}


		var hasPrepareaPrev=document.getElementById(this.prepareaName['prev']);
		if (!hasPrepareaPrev){
			var tb=document.createElement("textarea");
			tb.id=this.prepareaName['prev'];
			tb.disabled=true;
			tb.style.display="none";
			document.body.appendChild(tb);
		}

		/* for Debug */
		if(Me.isDebug){
			ta=document.getElementById(this.prepareaName['next']);
			tb=document.getElementById(this.prepareaName['prev']);
			ta.disabled=false;
			ta.style.display="inline";
			ta.rows=10;
			ta.cols=50;
			tb.disabled=false;
			tb.style.display="inline";
			tb.rows=10;
			tb.cols=50;
		}

	};

	Me.goPage=function(){

		var newPageNO = $_E(Me.pageFieldName).value;

		var key=null;

		if(newPageNO==Me.prepPage['next'] && Me.prepState['next']==2){
			key='next';
		}else if(newPageNO== Me.prepPage['prev'] && Me.prepState['prev']==2 && Me.doPreloadPrev){
			key='prev';
		}

    	if (key!==null){

			try{
				var newhtml=$_E(Me.prepareaName[key]).value;

				if (newhtml==''){	$_E(Me.id).submit(); return;	}
				Me.ECMainContent.innerHTML=newhtml;
				Me.prepState[key]=0;
				Me.init();
				var originalRequest={};
				originalRequest.responseText=newhtml;
				if (Me.afterFillForm && typeof(Me.afterFillForm)=="function"){
					Me.afterFillForm(originalRequest);
				}
				window.setTimeout(Me.ajaxPrepSubmit,10);
				if (typeof(Me.onLoad)=="function"){
					Me.onLoad();
				}
				Me.handleResize();
				Me.hideWaitingBar();
			}catch(ex){
				$_E(Me.pageFieldName).value=newPageNO;
				Me.ajaxSubmit();
				/* $_E(Me.id).submit(); */
			}
    	}else{
	    	/* $_E(Me.id).submit(); */
			Me.ajaxSubmit();
    	}

 	};

	Me.dealResponse={
		'next'	: function(originalRequest){
			$_E(Me.prepareaName['next']).value =ECTableUtil.cutText(originalRequest.responseText,Me.id);
			Me.prepState['next']=2;
			Me.doingAjaxSubmit=false;

		},
		'prev'	: function(originalRequest){
			$_E(Me.prepareaName['prev']).value =ECTableUtil.cutText(originalRequest.responseText,Me.id);
			Me.prepState['prev']=2;
			Me.doingAjaxSubmit=false;
		}
	};


	Me.ajaxPrepSubmit=function(){
		if (!Me.doPreload){
			return;
		}
		Me.ajaxPrep(1);
		Me.ajaxPrep(-1);
	};

    Me.ajaxPrep=function(which){

		var key;

		if (which==1){
			key='next';
		}else if (which==-1 && Me.doPreloadPrev){
			key='prev';
		}else{
			return;
		}
		Me.prepState[key]=1;
		Me.prepPage[key]=$_E(Me.pageFieldName).value/1+which;
		if (Me.prepPage[key]<1 || Me.prepPage[key]>($_E(Me.totalPagesFieldName).value/1)) {
			 return;
		}
		$_E(Me.pageFieldName).value=Me.prepPage[key];


		Me.ajaxSubmit(Me.dealResponse[key],true);

		$_E(Me.pageFieldName).value=Me.prepPage[key]-which;
	};



	Me.doingAjaxSubmit=false;
	Me.ajaxSubmit=function(resfunc,asy,parameter){

		if (!Me.useAjax){

			Me.ECForm.submit();
			return;
		}

		if (!asy){
			asy=false;
		}
		if (!resfunc){
			resfunc=Me.fillForm;
		}
		if(!asy && Me.doingAjaxSubmit){
			/*
			alert("the last ajax request is not complete. try later.");
			return;
			*/
		}
		Me.doingAjaxSubmit=true;

		/* fix a prototype bug */
		//pars=pars+'&'+appPara;
		//pars=pars.replace(/(^|&)_=(&|$)/g,'$1'+'$2');
		//pars=pars.replace(/&+/g,'&');
		/* end of fix a prototype bug */
		//alert(url+"  "+pars)
		//ECTableUtil.formSubmit(Me.ECForm,resfunc,"post",asy,parameter);
		if (Me.findAjaxZoneAtClient===false){
			if (!parameter){
				parameter={};
			}
			if (typeof(parameter)=='string'){
				parameter+="&findAjaxZoneAtClient=false&";
			}else{
				parameter.findAjaxZoneAtClient="false";
			}

		}

		Form.request(Me.ECForm,{method:'post',
			requestHeaders:ECTableConstants.AJAX_HEADER,
			asynchronous: asy,
			parameters: parameter,
			onComplete :resfunc });


	};



	Me.fillForm=function(originalRequest){

		var newhtml=ECTableUtil.cutText(originalRequest.responseText,Me.id);
		if (newhtml==''){
			Me.hideWaitingBar();
			return;
		}
		if (newhtml.indexOf("Exception:")==0){
			Me.hideWaitingBar();
			Me.ECMainContent.innerHTML=newhtml;
			return;
		}

		Me.ECMainContent.innerHTML=newhtml;
		Me.init();

		if (Me.afterFillForm && typeof(Me.afterFillForm )=="function"){
			Me.afterFillForm(originalRequest);
		}

		Me.doingAjaxSubmit=false;

		Me.handleResize();
		Me.hideWaitingBar();


		/*
		Me.initList();
		if (typeof(Me.onLoad)=="function"){
			Me.onLoad();
		}
		*/
	};

	Me.currentShadowRowParent=null;
	Me.currentShadowEventSrc=null;
	Me.autoCloseOtherShadowRow=true;



	Me.getTotalPage=function(){
		return Me.ECForm[Me.totalPagesFieldName].value/1;
	};
	Me.getPageNo=function(){
		if (!Me.ECForm[Me.pageFieldName]){
			return 1;
		}
		return Me.ECForm[Me.pageFieldName].value/1;
	};
	Me.setPageNo=function(pageNo){
		Me.ECForm[Me.pageFieldName]=pageNo;
	};

	Me.showShadowRowCallBack=function(formid,crow,shadowRow,eventSrc){};
	Me.hideShadowRowCallBack=function(formid,crow,shadowRow,eventSrc){};
	Me.firstShowShadowRowCallBack=function(formid,crow,shadowRow,eventSrc){};

	Me.beforeFilter=function(formid){};


	Me.beforeSave=function(formid){};

	Me.beforeSubmit=function(formid,crow,shadowRow,eventSrc){};




 	Me.init=function(){

		Me.ECForm=document.getElementById(Me.id);

		if (!Me.ECForm)	{
			/* alert("ERR: tableId=\""+Me.id+"\" not exist!"); */
			return;
		}

		Me.doPreload=Me.doPrep=="temp"?Me.doPreload:Me.doPrep;
		Me.doPreloadPrev=Me.doPrepPrev=="temp"?Me.doPreloadPrev:Me.doPrepPrev;

		Me.DEFAULT_ACTION=Me.ECForm.getAttribute("action");

		Me.ECMainContent=document.getElementById(Me.id+"_main_content");


		if (window.isIE){
			var hideHeader=document.getElementById(Me.id+ECTableConstants.HIDE_HEADER_ROW);
			if (hideHeader){
				hideHeader.style.display="none";
			}
		}

		if (window.frameElement && window.frameElement.name==Me.EXPORT_IFRAME_ID){
			Me.ECForm.style.visibility ="visible";
			ECTableUtil.printFrame(window.frameElement.contentWindow);
			return;
		}

		if (Me.sortedColumnHearderId){
			var sortedHeader=document.getElementById(Me.sortedColumnHearderId);
			if (sortedHeader && Me.sortType && Me.sortType!='' && Me.sortType!='default' ){
				var newHtml=ECTableUtil.trimString(sortedHeader.innerHTML,-1)+"&#160;<div class=\"sort"+Me.sortType.toUpperCase()+"\"></div>";
				sortedHeader.innerHTML=newHtml;
			}
		}

		if (!Me.useAjax){
			Me.doPreload=false;
		}



		if (typeof(Me.ajaxSubmit)!="function"){
			Me.useAjax=false;
			Me.ajaxSubmit=function(){
				Me.ECForm.submit();
			};
		}

		if (Me.useAjax){
			Me.buildPrepArea();
			Me.ajaxPrepSubmit();
		}


		Me.ECListHeadZone=document.getElementById(Me.id+ ECTableConstants.HEAD_ZONE_SUFFIX);
		Me.ECListBodyZone=document.getElementById(Me.id+ ECTableConstants.BODY_ZONE_SUFFIX);

		Me.ECListHead=document.getElementById(Me.id+"_table_head");
		Me.ECListBody=document.getElementById(Me.id+"_table_body");
		Me.ECListFoot=document.getElementById(Me.id+"_table_foot");
		Me.ECListToolbarTable=document.getElementById(Me.id+"_toolbarTable");
		Me.ECListToolbarShadow=document.getElementById(Me.id+"_toolbarShadow");

		if (Me.ECListToolbarTable){
			Me.ECListToolbarShadow.style.height=Me.ECListToolbarTable.offsetHeight+ 2 +"px";
		}
		Me.orgListHeight=Me.ECListBody.scrollHeight;

		Me.initWaitingBar();

		Me.initList();
		Me.columnHandler();


		Me.listWidth=Me.ECListHead.parentNode.clientWidth;
		Me.listHeight=Me.orgListHeight;

		Me.ECForm.style.visibility ="visible";

	};

Me.waitingBar=null;
Me.waitingBarCore=null;
Me.waitingShowTimes=0;
Me.initWaitingBar=function(){
	Me.waitingShowTimes=0;
	Me.waitingBar=document.getElementById(Me.id+"_waitingBar");
	Me.waitingBar.setAttribute("big","false");

	Me.waitingBarCore=document.getElementById(Me.id+"_waitingBarCore");

	Me.waitingBarCore.innerHTML=ECTableMessage.WAITTING_MSG;

	var wLeft=ECTableUtil.getPosLeft(Me.ECForm);
	var wTop=ECTableUtil.getPosTop(Me.ECForm);
	Me.waitingBar.style.left=wLeft+"px";
	Me.waitingBar.style.top=wTop+"px";
};

Me.resizeWaitinBar=function(){
	if (Me.waitingBar && Me.waitingBar.getAttribute("big")=="true"){
		var w=Me.ECForm.offsetWidth;
		var h=Me.ECForm.offsetHeight;
		var cw=Me.waitingBarCore.offsetWidth;
		var ch=Me.waitingBarCore.offsetHeight;
		Me.waitingBar.style.width=w +"px";
		Me.waitingBar.style.height=h-2 +"px";
		Me.waitingBarCore.style.left= parseInt(Me.waitingBar.style.left)+(w-cw-50)/2 + "px";
		Me.waitingBarCore.style.top=parseInt(Me.waitingBar.style.top)+ (h-ch-40)/2 + "px";
	}
};


Me.showWaitingBar=function(){
	Me.waitingShowTimes++;
	Me.waitingBar.style.height="";
	Me.waitingBar.style.width="";
	Me.waitingBar.setAttribute("big","false");

	if (Me.ECForm[Me.id+"_rd"])	{

	//	Me.ECForm[Me.id+"_rd"].style.display="";
	}
	Me.waitingBarCore.style.left=Me.waitingBar.style.left;
	Me.waitingBarCore.style.top=Me.waitingBar.style.top;
	Me.waitingBar.style.display="block";
	Me.waitingBarCore.style.display="block";
};
Me.showBigWaitingBar=function(){
	Me.waitingShowTimes++;
	Me.waitingBar.setAttribute("big","true");
	Me.resizeWaitinBar();
	if (Me.ECForm[Me.id+"_rd"])	{
		Me.ECForm[Me.id+"_rd"].style.display="none";
	}
	Me.waitingBar.style.display="block";
	Me.waitingBarCore.style.display="block";
};

Me.hideWaitingBar=function(){
	Me.waitingShowTimes--;
	if (Me.waitingShowTimes<1){
		Me.waitingBar.setAttribute("big","false");
		Me.waitingBar.style.display="none";
		Me.waitingBarCore.style.display="none";
		Me.waitingShowTimes=0;
		if (Me.ECForm[Me.id+"_rd"])	{
			Me.ECForm[Me.id+"_rd"].style.display="";
		}
	}
};


	Me.autoFitHeight=function(){

		if ( Me.ECListBodyZone.offsetHeight>=Me.ECListBody.parentNode.scrollHeight ){
			var dh=Me.ECListBodyZone.offsetHeight-Me.ECListBodyZone.clientHeight+ECTableConstants.LIST_HEIGHT_FIXED;
			if (dh <=2 && Me.ECListBodyZone.offsetWidth-Me.ECListBodyZone.clientWidth>2){
				dh=ECTableConstants.SCROLLBAR_WIDTH;
			}
			var tHeight=Me.ECListBody.parentNode.scrollHeight+dh;
			tHeight=tHeight<Me.minHeight/1?Me.minHeight/1:tHeight;

			Me.ECListBodyZone.style.height= tHeight+"px";
		}
	};

Me.columnHandler=function(){
		Me.MIN_COL_WIDTH=Me.ECForm.getAttribute("minColWidth");
		Me.canResizeColWidth=Me.ECForm.getAttribute("canResizeColWidth");
		if ( Me.canResizeColWidth=="true" || Me.canResizeColWidth===true){
			Me.canResizeColWidth=true;
			ECTableUtil.initSeparateLine();
			Me.ECListHead.parentNode.style.tableLayout="fixed";
			Me.ECListBody.parentNode.style.tableLayout="fixed";
			ECTableUtil.resizeInit();
		}else{
			Me.canResizeColWidth=false;
		}

		if (Me.ECListHead && Me.ECListHead.rows){
			var cells=Me.ECListHead.rows[0].cells;
			Me.columnNum=cells.length;
			for (var i=0;i<Me.columnNum;i++){
				if (cells[i].getAttribute("group")=="true"){
					ECTableUtil.groupByCol(Me.ECListBody.rows,i);
				}
			}
		}

}

	Me.handleScroll=function(){
		Me.ECListHeadZone.scrollLeft=Me.ECListBodyZone.scrollLeft;
	};

	Me.resizeHeader=function(){
		var fixWidth=window.isIE?2:0;
		var temp_1=Me.ECListBodyZone.clientWidth;
		var temp_2=Me.ECListHeadZone.clientWidth;
		Me.ECListHeadZone.style.width=Me.ECListBodyZone.clientWidth+ fixWidth +"px";
	};


	Me.initList=function(){

		if (!Me.ECListHeadZone || !Me.ECListBodyZone)	{
			Me.isClassic=true;
		}

		if (!Me.isClassic){

			Me.resizeWay="resizeTable";
			ECTableUtil_addEvent(Me.ECListBodyZone,"scroll",Me.handleScroll);
			ECTableUtil_addEvent(window,"resize",Me.handleResize);
			if (window.isIE){
				Me.ECListHead.parentNode.style.tableLayout="fixed";
				ECTableUtil_addEvent(Me.ECListBodyZone,"resize",Me.handleResize);
			}else{
				//Me.ECListHead.parentNode.style.tableLayout="fixed";
				//Me.ECListBody.parentNode.style.tableLayout="fixed";
			}

			Me.autoFitHeight();
			Me.handleResize();


			Me.orgListHeight=ECTableUtil.parseIntOrZero(Me.ECListBodyZone.style.height);
		}
	};




	Me.resized=0;
	Me.handleResize=function(){
		Me.resizeWaitinBar();
		if (Me.isClassic || !Me.ECListBodyZone ||  !Me.ECListHeadZone){
			return;
		}

		//Fix IE bug
		var temp_1=Me.ECListBodyZone.clientWidth;
		if (window.isIE && Me.resized>0){
			Me.resizeHeader();
			Me.resized=0;
			return;
		}

		Me.autoFitHeight();


		if (window.isIE && Me.ECListToolbarTable){
				if(Me.ECListBodyZone.offsetWidth +1 <Me.ECListToolbarTable.clientWidth){
					Me.ECListToolbarShadow.style.display="block";
					Me.ECListToolbarTable.parentNode.style.position="absolute";
				}else{
					Me.ECListToolbarShadow.style.display="none";
					Me.ECListToolbarTable.parentNode.style.position="static";
				}
		}

		//Fix IE bug
		temp_1=Me.ECListBodyZone.clientWidth;


		Me.resizeHeader();

		ECTableUtil.syncRowsWidth(Me.ECListHead.rows,Me.ECListBody.rows);


		Me.resized=1;

	};

Me.updateCallBack=function(responseObj){
	var rs=ECTableUtil.responseHandler(responseObj);
	var tableId=ECTableUtil.trimString(rs[0]);
	var ectableObj=ECTableUtil.getGridObj(tableId);
	for (var i=1;i<rs.length ;i+=3 ){

		var resultCode=ECTableUtil.trimString(rs[i]+"");
		if (resultCode=="END OF org.extremecomponents.defaultAjaxResopnse"){
			break;
		}
		var recordKey=ECTableUtil.trimString(rs[i+1]+"");
		var message=ECTableUtil.trimString(rs[i+2]+"");
		if (resultCode=="1" || resultCode=="Success") {
			//Success
			ECTableUtil.getRemoveUpdatedClassRows(ectableObj.forUpdateRows,recordKey);
		}else{
			//Fail
			if (message.length>1){
				alert(message);
			}
		}
	}
	ectableObj.hideWaitingBar();
};

Me.insertCallBack=function(responseObj){

	var rs=ECTableUtil.responseHandler(responseObj);
	var tableId=ECTableUtil.trimString(rs[0]);
	var ectableObj=ECTableUtil.getGridObj(tableId);
	for (var i=1;i<rs.length ;i+=3 ){
		var resultCode=ECTableUtil.trimString(rs[i]+"");

		if (resultCode=="END OF org.extremecomponents.defaultAjaxResopnse"){
			break;
		}
		var recordKey=ECTableUtil.trimString(rs[i+1]+"");
		var message=ECTableUtil.trimString(rs[i+2]+"");

		if (resultCode=="1" || resultCode=="Success") {
			//Success
			ECTableUtil.getRemoveInsertedClassRows(ectableObj.forInsertRows,recordKey);
		}else{
			//Fail

			if (message.length>1){
				alert(message);
			}
		}
	}

	ectableObj.hideWaitingBar();
};

Me.deleteCallBack=function(responseObj){
	var rs=ECTableUtil.responseHandler(responseObj);
	var tableId=ECTableUtil.trimString(rs[0]);
	var ectableObj=ECTableUtil.getGridObj(tableId);
	for (var i=1;i<rs.length ;i+=3 ){
		var resultCode=ECTableUtil.trimString(rs[i]+"");
		if (resultCode=="END OF org.extremecomponents.defaultAjaxResopnse"){
			break;
		}
		var recordKey=ECTableUtil.trimString(rs[i+1]+"");
		var message=ECTableUtil.trimString(rs[i+2]+"");
		if (resultCode=="1" || resultCode=="Success") {
			//Success
			ECTableUtil.getRemoveDeletedRows(ectableObj.forDeleteRows,recordKey);
		}else{
			//Fail
			if (message.length>1){
				alert(message);
			}
		}
	}
	ectableObj.hideWaitingBar();
};


};


var ECTableUtil={};

ECTableUtil.responseHandler=function(responseObj){
	var result=responseObj.responseText;
	result=ECTableUtil.trimString(result);
	var rs=result.split("\n");
	return rs;
};

ECTableUtil.syncRowsWidth=function(rows1,rows2){
	var IE_FIX=0;
	var FF_FIX=0;
	//IE_FIX=4;
	if (rows1 && rows2 && rows1.length>0 && rows2.length>0){
			var headTDs=rows1[0].cells;
			var bodyTDs=rows2[0].cells;
		if (window.isIE){
			for (var i=0;i<headTDs.length;i++ )	{
				headTDs[i].style.width= IE_FIX + bodyTDs[i].offsetWidth +"px";
			}
		}else{
			var colNum=headTDs.length;
			for (var i=0;i<colNum;i++ )	{
				var tt;
				if (bodyTDs[i].width && bodyTDs[i].width.length>0){
					tt=parseInt(bodyTDs[i].width);
				}else{
					tt=parseInt(bodyTDs[i].style.width);
				}
				if (isNaN(tt))	{
					continue;
				}
				//tt=bodyTDs[i].offsetWidth;
				var brw=0;
				brw=headTDs[i].getAttribute("resizeColWidth")=="true"?2:(i==0?0:(i+1==colNum?2:0));
				headTDs[i].style.width=  (tt+ FF_FIX+ brw )+  "px";
				var dw2=bodyTDs[i].clientWidth-headTDs[i].clientWidth;
				var dw=bodyTDs[i].offsetWidth-headTDs[i].offsetWidth;
				if (headTDs[i].getAttribute("resizeColWidth")!="true" && dw!=0){
					headTDs[i].style.width =(tt+ FF_FIX+ brw ) +dw+"px";
				}

			}

		}

	}
}

ECTableUtil.resizeAllGrid=function(){
	for (var gridId in ECTableList ){
		var grid=ECTableList[gridId];
		grid.handleResize();
	}
};



ECTableUtil.getGridObj=function(formid){
	if (!formid){
		formid=ECTableConstants.EC_ID;
	}
	return ECTableList[formid];
};

ECTableUtil.getECTableForm=function(formid){
	var ectableObj=ECTableUtil.getGridObj(formid);
	if (ectableObj){
		return ectableObj.ECForm;
	}
	return null;
};




ECTableUtil.getMessage=function(name, msgs){
var msgTemplate=ECTableMessage[name];
	for (var i=1;i<arguments.length ;i++ ){
		msgTemplate=ECTableUtil.replaceAll(msgTemplate,"#{"+i+"}",arguments[i]);
	}
	return msgTemplate;
};

ECTableUtil.getTotalPages=function(formid){
	var form=ECTableUtil.getECTableForm(formid);
	try{
		return form[formid+"_totalpages"].value;
	}catch(e){
		return -1;
	}
};

ECTableUtil.getTotalRows=function(formid){
	var form=ECTableUtil.getECTableForm(formid);
	try{
		return form[formid+"_totalrows"].value;
	}catch(e){
		return -1;
	}
};

ECTableUtil.clearTotalRows=function(formid){
	try{
		var form=ECTableUtil.getECTableForm(formid);
		form[formid+"_totalrows"].value="";
	}catch(e){
	}
};

ECTableUtil.cutText=function(text,formid){
		var ectableObj=ECTableUtil.getGridObj(formid);
		if (text.responseText){
			text=text.responseText;
		}
		if (ectableObj && !ectableObj.findAjaxZoneAtClient) {
			return text;
		}

		var begin=ECTableConstants.AJAX_ZONE_PREFIX+ECTableConstants.AJAX_ZONE_BEGIN+formid +ECTableConstants.AJAX_ZONE_SUFFIX;
		var end=ECTableConstants.AJAX_ZONE_PREFIX+ECTableConstants.AJAX_ZONE_END+formid +ECTableConstants.AJAX_ZONE_SUFFIX;

        var p1 = text.indexOf(begin);
        if (p1 != -1) {
            p1+=begin.length;
            var p2 = text.indexOf(end, p1);
            if (p2!=-1){
                return text.substring(p1, p2);
            }
        }
		return text;
	};

ECTableUtil.noExport=function(formid){
	var ectableObj=ECTableUtil.getGridObj(formid);
	var etiid;
	var form;
	if (!ectableObj)	{
		etiid=ECTableConstants.ETI_ID;
		form=document.getElementById(formid);
	}else{
		etiid=ectableObj.ETI_ID;
		form=ectableObj.ECForm;
	}

	try{
		form[etiid].value="";
	}catch(e){
	}

};



ECTableUtil.refresh=function(formid){
	var ectableObj=ECTableUtil.getGridObj(formid);
	var form;
	if (!ectableObj)	{
		form=document.getElementById(formid);
	}else{
		form=ectableObj.ECForm;
	}
	try{
    	form[formid+"_totalrows"].value="";
	}catch(e){
	}
};

ECTableUtil.reload=function(formid,pageno){
	ECTableUtil.noExport(formid);
	ECTableUtil.refresh(formid);
	var ectableObj=ECTableUtil.getGridObj(formid);
	if (!pageno){
		pageno=ectableObj.getPageNo();
	}
	ECTableUtil.gotoPage(pageno,formid);

	ECTableUtil.showShadowRow(ectableObj.currentShadowRowParent,ectableObj.currentShadowEventSrc,formid);
};


ECTableUtil.gotoPage=function(pageno,formid){


var ectableObj=ECTableUtil.getGridObj(formid);

ectableObj.showWaitingBar();

	try{
		ECTableUtil.NearPagesBar.doHideMe();
	}catch (e3){}

	var pageid=ectableObj.pageFieldName;
	var form=ectableObj.ECForm;

if (!form[pageid]){
	var hiddenpageid=document.createElement("input");
	hiddenpageid.id=pageid;
	hiddenpageid.style.display="none";
	form.appendChild(hiddenpageid);
}

	form[pageid].value=pageno;
	ECTableUtil.noExport(formid);
	form.action=ectableObj.DEFAULT_ACTION;

	try {
		if (ectableObj.doPreload){
			ectableObj.goPage();
		}else {
			ectableObj.ajaxSubmit();
		}
	}catch (e){
		try {
			ectableObj.ajaxSubmit();
		}catch (e2){
			form.submit();
		}
	}


};


ECTableUtil.gotoPageByInput=function(inputNoObj,formid){

	var form=ECTableList[formid].ECForm;
	var tempInput=null;
	if (inputNoObj.type!="text"){
		tempInput=inputNoObj.previousSibling.previousSibling;
		if (tempInput.type!="text"){
			tempInput=inputNoObj.nextSibling;
		}
		inputNoObj=tempInput;
	}

	var pageno=inputNoObj.value/1;


	var totalpages=form[formid+"_totalpages"].value/1;
	if (!isFinite(pageno) || (pageno+"").indexOf(".")!=-1 || pageno<1 || pageno>totalpages){
		alert(ECTableUtil.getMessage("ERR_PAGENO",totalpages));
		inputNoObj.focus();
		inputNoObj.select();
		return;
	}
	if (pageno<1){
		pageno=1;
	}
	ECTableUtil.gotoPage(pageno,formid);
};




ECTableUtil.doSort=function(event,columnAlias,sortT1,formid,columnHearderId){

	var e = event||window.event;

	if ( ECTableUtil.startDragobj==true || ECTableUtil.Dragobj){
		return;
	}

	if (window.isIE && e.button>1) {
		return;
	}

	var asc="asc";
	var desc="desc";
	var defaultOrder="default";

	var ectableObj=ECTableUtil.getGridObj(formid);

	var pageid=ectableObj.pageFieldName;

	var form=ectableObj.ECForm;

	//form[pageid].value=1;

	if ( typeof(sortT1)!='string'){
		columnHearderId=sortT1.id;
		sortT1=null;
	}else if(columnHearderId && typeof(columnHearderId)!='string'){
		columnHearderId=columnHearderId.id;
	}


	var tOrder="default";

	if (sortT1){
		tOrder=sortT1;
	}else if (ectableObj.sortedColumn==columnAlias){
		if (!ectableObj.sortType || ectableObj.sortType=="default"){
			tOrder="asc";
		}else if (ectableObj.sortType=="asc"){
			tOrder="desc";
		}else if (ectableObj.sortType=="desc"){
			tOrder="default";
		}else{
			tOrder="asc";
		}
	}else{
		tOrder="asc";
	}


	ECTableUtil.noExport(formid);
	var oAction=form.action;
	form.action=ectableObj.DEFAULT_ACTION;

	if (ectableObj.sortedColumn && ectableObj.sortedColumn!=''){
		form[formid+ECTableConstants.SORT_PREFIX+ectableObj.sortedColumn].value="";
	}


	ectableObj.sortedColumn=columnAlias;
	ectableObj.sortType=tOrder;
	ectableObj.sortedColumnHearderId=columnHearderId;


	if (ectableObj.custSort){
		ectableObj.custSort(columnAlias,tOrder);
	}else{
		form[formid+ECTableConstants.SORT_PREFIX+columnAlias].value=tOrder;

	}
	try {
		ectableObj.ajaxSubmit();
		form.action=oAction;
	}catch (e2){
		form.submit();
	}

	ECTableUtil.ColmunMenu.doHideMe();

};


ECTableUtil.doCustomExport=function(fileName,exportAction,formid){
	var form=ECTableUtil.getECTableForm(formid);
	var otarget=form.target;
	var oaction=form.action;

	form[formid+"_efn"].value=fileName;
	form.action=exportAction;

		var targetFrame=ECTableList[formid].EXPORT_IFRAME_ID;
		targetFrame=document.getElementById(targetFrame);
		if (targetFrame){
			form.target=ECTableList[formid].EXPORT_IFRAME_ID;
		}

	form.submit();

	form.target= otarget;
	form.action=oaction;

	ECTableUtil.noExport(formid);

};

ECTableUtil.doExportList=function(fileName,page,formid){
	var type="xls";
	ECTableUtil.doExport(type,fileName,page,formid);
};

ECTableUtil.doExport=function(type,fileName,page,formid){

	/* for compatibility */
	if (arguments.length>4){
		type=arguments[4];
		fileName=arguments[5];
	}


	var ectableObj=ECTableUtil.getGridObj(formid);

	var etiid=ectableObj.ETI_ID;
	var etip=ectableObj.ETI_PAGE_FLAG
	var form=ectableObj.ECForm;

	if (page==true){
		page=true;
	}else{
		page=false;
	}

	page=!confirm(ECTableMessage.EXPORT_CONFIRM);

	var maxRowsExported = form.getAttribute("maxRowsExported");
	if (page===false && maxRowsExported && ECTableUtil.parseIntOrZero(maxRowsExported)>0){
		if(ECTableUtil.parseIntOrZero(maxRowsExported)<ECTableUtil.getTotalRows(formid)){
			alert(ECTableUtil.getMessage("OVER_MAXEXPORT",ECTableUtil.parseIntOrZero(maxRowsExported)));
			return;
		}
	}

	form[formid+"_ev"].value=type;
	form[formid+"_efn"].value=fileName;
	form[etiid].value=formid;

	if (page===true){
		form[etip].value="true";
	}else{
		form[etip].value="";
	}


	var otarget=form.target;
	form.action=ectableObj.DEFAULT_ACTION;

	/* if (type=="print"){ */
		var targetFrame=ectableObj.EXPORT_IFRAME_ID;
		targetFrame=document.getElementById(targetFrame);
		if (targetFrame){
			//targetFrame.height="300";
			//targetFrame.width="500";
			form.target=ectableObj.EXPORT_IFRAME_ID;
		}

	/* } */
	form.submit();
	form.target= otarget;
	ECTableUtil.noExport(formid);

};


ECTableUtil.changeRowsDisplayed=function(formid,selectObj){

var ectableObj=ECTableUtil.getGridObj(formid);

	var pageid=ectableObj.pageFieldName;

	var form=ectableObj.ECForm;
	form[formid+"_crd"].value=selectObj.options[selectObj.selectedIndex].value;
	form[pageid].value='1';

	ECTableUtil.noExport(formid);
	form.action=ectableObj.DEFAULT_ACTION;
	try {

		ectableObj.ajaxSubmit();

	}catch (e2){

		form.submit();
	}
};

ECTableUtil.checkAll=function(checkcontrolObj,checkboxname,formid){

	var form=ECTableList[formid].ECForm;
	if (!form.elements[checkboxname]){ return;}
	var checked=false;
	if (checkcontrolObj.className=="checkedboxHeader"){
		checked=true;
		checkcontrolObj.className="checkboxHeader";
	}else{
		checkcontrolObj.className="checkedboxHeader";
	}
	if (!form.elements[checkboxname].length){
		if (!form.elements[checkboxname].disabled){
			form.elements[checkboxname].checked = !checked;
		}
		return;
	}
	for(i = 0; i < form.elements[checkboxname].length; i++) {
		if (!form.elements[checkboxname][i].disabled){
			form.elements[checkboxname][i].checked = !checked;
		}
	}
};

ECTableUtil.check=function(checkcontrolObj,checkboxHeadername,formid){
	var form=ECTableList[formid].ECForm;
	var checkboxName=checkcontrolObj.name;
    if (checkcontrolObj.checked) {
           var chckHeader= true;
           for ( i=0;i < form.elements[checkboxName].length; i++){
              chckHeader= chckHeader && form.elements[checkboxName][i].checked;
           }
           if(chckHeader)

                document.getElementById(checkboxHeadername).className="checkedboxHeader";

	} else {

		 document.getElementById(checkboxHeadername).className="checkboxHeader";
	}

};

ECTableUtil.selectRow=function(rowObj,formid){
	var selectlightClassName=ECTableConstants.ROW_SELECTLIGHT_CLASS;
	var ectableObj=ECTableUtil.getGridObj(formid);
	if (!ectableObj || rowObj==ectableObj.selectedRow){ return;}
	ECTableUtil.addClass(rowObj,selectlightClassName);
	ECTableUtil.removeClass(ectableObj.selectedRow,selectlightClassName);
	ectableObj.selectedRow=rowObj;
};

ECTableUtil.lightRow=function(rowObj,formid){

	ECTableUtil.addClass(rowObj,ECTableConstants.ROW_HIGHLIGHT_CLASS);
};

ECTableUtil.unlightRow=function(rowObj,formid){
	ECTableUtil.removeClass(rowObj,ECTableConstants.ROW_HIGHLIGHT_CLASS);
};

ECTableUtil.lightHeader=function(tdObj,formid){

	var className=tdObj.className;
	if (className){
		className=className.split(" ");
		className[0]+="Over";
	}
	tdObj.className=className.join(" ");

};

ECTableUtil.unlightHeader=function(tdObj,formid){

	var className=tdObj.className;
	if (className){
		className=className.split(" ");
		if (className[0].lastIndexOf("Over")==className[0].length-"Over".length){
			className[0]=className[0].substring(0,className[0].length-"Over".length);
		}
	}
	tdObj.className=className.join(" ");
};



ECTableUtil.getFirstChildElement=function(node){
	var nodeIdx=0;
	try{
		var nodeT=-1;
		while(nodeT!=1 && nodeIdx<node.childNodes.length){
			nodeT=node.childNodes[nodeIdx].nodeType;
			nodeIdx++;
		}
		nodeIdx--;
		return node.childNodes[nodeIdx];
	}catch(e){
		return node.childNodes[0];
	}
};

ECTableUtil.getNextElement=function(node){
	if (!node){
		return null;
	}
	var tnode=node.nextSibling;
	while ( tnode!=null ){
		if (tnode.nodeType==1) {
			return tnode;
		}
		tnode=tnode.nextSibling;
	}
	return null;
};



ECTableUtil.getShadowRow=function(crow,formid){

		var ectableObj=ECTableUtil.getGridObj(formid);

		var hasShadow=crow.getAttribute("hasShadow");
		var shadowRow=null;
		if (hasShadow=="true"){
			var crowIndex=crow.rowIndex;
			if (ectableObj.scrollList){
				crowIndex++;
			}
			shadowRow=crow.parentNode.rows[crowIndex];
		}
		return shadowRow;
};



ECTableUtil.ajaxRequest=function(){

};

ECTableUtil.submit=function(actionUrl,formid){
	var ectableObj=ECTableUtil.getGridObj(formid);
	ectableObj.showWaitingBar();
	var form=ectableObj.ECForm;
	form.action=actionUrl;
	form.submit();

	try {
		form.action=ectableObj.DEFAULT_ACTION;
	}catch (e){
	}

};

ECTableUtil.operateECForm=function(actionUrl,resFunc,parameter,asy,reFirstPage,formid){


	var ectableObj=ECTableUtil.getGridObj(formid);
	ectableObj.showWaitingBar();
	var pageid=ectableObj.pageFieldName;
	var form=ectableObj.ECForm;

	if (!asy){
		asy=true;
	}
	var appPara='';
	if (parameter){
		if (typeof(parameter)=='string'){
			appPara=parameter;
		}else{
			for (var k in parameter){
				if (!form[k]){
					appPara=appPara+k+'='+parameter[k]+'&';
				}else{
					form[k].value=parameter[k];
				}
			}
		}
	}

   if(reFirstPage=="true" || reFirstPage=="1"){
		ECTableUtil.refresh(formid);
		form[pageid].value=1;
	}
	ECTableUtil.noExport(formid);
	form.action=actionUrl;
	try {

		ectableObj.ajaxSubmit(resFunc,asy,appPara);
	}catch (e){

		form.submit();
	}
	try {
		form.action=ectableObj.DEFAULT_ACTION;
	}catch (e){
	}
};

ECTableUtil.queryECForm=function(formid,parameter,asy){

	if (asy==null || asy==window.undefined){
		asy=true;
	}

	var ectableObj=ECTableUtil.getGridObj(formid);
	ectableObj.showWaitingBar();
	var pageid=ectableObj.pageFieldName;
	var form=ectableObj.ECForm;
	ECTableUtil.refresh(formid);
	form[pageid].value=1;
	ECTableUtil.noExport(formid);

	ectableObj.ajaxSubmit(ectableObj.fillForm,asy,parameter)


};



ECTableUtil.printFrame=function(frame, doctitle,onfinish) {

	if ( !frame ) { frame = window; }
	if ( !doctitle ) {
		doctitle="";
	}

	frame.document.title=doctitle;

	  function execOnFinish() {
		switch ( typeof(onfinish) ) {
		  case "string": execScript(onfinish); break;
		  case "function": onfinish();
		}

		if ( focused && !focused.disabled ) { focused.focus(); }

		//if (frame!=window){
			frame.location="about:blank";
		//}
	  }

  if ( frame.document.readyState !== "complete" &&
       !confirm("The document to print is not downloaded yet! Continue with printing?") ) {
		execOnFinish();
		return;
  }

  if ( frame.print ) { // IE5+
    var focused = document.activeElement;
    frame.focus();
	frame.print();
    execOnFinish();
    return;
  }else{
	alert("the PRINT for IE 5.0+ Only");
  }

};


/*============ UTILS ============*/



function ECTableUtil_addEvent( obj, type, fn ) {
  if ( obj.attachEvent ) {
    obj['e'+type+fn] = fn;
    obj[type+fn] = function(){obj['e'+type+fn]( window.event );};
    obj.attachEvent( 'on'+type, obj[type+fn] );
  }else if(obj.addEventListener){
    obj.addEventListener( type, fn, false );
  }
}
function ECTableUtil_stopEvent(e) {
	if (e.stopPropagation){
		e.stopPropagation();
		e.preventDefault();
	} else {
		e.returnValue = false;
		e.cancelBubble = true;
	}
	return false;
}

function ECTableUtil_removeEvent( obj, type, fn ) {
  if ( obj.detachEvent ) {
    obj.detachEvent( 'on'+type, obj[type+fn] );
    obj[type+fn] = null;
    obj['e'+type+fn] = null;
  }else if(obj.removeEventListener){
    obj.removeEventListener( type, fn, false );
  }
}

ECTableUtil.trimString=function(str, wh){
		if(!str.replace){ return str; }
		if(!str.length){ return str; }
		var re = (wh > 0) ? (/^\s+/) : (wh < 0) ? (/\s+$/) : (/^\s+|\s+$/g);
		return str.replace(re, "");
};


ECTableUtil.getPosTop=function(elm) {
	var top = elm.offsetTop;
	while((elm = elm.offsetParent) != null)	{
		top += (elm.offsetTop-elm.scrollTop);
	}
	return top;
}

ECTableUtil.getPosLeft=function(elm) {
	var left = elm.offsetLeft;
	while((elm = elm.offsetParent) != null)	{
		left += (elm.offsetLeft-elm.scrollLeft);
	}
	return left;
};

ECTableUtil.getPosRight=function(elm){
	return ECTableUtil.getPosLeft(elm)+elm.offsetWidth;
};
ECTableUtil.getPosBottom=function(elm){
	return ECTableUtil.getPosTop(elm)+elm.offsetHeight;
};

ECTableUtil.replaceAll=function(exstr,ov,value){
	var gc=ECTableUtil.escapeRegExp(ov);
	if (gc==null || gc==''){
		return exstr;
	}
	var reReplaceGene="/"+gc+"/gm";
	var r=null;
	var cmd="r=exstr.replace("+reReplaceGene+","+ECTableUtil.escapeString(value)+")";
	eval(cmd);
	return r;
};

ECTableUtil.escapeRegExp=function(str) {
	return !str?''+str:(''+str).replace(/\\/gm, "\\\\").replace(/([\f\b\n\t\r[\^$|?*+(){}])/gm, "\\$1");
};

ECTableUtil.escapeString=function(str){

	return !str?''+str:('"' + (''+str).replace(/(["\\])/g, '\\$1') + '"'
		).replace(/[\f]/g, "\\f"
		).replace(/[\b]/g, "\\b"
		).replace(/[\n]/g, "\\n"
		).replace(/[\t]/g, "\\t"
		).replace(/[\r]/g, "\\r");
};


ECTableUtil.hasClass=function(object, className) {
	if (!object.className) { return false;}
	return (object.className.search('(^|\\s)' + className + '(\\s|$)') != -1);
};

ECTableUtil.removeClass=function(object,className) {
	if (!object) {return;}
	object.className = object.className.replace(new RegExp('(^|\\s)'+className+'(\\s|$)'), ' ');
};

ECTableUtil.addClass=function(object,className) {
	if (!object || ECTableUtil.hasClass(object, className)){return;}
	if (object.className) {
		object.className += ' '+className;
	} else {
		object.className = className;
	}
};


ECTableUtil.parseIntOrZero=function(num){
	return ECTableUtil.parseInt(num,0);
};
ECTableUtil.parseIntOrOne=function(num){
	return ECTableUtil.parseInt(num,1);
};
ECTableUtil.parseInt=function(num,defaultNum){
	var t=parseInt(num);
	return isNaN(t)?defaultNum:t;
};

ECTableUtil.isCollection=function(obj){
	return obj && typeof(obj) != 'string' && typeof(obj.length) == 'number';

};


ECTableUtil.appendMap=function(destination,source){
  for (var property in source) {
	  if (property in destination) {
		  if (destination[property].constructor != Array) destination[property] = [destination[property]];
          destination[property].push(source[property]);
	  }else{
		destination[property] = source[property];
	  }
  }
};



ECTableUtil.groupByCol=function(rows,colNo,startRowNo,endRowNo){

		if (!colNo){
			colNo=0;
		}
		if (!startRowNo || startRowNo<0){
			startRowNo=0;
		}
		if (!endRowNo||endRowNo>rows.length){
			endRowNo=rows.length;
		}
		var show="";
		var hide="none";
		var cell=null;

		var info=[];
		var startCell=rows[startRowNo].cells[colNo];
		var startRowSpan=startCell.rowSpan;

		for (var i=startRowNo+1;i<endRowNo ;i++ ){
			cell=rows[i].cells[colNo];

			if (cell.style.display==hide){
				startRowSpan+=cell.rowSpan;
			}else{
			//info.push(i+","+startRowSpan);
				startCell.rowSpan =startRowSpan;
				startCell=cell;
				startRowSpan=cell.rowSpan;
			}
		}
		//info.push(i+","+startRowSpan);
		startCell.rowSpan =startRowSpan;
		return info;
	};

ECTableUtil.ungroupByCol=function(rows,colNo,startRowNo,endRowNo){
		if (!colNo){
			colNo=0;
		}
		if (!startRowNo || startRowNo<0){
			startRowNo=0;
		}
		if (!endRowNo||endRowNo>rows.length){
			endRowNo=rows.length;
		}

		var show="";
		var hide="none";
		var cell=null;
		for (var i=startRowNo;i<endRowNo ;i++ ){
			cell=rows[i].cells[colNo];
			if (cell.style.display==hide){
				cell.style.display=show;
			}else if (cell.rowSpan>1){
				cell.rowSpan=1;
			}
		}
};





/* ===========LIST SCROLL ============= */





ECTableUtil.initSeparateLine=function(){
	var temp=document.getElementById("separateLine");
	if (!temp){
		ECTableUtil.separateLine=document.createElement("DIV");
		ECTableUtil.separateLine.id="separateLine";
		ECTableUtil.separateLine.className="separateLine";
		ECTableUtil.separateLine.style.display="none";
		document.body.appendChild(ECTableUtil.separateLine);
	}
};




/* =========== MENU ============= */





var ECSPopup=function(eid){
	var Me=this;
	Me.id=eid;
	Me.currentContent=null;

	Me.hideTimeout1=500;
	Me.hideTimeout2=200;

	Me.coreElement=null;

	Me.isShow=false;
	Me.setShow=function(){
		Me.isShow=true;
	};

	Me.setHide=function(){
		Me.isShow=false;
	};

	Me.initMe=function(){
		/*  TODO */
	};
	Me.showMe=function(){
		/*  TODO */
		Me.setShow();
	};

	Me.hideMe=function(){
			window.setTimeout(Me.tryHideMe, Me.hideTimeout1);
			Me.setHide();
	};

	Me.tryHideMe=function(){
		if (!Me.isShow){
			Me.doHideMe();
		}
	};

	Me.doHideMe=function(){
		Me.coreElement.style.display="none";
		Me.setHide();
	};
};

/*

<a href="#" onclick="ECTableUtil.gotoPage('ec',2);return false;" >2</a>

*/

ECTableUtil.NearPagesBar=new ECSPopup("nearPagesBar");
ECTableUtil.NearPagesBar.initMe=function(){
	var temp=document.getElementById("nearPagesBar");
	if (!temp){
		this.coreElement=document.createElement("DIV");
		this.coreElement.id=this.id;
		this.coreElement.className = this.id ;
		this.coreElement.style.display="none";
		document.body.appendChild(this.coreElement);
		ECTableUtil_addEvent(this.coreElement,"mouseover",this.setShow );
		ECTableUtil_addEvent(this.coreElement,"mouseout",this.hideMe);
		this.currentContent="formid : nearPages";

	}
};

ECTableUtil.NearPagesBar.createNearPagesList=function(nearPages,formid){
	var listHtml= ECTableMessage.NEARPAGE_TITLE;
	var ectableObj=ECTableUtil.getGridObj(formid);
	var page=ectableObj.getPageNo();
	var lastPage=ectableObj.getTotalPage();
	var pi1=0;
	var startP=page- nearPages;
	var endP=page+ nearPages;
	if ( startP<1){
			endP=endP+(1-startP);
			startP=1;
	}
	if ( endP>lastPage){
		startP=startP-(endP-lastPage);
		endP=lastPage;
	}
	startP=startP<1?1:startP;
	 listHtml+="<nobr>";
  	for (pi1=startP;pi1<=endP;pi1++){
	  		if (pi1==page){
	    			listHtml+=" <b>"+pi1+"</b> ";
	    		}else{
	    			listHtml+="<a href=\"#\" onclick=\"ECTableUtil.gotoPage("+pi1+",'"+formid+"');return false;\" >"+pi1+"</a>";
	    		}
	 }
	 listHtml+="</nobr>";
	return listHtml;
}

ECTableUtil.NearPagesBar.showMe=function(fireObj,formid){
	var ectableObj=ECTableUtil.getGridObj(formid);

	var nearPages=ECTableUtil.parseIntOrZero( ectableObj.ECForm.getAttribute("nearPages"));
	if (nearPages<2){ return ; }
	var page=ectableObj.getPageNo();
	//if (this.currentContent!=formid+" : "+page){
		this.currentContent=formid+" : "+page;

		this.coreElement.innerHTML=this.createNearPagesList(nearPages,formid);

	//}
	this.setShow();
	this.coreElement.style.display="block";
	var dx=(this.coreElement.offsetWidth-fireObj.offsetWidth)/2;
	this.coreElement.style.left=ECTableUtil.getPosLeft(fireObj)-dx+document.body.scrollLeft+"px";
	this.coreElement.style.top= (ECTableUtil.getPosTop(fireObj)- this.coreElement.offsetHeight+document.body.scrollTop) +"px";
};


////////////////////////////////////////////

ECTableUtil.doFilterFocus=function(event,filterInputObj){
	var e = event||window.event;
	filterInputObj.select();
	ECTableUtil_stopEvent(e);

}

ECTableUtil.doFilter=function(event,filterInputObj,filterFieldName,formid){
	var e = event||window.event;
	if(filterInputObj.type=="button"){
		filterInputObj=filterInputObj.nextSibling;
	}else if (event.keyCode != 13){
		return;
	}
	ECTableUtil.doFilterCore(filterInputObj,filterFieldName,ECTableConstants.FILTER_ACTION,formid);
};
ECTableUtil.doClearFilter=function(event,filterInputObj,filterFieldName,formid){
	ECTableUtil.doFilterCore(filterInputObj,filterFieldName,ECTableConstants.CLEAR_ACTION,formid);
};

ECTableUtil.doFilterCore=function(filterInputObj,filterFieldName,faction,formid){

	var ectableObj=ECTableUtil.getGridObj(formid);

	var pageid=ectableObj.pageFieldName;

	var form=ectableObj.ECForm;

	var continueFunction=true;
	if (ectableObj.beforeFilter){
		continueFunction=ectableObj.beforeFilter(formid);
	}
	if (continueFunction===false){
		return;
	}


	form[formid+ECTableConstants.FILTER_PREFIX+ECTableConstants.ACTION].value=faction;

	form[pageid].value=1;
	ECTableUtil.clearTotalRows(formid);
	ECTableUtil.noExport(formid);
	var oAction=form.action;
	form.action=ectableObj.DEFAULT_ACTION;

if (filterInputObj!=null){
	form[filterFieldName].value=filterInputObj.value;
}

	try {
		ectableObj.ajaxSubmit();
		form.action=oAction;
	}catch (e2){
		form.submit();
	}
		ECTableUtil.ColmunMenu.doHideMe();
}

ECTableUtil.showColmunMenu=function(event,columnObj,formid){
	var e = event||window.event;
	ECTableUtil.ColmunMenu.showMe(columnObj,formid);
	return ECTableUtil_stopEvent(e);
}

ECTableUtil.ColmunMenu=new ECSPopup("columnMenu");
ECTableUtil.ColmunMenu.initMe=function(){
	var temp=document.getElementById("columnMenu");
	if (!temp){
		this.coreElement=document.createElement("table");
		this.coreElement.id=this.id;
		this.coreElement.className = this.id ;
		this.coreElement.style.display="none";
		this.coreElement.style.left="0px";
		this.coreElement.style.top="0px";
		var ntbody=document.createElement("tbody");
		var ntr=document.createElement("tr");
		var ntd=document.createElement("td");
		ntr.appendChild(ntd);
		ntbody.appendChild(ntr);
		this.coreElement.appendChild(ntbody);
		document.body.appendChild(this.coreElement);
		this.currentContent="formid : columnIdx";
	}
	this.setHide();
};

ECTableUtil.ColmunMenu.getFilterItem=function(menuWidth,columnObj,columnName,formid){
	var FIX_WIDTH=20;

	var filterFieldName=formid+ECTableConstants.FILTER_PREFIX+columnName;
	var filterField=$_E(filterFieldName);
	if (!filterField){
		return "";
	}
	var v=filterField.value;

	var jsfunction="ECTableUtil.doFilter(event,this,'"+filterFieldName+"','"+formid+"')";
	//this.coreElement.style.width=width+"px";
	var filterHTML="<nobr><input type=\"button\" class=\"filterIcon\" onclick=\""+jsfunction+"\" />";

	var templateId=columnObj.getAttribute("editTemplate");
	var template=document.getElementById(templateId);
	var templateText=window.isIE?template.value:template.textContent;
	templateText=ECTableUtil.trimString(templateText);

if (templateText.toLowerCase().indexOf("<select ")==0 ){
	// todo:
	templateText=templateText.substring(8);
	templateText=ECTableUtil.replaceAll(templateText," name=\""," tempname=\"");
	templateText=ECTableUtil.replaceAll(templateText," id=\""," tempid=\"");
	templateText=ECTableUtil.replaceAll(templateText," style=\""," tempstyle=\"");
	templateText=ECTableUtil.replaceAll(templateText," class=\""," tempclass=\"");
	templateText=ECTableUtil.replaceAll(templateText," value=\""+v+"\""," value=\""+v+"\" selected=\"selected\" ");

	filterHTML+="<select onclick=\"ECTableUtil_stopEvent(event)\" style=\"width:"+(menuWidth- FIX_WIDTH)+"px\" filterfield=\"true\" onkeypress=\""+jsfunction+"\" "+templateText;
}else{
	filterHTML+="<input type=\"text\" class=\"filterInput\" value=\""+v+"\" style=\"width:"+(menuWidth- FIX_WIDTH)+"px\" onclick=\"ECTableUtil.doFilterFocus(event,this)\" onkeypress=\""+jsfunction+"\" /></nobr>";
}
	var clearHTML="<nobr><input type=\"button\" class=\"clearIcon\" />";
	clearHTML+="<span class=\"itemText\" style=\"width:"+(menuWidth- FIX_WIDTH)+"px\" onclick=\"ECTableUtil.doClearFilter(event,this,'"+filterFieldName+"','"+formid+"')\" >"+ECTableMessage.FILTERCLEAR_TEXT+"</span></nobr>";

	return filterHTML+"<br />"+clearHTML;

};

ECTableUtil.ColmunMenu.getSortItem=function(menuWidth,columnObj,columnName,formid){

		var FIX_WIDTH=30;

	var sortFieldName=formid+ECTableConstants.SORT_PREFIX+columnName;
	var sortField=$_E(sortFieldName);
	if (!sortField){
		return "";
	}

	var jsfunctionAsc="ECTableUtil.doSort(event,'"+columnName+"','asc','"+formid+"')";
	var jsfunctionDesc="ECTableUtil.doSort(event,'"+columnName+"','desc','"+formid+"')";
	var jsfunctionDefault="ECTableUtil.doSort(event,'"+columnName+"','default','"+formid+"')";


	var ascHTML="<nobr><input type=\"button\" class=\"ascIcon\" />";
	ascHTML+="<span class=\"itemText\" style=\"width:"+(menuWidth- FIX_WIDTH)+"px\" onclick=\""+jsfunctionAsc+"\" >"+ECTableMessage.SORTASC_TEXT+"</span></nobr>";

	var descHTML="<nobr><input type=\"button\" class=\"descIcon\" />";
	descHTML+="<span class=\"itemText\" style=\"width:"+(menuWidth- FIX_WIDTH)+"px\" onclick=\""+jsfunctionDesc+"\" >"+ECTableMessage.SORTDESC_TEXT+"</span></nobr>";


	var defaultHTML="<nobr><input type=\"button\" class=\"defaultIcon\" />";
	defaultHTML+="<span class=\"itemText\" style=\"width:"+(menuWidth- FIX_WIDTH)+"px\" onclick=\""+jsfunctionDefault+"\" >"+ECTableMessage.SORTDEFAULT_TEXT+"</span></nobr>";

	return ascHTML+"<br />"+descHTML+"<br />"+defaultHTML;

};

ECTableUtil.ColmunMenu.showMe=function(fireObj,formid){
	var minWidth=100;
	var FIX_WIDTH=5;
	var menuWidth=0;

	var columnName=fireObj.getAttribute("columnName");
	if(this.currentContent==formid+" : "+columnName && this.isShow){
		ECTableUtil.ColmunMenu.doHideMe();
		return;
	}


	menuWidth =fireObj.offsetWidth- FIX_WIDTH;
	menuWidth=menuWidth< minWidth? minWidth :menuWidth;


	var menuZone=this.coreElement.rows[0].cells[0];

	var filterItemHTML=this.getFilterItem(menuWidth,fireObj,columnName,formid);
	var sortItemHTML=this.getSortItem(menuWidth,fireObj,columnName,formid);

	if (filterItemHTML=="" && sortItemHTML==""){
		return;
	}
	var menuHTML=filterItemHTML+"<hr />" + sortItemHTML;

	menuZone.innerHTML= menuHTML ;

	this.coreElement.style.top= ECTableUtil.getPosBottom(fireObj)+document.body.scrollTop +"px";
	this.coreElement.style.display="block";
	var tempLeft=ECTableUtil.getPosLeft(fireObj)+document.body.scrollLeft;
	if ( tempLeft+this.coreElement.offsetWidth >=document.body.clientWidth-2){
		tempLeft=document.body.clientWidth-2-this.coreElement.offsetWidth;
	}
	this.coreElement.style.left=tempLeft+"px";

	this.setShow();
	this.currentContent=formid+" : "+columnName;

};



/* ============ RESIZE COLUMN WIDTH ======================= */

ECTableUtil.startDragobj=false;
ECTableUtil.MinColWidth=ECTableConstants.MIN_COLWIDTH;


ECTableUtil.Dragobj=null;
ECTableUtil.DragobjBodyCell=null;

ECTableUtil.DragobjBodyCellNext=null;
ECTableUtil.DragobjNext=null;

ECTableUtil.DragECTableObj=null;


ECTableUtil.leftC=0;
ECTableUtil.rightC=0;

ECTableUtil.startC=0;
ECTableUtil.endC=0;

ECTableUtil.StartResize=function(event,obj,formid){
	var e = event||window.event;
	if (!formid){
		formid=ECTableConstants.EC_ID;
	}

	obj.focus();
	document.body.style.cursor = "col-resize";
	var dx=window.isIE?e.x:e.pageX;

	ECTableUtil.DragECTableObj=ECTableList[formid];

	ECTableUtil.Dragobj=obj.parentNode;
	ECTableUtil.DragobjNext = ECTableUtil.getNextElement(ECTableUtil.Dragobj);

	var way=ECTableUtil.DragECTableObj.resizeWay;
	//ECTableUtil.startC=e.screenX;

	ECTableUtil.leftC =ECTableUtil.getPosLeft( ECTableUtil.Dragobj )+parseInt(ECTableUtil.MinColWidth);
	if (way=="resizeTable" || !ECTableUtil.DragobjNext){
		if (ECTableUtil.DragECTableObj.ECListBodyZone){
			ECTableUtil.rightC =ECTableUtil.getPosRight(ECTableUtil.DragECTableObj.ECListBodyZone);
		}else{
			ECTableUtil.rightC=document.body.clientWidth;
		}

	}else{
		ECTableUtil.rightC =ECTableUtil.getPosRight( ECTableUtil.DragobjNext )-parseInt(ECTableUtil.MinColWidth);
	}
	ECTableUtil.leftC+=document.body.scrollLeft;
	ECTableUtil.rightC+=document.body.scrollLeft;

	var cellIndex=ECTableUtil.Dragobj.cellIndex;
	try{
		 ECTableUtil.DragobjBodyCell=ECTableList[formid].ECListBody.rows[0].cells[cellIndex];
	}catch(e){
		ECTableUtil.DragobjBodyCell=null;
	}
	try{
		 ECTableUtil.DragobjBodyCellNext=ECTableUtil.getNextElement(ECTableUtil.DragobjBodyCell);
	}catch(e){
		ECTableUtil.DragobjBodyCellNext=null;
	}


	ECTableUtil.MinColWidth=ECTableList[formid].MIN_COL_WIDTH;
	if (!ECTableUtil.MinColWidth||ECTableUtil.MinColWidth=='' || ECTableUtil.MinColWidth<1){
		ECTableUtil.MinColWidth=ECTableConstants.MIN_COLWIDTH;
	}


	ECTableUtil.separateLine.style.top=ECTableUtil.getPosTop(ECTableUtil.DragECTableObj.ECListHead)+2;



var dX=window.isIE?document.body.scrollLeft+e.clientX:document.body.scrollLeft+e.pageX;

//ECTableUtil.startC=ECTableUtil.getPosRight( ECTableUtil.Dragobj )-ECTableUtil.parseIntOrZero(ECTableUtil.separateLine.style.width)+document.body.scrollLeft-ECTableUtil.DragECTableObj.ECListBodyZone.scrollLeft;
ECTableUtil.startC=dX;
ECTableUtil.separateLine.style.left=ECTableUtil.startC+"px";

	var th=ECTableUtil.DragECTableObj.ECListHead.parentNode.clientHeight;
	if (ECTableUtil.DragECTableObj.ECListHead.parentNode!=ECTableUtil.DragECTableObj.ECListBody.parentNode){
		th+=ECTableUtil.DragECTableObj.ECListBodyZone.clientHeight;
	}
	ECTableUtil.separateLine.style.height=th+'px';
	ECTableUtil.separateLine.style.display="block";

	ECTableUtil.startDragobj=true;

ECTableUtil_stopEvent(e);

}


ECTableUtil.DoResize=function(event){

	var e = event||window.event;

var dX=window.isIE?document.body.scrollLeft+e.clientX:document.body.scrollLeft+e.pageX;


	if (!ECTableUtil.Dragobj || !ECTableUtil.startDragobj){
		if (ECTableUtil.separateLine){
			ECTableUtil.separateLine.style.display="none";
		}
		document.body.style.cursor = "";
		return;
	}

	if (dX<=ECTableUtil.leftC || dX>=ECTableUtil.rightC){
		document.body.style.cursor = "not-allowed";
		return;
	}
	if (document.body.style.cursor == "not-allowed"){
		document.body.style.cursor = "col-resize";
	}


	ECTableUtil.separateLine.style.left=dX+"px";

}

ECTableUtil.EndResize=function(event){

if (!ECTableUtil.Dragobj){
	ECTableUtil.startDragobj=false;
	document.body.style.cursor = "";
	return;
}


	var e = event||window.event;
	//ECTableUtil.endC=e.screenX;



	ECTableUtil.endC=ECTableUtil.parseIntOrZero(ECTableUtil.separateLine.style.left);



var dWidth=ECTableUtil.startC-ECTableUtil.endC;

var fixX=0;
if (window.isIE){
	fixX = ECTableConstants.IE_WIDTH_FIX_A;

}else{
	fixX= ECTableConstants.FF_WIDTH_FIX_A;
}

var cc=0;

cc=ECTableUtil.DragobjBodyCell.clientWidth-dWidth+fixX;
ECTableUtil.Dragobj.style.width=cc+"px";
ECTableUtil.DragobjBodyCell.style.width=cc+"px";
ECTableUtil.DragobjBodyCell.width = cc+"px";

/*
	if (ECTableUtil.DragobjNext && ECTableUtil.DragECTableObj.resizeWay!="resizeTable"){
		cc=ECTableUtil.DragobjBodyCellNext.clientWidth+dWidth+fixX;
		if (cc<10){
			cc=10;
		}
		ECTableUtil.DragobjNext.style.width=cc+"px";
		ECTableUtil.DragobjBodyCellNext.style.width=cc+"px";
		ECTableUtil.DragobjBodyCellNext.width = cc+"px";
	}
*/
if (!ECTableUtil.DragECTableObj.isClassic){
ECTableUtil.syncRowsWidth(ECTableUtil.DragECTableObj.ECListHead.rows,ECTableUtil.DragECTableObj.ECListBody.rows);
}


	document.body.style.cursor = "";
	ECTableUtil.separateLine.style.display="none";


ECTableUtil.DragECTableObj.handleResize();

	try{
//ECTableUtil.DragECTableObj.initScrollXscrollWidth();
//ECTableUtil.DragECTableObj.initScrollBarSize();
}catch(e){}

	ECTableUtil.startDragobj=false;
	ECTableUtil.Dragobj=null;

ECTableUtil.DragECTableObj=null;
ECTableUtil.DragobjBodyCell=null;
ECTableUtil.DragobjBodyCellNext=null;
ECTableUtil.DragobjNext=null;

ECTableUtil_stopEvent(e);
}


ECTableUtil.resizeInit=function(){
	document.onmousemove = ECTableUtil.DoResize;
	document.onmouseup = ECTableUtil.EndResize;
	document.body.ondrag = function() {return false;};

    document.body.onselectstart = function() {
		return ECTableUtil.Dragobj==null && ECTableUtil.startDragobj==false;
	};


	/*
		var e = event||window.event;
 e.cancelBubble = true
 e.returnValue = false;
	return   false;
	*/
};





/* ===========EDIT CELL ============= */
ECTableUtil.getColumnName=function(cellObj,formid){
	var ectableObj=ECTableUtil.getGridObj(formid);
	var cname='';
	try {
		var idx=cellObj.cellIndex;
		var cell=ectableObj.ECListHead.rows[0].cells[idx];
		cname=cell.getAttribute("columnName");
	}catch(e){
		cname='';
	}
	return cname;
};

// editType =  input select checkbox radio
ECTableUtil.editCell=function(cellObj,formid,templateId){
	if (cellObj.getAttribute("editing")=="true"){
		return;
	}
	var ectableObj=ECTableUtil.getGridObj(formid);

	cellObj.setAttribute("editing","true");
	if (!templateId){
		var idx=cellObj.cellIndex;
		var thcell=ectableObj.ECListHead.rows[0].cells[idx];
		templateId=thcell.getAttribute("editTemplate");
	}
	var template=document.getElementById(templateId);

	var templateText=window.isIE?template.value:template.textContent;

templateText=ECTableUtil.trimString(templateText);

	var text=window.isIE?cellObj.innerText:cellObj.textContent;
	var value=cellObj.getAttribute("cellValue");
	value=value==null?text:value;

	value=ECTableUtil.trimString(value);

	var name=ECTableUtil.getColumnName(cellObj,formid);
	if (templateText.indexOf("name=\"\"")>0){
		templateText=ECTableUtil.replaceAll(templateText,"name=\"\"","name=\""+name+"\"");
	}



var editType="input";

if (templateText.toLowerCase().indexOf("<input ")==0 ){
	if (templateText.indexOf(" type=\"checkbox\"")>0){
		editType="checkbox";
	}else if(templateText.indexOf(" type=\"radio\"")>0){
		editType="radio";
	}
}else if (templateText.toLowerCase().indexOf("<select ")==0 ){
	editType="select";
}

	if (editType=="input"){
		cellObj.innerHTML=ECTableUtil.replaceAll(templateText,"value=\"\"","value=\""+value+"\"");
	}else if (editType=="select"){
		cellObj.innerHTML=ECTableUtil.replaceAll(templateText,"value=\""+value+"\"","value=\""+value+"\" selected=\"selected\"");
	}else if (editType=="checkbox" || editType=="radio"){
		cellObj.innerHTML=ECTableUtil.replaceAll(templateText,"value=\""+value+"\"","value=\""+value+"\" checked=\"checked\"");
	}



	var errMSG=cellObj.getAttribute("errMSG");

	var checkEXP=cellObj.getAttribute("checkEXP");
    if (checkEXP!=null  &&errMSG !=null){
	    checkEditForm.add(ECTableUtil.getFirstChildElement(cellObj),errMSG,checkEXP);

	}
ECTableUtil.getFirstChildElement(cellObj).focus();


};


ECTableUtil.updateEditCell=function(cellEditObj,editType){

	var cellObj=cellEditObj.parentNode;
    if (cellObj.getAttribute("checkEXP")){
		if(checkEditForm.groupcheck(cellEditObj)==false){
		    ECTableUtil.getFirstChildElement(cellObj).focus();
			return;
		}
		else
			checkEditForm.del(cellEditObj);

	}

	if (cellEditObj.getAttribute("filterfield")=="true"){
		return;
	}


	ECTableUtil.updateCellContent(cellObj,cellEditObj);

	cellObj.setAttribute("edited","true");
	cellObj.parentNode.setAttribute("edited","true");
	cellObj.setAttribute("editing","false");
	ECTableUtil.addClass(cellObj, "editedCell");
};



ECTableUtil.updateCell=function(cellObj){
	var elems=Form.getElements(cellObj);
	if (elems.length>0){
		var cellEditObj=elems[0];
		ECTableUtil.updateCellContent(cellObj,cellEditObj);
	}
};

ECTableUtil.updateCellContent=function(cellObj,elementObj){

		var editType=elementObj.tagName.toLowerCase();
		if (editType=="input"){
			var type=elementObj.type.toLowerCase();
			if (type=='checkbox' || type=='radio'){
				editType=type;
			}
		}
		var value=elementObj.value;
		if (editType=="input"){
			cellObj.innerHTML=elementObj.value;
		}else if (editType=="select"){
			value=elementObj.options[elementObj.selectedIndex].value;
			cellObj.innerHTML=elementObj.options[elementObj.selectedIndex].text;
		}else if (editType=="checkbox" || editType=="radio"){
			cellObj.innerHTML=elementObj.nextSibling.nodeValue;
		}else{
			cellObj.innerHTML=elementObj.innerHTML;
		}
		cellObj.setAttribute("cellValue",ECTableUtil.trimString(value));
};

ECTableUtil.getUpdatedRows=function(formid){
	var ectableObj=ECTableUtil.getGridObj(formid);
	var erows=[];
	if (ectableObj && ectableObj.ECListBody){
		var rs=ectableObj.ECListBody.rows;
		for (var i=0;i<rs.length;i++){
			if (rs[i].getAttribute("edited")=="true"){
				erows.push(rs[i]);
			}
		}
	}
	return erows;
};


ECTableUtil.getDeletedRows=function(formid){
	var ectableObj=ECTableUtil.getGridObj(formid);
	var erows=[];
	if (ectableObj && ectableObj.ECListBody){
		var rs=ectableObj.ECListBody.rows;
		for (var i=0;i<rs.length;i++){
			if (ECTableUtil.hasClass(rs[i],"del")){
				erows.push(rs[i]);
			}
		}
	}
	return erows;
};

ECTableUtil.getRemoveUpdatedClassRows=function(listRows,recordKey){

	if (listRows && listRows.length){
		for (var i=0;i<listRows.length;i++){
			if (listRows[i].getAttribute("recordKey")==ECTableUtil.trimString(recordKey)){

				listRows[i].setAttribute("edited",null);
				ECTableUtil.clearRowEditedFlag(listRows[i]);
			}
		}
	}
};

ECTableUtil.getRemoveInsertedClassRows=function(listRows,recordKey){

	if (listRows && listRows.length){
		for (var i=0;i<listRows.length;i++){
			var cells=listRows[i].cells;
			for (var j=0;j<cells.length ;j++ ){
				ECTableUtil.updateCell(cells[j]);
			}
			listRows[i].className="added";
		}
	}
};


ECTableUtil.clearRowEditedFlag=function(rowObj){
	var cs=rowObj.cells;
	for (var i=0;i<cs.length;i++){
		cs[i].setAttribute("edited","false");
		ECTableUtil.removeClass(cs[i], "editedCell");
	}
};

ECTableUtil.getRemoveDeletedRows=function(listRows,recordKey){

	if (listRows && listRows.length){
		for (var i=0;i<listRows.length;i++){
			var crow=listRows[i];
			if (crow && crow.getAttribute("recordKey")==ECTableUtil.trimString(recordKey) && ECTableUtil.hasClass(crow,"del")){
				var crowIndex=crow.rowIndex;
				if (crow.getAttribute("hasShadow")=="true" ){
					crow.parentNode.removeChild(crow.parentNode.rows[crowIndex+1]);
				}
				crow.parentNode.removeChild(crow);
			}
		}
	}
};

ECTableUtil.getRowCellsMap=function(rowObj,formid){
	var cellMap={};
	var keyvalue=rowObj.getAttribute("recordKey");
	if (keyvalue){
		cellMap["recordKey"]=keyvalue;
	}
	var cells=rowObj.cells;
	for (var i=0;i<cells.length;i++ ){
		var cellObj=cells[i];
		var name=ECTableUtil.getColumnName(cellObj,formid);
		if (!name){
			continue;
		}
		var value=cellObj.getAttribute("cellValue");
		if (!value){
			value=window.isIE?cellObj.innerText:cellObj.textContent;
		}
		if (cellMap[name]==undefined){
			cellMap[name]=value;
		}else if(ECTableUtil.isCollection(cellMap[name]) ){
			cellMap[name].push(value);
		}else{
			var temp_v=cellMap[name];
			cellMap[name]=[temp_v];
			cellMap[name].push(value);
		}

	}
	return cellMap;
};


ECTableUtil.getInsertRows=function(formid){

	var ectableObj=ECTableUtil.getGridObj(formid);
	var erows=[];
	if (ectableObj && ectableObj.ECListBody){
		var rs=ectableObj.ECListBody.rows;
		for (var i=0;i<rs.length;i++){
			if (ECTableUtil.hasClass(rs[i],"add")){
				erows.push(rs[i]);
			}
		}
	}
	return erows;
};


ECTableUtil.updateShadowRow=function(crow,shadowRow,formid,originalRequest){
	var text=originalRequest.responseText;

	if (ECTableUtil.trimString(text)!=''){
		shadowRow.cells[0].innerHTML=text;
	}else{
		shadowRow.cells[0].innerHTML= ECTableMessage.SHADOWROW_FAILED+" ( recordKey : "+crow.getAttribute("recordKey")+").";
	}


	var shadowRowHeight=ECTableUtil.parseIntOrZero(shadowRow.cells[0].scrollHeight);
	shadowRow.setAttribute("shadowRowHeight",shadowRowHeight);
	shadowRow.cells[0].style.height=shadowRowHeight+"px";


	var shadowRowHeight=ECTableUtil.parseIntOrZero(shadowRow.getAttribute("shadowRowHeight"));
var ectableObj=ECTableUtil.getGridObj(formid);
ectableObj.hideWaitingBar();

	//ECTableUtil.changeListHeight("+"+shadowRowHeight ,formid);
};

ECTableUtil.showShadowRow=function(crow,eventSrc,formid){
		var ectableObj=ECTableUtil.getGridObj(formid);


		if (!crow && !eventSrc){
			return;
		}
		if(typeof(crow)=="string" ){
			crow=document.getElementById(crow);
		}
		if(typeof(eventSrc)=="string" ){
			eventSrc=document.getElementById(eventSrc);
		}
		if (crow.tagName.toLowerCase()=="td"){
			crow=crow.parentNode;
		}
		var crowIndex=crow.rowIndex;

		if (!ectableObj.isClassic){
			crowIndex++;
		}
		var cellnum=crow.cells.length;

		var hasShadow=crow.getAttribute("hasShadow");
		var shadowRow=null;
		var isShowed=true;

		if (hasShadow=="true"){
			shadowRow=crow.parentNode.rows[crowIndex];
			if (shadowRow.style.display=="none"){
				shadowRow.style.display="";
				//if (ectableObj.showShadowRowCallBack){
					//ectableObj.showShadowRowCallBack(formid,crow,shadowRow,eventSrc);
				//}
				//alert(1)
				eventSrc.className="shadowRowButtonOpen";
			}else{
				shadowRow.style.display="none";
				//if (ectableObj.hideShadowRowCallBack){
					//ectableObj.hideShadowRowCallBack(formid,crow,shadowRow,eventSrc);
				//}
				//alert(2)
				eventSrc.className="shadowRowButtonClose";
				isShowed=false;
			}

		}else{
			//alert(3)
			shadowRow=crow.parentNode.insertRow(crowIndex);
			shadowRow.className="shadowRow";
			shadowRow.style.display="";
			var newcell=document.createElement("td");
			newcell.colSpan=cellnum;
			shadowRow.appendChild(newcell);
			crow.setAttribute("hasShadow","true");
			shadowRow.setAttribute("isShadow","true");

			var url=ectableObj.ECForm.getAttribute("shadowRowAction");
			var pars=ECTableUtil.getRowCellsMap(crow,formid);
			var updateShadowRow=ECTableUtil.updateShadowRow.bind(this,crow,shadowRow,formid);
			ECTableUtil.doAjaxUpdate(url,pars,updateShadowRow,formid);

			eventSrc.className="shadowRowButtonOpen";

			//if (ectableObj.firstShowShadowRowCallBack){
			//	ectableObj.firstShowShadowRowCallBack(formid,crow,shadowRow,eventSrc);
			//}

		}
		if ( isShowed && ectableObj.autoCloseOtherShadowRow ) {
			if (ectableObj.currentShadowRowParent && ectableObj.currentShadowEventSrc && crow.id!=ectableObj.currentShadowRowParent  )	{
				ECTableUtil.showShadowRow(ectableObj.currentShadowRowParent,ectableObj.currentShadowEventSrc,ectableObj.id);
			}
		}
		if (isShowed){
			ectableObj.currentShadowRowParent=crow.id;
			ectableObj.currentShadowEventSrc=eventSrc.id;
		}else{
			ectableObj.currentShadowRowParent=null;
			ectableObj.currentShadowEventSrc=null;
		}

	};

/////////////////////////////////////////////////



ECTableUtil.saveGridBatch=function(buttonObj,formid,isBatch){
	if(!confirm(ECTableMessage.UPDATE_CONFIRM)){
		return;
	}

	var ectableObj=ECTableUtil.getGridObj(formid);
	var form=ectableObj.ECForm;

	var urlu=form.getAttribute("updateAction")+"";

	var rows=ECTableUtil.getUpdatedRows(formid);

	var urli=form.getAttribute("insertAction")+"";

	var rowsi=ECTableUtil.getInsertRows(formid);

	var urld=form.getAttribute("deleteAction")+"";

	var rowsd=ECTableUtil.getDeletedRows(formid);

	var continueFunction=true;
	if (ectableObj.beforeSave){
			continueFunction=ectableObj.beforeSave(formid,rows,rowsi,rowsd);
	}
	if (continueFunction===false){
		return;
	}

	var parsMap;

	if (isBatch==null || isBatch==window.undefined)	{
		isBatch=true;
	}
	var doBatch = isBatch;

	ectableObj.forUpdateRows=rows;
	parsMap={};


	for (var i=0;i<rows.length;i++){
		var pars=ECTableUtil.getRowCellsMap(ectableObj.forUpdateRows[i],formid);
		if (doBatch){
			ECTableUtil.appendMap(parsMap,pars);
		}else{

			ECTableUtil.doAjaxUpdate(urlu,pars,ectableObj.updateCallBack,formid);

		}

	}
	if (doBatch && rows.length>0){
		ECTableUtil.doAjaxUpdate(urlu,parsMap,ectableObj.updateCallBack,formid);
	}

/* ============== */

	ectableObj.forInsertRows=rowsi;
	parsMap={};


	for (var i=0;i< rowsi.length;i++){
		var pars=Form.serialize(ectableObj.forInsertRows[i],true);
		if (doBatch){
			ECTableUtil.appendMap(parsMap,pars);
		}else{
            if( checkAddForm.groupcheckall()==true)
			    ECTableUtil.doAjaxUpdate(urli,pars,ectableObj.insertCallBack,formid);
		}

	}
	if (doBatch && rowsi.length>0){
         if( checkAddForm.groupcheckall()==true)
		    ECTableUtil.doAjaxUpdate(urli,parsMap,ectableObj.insertCallBack,formid);
	}


/* ============== */


	ectableObj.forDeleteRows = rowsd;
	parsMap={};

	for (var i=0;i< rowsd.length;i++){
		var pars=ECTableUtil.getRowCellsMap(ectableObj.forDeleteRows[i],formid);
		if (doBatch){
			ECTableUtil.appendMap(parsMap,pars);
		}else{
			ECTableUtil.doAjaxUpdate(urld,pars,ectableObj.deleteCallBack,formid);
		}
	}
	if (doBatch && rowsd.length>0){
		ECTableUtil.doAjaxUpdate(urld,parsMap,ectableObj.deleteCallBack,formid);
	}


/* ============== */

	if (rows.length<1){
		ectableObj.forUpdateRows=[];
	}
	if (rowsi.length<1){
		ectableObj.forInsertRows=[];
	}
	if (rowsd.length<1){
		ectableObj.forDeleteRows=[];
	}

	if ( rows.length<1 && rowsi.length<1 && rowsd.length<1 ){
		alert(ECTableMessage.NO_RECORD_UPDATE);
	}

};

ECTableUtil.saveGrid=ECTableUtil.saveGridBatch;
//ECTableUtil.saveGrid=ECTableUtil.saveGridSingle;

ECTableUtil.delFromGrid=function(buttonObj,formid,deleteFlags){

	var ectableObj=ECTableUtil.getGridObj(formid);
	var form=ectableObj.ECForm;

	if (!deleteFlags){
		deleteFlags=ectableObj.deleteFlags;
	}
	var checkBoxList=form[deleteFlags];

	var crow=ectableObj.selectedRow;
	if (crow && ECTableUtil.hasClass(crow,"added")){
		return;
	}
	if (crow && ECTableUtil.hasClass(crow,"add")){
		crow.parentNode.removeChild(crow);
		return;
	}

	if ( (!deleteFlags || !checkBoxList ) && crow){
		if (ECTableUtil.hasClass(crow,"del")){
			ECTableUtil.removeClass(crow,"del");
		}else{
			ECTableUtil.addClass(crow,"del");
		}
		return;
	}

	if (!checkBoxList){
		return ;
	}
	if ( ! ECTableUtil.isCollection(checkBoxList) ){
		checkBoxList=[checkBoxList];
	}

	for (var i=0;i<checkBoxList.length;i++){
		if (checkBoxList[i].checked){
			var rowObj=checkBoxList[i].parentNode.parentNode;
			if (ECTableUtil.hasClass(rowObj,"del")){
				ECTableUtil.removeClass(rowObj,"del");
			}else{
				ECTableUtil.addClass(rowObj,"del");
			}
			rowObj.className="del";
		}
	}

};

ECTableUtil.addToGrid = function(buttonObj,templateId,formid){
	var ectableObj =ECTableUtil.getGridObj(formid);
	var template=document.getElementById(templateId);

	if (!template){
		template= document.getElementById(ECTableConstants.DEFALUT_ADD_TEMPLATE);
	}

	template=template.value;

	var rowsNum=0;
	if (ectableObj.ECListBody.rows){
		rowsNum=ectableObj.ECListBody.rows.length

	}
	var newTr=ectableObj.ECListBody.insertRow(rowsNum);
	ECTableUtil_addEvent( newTr,"click", ECTableUtil.selectRow.bind(this,newTr,ectableObj.id) );

	template=template.split("<tpsp />");
	newTr.className="add";
	var cells=[];

	for (var i=0;i<ectableObj.columnNum;i++ ){
		 var thcell=ectableObj.ECListHead.rows[0].cells[i];
		 var errMSG=thcell.getAttribute("errMSG");
	     var checkEXP=thcell.getAttribute("checkEXP");
         var cid=thcell.getAttribute("columnName");
		 var id=""+cid+rowsNum;

	     if (template[i].indexOf("id=\"\"")>0){
		     template[i]=ECTableUtil.replaceAll(template[i],"id=\"\"","id=\""+id+"\"");
	     }
	    cells[i]=newTr.insertCell(i);
		cells[i].innerHTML=template[i];


        if (checkEXP!=null  &&errMSG !=null){
	          checkAddForm.add(ECTableUtil.getFirstChildElement(cells[i]),errMSG,checkEXP);
		}
	}



	var topTr=ECTableUtil.getPosTop(newTr);
	if (ectableObj.ECListBodyZone){
		ectableObj.ECListBodyZone.scrollTop=topTr;
	}

}



ECTableUtil.doAjaxUpdate=function(url,pars,callBack,formid){
	var ectableObj=ECTableUtil.getGridObj(formid);

	var continueFunction=true;
	if (ectableObj.beforeSubmit){
		continueFunction=ectableObj.beforeSubmit(pars,formid);
	}
	if (continueFunction===false){
		return;
	}

		var myAjax = new Ajax.Request( url,{
			requestHeaders:ECTableConstants.AJAX_HEADER,
			method: "POST",
			asynchronous: true ,
			parameters: pars,
			onComplete: callBack } );
		ectableObj.showWaitingBar();
}

// It's like Function.prototype.bind.(prorotype.js)
ECTableUtil.bindFunction=function(functionObj){
	var newArgumentsT=[];
	for (var j=1;j< arguments.length;j++ ){
		newArgumentsT.push(arguments[j]);
	}
	return function(){
		for (var i = 0; i < arguments.length; i++) {
			if ( typeof(arguments[i])!="undefined" /* i!=1 ||*/ ){
				newArgumentsT[i]=arguments[i];
			}
		}
		return functionObj.apply(this,newArgumentsT);
	};
};

ECTableUtil.changeListHeight=function(height,formid){
	var ectableObj=ECTableUtil.getGridObj(formid);
		height=height+"";

		if ( "auto"!=height){
			if (height.indexOf('+')==0){
				height=ectableObj.listHeight+ ECTableUtil.parseIntOrZero(height.substring(1));
			}else if (height.indexOf('-')==0){
				height=ectableObj.listHeight- ECTableUtil.parseIntOrZero(height.substring(1));
			}else if (height=="reset"){
				height=ectableObj.orgListHeight;
			}
		}
		if (ECTableUtil.parseIntOrZero(height)>ectableObj.ECListBody.scrollHeight-ECTableConstants.OFFSET_A || height=="auto"){
			/* divSYT.style.overflowY="hidden"; */
			height=ectableObj.ECListBody.parentNode.scrollHeight;
			var dh=ectableObj.ECListBodyZone.offsetHeight-ectableObj.ECListBodyZone.clientHeight+ECTableConstants.LIST_HEIGHT_FIXED;
			if (dh <=2 && Me.ECListBodyZone.offsetWidth-Me.ECListBodyZone.clientWidth>2){
				dh=ECTableConstants.SCROLLBAR_WIDTH;
			}

			height=height/1+dh;
		}
		height=height<ectableObj.minHeight/1?ectableObj.minHeight/1:height;
		if (ectableObj.ECListBodyZone){
			ectableObj.ECListBodyZone.style.height=height+"px";
		}
		ectableObj.listHeight=height;
		ectableObj.handleResize();
};

ECTableUtil.onWindowload=function(){
	ECTableUtil.initAllGrid();
	ECTableUtil.ColmunMenu.initMe();
	ECTableUtil.NearPagesBar.initMe();
	ECTableUtil_addEvent(document.body,"click", ECTableUtil.ColmunMenu.doHideMe);
	ECTableUtil_addEvent(window,"resize",ECTableUtil.ColmunMenu.doHideMe);
};


ECTableUtil.initAllGrid=function(){
	for (var gridId in ECTableList ){
		var grid=ECTableList[gridId];
		grid.init();
	}
};

ECTableUtil.createGrid=function(formid){
	var grid=ECTableUtil.getGridObj(formid);
	if (!grid){
		grid=new ECTable(formid);
	}
	return grid;
};

ECTableUtil_addEvent(window,"resize",ECTableUtil.resizeAllGrid);
ECTableUtil_addEvent(window,"load", ECTableUtil.onWindowload);

ECTableUtil.showCalendar=function(inputObj,dateFormat){
	if(typeof dateFormat =="undefined" ){
		dateFormat="%Y-%m-%d";
	}
    Calendar.trigger({
        inputField     :    inputObj.previousSibling.name,      // id of the input field
        ifFormat       :   dateFormat,       // format of the input field
        showsTime      :   true,            // will display a time selector
        button         :    "date_button",   // trigger for the calendar (button ID)
        singleClick    :    true,
		 date           : 	  new Date(),
		onClose	: ECTableUtil.fillDate,
        step           :    1                // show all years in drop-down boxes (instead of every other year as default)
    });

};
ECTableUtil.addCalendar=function(inputObj,dateFormat){
	if(typeof dateFormat =="undefined" ){
		dateFormat="%Y-%m-%d";
	}
    	Calendar.trigger({
        inputField     :  inputObj.previousSibling.id,      // id of the input field
        ifFormat       :    dateFormat,       // format of the input field
        showsTime      :     true,            // will display a time selector
        button         :    "date_button",   // trigger for the calendar (button ID)
        singleClick    :    true,

	      step           :    1                // show all years in drop-down boxes (instead of every other year as default)
    });

};

ECTableUtil.fillDate=function(calObj){

	ECTableUtil.updateEditCell(calObj.inputField);
	calObj.hide();
}


/* for compatibility */
//var ECCN = ECTable ;

var ECCN=function(formid){
/*

eccn.ajaxSubmit=false;
eccn.doPrep=false;
eccn.doPrepPrev=false;

*/
 return ECTableUtil.getGridObj(formid);

};
ECCN.init=function(){};

var ECTableUtil = ECTableUtil;
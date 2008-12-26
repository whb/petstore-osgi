<%
     String path = request.getContextPath();
     String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ path + "/";

    String sLang=request.getLocale().getLanguage();
    String fileName;
    java.util.List supported=new java.util.ArrayList();
         supported.add("zh");
         supported.add("en");
         if(supported.contains(sLang)){
        	 fileName="extremecomponents_msg_"+sLang+".js";
         }
         else{
        	 fileName="extremecomponents_msg_zh.js";
         }

%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="contextPath" value="<%=basePath%>" />
<link rel="stylesheet" type="text/css" href="${ctx}/extremecomponents/css/ectable_style.css" />
<script type="text/javascript" src="${ctx}/extremecomponents/js/prototype_mini.js" ></script>
<script type="text/javascript" src="${ctx}/extremecomponents/js/<%=fileName%>" ></script>
<script type="text/javascript" src="${ctx}/extremecomponents/js/extremecomponents.js" ></script>
<script type="text/javascript" src="${ctx}/scripts/validation/validate.js"></script>
<link rel="stylesheet" type="text/css" 	href="${ctx}/scripts/validation/style.css"></link>
</div>

<div id="Footer">

  <div id="PoweredBy">
    <a href="mailto:zhouweipark@gmail.com"><img src="../images/poweredby.gif"/></a>
  </div>

  <div id="Banner">
    <logic:present name="accountForm" scope="session">
      <logic:equal name="accountForm" property="authenticated" value="true">
        <logic:equal name="accountForm" property="account.bannerOption" value="true">
          <bean:write filter="false" name="accountForm" property="account.bannerName"/>
        </logic:equal>
      </logic:equal>
    </logic:present>
  </div>

</div>

</body>
</html>
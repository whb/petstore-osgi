<h3>Account Information</h3>

<table>
  <tr>
    <td>First name:</td><td><html:text name="accountForm" property="account.firstName"/></td>
  </tr><tr>
  <td>Last name:</td><td><html:text name="accountForm" property="account.lastName"/></td>
</tr><tr>
  <td>Email:</td><td><html:text size="40" name="accountForm" property="account.email"/></td>
</tr><tr>
  <td>Phone:</td><td><html:text name="accountForm" property="account.phone"/></td>
</tr><tr>
  <td>Address 1:</td><td><html:text size="40" name="accountForm" property="account.address1"/></td>
</tr><tr>
  <td>Address 2:</td><td><html:text size="40" name="accountForm" property="account.address2"/></td>
</tr><tr>
  <td>City:</td><td><html:text name="accountForm" property="account.city"/></td>
</tr><tr>
  <td>State:</td><td><html:text size="4" name="accountForm" property="account.state"/></td>
</tr><tr>
  <td>Zip:</td><td><html:text size="10" name="accountForm" property="account.zip"/></td>
</tr><tr>
  <td>Country:</td><td><html:text size="15" name="accountForm" property="account.country"/></td>
</tr>
</table>

<h3>Profile Information</h3>

<table>
  <tr>
    <td>Language Preference:</td><td>
    <html:select name="accountForm" property="account.languagePreference">
      <html:options name="accountForm" property="languages"/>
    </html:select></td>
  </tr><tr>
  <td>Favourite Category:</td><td>
  <html:select name="accountForm" property="account.favoriteCategoryId">
    <html:options name="accountForm" property="categories"/>
  </html:select></td>
</tr><tr>
  <td>Enable MyList</td><td><html:checkbox name="accountForm" property="account.listOption"/></td>
</tr><tr>
  <td>Enable MyBanner</td><td><html:checkbox name="accountForm" property="account.bannerOption"/></td>
</tr>

</table>

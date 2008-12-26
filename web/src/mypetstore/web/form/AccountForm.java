package mypetstore.web.form;

import mypetstore.web.vo.AccountVo;
import mypetstore.web.vo.ProfileVo;

import org.apache.struts.validator.ValidatorForm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * strus action form(账户)
 * 
 * @author zhou wei
 * @since 2008-07-09
 */
@SuppressWarnings({ "unchecked", "serial" })
public class AccountForm extends ValidatorForm {

	private static final List LANGUAGE_LIST;
	private static final List CATEGORY_LIST;

	private AccountVo account = new AccountVo();
	private ProfileVo profile = new ProfileVo();
	private String repeatedPassword;
	private String pageDirection;
	private String validation;
	private List myList;
	private boolean authenticated;

	static {
		List langList = new ArrayList();
		langList.add("english");
		langList.add("japanese");
		LANGUAGE_LIST = Collections.unmodifiableList(langList);

		List catList = new ArrayList();
		catList.add("FISH");
		catList.add("DOGS");
		catList.add("REPTILES");
		catList.add("CATS");
		catList.add("BIRDS");
		CATEGORY_LIST = Collections.unmodifiableList(catList);
	}

	public String getUsername() {
		return account.getUsername();
	}

	public void setUsername(String username) {
		account.setUsername(username);
	}

	public String getPassword() {
		return account.getPassword();
	}

	public void setPassword(String password) {
		account.setPassword(password);
	}

	public List getMyList() {
		return myList;
	}

	public void setMyList(List myList) {
		this.myList = myList;
	}

	public String getRepeatedPassword() {
		return repeatedPassword;
	}

	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}

	public AccountVo getAccount() {
		return account;
	}

	public void setAccount(AccountVo account) {
		this.account = account;
	}

	public List getLanguages() {
		return LANGUAGE_LIST;
	}

	public List getCategories() {
		return CATEGORY_LIST;
	}

	public String getPageDirection() {
		return pageDirection;
	}

	public void setPageDirection(String pageDirection) {
		this.pageDirection = pageDirection;
	}

	public String getValidation() {
		return validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
	}

	public ProfileVo getProfile() {
		return profile;
	}

	public void setProfile(ProfileVo profile) {
		this.profile = profile;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public static List getLANGUAGE_LIST() {
		return LANGUAGE_LIST;
	}

	public static List getCATEGORY_LIST() {
		return CATEGORY_LIST;
	}
}

package Utils;

public class Locators extends webActions {

	/** Add Button XPATH Value */
	public static final String BASE_URL = "http://webtest.pqatesting.com/clean/";
    public static final String IPHONE_URL = BASE_URL + "index.php?route=product/product&product_id=40&search=iPhone";
    public static final String SAMSUNGPRODUCTURL= BASE_URL + "index.php?route=product/product&product_id=33&search=Sam";
    public static final String CAMERAPRODUCTURL= BASE_URL + "index.php?route=product/product&path=33&product_id=52";
	public static final String ACCOUNTICON = "//a[@title='My Account']";
	public static final String LOGIN = "//*[@id=\"top-links\"]/ul/li[2]/ul/li[2]/a";
	public static final String EMAILFIELD = "input-email";
	public static final String PASSWORD = "input-password";
	public static final String LOGINBUTTON = "//input[@type='submit']";
	public static final String LOGO = "//*[@id=\"logo\"]/h1/a";
	public static final String SEARCHBAR = "//*[@id=\"search\"]/input";
	public static final String SEARCHBUTTON = "//*[@id=\"search\"]/span/button";
	public static final String SEARCHURL = "http://webtest.pqatesting.com/clean/index.php?route=account/account";
//	public static final String PHONESTAB = "//*[@id=\"menu\"]/div[2]/ul/li[6]/a";
	public static final String SEARCHRESULTSTEXT = "#content > h2";
	public static final String ADDTOCART_BUTTON = "button-cart";
	public static final String ADDTOCARTICON = "//button[@data-toggle='dropdown']";
	public static final String CHECKOUTICON = "//*[@id=\"cart\"]/ul/li[2]/div/p/a[2]/strong";
	public static final String GUESTCHECKOUT_RADIOBUTTON = "input[name='account'][value='guest']";
	public static final String CONTINUEBUTTON = "button-account";
	public static final String ACCEPT_TERMS = "//input[@type='checkbox']";
	
	
	//Personal Details IDs
	public static final String FIRSTNAME = "input-payment-firstname";
	public static final String LASTNAME = "input-payment-lastname";
	public static final String EMAIL = "input-payment-email";
	public static final String TELEPHONE = "input-payment-telephone";
	public static final String ADDRESS = "input-payment-address-1";
	public static final String CITY = "input-payment-city";
	public static final String POSTALCODE = "input-payment-postcode";
	public static final String COUNTRY = "input-payment-country";
	public static final String REGION = "input-payment-zone";
	public static final String GUEST_CONTINUE = "button-guest";
	public static final String DELIVERY_CONTINUE = "button-shipping-method";
	public static final String PAYMENT_CONTINUE = "button-payment-method";
	public static final String COMFIRMORDER_BUTTON = "button-confirm";
	

}

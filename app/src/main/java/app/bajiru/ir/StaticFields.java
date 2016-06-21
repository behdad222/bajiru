package app.bajiru.ir;

public class StaticFields {
	public static final String SERVER_URL = "http://192.168.1.243:5000/api/v1.8";

	/***************************header******************/
	public static final String HEADER_AUTHORIZATION = "token";

	/***************************api******************/
	public static final String CUSTOMER = "/customer/";
	public static final String LOGIN_USER = "/user/login/";
	public static final String ALL_USER = "/user/all/";
	public static final String USER = "/user/";

	/***************************QUERY******************/
	public static final String PER_PAGE = "per_page";
	public static final String PAGE = "page";


	public static final String TOKEN = "token";
	public static final String USER_NAME = "user_name";
	public static final String[] drawerIcon = {
			"{zmdi-shopping-cart}",
			"{zmdi-assignment}",
			"{zmdi-accounts}",
			"{zmdi-cutlery}",
			"{zmdi-bike}",
			"{zmdi-account-circle}",
			"{zmdi-settings}"};
}

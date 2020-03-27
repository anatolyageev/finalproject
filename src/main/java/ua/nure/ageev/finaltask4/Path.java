package ua.nure.ageev.finaltask4;

/**
 * Path holder (jsp pages, controller commands).
 * 
 * @author A.Ageev
 * 
 */
public final class Path {
	
	// pages
	public static final String PAGE_LOGIN = "/login.jsp";
	public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";

	public static final String PAGE_USER_PAGE = "/WEB-INF/jsp/user/user_page.jsp";

	public static final String PAGE_ADMIN_PAGE = "/WEB-INF/jsp/admin/list_quizes.jsp";
	public static final String PAGE_SETTINGS = "/WEB-INF/jsp/settings.jsp";

	public static final String PAGE_USER_USER_LIST = "/WEB-INF/jsp/userList.jsp";

	// commands
	public static final String COMMAND_LIST_ORDERS = "/controller?command=listOrders";
	public static final String COMMAND_LIST_MENU = "/controller?command=listMenu";

}
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
	public static final String PAGE_REGISTRATION = "/registration.jsp";
	public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";

	public static final String PAGE_USER_PAGE = "/WEB-INF/jsp/user/user_page.jsp";
	public static final String PAGE_USER_TEST = "/WEB-INF/jsp/user/user_test.jsp";

	public static final String PAGE_ADMIN_PAGE = "/WEB-INF/jsp/admin/list_quizes.jsp";
	public static final String PAGE_SETTINGS = "/WEB-INF/jsp/settings.jsp";
	public static final String PAGE_ADMIN_CREATE_SUBJECT = "/WEB-INF/jsp/admin/createSubject.jsp";
	public static final String PAGE_ADMIN_EDIT_SUBJECT = "/WEB-INF/jsp/admin/editSubject.jsp";
	public static final String PAGE_ADMIN_TEST_LIST = "/WEB-INF/jsp/admin/testList.jsp";
	public static final String PAGE_ADMIN_CREATE_TEST = "/WEB-INF/jsp/admin/testCreate.jsp";
	public static final String PAGE_ADMIN_EDIT_TEST = "/WEB-INF/jsp/admin/editTest.jsp";
	public static final String PAGE_ADMIN_QUESTIONS = "/WEB-INF/jsp/admin/questionList.jsp";
	public static final String PAGE_ADMIN_CREATE_QUESTION = "/WEB-INF/jsp/admin/questionCreate.jsp";
	public static final String PAGE_ADMIN_CREATE_ANSWER = "/WEB-INF/jsp/admin/answersCreate.jsp";

	public static final String PAGE_USER_USER_LIST = "/WEB-INF/jsp/admin/userList.jsp";

	public static final String PAGE_QUIZZE_LIST = "/WEB-INF/jsp/user/userQuizzes.jsp";

	// commands
	public static final String COMMAND_LIST_ORDERS = "/controller?command=listOrders";
	public static final String COMMAND_LIST_MENU = "/controller?command=listMenu";
	public static final String COMMAND_LIST_USERS = "/controller?command=listUsersCommand";

}
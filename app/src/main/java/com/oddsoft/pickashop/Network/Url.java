package com.oddsoft.pickashop.Network;

public class Url {

    public static String BASE_URL = "http://pasmob.pickashop.com/";

    public static String LOGIN_URL = BASE_URL + "LoginServices?model=Login&";
    public static String HOME_POPULAR_URL = BASE_URL + "pick_home.php?picktag=home";
    public static String HOME_SEARCH_SGN = BASE_URL + "pick_search.php?picktag=search_keywords";
    public static String NOTIFICATION_URL = BASE_URL + "ConversationsServices?model=CONVERSATIONS&";
    public static String PROFILE_URL = BASE_URL + "LoginServices?model=USERINFO&";
    public static String EVENTS_URL = BASE_URL + "MeetingServices?model=MEETING&";
    public static String SIGNUP_URL = BASE_URL + "RegistrationServices?model=REGISTRATION&";
    public static String CONVERSATION_URL = BASE_URL + "ConversationsServices?model=CONVERSATIONS&";
    public static String ACTIONS_URL = BASE_URL + "LoginServices?model=CONVERSATIONS&";
    public static String COMP_URL = BASE_URL + "pick_profile.php";
    public static String SEARCH_URL = BASE_URL + "pick_search.php";


}
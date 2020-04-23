package com.example.myapplication.utils;


import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Created by Vishwnath on 11/10/19.
 */
public interface AppConstants {
    /**
     * Messages for user interaction
     */
    String SHOW_ERROR = "-1", HIDE_ERROR = "-1", SUCCESS_1 = "1", SUCCESS_0 = "0", SUCCESS_TRUE = "true", SUCCESS_UNKNOWN = "Whoops! Unknown sucess value";
    String WORK_IN_PROGRESS = "WORK IN PROGRESS";
    String UNEXPEXTED_ERROR = "Whoops! Something is happen unexpectedly. Please try again.";
    String UNEXPECTED_RESPONSE = "Whoops! Something is happen unexpectedly. Response is not in proper format.";
    String PARSING_ERROR = "Whoops! Something is happen unexpectedly. Exception in data parsing.";
    String EXCEPTION = "Whoops! Something is happen unexpectedly. Exception in data processing.";
    String TM_PROFILE="1",TM_HOME="2",TM_MSG_LIKE="3";
    int DARK = 1;
    int LIGHT = 2;

    //for Server
    String BASE_URL = "http://webdesky.com/demowork/social/api/";
    String BASE_URL_FOR_IMAGE = "http://m7laty.com/";
    /**
     * Image Storage Path
     */
    String IMAGE_DIRECTORY = "/DCIM/PICTURES";
    String IMAGE_DIRECTORY_CROP = "/DCIM/CROP_PICTURES";
    String VIDEO_DIRECTORY = "/DCIM/VIDEOS";

    /**
     * Constant for Intent calling
     */
    int ACTIVITY_RESULT = 1001, ACTIVITY_FINISH = 1002,
            GALLERY = 111, CAMERA = 112, CROP = 113, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;

    /**
     * Validation regular expression
     */
    Pattern EMAIL_ADDRESS_PATTERN = Pattern
            .compile("^([a-zA-Z0-9._-]+)@{1}(([a-zA-Z0-9_-]{1,67})|([a-zA-Z0-9-]+\\.[a-zA-Z0-9-]{1,67}))\\.(([a-zA-Z0-9]{2,6})(\\.[a-zA-Z0-9]{2,6})?)$");
    Pattern MOBILE_NUMBER_PATTERN = Pattern.compile("^[0-9]{10,14}$");
    Pattern LAND_LINE_NUMBER_PATTERN = Pattern.compile("^[0-9]\\d{2,4}-\\d{6,8}$");
    Pattern PERSON_NAME_PATTERN = Pattern.compile("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$");
    Pattern USER_NAME_PATTERN = Pattern.compile("^([a-zA-Z0-9._-]){6,20}$");
    Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$");
    Pattern DATE_PATTERN = Pattern.compile("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])*$");
    Pattern LATITUDE_PATTERN = Pattern.compile("^(\\+|-)?(?:90(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,6})?))$");
    Pattern LONGITUDE_PATTERN = Pattern.compile("^(\\+|-)?(?:180(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,6})?))$");

    Pattern ADDRESS_PATTERN = Pattern
            .compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");


    //Menu parent items
    int EVENTS = 0, FRIENDS = 1, NOTIFICATIONS = 2, SETTINGS = 3, LOGOUT = 4;

    //Menu child items
    int MY_EVENTS = 0, PARTICIPATING_EVENT = 1;


    String FILE_PATH = "file_path";
    String MINE_TYPE = "mimeType";

    /*Time format for 24 hours time to 12 hours*/
    SimpleDateFormat HH_MM_SS = new SimpleDateFormat(
            "HH:mm:ss", Locale.getDefault());
    SimpleDateFormat HH_MM_AM_PM = new SimpleDateFormat(
            "hh:mm a", Locale.getDefault());
    SimpleDateFormat HH_MM = new SimpleDateFormat(
            "HH:mm", Locale.getDefault());

    SimpleDateFormat YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    /*Date format for later ride*/
    SimpleDateFormat MM_DD_YYYY = new SimpleDateFormat(
            "MM-dd-yyyy", Locale.getDefault());
    SimpleDateFormat YYYY_MM_DD = new SimpleDateFormat(
            "yyyy-MM-dd", Locale.getDefault());
    SimpleDateFormat DD_MMM_YYYY = new SimpleDateFormat(
            "dd MMM yyyy", Locale.getDefault());
    SimpleDateFormat DD_MM_YYYY = new SimpleDateFormat(
            "dd-MM-yyyy", Locale.getDefault());
    SimpleDateFormat MMM_DD_YYYY = new SimpleDateFormat(
            "MMMM dd, yyyy", Locale.getDefault());


    /**
     * Variable For Condition Check
     */
    String FRIEND_LIST="friend_list";
    String UNFRIEND="unfriend";
    String FORGOT_PASSWORD="forgot_password";
    String SIGN_IN="sign_in_mobile";
    String OTP="otp";
    String MUSIC="music";
    String EVENT="event";
    String SEARCH="search";
    String FRIEND_REQUEST="friend_request";
    String VIEW_PROFILE="view_profile";
    String STATUS="status";
    String ACCEPT="accept";
    String REJECT="reject";
    String POST_LIST="post_list";
    String LIKE="post";
    String COMMENT="comment";
    String COMMENT_LIST="comment_list";
    String IMAGE="Image";
    String VIDEO="Video";
    String HELP="help";
    String ABOUT_US="about_us";
    String HELP_CONTENT="help_content";
    String ABOUT_US_CONTENT="about_us_content";
    String IMAG_URL="image_url";
    String POSITION="position";
    String SCREEN_CHECK = "screen_check";
    String REGIRSTER="registered";
    String UN_REGISTER="unregistered";
    String EDIT_PROFILE="edit_profile";
    String ART_CATEGORY="art_category";
    String MATCHES="matches";
    String MESSAGES="messages";
    String ADDRESS="address";
    String DELETE="delete";
    String CITY="city";
    String REGISTER="register";
    String POS="pos";
    String PRODUCT_LIST="product_list";
    String CART_LIST="cart_list";
    String REMOVE_CART="remove_cart";
    String ADD_TO_CART="add_to_cart";
    String CAT_POSITION="cat_position";
    String COMPLETE="wc-completed";
    String PENDING="wc-processing";
    String FB_LOGIN = "fb_login";
    String CATEGORY="category";
    String SUB_CATEGORY="sub_category";


    /**************************
     * SERVICE NAME START***************************************
     * /**
     * Methods for request on remote server
     */

    /**************************
     * REQUEST PARAM START***************************************
     * /**
     * Parameter name for request on remote server
     */
    String PN_NAME = "username";
    String PN_STATUS = "status";
    String PN_MOBILE = "mobile";
    String PN_COUNTRY_CODE = "country_code";
    String PN_PHONE = "phone";
    String PN_EMAIL = "ic_email";
    String PN_SIGN_IN = "sign_in";
    String PN_PASSWORD = "password";
    String PN_GENDER = "gender";
    String PN_DOB = "date_of_birth";
    String PN_ADDRESS = "address";
    String PN_MUSIC = "music";
    String PN_EVENT = "event";
    String PN_USER_ID = "user_id";
    String PN_ARTIST_ID = "artist_id";
    String PN_POST_ID = "post_id";
    String PN_ACCEPT="1";
    String PN_REJECT="3";


}

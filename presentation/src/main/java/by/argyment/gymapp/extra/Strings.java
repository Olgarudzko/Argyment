package by.argyment.gymapp.extra;

/**
 * @author Olga Rudzko
 */

public interface Strings {

    //Helpers
    String EMPTY = "";
    String DATE = "dd/MM/yyyy HH:mm";
    String USERIMAGES = "userimages/";
    String NEWSIMAGES="newsimages/";
    String JPG = ".jpg";

    //URLs
    String VK = "https://vk.com/argyment_gym";
    String INSTAGRAM = "https://www.instagram.com/argyment_gym/";
    String FACEBOOK = "https://www.facebook.com/argument.gym/";
    String YOUTUBE_CHANNEL = "https://www.youtube.com/channel/UC-0HL69hukvqVg6EBAdG7Sg";

    //Strings for binding images annotation
    String BIND_MAIN_IMAGE = "bind:main_image";
    String BIND_SET_IMAGE = "bind:set_image";
    String BIND_ROUND = "bind:set_round_img";

    //Strings for saving data
    String USERMAIL = "USERMAIL";
    String CHECKIN = "CHECKIN";
    String SHARED = "SHARED";
    String KEY_NAME = "KEY_NAME";
    String PASSWORD = "PASSWORD";
    String SHARED_PROF = "SHARED_PROF";
    String SAVED_MAIL = "SAVED_MAIL";
    String NO = "no";

    //Regex strings
    String NAME_REGEX = "[a-zA-Zа-яёЁА-Я0-9 ]{4,12}";
    String PASSW_REGEX = "[a-zA-Z0-9]{4,12}";
    String EMAIL_REGEX = "[-\\w\\.]+@[a-zA-Z]+\\.[a-zA-Z]{2,3}";
    String SLON_REGEX = "[\\wа-яёЁА-Я0-9 -]{10,50}";
    String NEWS_REGEX = "[\\wа-яёЁА-Я0-9 ()«»—\\n\\.,!?;:\"%-]{30,499}";
    String TITLE_REGEX = "[\\wа-яёЁА-Я0-9 ()«»\"?-]{5,50}";
    String YOUTU_BE = "http[s]?:\\/\\/youtu\\.be\\/[\\w?=&-]{5,}";
    String YOUTUBE_COM = "http[s]?:\\/\\/www\\.youtube\\.com\\/[\\w?=&-]{5,}";

}

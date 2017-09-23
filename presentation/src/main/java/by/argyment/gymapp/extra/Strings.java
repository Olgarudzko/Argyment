package by.argyment.gymapp.extra;

/**
 * @author Olga Rudzko
 */

public interface Strings {

    String EMPTY = "";

    //URLs
    String DEFAULT_IMG = "https://goo.gl/hTFuC4";
    String VK = "https://vk.com/argyment_gym";
    String INSTAGRAM = "https://www.instagram.com/argyment_gym/";
    String FACEBOOK = "https://www.facebook.com/argument.gym/";

    //Strings for binding images annotation
    String BIND_MAIN_IMAGE = "bind:main_image";
    String BIND_SET_IMAGE = "bind:set_image";
    String BIND_ROUND="bind:set_round_img";

    //Strings for saving data
    String USERMAIL = "USERMAIL";
    String CHECKIN = "CHECKIN";
    String SHARED = "SHARED";
    String KEY_NAME = "KEY_NAME";
    String PASSWORD = "PASSWORD";
    String SHARED_PROF = "SHARED_PROF";
    String SAVED_MAIL = "SAVED_MAIL";

    //Regex strings
    String NAME_REGEX = "[\\wа-яА-Я]{5,10}";
    String EMAIL_REGEX = "[-\\w\\.]+@[a-zA-Z]+\\.[a-zA-Z]{2,3}";
    String SLON_REGEX = "[\\wа-яА-Я0-9 -]{10,50}";
    String TITLEVIDEO = "[\\wа-яА-Я -]{5,30}";

    String YOUTUBE_CHANNEL = "https://www.youtube.com/channel/UC-0HL69hukvqVg6EBAdG7Sg";
}

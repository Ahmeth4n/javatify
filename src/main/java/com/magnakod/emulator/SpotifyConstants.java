package com.magnakod.emulator;

public class SpotifyConstants {
    public final static String SERVER_AUTHORIZATION_KEY = "aS1hbG1vc3Qtd3JvdGUtbXktcGVyc29uYWwtcGFzc3dvcmQtaGVyZS06KQ==";
    public volatile String SP_REGISTER_ENDPOINT = "https://auth-callback.spotify.com/r/android/music/signup";
    public volatile String SP_CLIENT_ID = "9a8d2f0ce77a4e248bb71fefcb557637";
    public static String SP_APP_VERSION_8_7_78 = "8.7.78.373";
    public static String SP_DEFAULT_LOCALE = "en-US";
    public static final String SP_OS_SDK = "Android SDK built for x86";
    public static final String SP_OS_TYPE = "Android";
    public static final String SP_DEVICE_CPU_INFO = "Android-ARM";
    public static final String SP_SIGN_UP_KEY = "142b583129b2df829de3656f9eb484e6";
    public volatile String SP_ANDROID_USER_AGENT = "Spotify/%s Android/%s (%s)";
//    public final String SP_ANDROID_USER_AGENT = "Spotify/"+SP_APP_VERSION_8_7_78+" Android/"+ANDROID_TARGET_SDK_30+" ("+ANDROID_OS_VERSION_11+")";
    public static final int ANDROID_TARGET_SDK_30 = 30;
    public static final String ANDROID_OS_VERSION_11 = "11";
    /*
    * this MAIL_DOMAIN variable is using for registration step. you can change with your MX domain or anything.
    * */
    public static final String MAIL_DOMAIN = "github.com";
    public static final int DEVICE_ID_LENGTH = 16;
    public static final String RANDOM_CHARS = "0123456789abcdefghijklmnopqrstuvwxyz";
    public static final String lexicon = "abcdefghijklmnopqrstuvwxyz12345674890";
    public static final String[] SPECIAL_CHARACTERS =  {"!","?","_","-","/","*",".","^","+","#"};
    public enum COLLECTION_TYPES{
      ARTIST,TRACK,PLAYLIST
    };

}

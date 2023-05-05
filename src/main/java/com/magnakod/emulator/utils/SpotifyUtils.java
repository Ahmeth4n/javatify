package com.magnakod.emulator.utils;

import com.github.javafaker.Faker;
import com.magnakod.Constants;
import com.magnakod.emulator.Spotify;
import com.magnakod.emulator.SpotifyConstants;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ThreadLocalRandom;

public class SpotifyUtils {
    static SecureRandom rnd = new SecureRandom();
    final java.util.Random rand = new java.util.Random();
    final Set<String> identifiers = new HashSet<String>();
    public Faker faker = new Faker();

    public String randomCollectionID(){
        StringBuilder builder = new StringBuilder();
        int firstCharacter = ThreadLocalRandom.current().nextInt(0,9);
        while(builder.toString().length() == 0) {
            for (int i = 0; i < 15; i++) {
                builder.append(SpotifyConstants.lexicon.charAt(rand.nextInt(SpotifyConstants.lexicon.length())));
            }
            if(identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        return String.valueOf(firstCharacter) + builder.toString();
    }
    public static String dateFormat(Date date){
        String pattern = "MM-dd-yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        String todayAsString = df.format(date);
        return todayAsString;
    }
    public String getCollectionType(Constants.SPOTIFY_TASK_TYPE types){
        return switch (types){
            case SPOTIFY_ARTIST_STREAM -> null;
            case SPOTIFY_ARTIST_FOLLOW -> "artist";
            case SPOTIFY_SONG_LIKE -> "collection";
            case SPOTIFY_SONG_SAVE -> null;
            case SPOTIFY_PLAYLIST_SAVE -> null;
            case SPOTIFY_PLAYLIST_STREAM -> null;
            case SPOTIFY_SONG_STREAM -> null;
        };
    }
    public String generate_random_str() {
        StringBuilder builder = new StringBuilder();
        while(builder.toString().length() == 0) {
            int length = rand.nextInt(5)+10;
            for(int i = 0; i < length; i++) {
                builder.append(SpotifyConstants.lexicon.charAt(rand.nextInt(SpotifyConstants.lexicon.length())));
            }
            if(identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        return builder.toString();
    }

    public String get_email_address(){
        return generate_random_str()+"@"+SpotifyConstants.MAIL_DOMAIN;
    }
    public String get_random_password(){
        return generate_random_str()+SpotifyConstants.SPECIAL_CHARACTERS[SpotifyConstants.SPECIAL_CHARACTERS.length - 1];
    }

    public String get_random_username(){
        return faker.name().firstName().toLowerCase() + "_" + generate_random_str();
    }
    private static int randBetween(int start, int end) {
        // TODO Auto-generated method stub
        return start + (int)Math.round(Math.random() * (end - start));
    }
    public String randomDataOfBirth(int yearStartInclusive, int yearEndExclusive) {
        LocalDate start = LocalDate.ofYearDay(yearStartInclusive, 1);
        LocalDate end = LocalDate.ofYearDay(yearEndExclusive, 1);

        long longDays = ChronoUnit.DAYS.between(start, end);
        int days = (int) longDays;
        if (days != longDays) {
            throw new IllegalStateException("int overflow; too many years");
        }
        int day = randBetween(0, days);
        LocalDate dateOfBirth = start.plusDays(day);

        return dateOfBirth.toString();
    }
    public String getDeviceID(){
        StringBuilder device_id = new StringBuilder(SpotifyConstants.DEVICE_ID_LENGTH);
        for(int i = 0; i < SpotifyConstants.DEVICE_ID_LENGTH; i++)
            device_id.append(SpotifyConstants.RANDOM_CHARS.charAt(rnd.nextInt(SpotifyConstants.RANDOM_CHARS.length())));
        return device_id.toString();
    }

    public String dateHeader() {
        String DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss z" ;
        final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String dateTimeString =  sdf.format(new Date());
        return dateTimeString;
    }

}

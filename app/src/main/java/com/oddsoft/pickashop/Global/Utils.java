package com.oddsoft.pickashop.Global;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings.Secure;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    static String SHARED_PREF_NAME = "PICKASHOP Shared Preference";

    public static final boolean checkNetwork(Context context) {
        if (context != null) {
            boolean result = true;
            ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager
                    .getActiveNetworkInfo();
            if (networkInfo == null || !networkInfo.isConnectedOrConnecting()) {
                result = false;
            }
            return result;
        } else {
            return false;
        }
    }

    public static final boolean logOutShared(Context context) {
        SharedPreferences Preference = context.getSharedPreferences(
                SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = Preference.edit();
        return true;
    }

    public static final boolean checkLocation(Context context) {
        if (context != null) {
            // boolean result = true;
            LocationManager lm = (LocationManager) context
                    .getSystemService(Context.LOCATION_SERVICE);
            boolean result = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

            return result;
        } else {
            return false;
        }
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public static final void callToNumber(Context context, String number) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + number));
        context.startActivity(intent);
    }

    public static final void SmsToNumber(Context context, String number,
                                         String text) {
        String uri = "smsto:" + number;
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(uri));
        intent.putExtra("sms_body", text);
        intent.putExtra("compose_mode", true);
        context.startActivity(intent);
    }

    public static long getLongValue(String valueStr) {
        long ans = 0;
        try {
            if (null != valueStr) {
                ans = Long.parseLong(valueStr);

            }
        } catch (NumberFormatException e) {
        }
        return ans;
    }

    public static double getDoubleValue(String valueStr) {
        double ans = 0;
        try {
            if (null != valueStr) {
                // ans = Long.parseLong(valueStr);
                ans = Double.parseDouble(valueStr);
            }
        } catch (NumberFormatException e) {
        }
        return ans;
    }

    public static Typeface getfont(Context context) {
        Typeface font = Typeface.createFromAsset(context.getAssets(),
                "fonts/HelveticaNeue-CondensedBlack.otf");
        return font;
    }

    public static final int getandroidversion() {
        return Build.VERSION.SDK_INT;
    }

    public static final void setStringSharedPreference(Context context,
                                                       String key, String value) {
        SharedPreferences goaPref = context.getSharedPreferences(
                SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = goaPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static final String getStringSharedPreference(Context context,
                                                         String key) {
        Logger.e("context " + context);
        if (context != null) {

            SharedPreferences Pref = context.getSharedPreferences(
                    SHARED_PREF_NAME, Context.MODE_PRIVATE);
            String value = Pref.getString(key, "");
            return value;

        } else {
            return "";
        }
    }

    public static final void setIntSharedPreference(Context context,
                                                    String key, int value) {
        SharedPreferences goaPref = context.getSharedPreferences(
                SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = goaPref.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static final int getINtSharedPreference(Context context,
                                                   String key) {
        Logger.e("context " + context);
        if (context != null) {

            SharedPreferences Pref = context.getSharedPreferences(
                    SHARED_PREF_NAME, Context.MODE_PRIVATE);
            int value = Pref.getInt(key, 0);
            return value;

        } else {
            return 0;
        }
    }

    public static final void setLongSharedPreference(Context context,
                                                     String key, Long value) {
        SharedPreferences goaPref = context.getSharedPreferences(
                SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = goaPref.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static final Long getLongSharedPreference(Context context,
                                                     String key) {
        Logger.e("context " + context);
        if (context != null) {

            SharedPreferences Pref = context.getSharedPreferences(
                    SHARED_PREF_NAME, Context.MODE_PRIVATE);
            Long value = Pref.getLong(key, 0);
            return value;

        } else {
            return (long) 0;
        }
    }

    public static final void setBooleanSharedPreference(Context context,
                                                        String key, boolean value) {
        SharedPreferences Preference = context.getSharedPreferences(
                SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = Preference.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static final boolean getBooleanSharedPreference(Context context,
                                                           String key) {
        boolean value;
        SharedPreferences Preference = context.getSharedPreferences(
                SHARED_PREF_NAME, Context.MODE_PRIVATE);
        value = Preference.getBoolean(key, false);
        return value;
    }

    public static final boolean isLoggedIn(Context context) {
        boolean value;
        SharedPreferences Pref = context.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        value = Pref.getBoolean(Constants.SHARED_PREF_IS_LOGGED_IN, false);
        return value;
    }

    public static String getDeviceId(Context context) {
        return Secure
                .getString(context.getContentResolver(), Secure.ANDROID_ID);
    }

    // "Wed Aug 06 2014 21:49:03 GMT+0530 (IST)","Thu Aug 07 2014 15:23:41 GMT+0530 (IST)"}]}

    public static String getdisplayDateWithtime(String selectedDate) {
        // Log.e("selectedDate", selectedDate);//17/6/2014 0:30

        SimpleDateFormat format = new SimpleDateFormat("MMM MM/yyyy HH:mm");
        // format.setTimeZone(TimeZone.getTimeZone("GMT"));//MM/dd/yyyy
        // HH:mm"MM/dd/yyyy HH:mm"
        Date date = null;
        try {
            date = format.parse(selectedDate);
            // return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy K:mm a");
        String newFormat = formatter.format(date);
        // System.out.println(".....Date..."+newFormat);
        return newFormat;
    }

    public static String GetDateFromMilliseconds(long milliSeconds) {
        Logger.e("milliSeconds " + milliSeconds);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        long dv = milliSeconds * 1000;
        Date df = new Date(dv);
        return new SimpleDateFormat("dd MMM").format(df);
    }

    public static String GetSurveyDateFromMilliseconds(long milliSeconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        long dv = milliSeconds * 1000;
        Date df = new Date(dv);
        return new SimpleDateFormat("MMMM dd, yyyy").format(df);
    }

    public static String GetEventDate(long milliSeconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        long dv = milliSeconds * 1000;
        Date df = new Date(dv);
        return new SimpleDateFormat("yyyy-MM-dd").format(df);
    }

    public static String GetDayAndHourMilliseconds(long timeStamp) {

        int SECOND = 1000;
        int MINUTE = 60 * SECOND;
        int HOUR = 60 * MINUTE;
        int DAY = 24 * HOUR;
        long hours = 0;
        Calendar objCalendar = Calendar.getInstance();
        objCalendar.setTimeInMillis(timeStamp * 1000);//edit
        Calendar currentCal = Calendar.getInstance();

        long ms = currentCal.getTime().getTime() - objCalendar.getTime().getTime();

        StringBuffer text = new StringBuffer("");
        if (ms > DAY) {
            // text.append(ms / DAY).append(" days ");
            //  ms %= DAY;
        }
        if (ms > HOUR) {
            hours = ms / HOUR;
            if (hours > 1)
                text.append(hours).append(" HRS AGO");
            else
                text.append(hours).append(" HR AGO");
            ms %= HOUR;
        }
        if (hours < 1) {
            if (ms > MINUTE) {
                text.append(ms / MINUTE).append(" minutes AGO");
                ms %= MINUTE;
            } else {
                text.append(" Seconds AGO");
            }
        }

        if (ms > SECOND) {
            //   text.append(ms / SECOND).append(" seconds ");
            //  ms %= SECOND;
        }
        //text.append(ms + " ms");
        System.out.println(text.toString());
        return text.toString();
    }

    public static final void setLanguageLocale(Context context,
                                               String languageCode) {
        Logger.i("languageCode : " + languageCode);
        Configuration config = context.getResources().getConfiguration();
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        config.locale = locale;
        context.getResources().updateConfiguration(config,
                context.getResources().getDisplayMetrics());

    }

    // validate first name
    public static boolean validateFirstName(String firstName) {

        return firstName.trim().length() > 0;
        // return firstName.matches("[a-zA-Z][a-zA-Z]*");
    }

    // validate last name
    public static boolean validateLastName(String lastName) {
        return lastName.matches("[a-zA-z]+([ '-][a-zA-Z]+)*");
    }

    public static boolean isLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    /**
     * Method to detect whether the device is phone or tablet
     */
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static String getDay(int day) {
        /*
        Sunday Day of Week 1
        Monday Day of Week 2
        Tuesday Day of Week 3
        Wednesday Day of Week 4
        Thrusday Day of Week 5
        Friday Day of Week 6
        Saturday Day of Week 7
        */
        String dayString = "";
        switch (day) {
            case 1:
                dayString = "Sun";
                break;
            case 2:
                dayString = "Mon";
                break;
            case 3:
                dayString = "Tue";
                break;
            case 4:
                dayString = "Wed";
                break;
            case 5:
                dayString = "Thu";
                break;
            case 6:
                dayString = "Fri";
                break;
            case 7:
                dayString = "Sat";
                break;
        }
        return dayString;
    }

    public static String getMonth(int day) {

        String monthString = "";
        switch (day) {
            case GregorianCalendar.JANUARY:
                monthString = "JANUARY";
                break;
            case GregorianCalendar.FEBRUARY:
                monthString = "FEBRUARY";
                break;
            case GregorianCalendar.MARCH:
                monthString = "MARCH";
                break;
            case GregorianCalendar.APRIL:
                monthString = "APRIL";
                break;
            case GregorianCalendar.MAY:
                monthString = "MAY";
                break;
            case GregorianCalendar.JUNE:
                monthString = "JUNE";
                break;
            case GregorianCalendar.JULY:
                monthString = "JULY";
                break;
            case GregorianCalendar.AUGUST:
                monthString = "AUGUST";
                break;
            case GregorianCalendar.SEPTEMBER:
                monthString = "SEPTEMBER";
                break;
            case GregorianCalendar.OCTOBER:
                monthString = "OCTOBER";
                break;
            case GregorianCalendar.NOVEMBER:
                monthString = "NOVEMBER";
                break;
            case GregorianCalendar.DECEMBER:
                monthString = "DECEMBER";
                break;

        }
        return monthString;
    }
}

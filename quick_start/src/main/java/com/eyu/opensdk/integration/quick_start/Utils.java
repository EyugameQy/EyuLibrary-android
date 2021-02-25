package com.eyu.opensdk.integration.quick_start;

import android.content.Context;

class Utils {
    public static int getStringResource(Context context, String name) {
        return context.getResources().getIdentifier(name, "string", context.getPackageName());
    }

    public static int getBooleanResource(Context context, String name) {
        return context.getResources().getIdentifier(name, "bool", context.getPackageName());
    }

    public static String getStringResourceText(Context context, String name) {
        final int resource = getStringResource(context, name);
        if (resource == 0) {
            return null;
        }
        return context.getResources().getString(resource);
    }

    public static boolean getBooleanResourceValue(Context context, String name) {
        final int resource = getBooleanResource(context, name);
        if (resource == 0) {
            return false;
        }
        return context.getResources().getBoolean(resource);
    }
}

package de.juliushetzel.boilerplate.util;


import android.util.Log;

public final class Logger {
    private static final boolean DEBUG = true;
    private static final boolean INFO = true;

    public static void d(String tag, String title, String... infos){
        if(DEBUG){
            Log.d(tag, title + "\n" + buildStringFromArray(infos));
        }
    }

    private static String buildStringFromArray(String... strings){
        StringBuilder stringBuilder = new StringBuilder();
        for(String line : strings){
            stringBuilder.append(line)
                    .append("\n");
        }
        return stringBuilder.toString();
    }

    public static Output debug(String tag, String title){
        return new Output(DEBUG, tag, title) {
            @Override
            protected void print(String tag, String logMessage) {
                Log.d(tag, logMessage);
            }
        };
    }

    public static Output info(String tag, String title){
        return new Output(INFO, tag, title) {
            @Override
            protected void print(String tag, String logMessage) {
                Log.i(tag, logMessage);
            }
        };
    }

    public static abstract class Output {
        private static final String TAG_DIVIDER = ":    ";
        private StringBuilder mStringBuilder;
        private String mTag;
        private boolean mLog;

        public Output(boolean log, String tag, String title){
            if(log) {
                mLog = log;
                mStringBuilder = new StringBuilder();
                mTag = tag;
                mStringBuilder.append(title);
            }
        }

        public Output row(String tag, Boolean value){
            if(mLog) {
                mStringBuilder.append("\n");
                mStringBuilder.append(tag);
                mStringBuilder.append(TAG_DIVIDER);
                mStringBuilder.append(value);
            }
            return this;
        }

        public Output row(String tag, String value){
            if(mLog) {
                mStringBuilder.append("\n")
                        .append(tag)
                        .append(TAG_DIVIDER)
                        .append(value);
            }
            return this;
        }

        public Output row(String tag, int value){
            if(mLog) {
                mStringBuilder.append("\n")
                        .append(tag)
                        .append(TAG_DIVIDER)
                        .append(value);
            }
            return this;
        }

        public Output row(String tag, double value){
            if(mLog) {
                mStringBuilder.append("\n")
                        .append(tag)
                        .append(TAG_DIVIDER)
                        .append(value);
            }
            return this;
        }

        public Output row(String tag, float value){
            if(mLog) {
                mStringBuilder.append("\n")
                        .append(tag)
                        .append(TAG_DIVIDER)
                        .append(value);
            }
            return this;
        }

        public Output row(String value){
            if(mLog) {
                mStringBuilder.append("\n")
                        .append(value);
            }
            return this;
        }

        public Output append(String value){
            if(mLog) {
                mStringBuilder.append(value);
            }
            return this;
        }

        public void out(){
            if(mLog)
                print(mTag, mStringBuilder.toString());
        }

        protected abstract void print(String tag, String logMessage);
    }
}


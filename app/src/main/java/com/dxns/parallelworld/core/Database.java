package com.dxns.parallelworld.core;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import se.emilsjolander.sprinkles.Sprinkles;
import se.emilsjolander.sprinkles.typeserializers.SqlType;
import se.emilsjolander.sprinkles.typeserializers.TypeSerializer;


/**
 * Created by Administrator on 13-8-12.
 */
public class Database {
    /*采用Versicode*/
    //public static int DB_VERSION = 102;
    private static Object lock = new Object();
    private static volatile Sprinkles db;

    public static Sprinkles get() {

        if (db == null) {
            synchronized (lock) {
                if (db == null) {
                    db = Sprinkles.init(ParallelwordApplacation.get(), getCurrentDatabasePath(), ParallelwordApplacation.getPackageInfo().versionCode);

                    db.registerType(JsonArray.class, new TypeSerializer<JsonArray>() {
                        @Override
                        public JsonArray unpack(Cursor c, String name) {
                            JsonParser jsonParser = new JsonParser();
                            try {
                                return jsonParser.parse(c.getString(c.getColumnIndexOrThrow(name))).getAsJsonArray();
                            } catch (Exception e) {
                                return new JsonArray();
                            }
                        }

                        @Override
                        public void pack(JsonArray object, ContentValues cv, String name) {
                            cv.put(name, object == null ? "[]" : object.toString());
                        }

                        @Override
                        public String toSql(JsonArray object) {
                            return DatabaseUtils.sqlEscapeString(object.toString());
                        }

                        @Override
                        public SqlType getSqlType() {
                            return SqlType.TEXT;
                        }

                    });
                    db.registerType(JsonObject.class, new TypeSerializer<JsonObject>() {
                        @Override
                        public JsonObject unpack(Cursor c, String name) {
                            JsonParser jsonParser = new JsonParser();
                            try {
                                return jsonParser.parse(c.getString(c.getColumnIndexOrThrow(name))).getAsJsonObject();
                            } catch (Exception e) {
                                return new JsonObject();
                            }
                        }

                        @Override
                        public void pack(JsonObject object, ContentValues cv, String name) {
                            cv.put(name, object == null ? "" : object.toString());
                        }

                        @Override
                        public String toSql(JsonObject object) {
                            return DatabaseUtils.sqlEscapeString(object.toString());
                        }

                        @Override
                        public SqlType getSqlType() {
                            return SqlType.TEXT;
                        }

                    });

                }
            }
        }
        return db;
    }

    public static String getCurrentDatabasePath() {
        return getSharedPreferences().getString("databasePath", AppConfig.DATABASENAME);
    }

    public static SharedPreferences getSharedPreferences() {
        String packageName = ParallelwordApplacation.get().getPackageName();
        SharedPreferences sharedPreferences = ParallelwordApplacation.get().getSharedPreferences(packageName, Context.MODE_PRIVATE);
        return sharedPreferences;
    }

    public static void resetDb() {
        db.dropInstances();
        db = null;
        get();
    }
}

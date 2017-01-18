package at.fh.swengb.android_resifo

import android.content.Context
import android.database.sqlite.{SQLiteDatabase, SQLiteOpenHelper}

/**
  * Created by Kevin on 16.01.2017.
  */
case class DataBase(context: Context) extends SQLiteOpenHelper(context, "mydb", null, 1) {
  override def onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int): Unit = ()
  override def onCreate(db: SQLiteDatabase): Unit = {
    db.execSQL("create table person (id INTEGER PRIMARY KEY ASC, firstname TEXT);")
  }
}


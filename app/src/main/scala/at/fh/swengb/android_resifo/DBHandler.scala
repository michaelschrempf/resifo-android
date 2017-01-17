package at.fh.swengb.android_resifo;


import android.content.ContentValues

import android.content.Context

import android.database.Cursor

import android.database.sqlite.SQLiteDatabase

import android.database.sqlite.SQLiteOpenHelper

import java.util.ArrayList

import java.util.List

import DBHandler._

//remove if not needed
import scala.collection.JavaConversions._

object DBHandler {

  // Database Version
  private val DATABASE_VERSION: Int = 1

  // Database Name
  private val DATABASE_NAME: String = "meldezDatabase"

  // Contacts table name
  private val TABLE_MELDEZ: String = "meldez"

  // Shops Table Columns names
  private val KEY_ID: String = "id"

  private val KEY_NAME: String = "name"

  private val KEY_SH_ADDR: String = "shop_address"

}

class DBHandler(context: Context)
  extends SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

  override def onCreate(db: SQLiteDatabase): Unit = {
    val CREATE_CONTACTS_TABLE: String = "CREATE TABLE " + TABLE_MELDEZ + "(" + KEY_ID + " INTEGER PRIMARY KEY," +
      KEY_NAME +
      " TEXT," +
      KEY_SH_ADDR +
      " TEXT" +
      ")"
    db.execSQL(CREATE_CONTACTS_TABLE)
  }

  override def onUpgrade(db: SQLiteDatabase,
                         oldVersion: Int,
                         newVersion: Int): Unit = {
    // Drop older table if existed
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_MELDEZ)
    // Creating tables again
    onCreate(db)
  }

  // Adding new shop
  def addShop(meldez: Meldez): Unit = {
    val db: SQLiteDatabase = this.getWritableDatabase
    val values: ContentValues = new ContentValues()
    // Shop Name
    values.put(KEY_NAME, meldez.getName)
    // Shop Phone Number
    values.put(KEY_SH_ADDR, meldez.getSurname)
    // Inserting Row
    db.insert(TABLE_MELDEZ, null, values)
    // Closing database connection
    db.close()
  }

  // Getting one shop
  def getShop(id: Int): Meldez = {
    val db: SQLiteDatabase = this.getReadableDatabase
    val cursor: Cursor = db.query(TABLE_MELDEZ,
      Array(KEY_ID, KEY_NAME, KEY_SH_ADDR),
      KEY_ID + "=?",
      Array(String.valueOf(id)),
      null,
      null,
      null,
      null)
    if (cursor != null) cursor.moveToFirst()
    val contact: Meldez = new Meldez(
      java.lang.Integer.parseInt(cursor.getString(0)),
      cursor.getString(1),
      cursor.getString(2))
    // return shop
    contact
  }

  // Getting All Shops
  def getAllShops(): List[Meldez] = {
    val shopList: List[Meldez] = new ArrayList[Meldez]()
    // Select All Query
    val selectQuery: String = "SELECT * FROM " + TABLE_MELDEZ
    val db: SQLiteDatabase = this.getWritableDatabase
    val cursor: Cursor = db.rawQuery(selectQuery, null)
    // looping through all rows and adding to list
    if (cursor.moveToFirst()) {
      do {
        val meldez: Meldez = new Meldez()
        meldez.setId(java.lang.Integer.parseInt(cursor.getString(0)))
        meldez.setName(cursor.getString(1))
        meldez.setSurname(cursor.getString(2))
        // Adding contact to list
        shopList.add(meldez)
      } while (cursor.moveToNext());
    }
    // return contact list
    shopList
  }

  // Getting shops Count
  def getShopsCount(): Int = {
    val countQuery: String = "SELECT * FROM " + TABLE_MELDEZ
    val db: SQLiteDatabase = this.getReadableDatabase
    val cursor: Cursor = db.rawQuery(countQuery, null)
    cursor.close()
    // return count
    cursor.getCount
  }

  // Updating a shop
  def updateShop(meldez: Meldez): Int = {
    val db: SQLiteDatabase = this.getWritableDatabase
    val values: ContentValues = new ContentValues()
    values.put(KEY_NAME, meldez.getName)
    values.put(KEY_SH_ADDR, meldez.getSurname)
    // updating row
    db.update(TABLE_MELDEZ,
      values,
      KEY_ID + " = ?",
      Array(String.valueOf(meldez.getId)))
  }

  // Deleting a shop
  def deleteShop(meldez: Meldez): Unit = {
    val db: SQLiteDatabase = this.getWritableDatabase
    db.delete(TABLE_MELDEZ, KEY_ID + " = ?", Array(String.valueOf(meldez.getId)))
    db.close()
  }

}
package at.fh.swengb.android_resifo

import android.content.{ContentValues, Context}
import android.database.Cursor
import android.database.sqlite.{SQLiteDatabase, SQLiteOpenHelper}

import scala.collection.mutable.ListBuffer

object SimpleDb {

  val Name = "mydb"

}

/**
  * A simple wrapper around SQLiteOpenHelper to ease SQLite access.
  *
  * Created by lad on 19/01/2017.
  */
case class SimpleDb(context: Context) extends SQLiteOpenHelper(context, SimpleDb.Name, null, 1) {

  override def onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int): Unit = ()

  override def onCreate(db: SQLiteDatabase): Unit = {

    // perform initial setup
    val personDao = SqlitePersonDao(db)

    personDao.init()

    for (i <- 1 to 100) personDao.insert(Person.mkRandom)

  }

  def mkPersonDao(): SqlitePersonDao = SqlitePersonDao(getWritableDatabase)


  trait BaseDao[T] {

    def insert(t: T): Long

    def update(t: T): Int

    // ... and other functions
  }

  def mkContentValues(p: Person): ContentValues = {
    val cv = new ContentValues
    cv.put("firstname", p.firstName)
    cv.put("secondname", p.secondName)
    cv.put("secondName_before", p.secondName_before)
    cv.put("birthDay", p.birthDay)
    cv.put("birthLocation", p.birthLocation)
    cv.put("familyStatus", p.familyStatus)
    cv.put("religion", p.religion)
    cv.put("nationality", p.nationality)
    cv.put("sex", p.sex)
    cv
  }

  /**
    * Hides details of database table 'Person'
    *
    * @param db
    */
  case class SqlitePersonDao(db: SQLiteDatabase) extends BaseDao[Person] {

    def updateTable(): Unit = {
      db.execSQL("drop table if exists person")
      db.execSQL("create table person (id INTEGER PRIMARY KEY ASC, " +
      "firstname TEXT, " +
      "secondname TEXT, " +
      "secondName_before TEXT, " +
      "birthDay TEXT, " +
      "birthLocation TEXT, " +
      "sex TEXT, " +
      "religion TEXT, " +
      "familyStatus TEXT, " +
      "nationality TEXT " +
      ");")}

    def init(): Unit = db.execSQL("create table person (id INTEGER PRIMARY KEY ASC, " +
      "firstname TEXT, " +
      "secondname TEXT, " +
      "secondName_before TEXT, " +
      "birthDay TEXT, " +
      "birthLocation TEXT, " +
      "sex TEXT, " +
      "religion TEXT, " +
      "familyStatus TEXT, " +
      "nationality TEXT " +
      ");")

    /**
      * Insert a person to the database.
      *
      * @param p
      */
    def insert(p: Person): Long = {
      val cv: ContentValues = mkContentValues(p)
      db.insert("person", null, cv)
    }

    def deleteByFirstName(firstName: String): Unit = {
      db.delete("person", "firstname = ?", Array(firstName))
    }

    def update(p: Person): Int = {
      db.update("person", mkContentValues(p), "firstname = ? and secondname = ?", Array(p.firstName, p.secondName))
    }

    /**
      * Returns a list of persons matching given firstName, or Nil if there is none
      *
      * @param firstName the firstName to search for
      * @return
      */
    def findByFirstName(firstName: String): List[Person] = {
      var someCursor: Option[Cursor] = None
      try {
        someCursor = someCursorForFirstnameQuery(firstName)
        someCursor match {
          case None =>
            System.err.println("Could not execute query due to some reason")
            Nil
          case Some(c) =>
            val lb = new ListBuffer[Person]()
            while (c.moveToNext()) {
              val id = c.getInt(c.getColumnIndex("id"))
              val firstName = c.getString(c.getColumnIndex("firstname"))
              val secondName = c.getString(c.getColumnIndex("secondname"))
              val secondName_before = c.getString(c.getColumnIndex("secondName_before"))
              val birthDay = c.getString(c.getColumnIndex("birthDay"))
              val birthLocation = c.getString(c.getColumnIndex("birthLocation"))
              val sex = c.getString(c.getColumnIndex("sex"))
              val religion = c.getString(c.getColumnIndex("religion"))
              val familyStatus = c.getString(c.getColumnIndex("familyStatus"))
              val nationality = c.getString(c.getColumnIndex("nationality"))
              lb.append(Person(firstName, secondName, secondName_before, birthDay, birthLocation, sex, religion, familyStatus, nationality))

            }
            lb.toList
        }
      } finally {
        someCursor foreach (_.close())
      }





    }

  def allEntries():List[Person] = {

    var someCursor: Option[Cursor] = None
    someCursor = somePersonCursor()
    someCursor match {
      case None =>
        System.err.println("Could not execute query due to some reason")
        Nil
      case Some(c) =>
        val lb = new ListBuffer[Person]()
        while (c.moveToNext()) {

          val id = c.getInt(c.getColumnIndex("id"))
          val firstName = c.getString(c.getColumnIndex("firstname"))
          val secondName = c.getString(c.getColumnIndex("secondname"))
          val secondName_before = c.getString(c.getColumnIndex("secondName_before"))
          val birthDay = c.getString(c.getColumnIndex("birthDay"))
          val birthLocation = c.getString(c.getColumnIndex("birthLocation"))
          val sex = c.getString(c.getColumnIndex("sex"))
          val religion = c.getString(c.getColumnIndex("religion"))
          val familyStatus = c.getString(c.getColumnIndex("familyStatus"))
          val nationality = c.getString(c.getColumnIndex("nationality"))
          lb.append(Person(firstName, secondName, secondName_before, birthDay, birthLocation, sex, religion, familyStatus, nationality))
        }
        lb.toList
    }
  }

    /**
      * Returns an optional cursor for a firstname query on the person table.
      *
      * @param firstName
      * @return
      */
    private def someCursorForFirstnameQuery(firstName: String): Option[Cursor] = {
      Option(db.query("person", Array("id", "firstname", "secondname"), "firstname = ?", Array(firstName), null, null, null))
    }

    private def somePersonCursor(): Option[Cursor] = {
      Option(db.query("person", Array("id", "firstname", "secondname","secondName_before",
                                      "birthDay","birthLocation","sex",
                                      "religion","familyStatus","nationality"), null, null, null, null, null))
    }


  }


}
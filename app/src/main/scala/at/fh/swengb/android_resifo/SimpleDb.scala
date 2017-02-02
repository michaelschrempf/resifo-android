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
    cv.put("on_street", p.on_street)
    cv.put("on_streetNumber", p.on_streetNumber)
    cv.put("on_stair", p.on_stair)
    cv.put("on_door", p.on_door)
    cv.put("on_plz", p.on_plz)
    cv.put("on_city", p.on_city)
    cv.put("on_state", p.on_state)
    cv.put("on_foreign", p.on_foreign)
    cv.put("on_main", p.on_main)
    cv.put("on_owner", p.on_owner)
    cv.put("main_street", p.main_street)
    cv.put("main_streetNumber", p.main_streetNumber)
    cv.put("main_stair", p.main_stair)
    cv.put("main_door", p.main_door)
    cv.put("main_plz", p.main_plz)
    cv.put("main_city", p.main_city)
    cv.put("main_state", p.main_state)
    cv.put("off_street", p.off_street)
    cv.put("off_streetNumber", p.off_streetNumber)
    cv.put("off_stair", p.off_stair)
    cv.put("off_door", p.off_door)
    cv.put("off_plz", p.off_plz)
    cv.put("off_city", p.off_city)
    cv.put("off_state", p.off_state)
    cv.put("off_foreign", p.off_foreign)
    cv
  }

  /**
    * Hides details of database table 'Person'
    *
    * @param db
    */
  case class SqlitePersonDao(db: SQLiteDatabase) extends BaseDao[Person] {

    def dropAndInitTable(): Unit = {
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
        "nationality TEXT, " +
        "on_street TEXT, " +
        "on_streetNumber TEXT, " +
        "on_stair TEXT, " +
        "on_door TEXT, " +
        "on_plz TEXT, " +
        "on_city TEXT, " +
        "on_state TEXT, " +
        "on_foreign TEXT, " +
        "on_main TEXT, " +
        "on_owner TEXT, " +
        "main_street TEXT, " +
        "main_streetNumber TEXT, " +
        "main_stair TEXT, " +
        "main_door TEXT, " +
        "main_plz TEXT, " +
        "main_city TEXT, " +
        "main_state TEXT, " +
        "off_street TEXT, " +
        "off_streetNumber TEXT, " +
        "off_stair TEXT, " +
        "off_door TEXT, " +
        "off_plz TEXT, " +
        "off_city TEXT, " +
        "off_state TEXT, " +
        "off_foreign TEXT" +
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
      "nationality TEXT, " +
      "on_street TEXT, " +
      "on_streetNumber TEXT, " +
      "on_stair TEXT, " +
      "on_door TEXT, " +
      "on_plz TEXT, " +
      "on_city TEXT, " +
      "on_state TEXT, " +
      "on_foreign TEXT, " +
      "on_main TEXT, " +
      "on_owner TEXT, " +
      "main_street TEXT, " +
      "main_streetNumber TEXT, " +
      "main_stair TEXT, " +
      "main_door TEXT, " +
      "main_plz TEXT, " +
      "main_city TEXT, " +
      "main_state TEXT, " +
      "off_street TEXT, " +
      "off_streetNumber TEXT, " +
      "off_stair TEXT, " +
      "off_door TEXT, " +
      "off_plz TEXT, " +
      "off_city TEXT, " +
      "off_state TEXT, " +
      "off_foreign TEXT" +
      ");")

    /**
      * Insert a person to the database.
      *
      * @param p
      */

    def dropTable(name: String) =
    {
      db.execSQL("drop table if exists " + name)
    }
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
              val on_street = c.getString(c.getColumnIndex("on_street"))
              val on_streetNumber = c.getString(c.getColumnIndex("on_streetNumber"))
              val on_stair = c.getString(c.getColumnIndex("on_stair"))
              val on_door = c.getString(c.getColumnIndex("on_door"))
              val on_plz = c.getString(c.getColumnIndex("on_plz"))
              val on_city = c.getString(c.getColumnIndex("on_city"))
              val on_state = c.getString(c.getColumnIndex("on_state"))
              val on_foreign = c.getString(c.getColumnIndex("on_foreign"))
              val on_main = c.getString(c.getColumnIndex("on_main"))
              val on_owner = c.getString(c.getColumnIndex("on_owner"))
              val main_street = c.getString(c.getColumnIndex("main_street"))
              val main_streetNumber = c.getString(c.getColumnIndex("main_streetNumber"))
              val main_stair = c.getString(c.getColumnIndex("main_stair"))
              val main_door = c.getString(c.getColumnIndex("main_door"))
              val main_plz = c.getString(c.getColumnIndex("main_plz"))
              val main_city = c.getString(c.getColumnIndex("main_city"))
              val main_state = c.getString(c.getColumnIndex("main_state"))
              val off_street = c.getString(c.getColumnIndex("off_street"))
              val off_streetNumber = c.getString(c.getColumnIndex("off_streetNumber"))
              val off_stair = c.getString(c.getColumnIndex("off_stair"))
              val off_door = c.getString(c.getColumnIndex("off_door"))
              val off_plz = c.getString(c.getColumnIndex("off_plz"))
              val off_city = c.getString(c.getColumnIndex("off_city"))
              val off_state = c.getString(c.getColumnIndex("off_state"))
              val off_foreign = c.getString(c.getColumnIndex("off_foreign"))
              lb.append(Person(firstName, secondName, secondName_before, birthDay, birthLocation,
                sex, religion, familyStatus, nationality,on_street,on_streetNumber,
                on_stair,on_door,on_plz,on_city,on_state,on_foreign,on_main,on_owner,main_street,main_streetNumber,
                main_stair,main_door,main_plz,main_city,main_state,off_street,off_streetNumber,
                off_stair,off_door,off_plz,off_city,off_state,off_foreign))

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
            val on_street = c.getString(c.getColumnIndex("on_street"))
            val on_streetNumber = c.getString(c.getColumnIndex("on_streetNumber"))
            val on_stair = c.getString(c.getColumnIndex("on_stair"))
            val on_door = c.getString(c.getColumnIndex("on_door"))
            val on_plz = c.getString(c.getColumnIndex("on_plz"))
            val on_city = c.getString(c.getColumnIndex("on_city"))
            val on_state = c.getString(c.getColumnIndex("on_state"))
            val on_foreign = c.getString(c.getColumnIndex("on_foreign"))
            val on_main = c.getString(c.getColumnIndex("on_main"))
            val on_owner = c.getString(c.getColumnIndex("on_owner"))
            val main_street = c.getString(c.getColumnIndex("main_street"))
            val main_streetNumber = c.getString(c.getColumnIndex("main_streetNumber"))
            val main_stair = c.getString(c.getColumnIndex("main_stair"))
            val main_door = c.getString(c.getColumnIndex("main_door"))
            val main_plz = c.getString(c.getColumnIndex("main_plz"))
            val main_city = c.getString(c.getColumnIndex("main_city"))
            val main_state = c.getString(c.getColumnIndex("main_state"))
            val off_street = c.getString(c.getColumnIndex("off_street"))
            val off_streetNumber = c.getString(c.getColumnIndex("off_streetNumber"))
            val off_stair = c.getString(c.getColumnIndex("off_stair"))
            val off_door = c.getString(c.getColumnIndex("off_door"))
            val off_plz = c.getString(c.getColumnIndex("off_plz"))
            val off_city = c.getString(c.getColumnIndex("off_city"))
            val off_state = c.getString(c.getColumnIndex("off_state"))
            val off_foreign = c.getString(c.getColumnIndex("off_foreign"))
            lb.append(Person(firstName, secondName, secondName_before, birthDay, birthLocation,
              sex, religion, familyStatus, nationality,on_street,on_streetNumber,
              on_stair,on_door,on_plz,on_city,on_state,on_foreign,on_main,on_owner,main_street,main_streetNumber,
              main_stair,main_door,main_plz,main_city,main_state,off_street,off_streetNumber,
              off_stair,off_door,off_plz,off_city,off_state,off_foreign))

          }
          lb.toList
      }
    }
    def allEntriesOnlyFirstAndSecondname():List[String]= {
      var someCursor: Option[Cursor] = None
      someCursor = somePersonCursor()
      someCursor match {
        case None =>
          System.err.println("Could not execute query due to some reason")
          Nil
        case Some(c) =>
          val lb = new ListBuffer[String]()
          while (c.moveToNext()) {

            val id = c.getInt(c.getColumnIndex("id"))
            val firstName = c.getString(c.getColumnIndex("firstname"))
            val secondName = c.getString(c.getColumnIndex("secondname"))
            lb.append(firstName +" " + secondName)
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
      Option(db.query("person", Array("id", "firstname", "secondname","secondName_before",
        "birthDay","birthLocation","sex",
        "religion","familyStatus","nationality","on_street",
        "on_streetNumber","on_stair","on_door","on_plz","on_city",
        "on_state","on_foreign","on_main","on_owner","main_street",
        "main_streetNumber","main_stair","main_door","main_plz","main_city",
        "main_state","off_street","off_streetNumber","off_stair","off_door",
        "off_plz","off_city","off_state","off_foreign"), "firstname = ?", Array(firstName), null, null, null))
    }

    private def somePersonCursor(): Option[Cursor] = {
      Option(db.query("person", Array("id", "firstname", "secondname","secondName_before",
        "birthDay","birthLocation","sex",
        "religion","familyStatus","nationality","on_street",
        "on_streetNumber","on_stair","on_door","on_plz","on_city",
        "on_state","on_foreign","on_main","on_owner","main_street",
        "main_streetNumber","main_stair","main_door","main_plz","main_city",
        "main_state","off_street","off_streetNumber","off_stair","off_door",
        "off_plz","off_city","off_state","off_foreign"), null, null, null, null, null))
    }


  }


}
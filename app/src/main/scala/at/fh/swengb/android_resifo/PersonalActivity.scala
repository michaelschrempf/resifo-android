package at.fh.swengb.android_resifo

import android.app.Activity
import android.content.{ContentValues, Context, Intent}
import android.database.Cursor
import android.database.sqlite.{SQLiteDatabase, SQLiteOpenHelper}
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.view.View
import android.widget.EditText
import at.fh.swengb.resifo_android.{MainActivity, R}

class PersonalActivity extends AppCompatActivity{
  var importData: DataBase = _
  override protected def onCreate(savedInstanceState: Bundle){
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_personal)
    importData = DataBase(getApplicationContext)
  }
  def GoBackHomeScreen(view: View):Unit={
    val homeBack = new Intent(this,classOf[MainActivity])
    startActivity(homeBack)
  }
  def GoToAnmeldung(view: View):Unit={
    val nextPage = new Intent(this,classOf[AnmeldungActivity])
    startActivity(nextPage)
  }


  def saveToDb(view: View): Unit = {
    val firstNameEditText = findViewById(R.id.vorname).asInstanceOf[EditText]
    val firstName: String = firstNameEditText.getText.toString
    println(firstName)
    val cv = new ContentValues()
    Map("firstname" -> firstName) foreach
      {
        case (k, v) => cv.put(k, v)
      }
    importData.getWritableDatabase().insert("person", null, cv)

    var someCursor: Option[Cursor] = None
    try {
      someCursor = Option(importData.getReadableDatabase.query("person", Array("id", "firstname"), null, null, null, null, null))
      someCursor match {
        case None => System.err.println("Could not execute query due to some reason")
        case Some(c) =>
          while (c.moveToNext()) {
            val id = c.getInt(c.getColumnIndex("id"))
            val firstName = c.getString(c.getColumnIndex("firstname"))
            println(s"ID($id) : $firstName")
          }
      }
    }
    finally {
      someCursor foreach (_.close())
    }
  }
}

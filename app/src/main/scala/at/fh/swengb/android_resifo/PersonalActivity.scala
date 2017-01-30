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

  override protected def onCreate(savedInstanceState: Bundle){
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_personal)

  }
  def GoBackHomeScreen(view: View):Unit={
    val homeBack = new Intent(this,classOf[MainActivity])
    startActivity(homeBack)
  }
  def nextAnmeldung(view: View):Unit={
    val nextPage = new Intent(this,classOf[AnmeldungActivity])
    startActivity(nextPage)
  }
}

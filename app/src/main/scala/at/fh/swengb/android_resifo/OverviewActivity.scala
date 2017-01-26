package at.fh.swengb.android_resifo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.TextView
import at.fh.swengb.resifo_android.{MainActivity, R}

/**
  * Created by Kevin on 16.01.2017.
  */
class OverviewActivity extends AppCompatActivity{
  override protected def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_overview)

    val tV_name: TextView = findViewById(R.id.tV_vorname).asInstanceOf[TextView]
    val tV_surname: TextView = findViewById(R.id.tV_nachname).asInstanceOf[TextView]
    val tV_surname_before: TextView = findViewById(R.id.tV_nachnameVorher).asInstanceOf[TextView]






    //db.onUpgrade(db.getWritableDatabase,0,0)












  }
  def personalEdit(view: View){
    def personEdit: Intent = new Intent(this, classOf[PersonalActivity])
    startActivity(personEdit)
  }
  def anmeldungEdit(view: View){
    def anmeldung: Intent = new Intent(this, classOf[AnmeldungActivity])
    startActivity(anmeldung)
  }
  def abmeldungEdit(view: View){
    def abmeldung: Intent = new Intent(this, classOf[AbmeldungActivity])
    startActivity(abmeldung)
  }
  def hauptwohnsitzEdit(view: View):Unit={
    def hauptWohnSitz = new Intent(this, classOf[HauptWohnActivity])
    startActivity(hauptWohnSitz)
  }
  def storeData(view: View) {
    def store: Intent = new Intent(this, classOf[MainActivity])
    startActivity(store)
  }
}

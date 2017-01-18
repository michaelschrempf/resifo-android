package at.fh.swengb.android_resifo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import at.fh.swengb.resifo_android.{MainActivity, R}

/**
  * Created by Kevin on 16.01.2017.
  */
class OverviewActivity extends AppCompatActivity{
  override protected def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_overview)



  }
  def personalEdit(view: View):Unit={
    def personEdit = new Intent(this, classOf[PersonalActivity])
    startActivity(personEdit)
  }
  def anmeldungEdit(view: View):Unit={
    def anmeldung = new Intent(this, classOf[AnmeldungActivity])
    startActivity(anmeldung)
  }
  def abmeldungEdit(view: View):Unit={
    def abmeldung = new Intent(this, classOf[AbmeldungActivity])
    startActivity(abmeldung)
  }
  def hauptwohnsitzEdit(view: View):Unit={
    def hauptWohnSitz = new Intent(this, classOf[HauptWohnActivity])
    startActivity(hauptWohnSitz)
  }
  def storeData(view: View):Unit= {
    def store = new Intent(this, classOf[MainActivity])
    startActivity(store)
  }
}

package at.fh.swengb.android_resifo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import at.fh.swengb.resifo_android.{MainActivityTest, R}
/**
  * Created by Kevin on 14.01.2017.
  */
class EditActivity extends AppCompatActivity{
  override protected def onCreate(savedInstanceState: Bundle): Unit ={
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_edit)
  }
  def EditPersonal(view: View):Unit= {
    val editPerson = new Intent(this, classOf[PersonalActivity])
    startActivity(editPerson)
  }
  def GoToAbmeldung(view: View):Unit={
    val goAbmeldung = new Intent(this,classOf[AbmeldungActivity])
    startActivity(goAbmeldung)
  }
  def GoBackHomeScreen(view: View):Unit={
    val homeBack = new Intent(this,classOf[MainActivityTest])
    startActivity(homeBack)
  }
  def GoToAnmeldung(view: View):Unit= {
    val goAnmeldung = new Intent(this, classOf[AnmeldungActivity])
    startActivity(goAnmeldung)
  }
}

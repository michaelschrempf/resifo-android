package at.fh.swengb.android_resifo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import at.fh.swengb.resifo_android.{MainActivity, R}

/**
  * Created by Kevin on 14.01.2017.
  */
class AbmeldungActivity extends AppCompatActivity{
  override protected def onCreate(savedInstanceState: Bundle): Unit ={
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_abmeldung)
  }
  def saveAndHome(view: View):Unit={
    val homeBack = new Intent(this,classOf[MainActivity])
    startActivity(homeBack)
  }
  def goBack(view: View):Unit={
    val goback = new Intent(this,classOf[HauptwohnsitzActivity])
    startActivity(goback)
  }
}

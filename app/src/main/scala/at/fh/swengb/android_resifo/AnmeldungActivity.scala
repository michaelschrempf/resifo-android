package at.fh.swengb.android_resifo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import at.fh.swengb.resifo_android.{MainActivity, R}

/**
  * Created by Kevin on 14.01.2017.
  */
class AnmeldungActivity extends AppCompatActivity{
  override protected def onCreate(savedInstanceState: Bundle): Unit ={
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_anmeldung)
  }
  def nextHauptWohnSitz(view: View):Unit={
    val goTo = new Intent(this,classOf[HauptwohnsitzActivity])
    startActivity(goTo)
  }
  def goBack(view: View):Unit={
    val goBack = new Intent(this,classOf[PersonalActivity])
    startActivity(goBack)
  }
}

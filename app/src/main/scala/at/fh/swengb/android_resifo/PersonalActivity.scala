package at.fh.swengb.android_resifo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import at.fh.swengb.resifo_android.{MainActivity, R}


class PersonalActivity extends AppCompatActivity{
  override protected def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_personal)
  }
  def GoBackHomeScreen(view: View):Unit={
    val homeBack = new Intent(this,classOf[MainActivity])
    startActivity(homeBack)
  }
  def GoToAnmeldung(view: View):Unit={
    val nextPage = new Intent(this,classOf[AnmeldungActivity])
    startActivity(nextPage)
  }
}

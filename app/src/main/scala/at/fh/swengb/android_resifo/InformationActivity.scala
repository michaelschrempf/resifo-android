package at.fh.swengb.android_resifo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import at.fh.swengb.resifo_android.{MainActivity, R}

/**
  * Created by Michael and Kevin but not by Sebastian on 16.01.2017.
  */
class InformationActivity extends AppCompatActivity{
  override protected def onCreate(savedInstanceState: Bundle): Unit ={
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_information)
  }

  def GoBackHome(view: View):Unit={
    val goHome: Intent = new Intent(this,classOf[MainActivity])
    startActivity(goHome)
  }
}

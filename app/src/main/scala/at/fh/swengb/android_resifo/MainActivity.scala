package at.fh.swengb.resifo_android

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import at.fh.swengb.android_resifo.{EditActivity, InformationActivity, OverviewActivity}


class MainActivity extends AppCompatActivity {
  override protected def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)




  }
  def editActivity(view: View){
    val i: Intent = new Intent(this, classOf[EditActivity])
    startActivity(i)
  }
  def impressumActivity(view: View){
    val impressum: Intent = new Intent(this, classOf[InformationActivity])
    startActivity(impressum)
  }
  def downLoad(view: View) {
    val url: Uri = Uri.parse("http://www.graz.at/cms/ziel/313359/DE")
    val urlIntent: Intent = new Intent(Intent.ACTION_VIEW, url)
    startActivity(urlIntent)
  }
  def viewData(view: View){
    val data: Intent = new Intent(this, classOf[OverviewActivity])
    startActivity(data)
  }
}

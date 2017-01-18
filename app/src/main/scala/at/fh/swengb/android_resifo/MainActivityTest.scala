package at.fh.swengb.resifo_android

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import java.util.List
import java.util.jar.Attributes.Name

import android.location.Address
import android.widget.TextView
import at.fh.swengb.android_resifo.{DBHandler, EditActivity, Meldez}


class MainActivityTest extends AppCompatActivity {
  override protected def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    /*val txtView: TextView = findViewById(R.id.test).asInstanceOf[TextView]




    val db: DBHandler = new DBHandler(this)
    // Inserting Shop/Rows
    Log.d("Insert: ", "Inserting ..")

    val entry = db.addMeldez(new Meldez(1, "Michael", "Schrempf"))

    // Reading all shops
    Log.d("Reading: ", "Reading all shops..")
    val meldezs: List[Meldez] = db.getAllMeldezs

    /*for (meldez: Meldez <- meldezs) {
      val log: String = "Id: " + meldez.id + " ,Name: " + meldez.name + " ,Address: " +
        meldez.surname
      Log.d("Meldez: : ", log)



    }*/

    txtView.setText(db.getAllMeldezs().toString)*/
  }


  def editActivity(view: View):Unit = {
    val i = new Intent(this, classOf[EditActivity])
    startActivity(i)
  }

  def downLoad(view: View) {
    val url: Uri = Uri.parse("http://www.graz.at/cms/ziel/313359/DE")
    val urlIntent: Intent = new Intent(Intent.ACTION_VIEW, url)
    startActivity(urlIntent)
  }
}

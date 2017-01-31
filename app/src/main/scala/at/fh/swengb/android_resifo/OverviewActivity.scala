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

  var aDb: SimpleDb = _

  override protected def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_overview)

    aDb = SimpleDb(getApplicationContext)

    val persons : List[Person] = aDb.mkPersonDao().allEntries()

    val person: Person = persons(getIntent.getExtras.getInt("person"))

    val firstName: TextView = findViewById(R.id.tV_vorname).asInstanceOf[TextView]
    val secondName: TextView = findViewById(R.id.tV_nachname).asInstanceOf[TextView]
    val secondName_before: TextView = findViewById(R.id.tV_nachnameVorher).asInstanceOf[TextView]
    val birthDay: TextView = findViewById(R.id.tV_gebDatum).asInstanceOf[TextView]
    val birthLocation: TextView = findViewById(R.id.tV_gebOrt).asInstanceOf[TextView]
    val sex: TextView = findViewById(R.id.tV_geschlecht).asInstanceOf[TextView]
    val religion: TextView = findViewById(R.id.tV_religion).asInstanceOf[TextView]
    val familyStatus: TextView = findViewById(R.id.tV_familienstand).asInstanceOf[TextView]
    val nationality: TextView = findViewById(R.id.tV_staat).asInstanceOf[TextView]
    val on_street: TextView = findViewById(R.id.tV_anStraße).asInstanceOf[TextView]
    val on_streetNumber: TextView = findViewById(R.id.tV_anHausNr).asInstanceOf[TextView]
    val on_stair: TextView = findViewById(R.id.tV_anStiege).asInstanceOf[TextView]
    val on_door: TextView = findViewById(R.id.tV_anTuer).asInstanceOf[TextView]
    val on_plz: TextView = findViewById(R.id.tV_anPLZ).asInstanceOf[TextView]
    val on_city: TextView = findViewById(R.id.tV_anOrt).asInstanceOf[TextView]
    val on_state: TextView = findViewById(R.id.tV_anBundesland).asInstanceOf[TextView]
    val off_street:TextView=findViewById(R.id.tV_abStraße).asInstanceOf[TextView]
    val off_streetNumber:TextView=findViewById(R.id.tV_abHausNr).asInstanceOf[TextView]
    val off_stair:TextView=findViewById(R.id.tV_abStiege).asInstanceOf[TextView]
    val off_door:TextView=findViewById(R.id.tV_abTuer).asInstanceOf[TextView]
    val off_plz:TextView=findViewById(R.id.tV_abPLZ).asInstanceOf[TextView]
    val off_city:TextView=findViewById(R.id.tV_abOrt).asInstanceOf[TextView]
    val off_state:TextView=findViewById(R.id.tV_abBundesland).asInstanceOf[TextView]

    firstName.setText(person.firstName)
    secondName.setText(person.secondName)
    secondName_before.setText(person.secondName_before)
    birthDay.setText(person.birthDay)
    birthLocation.setText(person.birthLocation)
    sex.setText(person.sex)
    religion.setText(person.religion)
    familyStatus.setText(person.familyStatus)
    nationality.setText(person.nationality)
    on_street.setText(person.on_street)
    on_streetNumber.setText(person.on_streetNumber)
    on_stair.setText(person.on_stair)
    on_door.setText(person.on_door)
    on_plz.setText(person.on_plz)
    on_city.setText(person.on_city)
    on_state.setText(person.on_state)
    off_street.setText(person.off_street)
    off_streetNumber.setText(person.off_streetNumber)
    off_stair.setText(person.off_stair)
    off_door.setText(person.off_door)
    off_plz.setText(person.off_plz)
    off_city.setText(person.off_city)
    off_state.setText(person.off_state)

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
    def hauptWohnSitz = new Intent(this, classOf[HauptwohnsitzActivity])
    startActivity(hauptWohnSitz)
  }
  def storeData(view: View) {
    def store: Intent = new Intent(this, classOf[MainActivity])
    startActivity(store)
  }
}

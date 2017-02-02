package at.fh.swengb.android_resifo

import java.security.Timestamp
import java.text.{DateFormat, FieldPosition, ParsePosition, SimpleDateFormat}
import java.util.{Calendar, Date}

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
    val on_foreign: TextView = findViewById(R.id.tV_anAusland).asInstanceOf[TextView]
    val on_owner: TextView = findViewById(R.id.tV_anNameUG).asInstanceOf[TextView]
    val on_main: TextView = findViewById(R.id.tV_anHauptWS).asInstanceOf[TextView]
    val main_street: TextView = findViewById(R.id.tV_hwsStraße).asInstanceOf[TextView]
    val main_streetNumber: TextView = findViewById(R.id.tV_hwsHausNr).asInstanceOf[TextView]
    val main_stair: TextView = findViewById(R.id.tV_hwsStiege).asInstanceOf[TextView]
    val main_door: TextView = findViewById(R.id.tV_abTuer).asInstanceOf[TextView]
    val main_plz: TextView = findViewById(R.id.tV_hwsPLZ).asInstanceOf[TextView]
    val main_city: TextView = findViewById(R.id.tV_hwsOrt).asInstanceOf[TextView]
    val main_state: TextView = findViewById(R.id.tV_hwsBundesland).asInstanceOf[TextView]
    val off_street:TextView=findViewById(R.id.tV_abStraße).asInstanceOf[TextView]
    val off_streetNumber:TextView=findViewById(R.id.tV_abHausNr).asInstanceOf[TextView]
    val off_stair:TextView=findViewById(R.id.tV_abStiege).asInstanceOf[TextView]
    val off_door:TextView=findViewById(R.id.tV_abTuer).asInstanceOf[TextView]
    val off_plz:TextView=findViewById(R.id.tV_abPLZ).asInstanceOf[TextView]
    val off_city:TextView=findViewById(R.id.tV_abOrt).asInstanceOf[TextView]
    val off_state:TextView=findViewById(R.id.tV_abBundesland).asInstanceOf[TextView]
    val off_foreign:TextView=findViewById(R.id.tV_abAusland).asInstanceOf[TextView]
    val date:TextView=findViewById(R.id.tV_datum).asInstanceOf[TextView]

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
    on_foreign.setText(person.on_foreign)
    on_owner.setText(person.on_owner)
    on_main.setText(person.on_main)
    main_street.setText(person.main_street)
    main_streetNumber.setText(person.main_streetNumber)
    main_stair.setText(person.main_stair)
    main_door.setText(person.main_door)
    main_plz.setText(person.main_plz)
    main_city.setText(person.main_city)
    main_state.setText(person.main_state)
    off_street.setText(person.off_street)
    off_streetNumber.setText(person.off_streetNumber)
    off_stair.setText(person.off_stair)
    off_door.setText(person.off_door)
    off_plz.setText(person.off_plz)
    off_city.setText(person.off_city)
    off_state.setText(person.off_state)
    off_foreign.setText(person.off_foreign)
    val today = Calendar.getInstance().getTime()

    // create the date/time formatters
    val minuteFormat = new SimpleDateFormat("mm")
    val hourFormat = new SimpleDateFormat("hh")
    val amPmFormat = new SimpleDateFormat("a")

    val currentHour = hourFormat.format(today)      // 12
    val currentMinute = minuteFormat.format(today)  // 29
    val amOrPm = amPmFormat.format(today)

    val nowFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
    val now = nowFormat.format(today)
    date.setText(now)

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

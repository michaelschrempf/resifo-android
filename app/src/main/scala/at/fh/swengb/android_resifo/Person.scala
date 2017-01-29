package at.fh.swengb.android_resifo

/**
  * Created by lad on 19/01/2017.
  */
object Person {

  def mkRandom: Person = Person(Gen.Aword, Gen.Aword,Gen.Aword,Gen.Aword,Gen.Aword,Gen.Aword,Gen.Aword,Gen.Aword,Gen.Aword,Gen.Aword,Gen.Aword,Gen.Aword,Gen.Aword,Gen.Aword,Gen.Aword,Gen.Aword,Gen.Aword,Gen.Aword,Gen.Aword,Gen.Aword,Gen.Aword,Gen.Aword,Gen.Aword)

}

case class Person(firstName: String, secondName: String, secondName_before: String,
                  birthDay: String, birthLocation: String, sex: String,
                  religion: String, familyStatus: String, nationality: String,
                  on_street: String, on_streetNumber: String, on_stair: String, on_door: String,
                  on_plz: String, on_city: String, on_state: String,
                  off_street: String, off_streetNumber: String, off_stair: String, off_door: String,
                  off_plz: String, off_city: String, off_state: String  )

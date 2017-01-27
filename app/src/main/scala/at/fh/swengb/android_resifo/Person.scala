package at.fh.swengb.android_resifo

/**
  * Created by lad on 19/01/2017.
  */
object Person {

  def mkRandom: Person = Person(Gen.Aword, Gen.Aword,null,null,null,null,null,null,null)

}

case class Person(firstName: String, secondName: String, secondName_before: String,
                  birthDay: String,birthLocation: String, sex: String,
                  religion: String, familyStatus: String, nationality: String)

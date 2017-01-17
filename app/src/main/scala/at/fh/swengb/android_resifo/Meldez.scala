package at.fh.swengb.android_resifo

/**
  * Created by micha on 17.01.2017.
  */
import scala.beans.{BeanProperty, BooleanBeanProperty}

//remove if not needed
import scala.collection.JavaConversions._

class Meldez {

  @BeanProperty
  var id: Int = _

  @BeanProperty
  var name: String = _

  @BeanProperty
  var surname: String = _

  def this(id: Int, name: String, surname: String) = {
    this()
    this.id = id
    this.name = name
    this.surname = surname
  }

}

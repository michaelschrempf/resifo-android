package at.fh.swengb.android_resifo

import android.app.ListActivity
import android.os.Bundle
import android.view.View
import android.widget.{ArrayAdapter, ListView}

import scala.collection.JavaConversions._

/**
  * Created by Michael on 19/01/2017.
  */
class MyListActivity extends ListActivity {

  var aDb: SimpleDb = _

  override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)

    aDb = SimpleDb(getApplicationContext)

    aDb.mkPersonDao().updateTable()
    aDb.mkPersonDao().insert(Person("Michael","Schrempf",null,"16.12.1994",
                                    "Graz","Männlich","römisch-katholisch","ledig","Österreich"))



    val persons: List[Person] = aDb.mkPersonDao().allEntries()

    //val ps : List[Person] = List(Person("aaa","b"))

    val pA = new ArrayAdapter[Person](this, android.R.layout.simple_list_item_1, persons)

    setListAdapter(pA)
  }

  override def onListItemClick(l: ListView, v: View, pos: Int, i: Long) {
    println("Pos: " + pos + " clicked")
    val p  = l.getAdapter.getItem(pos).asInstanceOf[Person]
    println(p)
  }
}
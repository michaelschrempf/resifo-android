package at.fh.swengb.android_resifo

import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.{ArrayAdapter, ListView}

import scala.collection.JavaConversions._
class MyListActivity extends ListActivity {

  var aDb: SimpleDb = _

  override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)

    aDb = SimpleDb(getApplicationContext)


    val persons: List[String] = aDb.mkPersonDao().allEntriesOnlyFirstAndSecondname()

    val pA = new ArrayAdapter[String](this, android.R.layout.simple_list_item_1, persons)
    setListAdapter(pA)

    //val persons2: List[Person] = aDb.mkPersonDao().allEntries()

    //val ps : List[Person] = List(Person("aaa","b"))

    val pA = new ArrayAdapter[String](this, android.R.layout.simple_list_item_1, persons)

    setListAdapter(pA)
  }

  override def onListItemClick(l: ListView, v: View, pos: Int, i: Long) {
    println("Pos: " + pos + " clicked")
    val p  = l.getAdapter.getItem(pos).asInstanceOf[String]
    val data: Intent = new Intent(this, classOf[OverviewActivity])
    data.putExtra("person", pos)
    startActivity(data)
  }
}
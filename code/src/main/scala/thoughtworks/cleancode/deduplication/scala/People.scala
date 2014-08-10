package thoughtworks.cleancode.deduplication.scala

case class Person(age:Int, name:String, gender:String)

class People {
  val persons: List[Person] = List(Person(22, "zhangyi", "male"));

}

object People {
  val persons: List[Person] = List(Person(22, "zhangyi", "male"));

  def find(predicate:Person => Boolean):List[Person] = {
    var result:List[Person] = Nil
    for (p <- persons) {
      if (predicate(p)) result = p::result
    }
    result
  }


  val personWith23age = find(_.age == 23)
  val personsBelow23age = find(_.age < 23)
  val personsWithName = find(_.name.equals("zhangyi"))

  val resultByAge = persons.map(p => (p.name, p.age))
    .filter(_._1 == "zhangyi")
  persons.takeWhile(p => p.name == "zhangyi")
}


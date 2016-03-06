import org.scalatestplus.play._

import scala.collection.mutable.Stack

class SyntaxSpec extends PlaySpec {

  "Variable" must {
    "How to use Scala test" in {
      1 mustBe 1
      2 mustBe 2
      "test" mustBe "test"
      //"test" mustBe "test2" // テスト落ちる
    }

    "Basics" in {
      // 基本宣言
      // varは通常の変数。
      // 末尾にセミコロンはいらない。あってもよい
      var a: Int = 1
      a mustBe 1;

      // 複数の行を一度に書くときにセミコロンを使う。
      a = 2; a mustBe 2

      // 文字列。
      var b: String = "test"
      b mustBe "test"

      b = "test2"
      b mustBe "test2"

      // valは変更不可の宣言。
      val constInt: Int = 1
      val constString: String = "test"

      // 下記はコンパイルエラーとなる
      //constInt = 2
      //constString = "not modified"
    }

    "型" in {
      // Anyは何でも入る。推奨はもちろんされない。
      var a: Any = "test"
      a mustBe "test"

      // 暗黙の型変換
      var b: String = "test"
      //b = 1 // コンパイルエラー。String型にIntは入らない。
      b += 1 // こっちはいける。
      b mustBe "test1"
      b = b + 2 // これも可能
      b mustBe "test12"
      b = "test" + 3 // これも。暗黙の型変換は可能ということ
      b mustBe "test3"

      // Scalaには、voidではなくUnit型がある。
      // Unit型を宣言する場合は () を使う。
      var c:Unit = ()
      c mustBe ()
      //c mustBe null // エラー。値がないことを示す () は、 null とは異なる。

    }

    "分割代入" in {
      val (a, b) = (1, 2)
      a mustBe 1
      b mustBe 2
    }

    "match" in {
      val value = 1
      val result = value match {
        case 1 => "a1b"
        case 2 => "a2c"
        case _ => "default"
      }
      result mustBe "a1b"
    }

    "throw NoSuchElementException if an empty stack is popped" in {
      val emptyStack = new Stack[Int]
      a [NoSuchElementException] must be thrownBy {
        emptyStack.pop()
      }
    }
  }
}
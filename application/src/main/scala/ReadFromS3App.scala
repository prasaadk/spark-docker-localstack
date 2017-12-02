import org.apache.spark.sql.SparkSession

import scala.io.Source

/**
  * Created by prasaadk on 02/12/2017.
  */
object ReadFromS3App {
  def main(args: Array [String]): Unit = {
    val session = SparkSession.builder()
                              .master("local")
                              .appName("SparkApp")
                              .getOrCreate();

    val df = session.read.textFile("s3a://bucket-list/").toDF()

    df.foreach(row => {println (row)})
  }
}

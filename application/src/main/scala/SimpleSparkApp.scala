package demo

import org.apache.spark.sql.SparkSession

import scala.io.Source

/**
  * Created by prasaadk on 02/12/2017.
  */
object SimpleSparkApp {
  def main(args: Array [String]): Unit = {
    val session = SparkSession.builder()
                              .master("local")
                              .appName("SparkApp")
                              .getOrCreate();
    val data = Source.fromInputStream(getClass.getResourceAsStream("/data.csv")).mkString

    val rdd = session.sparkContext.parallelize(data.toList)
    rdd.foreach(print)
  }
}

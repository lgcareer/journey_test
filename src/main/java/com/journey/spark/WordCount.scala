package com.journey.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
 * WordCount
 * spark-submit --class com.journey.WordCount --master spark://journey:7077 jouney-1.0-SNAPSHOT.jar /journey/words.txt /journey/out
 * Created by qiaozhanwei on 2018-04-18.
 */
object WordCount {

  def main(args: Array[String]) {

    val conf = new SparkConf()
      .setAppName("WordCount")


    val sc = new SparkContext(conf)


    sc.textFile(args(0)).flatMap(_.split("\\s+")).map((_,1)).reduceByKey(_+_).saveAsTextFile(args(1))

    sc.stop()
  }
}

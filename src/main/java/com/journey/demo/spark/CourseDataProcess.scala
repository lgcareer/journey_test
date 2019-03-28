package com.journey.demo.spark

import com.alibaba.fastjson.JSON
import com.journey.demo.data.domain.{Course, Student}
import org.apache.spark.{SparkConf, SparkContext}


/**
 * input： /opt/data/course/20180812
 * output：/opt/data/out/course/20180812
 */
object CourseDataProcess {

  def main(args: Array[String]) {
    val inputPath = args(1)
    val outputPath = args(2)
    val conf = new SparkConf()
      .setAppName("CourseDataProcess")


    val sc = new SparkContext(conf)


    sc.textFile(inputPath + args(0)).map(line =>{
      val course = JSON.parseObject(line, classOf[Course])
      course.getCno+","+course.getCname+","+course.getTno
    }).saveAsTextFile(outputPath + args(0))

    sc.stop()
  }
}

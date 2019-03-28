package com.journey.demo.spark

import com.alibaba.fastjson.{JSON, JSONObject}
import com.journey.demo.data.domain.Student
import org.apache.spark.{SparkConf, SparkContext}


/**
 * input： /opt/data/student/20180812
 * output：/opt/data/out/student/20180812
 */
object StudentDataProcess {

  def main(args: Array[String]) {
    val inputPath = args(1)
    val outputPath = args(2)
    val conf = new SparkConf()
      .setAppName("StudentDataProcess")


    val sc = new SparkContext(conf)


    sc.textFile(inputPath+args(0)).map(line =>{
      val student = JSON.parseObject(line, classOf[Student])
      student.getSno+","+student.getSname+","+student.getSsex
    }).saveAsTextFile(outputPath+args(0))

    sc.stop()
  }
}

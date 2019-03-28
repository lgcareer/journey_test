package com.journey.demo.mr;

import com.alibaba.fastjson.JSON;
import com.journey.demo.data.domain.Sc;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * intput    /opt/data/sc/
 * output    /opt/data/out/sc/
 */
public class ScDataProcess {

    /**
     * 根据空格对单词进行分割
     */
    private static class WCMapper extends Mapper<LongWritable,Text,Text,NullWritable>{
        private static Text word = new Text();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            String line = value.toString();

            if (line == null || line.length()==0){
                return;
            }
            Sc sc = JSON.parseObject(line, Sc.class);

            String str = sc.getSno() + "," + sc.getCno() + "," + sc.getScore();
            word.set(str);
            context.write(word, NullWritable.get());
        }
    }


    public static void main(String[] args) throws Exception{

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf,"ScDataProcess");
        job.setJarByClass(ScDataProcess.class);
        //设置输入格式
        job.setInputFormatClass(TextInputFormat.class);

        //设置mapper的key输入格式
        job.setMapperClass(WCMapper.class);
        //设置mapper的value输入格式
        //设置reducer的key输入格式
        job.setMapOutputKeyClass(Text.class);
        //设置reducer的value输入格式
        job.setMapOutputValueClass(NullWritable.class);


        //设置输入
        FileInputFormat.addInputPath(job, new Path(args[1] + args[0]));
        //设置输出
        FileOutputFormat.setOutputPath(job, new Path(args[2]+args[0]));

        job.waitForCompletion(true);

    }
}

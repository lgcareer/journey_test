package com.journey.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;

/**
 *
 * 任务进行提交
 * hadoop jar jouney-1.0-SNAPSHOT.jar com.journey.hadoop.mr.wordcount.WordCount /journey/words.txt /journey/out
 *
 * 可以将core-site.xml和hdfs-site.xml放到resources下面
 * 以及log4j.properties
 * 可以在本地进行测试
 * Created by qiaozhanwei on 2018-06-03.
 */
public class WordCount {

    /**
     * 根据空格对单词进行分割
     */
    private static class WCMapper extends Mapper<LongWritable,Text,Text,IntWritable>{
        private static final IntWritable one = new IntWritable(1);
        private static Text word = new Text();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            String[] splits = value.toString().split("\\s+");
            for (String str : splits){
                word.set(str);
                context.write(word,one);
            }
        }
    }

    /**
     * 对单词进行累加
     */
    private static class WCReducer extends Reducer<Text,IntWritable,Text,IntWritable>{

        private static final IntWritable result = new IntWritable();
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int count = 0;

            for (IntWritable iw : values){
                count += iw.get();
            }
            result.set(count);
            context.write(key,result);
        }
    }

    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        for(String str:otherArgs){
            System.out.println("-----------arg-----"+str);
        }
        Job job = Job.getInstance(conf,"WordCount");
        job.setJarByClass(WordCount.class);
        //设置输入格式
        job.setInputFormatClass(TextInputFormat.class);

        //设置mapper的key输入格式
        job.setMapperClass(WCMapper.class);
        //设置mapper的value输入格式
        job.setReducerClass(WCReducer.class);

        //设置reducer的key输入格式
        job.setMapOutputKeyClass(Text.class);
        //设置reducer的value输入格式
        job.setMapOutputValueClass(IntWritable.class);

        //设置Mapper类
        job.setOutputKeyClass(Text.class);
        //设置Reducer类
        job.setOutputValueClass(IntWritable.class);

        //设置输入
        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
        //设置输出
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));

        job.waitForCompletion(true);

    }
}

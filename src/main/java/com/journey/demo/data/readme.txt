注意：/opt/data本地和hdfs的都需要授予写的权限的，要不就会报错
流程定义的workflow_define_json字段类型设置为longtext



1,GenerateData生成数据
/opt/data/student/前一天的数据
/opt/data/course/前一天的数据
/opt/data/sc/前一天的数据


2,将数据上传到hdfs上
这里在用户自定义参数中配置

hdfs dfs -put /opt/data/student/${前一天的数据} /opt/data/student
hdfs dfs -put /opt/data/course/${前一天的数据} /opt/data/course
hdfs dfs -put /opt/data/sc/${前一天的数据} /opt/data/sc

3,业务处理
见com.journey.demo.spark.StudentDataProcess
见com.journey.demo.spark.CourseDataProcess
见com.journey.demo.mr.ScDataProcess

4,创建表
CREATE TABLE t_student (sno int,sname string,ssex string) PARTITIONED BY (ds string) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',';
CREATE TABLE t_course (cno int,cname string,tno int) PARTITIONED BY (ds string) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',';
CREATE TABLE t_sc (sno int,cno int,score int) PARTITIONED BY (ds string) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',';

5,加载数据
load data inpath '/opt/data/out/student/20180812' into table t_student partition(ds='20180812');
load data inpath '/opt/data/out/course/20180812' into table t_course partition(ds='20180812');
load data inpath '/opt/data/out/sc/20180812' into table t_sc partition(ds='20180812');



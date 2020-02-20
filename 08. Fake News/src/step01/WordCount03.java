package step01;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCount03 {

	public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {

		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();

		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
			
				String[] unitSet = value.toString().split(",");
				
					word.set(unitSet[2].replace("\"", ""));
					context.write(word, one);
		}	
	}

	public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
		private IntWritable result = new IntWritable();

		//
		public void reduce(Text key, Iterable<IntWritable> values, Context context)
				throws IOException, InterruptedException {
			int sum = 0;
			for (IntWritable val : values) {
				sum += val.get(); //(key, 1) (key, 1)
			}
			result.set(sum);
			context.write(key, result);	
		}
	}

	public static void main(String[] args) throws Exception {
		//설정 정보 보유 객체
		Configuration conf = new Configuration();
		
		//로직 처리 기능 : job
		//job별 설정 정보와 고유한 이름 부여하는 설정
		Job job = Job.getInstance(conf, "word count");
		
		// job의 기능 : map + reduce
		job.setJarByClass(WordCount03.class);
		job.setMapperClass(TokenizerMapper.class);
		//job.setCombinerClass(IntSumReducer.class);
		job.setReducerClass(IntSumReducer.class);
		
		//집계하고자 하는 데이터 형태
		//IntWritable : 카운팅 가능한 집계 수치값 표현하는 타입
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		//데이터 제공 파일
		FileInputFormat.addInputPath(job, new Path(args[0]));
		//map & reduce 직후 결과값 출력하는 목적지
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//자바 실행 환경 강제 종료하는 API
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
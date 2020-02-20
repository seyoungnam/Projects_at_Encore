/* HDFS의 데이터를 활용 가능한 프로그래밍 개발 기술
 * 1. 전제 조건 : hdfs에 저장된 데이터 여야 함
 * 2. 기본 기질
 * 		1. 제공 받는 API 사용
 * 		2. 맵과 리듀스를 구분지어서 개발 권장
 * 3. 기본 로직
 * 		1. map : 개발자가 코드로 제시한 구분자에 맞게 단순 데이터 분해
 * 		2. reduce : 분해된 단어의 빈도수 가공(집계)
 * 4. 하둡 실행 관점에서 실행시 필요한 주체
 * 		1. map 기능 : TokenizerMapper
 * 		2. reduce 기능 : IntSumReducer
 * 		3. 데이터 근원지 정보 및 파일 : args[0], 데이터 목적지 정보 : args[1]
 * 			: 실행시 가변적인 위치값 적용 가능한 문법인 main의 argument 사용
 * 			: 주의사항 - 목적지는 hdfs에 미존재해야만 함
 * 		4. 실행 필수 - main()
 * 
 * 5. 실행 결과에 따른 상식
 * 	- 목적지에 _SUCCESS
 * 
 * 6. hdfs 사용 명령어
 * 	1. 디렉토리 확인 명령어 : hdfs dfs -ls /
 * 	2. 디렉토리 생성 명령어 : hdfs dfs -mkdir /생성디렉토리명
 * 	3. 디렉토리 삭제 명령어 : hdfs dfs -rm -r /디렉토리명
 * 	4. 디렉토리 파일 위치 변경 명렁어 : hdfs dfs -mv /원본파일위치및파일명 /이동시키고자하는위치
 * 	5. 파일을 hdfs에 생성(저장)하는 명령어 : hdfs dfs -put 저장하고자하는 파일 /저장위치
 * 	6. 파일을 획득(가져오는) 명령어 : hdfs dfs -get /가져오고자하는파일위치및파일명 저장할로컬위치
 * 	7. 파일의 내용 확인 명령어 : hdfs dfs -cat /보고자하는파일위치및파일명
 * 	8. 
 */


package step01;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCount01 {

	public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {

		private final static IntWritable one = new IntWritable(1); //분해시 분해된 데이터 옆에 집계 전에 적용시키는 수치 데이터 타입과 수치값
		private Text word = new Text();

		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
			//라인 단위로 구분되어 전송된 value의 문자열을 여백을 기준으로 구분
			StringTokenizer itr = new StringTokenizer(value.toString());
			while (itr.hasMoreTokens()) { //한 라인에 몇개의 단어가 있는지에 대한 불명확, 따라서 반복문은 while
				word.set(itr.nextToken()); //구분된 단어를 hadoop에서 처리 가능한 Text 타입으로 변환
				context.write(word, one); //(단어, 1)
			}
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
		job.setJarByClass(WordCount01.class);
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